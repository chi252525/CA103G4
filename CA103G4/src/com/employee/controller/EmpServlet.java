package com.employee.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.empauthority.model.EmpauthorityVO;
import com.employee.model.EmpService;
import com.employee.model.EmpVO;
import com.feature.model.FeatureVO;


@MultipartConfig(fileSizeThreshold=1024*1024)
public class EmpServlet extends HttpServlet{
	
	public void doGet (HttpServletRequest req,HttpServletResponse res)throws  ServletException{
		
		
		
		
	}
		
	public void doPost (HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
				
		
		if("empinsert".equals(action)) {
			List<String> errorMsgs = new ArrayList();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				
				String branch_No = req.getParameter("branch_No");
					
				//帳號檢查
				String emp_Acnum = req.getParameter("emp_Acnum").trim();
				String emp_AcnumReq = "^[(A-Za-z0-9_){1,50}]$";
				if(emp_Acnum == null || emp_Acnum.length()==0) {
					errorMsgs.add("帳號尚未填寫");
				}else if (!(emp_Acnum.matches(emp_AcnumReq))) {
					errorMsgs.add("帳號僅可填寫數字和英文");
				}				
				//密碼檢查
				String emp_Psw = req.getParameter("emp_Psw").trim();
				String emp_PswReq = "^[(0-9A-Za-z_)] {1,50}$";
				if(emp_Psw == null || emp_Psw.length()==0) {
					errorMsgs.add("密碼尚未填寫");
				}else if (!(emp_Psw.matches(emp_PswReq))) {
					errorMsgs.add("密碼僅可填寫數字和英文");
				}
				//性別檢查
				String emp_Gender = req.getParameter("emp_Gender");
				if(emp_Gender == null) {
					errorMsgs.add("性別尚未選擇");
				}
				//姓名檢查
				String emp_Name = req.getParameter("emp_Name").trim();
				String emp_NameReq = "^[\u4e00-\u9fa5_a-zA-Z0-9]+$";
				if(emp_Name == null || emp_Name.length() == 0) {
					errorMsgs.add("員工姓名尚未填寫");
				}else if (!(emp_Name.matches(emp_NameReq))) {
					errorMsgs.add("員工姓名僅可填寫中文與英文");
				}
				//電話檢查				
				String emp_Tel = req.getParameter("emp_Tel");
				String emp_TelReq = "^[0-9]+$";
				if(emp_Tel == null || emp_Tel.length()==0) {
					errorMsgs.add("員工電話尚未填寫");
				}else if(!(emp_Tel.matches(emp_TelReq))) {
					errorMsgs.add("電話僅可填數字");
				}
				//職稱選擇
				String emp_Pos = req.getParameter("emp_Pos");
				
				//照片選擇
				
				Part part = req.getPart("emp_Photo");
				InputStream in = part.getInputStream();
				byte[] emp_Photo = new byte[in.available()];
				in.read(emp_Photo);
				in.close();
				System.out.println("emp_Photo="+emp_Photo);
				
				//將所勾選的功能放入員工包裝成集合
				List<EmpauthorityVO> empauthorlist = new ArrayList<>();
				String valuse[]=req.getParameterValues("fea_No");
				if(valuse != null) {
					for(int i=0 ; i < valuse.length ; i++) {
						System.out.println("valuse["+i+"]"+valuse[i]);
						EmpauthorityVO empauthorVO = new EmpauthorityVO(); 
						empauthorVO.setFea_No(valuse[i]);
						empauthorlist.add(empauthorVO);
					}
				}
				else{
					errorMsgs.add("尚未給員工使用權限");
				}
				
						
				/***************************2.開始新增資料****************************************/
				
				
				EmpVO empVO = new EmpVO();
				empVO.setBranch_No(branch_No);
				System.out.println("branch_No="+branch_No);
				empVO.setEmp_Acnum(emp_Acnum);
				System.out.println("emp_Acnum="+emp_Acnum);
				empVO.setEmp_Psw(emp_Psw);
				System.out.println("emp_Psw="+emp_Psw);
				empVO.setEmp_Name(emp_Name);
				System.out.println("emp_Name="+emp_Name);
				empVO.setEmp_Gender(emp_Gender);
				System.out.println("emp_Gender="+emp_Gender);
				empVO.setEmp_Tel(emp_Tel);
				System.out.println("emp_Tel="+emp_Tel);
				empVO.setEmp_Pos(emp_Pos);
				System.out.println("emp_Pos="+emp_Pos);
				
				EmpService empsvc = new EmpService();
				empsvc.addEmpWithAutoKeys(branch_No, emp_Acnum, emp_Psw, emp_Name, emp_Gender, emp_Pos, emp_Tel, emp_Photo, empauthorlist);
				
				
				if(!(errorMsgs.isEmpty())) {					
					req.setAttribute("empVO", empVO);  // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/employee/RegistEmp.jsp");
					failureView.forward(req, res);
					return; //程式中斷
					
				}
				
				
			}catch(Exception e) {
				errorMsgs.add("資料新增失敗");
				RequestDispatcher failuerView = req.getRequestDispatcher("/back_end/employee/RegistEmp.jsp");
				failuerView.forward(req, res);
				System.out.println(e.getMessage());
			}
														
		}
		
		
		//員工登區塊
		if("login".equals(action)) {
			
			List<String> errorMsgs = new ArrayList();
			req.setAttribute("errorMsgs", errorMsgs);	
			System.out.println("這裡");
			
			try {
				
				/***************************1.接收請求參數****************************************/
				//取得帳號密碼
				String emp_Acnum = req.getParameter("emp_Acnum").trim();
				String emp_Psw = req.getParameter("emp_Psw").trim();
				
				EmpService empSvc = new EmpService();
				EmpVO empVO = new EmpVO();
				empVO = empSvc.findOnebyEmpNo(emp_Acnum);
				
				
				if(emp_Acnum==null||emp_Acnum.length()==0) {
					errorMsgs.add("帳號尚未填寫");
				}else if (empVO != null) {
					if(empVO.getEmp_Psw().equals(emp_Psw)) {
						errorMsgs.add("密碼錯誤");	
					}
					
				}else {
					errorMsgs.add("帳號錯誤");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/employee/empLogin.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.帳號密碼皆正確****************************************/
				System.out.println("帳密都沒錯");
				
				HttpSession session = req.getSession();
				session.setAttribute("empVO", empVO);
				
				
				/***************************3.登入完成************/

				res.sendRedirect(req.getContextPath()+"/back_end/employee/RegistEmp.jsp");
				
				
				
			}catch(Exception e){
				errorMsgs.add("登入失敗"+e.getMessage());
				RequestDispatcher failuerView = req.getRequestDispatcher("/back_end/employee/empLogin.jsp");
				failuerView.forward(req, res);
			}
			
		}
		
	}
	

}
