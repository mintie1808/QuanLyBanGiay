<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/views/admin/Ql_shop/header.jsp" />
<!-- chỉnh file index.jsp -->
<div class="content-body">
	<c:choose>
		<c:when test="${page == 'content_body'}">
			<jsp:include page="Ql_shop/content-body.jsp" />
		</c:when>
		<c:when test="${page == 'user_management'}">
			<jsp:include page="Ql_shop/UserManagement.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="Ql_shop/index.jsp" />
		</c:otherwise>
	</c:choose>
</div>
<!-- set footer cho file -->
<c:choose>
    <c:when test="${empty page or page == 'index'}">
        <jsp:include page="/views/admin/Ql_shop/footer.jsp" />
    </c:when>
    <c:otherwise>
        <jsp:include page="/views/admin/Ql_shop/footer__.jsp" />
    </c:otherwise>
</c:choose>
