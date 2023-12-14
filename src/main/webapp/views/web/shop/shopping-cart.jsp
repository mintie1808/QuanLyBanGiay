<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="doan.model.CartItem"%>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
	// Function to update quantity using Ajax
	function updateQuantity(productId, newQuantity) {
		$.ajax({
			url : 'updateQuantity', // Replace with your CartController UR
			method : 'POST',
			data : {
				productId : productId,
				newQuantity : newQuantity
			},
			success : function(response) {
				// Update the cart display or handle success as needed
				console.log(response);
				location.reload();
			},
			error : function(error) {
				console.error('Error updating quantity:', error);
			}
		});
	}
	function removeCartItem(productId) {
		$.ajax({
			url : 'removeCartItem',
			method : 'POST',
			data : {
				productId : productId
			},
			success : function(response) {
				console.log(response);
				location.reload();
			},
			error : function(error) {
				console.error('Error removing cart item:', error);
			}
		});
	}
</script>
<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-option">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="breadcrumb__text">
					<h4>Shopping Cart</h4>
					<div class="breadcrumb__links">
						<a href="./trang-chu">Home</a> <a href="./trang-chu?page=shop">Shop</a>
						<span>Shopping Cart</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Breadcrumb Section End -->

<!-- Shopping Cart Section Begin -->
<section class="shopping-cart spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-8">
				<c:set var="o" value="${cart}" />
				<div class="shopping__cart__table">
					<table>
						<thead>
							<tr>
								<th>Product</th>
								<th>Quantity</th>
								<th>Total</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<%
							List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
							double total = 0.0;
							if (cart != null && !cart.isEmpty()) {
								for (CartItem cartItem : cart) {
							%>
							<tr>
								<td class="product__cart__item">
									<div class="product__cart__item__pic">
										<img src="img/product/<%=cartItem.getProduct().getImg()%>"
											alt="" style="width: 70px;height: 70px;">
									</div>
									<div class="product__cart__item__text">
										<h6><%=cartItem.getProduct().getProductName()%></h6>
										<h5><%=cartItem.getPrice() * cartItem.getQuantity()%></h5>
									</div>
								</td>

								<td class="quantity__item">
									<div class="quantity">
										<div class="pro-qty-2">
											<input id="quantityInputC" name="quantity"
												class="input_quantity" min="1"
												max="<%=cartItem.getProduct().getAmount()%>" type="number"
												value="<%=cartItem.getQuantity()%>"
												placeholder="<%=cartItem.getQuantity()%>"
												oninput="updateQuantity(<%=cartItem.getProduct().getProductID()%>, this.value); " required>
										</div>
									</div>
								</td>
								<td class="cart__price"><%=cartItem.getPrice() * cartItem.getQuantity()%></td>
								<td class="cart__close"><i class="fa fa-close" style="cursor: pointer;"
									onclick="removeCartItem(<%=cartItem.getProduct().getProductID()%>)"></i></td>
							</tr>
							<%
							total += cartItem.getPrice() * cartItem.getQuantity();
							}
							} else {
							%>
							<tr>
								<td>Your cart is empty</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-6">
						<div class="continue__btn">
							<a href="./shop">Continue Shopping</a>
						</div>
					</div>
					<!-- 					<div class="col-lg-6 col-md-6 col-sm-6">
						<div class="continue__btn update__btn">
							<a href="#"><i class="fa fa-spinner"></i> Update cart</a>
						</div>
					</div> -->
				</div>
			</div>
			<div class="col-lg-4">
				<!-- 				<div class="cart__discount">
					<h6>Discount codes</h6>
					<form action="#">
						<input type="text" placeholder="Coupon code">
						<button type="submit">Apply</button>
					</form>
				</div> -->
				<div class="cart__total">
					<h6>Cart total</h6>
					<ul>

						<li>Total <span>$ <%=total%>
						</span></li>
						<a href="check_out" class="primary-btn">Proceed to checkout</a>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Shopping Cart Section End -->