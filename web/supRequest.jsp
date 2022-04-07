<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="user.UserDTO"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Supervisor Request</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="css/chat.css" rel="stylesheet">
    </head>

    <body id="page-top">
        <c:if test="${sessionScope.LOGIN_USER == null or sessionScope.LOGIN_USER.roleID ne 'MT'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <!-- Page Wrapper -->       
        <div id="wrapper">
            <%@include file="supSidebar.jsp" %>
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                        <form
                            class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                            <div class="input-group">
                                <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                       aria-label="Search" aria-describedby="basic-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">
                                        <i class="fas fa-search fa-sm"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                        <ul class="navbar-nav ml-auto">
                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.LOGIN_USER.username} (${sessionScope.LOGIN_USER.userID})</span>
                                    <img class="img-profile rounded-circle" src="${sessionScope.INFOR.photoUrl}">
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
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Request</h1>
                        </div>
                        <!-- <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
                            For more information about DataTables, please visit the <a target="_blank"
                                href="https://datatables.net">official DataTables documentation</a>.</p> -->

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-body">
                                <div class="table-responsive">
                                   
                                    <table class="table-sm" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>No</th>
                                                <th>Captones Name</th>
                                                <th>Group Name </th>
                                                <th>Request</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="top" varStatus="counter" items="${LIST_REGIST_TOPIC}">
                                                <tr>
                                                    <td>${counter.count}</td>
                                                    <td>${top.groupID}</td>
                                                    <td>${top.phone}</td>
                                                    <td>
                                                        <form action="MainController">
                                                            <input type="hidden" name="leaderID" value="${top.username}"/>
                                                            <input type="hidden" name="groupID" value="${top.gmail}"/>
                                                            <input type="hidden" name="capstoneID" value="${top.statusID}"/>
                                                            <button type="submit" name="action" value="Accept" class="btn btn-success btn-circle btn-sm"> <i class="fas fa-check"></i> </button>
                                                            <button type="submit" name="action" value="Refuse" class="btn btn-danger btn-circle btn-sm"> <i class="fas fa-ban"></i> </button>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <nav aria-label="Page navigation example" style="position: absolute; right: 20px" >
                            <ul class="pagination">
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>       
                    <!-- /.container-fluid -->

                </div>

                <!-- End of Main Content -->
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