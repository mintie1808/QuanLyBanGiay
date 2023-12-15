<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
	function setDefaultQuantity() {
		var quantityInput = document.getElementById('quantityInput');
		if (quantityInput.value.trim() === '') {
			quantityInput.value = '1';
		}
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
					window.location.reload();
				}
			});
			return false;
		});
	});
</script>
<!-- Shop Details Section Begin -->
<section class="shop-details">
	<c:forEach items="${listP }" var="data">
		<div class="product__details__pic">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="product__details__breadcrumb">
							<a href="./trang-chu">Home</a> <a href="./trang-chu?page=shop">Shop</a>
							<span>Product Details</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3 col-md-3">
						<ul class="nav nav-tabs" role="tablist">
							<li class="nav-item"><a class="nav-link active"
								data-toggle="tab" href="#tabs-1" role="tab">
									<div class="product__thumb__pic set-bg"
										data-setbg="img/product/${data.img }"></div>
							</a></li>
							<%-- 							<li class="nav-item"><a class="nav-link" data-toggle="tab"
								href="#tabs-2" role="tab">
									<div class="product__thumb__pic set-bg"
										data-setbg="img/product/${data.img }"></div>
							</a> <!-- </li> --%>
							<!-- 							<li class="nav-item"><a class="nav-link" data-toggle="tab"
								href="#tabs-3" role="tab">
									<div class="product__thumb__pic set-bg"
										data-setbg="img/shop/shop-details/thumb-3.png"></div>
							</a></li>
							<li class="nav-item"><a class="nav-link" data-toggle="tab"
								href="#tabs-4" role="tab">
									<div class="product__thumb__pic set-bg"
										data-setbg="img/shop/shop-details/thumb-4.png">
										<i class="fa fa-play"></i>
									</div>
							</a></li>  -->
						</ul>
					</div>
					<div class="col-lg-6 col-md-9">
						<div class="tab-content">
							<div class="tab-pane active" id="tabs-1" role="tabpanel">
								<div class="product__details__pic__item">
									<img src="img/product/${data.img }" alt="">
								</div>
							</div>
							<div class="tab-pane" id="tabs-2" role="tabpanel">
								<div class="product__details__pic__item">
									<img src="img/product/${data.img }" alt="">
								</div>
							</div>
							<!-- 						<div class="tab-pane" id="tabs-3" role="tabpanel">
							<div class="product__details__pic__item">
								<img src="img/shop/shop-details/product-big.png" alt="">
							</div>
						</div>
						<div class="tab-pane" id="tabs-4" role="tabpanel">
							<div class="product__details__pic__item">
								<img src="img/shop/shop-details/product-big-4.png" alt=""> <a
									href="https://www.youtube.com/watch?v=8PJ3_p7VqHw&list=RD8PJ3_p7VqHw&start_radio=1"
									class="video-popup"><i class="fa fa-play"></i></a>
							</div>
						</div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="product__details__content">
			<div class="container">
				<div class="row d-flex justify-content-center">
					<div class="col-lg-8">
						<div class="product__details__text">
							<h4>${data.productName}</h4>
							<h6>Amount: ${data.amount}</h6>
							<h6>Sold: ${data.sold}</h6>
							<!-- 						<div class="rating">
							<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star-o"></i> <span> - 5 Reviews</span>
						</div> -->
							<!-- <h3>
							$270.00 <span>70.00</span>
						</h3> -->
							<h3>${data.price}</h3>
							<p>${data.description}</p>
							<!-- 						<div class="product__details__option">
							<div class="product__details__option__size">
								<span>Size:</span> <label for="xxl">xxl <input
									type="radio" id="xxl">
								</label> <label class="active" for="xl">xl <input type="radio"
									id="xl">
								</label> <label for="l">l <input type="radio" id="l">
								</label> <label for="sm">s <input type="radio" id="sm">
								</label>
							</div>
							<div class="product__details__option__color">
								<span>Color:</span> <label class="c-1" for="sp-1"> <input
									type="radio" id="sp-1">
								</label> <label class="c-2" for="sp-2"> <input type="radio"
									id="sp-2">
								</label> <label class="c-3" for="sp-3"> <input type="radio"
									id="sp-3">
								</label> <label class="c-4" for="sp-4"> <input type="radio"
									id="sp-4">
								</label> <label class="c-9" for="sp-9"> <input type="radio"
									id="sp-9">
								</label>
							</div>
						</div> -->
							<div class="product__details__cart__option">
								<form class="addToCartForm" action="shopcart" method="post">
									<input type="hidden" name="productId" value="${data.productID}">
									<input min="1" max="${data.amount}" type="number"
										name="quantity" id="quantityInput" placeholder="1"> <input
										type="hidden" name="price" value="${data.price}"> <input
										type="submit" value="Add to Cart"
										onclick="setDefaultQuantity()">
								</form>

								<div class="product__details__last__option"
									style="margin-top: 10px;">
									<h5>
										<span>Guaranteed Safe Checkout</span>
									</h5>
									<img src="img/shop/shop-details/details-payment.png" alt="">
									<ul>

										<li><span>Categories:</span>${cateName }</li>

									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="product__details__tab">
							<ul class="nav nav-tabs" role="tablist">
								<li class="nav-item"><a class="nav-link active"
									data-toggle="tab" href="#tabs-5" role="tab">Description</a></li>
								<li class="nav-item"><a class="nav-link" data-toggle="tab"
									href="#tabs-6" role="tab">Customer Previews(5)</a></li>
								<li class="nav-item"><a class="nav-link" data-toggle="tab"
									href="#tabs-7" role="tab">Additional information</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="tabs-5" role="tabpanel">
									<div class="product__details__tab__content">
										<p class="note">Nam tempus turpis at metus scelerisque
											placerat nulla deumantos solicitud felis. Pellentesque diam
											dolor, elementum etos lobortis des mollis ut risus. Sedcus
											faucibus an sullamcorper mattis drostique des commodo
											pharetras loremos.</p>
										<div class="product__details__tab__content__item">
											<h5>Products Infomation</h5>
											<p>A Pocket PC is a handheld computer, which features
												many of the same capabilities as a modern PC. These handy
												little devices allow individuals to retrieve and store
												e-mail messages, create a contact file, coordinate
												appointments, surf the internet, exchange text messages and
												more. Every product that is labeled as a Pocket PC must be
												accompanied with specific software to operate the unit and
												must feature a touchscreen and touchpad.</p>
											<p>As is the case with any new technology product, the
												cost of a Pocket PC was substantial during it’s early
												release. For approximately $700.00, consumers could purchase
												one of top-of-the-line Pocket PCs in 2003. These days,
												customers are finding that prices have become much more
												reasonable now that the newness is wearing off. For
												approximately $350.00, a new Pocket PC can now be purchased.</p>
										</div>
										<div class="product__details__tab__content__item">
											<h5>Material used</h5>
											<p>Polyester is deemed lower quality due to its none
												natural quality’s. Made from synthetic materials, not
												natural like wool. Polyester suits become creased easily and
												are known for not being breathable. Polyester suits tend to
												have a shine to them compared to wool and cotton suits, this
												can make the suit look cheap. The texture of velvet is
												luxurious and breathable. Velvet is a great choice for
												dinner party jacket and can be worn all year round.</p>
										</div>
									</div>
								</div>
								<div class="tab-pane" id="tabs-6" role="tabpanel">
									<div class="product__details__tab__content">
										<div class="product__details__tab__content__item">
											<h5>Products Infomation</h5>
											<p>A Pocket PC is a handheld computer, which features
												many of the same capabilities as a modern PC. These handy
												little devices allow individuals to retrieve and store
												e-mail messages, create a contact file, coordinate
												appointments, surf the internet, exchange text messages and
												more. Every product that is labeled as a Pocket PC must be
												accompanied with specific software to operate the unit and
												must feature a touchscreen and touchpad.</p>
											<p>As is the case with any new technology product, the
												cost of a Pocket PC was substantial during it’s early
												release. For approximately $700.00, consumers could purchase
												one of top-of-the-line Pocket PCs in 2003. These days,
												customers are finding that prices have become much more
												reasonable now that the newness is wearing off. For
												approximately $350.00, a new Pocket PC can now be purchased.</p>
										</div>
										<div class="product__details__tab__content__item">
											<h5>Material used</h5>
											<p>Polyester is deemed lower quality due to its none
												natural quality’s. Made from synthetic materials, not
												natural like wool. Polyester suits become creased easily and
												are known for not being breathable. Polyester suits tend to
												have a shine to them compared to wool and cotton suits, this
												can make the suit look cheap. The texture of velvet is
												luxurious and breathable. Velvet is a great choice for
												dinner party jacket and can be worn all year round.</p>
										</div>
									</div>
								</div>
								<div class="tab-pane" id="tabs-7" role="tabpanel">
									<div class="product__details__tab__content">
										<p class="note">Nam tempus turpis at metus scelerisque
											placerat nulla deumantos solicitud felis. Pellentesque diam
											dolor, elementum etos lobortis des mollis ut risus. Sedcus
											faucibus an sullamcorper mattis drostique des commodo
											pharetras loremos.</p>
										<div class="product__details__tab__content__item">
											<h5>Products Infomation</h5>
											<p>A Pocket PC is a handheld computer, which features
												many of the same capabilities as a modern PC. These handy
												little devices allow individuals to retrieve and store
												e-mail messages, create a contact file, coordinate
												appointments, surf the internet, exchange text messages and
												more. Every product that is labeled as a Pocket PC must be
												accompanied with specific software to operate the unit and
												must feature a touchscreen and touchpad.</p>
											<p>As is the case with any new technology product, the
												cost of a Pocket PC was substantial during it’s early
												release. For approximately $700.00, consumers could purchase
												one of top-of-the-line Pocket PCs in 2003. These days,
												customers are finding that prices have become much more
												reasonable now that the newness is wearing off. For
												approximately $350.00, a new Pocket PC can now be purchased.</p>
										</div>
										<div class="product__details__tab__content__item">
											<h5>Material used</h5>
											<p>Polyester is deemed lower quality due to its none
												natural quality’s. Made from synthetic materials, not
												natural like wool. Polyester suits become creased easily and
												are known for not being breathable. Polyester suits tend to
												have a shine to them compared to wool and cotton suits, this
												can make the suit look cheap. The texture of velvet is
												luxurious and breathable. Velvet is a great choice for
												dinner party jacket and can be worn all year round.</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</section>
