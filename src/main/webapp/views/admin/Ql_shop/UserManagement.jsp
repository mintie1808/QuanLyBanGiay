<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row page-titles mx-0">
	<div class="col p-md-0">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="./admin-user">User
					Management</a></li>
			<li class="breadcrumb-item active"><a href="./admin-home">Home</a></li>
		</ol>
	</div>
</div>

<div class="container-fluid">
	<%-- <div class="row">
		<div class="col-12">
			<h4 class="d-flex">
				<span>Admin</span>
				<span translate="no" class="mx-2">&</span>
				<span>Seller</span>
			</h4>
			<p class="text-muted"></p>
		</div>
		<c:forEach var="seller" items="${listUser}">
			<c:if test="${seller.role == 1 || seller.role == 2 }">
				<div class="col-lg-3 col-sm-6">
					<div class="card">
						<div class="card-body">
							<div class="text-center">

								<img
									src="img/user/${empty seller.img ? 'user/Avatar_trang.jpg' : seller.img}"
									class="rounded-circle" alt=""
									style="width: 100px; height: 100px;">

								<h5 class="mt-3 mb-1">${seller.fullname}</h5>

								<c:choose>
									<c:when test="${seller.role==2 }">
										<p class="m-0">Sales Man</p>
									</c:when>
									<c:otherwise>
										<p class="m-0">Senior Manager</p>
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
	<!--END Admin & Seller --> --%>
	<div class="row">
		<div class="col-12">
			<h4 class="d-inline">Customers</h4>
			<p class="text-muted">${itemcounttotal}
				<span> results</span>
			</p>
		</div>
		<div class="col-lg-12">
			<div class="toolbar" role="toolbar">
				<div class="btn-group">
					<div class="">
						<div class="btn btn-light">
							<input type="checkbox" value="" id="selectAll">
						</div>
					</div>
				</div>
				<div class="btn-group m-b-20">
					<button type="button" class="btn btn-light hiddenbtn">
						<i class="fa fa fa-plus"></i>
					</button>
					<form action="./admin-user" id="deleteForm" method="post">
						<button type="button" class="btn btn-light deleteButton"
							id="hiddenDiv" onclick="deleteSelected('./admin-user')">
							<i class="fa fa-trash"></i>
						</button>
					</form>
				</div>
			</div>
			<div class="card">
				<div class="card-body" style="padding: 1.88rem 1px;">
					<div class="active-member">
						<div class="table-responsive div_table">
							<table class="table table-xs mb-0">
								<thead>
									<tr>
										<th></th>
										<th>Customers</th>
										<th>Fullname</th>
										<th>Email</th>
										<th>Address</th>
										<th>Phone</th>
										<th class="th_ul_user-status" data-content-before="status"
											style="height: 45px; width: 100px;"></th>
									</tr>
								</thead>
								<tbody>

									<c:forEach var="user" items="${listUser}">
										<c:if test="${user.role != 1 && user.role != 2}">
											<%-- 	<a href="${user.id }"> </a> --%>
											<tr class="tr_listuser">
												<td class="td_userma">
													<div
														class="d-flex justify-content-center align-items-center checkbox_div">
														<input type="checkbox" value="${user.id }"
															class="checkboxItem" name="selectedIds">
													</div>
												</td>
												<td translate="no"><a href="${user.id }"> <img
														src="img/user/${empty user.img ? 'Avatar_trang.jpg' : user.img}"
														class="rounded-circle mr-3" style="height: 35px;" alt="">
														${user.username}
												</a></td>
												<td translate="no">${user.fullname}</td>
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
												<td class="td_ul_user-status"><c:choose>
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
												<td class="td_ul_user">
													<ul style="margin-bottom: 0;">
														<li>
															<form action="./admin-user" method="post">
																<input type="hidden" name="action" value="delete">
																<input type="hidden" name="userId" value="${user.id}">
																<button type="submit" class="" name="bttn_edit"
																	style="border: 0; cursor: pointer;">
																	<div class="div_i_hover">
																		<i class="fa fa-trash"></i>

																	</div>
																</button>
															</form>
														</li>
													</ul>
												</td>
												<td class="td_ul_user" style="margin-left: 38px">
													<ul style="margin-bottom: 0;">
														<li>
															<form action="./admin-user" method="post">
																<input type="hidden" name="edit" value="delete">
																<input type="hidden" name="userIdToDelete"
																	value="${user.id}">
																<button type="submit" class="" name="bttn_delete"
																	style="border: 0; cursor: pointer;">
																	<div class="div_i_hover">
																		<i class="fa fa-edit"></i>
																	</div>
																</button>
															</form>
														</li>
													</ul>
												</td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<%-- <div class="bootstrap-pagination">
					<c:if test="${totalpage > 1}">
						<nav>
							<ul class="pagination justify-content-center">
								<c:if test="${itemcount != 1}">
									<li class="page-item"><a class="page-link"
										href="./admin-user?txtsearch=${txtsearch}&pages=1"
										aria-label="First"> <span aria-hidden="true">&laquo;&laquo;</span>
									</a></li>
									<li class="page-item"><a class="page-link"
										href="./admin-user?txtsearch=${txtsearch}&pages=${itemcount - 1}"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
									</a></li>
								</c:if>
								<c:choose>
									<c:when test="${itemcount > 2}">
										<li class="page-item"><a class="page-link"
											href="./admin-user?txtsearch=${txtsearch}&pages=${itemcount - 1}">
												${itemcount - 1} </a></li>
									</c:when>
									<c:otherwise>
										<c:if test="${itemcount > 1}">
											<li class="page-item"><a class="page-link"
												href="./admin-user?txtsearch=${txtsearch}&pages=${itemcount - 1}">
													${itemcount - 1} </a></li>
										</c:if>
									</c:otherwise>
								</c:choose>
								<li class="page-item active"><span class="page-link">
										${itemcount} <span class="sr-only">(current)</span>
								</span></li>
								<c:choose>
									<c:when test="${itemcount < totalpage - 1}">
										<li class="page-item"><a class="page-link"
											href="./admin-user?txtsearch=${txtsearch}&pages=${itemcount + 1}">
												${itemcount + 1} </a></li>
									</c:when>
									<c:otherwise>
										<c:if test="${itemcount < totalpage}">
											<li class="page-item"><a class="page-link"
												href="./admin-user?txtsearch=${txtsearch}&pages=${itemcount + 1}">
													${itemcount + 1} </a></li>
										</c:if>
									</c:otherwise>
								</c:choose>
								<c:if test="${itemcount < totalpage}">
									<li class="page-item"><a class="page-link"
										href="./admin-user?txtsearch=${txtsearch}&pages=${itemcount + 1}"
										aria-label="Next"> <span aria-hidden="true">&raquo;</span>
									</a></li>
									<li class="page-item"><a class="page-link"
										href="./admin-user?txtsearch=${txtsearch}&pages=${totalpage}"
										aria-label="Last"> <span aria-hidden="true">&raquo;&raquo;</span>
									</a></li>
								</c:if>

							</ul>
						</nav>
					</c:if>
				</div> --%>
			</div>

		</div>

	</div>
