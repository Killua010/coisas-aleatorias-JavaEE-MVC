package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import dao.ProductDao;
import domain.Category;
import domain.Product;

@WebServlet(urlPatterns="/catalogos")
public class CatalogServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDao dao = new ProductDao();
		CategoryDao categoryDao = new CategoryDao();
		List<Category> categories = categoryDao.read(null);
		List<Product> products = dao.read(null);
		Map<String, List<Product>> mapProducts = new HashMap<>();
		
		for(Category category: categories) {
			mapProducts.put(category.getName(), new ArrayList<Product>());
		}
		
		for(Product product: products) {
			mapProducts.get(product.getCategory().getName()).add(product);
		}
		
		req.getSession().setAttribute("categories", categories);
		req.getSession().setAttribute("products", mapProducts);
		
		RequestDispatcher rd = req.getRequestDispatcher("catalogo.jsp");
		rd.forward(req, resp);
	}
}
