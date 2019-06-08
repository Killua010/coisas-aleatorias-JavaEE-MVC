package controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import dao.OrderDao;
import domain.Category;
import domain.Client;
import domain.ItemOrder;
import domain.ItemShoppingCart;
import domain.Order;
import domain.ShoppingCart;

@WebServlet(urlPatterns="/orders")
public class OrderServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(null == req.getParameter("quantity")) {
			resp.sendRedirect("/CoisasAleatorias/index");
		}
		
		ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute("carrinho");
		int qtd = Integer.parseInt(req.getParameter("quantity"));
		
		for(int i = 0; i < qtd; i++) {
			int id = Integer.parseInt(req.getParameter("itemId"+i).trim());
			for(ItemShoppingCart item: shoppingCart.getItemShoppingCarts()) {
				if(id == item.getId()) {
					item.setQuantity(Integer.parseInt(req.getParameter("quantityItem"+i)));
				}
			}	
		}
		
		if(req.getSession().getAttribute("cliente") == null) {
			req.getSession().setAttribute("carrinho", shoppingCart);
			req.setAttribute("erro", "Antes de finalizar a compra, é necessario se logar ;)");
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.forward(req, resp);
		} else {
			Order order = new Order();
			Client client = (Client) req.getSession().getAttribute("cliente");
			order.setClient(client);
			Float total = (float) 0.0;
			for(ItemShoppingCart item: shoppingCart.getItemShoppingCarts()) {
				total += item.getQuantity() * item.getProduct().getValue();
				ItemOrder itemOrder = new ItemOrder();
				itemOrder.setId(item.getId());
				itemOrder.setProduct(item.getProduct());
				itemOrder.setQuantity(item.getQuantity());
				order.getItemOrders().add(itemOrder);
			}
			order.setTotal(total);
			OrderDao dao = new OrderDao();
			dao.create(order);
			req.getSession().setAttribute("carrinho", null);
			req.setAttribute("message", "Compra realizada com sucesso XP");
			RequestDispatcher rd = req.getRequestDispatcher("meus_dados.jsp");
			rd.forward(req, resp);
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		OrderDao dao = new OrderDao();
		if(req.getSession().getAttribute("cliente") == null) {
			
			rd = req.getRequestDispatcher("listar_pedidos.jsp");
			req.setAttribute("pedidos", dao.read(null));
		} else {
			rd = req.getRequestDispatcher("meus_pedidos.jsp");
			String[] data = {"cliente_id"};
			String[] value = {((Client)req.getSession().getAttribute("cliente")).getId().toString()};
			List<Order> orders = dao.find(data, value);
			req.setAttribute("pedidos", orders);
		}
			rd.forward(req, resp);
		
	}
	
}
