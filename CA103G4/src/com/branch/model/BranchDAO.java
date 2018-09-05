package com.branch.model;

import java.util.List;

public class BranchDAO implements BranchDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Test";
	String passwd = "123456";
	@Override
	public void insert(BranchVO branchVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(BranchVO branchVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(String branchVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public BranchVO findByPrimaryKey(String branch_No) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<BranchVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
