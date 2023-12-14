<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/views/web/header/header.jsp" />

<div id="content">
	<c:choose>
		<c:when test="${page == 'shop'}">
			<jsp:include page="shop/shop.jsp" />
		</c:when>
		<c:when test="${page == 'about'}">
			<jsp:include page="shop/about.jsp" />
		</c:when>
		<c:when test="${page == 'shop_details'}">
			<jsp:include page="shop/shop-details.jsp" />
		</c:when>
		<c:when test="${page == 'shopping_cart'}">
			<jsp:include page="shop/shopping-cart.jsp" />
		</c:when>
		<c:when test="${page == 'check_out'}">
			<jsp:include page="shop/checkout.jsp" />
		</c:when>
		<c:when test="${page == 'blog_details'}">
			<jsp:include page="shop/blog-details.jsp" />
		</c:when>
		<c:when test="${page == 'contacts'}">
			<jsp:include page="shop/contact.jsp" />
		</c:when>
		<c:when test="${page == 'blog'}">
			<jsp:include page="shop/blog.jsp" />
		</c:when>
		<c:when test="${page == 'login'}">
			<jsp:include page="shop/Authentication/login.jsp" />
		</c:when>
		<c:when test="${page == 'register'}">
			<jsp:include page="shop/Authentication/register.jsp" />
		</c:when>
		<c:when test="${page == 'edit_profile'}">
			<jsp:include page="shop/edit_Profile.jsp" />
		</c:when>
		<c:when test="${page == 'forgot_password'}">
			<jsp:include page="shop/Authentication/forgot_password.jsp" />
		</c:when>
		<c:when test="${page == 'enter_otp'}">
			<jsp:include page="shop/Authentication/enter_otp.jsp" />
		</c:when>
		<c:when test="${page == 'new_password'}">
			<jsp:include page="shop/Authentication/new_password.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="shop/index.jsp" />
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="/views/web/footer/footer.jsp" />