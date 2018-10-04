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
import javax.websocket.server.ServerEndpoint;

@WebListener
@ServerEndpoint("/AndroidMyBookingServer")
public class AndroidMyBookingServer implements ServletContextListener{
	
	// 用來存放WebSocket客戶端使用者(同步鎖定)
	private static final Set<Session> allSessions = Collections.synchronizedSet(new LinkedHashSet<Session>());
	// 線上訂位的紀錄, 將暫存儲存至這個TreeMap(同步鎖定)
	private static final Map<String,String> bookingMap = Collections.synchronizedSortedMap(new TreeMap<String,String>());
	// 伺服器時間的推播排程器
	Timer timer;
	
	//********************************** ServletContextListener **********************************
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		context.setAttribute("bookingMap", bookingMap);
		
		timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				sendTimeToAll(allSessions);
			}
		};
		timer.scheduleAtFixedRate(task, new java.util.Date(), 1000);
		System.out.println("ServletContextListener通知: 已建立時間推播排程 與 bookingList");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("WebSocket Context Destroyed");
		timer.cancel();
		System.out.println("已移除排程!");
	}
	

	//*************************************** WebSocket ********************************************
	@OnOpen
	public void onOpen(Session userSession, EndpointConfig config)  {
		allSessions.add(userSession);
		System.out.println(userSession.getId() + ": 已連線");
		userSession.getAsyncRemote().sendText("myID="+userSession.getId());
	}
	
	@OnMessage
    public void onMessage(Session userSession, String message) {
		for (Session session : allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
		//@true@1-2@0
		String[] str = message.split("@");
		String b = str[1];
		String seat = str[2];
		String id = str[3];
		if(b.equals("true")){
			bookingMap.put(seat, id);
		} else if (b.equals("false")){
			bookingMap.remove(seat);
		}
		System.out.println("Message received: " + message);
		System.out.println("bookingMap: " + bookingMap);
    }
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}

	@OnClose
    public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));  
    }

	private void sendTimeToAll(Set<Session> allSessions) {
		for (Session aUserSession : allSessions) {
			DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
			String clock = df.format(new java.util.Date());
			aUserSession.getAsyncRemote().sendText(clock +"【在線人數:"+allSessions.size()+"】");
		}
	}

}
