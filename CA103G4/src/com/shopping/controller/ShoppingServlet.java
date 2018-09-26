
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

		if (!action.equals("CHECKOUT")) {

			//刪除餐點
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
			}
			// �s�W���y���ʪ�����
			else if (action.equals("ADD")) {
				// ���o��ӷs�W�����y
				MenuVO aMenuVO = getMenuVO(req);

				if (buylist == null) {
					buylist = new Vector<MenuVO>();
					buylist.add(aMenuVO);
				} else {
					if (buylist.contains(aMenuVO)) {
						MenuVO innerMenuVO = buylist.get(buylist.indexOf(aMenuVO));
						innerMenuVO.setQuantity(innerMenuVO.getQuantity() + aMenuVO.getQuantity());
					} else {
						buylist.add(aMenuVO);
					}
				}
			}

			session.setAttribute("shoppingcart", buylist);
			String url = "/EShop.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		// ���b�A�p���ʪ������y�����`��
		else if (action.equals("CHECKOUT")) {
			double total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				MenuVO order = buylist.get(i);
				Double price = order.getPrice();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
			}

			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

	private MenuVO getMenuVO(HttpServletRequest req) {

		String quantity = req.getParameter("quantity");
		String menu_Id = req.getParameter("menu_Id");
		String total = req.getParameter("total");
		String publisher = req.getParameter("publisher");
		String price = req.getParameter("price");

		MenuVO MenuVO = new MenuVO();

		MenuVO.setMenu_Id(menu_Id);
		MenuVO.setMenu_Price(Integer.valueOf(price));
		MenuVO.setPublisher(publisher);
		MenuVO.setPrice(new Double(price));
		MenuVO.setQuantity((new Integer(quantity)).intValue());
		return MenuVO;
	}
}