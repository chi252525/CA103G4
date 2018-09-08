package com.report_msg.model;
import java.util.List;
public interface Report_MsgDAO_interface {
	void updateStatus(Report_MsgVO report_MsgVO);
	Report_MsgVO findbyStatus(String rpt_Status);
	List<Report_MsgVO> getAll();
}

