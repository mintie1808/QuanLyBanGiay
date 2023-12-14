<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="body_login">
<div class="container-fluid">
        <div class="row min-vh-50 flex-center g-0">
        <div class="col-lg-8 col-xxl-5 py-3 position-relative">
            <div class="card overflow-hidden z-1">
              <div class="card-body p-0">
                <div class="row g-0 h-100">
                  <div class="col-md-5 text-center bg-card-gradient">
                    <div class="position-relative p-4 pt-md-5 pb-md-7" data-bs-theme="light">
                      <div class="z-1 position-relative">
                      	<a class="link-light mb-4 font-sans-serif fs-4 d-inline-block fw-bolder" href="./trang-chu">Male-Fashion</a>
                        <p class="opacity-75 text-white">Discover the latest trends in male fashion with Male-Fashion. Our collection is designed to empower your style, allowing you to express yourself confidently.</p>
                      </div>
                    </div>
                    <div class="mt-3 mb-4 mt-md-4 mb-md-5" data-bs-theme="light">
                      <p class="pt-3 text-white">Have an account?<br>
                      	<a class="btn btn-outline-light mt-2 px-4" href="./login">Log In</a>
                      </p>
                    </div>
                  </div>
                  <div class="col-md-7 d-flex flex-center">
                    <div class="p-4 p-md-5 flex-grow-1">
                      <h3>Register</h3>
                      <form id="registrationForm" action="./register" method="post">
                        <div class="mb-3">
                        	<label class="form-label" for="card-name">Name</label>
                        	<input class="form-control" type="text" value="${us_register }" name="us_register" autocomplete="off" autofocus id="card-name" required>
                        </div>
                        <div class="mb-3">
                        	<label class="form-label" for="card-email">Email address</label>
                        	<input class="form-control" type="email" value="${email_register }" name="email_register" autocomplete="off" id="card-email" required>
                        </div>
                        <div class="row gx-2">
                          <div class="mb-3 col-sm-6">
	                          <label class="form-label" for="card-password">Password</label>
	                          <input class="form-control" type="password" name="ps_register" id="card-confirm-password" required>
                          </div>
                          <div class="mb-3 col-sm-6">
	                          <label class="form-label" for="card-confirm-password">Confirm Password</label>
	                          <input class="form-control" name="cf_ps_register" type="password" id="card-confirm-password"  required>
                          </div>
                        </div>
<!--                         <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="card-register-checkbox">
	                        <label class="form-label" for="card-register-checkbox">I accept the 
		                        <a class="a_text-decoration" href="#!">terms </a> and 
		                        <a class="white-space-nowrap a_text-decoration" href="#!">privacy policy</a>
	                        </label>
                        </div> -->
                        <div class="mb-3">
                        	<input type="hidden" name="page" value="register">
                        	<button class="btn btn-flat btn-login d-block w-100 mt-3" type="submit" name="submit">Register</button>
                        </div>
                      </form>
<!--                       <div class="position-relative mt-4">
                        <hr>
                        <div class="divider-content-center">or register with</div>
                      </div> -->
<!--                       <div class="row g-2 mt-2">
                        <div class="col-sm-12">
                        	<a class="btn btn-flat btn-outline-login btn-sm d-block w-100" href="#">
                        		<svg class="svg-inline--fa fa-google-plus-g fa-w-20 me-2" data-fa-transform="grow-8" aria-hidden="true" focusable="false" data-prefix="fab" data-icon="google-plus-g" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512" data-fa-i2svg="" style="transform-origin: 0.625em 0.5em;">
	                        		<g transform="translate(320 256)">
		                        		<g transform="translate(0, 0)  scale(1.5, 1.5)  rotate(0 0 0)">
			                        		<path fill="currentColor" d="M386.061 228.496c1.834 9.692 3.143 19.384 3.143 31.956C389.204 370.205 315.599 448 204.8 448c-106.084 0-192-85.915-192-192s85.916-192 192-192c51.864 0 95.083 18.859 128.611 50.292l-52.126 50.03c-14.145-13.621-39.028-29.599-76.485-29.599-65.484 0-118.92 54.221-118.92 121.277 0 67.056 53.436 121.277 118.92 121.277 75.961 0 104.513-54.745 108.965-82.773H204.8v-66.009h181.261zm185.406 6.437V179.2h-56.001v55.733h-55.733v56.001h55.733v55.733h56.001v-55.733H627.2v-56.001h-55.733z" transform="translate(-320 -256)">
			                        		</path>
		                        		</g>
	                        		</g>
                        		</svg> google</a></div>
                        </div> -->
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
	</div>
</div>