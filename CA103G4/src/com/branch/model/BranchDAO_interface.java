package com.branch.model;

import java.util.List;
import java.util.Set;


public interface BranchDAO_interface {
	public int insert(BranchVO branchVO);
    public int update(BranchVO branchVO);
    public int delete(String branch_No);
    public BranchVO findByPrimaryKey(String branch_No);
    public List<BranchVO> getAll();
    public List<BranchVO> findBy_City(String branch_City);
    public Set<String> getCity();//取得city 不重複集合
}
