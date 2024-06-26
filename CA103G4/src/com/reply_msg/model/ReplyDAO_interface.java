package com.reply_msg.model;

import java.util.List;


public interface ReplyDAO_interface {
	void insert(ReplyVO replyVO);
	void update(ReplyVO replyVO);
	public int delete(String rply_No);
	List<ReplyVO> findByPostNo(String post_No);
	public int updateStatus(String rply_Status ,String rply_No);
	List<ReplyVO> getAll(String rply_Status);
	public int updateStatusForBackEnd(String mem_No,String rply_Status);
	
}
