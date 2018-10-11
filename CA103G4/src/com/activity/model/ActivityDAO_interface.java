package com.activity.model;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface ActivityDAO_interface {
	String insert(ActivityVO activityVO);
	void update(ActivityVO activityVO);
    public ActivityVO findByPrimaryKey(String act_No);
    List<ActivityVO> findByDate_between(Timestamp act_Start1,Timestamp act_Start2,Timestamp act_End1,Timestamp act_End2);
    List<ActivityVO> findByAct_Cata(String act_Cata);
	List<ActivityVO> getAll();
	//修改廣告狀態(上架/下架也會更新實際上下架時間)
	int updateAct(String act_No,Integer act_Status,ActivityVO activityVO);
	//廣告點擊率++
	int updateAct_Views(String act_No);
	//查詢已上架熱門廣告
	List<ActivityVO> findHotAct();
	//查詢已上架的最新廣告
	List<ActivityVO> findNewAct();
	public List<ActivityVO> getAll(Map<String, String[]> map);
}













