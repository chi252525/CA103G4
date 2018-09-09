package com.branch.model;

import java.util.List;


public interface BranchDAO_interface {
	public void insert(BranchVO branchVO);
    public void update(BranchVO branchVO);
    public void delete(String branch_No);
    public BranchVO findByPrimaryKey(String branch_No);
    public List<BranchVO> getAll();
}
