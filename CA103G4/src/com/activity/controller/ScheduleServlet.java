package com.activity.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScheduleServlet {
	private static final long serialVersionUID = 1L;
    
    public ScheduleServlet () {
        super();
    }
 
    Timer timer ; 
    
   public void init() {
	   timer = new Timer();
	   
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				
				
				
				
				
				
			}
			
			
			
		};
	   
	   
   }
   
   public void destroy() {
		timer.cancel();
		System.out.println("排程器結束");
	}
	
	
	
	
}
