package com.reply_msg.model;

import java.sql.Timestamp;
import java.util.List;



public class ReplyService {
	private ReplyDAO_interface dao;
	
	public ReplyService() {
		dao=new ReplyDAO();
	}
	
	public ReplyVO addReply(String rply_No,String mem_No,String post_No,String rply_Cont) {
		ReplyVO replyVO=new ReplyVO();
		replyVO.setRply_No(rply_No);
		replyVO.setPost_No(post_No);
		replyVO.setMem_No(mem_No);
		replyVO.setRply_Cont(rply_Cont);
		dao.insert(replyVO);
		return replyVO;
	}
	
	public ReplyVO update(String rply_No,String rply_Cont,Timestamp rply_Time,String rply_Status) {
		ReplyVO replyVO=new ReplyVO();
		replyVO.setRply_No(rply_No);
		replyVO.setRply_Cont(rply_Cont);
		replyVO.setRply_Time(rply_Time);
		replyVO.setRply_Status(rply_Status);
		return replyVO;
	}
	public List<ReplyVO> getAll(String rply_Status){
		return dao.getAll(rply_Status);
	}
	
	public List<ReplyVO> getByPostNo(String post_No){
		return dao.findByPostNo(post_No);
	}
	
	public void updateStatus(String rply_Status ,String rply_No) {
		dao.updateStatus(rply_Status,rply_No);
	}
	
	public void updateStatusForBackend(String rply_Status,String mem_No) {
		dao.updateStatusForBackEnd(rply_Status, mem_No);
	}
	public void delete(String rply_No) {
		dao.delete(rply_No);
	}
	
	
}
