package com.post.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.reply_msg.model.ReplyVO;

public class PostService {
	private PostDAO_interface dao;
	
	public PostService() {
		dao=new PostDAO();
	}
	
	public PostVO addPost(String mem_No, String custom_No, String post_Cont, Integer post_Eva,
			byte[] post_Photo, Timestamp post_Time) {
		PostVO postVO=new PostVO();
		postVO.setMem_No(mem_No);
		postVO.setCustom_No(custom_No);
		postVO.setPost_Cont(post_Cont);
		postVO.setPost_Eva(post_Eva);
		postVO.setPost_Photo(post_Photo);
		postVO.setPost_Time(post_Time);
		dao.insert(postVO);
		return postVO;
	}
	
	public PostVO updatePost(String post_No,String mem_No, String custom_No, String post_Cont, Integer post_Eva,
			byte[] post_Photo, Timestamp post_Time) {
		PostVO postVO=new PostVO();
		postVO.setPost_No(post_No);
		postVO.setMem_No(mem_No);
		postVO.setCustom_No(custom_No);
		postVO.setPost_Cont(post_Cont);
		postVO.setPost_Eva(post_Eva);
		postVO.setPost_Photo(post_Photo);
		postVO.setPost_Time(post_Time);
		dao.update(postVO);
		return postVO;
	}

	
	public void updatePostStatus(String post_No) {
		dao.updatePostStatus(post_No);
	}
	
	
	public void deletePost(String post_No) {
		dao.delete(post_No);
	}
	
	public List<PostVO> getMem_Post(String mem_No) {
		return dao.findbyMem_No(mem_No);
	}
	
	public List<PostVO> getCustom_Post(String custom_No) {
		return dao.findbyCustom_No(custom_No);
	}
	
	public PostVO getOne_Post(String post_No) {
		return dao.findByPrimaryKey(post_No);
	}
	
	public List<PostVO> getYear_and_Month_Post(String year, String month) {
		return dao.findbyYearandMonth(year,month);
	}
	public Set<ReplyVO> getOnePost_AllRplys(String post_No) {
		return dao.getOnePost_AllRplys(post_No);
	}
	
	public List<PostVO> getAll(){
		return dao.getAll();
	}
	public void updatePostViews(String post_No) {
		dao.updateViews(post_No);
	}
	
	public List<PostVO> getAllByHot() {
		return dao.getAllByHot();
	}

	public List<PostVO> getAllByNewFour() {
		return dao.getAllByNewFour();
	}
	public List<PostVO> getAllByKeywordOrderByViews(String keyword) {
		return dao.getAllByKeywordOrderByViews(keyword);
	}
	
	public List<PostVO> getCountByEva() {
		return dao.getCountByEva();
	}
	

	
}
