<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>SRC Software</title>
    <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/icon.ico" type="image/x-icon" />
    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/singup.css">

    <!-- CSS Files -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/checkradio.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/microcurriculo.css">
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/demo.css">
    <!-- switch-button CSS  -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/switch-button.css">
</head>

<body>

    <div class="cuerpologin">
        <div class="row vh-20">
            <div class="row w-100 d-flex justify-content-center">
                <h2 class="t-white mt-5">Datos Personales</h2>
            </div>
            <div class="row w-100 ">
                <div class="offset-md-10 col-2">
                    <img class="logoUFPS" src="<%=request.getContextPath()%>/assets/img/Logo-nuevo-vertical.png" alt="logo de la UFPS">
                </div>

            </div>
        </div>
        <div class=" row d-flex justify-content-center">
           <form class="w-75" action="<%=request.getContextPath()%>/user/signup/procesar" method="post">
                <div class=" fbr plogin  gbcolor">
                    <div class="mb-3 w-100 d-flex justify-content-center align-items-center">
                        <img class="account" src="../../assets/img/profile.svg" alt="">
                    </div>
                    <div class="mb-3 row w-100 d-flex justify-content-center align-items-center">
                        <div class="col-6">
                            <h3>Usuario</h3>
                            <input type="text" class="col form-control singin" name="usuario" placeholder="Usuario" id="" required>
                            <br>
                            <h3>Email</h3>
                            <input type="email" class="col form-control singin"  name="email" placeholder="Email" id="" required>
                            <br>
                            <h3>Contraseña</h3>
                            <input type="password" class="col form-control singin" name="pass" placeholder="Contraseña" id=""
                                required>
                            <br>
                            <h3>Rol</h3>
                            <select class=" col form-control" id="formGroupDefaultSelect" name="rol">
                       			<option value="1">Administrador</option>
								<option value="2">Usuario</option>
                            </select>
                            <br>
                        </div>
                        <!--botones-->
                        <div class="w-100 d-flex justify-content-center ">
                            <div class="w-25">
                            <button class="btn btn-danger mb-2 w-100 brlogin ml-2" type="submit">Registrar</button>
                            </div>
                        </div>
                        <!--botones-->
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--   Core JS Files   -->
    <script src="<%=request.getContextPath()%>/assets/js/core/jquery.3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/core/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/core/bootstrap.min.js"></script>
    <!-- jQuery UI -->
    <script src="<%=request.getContextPath()%>/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>
    <!-- jQuery Scrollbar -->
    <script src="<%=request.getContextPath()%>/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
    <!-- Atlantis JS -->
    <script src="<%=request.getContextPath()%>/assets/js/atlantis.min.js"></script>
    <!-- Atlantis DEMO methods, don't include it in your project! -->
    <script src="<%=request.getContextPath()%>/assets/js/setting-demo2.js"></script>
</body>

</html>