package com.reply_msg.model;

import java.util.List;


public interface Reply_MsgDAO_interface {
	void insert(Reply_MsgVO reply_MsgVO);
	void delete(String rply_No);
	void update_status(Reply_MsgVO reply_MsgVO);
	List<Reply_MsgVO> getAll();
}
