<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section class="ep bg-light">
	<div class="container">
		<div class="row">
			<div class="col-md-3 col-xl-2" style="font-size: .875rem;">
				<div class="d-flex py-4">
					<a href="./user" class="a_ep displayDiv"> <c:choose>
							<c:when test="${not empty USER.img }">
								<img src="img/user/${USER.img }" alt="Avatar"
									class="img-fluid img_ep" />
							</c:when>
							<c:otherwise>
								<img src="img/user/Avatar_trang.jpg" alt="Avatar"
									class="img-fluid img_ep" />
							</c:otherwise>
						</c:choose>
					</a>
					<div
						class="d-flex flex-column justify-content-center pl-2 overflow-hidden">
						<div class="overflow-hidden font-weight-bold mb-2 text-dark"
							id="truncateDiv" style="min-height: 1rem;">${USER.fullname }</div>
						<a href="./user"
							class="a_ep text-muted text-capitalize text-decoration-none displayDiv">
							<i class="icon_pencil-edit" style="margin-right: 5px;"></i> <span>Edit
								Profile</span>
						</a>
					</div>
				</div>
				<hr class="my-2">
				<div class="nav_silde_ep">
					<ul class="nav navbar-nav" id="navList">
						<li class="active" data-target="myAccount">My Account</li>
						<li data-target="changePassword">Change Password</li>
						<li data-target="reviewOrder">Review Order</li>
					</ul>
				</div>
			</div>
			<div
				class="col-md-9 flex-grow-1 container bg-white shadow-sm rounded div_max">
				<div class="d-flex flex-column mx-4 div_edit_text">
					<div id="profileDiv">
						<div class="_title-ep">
							<h1 class="h1_title-ep">my profile</h1>
							<div class="div_title-ep">Manage and protect your account</div>
						</div>
						<form action="./user" method="post" enctype="multipart/form-data"
							name="form1">
							<div class="pt-4 d-flex flex-column flex-sm-row">
								<div class="pr-sm-4 form_ep">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label text-lg-right "
											for="val-username">Username </label>
										<div class="col-lg-6">
											<input type="text" class="form-control i_ep"
												id="val-username" value="${USER.username }"
												name="val-username" placeholder="Enter a username..">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label text-lg-right"
											for="val-email">Email </label>
										<div class="col-lg-6 mb-3">
											<input type="text" class="form-control i_ep" id="val-email"
												value="${USER.email }" name="val-email"
												placeholder="Your valid email..">
										</div>

									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label text-lg-right"
											for="val-fullname">Fullname</label>
										<div class="col-lg-6">
											<input type="text" class="form-control i_ep"
												id="val-fullname" value="${USER.fullname }"
												name="val-fullname" placeholder="Enter a  fullname..">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label text-lg-right"
											for="val-phoneus">Phone </label>
										<div class="col-lg-6">
											<c:choose>
												<c:when test="${USER.phone==0 }">
													<input type="text" class="form-control i_ep"
														id="val-phoneus" name="val-phoneus"
														placeholder="Enter phone..">
												</c:when>
												<c:otherwise>
													<input type="text" class="form-control i_ep"
														id="val-phoneus" value="0${USER.phone }"
														name="val-phoneus" placeholder="">
												</c:otherwise>
											</c:choose>

										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label text-lg-right"
											for="val-address">Address </label>
										<div class="col-lg-6">
											<input type="text" class="form-control i_ep" id="val-address"
												value="${USER.address }" name="val-address"
												placeholder="Enter a address...">
										</div>
									</div>

								</div>
								<div class="d-flex justify-content-center  mt-3 mt-sm-0"
									style="padding-bottom: 100px;">
									<div class="d-flex align-items-center sl_img-ep flex-column">
										<div class="img_sl-img-ep">
											<c:choose>
												<c:when test="${not empty USER.img }">
													<img src="img/user/${USER.img }" alt="Avatar"
														class="img-fluid sl_img-ep_avatar-img" id="avatarImage" />
												</c:when>
												<c:otherwise>
													<img src="img/user/Avatar_trang.jpg" alt="Avatar"
														class="img-fluid sl_img-ep_avatar-img" id="avatarImage" />
												</c:otherwise>
											</c:choose>
										</div>
										<div class="text-sl_img-ep" id="fileNameDisplay">No file
											selected</div>
										<div class="mt-text-sl_img-ep">
											<input type="file" id="fileInput" name="fileInput"
												class="d-none">
											<button type="button" class="_btn-ep sl-img-ep-btn">Select
												Image</button>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-8 ml-auto">
									<input type="hidden" name="name" value="form1">
									<button type="submit" class="btn btn-primary" name="form1">Submit</button>
								</div>
							</div>
						</form>
					</div>
					<div id="changePassDiv" style="display: none;">
						<div class="_title-ep">
							<h1 class="h1_title-ep">Update your password</h1>
							<div class="div_title-ep">Enter your current password and a
								new password.</div>
						</div>
						<form action="./user" method="post" name="form2">
							<div class="pt-4 d-flex flex-column flex-sm-row">

								<div class="pr-sm-4 form_ep" style="">

									<div class="form-group row">
										<label class="col-lg-4 col-form-label text-lg-right "
											for="val-current-pass">Current Password</label>
										<div class="col-lg-6">
											<input type="password" class="form-control i_ep"
												value="${val_current_pass }" name="val-current-pass"
												id="current_password" autocomplete="off" required>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label text-lg-right"
											for="val-new-pass">New Password</label>
										<div class="col-lg-6 mb-3">
											<input type="password" class="form-control i_ep" value=""
												name="val-new-pass" autocomplete="off" required>
										</div>

									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label text-lg-right"
											for="val-confirm-new-password">Confirm New Password</label>
										<div class="col-lg-6">
											<input type="password" class="form-control i_ep" value=""
												name="val-confirm-new-password" autocomplete="off" required>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-8 ml-auto">
									<input type="hidden" name="name" value="form2">
									<button type="submit" class="btn btn-primary" name="form2">Submit</button>
								</div>
							</div>
						</form>
					</div>
					<div id="orderDiv" style="display: none;">
						<c:choose>
							<c:when test="${empty listOrder}">

								<h2 style="text-align: center;">Order not found</h2>
							</c:when>
							<c:otherwise>
								<!-- Page Title: Order Management -->
								<div class="_title-ep">
									<h1 class="h1_title-ep">Order View</h1>
									<div class="div_title-ep">View your orders here.</div>
								</div>


								<div class="Order_">
									<c:forEach items="${listOrder }" var="data">
										<div class="formatted-order-date">${data.orderDate }</div>
										<div class="basic-list-group" style="margin-bottom: 20px;">
											<a href="#"
												class="list-group-item list-group-item-action flex-column align-items-start">
												<div class="d-flex w-100 justify-content-between">
													<h5 class="mb-1"></h5>

													<!-- <small class="text-muted">3 days ago</small> -->
												</div> 

												<p class="mb-1">${data.totalAmount }</p> <small
												class="text-muted">${ data.status}</small>
											</a>
										</div>


									</c:forEach>
								</div>

							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>