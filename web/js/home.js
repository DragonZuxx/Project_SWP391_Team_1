

/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
// Contents of home.js
document.addEventListener("DOMContentLoaded", function () {
    var banners = document.querySelectorAll(".banner-container img");
    var currentBannerIndex = 0;
    var interval = 10000;

    function hideAllExceptCurrent() {
        banners.forEach(function (banner, index) {
            if (index !== currentBannerIndex) {
                banner.style.display = "none";
            }
        });
    }

    function showCurrentBanner() {
        banners[currentBannerIndex].style.display = "block";
        hideAllExceptCurrent();
    }

    function nextBanner() {
        banners[currentBannerIndex].style.display = "none";
        currentBannerIndex = (currentBannerIndex + 1) % banners.length;
        showCurrentBanner();
    }

    function prevBanner() {
        banners[currentBannerIndex].style.display = "none";
        currentBannerIndex = (currentBannerIndex - 1 + banners.length) % banners.length;
        showCurrentBanner();
    }

    showCurrentBanner();

    var bannerInterval = setInterval(nextBanner, interval);

    document.querySelector(".next-btn").addEventListener("click", function () {
        clearInterval(bannerInterval);
        nextBanner();
        bannerInterval = setInterval(nextBanner, interval);
    });

    document.querySelector(".prev-btn").addEventListener("click", function () {
        clearInterval(bannerInterval);
        prevBanner();
        bannerInterval = setInterval(nextBanner, interval);
    });
});
$(document).ready(function () {
    $('#newrealease-slider').slick({
        dots: false,
        arrows: false,
        infinite: true,
        speed: 500,
        centerMode: true,
        centerPadding: '60px',
        slidesToShow: 5, // Số slide hiển thị cùng lúc
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 3000, // Tự động chuyển slide sau 3 giây
        responsive: [
            {
                breakpoint: 1200,
                settings: {
                    slidesToShow: 4,
                    slidesToScroll: 1
                }
            },
            {
                breakpoint: 992,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 1
                }
            },
            {
                breakpoint: 768,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1
                }
            },
            {
                breakpoint: 576,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
        ]
    });
});

