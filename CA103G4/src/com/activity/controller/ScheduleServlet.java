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
		SimpleDateFormat time_format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");  
	   
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
			
			
			
		};
	   
	   
   }
	
	
	
	
}
