package com.branch.model;

import java.util.List;

public class BranchDAO implements BranchDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Test";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO BRANCH (BRANCH_NO, BRANCH_NAME, BRANCH_CITY, BRANCH_DIST, BRANCH_ADDR, BRANCH_POS, BRANCH_LAN, BRANCH_LAT, BRANCH_TIME, BRANCH_DEL, BRANCH_TEL, BRANCH_TDESK) "
			+ "VALUES(LPAD(BRANCH_NO_seq.NEXTVAL,4,'0'), ?,?,?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE = "UPDATE BRANCH SET "
			+ "BRAN_NAME=?,BRANCH_CITY=?, BRANCH_DIST=?, BRANCH_ADDR=?, BRANCH_POS=?, BRANCH_LAN=?, BRANCH_LAT=?, BRANCH_TIME=?, BRANCH_DEL=?, BRANCH_TEL=?, BRANCH_TDESK=? WHERE BRANCH_NO =?";

	private static final String DELETE = "DELETE from BRANCH WHERE BRANCH_NO =?";

	private static final String GET_ONE_STMT = "SELECT FROM BRANCH WHERE BRANCH_NO =?";
							
	private static final String GET_ALL_STMT = "SELECT * FROM BRANCH ORDER BY BRANCH_NO";

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
