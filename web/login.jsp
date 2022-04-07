<%-- 
    Document   : login
    Created on : Dec 15, 2021, 8:08:39 PM
    Author     : mac
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Login Page</title>

        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body class="bg-gradient-primary">

        <div class="container">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-5 d-none d-lg-block ">
                            <img src="img/IMG_0052.JPG" height="620px" width="470px">
                        </div>
                        <div class="col-lg-7">
                            <div class="p-5">
                                <form action="MainController" method="POST" class="user">
                                    <img src="img/2021-FPTU-Eng.png" width="530.98px">
                                    <div class="form-group">
                                        <input type="email" class="form-control form-control-user" id="exampleInputEmail" name="gmail"
                                               placeholder="Email Address">
                                    </div>
                                    <div class="form-group">
                                        <div >
                                            <input type="password" class="form-control form-control-user" name="password"
                                                   id="exampleInputPassword" placeholder="Password">
                                        </div>
                                        <hr>
                                        <input type="submit" name="action"class="btn btn-primary btn-user btn-block" value="Login">
                                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/ManagementRegisterCaptoneProject/login-google&response_type=code
                                           &client_id=130712381079-lsfn0bj6q55gicpnofp13tbaa5jh59ra.apps.googleusercontent.com&approval_prompt=force" class="btn btn-google btn-user btn-block">
                                            <i class="fab fa-google fa-fw"></i> Login with Google
                                        </a>
                                    </div>
                                </form>                               
                                <div style="text-align: center;"><i>${requestScope.ERROR}</i></div>
                            </div>                            
                        </div>
                    </div>
                </div>

            </div>
        </div>
            <!-- Bootstrap core JavaScript-->
            <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

            <!-- Core plugin JavaScript-->
            <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="js/sb-admin-2.min.js"></script>
    </body>
</html>
