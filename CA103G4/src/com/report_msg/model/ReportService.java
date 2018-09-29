package com.report_msg.model;

import java.util.List;

import com.reply_msg.model.ReplyVO;

public class ReportService {
private ReportDAO_interface dao;
	
	public ReportService() {
		dao=new ReportDAO();
	}
	
	public ReportVO addReport(String post_No,String mem_No ,String rpt_Rsm) {
		ReportVO reportVO = new ReportVO();
		reportVO.setMem_No(mem_No);
		reportVO.setPost_No(post_No);
		reportVO.setRpt_Rsm(rpt_Rsm);
		dao.insert(reportVO);
		return reportVO;
	}

	public ReportVO updateStatus (String rpt_Status,String rpt_No) {
		ReportVO reportVO=new ReportVO();
		reportVO.setRpt_Status(rpt_Status);
		reportVO.setRpt_No(rpt_No);
		dao.updateStatus(reportVO);
		return reportVO;
	}
	//***********************取得已處理或未處理的留言
	public List<ReportVO> getReplybyStatus(String rpt_Status){
		return dao.findbyStatus(rpt_Status);
	}
	
	public ReportVO getOneReport(String rpt_No) {
		return dao.getOneReport(rpt_No);
	}
	public List<ReportVO> getAll(){
		return dao.getAll();
	}
}
