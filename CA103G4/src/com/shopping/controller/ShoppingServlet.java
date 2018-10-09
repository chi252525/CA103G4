package com.shopping.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import com.coucat.model.CoucatService;
import com.coupon.model.CouponService;
import com.custommeals.model.CustommealsVO;
import com.menu.model.MenuVO;

@WebServlet("/front_end/shoppingCart/ShoppingServlet.do")
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		@SuppressWarnings("unchecked")
		List<MenuVO> buylist = (Vector<MenuVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		System.out.println("action=" + action);

		if (!("CHECKOUT").equals(action) && (!("addCart").equals(action)) && (!("findMemCoupon").equals(action))
				&& (!("minusCart").equals(action))) { // 若action不等同上述四個 (action=checkout)

			// 刪除餐點
			if ("DELETE".equals(action)) {

				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
//				try {
				System.out.println("目前購物車內容:");
				if (buylist.size() != 0) {
					for (MenuVO x : buylist) {
						System.out.println(x.getMenu_Id() + "有" + x.getMenu_quantity() + "碗");
					}
				} else {
					System.out.println("沒東西 !");
				}
				res.sendRedirect("Cart.jsp");
				return;// 被免被下面的forward或redirect 導致exception
//				} catch (Exception e) {
//					
//				}
			}
			//
			else if ("ADD".equals(action)) {
				//
				MenuVO aMenuVO = getMenuVO(req);

				if (buylist == null) {
					buylist = new Vector<MenuVO>();
					buylist.add(aMenuVO);
				} else {
					if (buylist.contains(aMenuVO)) {
						MenuVO innerMenuVO = buylist.get(buylist.indexOf(aMenuVO));
						innerMenuVO.setMenu_quantity(innerMenuVO.getMenu_quantity() + aMenuVO.getMenu_quantity());
					} else {
						buylist.add(aMenuVO);
					}
				}

			}

			System.out.println("目前購物車內容:");
			if (buylist != null) {
				for (MenuVO x : buylist) {
					System.out.println(x.getMenu_Id() + "有" + x.getMenu_quantity() + "碗");
				}
			} else {
				System.out.println("沒東西 !");
			}

			session.setAttribute("shoppingcart", buylist);
			String url = req.getContextPath() + "/front_end/menu/listAllMenu4.jsp";// send back
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);
			res.sendRedirect(url);
			return;

		} else if ("CHECKOUT".equals(action)) {
			double total = 0;
			if (buylist == null) {
				String url = "Cart.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;
			} else {
				for (int i = 0; i < buylist.size(); i++) {
					MenuVO menuVO = buylist.get(i);
					Integer price = menuVO.getMenu_Price();
					Integer quantity = menuVO.getMenu_quantity();
					total += (price * quantity);
				}

				String amount = String.valueOf(total);
				req.setAttribute("amount", amount);
				String url = "Checkout.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;

			}
		}
		// 餐點數量加減按鈕(未完成)
		else if ("addCart".equals(action)) {
			//
			try {
//			String currentQuantity = req.getParameter("quantity");//獲取前端購物車商品數量
//			System.out.println("數量=" + currentQuantity);
				MenuVO aMenuVO = getMenuVO(req);// get點擊的餐點
//			if (buylist.contains(aMenuVO)) {
//				MenuVO innerMenuVO = buylist.get(buylist.indexOf(aMenuVO));

				MenuVO innerMenuVO = buylist.get(buylist.indexOf(aMenuVO));// 得到車裡與點擊餐點相同的餐
				innerMenuVO.setMenu_quantity(innerMenuVO.getMenu_quantity() + 1);// 將其數量+1

				res.setCharacterEncoding("UTF-8");
				res.setContentType("text/plain");
				JSONObject jso = new JSONObject();
				jso.put("memid", innerMenuVO.getMenu_Id());
//			
//				try {
				jso.put("memprice", innerMenuVO.getMenu_Price());
				jso.put("memid", innerMenuVO.getMenu_Id());
				jso.put("memquantity", innerMenuVO.getMenu_quantity());
				res.getWriter().print(jso);
			} catch (Exception e) {
				e.printStackTrace();
			}
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			// session.setAttribute("item", jsobj);
			// res.getWriter().print(jsobj.toString());//輸出資料到前端
//			} else {
//				buylist.add(aMenuVO);
//			}

			session.setAttribute("shoppingcart", buylist);
//			String url = "Cart.jsp";// send back
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);
//			res.sendRedirect(url);
//			return;
		} else if ("minusCart".equals(action)) {
			try {
				MenuVO aMenuVO = getMenuVO(req);// get點擊的餐點
				MenuVO innerMenuVO = buylist.get(buylist.indexOf(aMenuVO));// 得到車裡與點擊餐點相同的餐
				if (innerMenuVO.getMenu_quantity() > 0) { //大於0才可以減
					innerMenuVO.setMenu_quantity(innerMenuVO.getMenu_quantity() - 1);// 將其數量-1
				}
				res.setCharacterEncoding("UTF-8");
				res.setContentType("text/plain");
				JSONObject jso = new JSONObject();
				jso.put("memprice", innerMenuVO.getMenu_Price());
				jso.put("memid", innerMenuVO.getMenu_Id());
				jso.put("memquantity", innerMenuVO.getMenu_quantity());
				res.getWriter().print(jso);
				jso.put("memid", innerMenuVO.getMenu_Id());

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if ("findMemCoupon".equals(action)) {
			String amount = req.getParameter("amount");
			String couponDiscount = req.getParameter("coucatValue");
			amount = Double.toString(Double.parseDouble(amount) - Double.parseDouble(couponDiscount));
//			res.setContentType("application/json");
			System.out.println("折價後回前的amount=" + amount);
			res.getWriter().print(amount);// 輸出前端
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	private MenuVO getMenuVO(HttpServletRequest req) {

		String quantity = req.getParameter("quantity");
		String menuid = req.getParameter("menuid");
		String menuno = req.getParameter("menuno");
		String price = req.getParameter("price");

		System.out.println("品項: " + menuid);
		System.out.println("餐點編號: " + menuno);
		System.out.println("價格=" + price);
		System.out.println("數量=" + quantity);
		System.out.println();

		MenuVO MenuVO = new MenuVO();

		MenuVO.setMenu_Id(menuid);
		MenuVO.setMenu_Price(Integer.valueOf(price));
		MenuVO.setMenu_quantity((new Integer(quantity)).intValue());
		MenuVO.setMenu_No(menuno);
		return MenuVO;
	}
	

	private MenuVO getCustommealsVO(HttpServletRequest req) {

		String customno = req.getParameter("customno");
		String customname = req.getParameter("customname");
		String price = req.getParameter("price");
		String quantity = req.getParameter("quantity");

		System.out.println("自訂餐點編號: " + customno);
		System.out.println("自訂餐點名稱: " + customname);
		System.out.println("價格=" + price);
		System.out.println("數量=" + quantity);
		System.out.println();

		CustommealsVO CustommealsVO = new CustommealsVO();

		CustommealsVO.setcustom_No(customno);
		CustommealsVO.setcustom_Name(customname);
		CustommealsVO.setcustom_Price(Integer.valueOf(price));
		CustommealsVO.setcustom_Quantity((new Integer(quantity)).intValue());
		return CustommealsVO;
	}
}