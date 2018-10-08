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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@WebListener
@ServerEndpoint("/AndroidMyBookingServer/{myName}")
public class AndroidMyBookingServer implements ServletContextListener{
	
	// 用來存放WebSocket客戶端使用者(同步鎖定)
	private static final Set<Session> allSessions = Collections.synchronizedSet(new LinkedHashSet<Session>());
	// 線上訂位的紀錄, 將暫存儲存至這個TreeMap(同步鎖定)
	private static final Map<String,String> resInputMap = Collections.synchronizedSortedMap(new TreeMap<String,String>());
	Gson gson = new Gson();
	
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
		
		// 手機端新連線進入時會將context裡的map資訊推播出去
		Set<String> set = resInputMap.keySet();
        Iterator<String> it = set.iterator();
        StringBuilder sb = new StringBuilder();
        while(it.hasNext()) {
    		Object myKey = it.next();
            sb.append(myKey.toString().substring(0,myKey.toString().length()-1)+":");
        }
        userSession.getAsyncRemote().sendText(sb.toString()+"add");
	}
	
	@OnMessage
    public void onMessage(@PathParam("myName") String myName, Session userSession, String message) {
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
//			e.printStackTrace();
		}
		
    }
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}

	@OnClose
    public void onClose(@PathParam("myName") String myName, Session userSession, CloseReason reason) {
		
		// 會員離線時通知其他人更新座位頁面
		sendMessage(myName);
		
        // 會員離線時清空佔存map裡的該會員相關資訊
 		Collection<String> col = resInputMap.values();
 		while(col.remove(myName));
		
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));  
		
    }
	
	public void sendMessage(String myName) {
		for (Session session : allSessions) {
			if (session.isOpen()) {
				Set<String> set = resInputMap.keySet();
		        Iterator<String> it = set.iterator();
		        StringBuilder sb = new StringBuilder();
		        while(it.hasNext()) {
		    		Object myKey = it.next();
		    		if((resInputMap.get(myKey)).equals(myName))
		    			sb.append(myKey.toString().substring(0,myKey.toString().length()-1)+":");
		        }
		        session.getAsyncRemote().sendText(sb.toString()+"clear");
			}
		}
	}

}

