<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="doan.model.CartItem"%>
<%@ page import="doan.model.Usermodel"%>


<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-option">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="breadcrumb__text">
					<h4>Check Out</h4>
					<div class="breadcrumb__links">
						<a href="./trang-chu">Home</a> <a href="./trang-chu?page=shop">Shop</a>
						<span>Check Out</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Breadcrumb Section End -->

<!-- Checkout Section Begin -->
<section class="checkout spad">
	<div class="container">
		<div class="checkout__form">
			<form action="order" method="post">
				<div class="row">
					<div class="col-lg-8 col-md-6">
						<%
						HttpSession s = request.getSession();
						Usermodel u = (Usermodel) s.getAttribute("USER");
						String arr[] = u.getFullname().split(" ");
						String lastName = "";
						String  firstName = "";
						if(arr.length > 2 ){							
							lastName = arr[arr.length-1];
							for(int i = 0 ; i<arr.length-1 ; i ++ ){
								firstName += arr[i];
							}
						} else if(arr.length == 1 ){
							firstName = arr[0];
						}
						%>
						<h6 class="checkout__title">Billing Details</h6>
						<div class="row">
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>
										Fist Name<span>*</span>
									</p>
									<input type="text" value="<%=firstName%>">
								</div>
							</div>

							<div class="col-lg-6">
								<div class="checkout__input">
									<p>
										Last Name<span>*</span>
									</p>
									<input type="text" value="<%=lastName%>">
								</div>
							</div>
						</div>
						<div class="checkout__input">
							<p>
								Address<span>*</span>
							</p>
							<input type="text" placeholder=" Address"
								class="checkout__input__add" value="<%=u.getAddress()%>">
						</div>

						<div class="row">
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>
										Phone<span>*</span>
									</p>
									<input type="text">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>
										Email<span>*</span>
									</p>
									<input type="text" value="<%=u.getEmail()%>">
								</div>
							</div>
						</div>

						<!-- <div class="checkout__input">
							<p>Order note</p>
							<input type="text"
								placeholder="Notes about your order,special notes for delivery.">
						</div> -->
					</div>
					<div class="col-lg-4 col-md-6">
						<div class="checkout__order">
							<h4 class="order__title">Your order</h4>
							<div class="checkout__order__products">
								Product <span>Total</span>
							</div>
							<ul class="checkout__total__products">
								<%
								List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
								double total = 0.0;
								int i = 1;
								for (CartItem cartItem : cart) {
								%>
								<li><%=i%>. <%=cartItem.getProduct().getProductName()%> <span>$
										<%=cartItem.getPrice() * cartItem.getQuantity()%></span></li>
								<%
								total += cartItem.getPrice() * cartItem.getQuantity();
								i++;
								%>


								<%
								}
								%>
							</ul>
							<ul class="checkout__total__all">
								<!-- <li>Subtotal <span>$750.99</span></li> -->
								<li>Total <span>$<%=total%></span></li>
							</ul>

							<div class="checkout__input__checkbox">
								<label for="acc-or"> Create an account? <input
									type="checkbox" id="acc-or"> <span class="checkmark"></span>
								</label>
							</div>

							<div class="checkout__input__checkbox">
								<label for="payment"> Check Payment <input
									type="checkbox" id="payment"> <span class="checkmark"></span>
								</label>
							</div>
							<div class="checkout__input__checkbox">
								<label for="paypal"> Paypal <input type="checkbox"
									id="paypal"> <span class="checkmark"></span>
								</label>
							</div>
							<!-- <a href="order"> <input type="button" class="btn btn-primary"
								value="PLACE ORDER" />
							</a> -->
							<button type="submit" class="site-btn">PLACE ORDER</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>
<!-- Checkout Section End -->