<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<div class="card">
				<div class="card-body">
					<div class="active-member">
						<div class="table-responsive">
							<table class="table table-xs mb-0">
								<thead>
									<tr>
										<th>Users</th>
										<th>Fullname</th>
										<th>Email</th>
										<th>Address</th>
										<th>Phone</th>
										<th>status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="user" items="${listUser}">
										<tr>
											<td><img src="img/${user.img }"
												class=" rounded-circle mr-3" alt="">${user.username }</td>
											<td>${user.fullname }</td>
											<td><span>${user.email }</span></td>
											<td>${user.address }</td>
											<td>0${user.phone }</td>
											<c:choose>
													<c:when test="${user.status == 1}">
														<td><i class="fa fa-circle-o text-success  mr-2"></i> active</td>
													</c:when>
												<c:otherwise>
														<td><i class="fa fa-circle-o text-danger  mr-2"></i> inactive</td>
												</c:otherwise>
											</c:choose> 
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>