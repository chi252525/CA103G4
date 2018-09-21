package com.branch.model;

import java.util.List;


public interface BranchDAO_interface {
	public int insert(BranchVO branchVO);
    public int update(BranchVO branchVO);
    public int delete(String branch_No);
    public BranchVO findByPrimaryKey(String branch_No);
    public List<BranchVO> getAll();
    public List<BranchVO> findBy_City(String branch_City);
}
