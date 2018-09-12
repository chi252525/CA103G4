package com.activity.model;
import java.sql.Timestamp;
import java.util.List;

public interface ActivityDAO_interface {
	void insert(ActivityVO activityVO);
	void update(ActivityVO activityVO);
	ActivityVO findByDate_between(Timestamp act_Start1,Timestamp act_Start2,Timestamp act_End1,Timestamp act_End2);
	ActivityVO findByAct_Cata(String act_Cata);
    public ActivityVO findByPrimaryKey(String act_No);
	List<ActivityVO> getAll();
}


