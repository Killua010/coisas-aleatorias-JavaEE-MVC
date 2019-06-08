package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClientDao;
import domain.Client;
import domain.User;

@WebServlet(urlPatterns="/clientes")
public class ClientServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		Client client = new Client();
		User user = new User();
		user.setEmail(req.getParameter("txtEmail"));
		user.setPassword(req.getParameter("txtSenha"));
		client.setFirstName(req.getParameter("txtNome"));
		client.setLastName(req.getParameter("txtSobrenome"));
		client.setUser(user);
		ClientDao dao = new ClientDao();
		
		if(req.getParameter("operacao").equals("EDITAR")) {
			client.setId(Long.parseLong(req.getParameter("clientId")));
			client.getUser().setId(Long.parseLong(req.getParameter("userId")));
			req.setAttribute("message", "Dados atualizados :)");
			dao.update(client);
		} else {
			dao.create(client);
		}
		
		req.getSession().setAttribute("cliente", client);
		req.getSession().setAttribute("usuario", client.getUser());
		
		RequestDispatcher rd = req.getRequestDispatcher("meus_dados.jsp");
		rd.forward(req, resp);
		
	}
}
