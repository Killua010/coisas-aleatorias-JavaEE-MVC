package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import domain.Product;

@WebServlet(urlPatterns="/productDescription")
public class ProductDescriptionServlet extends HttpServlet {
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		ProductDao dao = new ProductDao();
		if(null != req.getParameter("id")) {
			Product product = new Product();
			product.setId(Long.parseLong(req.getParameter("id")));
			product = dao.read(product).get(0);
			req.setAttribute("produto", product);
			rd  = req.getRequestDispatcher("descricao-produto.jsp");
		} else {
			rd = req.getRequestDispatcher("catalogo.jsp");
		}
		
		rd.forward(req, resp);
	}
	
}
