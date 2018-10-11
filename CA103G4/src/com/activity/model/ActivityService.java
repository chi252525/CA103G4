package com.activity.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class ActivityService {
	private ActivityDAO_interface dao;
	
	public ActivityService() {
		dao=new ActivityDAO();
	}
	
	public ActivityVO addActivity(String coucat_No,String act_Cat,
	String act_Name,byte[] act_Carousel,byte[] act_Pic,String act_Content,Timestamp act_PreAddTime,Timestamp act_PreOffTime ,
	Timestamp act_Start,Timestamp act_End) {
		ActivityVO activityVO=new ActivityVO();
		activityVO.setCoucat_No(coucat_No);
		activityVO.setAct_Cat(act_Cat);
		activityVO.setAct_Name(act_Name);
		activityVO.setAct_Carousel(act_Carousel);
		activityVO.setAct_Pic(act_Pic);
		activityVO.setAct_Content(act_Content);
		activityVO.setAct_PreAddTime(act_PreAddTime);
		activityVO.setAct_PreOffTime(act_PreOffTime);
		activityVO.setAct_Start(act_Start);
		activityVO.setAct_End(act_End);
		//為了讓排程器抓到資料 故設定
		String next_act_No=dao.insert(activityVO);
		activityVO.setAct_No(next_act_No);
		activityVO.setAct_Status(0);
		return activityVO;
		
	}
	
	public ActivityVO updateActivity(String act_No,String coucat_No,String act_Cat,
			String act_Name,byte[] act_Carousel,byte[] act_Pic,String act_Content,Timestamp act_PreAddTime,Timestamp act_PreOffTime 
			) {
		ActivityVO activityVO=new ActivityVO();
		activityVO.setAct_No(act_No);
		activityVO.setCoucat_No(coucat_No);
		activityVO.setAct_Cat(act_Cat);
		activityVO.setAct_Name(act_Name);
		activityVO.setAct_Carousel(act_Carousel);
		activityVO.setAct_Pic(act_Pic);
		activityVO.setAct_Content(act_Content);
		activityVO.setAct_PreAddTime(act_PreAddTime);
		activityVO.setAct_PreOffTime(act_PreOffTime);
		dao.update(activityVO);
		return activityVO;
	}

	public List<ActivityVO> getByDateBetween(Timestamp act_Start1,Timestamp act_Start2,Timestamp act_End1,Timestamp act_End2) {
		return dao.findByDate_between(act_Start1, act_Start2, act_End1, act_End2);
	}
	
	public List<ActivityVO> getByAct_Cata(String act_Cata) {
		return dao.findByAct_Cata(act_Cata);
	}
	public ActivityVO getOneActivity(String act_No) {
		return dao.findByPrimaryKey(act_No);
	}
	public List<ActivityVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	public List<ActivityVO> findHotAct(){
		return dao.findHotAct();
	}
	public List<ActivityVO> findNewAct(){
		return dao.findNewAct();
	}
	
	public void updateAct_Views(String act_No) {
		dao.updateAct_Views(act_No);
		
	}
	
	public void updateAct(String act_No,Integer act_Status,ActivityVO activityVO) {
		dao.updateAct(act_No,act_Status,activityVO);
		
	}
	public List<ActivityVO> getAll(){
		return dao.getAll();
	}
	
		
	
	
}
