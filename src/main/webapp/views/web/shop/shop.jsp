<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Breadcrumb Section Begin -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
	function redirectTo(url) {
		window.location.href = url;
	}
	$(document).ready(function() {
		$(".addToCartForm").submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');
			$.ajax({
				type : "POST",
				url : url,
				data : form.serialize(),
				success : function(data) {
					alert("Thêm vào giỏ hàng thành công");
					window.location.reload();// Hiển thị thông báo (có thể thay bằng cách khác nếu cần)
				}
			});
			return false;
		});
	});
	window.onload = function() {
		// Lấy giá trị từ localStorage nếu có
		var pminValue = localStorage.getItem('pminValue');
		var pmaxValue = localStorage.getItem('pmaxValue');
		// Gán giá trị vào input
		document.getElementById('pmin').value = pminValue || '';
		document.getElementById('pmax').value = pmaxValue || '';
		// Thêm sự kiện input để lưu giá trị vào localStorage khi thay đổi
		document.getElementById('pmin').addEventListener('input', function() {
			localStorage.setItem('pminValue', this.value);
		});
		document.getElementById('pmax').addEventListener('input', function() {
			localStorage.setItem('pmaxValue', this.value);
		});
	};
</script>
<section class="breadcrumb-option">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="breadcrumb__text">
					<h4>Shop</h4>
					<div class="breadcrumb__links">
						<a href="./trang-chu">Home</a> <span>Shop</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Breadcrumb Section End -->

