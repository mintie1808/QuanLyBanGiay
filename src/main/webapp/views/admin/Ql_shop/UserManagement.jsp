<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row page-titles mx-0">
	<div class="col p-md-0">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="./admin-home?page=user_management">User Management</a></li>
			<li class="breadcrumb-item active"><a href="./admin-home">Home</a></li>
		</ol>
	</div>
</div>

<div class="container-fluid">
	<div class="row">
		<div class="col-12">
			<h4 class="d-inline">Admin & Seller</h4>
			<p class="text-muted"></p>
		</div>
		<c:forEach var="seller" items="${listUser}">
			<c:if test="${seller.role == 1 || seller.role == 2 }">
				<div class="col-lg-3 col-sm-6">
					<div class="card">
						<div class="card-body">
							<div class="text-center">

								<img
									src="img/${empty seller.img ? 'user/Avatar_trang.jpg' : seller.img}"
									class="rounded-circle" alt=""
									style="width: 100px; height: 100px;">

								<h5 class="mt-3 mb-1" _msttexthash="108524" _msthash="163">${seller.fullname}
								</h5>

								<c:choose>
									<c:when test="${seller.role==2 }">
										<p class="m-0" _msttexthash="2540746" _msthash="164">Sales
											Man</p>
									</c:when>
									<c:otherwise>
										<p class="m-0" _msttexthash="2540746" _msthash="164">Senior
											Manager</p>
									</c:otherwise>
								</c:choose>

								<!-- <a href="javascript:void()" class="btn btn-sm btn-warning">Send Message</a> -->
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</c:forEach>

	</div>
	<!--END Admin & Seller -->
	<div class="row">
		<div class="col-12">
			<h4 class="d-inline">Customers</h4>
			<p class="text-muted"></p>
		</div>
		<div class="col-lg-12">

			<div class="card">
				<div class="card-body">
					<div class="active-member">
						<div class="table-responsive">
							<table class="table table-xs mb-0">
								<thead>
									<tr>
										<th>Customers</th>
										<th>Fullname</th>
										<th>Email</th>
										<th>Address</th>
										<th>Phone</th>
										<th>status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="user" items="${listUser}">
										<c:if test="${user.role != 1 && user.role != 2}">
											<a href="${user.id }"> </a>
											<tr>
												<td><a href="${user.id }"> <img
														src="img/${empty user.img ? 'user/Avatar_trang.jpg' : user.img}"
														class="rounded-circle mr-3" alt=""> ${user.username}
												</a></td>
												<td>${empty user.fullname ? 'Empty' : user.fullname}</td>
												<td><span>${user.email}</span></td>
												<td>${empty user.address ? 'Empty' : user.address}</td>
												<td><c:choose>
														<c:when test="${user.phone!=0 }">
														0${user.phone }
														</c:when>
														<c:otherwise>
														Empty
														</c:otherwise>
													</c:choose></td>
												<td><c:choose>
														<c:when test="${user.status == 1}">
															<i class="fa fa-circle-o text-success mr-2"></i> active
												        </c:when>
														<c:when test="${user.status == 2}">
															<i class="fa fa-circle-o text-danger mr-2"></i> inactive
												        </c:when>
														<c:otherwise>
															<i class="fa fa-circle-o text-warning mr-2"></i> locked
												        </c:otherwise>
													</c:choose></td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END  row_Customers-->
</div>