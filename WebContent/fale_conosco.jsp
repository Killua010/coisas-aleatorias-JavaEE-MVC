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
    <title>Fale Conosco</title>
</head>
<body class="index-page sidebar-collapse">
  	<jsp:include page="componentes/main-navbar.jsp" />
    <div class="page-header clear-filter" filter-color="orange">
        <!-- Banner -->
        <div class="page-header-image" data-parallax="true" style="background-image:url('./img/banner2.jpg');">
        </div>
        <div class="container mt-5">
            <h1>Fale Conosco</h1>
            <form class="col-md-6 offset-md-3" action="/CoisasAleatorias/fale-conosco" method="POST">
                <div class="card">
                        <div class="card-body">
                            <div class="col-md-12">
                                <label for="txtNome">Nome</label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fa fa-user-circle"></i></span>
                                    </div>
                                    <input type="text" class="form-control" placeholder="Left Font Awesome Icon" name="txtNome">
                                </div>
                            </div>
                            <div class="col-md-12">
                                <label for="txtEmail">Email</label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fa fa-user-circle"></i></span>
                                    </div>
                                    <input type="email" class="form-control" placeholder="Left Font Awesome Icon" name="txtEmail">
                                </div>
                            </div>
                            <div class="col-md-12">
                                <label for="txtAssunto">Assunto: </label>
                                <div class="input-group">
                                    <textarea name="txtAssunto" class="form-control" rows="3"></textarea>
                                </div>
                            </div>
                            <input type="submit" value="enviar" class="btn btn-primary float-right">
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!-- ./Banner -->
    </div>


    <div class="wrapper">
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
