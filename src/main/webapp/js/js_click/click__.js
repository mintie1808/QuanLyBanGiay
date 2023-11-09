document.addEventListener("DOMContentLoaded", function() {
    var navItems = document.querySelectorAll(".header__menu.mobile-menu li");
    var urlParams = new URLSearchParams(window.location.search);
    var selectedNavItem = urlParams.get("page") || "Home";
    var title = selectedNavItem.replace(/_/g, ' ').replace(/\b\w/g, l => l.toUpperCase());

    if (["Check Out", "Shop Details", "Shopping Cart"].includes(title)) {
        title = "Shop";
    } else if (["About", "Blog Details"].includes(title)) {
        title = "Blog";
    }

    document.title = 'Male-Fashion' + (title ? ' | ' + title : '');

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


$(document).ready(function() {

	$(window).scroll(function() {
		if ($(this).scrollTop()) {
			$('container').addClass('sticky');
		}

	});
});