
package com.customerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

@ServerEndpoint("/CustomerService/{username}/{chatroom}")
public class CustomerService {

	private static final Set<Session> allsession = Collections.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void onOpen(@PathParam("username") String username, Session usersession,
			@PathParam("chatroom") String chatroom) {
		if (allsession.size() < 2)//限制為兩個session
			allsession.add(usersession);
		System.out.println("目前客服 " + allsession.size() + " 人");
		System.out.println(
				"SessionID " + usersession.getId() + " with userName: " + username + " connected! in the " + chatroom);
//		for (Session s : allsession) {
//			if (s.isOpen()) {
//				s.getAsyncRemote().sendText("{'username':" + username + ", 'message':' �[�J��� !','type':'sysMsg'}");
//				System.out.println("{'username':" + username + ", 'message':' �[�J��� !','type':'sysMsg'}");
//			}
//
//		}
	}

	@OnMessage
	public void onMessage(Session user, String msg, @PathParam("username") String username) throws IOException {
		ArrayList<Session> a = new ArrayList<Session>();//用來存session 的list物件
		
		for (Session s : allsession) {//將所有session加入arraylist
			a.add(s);
		}
		for (int i = 0; i < allsession.size(); i++) {
			for (int z = 0; z < a.size(); z++) {
				if (user.getId().equals(a.get(z).getId())) { //如果發訊息的session與allsession裡面任一相同就發訊息給allsession裡所有session
					if (a.get(i).isOpen()) {
						a.get(i).getBasicRemote().sendText(msg);
					}
				}

			}

		}

		System.out.println("Message received: " + msg);

	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		e.printStackTrace();
	}

	@OnClose
	public void onClose(Session usersession, CloseReason reason, @PathParam("username") String username) {
		ArrayList<Session> a = new ArrayList<Session>();//用來存session 的list物件
		for (Session s : allsession) {//將所有session加入arraylist
			a.add(s);
		}
		for (Session s : allsession) {
			for (int z = 0; z < a.size(); z++) {
				if (usersession.getId().equals(a.get(z).getId())) { 
					if (s.isOpen()) {
						s.getAsyncRemote().sendText("{\"username\":\"" + username + "\",\"type\":\"sysMsg\",\"message\":\" 離開客服系統!\"}");;
					}
				}
			}
		}
		System.out.println(allsession.remove(usersession));//發送離開消息後再移除
		System.out.println("SessionID: " + usersession.getId() + " userName: " + username + " : " + "IP位址: "
				+ usersession.getRequestURI() + " 離開了 " + reason.getCloseCode().getCode());
	}
}
