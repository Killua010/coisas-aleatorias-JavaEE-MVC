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
  <div class="page-header clear-filter" filter-color="orange">
    <div class="page-header-image" style="background-image:url(./img/banner2.jpg)"></div>
    <div class="content">
      <div class="container">
        <div class="col-md-4 ml-auto mr-auto">
          <div class="card card-login card-plain">
            <div class="card card-signup" data-background-color="orange">
              <form class="form" method="POST" action="usuarios">
                <div class="card-header text-center">
                  <h3 class="card-title title-up">Login</h3>
                </div>
                <div class="card-body">
                <%
	             	String message = (String) request.getAttribute("erro");
	             	if(message != null){
	             		out.print("<div class='alert alert-info'><span>" + message + "</span></div>");	
	             	}
	             %>
                  <div class="input-group no-border input-lg">
                    <div class="input-group-prepend">
                      <span class="input-group-text">
                        <i class="fas fa-user icone-login"></i>
                      </span>
                    </div>
                    <input type="email" class="form-control" name="txtEmail" placeholder="Digite o seu email">
                  </div>
                  <div class="input-group no-border input-lg">
                    <div class="input-group-prepend">
                      <span class="input-group-text">
                        <i class="fas fa-lock icone-login"></i>
                      </span>
                    </div>
                    <input type="password" placeholder="Digite sua senha" name="txtSenha" class="form-control" />

                  </div>
                </div>
                <div class="card-footer text-center">
                  <button type="submit" class="btn btn-primary btn-round btn-lg btn-block">Entrar</button>
                  <a href="./cadastro_cliente.jsp" class="link">Criar conta</a>
                </div>
              </form>
            </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <footer class="footer">
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
  </div>
  <script src="./javascript/jquery.min.js"></script>
  <script src="./javascript/popper.min.js"></script>
  <script src="./javascript/bootstrap.min.js"></script>
  <script src="./javascript/now-ui/now-ui-kit.min.js"></script>
</body>

</html>
