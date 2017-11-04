//String.format
//V1 method
String.prototype.format = function () {
    var args = arguments;
    return this.replace(/\{(\d+)\}/g,
        function (m, i) {
            return args[i];
        });
}

//V2 static
String.format = function () {
    if (arguments.length == 0)
        return null;

    var str = arguments[0];
    for (var i = 1; i < arguments.length; i++) {
        var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
        str = str.replace(re, arguments[i]);
    }
    return str;
}
layer.config({
    extend: 'extend/layer.ext.js' //注意，目录是相对layer.js根目录。如果加载多个，则 [a.js, b.js, …]
    //shift: 1, //默认动画风格
    //skin: 'layui-layer-molv' //默认皮肤
});
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
jQuery.ajaxSettings.traditional = true;
jQuery.ajaxSettings.cache = false;
jQuery.each(["getSync", "postSync"], function (i, method) {
    jQuery[method] = function (url, data, callback, type) {
        // shift arguments if data argument was omitted
        if (jQuery.isFunction(data)) {
            type = type || callback;
            callback = data;
            data = undefined;
        }
        return jQuery.ajax({
            url: url,
            type: method,
            dataType: type,
            data: data,
            success: callback,
            async: false
        });
    };
});
jQuery.extend({
    postJSON: function (url, data, callback) {
        return jQuery.post(url, data, callback, "json");
    },
    getSyncJSON: function (url, data, callback) {
        return jQuery.getSync(url, data, callback, "json");
    },
    postSyncJSON: function (url, data, callback) {
        return jQuery.postSync(url, data, callback, "json");
    }
});