<%@page import="domain.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <!-- Now UI -->
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/now-ui/now-ui-kit.min.css">
    <!-- Page Style -->
    <link rel="stylesheet" href="./css/style.css">
    <title>Coisas Aleatórias</title>
</head>
<body class="index-page sidebar-collapse">
    <jsp:include page="componentes/main-navbar.jsp" />
    <div class="wrapper">
        <div class="page-header clear-filter" filter-color="orange">
            <!-- Banner -->
            <div class="page-header-image" data-parallax="true" style="background-image:url('./img/banner2.jpg');">
            </div>
            <div class="container">
                <div class="content-center brand">
                    <h1 class="h1-seo">Coisas Aleatórias</h1>
                    <h3>Compre coisas aleatórias sem sair de casa</h3>
                </div>
            </div>
            <!-- ./Banner -->
        </div>
        <!-- Content -->
        <div class="main">
            <div class="section pt-2">
                <!-- Container -->
                <div class="container">
                    <!-- MainCategories -->
                    <div class="row justify-content-md-center">
                        <h3 class="title text-primary">Pricipais Categorias</h3>
                    </div>
                    <div class="row">
                    <%
                    	List<Category> categories = (List<Category>) request.getSession().getAttribute("categories");
                    	categories = categories.subList(0, 2);
                    	
                    	for(Category category: categories){
                    	%>
                    		<div class="col-md-6">
	                            <a href="catalogos#<% out.print(category.getName()); %>">
	                                <div class="zoom mt-2 text-center">
	                                    <img src="<% out.print(category.getPhoto()); %>" alt="" height="450">
	                                    <div class="carousel-caption">
	                                        <h5 class="h2 pb-5 pb-response" style="text-shadow: 2px 0 0 #000;"><% out.print(category.getName()); %></h5>
	                                    </div>
	                                </div>
	                            </a>
	                        </div>
                    		<%
                    	}
                    %>
                    </div>
                    <!-- ./MainCategories -->
                    <div class="row justify-content-md-center mb-3 text-center">
                        <h3 class="title text-primary">
                            <i class="fas fa-dice-d20"></i>
                            <a href="./catalogo.html">Todos os nossos itens</a>
                        </h3>
                    </div>
                    <!-- Paralax -->
                    <div class="follow-us-parallax">
                        <div class="follow-us-content">
                            <h5 class="h2 follow-us-padding">Siga-nos nas redes socias</h5>
                            <div class="social-line social-left">
                                <a href="javascript:void(0)" class="btn btn-neutral btn-facebook btn-icon btn-round mx-2">
                                    <i class="fab fa-facebook-square"></i>
                                </a>
                                <a href="javascript:void(0)" class="btn btn-neutral btn-twitter btn-icon btn-round mx-2">
                                    <i class="fab fa-twitter"></i>
                                </a>
                                <a href="javascript:void(0)" class="btn btn-neutral btn-google btn-icon btn-round mx-2">
                                    <i class="fab fa-google-plus"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <!-- ./Paralax -->
                </div>
                <!-- Container -->
            </div>
        </div>
        <!-- ./Content -->
        <!-- Footer -->
        <footer data-background-color="sucess" class="footer bg-primary">
            <div class="container">
                <nav>
                    <ul>
                        <li>
                            <a href="./index">
                                Coisas Aleatórias
                            </a>
                        </li>
                        <li>
                            <a href="./team-developer.html">
                                Nosso Time
                            </a>
                        </li>
                        <li>
                            <a href="./fale_conosco.html">
                                Fale Conosco
                            </a>
                        </li>
                    </ul>
                </nav>
                <div class="float-right">
                    Â© 2019, feito por Daniel Dias
                </div>
            </div>
        </footer>
        <!-- ./Footer -->
    </div>
    <script src="./javascript/jquery.min.js"></script>
    <script src="./javascript/popper.min.js"></script>
    <script src="./javascript/bootstrap.min.js"></script>
    <script src="./javascript/now-ui/now-ui-kit.min.js"></script>
</body>
</html>
