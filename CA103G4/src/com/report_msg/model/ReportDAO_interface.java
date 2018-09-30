package com.report_msg.model;
import java.util.List;
public interface ReportDAO_interface {
	
	public int insert(ReportVO reportVO);
	public int updateReportStatus(String rpt_No);
	List<ReportVO> findbyStatus(String rpt_Status);
	ReportVO getOneReport(String rpt_No);
	List<ReportVO> getAll();
	
}

