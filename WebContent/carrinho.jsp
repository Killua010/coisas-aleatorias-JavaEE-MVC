<%@page import="domain.ItemShoppingCart"%>
<%@page import="domain.ShoppingCart"%>
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
<title>Carrinho</title>
</head>
<body class="index-page sidebar-collapse">
	<jsp:include page="componentes/alter-navbar.jsp" />
	<!-- Content -->
	<div class="main margin-top-navbar">
		<div class="section pt-2">
			<!-- Container -->
			<div class="container">
				<h3>Carrinho</h3>
				<form action="/CoisasAleatorias/orders" method="POST">
					<div class="row">
						<div class="col-md-8">
							<div class="card">
								  <div class="card-body">
								  <% ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("carrinho"); %>
									<table class="table">
										<thead>
											<tr>
												<th>Nome</th>
												<th>Valor</th>
												<th>Quantidade</th>
												<th class="text-center">Remover</th>
											</tr>
										</thead>
										<tbody>
										<%
										if(cart != null){
											int count = 0;
											out.print("<input type='hidden' name='quantity' value='" + cart.getItemShoppingCarts().size() + "' />");
											for(ItemShoppingCart item: cart.getItemShoppingCarts()){
										
										%>
											<tr class="products">
												<td><% out.print(item.getProduct().getName()); %></td>
												<td>R$ <span class="value"><% out.print(item.getProduct().getValue()); %></span></td>
												<input type="hidden" name="itemId<% out.print(count); %>" value="<% out.print(item.getId()); %> " /> 
												<td><input type="number" step="1" min="1" name="quantityItem<% out.print(count); %>" value="<% out.print(item.getQuantity()); %>" class="quantity" /></td>	
												<td class="text-center"><a href="./shoppingCart?idItem=<% out.print(item.getId()); %>" class="btn btn-danger"><i class="fas fa-trash-alt"></i></a></td>
											</tr>
										<%
											count++;
												}
											}
										%> 
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="card">
							  <div class="card-body">
							    <h4 class="card-title">Sub total R$ <span id="supTotal">
							    	<% 
							    		if(cart != null)
							    			out.print(cart.getTotal()); 
							    	%></span></h4>
							    	<form>
							    	<%
							    		if(cart != null && cart.getItemShoppingCarts().size() != 0)
							    			out.print("<button type='submit' class='btn btn-warning'>Comprar</button>");
							    	%>    
							    	</form>	
							  </div>
							</div>
						</div>
					</div>
				</form>
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
						<li><a href="./index.html"> Coisas AleatÃ³rias </a></li>
						<li><a href="./team-developer.html"> Nosso Time </a></li>
						<li><a href="./fale_conosco.html"> Fale Conosco </a></li>
					</ul>
				</nav>
				<div class="float-right">Â© 2019, feito por Daniel Dias</div>
			</div>
		</footer>
	<script src="./javascript/jquery.min.js"></script>
	<script src="./javascript/popper.min.js"></script>
	<script src="./javascript/bootstrap.min.js"></script>
	<script src="./javascript/now-ui/now-ui-kit.min.js"></script>
	<script type="text/javascript">
		$(".quantity").change(function() {
			calculeteValue();
		})
		
		function calculeteValue(){
			let total = 0.0;
			$(".products").each(function(e){
				let value = $(this).find(".value").html()
				let newVal = $(this).find(".quantity").val() * value;
				total += newVal;
			})
			
			$("#supTotal").text(total);
			
		}
		
	</script>
</body>
</html>
