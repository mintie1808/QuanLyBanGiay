<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-9  flex-grow-1 container bg-white shadow-sm rounded"
	style="min-width: 300px;">
	<div class="d-flex flex-column mx-4 div_edit_text">
		<div class="_title-ep">
			<h1 class="h1_title-ep">my profile</h1>
			<div class="div_title-ep">Manage and protect your account</div>
		</div>
		<form action="./user" method="post" enctype="multipart/form-data">
			<div class="pt-4 d-flex flex-column flex-sm-row">

				<div class="pr-sm-4 form_ep" style="">

					<div class="form-group row">
						<label class="col-lg-4 col-form-label text-lg-right "
							for="val-username">Username </label>
						<div class="col-lg-6">
							<input type="text" class="form-control i_ep" id="val-username"
								value="${USER.username }" name="val-username"
								placeholder="Enter a username..">
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
							<input type="text" class="form-control i_ep" id="val-fullname"
								value="${USER.fullname }" name="val-fullname"
								placeholder="Enter a  fullname..">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-lg-4 col-form-label text-lg-right"
							for="val-phoneus">Phone </label>
						<div class="col-lg-6">
							<input type="text" class="form-control i_ep" id="val-phoneus"
								value="0${USER.phone }" name="val-phoneus"
								placeholder="212-999-0000">
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
							<input type="file" id="fileInput" name="fileInput" class="d-none">
							<button type="button" class="_btn-ep sl-img-ep-btn">Select
								Image</button>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-lg-8 ml-auto">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
		</form>

	</div>
</div>