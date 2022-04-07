<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Student Invite List</title>

        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="css/chat.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
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
                        <form action="GetListController?radioGroup=${param.radioGroup}" method="POST"
                              class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                            <div class="input-group">
                                <input type="text" class="form-control bg-light border-0 small" placeholder="Search for name..."
                                       aria-label="Search" aria-describedby="basic-addon2"name="txtSearch" value="${param.txtSearch}">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="submit">
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
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400" ></i>
                                        Logout
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>
                    <!-- Begin Page Content -->
                    <div class="container-fluid" style="margin-bottom: 20px">

                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Student</h1>

                        <div class="card shadow mb-4">
                            <div class="card-body">
                                <div class="table-responsive" >

                                    <table class="table-sm" width="100%" cellspacing="0">
                                        <thead>
                                            <tr >
                                                <th>No</th>
                                                <th>Name</th>
                                                <th>Gmail</th>
                                                <th>Photo</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <!-- tu chon field -->
                                        <c:forEach var="stu" varStatus="counter" items="${LIST_STUDENT}">
                                            <tbody>
                                                <tr>
                                                    <td>${counter.count}</td>
                                                    <td>${stu.username}</td>
                                                    <td>${stu.gmail}</td>
                                                    <td> 
                                                        <c:if test="${stu.photoUrl eq null}">
                                                            <img style="height: 100px; width: 100px; border-radius: 100%" src="img/275648169_3113055995634312_1238254939168784104_n.jpg"/>
                                                        </c:if>
                                                        <c:if test="${not empty stu.photoUrl}">
                                                            <img style="height: 100px; width: 100px; border-radius: 100%" src="${stu.photoUrl}"/>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <form action="MainController">
                                                            <input type="hidden" name="userID" value="${stu.userID}"/>
                                                            <input type="hidden" name="email" value="${stu.gmail}"/>
                                                            <button <c:if test="${sessionScope.CHECK_NUMOFPER == false}"> disabled </c:if> type="submit" name="action" value="Invite" class="btn btn-success btn-circle btn-sm"> <i class="fas fa-plus"></i> </button>
                                                            </form>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                        </c:forEach>

                                    </table>
                                </div>

                            </div>
                            <!-- /.container-fluid -->

                        </div>

                    </div>
                    <nav aria-label="Page navigation example" style="position: absolute; right: 10px" >
                        <ul class="pagination">
                            <c:url var="nextpage" value="GetListController">
                                <c:param name="radioGroup" value="${param.radioGroup}"></c:param>
                                <c:param name="txtSearch" value="${param.txtSearch}"></c:param>
                                <c:param name="semesterID" value="${param.semesterID}"></c:param>
                            </c:url>
                            <c:if test="${requestScope.currentPage > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="${nextpage}&page=${requestScope.currentPage - 1}"aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                                <c:choose >
                                    <c:when test="${requestScope.currentPage == i}">

                                        <li class="page-item"><a class="page-link" href="#">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                        <li class="page-item"><a class="page-link" href="${nextpage}&page=${i}">${i}</a></li>

                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${requestScope.currentPage < requestScope.noOfPages}">
                                <li class="page-item">
                                    <a class="page-link" href="${nextpage}&page=${requestScope.currentPage + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>    
                        </ul>
                    </nav>
                    <!-- End of Main Content -->

                </div>
                <div class="mt-5"></div>
                <!-- Footer -->
                <%@include file ="footer.jsp" %>
                <!-- End of Footer -->

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
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>

    </body>

</html>