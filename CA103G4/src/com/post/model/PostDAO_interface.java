package com.post.model;
import java.util.List;
import java.util.Set;

import com.reply_msg.model.ReplyVO;
public interface PostDAO_interface {
	void insert(PostVO postVO);
	void update(PostVO postVO);
	void delete(String post_No);
	public PostVO findByPrimaryKey(String post_No);
	public List<PostVO> findbyCustom_No(String custom_No);
	List<PostVO> findbyMem_No(String mem_No);
	List<PostVO> findbyYearandMonth(String year,String month);
	Set<ReplyVO> getOnePost_AllRplys(String post_No);
	List<PostVO> getAll();
	List<PostVO> getAllByHot();
	List<PostVO> getAllByNewFour();
	List<PostVO> getAllByKeywordOrderByViews(String keyword);
	public int updateViews(String post_No);
	public int updatePostStatus(String post_No);
	List<PostVO> getCountByEva();
}

