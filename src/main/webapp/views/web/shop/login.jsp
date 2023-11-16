<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="body_login">
	<div class="wrapper">
		<span class="bg-animate"></span> <span class="bg-animate2"></span>
		<div class="form-box login">
			<h2 class="animation" style="--i: 0;--j:20;">Login</h2>
			<form action="./trang-chu" method="post" >
				<div class="input-box animation" style="--i: 1;--j:21;">

					<input type="text" name="username" value="${pwus }" required > <label>Username</label> <i
						class="icon_profile"></i>
				</div>
				<div class="input-box animation" style="--i: 2;--j:22;">
					<input type="password" name="password" required> <label>Password</label> <i
						class="icon_lock"></i>

				</div>
				<input type="hidden" value="login" name="page"> 
				<button type="submit" class="btn_login animation" style="--i: 3;--j:23;">Login</button>
				<div class="logreg-link animation" style="--i: 4;--j:24;">
					<p>
						Don't have an account? <a class="register-link">Sign
							Up</a>
					</p>
				</div>
			</form>
		</div>
		<div class="info-text login">
			<h2 class="animation" style="--i: 0;--j:20;">Welcome Back!</h2>
			<p class="animation" style="--i: 1;--j:21;">Lorem ipsum, dolor sit amet consectetur adipsicing.</p>
		</div>

		<!-- register -->
		<div class="form-box register">
			<h2 class="animation" style="--i: 17;--j:0;">Sign Up</h2>
			<form action="./user" method="post">
				<div class="input-box animation" style="--i: 18;--j:1;">
					<input title="nhập sai UserName" name="us_register" type="text" required> <label>Username</label> <i
						class="icon_profile"></i>
				</div>
				<div class="input-box animation" style="--i: 19;--j:2;">
					<input type="text" required name="email_register"> <label>Email</label> <i
						class="icon_mail"></i>
				</div>
				<div class="input-box animation" style="--i: 20;--j:3;">
					<input type="password" name="ps_register" required> <label>Password</label> <i
						class="icon_lock"></i>
				</div>
				<input type="hidden" value="login" name="page">
				<button type="submit" class="btn_register animation" style="--i: 21;--j:4;">Sign Up</button>
				<div class="logreg-link animation" style="--i: 22;--j:5;">
					<p>
						Already have an account? <a class="login-link">Login </a>
					</p>
				</div>
			</form>
		</div>
			<div class="info-text register" style="--i: 17; --j:0;">
						<h2 class="animation" style="--i: 18;--j:1;">Welcome Back!</h2>
			<p class="animation">Lorem ipsum, dolor sit amet consectetur adipsicing.</p>
		</div>
	</div>
</div>
