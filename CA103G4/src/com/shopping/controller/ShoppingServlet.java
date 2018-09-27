package com.shopping.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

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
		System.out.println("action="+action);
		
		System.out.println("目前購物車內容:");
		if (buylist != null) {
			for (MenuVO x : buylist) {
				System.out.println(x.getMenu_Id()+"有"+x.getMenu_quantity()+"碗");
			}
		}else {
			System.out.println("沒東西 !");
		}

		if (!action.equals("CHECKOUT")) {

			// 刪除餐點
			if (action.equals("DELETE")) {

				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
				try {
					req.getRequestDispatcher("Cart.jsp").forward(req, res);
				} catch (Exception e) {

				}
			}
			//
			else if (action.equals("ADD")) {
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

			session.setAttribute("shoppingcart", buylist);
			String url = "noodleShop.jsp";// send back
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		//
		else if (action.equals("CHECKOUT")) {
			double total = 0;
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
		}
	}

	private MenuVO getMenuVO(HttpServletRequest req) {

		String quantity = req.getParameter("quantity");
		String menuid = req.getParameter("menuid");
		String menuno = req.getParameter("menuno");
		String price = req.getParameter("price");

		System.out.println("品項: " + menuid);
		System.out.println("餐點編號: " + menuno);
		System.out.println("價格=" + price);
		System.out.println("數量="+quantity);
		System.out.println();

		MenuVO MenuVO = new MenuVO();

		MenuVO.setMenu_Id(menuid);
		MenuVO.setMenu_Price(Integer.valueOf(price));
		MenuVO.setMenu_quantity((new Integer(quantity)).intValue());
		MenuVO.setMenu_No(menuno);
		return MenuVO;
	}
}