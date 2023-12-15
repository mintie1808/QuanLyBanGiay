<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row page-titles mx-0">
	<div class="col p-md-0">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="./admin-home?page=user_management">Edit</a></li>
			<li class="breadcrumb-item active"><a href="./admin-home">Home</a></li>
		</ol>
	</div>
</div>
<div class="container-fluid">
<%-- 
	<div class="row">
		<div class="col-10">
			<h4 class="d-inline">Product</h4>
			<p class="text-muted"></p>
		</div>
		<!-- Add Product Form -->
		<div class="col-lg-10">
			<div class="card">
				<div class="card-body">
					<div class="text-center">
						<form action="./admin-product" method="post">
							<c:choose>
						<c:when test="${disabled == 'disabled' }"></c:when>
						<c:otherwise test="${product == 'product' }"></c:otherwise></c:choose>

							<div class="row">
								<!-- 3 ô bên trái -->
								<div class="col-md-4">
									<div class="form-group">
										<label for="productName"><b>Product Name:</b></label> <input
											type="text" class="form-control" name="productName"
											value="${product.productName}" required>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label for="productPrice"><b>Product Price:</b></label> <input
											type="number" class="form-control" name="productPrice"
											value="${product.price}" required>
									</div>
								</div>

																<div class="col-md-2">
									<div class="form-group">
										<label for="seotitle"><b>SEO Title:</b></label> <input
											type="text" class="form-control" name="seotitle"
											value="${product.seotitle}" required>
									</div>
								</div>
								<!-- Kết thúc 3 ô bên trái -->

								<!-- 3 ô bên phải -->
								<div class="col-md-2">
									<div class="form-group">
										<label for="color"><b>Color:</b></label> <input type="text"
											class="form-control" name="color" value="${product.color}"
											required>
									</div>
								</div>

								<div class="col-md-2">
									<div class="form-group">
										<label for="category"><b>Category:</b></label> <select
											class="form-control" name="category">
											<c:forEach var="cate" items="${listC}">
												<option value="${cate.category_id }"
													${product.category_id == cate.category_id ? 'selected' : ''}>${cate.category_name }</option>
											</c:forEach>
										</select>

									</div>
								</div>

								<div class="col-md-2">
									<div class="form-group">
										<label for="supplier"><b>Supplier:</b></label> <select
											class="form-control" name="supplier">
											<c:forEach var="sup" items="${listS}">
												<option value="${sup.supplierID }"
													${product.supplierID == sup.supplierID ? 'selected' : ''}>${sup.supplierName }</option>
											</c:forEach>
										</select>

									</div>
								</div>


								<div class="col-md-2">
									<div class="form-group">
										<label for="amount"><b>Amount:</b></label> <input
											type="number" class="form-control" name="amount"
											value="${product.amount}" required>
									</div>
								</div>
								<c:if test="${not empty product.productID }">
									<div class="col-md-2">
										<div class="form-group">
											<label for="amount"><b>Id:</b></label> <input type="number"
												class="form-control" name="productId"
												value="${product.productID}" readonly>
										</div>
									</div>
								</c:if>


								<!-- Kết thúc 3 ô bên phải -->
							</div>

							<div class="form-row">

								<div class="col-md-8">
									<div class="form-group">
										<label for="description"><b>Description:</b></label>
										<textarea class="form-control" name="description">${product.description}</textarea>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label for="img"><b>Choose Image:</b></label> <input
											type="file" class="form-control" name="img"
											value="/${product.img}">
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-body">
									<div class="summernote">
										<h3>Default Summernote</h3>
									</div>
								</div>
							</div>

							<div class="form-row">
								<div class="col-md-6">
									<c:choose>
										<c:when test="${disabled == 'disabled' }">
											<button type="submit" class="btn btn-success" name="action"
												value="add" disabled>Add Product</button>
										</c:when>
										<c:otherwise>
											<button type="submit" class="btn btn-success" name="action"
												value="add">Add Product</button>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="col-md-6">
									<button type="submit" class="btn btn-primary" name="action"
										value="update">Update Product</button>
								</div>
							</div>
						</form>


					</div>
				</div>
			</div>
		</div>

	</div> --%>
	<!--END Admin & Seller -->
	<div class="row">
		<div class="col-12">
			<h4 class="d-inline">Table of Product</h4>
			<p class="text-muted"></p>
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
					<form action="./admin-product" id="deleteForm" method="post">
						<button type="button" class="btn btn-light deleteButton"
							id="hiddenDiv" onclick="deleteSelected('./admin-product')">
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
										<th>No.</th>
										<th>Name</th>
										<th>Price</th>
										<th>Seotitle</th>
										<th>Color</th>
										<th>Description</th>
										<th>Amount</th>
										<th class="th_ul_user-status" data-content-before="Sold"
											style="width: 107px;"></th>

									</tr>
								</thead>
								<tbody>
									<c:set var="c" value="0" />
									<c:forEach var="product" items="${listP}">
										<c:set var="c" value="${c+1 }" />
										<tr class="tr_listuser">
											<td class="td_userma">
												<div
													class="d-flex justify-content-center align-items-center checkbox_div">
													<input type="checkbox" value="${product.productID }"
														class="checkboxItem" name="selectedIds">
												</div>
											</td>
											<td>${c}</td>
											<td><img
												src="img/product/${empty product.img ? 'product-1.jpg' : product.img}"
												class="rounded-circle mr-3" alt="">
												${product.productName} </a></td>
											<td>${empty product.price ? 'Empty' : product.price}</td>
											<td><span>${product.seotitle}</span></td>
											<td>${empty product.color ? 'Empty' : product.color}</td>
											<td>${empty product.description ? 'Empty' : product.description}</td>
											<td >${empty product.amount ? '0' : product.amount}</td>
											<td class="td_ul_user-status">${empty product.sold ? '0' : product.sold}</td>
											<td class="td_ul_user">
												<ul style="margin-bottom: 0;">
													<li>
														<form action="./admin-product" method="post"
															id="deleteForm">
															<input type="hidden" name="action" value="delete">
															<input type="hidden" name="productId"
																value="${product.productID}">
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
														<form action="./admin-product" method="post" id="editForm">
															<input type="hidden" name="action" value="edit">
															<input type="hidden" name="productId"
																value="${product.productID}">
															<button type="submit" class="" 
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
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end show product -->
</div>
<div class="div_background" ${empty disabled ?'':'style="display:flex;' }
	tabindex="-1">
	<div class="s-a show-input_  visible ">
		<div class="s-a-close">
			<div class="s-a-close_div-i">
				<c:choose>
					<c:when test="${not empty disabled }">
						<a href="./admin-product"> <i class="icon-close"></i>
						</a>
					</c:when>
					<c:otherwise>
						<i class="icon-close"></i>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<form action="./admin-product" method="post"
			enctype="multipart/form-data">
			<div class="mx-auto text-center" style="padding-bottom: 10px;">
				<div class="mx-auto text-center">
					<div class="img_sl-img-ep">
						<img
							src="img/product/${ empty product.img ? 'icon_website.png' : product.img}"
							alt="Avatar" class="img-fluid sl_img-ep_avatar-img"
							id="avatarImage">
					</div>
					<div class="text-sl_img-ep" id="fileNameDisplay">No file
						selected</div>
					<div class="mt-text-sl_img-ep">
						<input type="file" id="fileInput" name="fileInput" class="d-none"
							value="${product.img }">
						<button type="button" class="_btn-ep sl_btn">Select Image</button>
					</div>
				</div>
			</div>
			<hr class="my-2">
			<div class="pr-4 pl-4">
				<div class="row">
					<div class="col-6">
						<label class="col-form-label s-a-label" for="productname">Product
							Name </label>
						<fieldset>
							<input type="text" class="form-control" name="productName"
								value="${product.productName}" required>
							<div class="sa-input-error"></div>
						</fieldset>
					</div>
					<div class="col-6">

						<label class="col-form-label s-a-label" for="val-fullname">Product
							Price </label>
						<fieldset>
							<input type="number" class="productPrice" name="productPrice"
								value="${product.price}" required>
							<div class="sa-input-error"></div>
						</fieldset>

					</div>
				</div>
				<div class="row">
					<div class="col-6">

						<label class="col-form-label s-a-label" for="color">Color
						</label>
						<fieldset>
							<input type="text" class="form-control" name="color"
								value="${product.color}" required>
							<div class="sa-input-error"></div>
						</fieldset>
					</div>
					<div class="col-6">
						<label class="col-form-label s-a-label" for="amount">Amount
						</label>
						<fieldset>
							<input type="number" class="form-control" name="amount"
								value="${product.amount}" required>
							<div class="sa-input-error"></div>
						</fieldset>
					</div>

				</div>
				<div class="row">
					<div class="form-group col-6">
						<label class="col-form-label s-a-label" for="Category">Supplier
						</label> <select class="form-control form-select" name="supplier">
							<c:forEach var="sup" items="${listS}">
								<option value="${sup.supplierID }"
									${product.supplierID == sup.supplierID ? 'selected' : ''}>${sup.supplierName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group col-6" style="margin-bottom: 0;">
						<label class="col-form-label s-a-label" for="Category">Category
						</label> <select class="form-control form-select" name="category">
							<c:forEach var="cate" items="${listC}">
								<option value="${cate.category_id }"
									${product.category_id == cate.category_id ? 'selected' : ''}>${cate.category_name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row">
					<label for="exampleFormControlTextarea1" class="form-label">Description
					</label>
					<textarea class="form-control" name="description"  id="exampleFormControlTextarea1"
						rows="3">${product.description }</textarea>
				</div>
				<div class="row">
					<label for="exampleFormControlTextarea1" class="form-label">Detail
					</label>
					<textarea class="form-control" name="detail" id="exampleFormControlTextarea1"
						rows="3">${product.detail }</textarea>
				</div>
			</div>

			<div>
				<c:choose>
					<c:when test="${not empty disabled }">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="updateproduct" value="${product.productID }">
						<button type="submit" class="_btn-ep sl-img-ep-btn">Update</button>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="action" value="add">
						<button type="submit" class="_btn-ep sl-img-ep-btn">Submit</button>
					</c:otherwise>
				</c:choose>

			</div>
	</div>
	</form>
</div>
</div>
