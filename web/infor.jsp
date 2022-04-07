<%-- 
    Document   : profile
    Created on : Jan 14, 2022, 2:18:27 PM
    Author     : dtsang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

    </head>
    <body>
        <div id="wrapper">
            <c:if test="${sessionScope.LOGIN_USER == null }">
                <c:redirect url="login.jsp"></c:redirect>

            </c:if>
            <c:if test="${sessionScope.LOGIN_USER.roleID == 'US'}">
                <%@include file="sidebar.jsp"%>
            </c:if>
            <c:if test="${sessionScope.LOGIN_USER.roleID == 'LD'}">
                <%@include file="sidebar.jsp"%>
            </c:if>
            <c:if test="${sessionScope.LOGIN_USER.roleID == 'MT'}">
                <%@include file="supSidebar.jsp"%>
            </c:if>
            <c:if test="${sessionScope.LOGIN_USER.roleID == 'AD'}">
                <%@include file="modSidebar.jsp"%>
            </c:if>
            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                        <form class="form-inline">
                            <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                                <i class="fa fa-bars"></i>
                            </button>
                        </form>
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
                                    <img class="img-profile rounded-circle" src="${sessionScope.LOGIN_USER.photoUrl}">
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
                    <div class="container-fluid" style="height: 70%">
                        <div class="card shadow mb-4">
                            
                                <div class="container">                              
                                    <div class="row">
                                            <h2 style="text-align: center">Profile</h2>
                                        <div class="col-7">
                                            <div class="row">
                                                <div class="col-6">
                                                    <div class="mb-3">
                                                        <label for="exampleFormControlInput1" class="form-label">ID</label>
                                                        <input class="form-control" type="text" id="userID" name="userID" aria-label="Disabled input example" readonly value="${sessionScope.INFORSTU.userID}">
                                                    </div>
                                                </div>
                                                <div class="col-6">
                                                    <div class="mb-3">
                                                        <label for="exampleFormControlInput1" class="form-label">Email address</label>
                                                        <input type="email" class="form-control" name="email" id="exampleFormControlInput1" placeholder="name@example.com" readonly value="${sessionScope.INFORSTU.gmail}" >
                                                    </div>
                                                </div>
                                                <div class="col-6">
                                                    <div class="mb-3">
                                                        <label for="exampleFormControlInput1" class="form-label">Full Name</label>
                                                        <input type="text" class="form-control" name="fullname" id="exampleFormControlInput1" readonly value="${sessionScope.INFORSTU.username}">
                                                    </div>
                                                </div>
                                                <div class="col-6">
                                                    <div class="mb-3">
                                                        <label for="exampleFormControlInput1" class="form-label">Phone</label>
                                                        
                                                        <input type="number" class="form-control" name="phone" id="phone" readonly value="${sessionScope.INFORSTU.phone}">
                                                        
                                                        <span id="span"></span>
                                                    </div>
                                                </div>
                                                <c:if test="${sessionScope.LOGIN_USER.roleID ne 'MT' and sessionScope.LOGIN_USER.roleID ne 'AD' }">
                                                    <div class="col-6">
                                                        <div class="mb-3">
                                                            <label for="exampleFormControlInput1" class="form-label">Semester</label>
                                                            <input type="text" class="form-control" name="semesterName" id="exampleFormControlInput1" disabled value="${sessionScope.INFOR.semesterName}">
                                                        </div>
                                                    </div>
                                                    <div class="col-6">
                                                        <div class="mb-3">
                                                            <label for="exampleFormControlInput1" class="form-label">Capstone Name</label>
                                                            <input type="text" class="form-control" name="capstoneName" id="exampleFormControlInput1" value="${sessionScope.INFOR.capstoneName}" disabled>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="col-5">
                                            <div id="preview-wrapper">
                                                <div class="container">
                                                    <div class="avatar-upload">                                               
                                                        <div class="avatar-preview">
                                                            <div>
                                                                <img id="imagePreview" src="${sessionScope.INFORSTU.photoUrl}"/>
                                                                <input type='text' id="imageURL" name="imageURL" hidden value="${sessionScope.INFORSTU.photoUrl}"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>

                                </div>
                                                        
                        </div>
                    </div>
                </div>
                <%@include file="footer.jsp" %>
            </div>
        </div>
        <%@include file="logout.jsp" %>
        <style >
            .container {
                max-width: 960px;
                margin: 30px auto;
                padding: 20px;
            }
            .avatar-upload {
                position: relative;
                max-width: 205px;
                margin: -20px auto;
            }
            .avatar-upload .avatar-edit {
                position: absolute;
                right: 12px;
                z-index: 1;
                top: 10px;
            }
            .avatar-upload .avatar-edit input {
                display: none;
            }
            .avatar-upload .avatar-edit input + label {
                display: inline-block;
                width: 34px;
                height: 34px;
                margin-bottom: 0;
                border-radius: 100%;
                background: #ffffff;
                border: 1px solid transparent;
                box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.12);
                cursor: pointer;
                font-weight: normal;
                transition: all 0.2s ease-in-out;
            }
            .avatar-upload .avatar-edit input + label:hover {
                background: #f1f1f1;
                border-color: #d6d6d6;
            }
            .avatar-upload .avatar-edit input + label:after {
                content: "";
                font-family: "FontAwesome";
                color: #757575;
                position: absolute;
                top: 10px;
                left: 0;
                right: 0;
                text-align: center;
                margin: auto;
            }
            .avatar-upload .avatar-preview {
                width: 192px;
                height: 192px;
                position: relative;
                border-radius: 100%;
                border: 6px solid #f8f8f8;
                box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.1);

            }
            .avatar-upload .avatar-preview img{

                width: 192px;
                height: 192px;
                position: relative;
                border-radius: 100%;
                background-size: auto;
                background-repeat: no-repeat;
                object-fit: cover;

            }
            .avatar-upload .avatar-preview > div {
                width: 100%;
                height: 100%;
                border-radius: 100%;
                background-size: cover;
                background-repeat: no-repeat;
                background-position: center;
            }

        </style>



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>  
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
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script type="module">
            import { initializeApp } from "https://www.gstatic.com/firebasejs/9.6.3/firebase-app.js";
            import { getStorage, ref, uploadBytesResumable, getDownloadURL } from "https://www.gstatic.com/firebasejs/9.6.3/firebase-storage.js";
            const firebaseConfig = {
                apiKey: "AIzaSyAaeDrbFpEOKxfsmeYaUvVhOCsh9o8nxyI",
                authDomain: "upload-image-125bb.firebaseapp.com",
                projectId: "upload-image-125bb",
                storageBucket: "upload-image-125bb.appspot.com",
                messagingSenderId: "186232203166",
                appId: "1:186232203166:web:8350eb791825d6adbad88f",
                measurementId: "G-16ZE60ZJVS"
            };
            const firebaseApp = initializeApp(firebaseConfig);
            const storage = getStorage(firebaseApp);
            function readURL(input) {
                const metadata = {
                    contentType: 'image/jpeg'
                };
                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        //            $('#imagePreview').css('background-image', 'url(' + e.target.result + ')');
                        //            $('#imagePreview').hide();
                        //            $('#imagePreview').fadeIn(650);

                    }

                    reader.readAsDataURL(input.files[0]);
                    const name = new Date().getTime() + '-' + input.files[0].name;
                    console.log(name);
                    const storageRef = ref(storage, 'images/' + name);
                    uploadBytesResumable(storageRef, input.files[0], metadata).then((snapshot) => {
                        console.log('Uploaded', snapshot.totalBytes, 'bytes.');
                        console.log('File metadata:', snapshot.metadata);
                        getDownloadURL(snapshot.ref).then((url) => {
                            console.log('File available at', url);
                            document.getElementById("imagePreview").src = url;
                            document.getElementById("imageURL").value = url;
                        });
                    }).catch((error) => {
                        console.error('Upload failed', error);

                    });
                }
            }

            $("#imageUpload").change(function () {
                readURL(this);

            });
