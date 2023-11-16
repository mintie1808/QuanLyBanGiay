document.addEventListener("DOMContentLoaded", function() {
	var navItems = document.querySelectorAll(".header__menu.mobile-menu li");
	var selectedNavItem = document.title || "Home";
	var title = selectedNavItem.replace(/_/g, ' ').replace(/\b\w/g, l => l.toUpperCase());
	document.title = 'Male-Fashion' + (title ? ' | ' + title : '');

	if (["Check Out", "Shop Details", "Shopping Cart"].includes(title)) {
		title = "Shop";
	} else if (["About", "Blog Details"].includes(title)) {
		title = "Blog";
	} else if (["Home"].includes(title)) {
		document.title = "Male-Fashion";
	}

	localStorage.setItem("selectedNavItem", title);

	navItems.forEach(navItem => {
		if (navItem.textContent.trim() === title) {
			navItem.classList.add("active");
		}

	});
});

/*login js*/
const wrapper = document.querySelector('.wrapper');
const registerLink = document.querySelector('.register-link');
const loginLink = document.querySelector('.login-link');

const toggleTitleAndClass = (title, isActive) => {
	document.title = `Male-Fashion | ${title}`;
	wrapper.classList.toggle('active', isActive);
};

if (wrapper && registerLink && loginLink) {
	registerLink.onclick = () => toggleTitleAndClass('Register', true);
	loginLink.onclick = () => toggleTitleAndClass('Login', false);
}

$(document).ready(function() {
	var $backToTop = $('#backtotop').hide();

	$(window).scroll(function() {
		$backToTop[$(this).scrollTop() ? 'fadeIn' : 'fadeOut']();
	});

	$backToTop.click(function() {
		$('html, body').animate({ scrollTop: 0 }, 500);
	}).trigger('scroll');
});


/*$(document).ready(function() {

	$(window).scroll(function() {
		if ($(this).scrollTop()) {
			$('container').addClass('sticky');
		}

	});
});
*/





var userImg = document.querySelector(".user-img");
var dropDown = document.querySelector('.drop-down');

if (dropDown) {
	dropDown.addEventListener('mouseleave', () => dropDown.classList.remove('show'));
	document.addEventListener('wheel', () => dropDown.classList.remove('show'));
	document.addEventListener('click', (event) => {
		if (!event.target.closest('.user-img') && !event.target.closest('.drop-down')) {
			dropDown.classList.remove('show');
		}
	});
	window.addEventListener('resize', function() {
		const isSmallScreen = window.innerWidth < 765;
		if (isSmallScreen) {
			dropDown.classList.remove('show');
		}
	});
	document.body.addEventListener('click', (event) => {
		const isClickInsideUserImg = userImg.contains(event.target);
		const isClickInsideDropDown = dropDown.contains(event.target);

		if (isClickInsideUserImg || isClickInsideDropDown) {
			dropDown.classList.toggle('show');
			dropDown.style.cssText = 'position: absolute; transform: translate3d(-132px, 5px, 0px); top: 0px; left: 0px; willChange: transform;';
		} else {
			dropDown.classList.remove('show');
		}
	});
}


document.addEventListener('DOMContentLoaded', function() {
	const get = (selector) => document.querySelector(selector);
	const getAll = (selector) => document.querySelectorAll(selector);
	const showElements = (selectors) => {
		selectors.forEach((selector) => get(selector).style.display = 'block');
	};
	const NoneElements = (selectors) => {
		selectors.forEach((selector) => get(selector).style.display = 'none');
	};

	const message = get('.tb');
	const message_ = get('.tb_1');
	const editProfile = get('.editProfile');
	const ihItem = get('.ih-item');
	const offcanvas_click_img = get('.offcanvas_click_img');
	const closeInformation = get('.close_Information');
	const popoverTriggers = getAll('.popoverTrigger');

	var btnLogoutElements = document.querySelectorAll('.click_logout');
	var cancel = document.querySelectorAll('.cancel');
	var div_close_i = document.querySelector(".div_close_i");

	if (message != null) {
		if (message && message.value.trim()) {
			showElements([
				'.sweet-overlay',
				'.showSweetAlert',
				'.sa-error',
				'.h2_sweet-alert',
				'.p_sweet-alert',
				'.div_sweet-alert_1'
			]);

		}
	}
	if (message_ != null) {
		if (message_ && message_.value.trim()) {
			showElements([
				'.sweet-overlay',
				'.showSweetAlert',
				'.sa-success',
				'.h2_sweet-alert',
				'.div_sweet-alert_1'
			]);

		}
	}
	if (btnLogoutElements.length > 0) {
		// Thêm sự kiện click cho từng phần tử
		btnLogoutElements.forEach(function(btnLogout) {
			btnLogout.addEventListener('click', function() {
				NoneElements([
					'.sweet-overlay',
					'.showSweetAlert',
					'.sa-info',
					'.sa-success',
					'.sa-error',
					'.h2_sweet-alert',
					'.p_sweet-alert',
					'.div_sweet-alert_1'
				]);
				showElements([
					'.sweet-overlay',
					'.showSweetAlert',
					'.sa-info',
					'.h2_sweet-alert_2',
					'.p_sweet-alert_2',
					'.div_sweet-alert_2'
				]);
				if (message) {
					message.value = '';
				}

				if (message_) {
					message_.value = '';
				}
			});
		});
	}
	if (cancel.length > 0) {
		cancel.forEach(function(btnLogout) {
			btnLogout.addEventListener('click', () => {
				NoneElements([
					'.sweet-overlay',
					'.showSweetAlert'
				]);
				if (message) {
					message.value = '';
				}

				if (message_) {
					message_.value = '';
				}
			});
		});
	}

	[ihItem, offcanvas_click_img].forEach((trigger) => {
		if (trigger) {
			trigger.addEventListener('click', (event) => {
				editProfile.style.display = 'block';
				event.stopPropagation();
			});
		}
	});

	if (editProfile && closeInformation) {
		document.addEventListener('click', (event) => {
			const isClickOutside = !closeInformation.contains(event.target);
			const isClickOnBackground = event.target.closest('.div_background');

			if (isClickOutside && !isClickOnBackground) {
				editProfile.style.display = 'none';
			}
			if (div_close_i.contains(event.target)) {
				editProfile.style.display = 'none';
			}
		});
	}

	popoverTriggers.forEach((popoverTrigger) => {
		['mouseover', 'mouseout'].forEach((eventType) => {
			popoverTrigger.addEventListener(eventType, () => {
				const popover = popoverTrigger.closest('.text-right').querySelector('.popover');
				popover && popover.classList.toggle('show', eventType === 'mouseover');
			});
		});
	});
});
