<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row page-titles mx-0">
	<div class="col p-md-0">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="./admin-Supplier">Supplier</a></li>
			<li class="breadcrumb-item active"><a href="./admin-home">Home</a></li>
		</ol>
	</div>
</div>

<div class="container-fluid">
	<!--END Admin & Seller -->
	<div class="row">
		<div class="col-12">
			<h4 class="d-inline">Table of Supplier</h4>
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
					<form action="./admin-Supplier" id="deleteForm" method="post">
						<button type="button" class="btn btn-light deleteButton"
							id="hiddenDiv" onclick="deleteSelected('./admin-Supplier')">
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
										<th>Supplier Name</th>
										<th>Products Supplied</th>
										<th>tt</th>
										<!-- số saản phẩm nhà cc đã cc -->
										<th>PaymentTerms</th>
										<th class="th_ul_user-status" data-content-before="Address"
											style="height: 46px; width: 178px;"></th>
									</tr>
								</thead>
								<tbody>
									<c:set var="b" value="0" />

									<c:forEach var="supplier" items="${listS}">
										<c:set var="b" value="${b+1 }" />
										<tr class="tr_listuser" style="height: 59px;">
											<td class="td_userma">
												<div
													class="d-flex justify-content-center align-items-center checkbox_div">
													<input type="checkbox" value="${supplier.supplierID }"
														class="checkboxItem" name="selectedIds">
												</div>
											</td>
											<td>${b}</td>
											<td>${empty supplier.supplierName ? 'Empty' : supplier.supplierName}</td>
											<td>${empty supplier.productsSupplied ? 'Empty' : supplier.productsSupplied}</td>
											<c:set var="sp" value="0" />
											<c:forEach var="product" items="${listP}">

												<c:choose>
													<c:when
														test="${supplier.supplierID == product.supplierID }">
														<c:set var="sp" value="${sp+1 }" />
													</c:when>
												</c:choose>
											</c:forEach>
											<td>${sp}</td>
											<td>${empty supplier.paymentTerms ? '0' : supplier.paymentTerms}</td>
											<td class="td_ul_user-status">${empty supplier.address ? '0' : supplier.address}</td>
											<td class="td_ul_user">
												<ul style="margin-bottom: 0;">
													<li>
														<form action="./admin-Supplier" method="post"
															id="editForm">
															<input type="hidden" name="action" value="delete">
															<input type="hidden" name="supplierID"
																value="${supplier.supplierID}"> <input
																type="hidden" name="sp" value="${sp}">
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
														<form action="./admin-Supplier" method="post"
															id="deleteForm">
															<input type="hidden" name="action" value="edit">
															<input type="hidden" name="supplierID"
																value="${supplier.supplierID}">
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
<div class="div_background"
	${empty disabledsup ?'':'style="display:flex;' } tabindex="-1">
	<div class="s-a show-input_  visible " style="max-height: 465px;">
		<div class="s-a-close">
			<div class="s-a-close_div-i">
				<c:choose>
					<c:when test="${not empty disabledsup }">
						<a href="./admin-Supplier"> <i class="icon-close"></i>
						</a>
					</c:when>
					<c:otherwise>
						<i class="icon-close"></i>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<form action="./admin-Supplier" method="post">
			<div class="pr-4 pl-4">
				<div class="row">
					<div class="col-6">
						<label class="col-form-label s-a-label" for="suppliername:">Supplier
							Name: </label>
						<fieldset>
							<input type="text" class="form-control" name="supplierName"
								value="${supplier.supplierName}" required>
							<div class="sa-input-error"></div>
						</fieldset>
					</div>
					<div class="col-6">
						<label class="col-form-label s-a-label" for="productsupplied">Product
							Supplied </label>
						<fieldset>
							<input type="text" class="form-control" name="productsSupplied"
								value="${supplier.productsSupplied}" required>
							<div class="sa-input-error"></div>
						</fieldset>

					</div>
				</div>
				<div class="row">
					<div class="col-6">
						<label class="col-form-label s-a-label" for="paymentTerms">Payment
							Terms </label>
						<fieldset>
							<input type="text" class="form-control" name="paymentTerms"
								value="${supplier.paymentTerms}" required>
							<div class="sa-input-error"></div>
						</fieldset>
					</div>
				</div>
				<div class="row">
					<label for="exampleFormControlTextarea1" class="form-label">Address
					</label>
					<textarea class="form-control" id="exampleFormControlTextarea1"
						rows="3" name="address">${supplier.address}</textarea>
				</div>
				<div>
					<c:choose>
						<c:when test="${not empty disabledsup }">
							<input type="hidden" name="action" value="update">
							<input type="hidden" name="updatesupplier"
								value="${supplier.supplierID }">
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