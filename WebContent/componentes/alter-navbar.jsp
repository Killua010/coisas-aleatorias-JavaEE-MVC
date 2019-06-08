<%@page import="domain.Client"%>
<%@page import="domain.Role"%>
<%@page import="domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Navbar -->
	<nav class="navbar navbar-expand-lg bg-primary navbar-orange">
		<div class="container">
			<div class="navbar-translate">
				<a class="navbar-brand" href="./index" rel="tooltip"
					data-placement="bottom"> <i class="fas fa-home"></i> Home
				</a>
				<button class="navbar-toggler navbar-toggler" type="button"
					data-toggle="collapse" data-target="#navigation"
					aria-controls="navigation-index" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-bar top-bar"></span> <span
						class="navbar-toggler-bar middle-bar"></span> <span
						class="navbar-toggler-bar bottom-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse justify-content-end"
				id="navigation">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="./catalogos">
							<i class="fas fa-dice-d20"></i>
							<p>Catalogo</p>
					</a></li>
					<li class="nav-item"><a href="./fale_conosco.jsp"
						class="nav-link"> <i class="fas fa-info"></i>
							<p>Fale Conosco</p>
					</a></li>
					<li class="nav-item"><a href="./carrinho.jsp"
						class="nav-link"> <i class="fas fa-shopping-cart"></i>
							<p>Meu carrinho</p>
					</a></li>
					<%
						User user = (User) request.getSession().getAttribute("usuario");
						Client client = (Client) request.getSession().getAttribute("cliente");

						if (user == null) {
							out.print("" + "<li class='nav-item dropdown'>"
									+ "<a href=\"javascript:void(0)\" class=\"nav-link\" id=\"btnLogin\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"
									+ "<i class=\"fas fa-sign-in-alt\"></i>" + "<p>&nbsp;Login</p>" + "</a>"
									+ "<div class=\"dropdown-menu\" aria-labelledby=\"btnLogin\">"
									+ "<a class=\"dropdown-item\" href=\"./login.jsp\">Entrar</a>"
									+ "<a class=\"dropdown-item\" href=\"./cadastro_cliente.jsp\">Cadastrar</a>" + "</div>"
									+ "</li>" + "");
						} else {
							if (user.getRole().equals(Role.ADMIN)) {
								out.print("" + "<li class='nav-item dropdown'>"
										+ "<a href=\"javascript:void(0)\" class=\"nav-link\" id=\"btnLogin\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"
										+ "<i class=\"fas fa-sign-in-alt\"></i>");
								out.print("<p>&nbsp;Olá administrador</p>");
								out.print("</a>" + "<div class=\"dropdown-menu\" aria-labelledby=\"btnLogin\">"
										+ "<a class=\"dropdown-item\" href=\"./login.jsp\">Area administrativa</a>"
										+ "<a class=\"dropdown-item\" href=\"./cadastro_cliente.jsp\">Sair</a>" + "</div>" + "</li>"
										+ "");
							} else {
								out.print("" + "<li class='nav-item dropdown'>"
										+ "<a href=\"javascript:void(0)\" class=\"nav-link\" id=\"btnLogin\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"
										+ "<i class=\"fas fa-sign-in-alt\"></i>");
								out.print("<p>&nbsp;Olá " + client.getFirstName() + "</p>");
								out.print("</a>" + "<div class=\"dropdown-menu\" aria-labelledby=\"btnLogin\">"
										+ "<a class=\"dropdown-item\" href=\"./meus_dados.jsp\">Meu Perfil</a>"
										+ "<a class=\"dropdown-item\" href=\"/CoisasAleatorias/usuarios?id="
										+ client.getUser().getId() + "\">Sair</a>" + "</div>" + "</li>" + "");
							}
						}
					%>

				</ul>
			</div>
		</div>
	</nav>
	<!-- ./Navbar -->