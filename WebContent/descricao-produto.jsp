<!DOCTYPE html>
<%@page import="domain.Product"%>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- Fonts and icons     -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<!-- Now UI -->
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/now-ui/now-ui-kit.min.css">
<!-- Page Style -->
<link rel="stylesheet" href="./css/style.css">
<title>Carrinho</title>
</head>
<body class="index-page sidebar-collapse">
	<jsp:include page="componentes/alter-navbar.jsp" />
	<!-- Content -->
	<div class="main margin-top-navbar">
		<div class="section pt-2">
		<%
			Product product = (Product) request.getAttribute("produto");
		 %>
			<!-- Container -->
			<div class="container">
				<h3><% out.print(product.getName()); %></h3>
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-body">
						 	<div class="row">
								 <div class="col-md-6 text-center">
								 	<img src="<% out.print(product.getPhoto()); %>"  height="300"/> 
								 </div>
								 <div class="col-md-6">
								 	<p><% out.print(product.getDescription()); %></p>
								 	<h6>R$ <% out.print(product.getValue()); %></h6>
								 	<form action="/CoisasAleatorias/shoppingCart" method="POST">
								 	<input type="hidden" name="id" value="<% out.print(product.getId()); %>" />
								 		<button type="submit" class="btn btn-success">Adicionar ao carrinho</button>
							 		</form>
								 </div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- ./Container -->
		</div>


	</div>
	<!-- ./Content -->
	<!-- Footer -->
	<footer data-background-color="sucess" class="footer bg-primary">
		<div class="container">
			<nav>
				<ul>
					<li><a href="./index.html"> Coisas Aleatórias </a></li>
					<li><a href="./team-developer.html"> Nosso Time </a></li>
					<li><a href="./fale_conosco.html"> Fale Conosco </a></li>
				</ul>
			</nav>
			<div class="float-right">© 2019, feito por Daniel Dias</div>
		</div>
	</footer>
	<script src="./javascript/jquery.min.js"></script>
	<script src="./javascript/popper.min.js"></script>
	<script src="./javascript/bootstrap.min.js"></script>
	<script src="./javascript/now-ui/now-ui-kit.min.js"></script>
</body>
</html>