//            
//        $(document).ready(function() {
//
//                //Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
//                $("#form").validate({
//                        rules: {
//                                userID: "required",
//                                email: "required",
//                                fullname: {
//                                        required: true,
//                                        minlength: 2
//                                },
//                                phone: {
//                                    required: true,
//                                    minlength: 10
//                                }
//                        },
//                        messages: {
//                                userID: "phone is required",
//                                email: "Vui lòng nhập tên",
//                                fullname: {
//                                        required: "phone is required",
//                                        minlength: "Địa chỉ ngắn vậy, chém gió ah?"
//                                },
//                                phone:{
//                                     required: "phone is required",
//                                     minlength: "Địa chỉ ngắn vậy, chém gió ah?"
//                                }
//                        }
//                });
//        });
            
            //sweatalert
            $("#btnSubmit").click(function () {
//                if ($("input").first().val() === "correct") {
//                    $("span").text("Validated...").show();
//                    return;
//                }

                Swal.fire({
                    title: 'Are you sure?',
                    text: "You want to update it!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes, Update Profile!'
                }).then((result) => {
                    if (result.isConfirmed) {

                        Swal.fire(
                                'Updated!',
                                'Your Profile has been updated.',
                                'success'
                                );
                        setTimeout(function (e) {
                            $("#form").submit();
                        }, 1000);
                    }
                })
            });
//            $("span").text("Not valid!").show().fadeOut(1000);
//            event.preventDefault();
        </script>

    </body>
</html>
