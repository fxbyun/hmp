/**
 * slider插件可悬停控制
 */
;$(function ($, window, document, undefined) {

    Slider = function (container, options) {
        /*
         options = {
         auto: true,
         time: 3000,
         event: 'hover' | 'click',
         mode: 'slide | fade',
         controller: $(),
         activeControllerCls: 'className',
         exchangeEnd: $.noop
         }
         */

        "use strict"; //stirct mode not support by IE9-

        if (!container) return;

        var options = options || {},
            currentIndex = 0,
            cls = options.activeControllerCls,
            delay = options.delay,
            isAuto = options.auto,
            controller = options.controller,
            event = options.event,
            interval,
            slidesWrapper = container.children().first(),
            slides = slidesWrapper.children(),
            length = slides.length,
            childWidth = container.width(),
            nowType = "",
            nextType = "",
            totalWidth = childWidth * slides.length;

        function init() {
            var controlItem = controller.children();

            mode();

            event == 'hover' ? controlItem.mouseover(function () {
                stop();
                var index = $(this).index();

                play(index, options.mode);
            }).mouseout(function () {
                isAuto && autoPlay();
            }) : controlItem.click(function () {
                stop();
                var index = $(this).index();

                play(index, options.mode);
                isAuto && autoPlay();
            });

            isAuto && autoPlay();
        }

        //animate mode
        function mode() {
            var wrapper = container.children().first();

            options.mode == 'slide' ? wrapper.width(totalWidth) : wrapper.children().css({
                'position': 'absolute',
                'left': 0,
                'top': 0
            })
                .first().siblings().hide();
        }

        //auto play
        function autoPlay() {
            interval = setInterval(function () {
                triggerPlay(currentIndex);
            }, options.time);
        }

        //trigger play
        function triggerPlay(cIndex) {
            var index;

            (cIndex == length - 1) ? index = 0 : index = cIndex + 1;
            play(index, options.mode);
        }

        //play
        function play(index, mode) {
            slidesWrapper.stop(true, true);
            slides.stop(true, true);

            mode == 'slide' ? (function () {
                if (index > currentIndex) {
                    slidesWrapper.animate({
                        left: '-=' + Math.abs(index - currentIndex) * childWidth + 'px'
                    }, delay);
                } else if (index < currentIndex) {
                    slidesWrapper.animate({
                        left: '+=' + Math.abs(index - currentIndex) * childWidth + 'px'
                    }, delay);
                } else {
                    return;
                }
            })() : (function () {
                if (slidesWrapper.children(':visible').index() == index) return;
                slidesWrapper.children().fadeOut(delay).eq(index).fadeIn(delay);
            })();

            try {
                controller.children('.' + cls).removeClass(cls);
                controller.children().eq(index).addClass(cls);
                nowType = controller.children().eq(index).attr("types");
                if (index + 1 == length) {
                    nextType = controller.children().eq(0).attr("types");
                } else {
                    nextType = controller.children().eq(index).attr("types");
                }
            } catch (e) {
            }

            currentIndex = index;
            //TODO 临时注释 作为屏蔽 视频处理 避免 图片二次 点击到下一张图
            // if (nowType == 'Pic') {
            //     setTimeout(function () {
            //         videoSlider.next();
            //     }, options.time)
            // }
            options.exchangeEnd && typeof options.exchangeEnd == 'function' && options.exchangeEnd.call(this, currentIndex);
        }

        //stop
        function stop() {
            clearInterval(interval);
        }

        //prev frame
        function prev() {
            stop();

            currentIndex == 0 ? triggerPlay(length - 2) : triggerPlay(currentIndex - 2);

            isAuto && autoPlay();
        }

        //next frame
        function next() {
            stop();
            currentIndex == length - 1 ? triggerPlay(-1) : triggerPlay(currentIndex);
            isAuto && autoPlay();
        }

        //init
        init();

        function getNextTypeFst() {

            if (nextType == "") {
                if (length == 0 || length == 1) {
                    nextType = nowType;
                } else {
                    nextType = controller.children().eq(currentIndex + 1).attr("types");
                }
            }
            return nextType;
        }

        //expose the Slider API
        return {
            prev: function () {
                prev();
            },
            next: function (msg) {
                if (msg) {
                    console.info(msg)
                }
                next();
            },
            getNowStatus: function () {
                return {
                    nowId: currentIndex,
                    allSize: length,
                    nowType: nowType,
                    nextType: getNextTypeFst()
                };
            },
            setNowType: function (type) {
                nowType = type;
            }
        }
    };

}(jQuery, window, document));