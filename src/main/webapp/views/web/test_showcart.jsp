<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/views/web/header/header.jsp" />
<h1>Your SHopping Cart</h1>
<c:set var="shop" value="${sessionScope.SHOP}" />
<c:if test="${not empty shop}">
	<table border="1">
		<thead>
			<tr>
				<td>no.</td>
				<td>id</td>
				<td>name</td>
				<td>price</td>
				<td>Quantiry</td>
				<td>Action</td>
			</tr>
		</thead>
		<tbody>
			<form action="ControllerCarBean" method="post">
				<c:set var="count" value="0" />
				<c:forEach var="rows" items="${shop}">
					<c:set var="count" value="${count + 1}" />
					<tr>
						<td>${count}</td>
						<td>${rows.value.sanpham.productID}</td>
						<td>${rows.value.sanpham.productName}</td>
						<td>${rows.value.sanpham.price}</td>
						<td>${rows.value.quantity}</td>
						<td><input type="checkbox" name="rmv"
							value="${rows.value.sanpham.productID}"></td>
					</tr>
				</c:forEach>
				<tr>
					<c:url var="add" value="ControllerCarBean">
						<c:param name="action" value="AddMore" />
					</c:url>
					<td colspan="2"><a href="${add}">ADD more</a></td>
					<td><input type="submit" value="Remove" name="action">
					</td>
				</tr>
			</form>
		</tbody>
	</table>
</c:if>
<jsp:include page="/views/web/footer/footer.jsp" />