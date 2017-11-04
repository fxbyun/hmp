/**
 * Created by Administrator on 2016/8/10.
 */
;$(function ($, window, document, undefined) {
    /*$(document).ready(function(){
     $(".line-paid ul").mousedown(function(e){//e鼠标事件
     $(this).css("cursor","move");//改变鼠标指针的形状

     var offset = $(this).offset();//DIV在页面的位置
     var x = e.pageX - offset.left;//获得鼠标指针离DIV元素左边界的距离
     var y = e.pageY - offset.top;//获得鼠标指针离DIV元素上边界的距离
     $(document).bind("mousemove",function(ev){//绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件

     //$(".line-paid ul").stop();//加上这个之后

     var _x = ev.pageX - x;//获得X轴方向移动的值
     //var _y = ev.pageY - y;//获得Y轴方向移动的值

     // $(".line-paid ul").animate({left:_x+"px",top:0},10);

     $(".line-paid ul").stop(true, false).animate({left:_x+"px",top:0},10);
     });

     });

     $(document).mouseup(function(){
     $(".line-paid ul").css("cursor","default");
     $(this).unbind("mousemove");
     })

     })*/

    function id(obj) {
        return document.getElementById(obj);
    }

    var page;
    var mx;
    var md = false;
    var sh = 0;
    var en = false;
    window.onload = function () {
        page = id("menu").getElementsByClassName("line-paid");
        if (page.length > 0) {
            page[0].style.zIndex = 2;
        }
        for (i = 0; i < page.length; i++) {
            //page[i].innerHTML+="<span class=\"tip\">"+(i+1)+"/"+page.length+"页 拖拽翻页</span>";
            page[i].id = "page" + i;
            page[i].i = i;
            page[i].onmousedown = function (e) {
                if (!en) {
                    if (!e) {
                        e = e || window.event;
                    }
                    ex = e.pageX ? e.pageX : e.x;
                    mx = 0 - ex;
                    this.style.cursor = "move";
                    md = true;
                    if (document.all) {
                        this.setCapture();
                    } else {
                        window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP);
                        //window.captureEvents(e.onmousemove(e)| e.onmouseup(e));
                    }
                }
            }
            page[i].onmousemove = function (e) {

                if (md) {
                    en = true;
                    if (!e) {
                        e = e || window.event;
                    }
                    ex = e.pageX ? e.pageX : e.x;
                    this.style.left = ex + mx + "px";
                    if (this.offsetLeft > 0) {
                        var cu = (this.i == 0) ? page.length - 1 : this.i - 1;
                        page[sh].style.zIndex = 0;
                        page[cu].style.zIndex = 1;
                        this.style.zIndex = 2;
                        sh = cu;
                    }
                    if (this.offsetLeft < 0) {
                        var cu = (this.i == page.length - 1) ? 0 : this.i + 1;
                        page[sh].style.zIndex = 0;
                        page[cu].style.zIndex = 1;
                        this.style.zIndex = 2;
                        sh = cu;
                    }
                }
            }
            page[i].onmouseup = function () {

                this.style.cursor = "default";
                md = false;
                if (this.offsetLeft == 0) {
                    en = false;
                }
                if (document.all) {
                    this.releaseCapture();
                } else {
                    window.releaseEvents(Event.MOUSEMOVE | Event.MOUSEUP);
                }
                flyout(this);
            }
        }
    }
    function flyout(obj) {
        if (obj.offsetLeft < 0) {
            if ((obj.offsetLeft - 1020) > 1020) {
                obj.style.left = obj.offsetLeft - 1020 + "px";
                window.setTimeout("flyout(id('" + obj.id + "'));", 0);
            } else {
                obj.style.left = 0 + "px";
                obj.style.zIndex = 0;
                flyin(id(obj.id));
            }
        }
        if (obj.offsetLeft > 0) {
            if ((obj.offsetLeft + 1020) < 1020) {
                obj.style.left = obj.offsetLeft + 1020 + "px";
                window.setTimeout("flyout(id('" + obj.id + "'));", 0);
            } else {
                obj.style.left = 0 + "px";
                obj.style.zIndex = 0;
                flyin(id(obj.id));
            }
        }
    }

    function flyin(obj) {
        if (obj.offsetLeft < 1020) {
            if ((obj.offsetLeft + 1020) < 0) {
                obj.style.left = obj.offsetLeft + 1020 + "px";
                window.setTimeout("flyin(id('" + obj.id + "'));", 0);
            } else {
                obj.style.left = 0 + "px";
                en = false;
            }
        }
        if (obj.offsetLeft > 0) {
            if ((obj.offsetLeft - 1020) > 0) {
                obj.style.left = obj.offsetLeft - 1020 + "px";
                window.setTimeout("flyin(id('" + obj.id + "'));", 0);
            } else {
                obj.style.left = 1020 + "px";
                en = false;
            }
        }
    }

}(jQuery, window, document));