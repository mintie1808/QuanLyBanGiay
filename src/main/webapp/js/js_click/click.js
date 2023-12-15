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
document.addEventListener("DOMContentLoaded", function() {
	var navItems_ep = document.querySelectorAll(".nav_silde_ep li");
	var displayDivs = document.querySelectorAll(".displayDiv");

	if (displayDivs.length > 0) {
		navItems_ep.forEach(item => item.classList.remove("active"));

		displayDivs.forEach(displayDiv => {
			displayDiv.addEventListener("click", function() {
				var titleOrder = displayDiv.dataset.target;
				// Kiểm tra nếu titleOrder rỗng, đặt giá trị mặc định là "My Account"
				if (!titleOrder || titleOrder != "reviewOrder" && titleOrder != "changePassword") {
					titleOrder = "myAccount";
				}
				localStorage.setItem("selectedNavItem_ep", titleOrder);
			});
		});
	}
	function handleNavItemClick(navItem) {
		var target = navItem.dataset.target;
		navItems_ep.forEach(item => item.classList.remove("active"));
		navItem.classList.add("active");

		if (!target) {
			target = "myAccount";
		}
		localStorage.setItem("selectedNavItem_ep", target);

		// Perform actions based on the clicked item
		var profileDiv = document.getElementById("profileDiv");
		var orderDiv = document.getElementById("orderDiv");
		var changePassDiv = document.getElementById("changePassDiv");
		var current_password = document.getElementById("current_password");

		if (changePassDiv && window.getComputedStyle(changePassDiv).display === "block") {
			// Check if current_password exists
			if (current_password) {
				current_password.focus();
			}
		}

		switch (target) {
			case "myAccount":
				profileDiv.style.display = "block";
				changePassDiv.style.display = "none";
				orderDiv.style.display = "none";
				break;
			case "reviewOrder":
				profileDiv.style.display = "none";
				changePassDiv.style.display = "none";
				orderDiv.style.display = "block";
				break;
			case "changePassword":
				changePassDiv.style.display = "block";
				profileDiv.style.display = "none";
				orderDiv.style.display = "none";
				break;
		}
	}

	navItems_ep.forEach(navItem => {
		navItem.addEventListener("click", function() {
			handleNavItemClick(navItem);
		});

		var storedTarget = localStorage.getItem("selectedNavItem_ep");
		if (navItem.dataset.target === storedTarget) {
			handleNavItemClick(navItem);
		}
	});

	var navList = document.getElementById("navList");
	if (navList) {
		navList.addEventListener("click", function(event) {
			var target = event.target;
			if (target.tagName === "LI") {
				handleNavItemClick(target);
			}
		});
	}
});



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

function resetStyles() {
	userImg.style.cssText = '';
	dropDown.style.cssText = '';
	dropDown.classList.remove('show');
}

