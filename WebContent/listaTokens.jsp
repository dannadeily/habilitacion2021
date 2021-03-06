<%@page import="co.edu.ufps.habilitacion.entidades.*"%>
<%@page import="co.edu.ufps.habilitacion.dao.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Lista de Solicitudes</title>
<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />
<link rel="icon" href="<%=request.getContextPath()%>/assets/img/icon.ico" type="image/x-icon" />

<!-- Fonts and icons -->
<script src="<%=request.getContextPath()%>/assets/js/plugin/webfont/webfont.min.js"></script>
<script>
		WebFont.load({
			google: {
				"families": ["Lato:300,400,700,900"]
			},
			custom: {
				"families": ["Flaticon", "Font Awesome 5 Solid", "Font Awesome 5 Regular", "Font Awesome 5 Brands",
					"simple-line-icons"
				],
				urls: ['<%=request.getContextPath()%>
	/assets/fonts.min.css' ]
		},
		active : function() {
			sessionStorage.fonts = true;
		}
	});
</script>

<!-- CSS Files -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/atlantis.min.css">

<!-- CSS Just for demo purpose, don't include it in your project -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/demo.css">
</head>

<body>
	<div class="wrapper">
		<div class="main-header">
			<!-- Logo Header -->
			<div class="logo-header" data-background-color="blue">
				<a href="index.html" class="logo"> <img
					src="<%=request.getContextPath()%>/assets/img/logo.svg" alt="navbar brand"
					class="navbar-brand">
				</a>
				<button class="navbar-toggler sidenav-toggler ml-auto" type="button"
					data-toggle="collapse" data-target="collapse" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"> <i class="icon-menu"></i>
					</span>
				</button>
				<button class="topbar-toggler more">
					<i class="icon-options-vertical"></i>
				</button>
				<div class="nav-toggle">
					<button class="btn btn-toggle toggle-sidebar">
						<i class="icon-menu"></i>
					</button>
				</div>
			</div>
			<!-- End Logo Header -->

			<!-- Navbar Header -->
			<nav class="navbar navbar-header navbar-expand-lg"
				data-background-color="blue2">
				<div class="container-fluid">
					<ul class="navbar-nav topbar-nav ml-md-auto align-items-center">
						<li class="nav-item dropdown hidden-caret"><a
							class="dropdown-toggle profile-pic" data-toggle="dropdown"
							href="#" aria-expanded="false">
								<div class="avatar-sm">
									<img src="<%=request.getContextPath()%>/assets/img/profile.jpg" alt="..."
										class="avatar-img rounded-circle">
								</div>
						</a>
							<ul class="dropdown-menu dropdown-user animated fadeIn">
								<div class="dropdown-user-scroll scrollbar-outer">
									<li>
										<div class="user-box">
											<div class="avatar-lg">
												<img src="<%=request.getContextPath()%>/assets/img/profile.jpg" alt="image profile"
													class="avatar-img rounded">
											</div>
											<div class="u-text">
												<h4>Hizrian</h4>
												<p class="text-muted">hello@example.com</p>
											</div>
										</div>
									</li>
									<li>
										<div class="dropdown-divider"></div> <a class="dropdown-item"
										href="../sign-in/singin.html">Logout</a>
									</li>
								</div>
							</ul></li>
					</ul>
				</div>
			</nav>
			<!-- End Navbar -->
		</div>

		<!-- Sidebar -->
		<div class="sidebar sidebar-style-2">
			<div class="sidebar-wrapper scrollbar scrollbar-inner">
				<div class="sidebar-content">
					<div class="user">
						<div class="avatar-sm float-left mr-2">
							<img src="<%=request.getContextPath()%>/assets/img/profile.jpg" alt="..."
								class="avatar-img rounded-circle">
						</div>
						<div class="info">
							<a aria-expanded="true"> <span> Hizrian <span
									class="user-level">Administrator</span>
							</span>
							</a>
						</div>
					</div>
					<ul class="nav nav-primary">
						<li class="nav-section"><span class="sidebar-mini-icon">
								<i class="fa fa-ellipsis-h"></i>
						</span>
							<h4 class="text-section">Componentes</h4></li>
						<li class="nav-item"><a data-toggle="collapse" href="#base">
								<i class="fas fa-layer-group"></i>
								<p>Informacion Basica</p>
						</a></li>
						<li class="nav-item"><a data-toggle="collapse"
							href="#sidebarLayouts"> <i class="fas fa-th-list"></i>
								<p>Informacion Personas</p>
						</a></li>
						<li class="nav-item"><a data-toggle="collapse" href="#forms">
								<i class="fas fa-pen-square"></i>
								<p>Informacion Cormobilidades</p>
						</a></li>
						<li class="nav-item"><a data-toggle="collapse" href="#tables">
								<i class="fas fa-table"></i>
								<p>Registro Ingreso</p>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- End Sidebar -->

		<div class="main-panel">
			<div class="content">
				<div class="page-inner">
					<div class="page-header">
						<h4 class="page-title">Dashboard</h4>
						<div class="ml-md-auto">
							<ul class="breadcrumbs">
								<li class="nav-home"><a href="#"> <i
										class="flaticon-home"></i>
								</a></li>
								<li class="separator"><i class="flaticon-right-arrow"></i>
								</li>
								<li class="nav-item"><a href="#">Home</a></li>
							</ul>
						</div>
					</div>
					<%
					Connectiontoken token = (Connectiontoken) request.getAttribute("tokens");
					%>
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">Basic</h4>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table id="basic-datatables"
											class="display table table-striped table-hover">
											<thead class="text-center">
												<tr>
													<th>Nombre</th>
													<th>Base</th>
													<th>Host</th>
													<th>Estado</th>
													<th>Acciones</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<th scope="row"><%=token.getToken()%></th>
													<td><%=token.getDb()%></td>
													<td><%=token.getHost()%></td>
													<td><%=token.getState()%></td>
													<td><button type="button"
															onclick="location.href='reportes'" class="btn btn-dark">Ver
															Reportes</button></td>

												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>

			<footer class="footer">
				<div class="container-fluid">
					<nav class="pull-left">
						<ul class="nav">
							<li class="nav-item"><a class="nav-link"
								href="https://www.themekita.com"> ThemeKita </a></li>
							<li class="nav-item"><a class="nav-link" href="#"> Help
							</a></li>
							<li class="nav-item"><a class="nav-link" href="#">
									Licenses </a></li>
						</ul>
					</nav>
					<div class="copyright ml-auto">
						2018, made with <i class="fa fa-heart heart text-danger"></i> by <a
							href="https://www.themekita.com">ThemeKita</a>
					</div>
				</div>
			</footer>
		</div>
	</div>
	<!--   Core JS Files   -->
	<script src="<%=request.getContextPath()%>/assets/js/core/jquery.3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/core/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/core/bootstrap.min.js"></script>

	<!-- jQuery UI -->
	<script
		src="<%=request.getContextPath()%>/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>

	<!-- jQuery Scrollbar -->
	<script
		src="<%=request.getContextPath()%>/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>

	<!-- jQuery Sparkline -->
	<script
		src="<%=request.getContextPath()%>/assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

	<!-- Chart Circle -->
	<script src="<%=request.getContextPath()%>/assets/js/plugin/chart-circle/circles.min.js"></script>

	<!-- Datatables -->
	<script src="<%=request.getContextPath()%>/assets/js/plugin/datatables/datatables.min.js"></script>

	<!-- jQuery Vector Maps -->
	<script src="<%=request.getContextPath()%>/assets/js/plugin/jqvmap/jquery.vmap.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugin/jqvmap/maps/jquery.vmap.world.js"></script>

	<!-- Sweet Alert -->
	<script src="<%=request.getContextPath()%>/assets/js/plugin/sweetalert/sweetalert.min.js"></script>

	<!-- Atlantis JS -->
	<script src="<%=request.getContextPath()%>/assets/js/atlantis.min.js"></script>

	<!-- Atlantis DEMO methods, don't include it in your project! -->
	<script src="<%=request.getContextPath()%>/assets/js/setting-demo.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/demo.js"></script>

</body>

</html>