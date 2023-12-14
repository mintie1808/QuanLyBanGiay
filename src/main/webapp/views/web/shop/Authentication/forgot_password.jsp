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
	                      <div class="z-1 position-relative"><a class="link-light mb-4 font-sans-serif fs-4 d-inline-block fw-bolder" href="./trang-chu">Male-Fashion</a>
	                        <p class="opacity-75 text-white">Discover the latest trends in male fashion with Male-Fashion. Our collection is designed to empower your style, allowing you to express yourself confidently.</p>
	                      </div>
	                    </div>
	                    <div class="mt-3 mb-4 mt-md-4 mb-md-5" data-bs-theme="light">
	                      <p class="mb-0 mt-4 mt-md-5 fs--1 fw-semi-bold text-white opacity-75">Read our <a class="text-decoration-underline text-white" href="#!">terms</a> and <a class="text-decoration-underline text-white" href="#!">conditions </a></p>
	                    </div>
	                  </div>
	                  <div class="col-md-7 d-flex flex-center">
	                    <div class="p-4 p-md-5 flex-grow-1">
	                      <div class="text-center text-md-start">
	                        <h4 class="mb-0"> Forgot your password?</h4>
	                        <p class="mb-4">Enter your email and we'll send you a reset link.</p>
	                      </div>
	                      <div class="row justify-content-center">
	                        <div class="col-sm-8 col-md">
	                          <form action="./forgot_password" method="post" class="mb-3"><input class="form-control" name="em_forgot_password" type="email" placeholder="Email address" autocomplete="off"  autofocus required>
	                            <div class="mb-3"></div>
	                               <input type="hidden" name="page" value="forgot_password">
	                            <button class="btn btn-flat btn-login  d-block w-100 mt-3" type="submit" name="submit">Send reset link</button>
	                          </form><a class="fs--1 text-600 a_text-decoration" href="./login">I can't recover my account using this page<span class="d-inline-block ms-1">→</span></a>
	                        </div>
	                      </div>
	                    </div>
	                  </div>
	                </div>
	              </div>
	            </div>
	          </div>
	        </div>
	      </div>
</div>