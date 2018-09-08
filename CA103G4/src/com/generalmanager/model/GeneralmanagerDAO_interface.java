package com.generalmanager.model;

import java.util.List;

public interface GeneralmanagerDAO_interface {
	
	public void insert(GeneralmanagerVO mgerVO);
	public void update(GeneralmanagerVO mgerVO);
	public void delete(String mger_No);
	public GeneralmanagerVO findByPrimaryKey(String mger_No);
	public List<GeneralmanagerVO> getAll();

}
