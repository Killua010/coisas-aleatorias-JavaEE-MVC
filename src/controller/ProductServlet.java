package controller;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import dao.CategoryDao;
import dao.ProductDao;
import domain.Category;
import domain.Product;

@WebServlet(urlPatterns="/produtos")
public class ProductServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		Product product = new Product();
		Category category = new Category();
		product.setCategory(category);
		ProductDao dao = new ProductDao();
		String operacao = req.getParameter("operacao"); 
		if(operacao != null && operacao.equals("DELETAR")){
			product.setId(Long.parseLong(req.getParameter("txtId")));
			dao.delete(product);
			doGet(req, resp);
			return;
		}
		if (ServletFileUpload.isMultipartContent(req)) {
			try {
				
				Map<String, List<FileItem>> multiparts = new ServletFileUpload(new DiskFileItemFactory())
						.parseParameterMap(req);

				for (List<FileItem> itens : multiparts.values()) {
					for (FileItem item : itens) {
						
						if (item.isFormField()) {
							if (item.getFieldName().equals("txtId")) {
								product.setId(Long.parseLong(item.getString()));
							} else if (item.getFieldName().equals("txtNome")) {
								product.setName(item.getString("UTF-8"));
							} else if (item.getFieldName().equals("txtValor")) {
								product.setValue(Float.parseFloat(item.getString()));
							} else if (item.getFieldName().equals("txtDescricao")) {
								product.setDescription(item.getString("UTF-8"));
							} else if (item.getFieldName().equals("categoria")) {
								product.getCategory().setId(Long.parseLong(item.getString()));
							}
						} else {
							product.setPhoto("img" + File.separator + item.getName());
							
							String path = getServletContext().getRealPath("img") + File.separator + item.getName();
							item.write(new File(path));

						}
						
					}
				
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if(product.getId() != null) {
			dao.update(product);
		} else {
			dao.create(product);
		}
		
		CategoryDao categoryDao = new CategoryDao();
		req.setAttribute("categorias", categoryDao.read(null));
		
		req.setAttribute("message", "Produto salvo com sucesso :)!!");
		
		RequestDispatcher rd = req.getRequestDispatcher("cadastro_produto.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		ProductDao dao = new ProductDao();
		String operacao = req.getParameter("operacao"); 
		CategoryDao categoryDao = new CategoryDao();
		if(null != req.getParameter("id")) {
			Product product = new Product();
			product.setId(Long.parseLong(req.getParameter("id")));
			product = dao.read(product).get(0);
			req.setAttribute("categorias", categoryDao.read(null));
			req.setAttribute("produto", product);
			rd  = req.getRequestDispatcher("cadastro_produto.jsp");
		} else if(null != operacao && operacao.equals("NOVO")) {
			req.setAttribute("categorias", categoryDao.read(null));
			rd  = req.getRequestDispatcher("cadastro_produto.jsp");
		} else {
			rd = req.getRequestDispatcher("listar_produto.jsp");
			req.setAttribute("produtos", dao.read(null));
		}
		
		rd.forward(req, resp);
	}
	
}
