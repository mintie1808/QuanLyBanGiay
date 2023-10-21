document.addEventListener("DOMContentLoaded", function() {
    var navItems = document.querySelectorAll(".header__menu.mobile-menu li");

    var currentURL = window.location.href;

    if (currentURL.includes("shop")) {
        localStorage.setItem("selectedNavItem", "Shop");
    } else if (currentURL.includes("about") || currentURL.includes("blog_details")) {
        localStorage.setItem("selectedNavItem", "Blog");
    } else if (currentURL.includes("contact")) {
        localStorage.setItem("selectedNavItem", "Contacts");
    } else {
        localStorage.setItem("selectedNavItem", "Home");
    }

    navItems.forEach(function(navItem) {
        navItem.addEventListener("click", function() {
            var clickedItemText = navItem.textContent.trim();
            if (clickedItemText === "Shop Details" || clickedItemText === "Shopping Cart" || clickedItemText === "Check Out") {
                localStorage.setItem("selectedNavItem", "Shop");
            } else if (clickedItemText === "About Us" || clickedItemText === "Blog Details") {
                localStorage.setItem("selectedNavItem", "Blog");
            } else if (clickedItemText === "Contacts") {
                localStorage.setItem("selectedNavItem", "Contacts");
            } else {
                localStorage.setItem("selectedNavItem", clickedItemText);
            }

            navItems.forEach(function(item) {
                item.classList.remove("active");
            });

            navItem.classList.add("active");
        });
    });

    var selectedNavItem = localStorage.getItem("selectedNavItem");
    if (selectedNavItem) {
        navItems.forEach(function(navItem) {
            if (navItem.textContent.trim() === selectedNavItem) {
                navItem.classList.add("active");
            }
        });
    }
});
