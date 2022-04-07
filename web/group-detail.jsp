<%-- 
    Document   : group-detail
    Created on : Jan 17, 2022, 2:53:22 PM
    Author     : dtsang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Group detail</title>
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="css/chat.css" rel="stylesheet">
    </head>
    <body id="page-top">
        
        <!-- Page Wrapper -->       
        <div id="wrapper">
            <%@include file="supSidebar.jsp" %>
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <ul class="navbar-nav ml-auto">
                            <%@include file="noti.jsp" %>
                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.LOGIN_USER.username} (${sessionScope.LOGIN_USER.userID})</span>
                                    <img class="img-profile rounded-circle"
                                         src="img/undraw_profile.svg">
                                </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="profile.jsp">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Profile
                                    </a>

                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </nav>
                    <!-- Begin Page Content -->
                    <div class="container-fluid" style="height: 80vh">

                        <!-- Page Heading -->
                        <h1>Group Details</h1>
                        <div class="card shadow mb-4">
                            <h3 style="text-align: center" class="mt-5">${sessionScope.LISTSTUDENTINGROUP[0].groupName}</h3>
                            <div class="card-body ">
                                <div class="col-12">
                                    <div class="row" style="display: flex; align-items: center" >
                                        <c:forEach items="${sessionScope.LISTSTUDENTINGROUP}" var="list">
                                            <div class="col-3">
                                                <div class="card shadow p-3 mb-5 bg-body rounded " >
                                                    <img style="width: 250px; height: 250px ; border-radius: 50%; margin: 0 auto" src="${list.photoUrl}" class="card-img-top" alt="..." >
                                                    <div class="card-body">
                                                        <h5 class="card-title">${list.username}</h5>
                                                        <h6>Phone: ${list.phone} </h6>
                                                        <h6>Email: ${list.gmail}</h6>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>

                                    </div>
                                    <c:if test="${sessionScope.CAPSTONE != null}">
                                        <div>
                                            <h5 style="text-align: center">
                                                Capstone Name: 
                                            </h5>
                                            <h5 style="text-align: center">${sessionScope.CAPSTONE.capstoneName}</h5>
                                            <h5 style="text-align: center">
                                                Supervisor:
                                            </h5>
                                            <h5 style="text-align: center">
                                                ${sessionScope.CAPSTONE.username}
                                            </h5>
                                        </div>
                                    </c:if>

                                </div>

                            </div>
                        </div>
                    </div>
                    <!-- End of Main Content -->
                    <section class="chatbox js-chatbox">
                        <div class="chatbox__header">
                            <h3 class="chatbox__header-cta-text"><span class="chatbox__header-cta-icon"><i
                                        class="fas fa-comments"></i></span>Let's chat</h3>
                            <button class="js-chatbox-toggle chatbox__header-cta-btn u-btn"><i class="fas fa-chevron-up"></i></button>
                        </div>
                        <!-- End of .chatbox__header -->
                        <div class="js-chatbox-display chatbox__display">

                        </div>
                        <!-- End of .chatbox__display -->

                        <form class="js-chatbox-form chatbox__form">     
                            <div style="display: flex; width: 100%">                  
                                <input type="text" class="js-chatbox-input chatbox__form-input" placeholder="Type your message..." required>
                                <span>
                                    <button class="chatbox__form-submit u-btn"><i class="fas fa-paper-plane"></i></button>
                                </span>
                            </div>
                        </form>

                        <!-- End of .chatbox__form -->
                    </section>
                    <%@include file ="footer.jsp" %>

                </div>
                <!-- End of Content Wrapper -->

            </div>
            <!-- End of Page Wrapper -->

            <!-- Scroll to Top Button-->
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fas fa-angle-up"></i>
            </a>

            <!-- Logout Modal-->
            <%@include file ="logout.jsp" %>

            <!-- Bootstrap core JavaScript-->
            <script>
                const toggleChatboxBtn = document.querySelector(".js-chatbox-toggle");
                const chatbox = document.querySelector(".js-chatbox");
                const chatboxMsgDisplay = document.querySelector(".js-chatbox-display");
                const chatboxForm = document.querySelector(".js-chatbox-form");

                const createChatBubble = input => {
                    const chatSection = document.createElement("p");
                    chatSection.textContent = input;
                    chatSection.classList.add("chatbox__display-chat");

                    chatboxMsgDisplay.appendChild(chatSection);
                };

                toggleChatboxBtn.addEventListener("click", () => {
                    chatbox.classList.toggle("chatbox--is-visible");

                    if (chatbox.classList.contains("chatbox--is-visible")) {
                        toggleChatboxBtn.innerHTML = '<i class="fas fa-chevron-down"></i>';
                    } else {
                        toggleChatboxBtn.innerHTML = '<i class="fas fa-chevron-up"></i>';
                    }
                });

                chatboxForm.addEventListener("submit", e => {
                    const chatInput = document.querySelector(".js-chatbox-input").value;

                    createChatBubble(chatInput);

                    e.preventDefault();
                    chatboxForm.reset();
                });

            </script>
            <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

            <!-- Core plugin JavaScript-->
            <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="js/sb-admin-2.min.js"></script>

            <!-- Page level plugins -->
            <script src="vendor/chart.js/Chart.min.js"></script>

            <!-- Page level custom scripts -->
            <script src="js/demo/chart-area-demo.js"></script>
            <script src="js/demo/chart-pie-demo.js"></script>

    </body>
</html>
