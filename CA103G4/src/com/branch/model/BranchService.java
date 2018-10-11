package com.branch.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class BranchService {
	private BranchDAO_interface dao;

	public BranchService() {

		dao = new BranchDAO();
	}

	public BranchVO addBranch(String Branch_NAME, String Branch_City, String Branch_Dist, String Branch_Addr,
			String Branch_Pos, String Branch_Lan, String Branch_Lat, String Branch_Time, Double Branch_Del,
			String Branch_Tel, Integer Branch_Tdesk) {

		BranchVO branchVO = new BranchVO();

		// branchVO.setBranch_Name(Branch_No);
		branchVO.setBranch_Name(Branch_NAME);
		branchVO.setBranch_City(Branch_City);
		branchVO.setBranch_Dist(Branch_Dist);
		branchVO.setBranch_Addr(Branch_Addr);
		branchVO.setBranch_Pos(Branch_Pos);
		branchVO.setBranch_Lan(Branch_Lan);
		branchVO.setBranch_Lat(Branch_Lat);
		branchVO.setBranch_Time(Branch_Time);
		branchVO.setBranch_Del(Branch_Del);
		branchVO.setBranch_Tel(Branch_Tel);
		branchVO.setBranch_Tdesk(Branch_Tdesk);
		dao.insert(branchVO);
		return branchVO;
	}

	public BranchVO updateBranch(String Branch_No, String Branch_NAME, String Branch_City, String Branch_Dist,
			String Branch_Addr, String Branch_Pos, String Branch_Lan, String Branch_Lat, String Branch_Time,
			Double Branch_Del, String Branch_Tel, Integer Branch_Tdesk) {
		BranchVO branchVO = new BranchVO();
		branchVO.setBranch_No(Branch_No);
		branchVO.setBranch_Name(Branch_NAME);
		branchVO.setBranch_City(Branch_City);
		branchVO.setBranch_Dist(Branch_Dist);
		branchVO.setBranch_Addr(Branch_Addr);
		branchVO.setBranch_Pos(Branch_Pos);
		branchVO.setBranch_Lan(Branch_Lan);
		branchVO.setBranch_Lat(Branch_Lat);
		branchVO.setBranch_Time(Branch_Time);
		branchVO.setBranch_Del(Branch_Del);
		branchVO.setBranch_Tel(Branch_Tel);
		branchVO.setBranch_Tdesk(Branch_Tdesk);
		dao.update(branchVO);
		return branchVO;
	}

	public BranchVO findByBranch_No(String Branch_No) {
		return dao.findByPrimaryKey(Branch_No);
	}

	public List<BranchVO> getAll() {
		return dao.getAll();
	}

	public int delete(String stor_No) {
		return dao.delete(stor_No);
	}

	public List<BranchVO> findBy_City(String city) {
		return dao.findBy_City(city);
	}

	public Set<String> getCity() {
		return dao.getCity();
	}
	
	public BranchVO getBranch(String Branch_No) {
		return dao.findByPrimaryKey(Branch_No);
	}
}
