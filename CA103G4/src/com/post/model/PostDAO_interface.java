package com.post.model;
import java.util.List;
public interface PostDAO_interface {
	void insert(PostVO postVO);
	void update(PostVO postVO);
	void delete(String post_No);
	PostVO findbyMem_No(String mem_No);
	PostVO findbyCustom_No(String custom_No);
	List<PostVO> getAll();
}

