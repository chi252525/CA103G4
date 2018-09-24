package com.report_msg.model;

import java.util.List;

import com.reply_msg.model.ReplyVO;

public class ReportService {
private ReportDAO_interface dao;
	
	public ReportService() {
		dao=new ReportDAO();
	}
	
	public ReportVO addReport(String mem_No, String rply_No ,String rpt_Rsm) {
		ReportVO reportVO = new ReportVO();
		reportVO.setMem_No(mem_No);
		reportVO.setRply_No(rply_No);
		reportVO.setRpt_Rsm(rpt_Rsm);
		dao.insert(reportVO);
		return reportVO;
	}

	public ReportVO updateStatus (String rpt_Status,String mem_No,String rply_No) {
		ReportVO reportVO=new ReportVO();
		reportVO.setRpt_Status(rpt_Status);
		reportVO.setMem_No(mem_No);
		reportVO.setRply_No(rply_No);
		dao.updateStatus(reportVO);
		return reportVO;
	}
	//***********************取得已處理或未處理的留言
	public List<ReportVO> getReplybyStatus(String rpt_Status){
		return dao.findbyStatus(rpt_Status);
	}
	
	public ReportVO getOne_Reply(String mem_No,String rply_No) {
		return dao.getOne(mem_No,rply_No);
	}
	public List<ReportVO> getAll(){
		return dao.getAll();
	}
}
