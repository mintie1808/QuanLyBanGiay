<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<input class="tb_1" value="${message_success}" type="hidden">
<input class="tb" value="${message}" type="hidden">
<div class="sweet-overlay div_background" tabindex="-1"
	style="opacity: 1.09; z-index: 99999; display: none;">
	<div class="sweet-alert showSweetAlert visible"
		style="display: none; margin-top: -230px;">
		<div class="sa-icon sa-error animateErrorIcon" style="display: none;">
			<span class="sa-x-mark animateXMark"> <span
				class="sa-line sa-left"></span> <span class="sa-line sa-right"></span>
			</span>
		</div>
		<div class="sa-icon sa-info" style="display: none;"></div>
		<div class="sa-icon sa-success animate" style="display: none;">
			<span class="sa-line sa-tip animateSuccessTip"></span> <span
				class="sa-line sa-long animateSuccessLong"></span>
			<div class="sa-placeholder"></div>
			<div class="sa-fix"></div>
		</div>
		<h2 class="h2_sweet-alert" style="display: none;">${message}${message_success }</h2>
		<h2 class="h2_sweet-alert_2" style="display: none;">Do you want
			to exit?</h2>
		<p class="p_sweet-alert" style="display: none;">${p_mes }</p>
		<p class="p_sweet-alert_2" style="display: none;">Press OK to exit</p>
		<fieldset>
			<input type="text" tabindex="3" placeholder="">
			<div class="sa-input-error"></div>
		</fieldset>
		<div class="sa-button-container div_sweet-alert_1"
			style="display: none;">
			<div class="sa-confirm-button-container">
				<button class="cancel" tabindex="1"
					style="display: inline-block; background-color: rgb(140, 212, 245); box-shadow: rgba(140, 212, 245, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.05) 0px 0px 0px 1px inset;">OK</button>
			</div>
		</div>
		<div class="sa-button-container div_sweet-alert_2"
			style="display: none;">
			<button class="cancel" tabindex="1"
				style="display: inline-block; box-shadow: none;">Cancel</button>
			<div class="sa-confirm-button-container">
				<form action="./user" method="post">
					<div class="f_logout">
						<input type="hidden" name="logout" value="true"> <span>
							<button class="confirm" tabindex="2"
								style="display: inline-block; background-color: rgb(140, 212, 245); box-shadow: rgba(140, 212, 245, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.05) 0px 0px 0px 1px inset;">OK</button>
						</span>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
