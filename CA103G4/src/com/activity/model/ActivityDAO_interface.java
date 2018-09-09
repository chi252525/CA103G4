package com.activity.model;
import java.util.List;

public interface ActivityDAO_interface {
	void insert(ActivityVO activityVO);
	void update(ActivityVO activityVO);
	ActivityVO findByDate_between(String act_Start1,String act_Start2,String act_End1,String act_End2);
	ActivityVO findByAct_Cata(String act_Cata);
	List<ActivityVO> getAll();
}


