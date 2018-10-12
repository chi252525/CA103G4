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

@ServerEndpoint("/MyEchoServer/{myName}/{date}/{zone}")
public class MyEchoServer implements ServletContextListener{
	
											  //(下面右)利用collectionS來排序、synchronized鎖定來補足Set的不足
private static final Set<Session> onlinePeople = Collections.synchronizedSet(new HashSet<Session>());
    //(上面左)裡面放websockets 的session //Set 快、不重複、亂、被鎖定、不安全。
//-----------------------------------------------------------------------------------------------------------------
	
private static final Map<String,Map<String,Set<String>>> date1 = Collections.synchronizedSortedMap(new TreeMap<String,Map<String,Set<String>>>());
    
private static final Map<String,Set<String>> zoneMS = Collections.synchronizedSortedMap(new TreeMap<String,Set<String>>());
	
private static final Set<String> seatS = Collections.synchronizedSet(new HashSet<String>());
//private static Map<String,Map<String,Set<String>>> date1 = null;
//private static Map<String,Set<String>> zoneMS = null;
//private static Set<String> seatS = null;
//---------------------------------------------------------------------------------------------------------------------
	
private static final Map<String,Set<String>> Mem_name = Collections.synchronizedSortedMap(new TreeMap<String,Set<String>>());
	
private static final Set<String> Mem_seats = Collections.synchronizedSet(new HashSet<String>());
//----------------------------------------------------------------------------------------------------------------------
	@Override
	public void contextInitialized(ServletContextEvent sre) {
		 ServletContext context = sre.getServletContext();
		 		
//				 context.setAttribute("resInputMap", resInputMap);
				 context.setAttribute("date1", date1);
				 System.out.println("Context setAttribute done!");
		
	}
	@Override
	public void contextDestroyed(ServletContextEvent sre) {
		System.out.println("Context Destroyed");
	}
	
	@OnOpen
	public void onOpen(@PathParam("myName") String myName,@PathParam("date") String date,@PathParam("zone") String zone,Session userSession) throws IOException {  //websockets 的session 
		onlinePeople.add(userSession); //線上人數	
//		Mem_seats.clear();
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(myName + ": 已連線");
		System.out.println("日期:" + date);
		System.out.println("用餐時間:" + zone);
		System.out.println("現在在線人數:" + onlinePeople.size());
		
	}

	
	@OnMessage
	public void onMessage(@PathParam("myName") String myName,@PathParam("date") String date,@PathParam("zone") String zone,Session userSession, String message) {
		for (Session session : onlinePeople) {
			
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
			         
		}
		System.out.println("Message received: " + message);
		JSONObject  j = new JSONObject(message);
		String seat = (String) j.get("seat");
//		String mem_no = (String) j.get("mem_no");
		Integer status = (Integer) j.get("status");
		if(status == 1) {
			date1.put(date, zoneMS);
			zoneMS.put(zone, seatS);
			seatS.add(seat+":");
	//-----------------------------------		
			Mem_name.put(myName, Mem_seats);
			Mem_seats.add(seat+":");
			
			Iterator objs = ((date1.get(date)).get(zone)).iterator();
			while (objs.hasNext())
			System.out.println("1, Already input a new kV:" + objs.next());
			
			Iterator objs2 = (Mem_name.get(myName)).iterator();
			while (objs2.hasNext())
			System.out.println(myName + ": Already input a new kV:" + objs2.next());
			
		}else if(status == 2 || status == 3){

			((date1.get(date)).get(zone)).remove(seat+":");
			(Mem_name.get(myName)).remove(seat+":");
			
			Iterator objs = ((date1.get(date)).get(zone)).iterator();
			while (objs.hasNext())
			System.out.println("2、3, Already remove a new kV:" + objs.next());
			
			Iterator objs2 = (Mem_name.get(myName)).iterator();
			while (objs2.hasNext())
			System.out.println(myName + ": Already remove a new kV:" + objs2.next());
		
		}
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(@PathParam("myName") String myName,@PathParam("date") String date,@PathParam("zone") String zone,Session userSession, CloseReason reason) {
		String str = "";
		onlinePeople.remove(userSession);
		Iterator objs2 = (Mem_name.get(myName)).iterator();
		while (objs2.hasNext()) 
		str += (String)objs2.next();
		String [] tokens = str.split(":");
		for (String token:tokens) {
			((date1.get(date)).get(zone)).remove(token+":");
			System.out.println("token remove:" + token+":");
		}
		Mem_name.remove(myName);
		System.out.println(Mem_name.containsKey(myName));
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
																		//get code 1000(正常關閉)
	}
 
}
