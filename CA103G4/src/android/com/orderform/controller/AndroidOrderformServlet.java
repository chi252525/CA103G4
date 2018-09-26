package android.com.orderform.controller;

import android.com.orderform.model.*;
import android.com.orderinvoice.model.*;
import android.com.desk.model.*;
import android.com.main.ImageUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AndroidOrderformServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ServletContext context = getServletContext();
		OrderformDAO_interface dao = new OrderformDAO();
		Gson gson = new Gson();

		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		if ("getAll".equals(action)) {
			List<OrderformVO> orderformList = dao.getAll();
			writeText(res, gson.toJson(orderformList));
		}
		else if("add".equals(action)) {
			String orderJson = jsonObject.get("order").getAsString();
			OrderformVO order = gson.fromJson(orderJson, OrderformVO.class);
			List<OrderInvoiceVO> orderList = order.getOrderList();
			OrderformVO orderformVO = null;
			String order_no = dao.addWithOrderItem(order, orderList);
			if (!"-1".equals(order_no)) {
				orderformVO = dao.findByPrimaryKey(order_no);
				orderformVO.setOrderList(orderList);
			}
			writeText(res, gson.toJson(orderformVO));
		}
		else if("getDeskByOrderTypeAndStatus".equals(action)) {
			String orderType = jsonObject.get("orderType").getAsString();
			String orderStatus = jsonObject.get("orderStatus").getAsString();
			List<OrderformVO> orderList = dao.findByOrderTypeAndStatus(Integer.parseInt(orderType), Integer.parseInt(orderStatus));
			DeskDAO_interface ddao = new DeskDAO();
			List<DeskVO> deskList = ddao.getByDekNo(orderList);
			writeText(res, gson.toJson(deskList));
		}
		else if("getOrderNoByDekNoAndOrderStatus".equals(action)) {
			String dekNo = jsonObject.get("dekNo").getAsString();
			String orderStatus = jsonObject.get("orderStatus").getAsString();
			OrderformVO orderformVO = dao.findByDekNoAndOrderStatus(dekNo, Integer.parseInt(orderStatus));
			OrderinvoiceDAO_interface oidao = new OrderinvoiceDAO();
			List<OrderinvoiceVO> orderinvoiceList = oidao.findByOrder_no_withMenu(orderformVO.getOrder_no());
			writeText(res, gson.toJson(orderinvoiceList));
		}
//		else if ("getDelivNo".equals(action)) {
//			String deliv_no = jsonObject.get("deliv_no").getAsString();
//			List<DeliveryVO> deliveryList = dao.getByDelivNo(deliv_no);
//			writeText(res, gson.toJson(deliveryList));
//		} 
		
		else {
			writeText(res, "");
		}

	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		OrderformDAO_interface dao = new OrderformDAO();
		List<OrderformVO> orderformList = dao.getAll();
		writeText(res, new Gson().toJson(orderformList));
	}

}
