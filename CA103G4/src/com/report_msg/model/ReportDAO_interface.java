package com.report_msg.model;
import java.util.List;
public interface ReportDAO_interface {
	
	public int insert(ReportVO reportVO);
	void updateStatus(ReportVO reportVO);
	List<ReportVO> findbyStatus(String rpt_Status);
	ReportVO getOneReport(String rpt_No);
	List<ReportVO> getAll();
	
}

