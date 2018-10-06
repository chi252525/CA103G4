package serverEndpoint;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.websocket.CloseReason;

@ServerEndpoint("/MyEchoServer/{myName}")
public class MyEchoServer implements ServletContextListener{
											  
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
 

private static final Map<String,String> resInputMap = Collections.synchronizedSortedMap(new TreeMap<String,String>());

	@Override
	public void contextInitialized(ServletContextEvent sre) {
		 ServletContext context = sre.getServletContext();

				 context.setAttribute("resInputMap", resInputMap);
				 context.setAttribute("onlinePeople", allSessions);
				 System.out.println("Context setAttribute done!");

		
	}
	@Override
	public void contextDestroyed(ServletContextEvent sre) {
		System.out.println("Context Destroyed");
	}
	
	@OnOpen
	public void onOpen(@PathParam("myName") String myName,Session userSession) throws IOException {  
		allSessions.add(userSession);
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(myName + ": 已連線");
		System.out.println("現在在線人數:" + allSessions.size());
		
	}

	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		for (Session session : allSessions) {
			
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
			        
		}
		System.out.println("Message received: " + message);
		JSONObject  j = new JSONObject(message);
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
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){

	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}																	
 
}
