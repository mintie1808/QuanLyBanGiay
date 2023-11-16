<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="description" content="Male_Fashion Template">
<meta name="keywords" content="Male_Fashion, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>${page }</title>

<link rel="icon" href="img/100-icon-cao_110447695.jpg"
	type="image/x-icon">
<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css/shop/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/shop/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="css/shop/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/shop/magnific-popup.css"
	type="text/css">
<link rel="stylesheet" href="css/shop/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/shop/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="css/shop/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/shop/style.css" type="text/css">
<link rel="stylesheet" href="css/shop/css.css" type="text/css">
<link href="css/progress/css.css" rel="stylesheet">
</head>

<body>

	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Offcanvas Menu Begin -->
	<div class="offcanvas-menu-overlay"></div>
	<div class="offcanvas-menu-wrapper">
		<div class="offcanvas__option">

			<c:choose>
				<c:when test="${empty USER }">
					<div class="offcanvas__links">
						<a href="./trang-chu?page=login">Sign in</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="c-pointer position-relative">
						<c:choose>
							<c:when test="${not empty USER.img }">
								<img src="img/${USER.img }" height="40" width="40" alt=""
									style="background: #fff; border-radius: 50%; box-shadow: 0px 1px 10px 0 rgba(0, 0, 0, 0.15);"
									class="offcanvas_click_img">
							</c:when>
							<c:otherwise>
								<img src="img/user/Avatar_trang.jpg" height="40" width="40"
									alt=""
									style="background: #fff; border-radius: 50%; box-shadow: 0px 1px 10px 0 rgba(0, 0, 0, 0.15);"
									class="offcanvas_click_img">
							</c:otherwise>
						</c:choose>
					</div>
				</c:otherwise>
			</c:choose>
			<div class="offcanvas__links">
				<a href="#">FAQs</a>
			</div>
			<div class="offcanvas__top__hover">
				<span>Usd <i class="arrow_carrot-down"></i></span>
				<ul>
					<li>USD</li>
					<li>EUR</li>
					<li>USD</li>
				</ul>
			</div>
		</div>
		<div class="offcanvas__nav__option">
			<a href="#" class="search-switch"><img
				src="img/shop/icon/search.png" alt=""></a> <a href="#"><img
				src="img/shop/icon/heart.png" alt=""></a> <a href="#"><img
				src="img/shop/icon/cart.png" alt=""> <span>0</span></a>
			<div class="price">$0.00</div>
		</div>
		<div id="mobile-menu-wrap"></div>
		<div class="offcanvas__text">
			<p>Free shipping, 30-day return or refund guarantee.</p>
		</div>
	</div>
	<!-- Offcanvas Menu End -->

	<!-- Header Section Begin -->
	<header class="header">
		<div class="header__top">
			<div class="container">
				<div class="row">

					<div class="col-lg-5 col-md-7">
						<div class="header__top__left">
							<p>Free shipping, 30-day return or refund guarantee.</p>
						</div>
					</div>

					<div class="col-lg-7 col-md-5">
						<div class="header__top__right">

							<c:if test="${not empty USER }">
								<div class="header__top__left">
									<div class="user-img c-pointer position-relative">
										<c:choose>
											<c:when test="${not empty USER.img }">
												<img src="img/${USER.img }" height="40" width="40" alt=""
													style="background: #fff;">
											</c:when>
											<c:otherwise>
												<img src="img/user/Avatar_trang.jpg" height="40" width="40"
													alt="" style="background: #fff;">
											</c:otherwise>
										</c:choose>
									</div>
									<div
										class="drop-down dropdown-profile animated fadeIn dropdown-menu">
										<div class="dropdown-content-body">
											<ul>
												<li>
													<div class="text-right">
														<div class="popover fade bs-popover-top"
															style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(157px, -42px, 0px);">
															<div class="arrow" style="left: 33px;"></div>
															<h3 class="popover-header"></h3>
															<div class="popover-body">Edit profile</div>
														</div>
														<a href="#" class="a_edit"> <span
															class="popoverTrigger"> <i class="icon_link_alt"></i>
														</span>
														</a>
													</div>
													<div class="ih-item circle effect1">
														<div class="span_profile">
															<div class="spinner"
																style="border: 1px solid #fff; box-shadow: 0px 1px 10px 0 rgba(0, 0, 0, 0.15);"></div>
															<div class="img">
																<c:choose>
																	<c:when test="${not empty USER.img }">
																		<img class="img_click" src="img/${USER.img }"
																			alt="img" style="background: #fff;">
																	</c:when>
																	<c:otherwise>
																		<img class="img_click" src="img/user/Avatar_trang.jpg"
																			alt="img" style="background: #fff;">
																	</c:otherwise>
																</c:choose>
															</div>
															<div class="info">
																<div class="info-back">
																	<h3>Profile</h3>
																</div>
															</div>
														</div>
													</div>
												</li>
												<li class="d-flex justify-content-center"><a
													href="app-profile.html"><span>${USER.fullname }</span></a>
												</li>

												<hr class="my-2">
												<li><a href="page-lock.html"><i
														class="icon_lock_alt"></i> <span>Lock Screen</span></a></li>
												<li>
													<div class="f_logout">
														<i class="icon_key_alt"></i> <span><button
																class="btn_logout click_logout">Logout</button></span>
													</div>

												</li>
											</ul>
										</div>
									</div>
								</div>
							</c:if>
							<div class="header__top__links">
								<c:if test="${empty USER }">
									<a href="./trang-chu?page=login">Sign in</a>
								</c:if>
								<a href="#">FAQs</a>
							</div>
							<div class="header__top__hover">
								<span>Usd <i class="arrow_carrot-down"></i></span>
								<ul>
									<li>USD</li>
									<li>EUR</li>
									<li>USD</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-3">
					<div class="header__logo">
						<a href="./trang-chu"><img
							src="img/100-icon-cao_110447695.jpg" alt=""
							style="width: 50px; height: 50px"></a>
					</div>
				</div>
				<div class="col-lg-6 col-md-6">
					<nav class="header__menu mobile-menu">
						<ul>
							<!-- class="active" -->
							<li><a href="./trang-chu">Home</a></li>
							<li><a href="./shop">Shop</a></li>
							<li><a class="not_href">Pages</a>
								<ul class="dropdown">
									<li><a href="./trang-chu?page=about">About Us</a></li>
									<li><a href="./trang-chu?page=shop_details">Shop
											Details</a></li>
									<li><a href="./trang-chu?page=shopping_cart">Shopping
											Cart</a></li>
									<li><a href="./trang-chu?page=check_out">Check Out</a></li>
									<li><a href="./trang-chu?page=blog_details">Blog
											Details</a></li>
								</ul></li>
							<li><a href="./trang-chu?page=blog">Blog</a></li>
							<li><a href="./trang-chu?page=contacts">Contacts</a></li>
						</ul>
					</nav>
				</div>
				<div class="col-lg-3 col-md-3">
					<div class="header__nav__option">
						<a href="#" class="search-switch"><img
							src="img/shop/icon/search.png" alt=""></a> <a href="#"><img
							src="img/shop/icon/heart.png" alt=""></a> <a
							href="./trang-chu?page=shopping_cart"><img
							src="img/shop/icon/cart.png" alt=""> <span>0</span></a>
						<div class="price">$0.00</div>
					</div>
				</div>
			</div>
			<div class="canvas__open">
				<i class="fa fa-bars"></i>
			</div>
		</div>
	</header>
	<!-- Header Section End -->