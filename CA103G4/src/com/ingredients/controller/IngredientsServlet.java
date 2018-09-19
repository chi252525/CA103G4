package com.ingredients.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ingredients.model.*;



@MultipartConfig                   //上傳三要素 第二步 加上@MultipartConfig 的 @annotation註解
public class IngredientsServlet extends HttpServlet{      //Ingredients的控制器Servlet繼承自HttpServlet類別，並覆寫doGet()和doPost()方法

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {    ////上傳三要素 第三步  doPost方法
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
			if("getOne_For_Display".equals(action)) {  // 來自select_page.jsp的請求
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				List<String> errorMsgs = new LinkedList<>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String ingdt_Id = req.getParameter("ingdt_Id").trim();           //利用主鍵ingdt_Id 傳入請求參數  指定給ingdt_Id變數
					//無輸入
					if (ingdt_Id == null || ingdt_Id.length() == 0) {
						errorMsgs.add("請輸入自訂餐點編號");
					}
					if (!errorMsgs.isEmpty()) {               //請求的遞送者             forward 到   select_page頁面
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/ingredients/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					//格式錯誤
					if(!(ingdt_Id.matches("I\\d{4}")))
						errorMsgs.add("自訂餐點編號格式不正確");
					if(!errorMsgs.isEmpty()) {                            
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/ingredients/select_page.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始查詢資料*****************************************/
					IngredientsService ingredientsSvc = new IngredientsService();              //ingredientsService呼叫getOneIngredients()方法
					IngredientsVO ingredientsVO = ingredientsSvc.getOneIngredients(ingdt_Id);  //用主鍵ingdt_Id當作參數傳入 ，指定給ingredientsVO物件
					if(ingredientsVO == null)
						errorMsgs.add("查無資料");
					if(!errorMsgs.isEmpty()) {                                   
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/ingredients/select_page.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("ingredientsVO", ingredientsVO);   //將ingredientsVO物件,存入req             頁面導向listOneIngredients
					RequestDispatcher successView = req.getRequestDispatcher("/front_end/ingredients/listOneIngredients.jsp");
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理*************************************/
				}
				 catch(Exception e) {
					errorMsgs.add("無法取得資料" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/ingredients/select_page.jsp");
					failureView.forward(req, res);
				}
				
			}
			
			if("getOne_For_Update".equals(action)){  // 來自listIngredients.jsp的請求
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				List<String> errorMsgs = new LinkedList<>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					String ingdt_Id = req.getParameter("ingdt_Id").trim();                    //利用主鍵ingdt_Id 傳入請求參數  指定給ingdt_Id變數
					
					/***************************2.開始查詢資料****************************************/
					IngredientsService ingredientsSvc = new IngredientsService();             //ingredientsService呼叫getOneIngredients()方法
					IngredientsVO ingredientsVO = ingredientsSvc.getOneIngredients(ingdt_Id); //用主鍵ingdt_Id當作參數傳入 ，指定給ingredientsVO物件
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("ingredientsVO", ingredientsVO);      //將ingredientsVO物件,存入req       頁面導向update_ingredients_input
					RequestDispatcher successView = req.getRequestDispatcher("/front_end/ingredients/update_ingredients_input.jsp");
					successView.forward(req, res);

					/***************************其他可能的錯誤處理**********************************/
				} 
				  catch(Exception e) {
					errorMsgs.add("無法取得要修改的資料" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/ingredients/listAllIngredients.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			if("update".equals(action)) {  // 來自update_ingredients_input.jsp的請求
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				List<String> errorMsgs = new LinkedList<>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				
				try {
					/***************************1.接收請求參數****************************************/
					//(食材編號)
					String ingdt_Id = req.getParameter("ingdt_Id").trim();
					
					//無輸入OR長度超出範圍(食材類別編號)
					String ingdtc_Id = req.getParameter("ingdtc_Id").trim();
					if(ingdtc_Id == null || ingdtc_Id.length() == 0) {
						errorMsgs.add("會員編號:請勿空白");
					}  else if(ingdtc_Id.length() >= 15) {
						errorMsgs.add("會員編號格式不符");
					}
					
					//無輸入OR長度超出範圍(食材名稱)
					String ingdt_Name = req.getParameter("ingdt_Name").trim();
					if(ingdt_Name == null || ingdt_Name.length() == 0) {
						errorMsgs.add("自訂餐點名稱:請勿空白");
					} else if(ingdt_Name.length() >= 20) {
						errorMsgs.add("自訂餐點名稱長度過長");
					}
					
					
					//無輸入OR格式不正確(食材狀態)
					String str = req.getParameter("ingdt_Status");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入食材狀態");
					}
					Integer ingdt_Status = null;
					try {
						ingdt_Status = new Integer(str);
					} catch (Exception e) {
						errorMsgs.add("食材狀態格式不正確");
					}
					
					//食材兌換點數(竹幣)
					String ingdt_Point = req.getParameter("ingdt_Point");
					if(ingdt_Name.length() >= 10) {
						errorMsgs.add("食材單位名稱長度過長");
					}
					
					//食材單位
					String ingdt_Unit = req.getParameter("ingdt_Unit");
					if(ingdt_Unit == null || ingdt_Unit.length() == 0) {
						errorMsgs.add("食材單位:請勿空白");
					} else if(ingdt_Name.length() >= 10) {
						errorMsgs.add("食材單位名稱長度過長");
					}
					
					//無輸入OR格式不正確(食材價格)
					str = req.getParameter("ingdt_Price");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入食材價格");
					}
					Integer ingdt_Price = null;
					try {
						ingdt_Price = new Integer(str);
					} catch (Exception e) {
						errorMsgs.add("食材價格格式不正確");
					}
					
					
					
	//******************* img要存入Database需先取得part物件，轉成inputstream後再放入byte[] *******************
					//食材照片
					Part part = req.getPart("ingdt_Photo");                 //用req.getPart(String name)方法 上傳檔案	，傳回一個Part物件
					InputStream in = part.getInputStream();                 //用part物件呼叫 getInputStream() 方法，把檔案送進資料庫
					byte[] ingdt_Photo = new byte[in.available()];
					in.read(ingdt_Photo);
					in.close();
					
	//***********************************************************************************************
					
					
					IngredientsVO ingredientsVO = new IngredientsVO();  //用ingredientsVO物件呼叫set方法
					ingredientsVO.setingdt_Id(ingdt_Id);
					ingredientsVO.setingdtc_Id(ingdtc_Id);
					ingredientsVO.setingdt_Name(ingdt_Name);
					ingredientsVO.setingdt_Status(ingdt_Status);
					ingredientsVO.setingdt_Point(ingdt_Point);
					ingredientsVO.setingdt_Unit(ingdt_Unit);
					ingredientsVO.setingdt_Price(ingdt_Price);
					ingredientsVO.setingdt_Photo(ingdt_Photo);
					
					
					// Send the use back to the form, if there were errors
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("ingredientsVO", ingredientsVO);  // 含有輸入格式錯誤的ingredientsVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/ingredients/update_ingredients_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料****************************************/
					IngredientsService ingredientsSvc = new IngredientsService();  //ingredientsService呼叫updateIngredients()方法
					ingredientsVO = ingredientsSvc.updateIngredients(ingdt_Id, ingdtc_Id, ingdt_Name, ingdt_Status, ingdt_Point, ingdt_Unit, ingdt_Price, ingdt_Photo);
									
					/***************************3.修改完成,準備轉交(Send the Success view)************/
					req.setAttribute("ingredientsVO", ingredientsVO);    // 資料庫修改成功後,正確的ingredientsVO物件,存入req
					RequestDispatcher successView = req.getRequestDispatcher("/front_end/ingredients/listOneIngredients.jsp");
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} 
				 catch(Exception e) {
					errorMsgs.add("資料修改失敗"+e.getMessage());
					RequestDispatcher failuerView = req.getRequestDispatcher("/front_end/ingredients/update_ingredients_input.jsp");
					failuerView.forward(req, res);
				}
			}
			
			
			
			if("insert".equals(action)){  // 來自addIngredients.jsp的請求  
				List<String> errorMsgs = new LinkedList<>();
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					/***************************1.接收請求參數****************************************/
					
				//無輸入OR長度超出範圍(食材類別編號)
				String ingdtc_Id = req.getParameter("ingdtc_Id").trim();
				if(ingdtc_Id == null || ingdtc_Id.length() == 0) {
					errorMsgs.add("會員編號:請勿空白");
				}  else if(ingdtc_Id.length() >= 15) {
					errorMsgs.add("會員編號格式不符");
				}
				
				//無輸入OR長度超出範圍(食材名稱)
				String ingdt_Name = req.getParameter("ingdt_Name").trim();
				if(ingdt_Name == null || ingdt_Name.length() == 0) {
					errorMsgs.add("自訂餐點名稱:請勿空白");
				} else if(ingdt_Name.length() >= 20) {
					errorMsgs.add("自訂餐點名稱長度過長");
				}
				
				
				//無輸入OR格式不正確(食材狀態)
				String str = req.getParameter("ingdt_Status");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入食材狀態");
				}
				Integer ingdt_Status = null;
				try {
					ingdt_Status = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("食材狀態格式不正確");
				}
				
				//食材兌換點數(竹幣)
				String ingdt_Point = req.getParameter("ingdt_Point");
				if(ingdt_Name.length() >= 10) {
					errorMsgs.add("食材單位名稱長度過長");
				}
				
				//食材單位
				String ingdt_Unit = req.getParameter("ingdt_Unit");
				if(ingdt_Unit == null || ingdt_Unit.length() == 0) {
					errorMsgs.add("食材單位:請勿空白");
				} else if(ingdt_Name.length() >= 10) {
					errorMsgs.add("食材單位名稱長度過長");
				}
				
				//無輸入OR格式不正確(食材價格)
				str = req.getParameter("ingdt_Price");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入食材價格");
				}
				
				Integer ingdt_Price = null;
				try {
					ingdt_Price = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("食材價格格式不正確");
				}
				
				
				
//******************* img要存入Database需先取得part物件，轉成inputstream後再放入byte[] *******************
				//食材照片
				Part part = req.getPart("ingdt_Photo");
				InputStream in = part.getInputStream();
				System.out.println("in.available()="+in.available());
				byte[] ingdt_Photo = new byte[in.available()];
				in.read(ingdt_Photo);
				in.close();
					
	//***********************************************************************************************
					

					
					IngredientsVO ingredientsVO = new IngredientsVO();		
					ingredientsVO.setingdtc_Id(ingdtc_Id);
					ingredientsVO.setingdt_Name(ingdt_Name);
					ingredientsVO.setingdt_Status(ingdt_Status);
					ingredientsVO.setingdt_Point(ingdt_Point);
					ingredientsVO.setingdt_Unit(ingdt_Unit);
					ingredientsVO.setingdt_Price(ingdt_Price);
					ingredientsVO.setingdt_Photo(ingdt_Photo);
					
					
					
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("ingredientsVO", ingredientsVO);  // 含有輸入格式錯誤的ingredientsVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/ingredients/addIngredients.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始新增資料****************************************/
					IngredientsService ingredientsSvc = new IngredientsService();
					ingredientsVO = ingredientsSvc.addIngredients(ingdtc_Id, ingdt_Name, ingdt_Status, ingdt_Point, ingdt_Unit, ingdt_Price, ingdt_Photo);
									
					/***************************3.新增完成,準備轉交(Send the Success view)************/
					req.setAttribute("ingredientsVO", ingredientsVO);  // 資料庫新增成功後,正確的ingredientsVO物件,存入req
					RequestDispatcher successView = req.getRequestDispatcher("/front_end/ingredients/listAllIngredients.jsp");
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch(Exception e) {
					errorMsgs.add("資料新增失敗"+e.getMessage());
					RequestDispatcher failuerView = req.getRequestDispatcher("/front_end/ingredients/addIngredients.jsp");
					failuerView.forward(req, res);
				}
			}
			
			
			
			if("delete".equals(action)) {  // 來自listAllIngredients.jsp
				List<String> errorMsgs = new LinkedList<>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					String ingdt_Id = req.getParameter("ingdt_Id");
					String whichPage = req.getParameter("whichPage");
					
					/***************************2.開始刪除資料****************************************/
					IngredientsService ingredientsSvc = new IngredientsService();
					ingredientsSvc.deleteIngredients(ingdt_Id);
									
					/***************************3.刪除完成,準備轉交(Send the Success view)************/
					req.setAttribute("ingdt_Id", ingdt_Id);
					RequestDispatcher successView = req.getRequestDispatcher("/front_end/ingredients/listAllIngredients.jsp?"+whichPage);
					successView.forward(req, res);

					/***************************其他可能的錯誤處理**********************************/
					
				} catch(Exception e) {
					errorMsgs.add("刪除資料失敗" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/ingredients/listAllIngredients.jsp");
					failureView.forward(req, res);
				}
			}
			
		}

	}
