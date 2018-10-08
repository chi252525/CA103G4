package android.com.main;

import java.text.DateFormat;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

@WebListener
@ServerEndpoint("/AndroidMyBookingServer/{myName}")
public class AndroidMyBookingServer implements ServletContextListener{
	
	// 用來存放WebSocket客戶端使用者(同步鎖定)
	private static final Set<Session> allSessions = Collections.synchronizedSet(new LinkedHashSet<Session>());
	// 線上訂位的紀錄, 將暫存儲存至這個TreeMap(同步鎖定)
	private static final Map<String,String> resInputMap = Collections.synchronizedSortedMap(new TreeMap<String,String>());
	
	//********************************** ServletContextListener **********************************
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		context.setAttribute("resInputMap", resInputMap);
		context.setAttribute("onlinePeople", allSessions);
		System.out.println("Context setAttribute done!");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("WebSocket Context Destroyed");
		System.out.println("已移除排程!");
	}
	

	//*************************************** WebSocket ********************************************
	@OnOpen
	public void onOpen(@PathParam("myName") String myName, Session userSession, EndpointConfig config)  {
		allSessions.add(userSession);
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(myName + ": 已連線");
		System.out.println("現在在線人數:" + allSessions.size());
		
		userSession.getAsyncRemote().sendObject(resInputMap);
		userSession.getAsyncRemote().sendText("myID="+userSession.getId());
	}
	
	@OnMessage
    public void onMessage(Session userSession, String message) {
		for (Session session : allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
		System.out.println("Message received: " + message);
		JSONObject j;
		try {
			j = new JSONObject(message);
			String seatMapK = (String) j.get("seat");
			String seatMapV = (String) j.get("mem_no");
			Integer status = (Integer) j.get("status");
			if(status == 1) {
				resInputMap.put(seatMapK+":", seatMapV);
				System.out.println("1, Already input a new kV:"+resInputMap);
			}else if(status == 2 || status == 3){
				resInputMap.remove(seatMapK+":");
				System.out.println("2、3, Already remove a new kV:"+resInputMap);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}

	@OnClose
    public void onClose(@PathParam("myName") String myName, Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		Collection<String> col = resInputMap.values();
		while(col.remove(myName));
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));  
    }

}

//@ServerEndpoint("/TogetherWS/{userName}")
//public class TogetherWS {
//	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
//
//	/*
//	 * 如果想取得HttpSession與ServletContext必須實作
//	 * ServerEndpointConfig.Configurator.modifyHandshake()，
//	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
//	 */
//	@OnOpen
//	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
//		connectedSessions.add(userSession);
//		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
//		System.out.println(text);
//	}
//
//	@OnMessage
//	public void onMessage(Session userSession, String message) {
//		for (Session session : connectedSessions) {
//			if (session.isOpen())
//				session.getAsyncRemote().sendText(message);
//		}
//		System.out.println("Message received: " + message);
//	}
//
//	@OnClose
//	public void onClose(Session userSession, CloseReason reason) {
//		connectedSessions.remove(userSession);
//		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
//				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
//		System.out.println(text);
//	}
//
//	@OnError
//	public void onError(Session userSession, Throwable e) {
//		System.out.println("Error: " + e.toString());
//	}
//
//}

