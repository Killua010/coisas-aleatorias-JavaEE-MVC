package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContactUsDao;
import domain.ContactUs;

@WebServlet(urlPatterns="/fale-conosco")
public class ContactUsServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ContactUs contactUs = new ContactUs();
		contactUs.setName(req.getParameter("txtNome"));
		contactUs.setEmail(req.getParameter("txtEmail"));
		contactUs.setSubject(req.getParameter("txtAssunto"));
		ContactUsDao contactUsDao = new ContactUsDao();
		contactUsDao.create(contactUs);
	}
}