<!-- Shop Details Section End -->
<!-- Related Section Begin -->
<section class="related spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="related-title">Related Product</h3>
			</div>
		</div>
		<div class="row">

			<c:forEach items="${relatedList}" var="listR">
				<div class="col-lg-3 col-md-6 col-sm-6 col-sm-6">
					<div class="product__item">
						<div class="product__item__pic set-bg"
							data-setbg="img/product/${listR.img}">
							<span style="display:${listR.hot=='true'?'none;':'' }"
								class="label">Hot</span>
							<ul class="product__hover">
								<li><a href="./shop_details?proid=${listR.productID}"><img
										src="img/shop/icon/search.png" alt=""></a></li>
							</ul>
						</div>
						<div class="product__item__text">
							<form class="addToCartForm" action="shopcart" method="post">
								<input type="hidden" name="productName"
									value="${listR.productName}">
								<h6>${data.productName }</h6>
								<input type="hidden" name="productId" value="${listR.productID}">
								<input type="hidden" name="quantity" value="1"> <input
									type="hidden" name="price" value="${listR.price}">
								<h5>$${listR.price }</h5>
								<h6>${listR.color}</h6>
								<!-- 	<a class="add-cart">+ Add To Cart</a> -->
								<input class="btn btn-primary" type="submit" value="Add to Cart">
							</form>

							<%-- 	<h6>${listR.productName}</h6>
							<a href="#" class="add-cart">+ Add To Cart</a>
							<div class="rating">
								<i class="fa fa-star-o"></i> <i class="fa fa-star-o"></i> <i
									class="fa fa-star-o"></i> <i class="fa fa-star-o"></i> <i
									class="fa fa-star-o"></i>
							</div>
							<h5>$${listR.price}</h5>
							<h5>${listR.color}</h5> --%>

						</div>
					</div>
				</div>
			</c:forEach>





		</div>
	</div>
</section>
<!-- Related Section End -->
