<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Footer Section Begin -->
<footer class="footer">
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-6 col-sm-6">
				<div class="footer__about">
					<div class="footer__logo">
						<a href="#"><img src="img/shop/footer-logo.png" alt=""></a>
					</div>
					<p>The customer is at the heart of our unique business model,
						which includes design.</p>
					<a href="#"><img src="img/shop/payment.png" alt=""></a>
				</div>
			</div>
			<div class="col-lg-2 offset-lg-1 col-md-3 col-sm-6">
				<div class="footer__widget">
					<h6>Shopping</h6>
					<ul>
						<li><a href="./trang-chu?page=shop">Clothing Store</a></li>
						<li><a href="#">Trending Shoes</a></li>
						<li><a href="#">Accessories</a></li>
						<li><a href="#">Sale</a></li>
					</ul>
				</div>
			</div>
			<div class="col-lg-2 col-md-3 col-sm-6">
				<div class="footer__widget">
					<h6>Shopping</h6>
					<ul>
						<li><a href="./trang-chu?page=contacts">Contact Us</a></li>
						<li><a href="#">Payment Methods</a></li>
						<li><a href="#">Delivary</a></li>
						<li><a href="#">Return & Exchanges</a></li>
					</ul>
				</div>
			</div>
			<div class="col-lg-3 offset-lg-1 col-md-6 col-sm-6">
				<div class="footer__widget">
					<h6>NewLetter</h6>
					<div class="footer__newslatter">
						<p>Be the first to know about new arrivals, look books, sales
							& promos!</p>
						<form action="#">
							<input type="text" placeholder="Your email">
							<button type="submit">
								<span class="icon_mail_alt"></span>
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 text-center">
				<div class="footer__copyright__text">
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					<p>
						Copyright ©
						<script>
							document.write(new Date().getFullYear());
						</script>
						2020 All rights reserved | This template is made with <i
							class="fa fa-heart-o" aria-hidden="true"></i> by <a
							href="https://colorlib.com" target="_blank">Colorlib</a>
					</p>
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
				</div>
			</div>
		</div>
	</div>
</footer>
<!-- Footer Section End -->

<!-- Search Begin -->
<div class="search-model">
	<div class="h-100 d-flex align-items-center justify-content-center">
		<div class="search-close-switch">+</div>
		<form class="search-model-form" action="./shop" method="get">
			<input type="text" id="search-input" name="textsearchName"
				placeholder="Search here.....">
		</form>
	</div>
</div>
<!-- Search End -->
<div id="backtotop" class="align-items-center justify-content-center">
	<i class="arrow_up"></i>
</div>
<!-- Js Plugins -->
<script src="js/shop/jquery-3.3.1.min.js"></script>
<script src="js/shop/bootstrap.min.js"></script>
<script src="js/shop/jquery.nice-select.min.js"></script>
<script src="js/shop/jquery.nicescroll.min.js"></script>
<script src="js/shop/jquery.magnific-popup.min.js"></script>
<script src="js/shop/jquery.countdown.min.js"></script>
<script src="js/shop/jquery.slicknav.js"></script>
<script src="js/shop/mixitup.min.js"></script>
<script src="js/shop/owl.carousel.min.js"></script>
<script src="js/shop/main.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="js/js_click/click__.js"></script>

<c:if test="${ not empty message_success}">
	<%
	String message = (String) session.getAttribute("message_success");
	if (message != null) {
	%>
	<jsp:include page="/views/progress/index.jsp"></jsp:include>
	<%
	session.removeAttribute("message_success"); // Để tránh hiển thị lại thông báo khi refresh trang
	}
	%>
</c:if>
<c:if test="${not empty message}">

	<jsp:include page="/views/progress/index.jsp"></jsp:include>

</c:if>

<c:if test="${not empty USER }">
	<jsp:include page="/views/progress/index.jsp"></jsp:include>

	<div class="sweet-overlay editProfile" tabindex="-1"
		style="opacity: 1.09; display: none;">
		<section class="vh-100">
			<div class="container py-5 h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col col-lg-8 mb-4 mb-lg-0 close_Information">
						<div class="card mb-3" style="border-radius: .5rem; border: none;">
							<div class="row g-0">
								<div class="col-md-4 gradient-custom text-center text-white"
									style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem; box-shadow: 0px 1px 10px 0 rgba(0, 0, 0, 0.15);">

									<div class="position-absolute d-md-none p-2 div_close_i"
										style="right: 0; font-size: 23px;">
										<span class="i_close_1"> <i class="icon_close_alt2"></i>
										</span> <span class="i_close_2"> <i class="icon_close_alt"></i>
										</span>
									</div>

									<c:choose>
										<c:when test="${not empty USER.img }">
											<img src="img/${USER.img }" alt="Avatar"
												class="img-fluid my-5"
												style="width: 80px; border-radius: 50%; box-shadow: 0px 1px 10px 0 rgba(0, 0, 0, 0.15); background: #fff;" />
										</c:when>
										<c:otherwise>
											<img src="img/user/Avatar_trang.jpg" alt="Avatar"
												class="img-fluid my-5"
												style="width: 80px; border-radius: 50%; box-shadow: 0px 1px 10px 0 rgba(0, 0, 0, 0.15);" />
										</c:otherwise>
									</c:choose>
									<h5>${USER.fullname }</h5>
									<p>Hello Customer</p>
									<div class="f_logout">
										<input type="hidden" name="logout" value="true"> <span><button
												class="btn btn-outline-danger click_logout">Logout</button></span>
									</div>
									<p></p>
								</div>
								<div class="col-md-8">
									<div class="card-body p-4">
										<div class="text-right Information">
											<div class="popover fade bs-popover-top css_edit_profile"
												style="position: absolute; will-change: transform; top: 0px; left: 0px;">
												<div class="arrow" style="left: 33px;"></div>
												<h3 class="popover-header"></h3>
												<div class="popover-body">Edit profile</div>
											</div>
											<a href="#" class="a_edit popoverTrigger"> <span>
													<i class="icon_link_alt"></i>
											</span>
											</a>
										</div>
										<h6>Information</h6>
										<hr class="mt-0 mb-4">
										<div class="row pt-1">
											<div class="col-6 mb-3">
												<h6>Email</h6>
												<p class="text-muted">${USER.email }</p>
											</div>
											<div class="col-6 mb-3">
												<h6>Account</h6>
												<p class="text-muted">${USER.username }</p>
											</div>
										</div>
										<h6>Other information</h6>
										<hr class="mt-0 mb-4">
										<div class="row pt-1">
											<div class="col-6 mb-3">
												<h6>Address</h6>
												<p class="text-muted">${USER.address }</p>
											</div>
											<div class="col-6 mb-3">
												<h6>Phone</h6>
												<p class="text-muted">0${USER.phone }</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</c:if>

</body>

</html>