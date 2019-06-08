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

@WebServlet(urlPatterns="/categorias")
public class CategoryServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Category category = new Category();
		CategoryDao dao = new CategoryDao();
		String operacao = req.getParameter("operacao"); 
		if(operacao != null && operacao.equals("DELETAR")){
			category.setId(Long.parseLong(req.getParameter("txtId")));
			dao.delete(category);
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
								category.setId(Long.parseLong(item.getString()));
							} else if (item.getFieldName().equals("txtNome")) {
								category.setName(item.getString());
							}
						} else {
							category.setPhoto("img" + File.separator + item.getName());
							
							String path = getServletContext().getRealPath("img") + File.separator + item.getName();
							
							item.write(new File(path));

						}
						
					}
				
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if(category.getId() != null) {
			dao.update(category);
		} else {
			dao.create(category);
		}
		req.setAttribute("message", "Categoria salvo com sucesso :)!!");
		
		RequestDispatcher rd = req.getRequestDispatcher("cadastro_categoria.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		CategoryDao dao = new CategoryDao();
		if(null != req.getParameter("id")) {
			Category category = new Category();
			category.setId(Long.parseLong(req.getParameter("id")));
			category = dao.read(category).get(0);
			req.setAttribute("categoria", category);
			rd  = req.getRequestDispatcher("cadastro_categoria.jsp");
		} else {
			rd = req.getRequestDispatcher("listar_categoria.jsp");
			req.setAttribute("categorias", dao.read(null));
		}
		
		rd.forward(req, resp);
	}
	
}
