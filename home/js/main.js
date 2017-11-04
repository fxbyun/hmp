jQuery(document).ready(function ($) {
    'use strict';


    /*--------- Navigation -------*/

    var $scroll = $(".scroll-top");

    if ($(this).scrollTop() > 300) {
        $scroll.fadeIn(200);
    }

    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $scroll.fadeIn(200);
        } else {
            $scroll.fadeOut(200);
        }
    });

    $scroll.on("click", function (e) {
        e.preventDefault();
        $("body,html").animate({scrollTop: 0}, 500);

    });


    /*---------  Loader and Animations -------*/

    $(window).load(function () {

        $(".loader").delay(800).fadeOut("slow");

        setInterval(function () {
            $(".Shaki").addClass('animated shake');
        }, 1000);


        $('.FadeIn').one('inview', function (event, visible) {
            if (visible) {
                $(this).addClass('animated fadeIn');

            }
        });

        $('.FadeIn2, .FadeIn3,  .FadeIn4, .FadeIn5').one('inview', function (event, visible) {
            if (visible) {
                $(this).addClass('animated fadeIn');

            }
        });

        $('.FadeRight').one('inview', function (event, visible) {
            if (visible) {
                $(this).addClass('animated fadeInRight');

            }
        });

        $('.FadeLeft').one('inview', function (event, visible) {
            if (visible) {
                $(this).addClass('animated fadeInLeft');

            }
        });

        $('.FadeRight2').one('inview', function (event, visible) {
            if (visible) {
                $(this).addClass('animated fadeInRight');

            }
        });


    });


    /*--------- Backstretch -------*/

    $("#main-section").backstretch("img/bg.png");
    $("#cta").backstretch("img/bg2.png");


    $("#accordion").accordion();
    $('.testi-slider').cycle({
        fx: 'none',
        speed: 'slow',
        timeout: 6000,
        next: '#next2',
        prev: '#prev2',
        slideResize: 0

    });


    /*--------- Navbar -------*/

    $(".navbar").onePageNav({

        filter: ':not(.external)',
        currentClass: 'current',
        changeHash: false,
        scrollOffset: 89,
        scrollSpeed: 1200,
        scrollThreshold: 0.5

    });


    /*---------  Popup click -------*/

    $("#pop-contact").on("click", function (e) {
        e.preventDefault();
        $("#overlay_form").fadeIn(1000);
        $("#popi-bg").css({
            "opacity": "0.7"
        });
        $("#popi-bg").fadeIn("slow");
        positionPopup();
    });


    /*---------  Popup Close -------*/

    $("#close2").on("click", function (e) {
        e.preventDefault();
        $("#overlay_form").fadeOut(500);
        $("#popi-bg").fadeOut("slow");

    });


    /*---------  Popup Position -------*/

    function positionPopup() {
        if (!$("#overlay_form").is(':visible')) {
            return;
        }
        $("#overlay_form").css({
            top: ($(window).height() - $('#overlay_form').height()) / 2,
            left: ($(window).width() - $('#overlay_form').width()) / 2,
        });
    }

    $(window).bind('resize', positionPopup);

    /*---------  Email Validation -------*/

    function validateEmail(email) {
        var reg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return reg.test(email);
    }


    $("#contactform").submit(function () {
        return false;
    });


    $("#send").on("click", function () {
        var emailval = $("#email").val();
        var msgval = $("#message").val();
        var msglen = msgval.length;
        var mailvalid = validateEmail(emailval);

        if (mailvalid == false) {
            $("#email").addClass("error");
        }
        else if (mailvalid == true) {
            $("#email").removeClass("error");
        }

        if (msglen < 2) {
            $("#msg").addClass("error");
        }
        else if (msglen >= 2) {
            $("#msg").removeClass("error");
        }

        if (mailvalid == true && msglen >= 2) {
            $("input.submit-contact").replaceWith("<em>sending...</em>");

            $.ajax({
                type: 'POST',
                url: 'mailer.php',
                data: $("#contactform").serialize(),
                success: function (data) {
                    if (data == "true") {
                        $("em").fadeOut("fast", function () {
                            $(this).before("<p> Your Message has been sent.</p>");
                            setTimeout("$.fancybox.close()", 1000);
                        });
                    }
                }
            });
        }
    });

});