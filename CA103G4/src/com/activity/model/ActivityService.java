package com.activity.model;

import java.sql.Timestamp;
import java.util.List;

public class ActivityService {
	private ActivityDAO_interface dao;
	
	public ActivityService() {
		dao=new ActivityDAO();
	}
	
	public ActivityVO addActivity(String coucat_No,String act_Cat,
	String act_Name,byte[] act_Carousel,String act_Cmimetype,byte[] act_Pic,
	String act_Pmimetype,String act_Content,Timestamp act_Start,Timestamp act_End,
	String act_Usecou) {
		ActivityVO activityVO=new ActivityVO();
		activityVO.setCoucat_No(coucat_No);
		activityVO.setAct_Cat(act_Cat);
		activityVO.setAct_Name(act_Name);
		activityVO.setAct_Carousel(act_Carousel);
		activityVO.setAct_Cmimetype(act_Cmimetype);
		activityVO.setAct_Pic(act_Pic);
		activityVO.setAct_Pmimetype(act_Pmimetype);
		activityVO.setAct_Content(act_Content);
		activityVO.setAct_Start(act_Start);
		activityVO.setAct_End(act_End);
		activityVO.setAct_Usecou(act_Usecou);
		dao.insert(activityVO);
		return activityVO;
	}
	
	public ActivityVO updateActivity(String act_No,String coucat_No,String act_Cat,
			String act_Name,byte[] act_Carousel,String act_Cmimetype,byte[] act_Pic,
			String act_Pmimetype,String act_Content,Timestamp act_Start,Timestamp act_End,
			String act_Usecou) {
		ActivityVO activityVO=new ActivityVO();
		activityVO.setAct_No(act_No);
		activityVO.setCoucat_No(coucat_No);
		activityVO.setAct_Cat(act_Cat);
		activityVO.setAct_Name(act_Name);
		activityVO.setAct_Carousel(act_Carousel);
		activityVO.setAct_Cmimetype(act_Cmimetype);
		activityVO.setAct_Pic(act_Pic);
		activityVO.setAct_Pmimetype(act_Pmimetype);
		activityVO.setAct_Content(act_Content);
		activityVO.setAct_Start(act_Start);
		activityVO.setAct_End(act_End);
		activityVO.setAct_Usecou(act_Usecou);
		return activityVO;
	}
	
	public ActivityVO getByDateBetween(Timestamp act_Start1,Timestamp act_Start2,Timestamp act_End1,Timestamp act_End2) {
		return dao.findByDate_between(act_Start1, act_Start2, act_End1, act_End2);
	}
	
	public ActivityVO getByAct_Cata(String act_Cata) {
		return dao.findByAct_Cata(act_Cata);
	}
	public ActivityVO getOneActivity(String act_No) {
		return dao.findByPrimaryKey(act_No);
	}
	public List<ActivityVO> getAll(){
		return dao.getAll();
	}
}
