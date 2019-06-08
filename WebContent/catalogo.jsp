<%@page import="java.util.Map.Entry"%>
<%@page import="domain.Category"%>
<%@page import="java.util.HashMap"%>
<%@page import="domain.Product"%>
<%@page import="java.util.List"%>
<%@page import="domain.Role"%>
<%@page import="domain.Client"%>
<%@page import="domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
<title>Catálogo</title>
</head>
<body class="index-page sidebar-collapse">
	<jsp:include page="componentes/alter-navbar.jsp" />
	<!-- Content -->
	<div class="main margin-top-navbar">
		<div class="section pt-2">
			<!-- Container -->
			<div class="container">
				<h1 class="title text-primary">Catálogo</h1>
				<div class="row">
					<div class="col-md-12">
						<div id="carouselExampleIndicators" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#carouselExampleIndicators" data-slide-to="0"></li>
								<li data-target="#carouselExampleIndicators" data-slide-to="1"
									class="active"></li>
							</ol>
							<div class="carousel-inner" role="listbox">
								<div class="carousel-item">
									<img class="category-carousel img-fluid d-block"
										src="./img/banner.jpg" alt="First slide">
									<div class="carousel-caption d-none d-md-block" id="slide-1">
										<h4 class="font-weight-bold">Coisas aleatóias a qualquer
											momento</h4>
									</div>
								</div>
								<div class="carousel-item active">
									<img class="category-carousel img-fluid d-block"
										src="https://images.pexels.com/photos/1455985/pexels-photo-1455985.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
										alt="Second slide">
									<div class="carousel-caption d-none d-md-block" id="slide-2">
										<h4 class="font-weight-bold">Venha comemorar o ano do
											porco</h4>
									</div>
								</div>
							</div>
							<a class="carousel-control-prev"
								href="#carouselExampleIndicators" role="button"
								data-slide="prev"> <i class="fas fa-chevron-left"></i>
							</a> <a class="carousel-control-next"
								href="#carouselExampleIndicators" role="button"
								data-slide="next"> <i class="fas fa-chevron-right"></i>
							</a>
						</div>
					</div>
				</div>
				<%
					List<Category> categories = (List<Category>) request.getSession().getAttribute("categories");
				%>
				<div class="row mt-5">
					<div class="col-md-3">
						<div class="card card-border">
							<div class="card-body py-0">
								<h4 class="card-title">Categorias</h4>
							</div>
							<ul class="list-group list-group-flush">
								<%
									for(Category category: categories){
										%>
										<li class="list-group-item"><a href="#<% out.print(category.getName()); %>"><% out.print(category.getName()); %></a></li>		
										<%
									}
								%>
							</ul>
						</div>
					</div>
					<div class="col-md-9 scrollInicial"
						style="overflow-y: scroll; height: 500px;">

						<%
							HashMap<String, List<Product>> products = (HashMap<String, List<Product>>) request.getSession()
									.getAttribute("products");							
							for(Category category: categories){
								if(null == products.get(category.getName()) || products.get(category.getName()).size() == 0)
									continue;
						%>
						<div id="<%out.print(category.getName());%>">
							<div class="row justify-content-md-center">
								<h3 class="title text-primary">
									<%out.print(category.getName());%>
								</h3>
							</div>
							<div class="row">
								<%
									List<Product> prodts = products.get(category.getName()); 
									for (Product product : prodts) {
								%>
								<div class="col-md-4 col-sm-6 text-center">
									<h4>
										<%
											out.print(product.getName());
										%>
									</h4>
									<div class="product-animation">
										<a href="productDescription?id=<% out.print(product.getId()); %>">
											<figure>
												<%
													out.print("<img class='product rounded img-thumbnail' src='" + product.getPhoto() + "'/>");
												%>
											</figure>
											<div
												class="product rounded img-thumbnail description-box rounded">
												<p class="description">
													<%
														out.print(product.getName());
													%>
												</p>
											</div>
										</a>
									</div>
									<p class="title text-primary pt-0">
										R$
										<%
										out.print(product.getValue());
									%>
									</p>
								</div>
								<%
									}
								%>
							</div>
							<%
								}
							%>





						</div>
					</div>
				</div>
			</div>
			<!-- ./Container -->
		</div>
	</div>
	<!-- Footer -->
		<footer data-background-color="sucess" class="footer bg-primary">
			<div class="container">
				<nav>
					<ul>
						<li><a href="./index.html"> Coisas AleatÃ³rias </a></li>
						<li><a href="./team-developer.html"> Nosso Time </a></li>
						<li><a href="./fale_conosco.html"> Fale Conosco </a></li>
					</ul>
				</nav>
				<div class="float-right">Â© 2019, feito por Daniel Dias</div>
			</div>
		</footer>
		<!-- ./Footer -->
	<!-- ./Content -->
	<script src="./javascript/jquery.min.js"></script>
	<script src="./javascript/popper.min.js"></script>
	<script src="./javascript/bootstrap.min.js"></script>
	<script src="./javascript/now-ui/now-ui-kit.min.js"></script>
</body>
</html>
