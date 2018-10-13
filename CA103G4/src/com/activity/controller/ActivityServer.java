package com.activity.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import org.json.JSONObject;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;

@WebListener
@ServerEndpoint("/ActivityServer")
public class ActivityServer implements ServletContextListener{

	// 用來存放WebSocket客戶端使用者(同步鎖定)
		private static final Set<Session> allSessions = Collections.synchronizedSet(new LinkedHashSet<Session>());
		Timer timer;
		@Override
		public void contextInitialized(ServletContextEvent event) {
			ServletContext context = event.getServletContext();

			 timer = new Timer();
			ActivityService actSvc = new ActivityService();
			List<ActivityVO> actloadlist = actSvc.getAll();
			context.setAttribute("actloadlist", actloadlist);

			SimpleDateFormat time_format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					long nowTime = System.currentTimeMillis();
					long onTime = 0;// 排程器的預計上架時間
					long offTime = 0;// 排程器的預計下架時間

					System.out.println("************廣告排程檢查start**************");
					@SuppressWarnings("unchecked")
					List<ActivityVO> activityList = (List<ActivityVO>) context.getAttribute("actloadlist");
					System.out.println("activityList"+activityList.size());
					if (activityList.size() != 0) {
							
						ActivityVO removeElem= null;
						Iterator actVOs = activityList.iterator();
						while (actVOs.hasNext()) {
							ActivityVO actVO=(ActivityVO) actVOs.next();
							if (actVO.getAct_PreAddTime() != null && actVO.getAct_PreOffTime() != null) {
//								System.out.println("進到for");
								onTime = actVO.getAct_PreAddTime().getTime();
								offTime = actVO.getAct_PreOffTime().getTime();
	//
								System.out.println(actVO.getAct_No() +"狀態"+actVO.getAct_Status() +":執行時間：" + nowTime + "("
										+ time_format.format(nowTime) + ")；預計上架時間："
										+ onTime + "(" + time_format.format(onTime) + ")；預計下架時間：" + offTime + "("
										+ time_format.format(offTime) + ")");
								// 上架
							
								if ( ((nowTime-1000) <= onTime && onTime <= nowTime) &&actVO.getAct_Status()==0) {
									actSvc.updateAct(actVO.getAct_No(), 0, actVO);
									System.out.println("*********已順利將" + actVO.getAct_No() + "上架了*************");
									actVO.setAct_Status(1);
									sendNewActivityToAll(allSessions,actVO);
									System.out.println(activityList.size());
								// 下架
									
								} else if (((nowTime-1000) <= offTime && offTime <= nowTime)&&actVO.getAct_Status()==1) {
									actSvc.updateAct(actVO.getAct_No(), 1, actVO);
									System.out.println("*********已順利將" + actVO.getAct_No() + "下架了*************");
									removeElem=actVO;
									 if(removeElem.equals(actVOs.next())){
										 actVOs.remove();
							            }
									

								}

							}
						}

					}
				}

			};
			Date start_time = new Date();
			@SuppressWarnings("deprecation")
			Calendar cal = new GregorianCalendar(2018, start_time.getMonth(), start_time.getDate(), start_time.getHours(),
					start_time.getMinutes(), 0);
			timer.scheduleAtFixedRate(task, cal.getTime(), 1 * 20 * 1000);
			System.out.println("開啟伺服器時間：" + time_format.format(start_time) + ";廣告排程器時間:" + cal.getTime());
		
			
		}
		@Override
		public void contextDestroyed(ServletContextEvent event) {
			System.out.println("WebSocket Context Destroyed");
			timer.cancel();
			System.out.println("已移除廣告排程器!");
		}
		//*************************************** WebSocket ********************************************/
		
		@OnOpen
		public void onOpen(Session userSession, EndpointConfig config)  {
			allSessions.add(userSession);
			System.out.println(userSession.getId() + ": 已連線");
			userSession.getAsyncRemote().sendText("myID="+userSession.getId());
		}
		@OnClose
	    public void onClose(Session userSession, CloseReason reason) {
			allSessions.remove(userSession);
			System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));  
	    }
		private void sendNewActivityToAll(Set<Session> allSessions,ActivityVO actVO) {
			for (Session aUserSession : allSessions) {
				DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
				String clock = df.format(new java.util.Date());
				JSONObject obj = new JSONObject();
				obj.put("act_Name", actVO.getAct_Name());
				aUserSession.getAsyncRemote().sendObject(obj);
				
			}
		}
		
		
	
}
