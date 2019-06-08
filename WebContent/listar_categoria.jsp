<%@page import="domain.Category"%>
<%@page import="domain.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="now-ui-dashboard-master/assets/img/apple-icon.png">
<link rel="icon" type="image/png"
	href="now-ui-dashboard-master/assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Coisas Aleatorias</title>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
	name='viewport' />
<!--     Fonts and icons     -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<!-- CSS Files -->
<link href="now-ui-dashboard-master/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="now-ui-dashboard-master/assets/css/now-ui-dashboard.css?v=1.3.0"
	rel="stylesheet" />
<!-- CSS Just for demo purpose, don't include it in your project -->
<link href="now-ui-dashboard-master/assets/demo/demo.css"
	rel="stylesheet" />
</head>

<body class="user-profile">
	<div class="wrapper ">
		<div class="sidebar" data-color="orange">
			<!--
        Tip 1: You can change the color of the sidebar using: data-color="blue | green | orange | red | yellow"
    -->
			<div class="logo">
				<a href="./index" class="simple-text logo-mini"> CA </a> <a
					href="./index" class="simple-text logo-normal"> Coisas
					Aleatórias </a>
			</div>
			<jsp:include page="componentes/sidebar-admin.jsp" />
		</div>
		<div class="main-panel" id="main-panel">
			<!-- Navbar -->
			<nav
				class="navbar navbar-expand-lg navbar-transparent  bg-primary  navbar-absolute">
				<div class="container-fluid">
					<div class="navbar-wrapper">
						<div class="navbar-toggle">
							<button type="button" class="navbar-toggler">
								<span class="navbar-toggler-bar bar1"></span> <span
									class="navbar-toggler-bar bar2"></span> <span
									class="navbar-toggler-bar bar3"></span>
							</button>
						</div>
						<a class="navbar-brand" href="#">Cadastro Categoria</a>
					</div>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navigation" aria-controls="navigation-index"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-bar navbar-kebab"></span> <span
							class="navbar-toggler-bar navbar-kebab"></span> <span
							class="navbar-toggler-bar navbar-kebab"></span>
					</button>
					<div class="collapse navbar-collapse justify-content-end"
						id="navigation"></div>
				</div>
			</nav>
			<!-- End Navbar -->
			<div class="panel-header panel-header-sm"></div>
			<div class="content">
				<div class="row">
					<div class="col-md-10">
						<div class="card">
							<div class="card-header">
								<h5 class="title">Categorias</h5>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table">
										<thead class=" text-primary">
											<tr>
												<th>Id</th>
												<th>Nome</th>
												<th class="text-center">Editar</th>
												<th class="text-center">Excluir</th>
											</tr>
										</thead>
										<tbody>
										<%
											List<Category> categories =
												(List<Category>) request.getAttribute("categorias");
										
											for(Category category: categories) {
												out.print("<tr>");
												out.print("<td>" + category.getId() + "</td>");
												out.print("<td>"+ category.getName() + "</td>");
												out.print("<td class='text-center'><a href='categorias?id=" + category.getId() + "' class='btn btn-warning'>Editar</a></td>");
												out.print("<td class='text-center'><form action='/CoisasAleatorias/categorias' method='POST'><input type='hidden' name='txtId' value='" + category.getId() + "' /> <button class='btn btn-danger' type='submit' name='operacao' value='DELETAR'>Excluir</button></form></td>");
												out.print("</tr>");
											}
												
												
										%>
											
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="componentes/footer-admin.jsp" />
		</div>
	</div>
	<!--   Core JS Files   -->
	<script src="now-ui-dashboard-master/assets/js/core/jquery.min.js"></script>
	<script src="now-ui-dashboard-master/assets/js/core/popper.min.js"></script>
	<script src="now-ui-dashboard-master/assets/js/core/bootstrap.min.js"></script>
	<script
		src="now-ui-dashboard-master/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
	<!--  Google Maps Plugin    -->
	<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
	<!-- Chart JS -->
	<script src="now-ui-dashboard-master/assets/js/plugins/chartjs.min.js"></script>
	<!--  Notifications Plugin    -->
	<script
		src="now-ui-dashboard-master/assets/js/plugins/bootstrap-notify.js"></script>
	<!-- Control Center for Now Ui Dashboard: parallax effects, scripts for the example pages etc -->
	<script
		src="now-ui-dashboard-master/assets/js/now-ui-dashboard.min.js?v=1.3.0"
		type="text/javascript"></script>
	<!-- Now Ui Dashboard DEMO methods, don't include it in your project! -->
	<script src="now-ui-dashboard-master/assets/demo/demo.js"></script>
</body>

</html>