<!-- Shop Section Begin -->
<section class="shop spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="shop__sidebar">
					<div class="shop__sidebar__search">
						<form action="./shop" method="get">
							<input type="text" name="textsearchName" autocomplete="off"
								placeholder="Search...">
							<button type="submit">
								<span class="icon_search"></span>
							</button>
						</form>
					</div>
					<div class="shop__sidebar__accordion">
						<div class="accordion" id="accordionExample">
							<div class="card">
								<div class="card-heading">
									<a data-toggle="collapse" data-target="#collapseOne">Categories</a>
								</div>
								<div id="collapseOne" class="collapse show"
									data-parent="#accordionExample">
									<div class="card-body">
										<div class="shop__sidebar__categories">
											<ul class="nice-scroll">
												<c:forEach items="${listC}" var="datac">
													<li><a href="./shop?cid=${datac.category_id}"
														style="${tag == datac.category_id ? 'color:red;' : ''}">${datac.category_name}</a></li>
												</c:forEach>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-heading">
									<a data-toggle="collapse" data-target="#collapseTwo">Branding</a>
								</div>
								<div id="collapseTwo" class="collapse show"
									data-parent="#accordionExample">
									<div class="card-body">
										<div class="shop__sidebar__brand">
											<ul>
												<c:forEach items="${listS}" var="datas">
													<li><a href="./shop?supid=${datas.supplierID}"
														style="${tags == datas.supplierID ? 'color:red;' : ''}">${datas.productsSupplied }</a></li>
												</c:forEach>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-heading">
									<a data-toggle="collapse" data-target="#collapseThree">Filter
										Price</a>
								</div>
								<div id="collapseThree" class="collapse show"
									data-parent="#accordionExample">
									<div class="card-body">
										<div class="shop__sidebar__price">
											<ul>
												<li><a href="./shop?pid=0+200000">0-200000</a></li>
												<li><a href="./shop?pid=200000+300000">200000 -
														300000</a></li>
												<li><a href="./shop?pid=300000+500000">300000 -
														500000</a></li>
												<li><a href="./shop?pid=500000+top">500000+</a></li>
												<li>
													<form action="./shop?prid=pricerange" method="post">
														<div style="display: flex;">
															<input type="number" id="pmin" name="pmin"
																autocomplete="off"
																style="width: 30%; border: 1px solid #222222;" class="">
															<span style="width: 5%; text-align: center;">-</span> <input
																type="number" id="pmax" name="pmax" autocomplete="off"
																style="width: 30%; border: 1px solid #222222;">
														</div>
														<button style="margin-top: 10px; background: #ffff;"
															type="submit">Filter</button>
													</form>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<!-- 							<div class="card">
								<div class="card-heading">
									<a data-toggle="collapse" data-target="#collapseFour">Size</a>
								</div>
								<div id="collapseFour" class="collapse show"
									data-parent="#accordionExample">
									<div class="card-body">
										<div class="shop__sidebar__size">
											<label for="xs">xs <input type="radio" id="xs">
											</label> <label for="sm">s <input type="radio" id="sm">
											</label> <label for="md">m <input type="radio" id="md">
											</label> <label for="xl">xl <input type="radio" id="xl">
											</label> <label for="2xl">2xl <input type="radio" id="2xl">
											</label> <label for="xxl">xxl <input type="radio" id="xxl">
											</label> <label for="3xl">3xl <input type="radio" id="3xl">
											</label> <label for="4xl">4xl <input type="radio" id="4xl">
											</label>
										</div>
									</div>
								</div>
							</div> -->
							<div class="card">
								<div class="card-heading">
									<a data-toggle="collapse" data-target="#collapseFive">Colors</a>
								</div>
								<div id="collapseFive" class="collapse show"
									data-parent="#accordionExample">
									<div class="card-body">
										<div class="shop__sidebar__color">
											<c:set var="counter" value="1" />
											<c:forEach items="${listCo}" var="dataCo">
												<label style="background: ${dataCo}; position: relative;"
													for="sp-${counter}"> <a
													href="./shop?coid=${dataCo}"
													style="position: absolute; width: 100%; height: 100%; z-index: 10;"></a>
													<input type="radio" style="z-index: 0;" id="sp-${counter}">
												</label>
												<c:set var="counter" value="${counter + 1}" />
											</c:forEach>
											<!-- 											  <label class="c-8" for="sp-8"> <input type="radio"
												id="sp-8">
											</label>  -->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-9">
				<div class="shop__product__option">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6">
							<div class="shop__product__option__left">
								<p>Showing 1–12 of 126 results</p>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6">
							<div class="shop__product__option__right">
								<p>Sort by Price:</p>
								<select onchange="redirectTo(this.value)">
									<option value="./shop">All</option>
									<option value="./shop?sort=LtoH" ${empty LtoH ?'' :'selected'}>Low
										To High</option>
									<option value="./shop?sort=HtoL" ${empty HtoL ? '':'selected' }>High
										To Low</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<!-- 					<div class="col-lg-4 col-md-6 col-sm-6">
						<div class="product__item">
							<div class="product__item__pic set-bg"
								data-setbg="img/shop/product/product-2.jpg">
								<ul class="product__hover">
									<li><a href="#"><img src="img/shop/icon/heart.png"
											alt=""></a></li>
									<li><a href="#"><img src="img/shop/icon/compare.png"
											alt=""> <span>Compare</span></a></li>
									<li><a href="#"><img src="img/shop/icon/search.png"
											alt=""></a></li>
								</ul>
							</div>
							<div class="product__item__text">
								<h6>Piqué Biker Jacket</h6>
								<a href="#" class="add-cart">+ Add To Cart</a>
								<div class="rating">
									<i class="fa fa-star-o"></i> <i class="fa fa-star-o"></i> <i
										class="fa fa-star-o"></i> <i class="fa fa-star-o"></i> <i
										class="fa fa-star-o"></i>
								</div>
								<h5>$67.24</h5>
								<div class="product__color__select">
									<label for="pc-4"> <input type="radio" id="pc-4">
									</label> <label class="active black" for="pc-5"> <input
										type="radio" id="pc-5">
									</label> <label class="grey" for="pc-6"> <input type="radio"
										id="pc-6">
									</label>
								</div>
							</div>
						</div>
					</div> -->
					<c:choose>
						<c:when test="${empty  listP}">
							<b>No Result found</b>
						</c:when>
						<c:otherwise>
							<c:forEach items="${listP }" var="data">
								<div class="col-lg-4 col-md-6 col-sm-6">
									<div class="product__item">
										<div class="product__item__pic set-bg"
											data-setbg="img/product/${empty data.img ? 'icon_website.png':data.img}"
											${empty data.img ? 'style="filter: grayscale(100%) brightness(117%);"':''}>
											<c:if test="${data.hot=='true' }">
												<span class="label">Hot</span>
											</c:if>
											<ul class="product__hover">
												<li><a href="./shop_details?proid=${data.productID}"><img
														src="img/shop/icon/search.png" alt=""><span>Search</span></a></li>
												<li><a href="#"><img
														src="img/shop/icon/compare.png" alt=""> <span>Compare</span></a></li>
												<li><a href="#"><img src="img/shop/icon/search.png"
														alt=""></a></li>
											</ul>
										</div>
										<div class="product__item__text">
											<%-- 											<h6>${data.productName }</h6>
											<a href="#" class="add-cart">+ Add To Cart</a>
											<h5>${data.price }</h5>
											<h6>${data.color }</h6> --%>

											<form class="addToCartForm" action="shopcart" method="post">
												<input type="hidden" name="productId"
													value="${data.productID}"> <input type="hidden"
													name="quantity" value="1"> <input type="hidden"
													name="price" value="${data.price}">
												<h5>$${data.price }</h5>
												<%-- 												<h6>${data.productName }</h6>
												<h6>${data.color}</h6>
												<h6>Amount: ${data.amount}</h6>
												<h6>Sold: ${data.sold}</h6> --%>
												<!-- 	<a class="add-cart">+ Add To Cart</a> -->
												<input class="btn btn-primary" type="submit"
													value="Add to Cart">
											</form>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="product__pagination">
							<a class="active" href="#">1</a> <a href="#">2</a> <a href="#">3</a>
							<span>...</span> <a href="#">21</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>