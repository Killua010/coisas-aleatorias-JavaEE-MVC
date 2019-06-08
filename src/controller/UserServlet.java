package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClientDao;
import dao.UserDao;
import domain.Client;
import domain.Role;
import domain.User;

@WebServlet(urlPatterns="/usuarios")
public class UserServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		User user = new User();
		user.setEmail(req.getParameter("txtEmail"));
		user.setPassword(req.getParameter("txtSenha"));
		
		UserDao userDao = new UserDao();
		
		List<User> users = userDao.read(user);
		
		if(users.size() == 0) {
			req.setAttribute("erro", "Login ou senha incorreto");
			
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.forward(req, resp);
			return;
		}
		
		user = users.get(0);
		
		if(user.getRole().equals(Role.CLIENTE)) {
			ClientDao clientDao = new ClientDao();
			Client client = clientDao.find(new String[] {"usuario_id"}, 
					new String[] {user.getId().toString()}).get(0);
			
			req.setAttribute("cliente", client);
			
			RequestDispatcher rd = req.getRequestDispatcher("meus_dados.jsp");
			req.getSession().setAttribute("usuario", user);
			req.getSession().setAttribute("cliente", client);
			rd.forward(req, resp);
		} else {
			resp.sendRedirect("/CoisasAleatorias/produtos");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("id") != null) {
			req.getSession().setAttribute("usuario", null);
			req.getSession().setAttribute("cliente", null);
			resp.sendRedirect("/CoisasAleatorias/index");
		}
	}
}
