<%@page import="domain.Report"%>
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
	<% List<Report> reports = (List<Report>) request.getAttribute("relatorio"); %>
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
						<a class="navbar-brand" href="#">Relatórios</a>
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
								<h5 class="title">Produtos mais vendidos</h5>
							</div>
							<div class="card-body">
								<canvas id="chart-area"></canvas>
							</div>
							<div class="card-header">
								<h5 class="title">Lucro Bruto Total: R$ <% out.print(request.getAttribute("lucroBruto")); %></h5>
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
	<script>
		var randomScalingFactor = function() {
			return Math.round(Math.random() * 100);
		};

		var config = {
			type: 'pie',
			data: {
				datasets: [{
					data: [
						<%
							for(Report report: reports){
								out.print("'" +report.getValue() + "',");
							}
						%>
					],
					backgroundColor: [
						'#9FC6B7',
						'#D9CF3A',
						'#FCE6AA',
						'#C05241',
						'#DEB888'
					],
					label: 'Dataset 1'
				}],
				labels: [
					<%
					for(Report report: reports){
						out.print("'" + report.getName() + "',");
					}
				%>
				]
			},
			options: {
				responsive: true
			}
		};

		window.onload = function() {
			var ctx = document.getElementById('chart-area').getContext('2d');
			window.myPie = new Chart(ctx, config);
		};

		document.getElementById('randomizeData').addEventListener('click', function() {
			config.data.datasets.forEach(function(dataset) {
				dataset.data = dataset.data.map(function() {
					return randomScalingFactor();
				});
			});

			window.myPie.update();
		});

		var colorNames = Object.keys(window.chartColors);
		document.getElementById('addDataset').addEventListener('click', function() {
			var newDataset = {
				backgroundColor: [],
				data: [],
				label: 'New dataset ' + config.data.datasets.length,
			};

			for (var index = 0; index < config.data.labels.length; ++index) {
				newDataset.data.push(randomScalingFactor());

				var colorName = colorNames[index % colorNames.length];
				var newColor = window.chartColors[colorName];
				newDataset.backgroundColor.push(newColor);
			}

			config.data.datasets.push(newDataset);
			window.myPie.update();
		});

		document.getElementById('removeDataset').addEventListener('click', function() {
			config.data.datasets.splice(0, 1);
			window.myPie.update();
		});
	</script>
</body>

</html>
