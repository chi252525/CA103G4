

package com.customerService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/CustomerService/{username}/{chatroom}")
public class CustomerService {

	private static final Set<Session> allsession = Collections.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void onOpen(@PathParam("username") String username, Session usersession, @PathParam("chatroom")String chatroom) {
		allsession.add(usersession);
		System.out.println("SessionID " + usersession.getId() + " with userName: " + username + " connected! in the "+ chatroom);
//		for (Session s : allsession) {
//			if (s.isOpen()) {
//				s.getAsyncRemote().sendText("{'username':" + username + ", 'message':' �[�J��� !','type':'sysMsg'}");
//				System.out.println("{'username':" + username + ", 'message':' �[�J��� !','type':'sysMsg'}");
//			}
//
//		}
	}

	@OnMessage
	public void onMessage(Session user, String msg) {
		for (Session s : allsession) {
			if (s.isOpen()) {
				s.getAsyncRemote().sendText(msg);
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
		allsession.remove(usersession);
		for (Session s : allsession) {
			if (s.isOpen()) {
				//���H���u�����t�ΰT��
				s.getAsyncRemote().sendText("{\"username\":\""+username+"\",\"type\":\"sysMsg\",\"message\":\" ���}�ȪA�t��!\"}");
			}
		}
		
		
		System.out.println("SessionID: " + usersession.getId() + " userName: " + username + " : " + "IP��}: "
				+ usersession.getRequestURI() + " ���}�F " + reason.getCloseCode().getCode());
	}

}
