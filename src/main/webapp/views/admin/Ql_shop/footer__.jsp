<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--**********************************
            Footer start
        ***********************************-->
<div class="footer">
	<div class="copyright">
		<p>
			Copyright &copy; Designed & Developed by <a
				href="https://themeforest.net/user/quixlab">Quixlab</a> 2018
		</p>
	</div>
</div>
<!--**********************************
            Footer end
        ***********************************-->
</div>
<!--**********************************
        Main wrapper end
    ***********************************-->


<jsp:include page="/views/admin/Ql_shop/footer_import.jsp"></jsp:include>

<c:if test="${ not empty message_add}">
	<%
	String message = (String) session.getAttribute("message_add");
	if (message != null) {
	%>
	<jsp:include page="/views/progress/index.jsp"></jsp:include>
	<%
	session.removeAttribute("message_add"); // Để tránh hiển thị lại thông báo khi refresh trang
	}
	%>
</c:if>
<c:if test="${not empty message}">

	<jsp:include page="/views/progress/index.jsp"></jsp:include>

</c:if>
<c:if test="${not empty USER }">
	<jsp:include page="/views/progress/index.jsp"></jsp:include>
</c:if>
</body>
</html>