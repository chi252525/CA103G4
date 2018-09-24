package com.branch.model;

import java.util.List;

public class BranchDAOtest {

	public static void main(String[] args) {
		BranchDAO dao = new BranchDAO();
		int row = 0;
		// insert
		BranchVO BranchVO1 = new BranchVO();
		BranchVO1.setBranch_No("0012");
		BranchVO1.setBranch_Name("竹風堂信義2店");
		BranchVO1.setBranch_City("台北");
		BranchVO1.setBranch_Dist("東區");
		BranchVO1.setBranch_Addr("信義店99號");
		BranchVO1.setBranch_Pos("120E 35N");
		BranchVO1.setBranch_Lan("120E");
		BranchVO1.setBranch_Lat("35N");
		BranchVO1.setBranch_Time("竹風堂信義店");
		BranchVO1.setBranch_Del(10.0);
		BranchVO1.setBranch_Tel("0909392246");
		BranchVO1.setBranch_Tdesk(99);
		row = dao.insert(BranchVO1);
		System.out.println("新增完畢:新增" + row + "筆資料");
		System.out.println("===============================");
		// update
		BranchVO1.setBranch_No("0014");
		BranchVO1.setBranch_Name("竹風堂信義3店");
		row = dao.update(BranchVO1);
		System.out.println("修改完畢: 修改" + row + "筆資料");
		System.out.println("===============================");
		// delete
		row = dao.delete("0015");
		System.out.println("刪除完畢: 刪了" + row + "筆資料");
		System.out.println("===============================");
		// select one
		BranchVO BranchVO3 = new BranchVO();
		BranchVO3 = dao.findByPrimaryKey("0009");
		System.out.println(BranchVO3.getBranch_No());
		System.out.println(BranchVO3.getBranch_Name());
		System.out.println(BranchVO3.getBranch_City());
		System.out.println(BranchVO3.getBranch_Dist());
		System.out.println(BranchVO3.getBranch_Pos());
		System.out.println(BranchVO3.getBranch_Lan());
		System.out.println(BranchVO3.getBranch_Lat());
		System.out.println(BranchVO3.getBranch_Time());
		System.out.println(BranchVO3.getBranch_Del());
		System.out.println(BranchVO3.getBranch_Tel());
		System.out.println(BranchVO3.getBranch_Tdesk());
		System.out.println(BranchVO3.getBranch_Addr());
		System.out.println("查詢完畢");
		System.out.println("===============================");
		//find by city
		System.out.println("位於台北的店有: ");
		List<BranchVO> list1 = dao.findBy_City("台北");
		for (BranchVO aBranch : list1) {
			System.out.println("Branch_No: " + aBranch.getBranch_No());
			System.out.println("Branch_Name: " + aBranch.getBranch_Name());
			System.out.println("Branch_City: " + aBranch.getBranch_City());
			System.out.println("Branch_Dist: " + aBranch.getBranch_Dist());
			System.out.println("Branch_Pos: " + aBranch.getBranch_Pos());
			System.out.println("Branch_Lan: " + aBranch.getBranch_Lan());
			System.out.println("Branch_Lat: " + aBranch.getBranch_Lat());
			System.out.println("Branch_Time:" + aBranch.getBranch_Time());
			System.out.println("Branch_Del: " + aBranch.getBranch_Del());
			System.out.println("Branch_Tel: " + aBranch.getBranch_Tel());
			System.out.println("Branch_Tdesk: " + aBranch.getBranch_Tdesk());
			System.out.println("Branch_Addr: " + aBranch.getBranch_Addr());
			System.out.println("===============================");
		}
		// select all
		List<BranchVO> list = dao.getAll();
		for (BranchVO aBranch : list) {
			System.out.println("Branch_No: " + aBranch.getBranch_No());
			System.out.println("Branch_Name: " + aBranch.getBranch_Name());
			System.out.println("Branch_City: " + aBranch.getBranch_City());
			System.out.println("Branch_Dist: " + aBranch.getBranch_Dist());
			System.out.println("Branch_Pos: " + aBranch.getBranch_Pos());
			System.out.println("Branch_Lan: " + aBranch.getBranch_Lan());
			System.out.println("Branch_Lat: " + aBranch.getBranch_Lat());
			System.out.println("Branch_Time:" + aBranch.getBranch_Time());
			System.out.println("Branch_Del: " + aBranch.getBranch_Del());
			System.out.println("Branch_Tel: " + aBranch.getBranch_Tel());
			System.out.println("Branch_Tdesk: " + aBranch.getBranch_Tdesk());
			System.out.println("Branch_Addr: " + aBranch.getBranch_Addr());
			System.out.println("===============================");
		}
		System.out.println("查完了");
	}

}
