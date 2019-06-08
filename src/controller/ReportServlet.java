package controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.ProductDao;
import domain.ItemOrder;
import domain.Order;
import domain.Product;
import domain.Report;

@WebServlet(urlPatterns="/report")
public class ReportServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		OrderDao dao = new OrderDao();
		ProductDao productDao = new ProductDao();
		
		Map<Product, Integer> mapProduct = new HashMap();
		
		for(Product product: productDao.read(null)) {
			mapProduct.put(product, 0);
		}
		
		List<Order> orders = dao.read(null);
		Float grossProfit = (float) 0.0;
		for(Order order: orders) {
			grossProfit += order.getTotal();
			for(ItemOrder itemOrder: order.getItemOrders()) {
				int val = mapProduct.get(itemOrder.getProduct()) + (itemOrder.getQuantity());
				mapProduct.put(itemOrder.getProduct(), val);
			}
		}
		
		List<Report> reports = new ArrayList<Report>();

		mapProduct.forEach((key, value) -> {
			reports.add(new Report(key.getName(), value));
		});

		boolean flag = false;
		Report aux;
		while (flag != true) {
			flag = true;
			for (int i = 0; i < reports.size() - 1; i++) {
				if (((int) reports.get(i).getValue()) < ((int) reports.get(i + 1).getValue())) {
					aux = reports.get(i);
					reports.set(i, reports.get(i + 1));
					reports.set(i + 1, aux);
					flag = false;
				}
			}
		}

		req.setAttribute("relatorio", reports.subList(0, 5));
		req.setAttribute("lucroBruto", grossProfit);
		
		rd = req.getRequestDispatcher("reports.jsp");
		
		rd.forward(req, resp);
	}
	
}
