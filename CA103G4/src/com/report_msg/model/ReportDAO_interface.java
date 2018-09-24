package com.report_msg.model;
import java.util.List;
public interface ReportDAO_interface {
	
	public int insert(ReportVO report_MsgVO);
	void updateStatus(ReportVO report_MsgVO);
	List<ReportVO> findbyStatus(String rpt_Status);
	ReportVO getOne(String mem_No,String rply_No);
	List<ReportVO> getAll();
}