</div>
<style>
.div_background {
	opacity: 1.09;
	z-index: 99999;
	display: flex;
	justify-content: center;
	position: absolute;
	min-width: 434px;
}

.s-a {
	display: block;
	position: relative;
	margin-top: 12px;
	top: 5px;
	overflow: auto;
	max-height: 720px;
	padding: 0 17px 17px 17px;
	width: 800px;
	left: 0;
	margin-left: 0;
}

.s-a-close {
	position: sticky;
	top: 0;
	z-index: 2;
	background: #fff;
	text-align: right;
	z-index: 2;
}

.s-a-close_div-i {
	font-size: 27px;
	display: flex;
	justify-content: right;
	align-items: center;
	padding: 10px;
}

.s-a-close_div-i i {
	height: 26px;
	border-radius: 50%;
}

.s-a-close_div-i i:hover {
	background: red;
	cursor: pointer;
	color: #fff;
}

.s-a-label {
	display: flex;
	justify-content: left;
}

.form-select {
	display: block;
	width: 100%;
	padding: .375rem 2.25rem .375rem .75rem;
	-moz-padding-start: calc(0.75rem - 3px);
	font-size: 1rem;
	font-weight: 400;
	line-height: 1.5;
	color: #212529;
	background-color: #fff;
	background-repeat: no-repeat;
	background-position: right .75rem center;
	background-size: 16px 12px;
	border: 1px solid #ced4da;
	border-radius: .25rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
	margin-top: 10px;
	margin-bottom: 17px;
}

