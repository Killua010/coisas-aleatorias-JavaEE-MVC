package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import domain.ItemShoppingCart;
import domain.Product;
import domain.ShoppingCart;

@WebServlet(urlPatterns="/shoppingCart")
public class ShoppingCartServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;
		if(null != req.getParameter("idItem")) {
			Long id =  Long.parseLong(req.getParameter("idItem"));
			ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute("carrinho");
			
			for(ItemShoppingCart item: shoppingCart.getItemShoppingCarts()) {
				if(id == item.getId()) {
					shoppingCart.setTotal(shoppingCart.getTotal() - (item.getQuantity() * item.getProduct().getValue()));
					shoppingCart.getItemShoppingCarts().remove(item);
					break;
				}
			}
			
			req.getSession().setAttribute("carrinho", shoppingCart);
			
			rd  = req.getRequestDispatcher("carrinho.jsp");
		} else {
			rd = req.getRequestDispatcher("catalogo.jsp");
		}
		
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ProductDao dao = new ProductDao();
		if(null != req.getParameter("id")) {
			Product product = new Product();
			product.setId(Long.parseLong(req.getParameter("id")));
			product = dao.read(product).get(0);
			if(req.getSession().getAttribute("carrinho") == null) {
				req.getSession().setAttribute("carrinho", new ShoppingCart());
			}
			
			ItemShoppingCart cart = new ItemShoppingCart();
			cart.setProduct(product);
			cart.setQuantity(1);
			cart.setId(product.getId());
			ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute("carrinho");
			shoppingCart.getItemShoppingCarts().add(cart);
			
			Float total = (float) 0.0;
			
			for(ItemShoppingCart item: shoppingCart.getItemShoppingCarts()) {
				total += item.getProduct().getValue() * item.getQuantity();
			}
			
			shoppingCart.setTotal(total);
			
			req.getSession().setAttribute("carrinho", shoppingCart);
			
			rd  = req.getRequestDispatcher("carrinho.jsp");
			
		} else {
			rd = req.getRequestDispatcher("catalogo.jsp");
		}
		
		rd.forward(req, resp);
	}
	
}