if (dropDown) {
	dropDown.addEventListener('mouseleave', resetStyles);
	document.addEventListener('wheel', resetStyles);

	userImg.addEventListener('mouseenter', () => {
		dropDown.classList.add('show');
		userImg.style.cssText = 'filter: blur(1px);';
		dropDown.style.cssText = 'position: absolute; transform: translate3d(-132px, 5px, 0px); top: 0px; left: 0px; willChange: transform;';
	});

	document.addEventListener('click', (event) => {
		if (!event.target.closest('.user-img') && !event.target.closest('.drop-down')) {
			resetStyles();
		}
	});

	window.addEventListener('resize', function() {
		const isSmallScreen = window.innerWidth < 765;
		if (isSmallScreen) {
			resetStyles();
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
				'.p_sweet-alert',
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
	var emailDisplayElement = document.getElementById("emailDisplay");


	// Wait for the DOM to be ready

	// Get the element with the ID "emailDisplay"
	var emailDisplayElement = document.getElementById("emailDisplay");

	// Get the button element with the class "toggleButton"
	var toggleButton = document.querySelector(".emdisplay");

	// Check if the elements exist
	if (emailDisplayElement && toggleButton) {
		// Save the original email text
		var originalEmail = emailDisplayElement.textContent.trim();


		emailDisplayElement.textContent = "...@gmail.com";

		// Add a click event listener to the button
		toggleButton.addEventListener("click", function() {
			// Toggle between "..." and original email
			if (emailDisplayElement.textContent.trim() === "...@gmail.com") {
				toggleButton.textContent = "None";
				emailDisplayElement.textContent = originalEmail;
			} else {
				emailDisplayElement.textContent = "...@gmail.com";
				toggleButton.textContent = "Display";
			}
		});
	}


	// Kiểm tra xem có thẻ button có class '_btn-ep' hay không
	var btnEp = document.querySelector('._btn-ep');
	var fileInput = document.getElementById('fileInput');
	var avatarImage = document.getElementById('avatarImage');
	var fileNameDisplay = document.getElementById('fileNameDisplay');
	// Nếu tồn tại thẻ button, thêm sự kiện click
	if (btnEp) {
		btnEp.addEventListener('click', function() {
			// Kích thích sự kiện click cho input type file
			fileInput.click();
		});
	}

	if (fileInput && avatarImage && fileNameDisplay) {
		fileInput.addEventListener('change', function() {
			if (fileInput.files && fileInput.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					avatarImage.src = e.target.result;
					fileNameDisplay.innerText = fileInput.files[0].name;
				}
				reader.readAsDataURL(fileInput.files[0]);
			}
		});
	}

	var divElement = document.getElementById("truncateDiv");

	if (divElement) { // Kiểm tra xem phần tử có tồn tại hay không
		var content = divElement.innerText;

		if (content.length > 10) {
			// Rút gọn nội dung nếu độ dài lớn hơn 10 ký tự
			divElement.innerText = content.slice(0, 10) + "...";
		}
	}
	var selectAllCheckbox = document.getElementById('selectAll');
	var itemCheckboxes = document.querySelectorAll('.checkboxItem');
	var hiddenDiv = document.getElementById('hiddenDiv');
	var hiddenbtns = document.querySelectorAll('.hiddenbtn');

	if (selectAllCheckbox) {
		// Thêm sự kiện cho checkbox chọn tất cả
		selectAllCheckbox.addEventListener('change', function() {
			// Đặt trạng thái của các checkbox con dựa trên trạng thái của checkbox chọn tất cả
			itemCheckboxes.forEach(function(checkbox) {
				checkbox.checked = selectAllCheckbox.checked;
				updateRowColor(checkbox);
			});

			// Cập nhật thuộc tính hiển thị của div ẩn dựa trên trạng thái của checkbox
			hiddenDiv.style.display = isAnyCheckboxChecked() ? 'block' : 'none';

			// Cập nhật thuộc tính hiển thị của từng nút ẩn dựa trên trạng thái của checkbox
			hiddenbtns.forEach(function(hiddenbtn) {
				hiddenbtn.style.display = isAnyCheckboxChecked() ? 'none' : '';
			});
		});

		// Thêm sự kiện cho từng checkbox con
		itemCheckboxes.forEach(function(checkbox) {
			checkbox.addEventListener('change', function() {
				updateRowColor(this);

				// Nếu bất kỳ checkbox con nào không được chọn, hủy chọn checkbox chọn tất cả
				if (!this.checked) {
					selectAllCheckbox.checked = false;
				}

				// Cập nhật hiển thị của các nút ẩn dựa trên trạng thái của checkbox
				hiddenbtns.forEach(function(hiddenbtn) {
					hiddenbtn.style.display = isAnyCheckboxChecked() ? 'none' : '';
				});

				// Cập nhật thuộc tính hiển thị của div ẩn dựa trên trạng thái của checkbox
				hiddenDiv.style.display = isAnyCheckboxChecked() ? 'block' : 'none';
			});
		});
	}

	// Hàm cập nhật màu sắc của hàng dựa trên trạng thái của checkbox
	function updateRowColor(checkbox) {
		const parentTr = checkbox.closest('.tr_listuser');
		if (checkbox.checked) {
			parentTr.classList.add('selected-row');
		} else {
			parentTr.classList.remove('selected-row');
		}
	}

	// Hàm kiểm tra xem có ít nhất một checkbox được chọn hay không
	function isAnyCheckboxChecked() {
		return Array.from(itemCheckboxes).some(function(item) {
			return item.checked;
		});
	}
	var s_a_close_div_i = document.querySelector(".s-a-close_div-i");
	var hiddenbtn = document.querySelector(".hiddenbtn");
	var div_background = document.querySelector(".div_background");

	if (hiddenbtn) {
		hiddenbtn.addEventListener("click", function() {
			if (div_background) {
				div_background.style.display = "flex";
			}
		});
	}

	if (div_background) {
		div_background.addEventListener("click", function(event) {
			// Kiểm tra xem sự kiện click có phát sinh từ phần tử con của .s-a hay không
			if (!event.target.closest('.s-a')) {
				div_background.style.display = "none";
			}
		});
	}

	if (s_a_close_div_i) {
		s_a_close_div_i.addEventListener("click", function() {
			if (div_background) {
				div_background.style.display = "none";
			}
		});
	}


});
document.addEventListener("DOMContentLoaded", function() {
	// Lấy tất cả các phần tử có class "formatted-order-date"
	var formattedDateElements = document.querySelectorAll(".formatted-order-date");

	// Lặp qua từng phần tử và định dạng lại ngày giờ
	formattedDateElements.forEach(function(element) {
		var originalDate = element.textContent;
		var formattedDate = new Date(originalDate).toLocaleString("en-US", {
			year: "numeric",
			month: "numeric",
			day: "numeric",
			hour: "numeric",
			minute: "numeric"
		});

		// Gán giá trị định dạng vào phần tử
		element.textContent = formattedDate;
	});
});
function redirectTo(url) {
	window.location.href = url;
}
function deleteSelected(actionUrl) {
	var selectedIds = [];
	var checkboxes = document.querySelectorAll('.checkboxItem:checked');

	checkboxes.forEach(function(checkbox) {
		selectedIds.push(checkbox.value);
	});
	console.log(selectedIds);
	// Tạo một form ảo và thêm input ẩn vào đó
	var form = document.createElement('form');
	form.id = 'virtualForm';
	form.method = 'post';

	// Thay đổi action để trỏ đến link được truyền vào hàm
	form.action = actionUrl;

	selectedIds.forEach(function(id) {
		var hiddenInput = document.createElement('input');
		hiddenInput.type = 'hidden';
		hiddenInput.name = 'selectedIds';
		hiddenInput.value = id;
		form.appendChild(hiddenInput);
	});

	document.body.appendChild(form);

	// Gửi form ảo
	document.getElementById('virtualForm').submit();
}
