package com.activity.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;

public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ScheduleServlet() {
		super();
	}

	Timer timer;

	public void init() {
		timer = new Timer();
		ActivityService actSvc = new ActivityService();
		List<ActivityVO> list = actSvc.getAll();
		ServletContext context = getServletContext();
		context.setAttribute("list", list);

		SimpleDateFormat time_format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				long nowTime = System.currentTimeMillis();
				long onTime = 0;// 排程器的預計上架時間
				long offTime = 0;// 排程器的預計下架時間

				System.out.println("************start**************");
				@SuppressWarnings("unchecked")
				List<ActivityVO> activityList = (List<ActivityVO>) context.getAttribute("list");
				if (activityList.size() != 0) {

					for (ActivityVO actVO : activityList) {
						if (actVO.getAct_PreAddTime() != null && actVO.getAct_PreOffTime() != null) {

							onTime = actVO.getAct_PreAddTime().getTime();
							offTime = actVO.getAct_PreOffTime().getTime();

							System.out.println(actVO.getAct_No() + "=執行時間：" + nowTime + "("
									+ time_format.format(nowTime) + ")；預計上架時間："
									+ onTime + "(" + time_format.format(onTime) + ")；預計下架時間：" + offTime + "("
									+ time_format.format(offTime) + ")");
							// 上架
							if ((nowTime - 5000) == onTime) {
								actSvc.updateAct(actVO.getAct_No(), 1, actVO);
								System.out.println("*********已順利將" + actVO.getAct_No() + "上架了*************");
								activityList.remove(actVO);
								
							// 下架
							} else if ((nowTime - 5000) == offTime) {
								actSvc.updateAct(actVO.getAct_No(), 0, actVO);
								System.out.println("*********已順利將" + actVO.getAct_No() + "下架了*************");
								activityList.remove(actVO);

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
		timer.scheduleAtFixedRate(task, cal.getTime(), 1 * 30 * 1000);
		System.out.println("開啟伺服器時間：" + time_format.format(start_time) + ";排程器時間:" + cal.getTime());
	}

	public void destroy() {
		timer.cancel();
		System.out.println("廣告排程器結束");
	}

}
