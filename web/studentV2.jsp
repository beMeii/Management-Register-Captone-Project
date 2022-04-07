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

        <title>Student</title>

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
        <c:if test="${sessionScope.LOGIN_USER == null or sessionScope.LOGIN_USER.roleID ne 'US' and sessionScope.LOGIN_USER.roleID ne 'LD'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <!-- Page Wrapper -->
        <div id="wrapper">
            <%@include file="studentSidebar.jsp"%>
            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <ul class="navbar-nav ml-auto">
                            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                            <li class="nav-item dropdown no-arrow d-sm-none">
                                <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-search fa-fw"></i>
                                </a>
                                <!-- Dropdown - Messages -->
                                <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                     aria-labelledby="searchDropdown">
                                    <form class="form-inline mr-auto w-100 navbar-search">
                                        <div class="input-group">
                                            <input type="text" class="form-control bg-light border-0 small"
                                                   placeholder="Search for..." aria-label="Search"
                                                   aria-describedby="basic-addon2">
                                            <div class="input-group-append">
                                                <button class="btn btn-primary" type="button">
                                                    <i class="fas fa-search fa-sm"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>
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
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400" ></i>
                                        Logout
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Group</h1>
                        <!-- <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
                            For more information about DataTables, please visit the <a target="_blank"
                                href="https://datatables.net">official DataTables documentation</a>.</p> -->

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <!-- <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
                            </div> -->
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table-sm" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>No</th>
                                                <th>Name</th>
                                                <th>Phone</th>
                                                <th>Gmail</th>
                                                <th>Group name</th>
                                                <th>Info</th>
                                            </tr>
                                        </thead>
                                        <c:forEach var="listGroup" varStatus="counter" items="${LISTGROUP}">
                                            <tbody>
                                                <tr>
                                                    <td>${counter.count}</td>
                                                    <td>${listGroup.username}</td>
                                                    <td>${listGroup.phone}</td>
                                                    <td>${listGroup.gmail}</td>
                                                    <td>${listGroup.groupName}</td>
                                                    <td>
                                                        <c:url var="info" value="MainController">
                                                            <c:param name="action" value="Infor"></c:param>
                                                            <c:param name="userID" value="${listGroup.userID}"></c:param>
                                                        </c:url>
                                                        <a href="${info}" class="btn btn-info btn-circle btn-sm">
                                                            <i class="fas fa-info"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
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
        <%@include file="logout.jsp" %>

        <!-- Bootstrap core JavaScript-->
        <script>
            const toggleChatboxBtn = document.querySelector(".js-chatbox-toggle");
            const chatbox = document.querySelector(".js-chatbox");
            const chatboxMsgDisplay = document.querySelector(".js-chatbox-display");
            const chatboxForm = document.querySelector(".js-chatbox-form");

// Use to create chat bubble when user submits text
// Appends to display
            const createChatBubble = input => {
                const chatSection = document.createElement("p");
                chatSection.textContent = input;
                chatSection.classList.add("chatbox__display-chat");

                chatboxMsgDisplay.appendChild(chatSection);
            };

// Toggle the visibility of the chatbox element when clicked
// And change the icon depending on visibility
            toggleChatboxBtn.addEventListener("click", () => {
                chatbox.classList.toggle("chatbox--is-visible");

                if (chatbox.classList.contains("chatbox--is-visible")) {
                    toggleChatboxBtn.innerHTML = '<i class="fas fa-chevron-down"></i>';
                } else {
                    toggleChatboxBtn.innerHTML = '<i class="fas fa-chevron-up"></i>';
                }
            });

// Form input using method createChatBubble
// To append any user message to display
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