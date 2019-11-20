
        $(document).ready(function(){

            // OWL CAROUSEL - https://owlcarousel2.github.io/OwlCarousel2/
            $('#main-slider .owl-carousel').owlCarousel(
                {
                    items: 1,
                    margin: 0,
                    loop: true,
                    nav: false,
                    navText: ['Back','Next'],
                    dots: false,
                    dotsEach: true,
                    autoplay: true,
                    autoplaySpeed: 500,
                    animateOut: 'fadeOut',
                    animateIn: 'fadeIn',
                }
            );
            $('#owl-1 .owl-carousel').owlCarousel(
                {
                    items: 4,
                    margin: 16,
                    loop: true,
                    stagePadding: 64,
                    nav: true,
                    // navText: ['Back','Next'],
                    navText: ['',''],
                    // navText: ["<img src='myprevimage.png'>","<img src='mynextimage.png'>"],
                    dots: false,
                    dotsEach: true,
                    lazyLoad: false,
                    autoplay: true,
                    autoplaySpeed: 500,
                    navSpeed: 500,
                    autoplayTimeout: 2000,
                    autoplayHoverPause: true,
                }
            );

            $('#owl-2 .owl-carousel').owlCarousel(
                {
                    items: 5,
                    margin: 16,
                    stagePadding: 32,
                    loop: true,
                    autoplay: true,
                    autoplaySpeed: 500,
                    navSpeed: 500,
                    dots: false,
                    dotsEach: true,
                    nav: true,
                    // navText: ['Back','Next'],
                    navText: ['',''],
                    autoplayTimeout: 2000,
                    autoplayHoverPause: false,
                    animateOut: 'slideOutDown',
                    animateIn: 'flipInX',
                }
            );

        });
