<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row page-titles mx-0">
	<div class="col p-md-0">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="./admin-home?page=supplier_manager">Supplier</a></li>
			<li class="breadcrumb-item active"><a href="./admin-home">Home</a></li>
		</ol>
	</div>
</div>

<div class="container-fluid">
	<div class="row">
		<div class="col-10">
			<h4 class="d-inline">Supplier</h4>
			<p class="text-muted"></p>
		</div>
		<!-- Add Product Form -->
		<div class="col-lg-10">
			<div class="card">
				<div class="card-body">
					<div class="text-center">
						<form action="./admin-Supplier" method="post">
							<%-- <c:choose>
						<c:when test="${disabled == 'disabled' }"></c:when>
						<c:otherwise test="${product == 'product' }"></c:otherwise></c:choose> --%>

							<div class="row">
								<!-- 3 ô bên trái -->
								<div class="col-md-4">
									<div class="form-group">
										<label for="supplierName"><b>Supplier Name:</b></label> <input
											type="text" class="form-control" name="supplierName"
											value="${supplier.supplierName}" required>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label for="productSupplier"><b>Product Supplied:</b></label>
										<input type="text" class="form-control"
											name="productsSupplied" value="${supplier.productsSupplied}"
											required>
									</div>
								</div>

								<div class="col-md-2">
									<div class="form-group">
										<label for="paymentTerms"><b>Payment Terms:</b></label> <input
											type="text" class="form-control" name="paymentTerms"
											value="${supplier.paymentTerms}" required>
									</div>
								</div>
								<!-- Kết thúc 3 ô bên trái -->


							</div>

							<div class="form-row">

								<div class="col-md-8">
									<div class="form-group">
										<label for="address"><b>Address:</b></label>
										<textarea class="form-control" name="address">${supplier.address}</textarea>
									</div>
								</div>
								<c:if test="${not empty supplier.supplierID }">
									<div class="col-md-2">
										<div class="form-group">
											<label for="supplierID"><b>Supplier ID:</b></label> <input
												type="number" class="form-control" name="supplierID"
												value="${supplier.supplierID}" readonly>
										</div>
									</div>
								</c:if>
							</div>


							<div class="form-row">
								<div class="col-md-6">
									<c:choose>
										<c:when test="${disabled == 'disabled' }">
											<button type="submit" class="btn btn-success" name="action"
												value="add" disabled>Add Supplier</button>
										</c:when>
										<c:otherwise>
											<button type="submit" class="btn btn-success" name="action"
												value="add">Add Supplier</button>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="col-md-6">
									<button type="submit" class="btn btn-primary" name="action"
										value="update">Update Supplier</button>
								</div>
							</div>
						</form>


					</div>
				</div>
			</div>
		</div>

		<%--  <!-- Display Products -->
        <c:forEach var="product" items="${listP}">
            <div class="col-lg-3 col-sm-6">
                <div class="card">
                    <div class="card-body">
                        <div class="text-center">
                            <h5 class="mt-3 mb-1">${product.productName}</h5>
                            <p class="m-0">${product.price}</p>
                            <form action="ProductServlet" method="post">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="productId" value="${product.productID}">
                                <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach> --%>

	</div>
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
											style="height: 46px;width: 178px;"></th>
									</tr>
								</thead>
								<tbody>
									<c:set var="b" value="0" />

									<c:forEach var="supplier" items="${listS}">
										<c:set var="b" value="${b+1 }" />
										<tr class="tr_listuser" style="height: 59px;">
											<td class="td_userma" >
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
														<form action="./admin-Supplier" method="get"
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
														<form action="./admin-Supplier" method="get"
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
<!-- san pham theo supplier -->
<%-- <div class="card">
				<div class="card-body">
					<div class="active-member">
						<div class="table-responsive">
							<table class="table table-xs mb-0">
								<thead>
									<tr>
										<th>ID</th>
										<th>Name</th>
										<th>Price</th>
										<th>Edit</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="c" value="0" />
									<c:forEach var="product" items="${listP}">
										<c:choose>
										<c:when test="${supplier.supplierID == product.supplierID }">
										<c:set var="c" value="${c+1 }" />
										<tr>
											<td>${c}</td>
											<td><img
												src="img/shop/product/${empty product.img ? 'product-1.jpg' : product.img}"
												class="rounded-circle mr-3" alt="">
												${product.productName} </a></td>
											<td>${empty product.price ? 'Empty' : product.price}</td>
											
											<td>
												<div class="row">

													<form action="./productedit" method="post" id="editForm">
														<input type="hidden" name="action" value="edit"> <input
															type="hidden" name="productId"
															value="${product.productID}">
														<button type="submit" class="btn btn-sm btn-danger">Edit</button>
													</form>

													<div class="col mx-0.5">
														<form action="./productedit" method="post" id="deleteForm">
															<input type="hidden" name="action" value="delete">
															<input type="hidden" name="productId"
																value="${product.productID}">
															<button type="submit" class="btn btn-sm btn-danger">Delete</button>
														</form>
													</div>
												</div>



											</td>
										</tr>
										</c:when></c:choose>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div> --%>