.form-control:focus {
	border-color: #d7d7d7;
}

.sweet-alert button.sl_btn {
	font-size: 15px;
	color: #8CD4F5;
	background-color: transparent;
	background-image: none;
	border-color: #8CD4F5;
	border: 1px solid #8CD4F5;
}

.sweet-alert button.sl_btn:hover {
	background: #8CD4F5;
	color: #fff;
	border-color: #8CD4F5;
}

.sweet-alert button:hover {
	background-color: #96DEFD;
}
</style>
<div class="sweet-overlay div_background" tabindex="-1" style="">
	<div class="sweet-alert show-input showSweetAlert visible s-a" style="">
		<div class="s-a-close" style="">
			<div class="s-a-close_div-i" style="">
				<i class="icon-close"></i>
			</div>
		</div>
		<form action="./admin-user" method="post" enctype="multipart/form-data">
			<div class="mx-auto text-center" style="padding-bottom: 10px;">
				<div class="mx-auto text-center">
					<div class="img_sl-img-ep">
						<img src="img/user/Avatar_trang.jpg" alt="Avatar"
							class="img-fluid sl_img-ep_avatar-img" id="avatarImage">
					</div>
					<div class="text-sl_img-ep" id="fileNameDisplay">No file
						selected</div>
					<div class="mt-text-sl_img-ep">
						<input type="file" id="fileInput" name="fileInput" class="d-none">
						<button type="button" class="_btn-ep sl_btn">Select Image</button>
					</div>
				</div>
			</div>
			<hr class="my-2">
			<div class="pr-4 pl-4">
				<div class="row">
					<div class="col-6">
						<label class="col-form-label s-a-label" for="val-username">Username
						</label>
						<fieldset>
							<input type="text" tabindex="3" placeholder="Username"
								autocomplete="off" name="username">
							<div class="sa-input-error"></div>
						</fieldset>
					</div>
					<div class="col-6">
						<label class="col-form-label s-a-label" for="val-fullname">Fullname
						</label>
						<fieldset>

							<input type="text" tabindex="3" name="fullname" value=""
								placeholder="Fullname" name="fullname" autocomplete="off">
							<div class="sa-input-error"></div>
						</fieldset>

					</div>
				</div>
				<div class="row">
					<div class="col-6">
						<label class="col-form-label s-a-label" for="val-email">Email
						</label>
						<fieldset>
							<input type="text" tabindex="3" placeholder="Email" name="email"
								autocomplete="off">
							<div class="sa-input-error"></div>
						</fieldset>
					</div>
					<div class="col-6">
						<label class="col-form-label s-a-label" for="val-address">Address
						</label>
						<fieldset>
							<input type="text" tabindex="3" placeholder="Address"
								name="address" autocomplete="off">
							<div class="sa-input-error"></div>
						</fieldset>

					</div>
				</div>
				<div class="row">
					<div class="form-group col-6" style="margin-bottom: 0;">
						<label class="col-form-label s-a-label" for="val-role">Role
						</label> <select class="form-control form-select" name="select_role">
							<option selected disabled>Select Role</option>
							<c:forEach var="role" items="${listrole}">
								<option value="${role.roleID }">${role.name}</option>
							</c:forEach>

						</select>
					</div>
					<div class="col-6">
						<label class="col-form-label s-a-label" for="val-phone">Phone
						</label>
						<fieldset>
							<input type="text" tabindex="3" placeholder="Phone" name="phone"
								autocomplete="off">
							<div class="sa-input-error"></div>
						</fieldset>

					</div>
				</div>
				<input type="hidden" name="action" value="add">
				<button type="submit" class="_btn-ep sl-img-ep-btn">Submit</button>
			</div>
		</form>
	</div>
</div>
<!-- END  row_Customers-->
