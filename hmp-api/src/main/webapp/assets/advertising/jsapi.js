(function () {
    var R, B, E, J;

    function D(b, e) {
        if (!e)return b;
        for (var c in e)Da.call(e, c) && (b[c] = e[c]);
        return b
    }

    function Ea(b) {
        if (!b)return "";
        var b = b.toString(), e, c, g, d, f, h = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43,
            44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1];
        d = b.length;
        g = 0;
        for (f = ""; g < d;) {
            do e = h[b.charCodeAt(g++) & 255]; while (g < d && -1 == e);
            if (-1 == e)break;
            do c = h[b.charCodeAt(g++) & 255]; while (g < d && -1 == c);
            if (-1 == c)break;
            f += String.fromCharCode(e << 2 | (c & 48) >> 4);
            do {
                e = b.charCodeAt(g++) & 255;
                if (61 == e)return f;
                e = h[e]
            } while (g < d && -1 == e);
            if (-1 == e)break;
            f += String.fromCharCode((c & 15) << 4 | (e & 60) >> 2);
            do {
                c = b.charCodeAt(g++) & 255;
                if (61 == c)return f;
                c = h[c]
            } while (g < d && -1 == c);
            if (-1 == c)break;
            f += String.fromCharCode((e & 3) << 6 | c)
        }
        return f
    }

    function N(b,
               e) {
        for (var c = [], g = 0, d, f = "", h = 0; 256 > h; h++)c[h] = h;
        for (h = 0; 256 > h; h++)g = (g + c[h] + b.charCodeAt(h % b.length)) % 256, d = c[h], c[h] = c[g], c[g] = d;
        for (var j = g = h = 0; j < e.length; j++)h = (h + 1) % 256, g = (g + c[h]) % 256, d = c[h], c[h] = c[g], c[g] = d, f += String.fromCharCode(e.charCodeAt(j) ^ c[(c[h] + c[g]) % 256]);
        return f
    }

    function O(b, e) {
        for (var c = [], g = 0; g < b.length; g++) {
            for (var d = 0, d = "a" <= b[g] && "z" >= b[g] ? b[g].charCodeAt(0) - 97 : b[g] - 0 + 26, f = 0; 36 > f; f++)if (e[f] == d) {
                d = f;
                break
            }
            c[g] = 25 < d ? d - 26 : String.fromCharCode(d + 97)
        }
        return c.join("")
    }

    function Y(b) {
        for (var b =
            parseInt(b) || 3, b = isNaN(b) ? 3 : b, e = Z.length, c = ""; 0 <= b;)c += Z[Math.floor(Math.random() * e)], b--;
        return c
    }

    function Fa(b) {
        function e(b, e) {
            return b << e | b >>> 32 - e
        }

        function c(b) {
            var e = "", g, l;
            for (g = 7; 0 <= g; g--)l = b >>> 4 * g & 15, e += l.toString(16);
            return e
        }

        var g, d, f = Array(80), h = 1732584193, j = 4023233417, x = 2562383102, i = 271733878, o = 3285377520, p, w, u, r, s, b = function (b) {
            for (var b = b.replace(/\r\n/g, "\n"), e = "", c = 0; c < b.length; c++) {
                var g = b.charCodeAt(c);
                128 > g ? e += String.fromCharCode(g) : (127 < g && 2048 > g ? e += String.fromCharCode(g >> 6 | 192) :
                    (e += String.fromCharCode(g >> 12 | 224), e += String.fromCharCode(g >> 6 & 63 | 128)), e += String.fromCharCode(g & 63 | 128))
            }
            return e
        }(b);
        p = b.length;
        var t = [];
        for (g = 0; g < p - 3; g += 4)d = b.charCodeAt(g) << 24 | b.charCodeAt(g + 1) << 16 | b.charCodeAt(g + 2) << 8 | b.charCodeAt(g + 3), t.push(d);
        switch (p % 4) {
            case 0:
                g = 2147483648;
                break;
            case 1:
                g = b.charCodeAt(p - 1) << 24 | 8388608;
                break;
            case 2:
                g = b.charCodeAt(p - 2) << 24 | b.charCodeAt(p - 1) << 16 | 32768;
                break;
            case 3:
                g = b.charCodeAt(p - 3) << 24 | b.charCodeAt(p - 2) << 16 | b.charCodeAt(p - 1) << 8 | 128
        }
        for (t.push(g); 14 != t.length %
        16;)t.push(0);
        t.push(p >>> 29);
        t.push(p << 3 & 4294967295);
        for (b = 0; b < t.length; b += 16) {
            for (g = 0; 16 > g; g++)f[g] = t[b + g];
            for (g = 16; 79 >= g; g++)f[g] = e(f[g - 3] ^ f[g - 8] ^ f[g - 14] ^ f[g - 16], 1);
            d = h;
            p = j;
            w = x;
            u = i;
            r = o;
            for (g = 0; 19 >= g; g++)s = e(d, 5) + (p & w | ~p & u) + r + f[g] + 1518500249 & 4294967295, r = u, u = w, w = e(p, 30), p = d, d = s;
            for (g = 20; 39 >= g; g++)s = e(d, 5) + (p ^ w ^ u) + r + f[g] + 1859775393 & 4294967295, r = u, u = w, w = e(p, 30), p = d, d = s;
            for (g = 40; 59 >= g; g++)s = e(d, 5) + (p & w | p & u | w & u) + r + f[g] + 2400959708 & 4294967295, r = u, u = w, w = e(p, 30), p = d, d = s;
            for (g = 60; 79 >= g; g++)s = e(d, 5) + (p ^ w ^
                u) + r + f[g] + 3395469782 & 4294967295, r = u, u = w, w = e(p, 30), p = d, d = s;
            h = h + d & 4294967295;
            j = j + p & 4294967295;
            x = x + w & 4294967295;
            i = i + u & 4294967295;
            o = o + r & 4294967295
        }
        s = c(h) + c(j) + c(x) + c(i) + c(o);
        return s.toLowerCase()
    }

    function F(b, e) {
        if ("js" == e) {
            var c = document.createElement("script");
            c.setAttribute("type", "text/javascript");
            c.setAttribute("src", b)
        } else"css" == e && (c = document.createElement("link"), c.setAttribute("rel", "stylesheet"), c.setAttribute("type", "text/css"), c.setAttribute("href", b));
        "undefined" != typeof c && document.getElementsByTagName("head")[0].appendChild(c)
    }

    function $() {
        return f.isAndroid ? f.isAndroid4 ? "adr4" : "adr" : f.isIPHONE ? "iph" : f.isIPAD ? "ipa" : f.isIPOD ? "ipo" : "oth"
    }

    function G(b) {
        return f.isIPAD && 0 <= window.location.href.indexOf("v.youku.com") ? "x-player" : 200 >= b ? "x-player x-player-200" : 300 >= b ? "x-player x-player-200-300" : 660 >= b ? "x-player x-player-300-660" : 800 >= b ? "x-player x-player-660-800" : "x-player"
    }

    var d = "2016/04/1312:11:20", d = "2016/04/1614:45:00", d = "2016/04/1722:44:04", d = "2016/04/1722:51:55", d = "2016/04/1810:51:46", d = "2016/04/1810:59:40", d = "2016/04/1813:25:57",
        d = "2016/04/1813:36:51", d = "2016/04/1813:40:25", d = "2016/04/1814:04:32", d = "2016/04/1814:24:39", d = "2016/04/1814:28:16", d = "2016/04/1814:29:20", d = "2016/04/1814:30:12", d = "2016/04/1814:33:06", d = "2016/04/1815:17:41", d = "2016/04/1815:19:28", d = "2016/04/1815:41:29", d = "2016/04/1815:43:12", d = "2016/04/1815:48:47", d = "2016/04/1815:49:40", d = "2016/04/1815:51:42", d = "2016/04/1815:52:36", d = "2016/04/1815:58:04", d = "2016/04/1816:04:16", d = "2016/04/1816:05:09", d = "2016/04/1816:07:13", d = "2016/04/1818:18:07", d = "2016/04/1818:21:33",
        d = "2016/04/1819:09:26", d = "2016/04/1819:14:58", d = "2016/04/1819:37:45", d = "2016/04/1819:39:02", d = "2016/04/1819:43:01", d = "2016/04/1819:44:42", d = "2016/04/1819:47:23", d = "2016/04/1910:03:44", d = "2016/04/1910:14:24", d = "2016/04/1910:23:55", d = "2016/04/1910:25:46", d = "2016/04/1910:28:16", d = "2016/04/1910:29:25", d = "2016/04/1910:37:36", d = "2016/04/1910:47:00", d = "2016/04/1911:00:21", d = "2016/04/1911:01:20", d = "2016/04/1911:14:55", d = "2016/04/1911:23:14", d = "2016/04/1911:28:10", d = "2016/04/1911:28:49", d = "2016/04/1911:29:42",
        d = "2016/04/1911:33:44", d = "2016/04/1911:34:50", d = "2016/04/1911:35:24", d = "2016/04/1911:41:01", d = "2016/04/1913:13:17", d = "2016/04/1913:16:00", d = "2016/04/1913:16:37", d = "2016/04/1913:18:27", d = "2016/04/1913:24:30", d = "2016/04/1913:30:57", d = "2016/04/1913:32:47", d = "2016/04/1913:39:43", d = "2016/04/1913:40:31", d = "2016/04/1913:43:25", d = "2016/04/1913:56:15", d = "2016/04/1913:57:10", d = "2016/04/1913:57:36", d = "2016/04/1914:00:08", d = "2016/04/1914:12:59", d = "2016/04/1914:28:56", d = "2016/04/1914:29:54", d = "2016/04/1914:40:27",
        d = "2016/04/1914:42:16", d = "2016/04/1914:43:52", d = "2016/04/1914:55:18", d = "2016/04/1914:56:18", d = "2016/04/1915:10:43", d = "2016/04/1915:35:24", d = "2016/04/1915:35:50", d = "2016/04/1915:40:24", d = "2016/04/1915:42:19", d = "2016/04/1915:42:37", d = "2016/04/1917:20:38", d = "2016/04/1917:22:45", d = "2016/04/1917:40:56", d = "2016/04/1917:53:08", d = "2016/04/1917:56:30", d = "2016/04/1917:59:18", d = "2016/04/1918:00:14", d = "2016/04/1918:05:02", d = "2016/04/1918:07:14", d = "2016/04/1918:08:22", d = "2016/04/1918:19:43", d = "2016/04/2010:56:01",
        d = "2016/04/2010:56:59", d = "2016/04/2015:05:30", d = "2016/04/2015:08:13", d = "2016/04/2015:11:45", d = "2016/04/2015:12:33", d = "2016/04/2015:13:14", d = "2016/04/2015:18:01", d = "2016/04/2015:20:05", d = "2016/04/2015:23:15", d = "2016/04/2015:25:39", d = "2016/04/2015:26:23", d = "2016/04/2015:26:54", d = "2016/04/2016:00:42", d = "2016/04/2016:06:18", d = "2016/04/2016:10:48", d = "2016/04/2016:14:05", d = "2016/04/2016:15:47", d = "2016/04/2016:18:25", d = "2016/04/2016:23:48", d = "2016/04/2016:24:22", d = "2016/04/2016:26:00", d = "2016/04/2016:29:37",
        d = "2016/04/2016:31:31", d = "2016/04/2017:34:49", d = "2016/04/2017:36:41", d = "2016/04/2017:38:26", d = "2016/04/2017:39:14", d = "2016/04/2017:53:08", d = "2016/04/2017:53:54", d = "2016/04/2017:55:09", d = "2016/04/2017:56:28", d = "2016/04/2017:56:51", d = "2016/04/2018:05:19", d = "2016/04/2018:06:39", d = "2016/04/2018:07:47", d = "2016/04/2018:08:18", d = "2016/04/2018:08:53", d = "2016/04/2018:10:00", d = "2016/04/2018:10:55", d = "2016/04/2018:11:32", d = "2016/04/2018:12:47", d = "2016/04/2018:19:25", d = "2016/04/2018:22:16", d = "2016/04/2109:49:14",
        d = "2016/04/2109:49:36", d = "2016/04/2109:56:41", d = "2016/04/2110:33:38", d = "2016/04/2110:48:02", d = "2016/04/2110:50:20", d = "2016/04/2111:19:31", d = "2016/04/2111:21:02", d = "2016/04/2111:29:23", d = "2016/04/2111:31:19", d = "2016/04/2111:34:12", d = "2016/04/2111:36:57", d = "2016/04/2111:37:53", d = "2016/04/2111:38:52", d = "2016/04/2111:39:33", d = "2016/04/2111:47:40", d = "2016/04/2111:59:12", d = "2016/04/2112:00:03", d = "2016/04/2112:04:07", d = "2016/04/2112:05:29", d = "2016/04/2112:06:45", d = "2016/04/2113:39:27", d = "2016/04/2113:41:16",
        d = "2016/04/2113:57:22", d = "2016/04/2113:58:01", d = "2016/04/2116:28:30", d = "2016/04/2117:56:04", d = "2016/04/2211:51:28", d = "2016/04/2211:55:46", d = "2016/04/2212:04:54", d = "2016/04/2215:53:41", d = "2016/04/2215:55:05", d = "2016/04/2215:55:20", d = "2016/04/2215:58:32", d = "2016/04/2216:16:37", d = "2016/04/2216:19:42", d = "2016/04/2216:20:33", d = "2016/04/2217:07:21", d = "2016/04/2217:07:51", d = "2016/04/2217:11:17", d = "2016/04/2218:04:45", d = "2016/04/2218:09:37", d = "2016/04/2218:13:46", d = "2016/04/2218:47:12", d = "2016/04/2218:48:54",
        d = "2016/04/2218:50:46", d = "2016/04/2218:55:54", d = "2016/04/2218:57:57", d = "2016/04/2219:01:52", d = "2016/04/2219:02:47", d = "2016/04/2219:10:06", d = "2016/04/2219:12:08", d = "2016/04/2219:20:03", d = "2016/04/2510:03:02", d = "2016/04/2510:24:50", d = "2016/04/2510:26:43", d = "2016/04/2510:35:54", d = "2016/04/2510:44:12", d = "2016/04/2510:45:19", d = "2016/04/2510:48:58", d = "2016/04/2510:52:30", d = "2016/04/2511:05:55", d = "2016/04/2511:14:31", d = "2016/04/2511:23:22", d = "2016/04/2511:30:35", d = "2016/04/2511:43:55", d = "2016/04/2511:49:52",
        d = "2016/04/2511:50:13", d = "2016/04/2513:14:10", d = "2016/04/2513:20:24", d = "2016/04/2513:21:16", d = "2016/04/2513:22:44", d = "2016/04/2513:27:39", d = "2016/04/2513:28:42", d = "2016/04/2513:39:22", d = "2016/04/2513:47:49", d = "2016/04/2513:50:39", d = "2016/04/2513:54:36", d = "2016/04/2513:57:05", d = "2016/04/2513:59:20", d = "2016/04/2514:03:16", d = "2016/04/2514:05:36", d = "2016/04/2514:08:49", d = "2016/04/2514:09:56", d = "2016/04/2514:10:46", d = "2016/04/2514:11:09", d = "2016/04/2514:11:40", d = "2016/04/2514:26:03", d = "2016/04/2514:28:27",
        d = "2016/04/2514:33:19", d = "2016/04/2515:29:08", d = "2016/04/2516:00:59", d = "2016/04/2516:04:13", d = "2016/04/2516:05:21", d = "2016/04/2516:05:56", d = "2016/04/2516:13:25", d = "2016/04/2516:22:06", d = "2016/04/2516:23:44", d = "2016/04/2516:24:45", d = "2016/04/2516:25:28", d = "2016/04/2516:26:42", d = "2016/04/2516:27:32", d = "2016/04/2517:13:39", d = "2016/04/2519:08:44", d = "2016/04/2611:03:54", d = "2016/04/2611:21:14", d = "2016/04/2611:22:04", d = "2016/04/2611:32:51", d = "2016/04/2611:34:53", d = "2016/04/2611:37:31", d = "2016/04/2611:37:56",
        d = "2016/04/2611:45:02", d = "2016/04/2611:50:08", d = "2016/04/2612:04:36", d = "2016/04/2612:19:20", d = "2016/04/2613:33:23", d = "2016/04/2616:43:05", d = "2016/04/2616:47:59", d = "2016/04/2616:50:34", d = "2016/04/2616:57:31", d = "2016/04/2617:03:37", d = "2016/04/2617:03:56", d = "2016/04/2617:06:28", d = "2016/04/2617:08:27", d = "2016/04/2617:11:57", d = "2016/04/2617:14:40", d = "2016/04/2617:20:14", d = "2016/04/2617:38:10", d = "2016/04/2617:39:05", d = "2016/04/2617:43:48", d = "2016/04/2617:47:14", d = "2016/04/2617:50:09", d = "2016/04/2617:51:19",
        d = "2016/04/2617:56:25", d = "2016/04/2618:04:26", d = "2016/04/2618:27:17", d = "2016/04/2618:31:04", d = "2016/04/2618:31:56", d = "2016/04/2618:33:13", d = "2016/04/2618:34:50", d = "2016/04/2618:35:37", d = "2016/04/2618:36:33", d = "2016/04/2618:37:43", d = "2016/04/2618:38:36", d = "2016/04/2618:39:57", d = "2016/04/2618:41:04", d = "2016/04/2618:41:27", d = "2016/04/2618:42:55", d = "2016/04/2618:48:41", d = "2016/04/2619:00:45", d = "2016/04/2619:15:19", d = "2016/04/2619:16:43", d = "2016/04/2619:26:14", d = "2016/04/2619:27:01", d = "2016/04/2619:28:36",
        d = "2016/04/2619:30:33", d = "2016/04/2619:31:54", d = "2016/04/2710:02:06", d = "2016/04/2710:15:49", d = "2016/04/2710:38:33", d = "2016/04/2710:49:30", d = "2016/04/2711:02:51", d = "2016/04/2711:19:35", d = "2016/04/2711:26:17", d = "2016/04/2711:30:34", d = "2016/04/2711:34:48", d = "2016/04/2711:39:39", d = "2016/04/2711:40:59", d = "2016/04/2711:51:03", d = "2016/04/2713:41:26", d = "2016/04/2714:13:52", d = "2016/04/2717:12:00", d = "2016/04/2717:18:26", d = "2016/04/2717:41:20", d = "2016/04/2717:51:54", d = "2016/04/2815:15:40", d = "2016/04/2918:08:54",
        d = "2016/04/2918:13:45", d = "2016/04/2918:20:35", d = "2016/05/0415:01:32", d = "2016/05/0416:06:02", d = "2016/05/0416:18:57", d = "2016/05/0417:12:43", d = "2016/05/0417:16:17", d = "2016/05/0417:29:04", d = "2016/05/0417:32:28", d = "2016/05/0418:38:16", d = "2016/05/1318:19:37", d = "2016/05/1318:32:09", d = "2016/05/1318:33:26", d = "2016/05/1318:34:17", d = "2016/05/1318:35:02", d = "2016/05/1318:39:12", d = "2016/05/1318:57:48", d = "2016/05/1610:10:13", d = "2016/05/1610:16:04", d = "2016/05/1610:16:55", d = "2016/05/1610:18:13", d = "2016/05/1610:18:44",
        d = "2016/05/1610:18:54", d = "2016/05/1610:19:21", d = "2016/05/1610:19:50", d = "2016/05/1615:18:57", d = "2016/05/1617:26:35", d = "2016/05/1617:27:32", d = "2016/05/1617:28:42", d = "2016/05/1617:29:01", d = "2016/05/1617:33:04", d = "2016/05/1617:33:31", d = "2016/05/1617:34:23", d = "2016/05/1617:34:43", d = "2016/05/1617:36:15", d = "2016/05/1617:37:53", d = "2016/05/1617:44:54", d = "2016/05/1617:45:29", d = "2016/05/1617:45:51", d = "2016/05/1617:47:03", d = "2016/05/1617:48:44", d = "2016/05/1711:03:58", d = "2016/05/1711:14:23", d = "2016/05/1711:16:42",
        d = "2016/05/1711:19:20", d = "2016/05/1711:26:30", d = "2016/05/1711:29:30", d = "2016/05/1711:38:33", d = "2016/05/1711:42:44", d = "2016/05/1711:45:17", d = "2016/05/1711:57:29", d = "2016/05/1711:58:33", d = "2016/05/1713:23:28", d = "2016/05/1714:30:06", d = "2016/05/1715:42:43", d = "2016/05/1716:21:12", d = "2016/05/1716:29:17", d = "2016/05/1716:30:22", d = "2016/05/1716:32:46", d = "2016/05/1716:43:59", d = "2016/05/1716:47:21", d = "2016/05/1717:32:13", d = "2016/05/1717:33:17", d = "2016/05/1810:35:52", d = "2016/05/1811:38:16", d = "2016/05/1816:04:46",
        d = "2016/05/1816:16:12", d = "2016/05/1816:16:34", d = "2016/05/1816:22:16", d = "2016/05/1817:13:51", d = "2016/05/1817:22:30", d = "2016/05/1817:35:47", d = "2016/05/1817:39:02", d = "2016/05/1817:41:20", d = "2016/05/1817:42:04", d = "2016/05/1817:43:12", d = "2016/05/1817:46:09", d = "2016/05/1817:53:15", d = "2016/05/1817:55:19", d = "2016/05/1817:58:10", d = "2016/05/1818:22:08", d = "2016/05/1818:31:06", d = "2016/05/1818:35:07", d = "2016/05/1818:40:40", d = "2016/05/1818:42:03", d = "2016/05/1818:43:01", d = "2016/05/1818:45:40", d = "2016/05/1911:00:32",
        d = "2016/05/1911:15:06", d = "2016/05/1911:16:25", d = "2016/05/1911:19:00", d = "2016/05/1911:20:10", d = "2016/05/1911:21:14", d = "2016/05/1911:23:49", d = "2016/05/1913:30:58", d = "2016/05/1913:32:02", d = "2016/05/1913:42:02", d = "2016/05/1915:15:22", d = "2016/05/1915:18:21", d = "2016/05/1915:28:31", d = "2016/05/1915:32:13", d = "2016/05/1915:50:29", d = "2016/05/1917:29:37", d = "2016/05/1917:43:26", d = "2016/05/2310:46:05", d = "2016/05/2310:47:17", d = "2016/05/2310:48:39", d = "2016/05/2310:49:17", d = "2016/05/2310:49:57", d = "2016/05/2310:52:16",
        d = "2016/05/2310:52:55", d = "2016/05/2311:01:02", d = "2016/05/2311:03:08", d = "2016/05/2311:03:48", d = "2016/05/2311:04:59", d = "2016/05/2311:05:40", d = "2016/05/2311:08:14", d = "2016/05/2311:15:51", d = "2016/05/2311:18:11", d = "2016/05/2311:23:07", d = "2016/05/2311:23:46", d = "2016/05/2311:39:58", d = "2016/05/2311:46:48", d = "2016/05/2311:50:51", d = "2016/05/2311:52:40", d = "2016/05/2311:54:55", d = "2016/05/2311:57:13", d = "2016/05/2311:59:18", d = "2016/05/2312:07:36", d = "2016/05/2312:08:20", d = "2016/05/2312:08:58", d = "2016/05/2312:10:08",
        d = "2016/05/2312:14:38", d = "2016/05/2312:25:33", d = "2016/05/2316:13:22", d = "2016/05/2414:29:33", d = "2016/05/2414:32:29", d = "2016/05/2414:33:29", d = "2016/05/2414:39:11", d = "2016/05/2414:42:32", d = "2016/05/2415:02:57", d = "2016/05/2415:04:24", d = "2016/05/2415:07:31", d = "2016/05/2415:11:19", d = "2016/05/2415:13:28", d = "2016/05/2415:15:03", d = "2016/05/2415:15:25", d = "2016/05/2415:46:59", d = "2016/05/2416:21:15", d = "2016/05/2616:46:35", d = "2016/05/3117:25:34", d = "2016/05/3117:33:19", d = "2016/05/3117:55:04", d = "2016/06/0118:06:20",
        d = "2016/06/0118:07:30", d = "2016/06/0118:09:01", d = "2016/06/0118:16:44", d = "2016/06/0118:28:23", d = "2016/06/0118:31:33", d = "2016/06/0118:31:58", d = "2016/06/0118:32:16", d = "2016/06/0118:44:34", d = "2016/06/0118:46:41", d = "2016/06/0118:47:17", d = "2016/06/0118:48:39", d = "2016/06/0118:49:51", d = "2016/06/0119:03:54", d = "2016/06/0315:42:50", d = "2016/06/0715:55:05", d = "2016/06/0716:01:26", d = "2016/06/0716:10:37", d = "2016/06/0716:11:40", d = "2016/06/1310:18:23", d = "2016/06/1310:21:21", d = "2016/06/1310:28:08", d = "2016/06/1310:33:52",
        d = "2016/06/1310:35:48", d = "2016/06/1310:40:37", d = "2016/06/1310:47:56", d = "2016/06/1311:00:47", d = "2016/06/1311:02:01", d = "2016/06/1311:10:01", d = "2016/06/1311:14:20", d = "2016/06/1311:15:16", d = "2016/06/1311:18:38", d = "2016/06/1311:43:53", d = "2016/06/1311:49:50", d = "2016/06/1311:50:24", d = "2016/06/1311:50:57", d = "2016/06/1311:51:57", d = "2016/06/1414:57:29", d = "2016/06/1415:19:37", d = "2016/06/1415:25:11", d = "2016/06/1415:27:41", d = "2016/06/1415:29:30", d = "2016/06/1415:30:11", d = "2016/06/1415:42:29", d = "2016/06/1415:48:36",
        d = "2016/06/1416:08:33", d = "2016/06/1416:09:34", d = "2016/06/1416:14:36", d = "2016/06/1416:27:47", d = "2016/06/1416:30:17", d = "2016/06/1416:30:51", d = "2016/06/1416:52:23", d = "2016/06/1416:53:55", d = "2016/06/1416:56:24", d = "2016/06/1417:08:50", d = "2016/06/1418:49:25", d = "2016/06/1510:26:10", d = "2016/06/1510:37:38", d = "2016/06/1510:38:15", d = "2016/06/1510:38:35", d = "2016/06/1510:39:11", d = "2016/06/1510:40:31", d = "2016/06/1510:41:03", d = "2016/06/1510:57:00", d = "2016/06/1511:10:50", d = "2016/06/1511:13:36", d = "2016/06/1511:40:41",
        d = "2016/06/1511:53:48", d = "2016/06/1513:46:27", d = "2016/06/1513:49:59", d = "2016/06/1513:51:00", d = "2016/06/1513:54:29", d = "2016/06/1513:55:43", d = "2016/06/1513:58:32", d = "2016/06/1514:00:59", d = "2016/06/1514:03:59", d = "2016/06/1514:09:34", d = "2016/06/1514:16:47", d = "2016/06/1514:21:09", d = "2016/06/1514:29:59", d = "2016/06/1514:31:02", d = "2016/06/1514:37:37", d = "2016/06/1514:42:16", d = "2016/06/1514:43:29", d = "2016/06/1514:46:31", d = "2016/06/1514:48:18", d = "2016/06/1514:49:33", d = "2016/06/1514:55:31", d = "2016/06/1515:20:49",
        d = "2016/06/1515:27:25", d = "2016/06/1515:32:29", d = "2016/06/1515:35:25", d = "2016/06/1515:37:38", d = "2016/06/1515:37:50", d = "2016/06/1516:04:55", d = "2016/06/1517:56:56", d = "2016/06/2015:53:35", d = "2016/06/2015:54:07", d = "2016/06/2015:55:49", d = "2016/06/2015:56:32", d = "2016/06/2016:01:43", d = "2016/06/2017:28:32", d = "2016/06/2114:36:18", d = "2016/06/2115:08:20", d = "2016/06/2115:12:59", d = "2016/06/2211:42:35", d = "2016/06/2211:43:47", d = "2016/06/2211:45:54", d = "2016/06/2213:15:39", d = "2016/06/2812:47:00", d = "2016/06/2813:30:30",
        d = "2016/06/2813:33:53", d = "2016/06/2813:43:47", d = "2016/06/2913:29:48", d = "2016/06/2913:40:55", d = "2016/06/2913:48:10", d = "2016/06/3010:19:53", d = "2016/06/3010:59:53", d = "2016/06/3011:01:38", d = "2016/06/3011:02:51", d = "2016/07/0114:23:20", d = "2016/07/0114:24:55", d = "2016/07/0114:31:45", d = "2016/07/0114:32:47", d = "2016/07/0114:39:24", d = "2016/07/0616:32:25", d = "2016/07/0616:36:42", d = "2016/07/0717:04:52", d = "2016/07/0811:16:56", d = "2016/07/0811:18:06", d = "2016/07/0811:20:43", d = "2016/07/1310:49:10", d = "2016/07/1310:51:56",
        d = "2016/07/1310:52:58", d = "2016/07/1310:55:57", d = "2016/07/1310:57:25", d = "2016/07/1311:00:49", d = "2016/07/1311:12:15", d = "2016/07/1311:15:17", d = "2016/07/1311:15:33", d = "2016/07/1311:16:19", d = "2016/07/1311:19:13", d = "2016/07/1311:19:38", d = "2016/07/1311:27:08", d = "2016/07/1311:27:40", d = "2016/07/1311:29:00", d = "2016/07/1315:53:19", d = "2016/07/1315:56:17", d = "2016/07/1315:57:12", d = "2016/07/1315:59:38", d = "2016/07/1316:01:24", d = "2016/07/1317:06:29", d = "2016/07/1317:07:33", d = "2016/07/1317:09:26", d = "2016/07/1317:10:18",
        d = "2016/07/1317:12:33", d = "2016/07/1317:13:32", d = "2016/07/1317:15:56", d = "2016/07/1317:18:31", d = "2016/07/1317:23:23", d = "2016/07/1317:25:21", d = "2016/07/1317:29:34", d = "2016/07/1317:32:21", d = "2016/07/1317:33:20", d = "2016/07/1317:33:42", d = "2016/07/1510:46:05", d = "2016/07/1510:48:40", d = "2016/07/1510:50:02", d = "2016/07/1511:42:39", d = "2016/07/1511:51:04", d = "2016/07/1810:21:08", v = window.console;
    v || (v = {
        log: function () {
        }
    });
    var h = {
        log: function (b) {
            v.log(b)
        }
    }, c = {}, s = {}, f = {
        playerType: "", userCache: {a1: "4", a2: "1"}, playerState: {
            PLAYER_STATE_INIT: "PLAYER_STATE_INIT",
            PLAYER_STATE_READY: "PLAYER_STATE_READY",
            PLAYER_STATE_AD: "PLAYER_STATE_AD",
            PLAYER_STATE_PLAYING: "PLAYER_STATE_PLAYING",
            PLAYER_STATE_END: "PLAYER_STATE_END",
            PLAYER_STATE_ERROR: "PLAYER_STATE_ERROR"
        }, playerCurrentState: "PLAYER_STATE_INIT"
    }, aa = function () {
        this._player = document.getElementById(f.playerId)
    };
    aa.prototype = {
        resize: function (b, e) {
            this._player.style.width = b + "px";
            this._player.style.height = e + "px"
        }, currentTime: function () {
            var b = this._player.getPlayerState().split("|");
            return 3 <= b.length ? b[2] : -1
        }, totalTime: function () {
            var b =
                this._player.getPlayerState().split("|");
            return 4 <= b.length ? b[3] : -1
        }, playVideo: function () {
            this._player.pauseVideo(!1)
        }, pauseVideo: function () {
            this._player.pauseVideo(!0)
        }, seekTo: function (b) {
            this._player.nsseek(b)
        }, playVideoById: function (b) {
            this._player.playVideoByID(b)
        }, hideControls: function () {
            this._player.showControlBar(!1)
        }, showControls: function () {
            this._player.showControlBar(!0)
        }
    };
    var ba = function (b) {
        this._handler = {};
        var e = this;
        y.custom = (new Date).getTime();
        f.sendPlayServiceQuality("custom", 0, 0);
        s.jsonp({
            url: "https://api.youku.com/players/custom.json", data: b, time: 2E3, success: function (b) {
                var c = (new Date).getTime() - y.custom;
                f.sendPlayServiceQuality("custom", 200, c);
                e.parse(b)
            }, fail: function (b) {
                var c = (new Date).getTime() - y.custom;
                f.sendPlayServiceQuality("custom", "timeout" == b.message ? 408 : 404, c);
                e.dispatch({type: "openapitimeoutyouku"})
            }
        })
    };
    ba.prototype = {
        addEventListener: function (b, e) {
            this._handler[b] = e
        }, removeEventListener: function (b) {
            this._handler[b] = null
        }, dispatch: function (b) {
            b && this._handler[b.type] &&
            (b._target = this, this._handler[b.type](b))
        }, parse: function (b) {
            null != b.error || 1 != b.status ? this.dispatch({type: "openapierror"}) : this.dispatch({
                type: "openapiokyouku",
                data: b
            })
        }
    };
    var o = {}, H = {}, K = [];
    o.start = function (b, e, c, g) {
        this._callback = g || function () {
            };
        null != H[b] && null != H[b][c] ? (v.log("Cache Hit vid = " + b), this._callback(H[b][c].v, H[b][c].videoInfo)) : (this._vid = decodeURIComponent(b), this._password = e, this.requestError = !1, this._type = c, this._videoInfo = null, this._url = "", this.request())
    };
    o.cache = function () {
        H[o._vid] =
        {};
        H[o._vid][o._type] = {v: this._v, videoInfo: this._videoInfo}
    };
    o.error = function () {
        f.sendErrorReport(0);
        f.showError({
            code: "2002",
            message: "\u8be5\u89c6\u9891\u5728H5\u7aef\u6682\u4e0d\u652f\u6301\u64ad\u653e"
        }, "\u8be5\u89c6\u9891\u6682\u65f6\u4e0d\u80fd\u64ad\u653e,\u8bf7\u4e0b\u8f7dAPP\u6216\u5728PC\u4e0a\u89c2\u770b", 320)
    };
    o.reportPlayListUep = function () {
        var b = (new Date).getTime() - y.get_json;
        K.push({type: "getplaylist", time: b})
    };
    o.response = function (b) {
        var e = b.data, l = e.stream;
        !this.playlistError && !o.requestError &&
        (this.playlistOK = !0, this.reportPlayListUep(), (c.v = b) && e ? l && ("default" !== l[0].drm_type || "http" !== l[0].transfer_mode) ? this.error(1, b, e, l) : this.init(b) : this.error(1, b, e, l))
    };
    o.request = function () {
        for (var b = "http://play.youku.com/play/get.json", e = document.getElementsByTagName("script"), l = 0, g = e.length; l < g; l++)if (/https:\/\/players\.youku\.com\/jsapi/i.exec(e[l].src)) {
            b = "https://plays.youku.com/play/get.json";
            break
        }
        e = D({vid: decodeURIComponent(this._vid), ct: 12}, this.playlistconfig);
        this._password && (e.pwd =
            encodeURI(this._password));
        this._password && (c.initConfig.client_id && c.config.partner_config && 1 == c.config.partner_config.status && 1 == c.config.partner_config.passless) && (e.cid = c.initConfig.client_id);
        c.config.partner_config && c.config.partner_config.stealsign && (e.r = encodeURIComponent(c.config.partner_config.stealsign));
        e = D(e, c.getUCParam(this._vid));
        y.get_json = (new Date).getTime();
        f.sendPlayServiceQuality("get_json", 0, 0);
        s.jsonp({
            url: b, data: e, time: 1E4, success: function (b) {
                var e = (new Date).getTime() - y.get_json;
                f.sendPlayServiceQuality("get_json", 200, e);
                o.response(b)
            }, fail: function (b) {
                var e = (new Date).getTime() - y.get_json;
                f.sendPlayServiceQuality("get_json", b.message == "timeout" ? 408 : 404, e);
                self.playlistError = true;
                f.sendErrorReport(2003);
                f.showError({
                    code: "2003",
                    message: "\u89c6\u9891\u4fe1\u606f\u51fa\u9519\uff0c\u8bf7\u5237\u65b0\u91cd\u8bd5"
                }, "\u89c6\u9891\u4fe1\u606f\u51fa\u9519\uff0c\u8bf7\u5237\u65b0\u91cd\u8bd5")
            }
        })
    };
    o.m3u8src = function (b) {
        c.password = this._password;
        return c.m3u8src_v2(this._vid, b)
    };
    o.total =
        function (b, e, l) {
            b = b[e][l];
            l = e = 0;
            if (c.v.data.controller && c.v.data.controller.html5_disable)e += c.v.data.video.seconds; else for (var g = 0; g < b.length; g++)var d = b[g], e = e + d.seconds, l = l + parseInt(d.size);
            return {totalTime: parseInt(e), totalBytes: l}
        };
    o.processError = function (b) {
        h.log("playlist errorcode = " + b.error.code);
        var e = b.stream;
        if (-301 == b.error.code || -303 == b.error.code || -307 == b.error.code || -308 == b.error.code) {
            for (b = 0; b < e.length; b++) {
                e[b].audio_lang = "default";
                e[b].drm_type = "";
                e[b].logo = "";
                e[b].milliseconds_audio =
                    0;
                e[b].milliseconds_video = 0;
                e[b].kye = "";
                e[b].size = 0;
                e[b].stream_fileid = "0*0";
                e[b].subtitle_lang = "";
                for (var c = 0; c < e[b].segs.length; c++)e[b].segs[c].kye = "", e[b].segs[c].size = 0, e[b].segs[c].total_milliseconds_audio = 0, e[b].segs[c].total_milliseconds_video = 0
            }
            return !1
        }
        this._callback(this._v, {});
        return !0
    };
    o.init = function (b) {
        this._v = b;
        var e = b.data, l = e.stream;
        if (!e.security || !e.security.encrypt_string || !e.security.ip)f.sendErrorReport(2004), f.showError({
                code: "2004",
                message: "\u6570\u636e\u89e3\u6790\u9519\u8bef"
            },
            "\u6570\u636e\u89e3\u6790\u9519\u8bef"); else if (!l && !e.error)f.showError({
            code: "2999",
            message: "\u6570\u636e\u89e3\u6790\u9519\u8bef"
        }, '\u8be5\u89c6\u9891\u6682\u4e0d\u80fd\u64ad\u653e <a href="http://m.youku.com/webapp/dl?app=youku&amp;source=webqr" title="\u4e0b\u8f7d\u4f18\u9177\u5ba2\u6237\u7aef" target="_blank"><button type="button" class="x-btn" style="background: #3bb4fc;text-align: center;color: #fff;border-radius: 1rem;">\u7528app\u89c2\u770b</button></a>'); else {
            var g = N(O(c.mk.a3 + "o0b" + f.userCache.a1,
                [19, 1, 4, 7, 30, 14, 28, 8, 24, 17, 6, 35, 34, 16, 9, 10, 13, 22, 32, 29, 31, 21, 18, 3, 2, 23, 25, 27, 11, 20, 5, 15, 12, 0, 33, 26]).toString(), Ea(e.security.encrypt_string));
            if (2 > g.split("_").length)f.sendErrorReport(2004), f.showError({
                code: "2004",
                message: "\u6570\u636e\u89e3\u6790\u9519\u8bef"
            }, "\u6570\u636e\u89e3\u6790\u9519\u8bef"); else {
                f.userCache.sid = g.split("_")[0];
                f.userCache.token = g.split("_")[1];
                if (null != e.error) {
                    if (-202 == e.error.code || -203 == e.error.code)f.sendErrorReport(4E3); else {
                        if (-402 == e.error.code) {
                            f.sendErrorReport(2007);
                            f.showError({
                                code: "2007",
                                message: "\u7528\u6237\u6ca1\u6709\u6743\u9650\u89c2\u770b"
                            }, "\u7528\u6237\u6ca1\u6709\u6743\u9650\u89c2\u770b,\u8bf7\u8fd4\u56de\u4e3b\u7ad9\u64ad\u653e");
                            return
                        }
                        f.sendErrorReport(1E3)
                    }
                    if (this.processError(e))return
                }
                this._videoInfo = new ca(e, l, this._type);
                l = this._videoInfo._videoSegsDic;
                g = "";
                c.defaultLanguage == l.lang ? g = c.defaultLanguage : (g = l.lang, c.defaultLanguage = l.lang);
                var d = o.total(l.streams, g, l.typeArr[g][0]);
                this._videoInfo.totalTime = d ? d.totalTime : e.video.seconds;
                if ("m3u8" ==
                    this._type)c.defaultVideoType = "mp4", null != i.getItem("defaultVideoType") && (c.defaultVideoType = i.getItem("defaultVideoType")), -1 == b.data.stream[0].stream_type.indexOf(c.defaultVideoType) && (c.defaultVideoType = "mp4", -1 == b.data.stream[0].stream_type.indexOf("mp4") && (c.defaultVideoType = "flv")), h.log("default = " + c.defaultVideoType), this._videoInfo.src = o.m3u8src(c.defaultVideoType), this.cache(), this._callback(this._v, this._videoInfo); else if ("mp4" == this._type) {
                    e = ["3gphd", "mp4", "flv"];
                    c.defaultVideoType =
                        null;
                    for (d = 0; d < e.length; d++)if (l.streams[g][e[d]] && !("3gphd" == e[d] && 7200 < parseInt(b.seconds))) {
                        c.defaultVideoType = e[d];
                        break
                    }
                    h.log("mp4 type=" + c.defaultVideoType);
                    c.defaultVideoType ? ("flv" == c.defaultVideoType && (c.config.playType = "directsrc"), this.cache(), this._callback(this._v, this._videoInfo)) : this.error(2)
                }
            }
        }
    };
    var ca = function (b, e, c) {
        this._sid = f.userCache.sid;
        this._fileType = c;
        this._videoSegsDic = {};
        new da;
        var c = [], g = [];
        g.streams = {};
        g.logos = {};
        g.typeArr = {};
        g.totalTime = {};
        for (var d = 0; d < e.length; d++) {
            for (var n =
                e[d].audio_lang, h = !1, j = 0; j < c.length; j++)if (c[j] == n) {
                h = !0;
                break
            }
            h || c.push(n)
        }
        for (d = 0; d < c.length; d++) {
            for (var x = c[d], n = {}, h = {}, i = [], j = 0; j < e.length; j++) {
                var o = e[j];
                if (x == o.audio_lang && this.isValidType(o.stream_type)) {
                    var p = this.convertType(o.stream_type), w = 0;
                    "none" != o.logo && (w = 1);
                    h[p] = w;
                    var u = !1, r;
                    for (r in i)p == i[r] && (u = !0);
                    u || i.push(p);
                    w = o.segs;
                    if (null != w) {
                        var s = [];
                        u && (s = n[p]);
                        for (u = 0; u < w.length; u++) {
                            var t = w[u];
                            if (null == t)break;
                            var v = {};
                            v.no = u;
                            v.size = t.size;
                            v.seconds = Number(t.total_milliseconds_video) /
                                1E3;
                            v.milliseconds_video = Number(o.milliseconds_video) / 1E3;
                            v.key = t.key;
                            v.fileId = t.fileid;
                            v.src = this.getVideoSrc(j, u, b, o.stream_type, v.fileId);
                            v.type = p;
                            s.push(v)
                        }
                        n[p] = s
                    }
                }
            }
            j = this.langCodeToCN(x).key;
            g.logos[j] = h;
            g.streams[j] = n;
            g.typeArr[j] = i
        }
        this._videoSegsDic = g;
        this._videoSegsDic.lang = this.langCodeToCN(c[0]).key
    }, da = function (b) {
        this._randomSeed = b;
        this.cg_hun()
    };
    da.prototype = {
        cg_hun: function () {
            this._cgStr = "";
            for (var b = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/\\:._-1234567890", e = b.length,
                     c = 0; c < e; c++) {
                var g = parseInt(this.ran() * b.length);
                this._cgStr += b.charAt(g);
                b = b.split(b.charAt(g)).join("")
            }
        }, cg_fun: function (b) {
            for (var b = b.split("*"), e = "", c = 0; c < b.length - 1; c++)e += this._cgStr.charAt(b[c]);
            return e
        }, ran: function () {
            this._randomSeed = (211 * this._randomSeed + 30031) % 65536;
            return this._randomSeed / 65536
        }
    };
    ca.prototype = {
        getFileId: function (b, e) {
            if (null == b || "" == b)return "";
            var c = b.slice(0, 8), g = e.toString(16);
            1 == g.length && (g = "0" + g);
            var g = g.toUpperCase(), d = b.slice(10, b.length);
            return c + g + d
        }, isValidType: function (b) {
            return "3gphd" ==
            b || "flv" == b || "flvhd" == b || "mp4hd" == b || "mp4hd2" == b || "mp4hd3" == b ? !0 : !1
        }, convertType: function (b) {
            var e = b;
            switch (b) {
                case "m3u8":
                    e = "mp4";
                    break;
                case "3gphd":
                    e = "3gphd";
                    break;
                case "flv":
                    e = "flv";
                    break;
                case "flvhd":
                    e = "flv";
                    break;
                case "mp4hd":
                    e = "mp4";
                    break;
                case "mp4hd2":
                    e = "hd2";
                    break;
                case "mp4hd3":
                    e = "hd3"
            }
            return e
        }, langCodeToCN: function (b) {
            var e = "";
            switch (b) {
                case "default":
                    e = {key: "guoyu", value: "\u56fd\u8bed"};
                    break;
                case "guoyu":
                    e = {key: "guoyu", value: "\u56fd\u8bed"};
                    break;
                case "yue":
                    e = {key: "yue", value: "\u7ca4\u8bed"};
                    break;
                case "chuan":
                    e = {key: "chuan", value: "\u5ddd\u8bdd"};
                    break;
                case "tai":
                    e = {key: "tai", value: "\u53f0\u6e7e"};
                    break;
                case "min":
                    e = {key: "min", value: "\u95fd\u5357"};
                    break;
                case "en":
                    e = {key: "en", value: "\u82f1\u8bed"};
                    break;
                case "ja":
                    e = {key: "ja", value: "\u65e5\u8bed"};
                    break;
                case "kr":
                    e = {key: "kr", value: "\u97e9\u8bed"};
                    break;
                case "in":
                    e = {key: "in", value: "\u5370\u5ea6"};
                    break;
                case "ru":
                    e = {key: "ru", value: "\u4fc4\u8bed"};
                    break;
                case "fr":
                    e = {key: "fr", value: "\u6cd5\u8bed"};
                    break;
                case "de":
                    e = {key: "de", value: "\u5fb7\u8bed"};
                    break;
                case "it":
                    e = {key: "it", value: "\u610f\u8bed"};
                    break;
                case "es":
                    e = {key: "es", value: "\u897f\u8bed"};
                    break;
                case "po":
                    e = {key: "po", value: "\u8461\u8bed"};
                    break;
                case "th":
                    e = {key: "th", value: "\u6cf0\u8bed"};
                    break;
                case "man":
                    e = {key: "man", value: "\u6696\u7537"};
                    break;
                case "baby":
                    e = {key: "baby", value: "\u840c\u7ae5"}
            }
            return e
        }, getVideoSrc: function (b, e, d, g, q, n, h) {
            var j = d.stream[b];
            if (!d.video.encodeid || !g)return "";
            var b = {flv: 0, flvhd: 0, mp4: 1, hd2: 2, "3gphd": 1, "3gp": 0}[g], g = {
                flv: "flv", mp4: "mp4", hd2: "flv", mp4hd: "mp4",
                mp4hd2: "mp4", "3gphd": "mp4", "3gp": "flv", flvhd: "flv"
            }[g], x = e.toString(16);
            1 == x.length && (x = "0" + x);
            var i = j.segs[e].total_milliseconds_video / 1E3, e = j.segs[e].key;
            if ("" == e || -1 == e)e = j.key2 + j.key1;
            j = "";
            d.show && (j = d.show.pay ? "&ypremium=1" : "&ymovie=1");
            d = "/player/getFlvPath/sid/" + f.userCache.sid + "_" + x + "/st/" + g + "/fileid/" + q + "?K=" + e + "&hd=" + b + "&myp=0&ts=" + i + "&ypp=0" + j;
            q = encodeURIComponent(L(N(O(c.mk.a4 + "poz" + f.userCache.a2, [19, 1, 4, 7, 30, 14, 28, 8, 24, 17, 6, 35, 34, 16, 9, 10, 13, 22, 32, 29, 31, 21, 18, 3, 2, 23, 25, 27, 11, 20, 5, 15,
                12, 0, 33, 26]).toString(), f.userCache.sid + "_" + q + "_" + f.userCache.token)));
            d = d + ("&ep=" + q) + "&ctype=12&ev=1" + ("&token=" + f.userCache.token);
            d += "&oip=" + c.v.data.security.ip;
            return "http://k.youku.com" + (d + ((n ? "/password/" + n : "") + (h ? h : "")))
        }
    };
    var ea = function (b) {
        this._player = b.video;
        null == this._player && (this._player = document.getElementById("youku-html5player-video-0"));
        this._oplayer = b
    };
    ea.prototype = {
        resize: function (b, e) {
            this._oplayer.resize(b, e)
        }, currentTime: function () {
            return this._player.currentTime
        }, totalTime: function () {
            return this._player.duration
        },
        playVideo: function () {
            this._oplayer.play()
        }, startPlayVideo: function () {
            if (f.isNeedFrontAd)this._oplayer.controls.onVideoBtnTouchEnd(); else this._oplayer.controls.onVideoBtnClick()
        }, pauseVideo: function () {
            this._player.pause()
        }, seekTo: function (b) {
            try {
                this._player.currentTime = b
            } catch (e) {
            }
        }, playVideoById: function (b, e) {
            h.log("YKH5Player playVideoByid");
            var d = this._oplayer;
            c.config.autoplay = !0;
            c.config.vid = b;
            o.start(b, e, c.config.content, function (b, e) {
                d.startPlay(b, e)
            })
        }, hideControls: function () {
            this._player.removeAttribute("controls")
        },
        showControls: function () {
            this._player.setAttribute("controls", !0)
        }, switchFullScreen: function () {
            this._oplayer.controls.fullscreenPanel.switchFullScreen({})
        }
    };
    f.Log = function (b, e) {
        var c = document.createElement("img");
        e && c.addEventListener("error", e, !1);
        c.src = b + "&r_=" + Math.floor(1E4 * Math.random());
        c.id = "youku-uniplayer-stat"
    };
    f.isNeedAdrTrick = function () {
        return f.isAndroid && !f.adrPlayTrick && !f.isHTC && f.isNeedFrontAd && !f.isVIVO
    };
    f.getParentUrl = function () {
        var b = null;
        if (parent !== window)try {
            b = parent.location.href
        } catch (e) {
            b =
                document.referrer
        }
        return b
    };
    f.adrInvalidPauseCheck = function (b) {
        var e = b.currentTime, c = 0, g = !1;
        f.adrPlayTrick = !0;
        b.pause();
        b.play();
        setInterval(function () {
            b.currentTime == e && !g ? (c++, b.play(), 0 == c % 10 && (b.load(), b.play())) : g = !0
        }, 1E3)
    };
    f.sendPlayServiceQuality = function (b, e, d) {
        b = {
            n: b,
            vid: c.initConfig.vid,
            u: encodeURIComponent(f.getParentUrl() ? f.getParentUrl() : window.location.href),
            c: e,
            t: d / 1E3,
            p: "hvp"
        };
        f.Log("http://v.l.youku.com/h_player_service?" + t(b))
    };
    f.sendErrorReport = function (b) {
        var e = {}, d = "", d = f.isIPAD ?
            "xplayer_ipad" : f.isIPHONE ? "xplayer_iphone" : "xplayer_android";
        e.m = d;
        e.ec = b;
        d = "";
        1E3 == b && (d = c.v.data.error.code);
        e.gc = d;
        e.u = encodeURIComponent(window.location.href);
        e.v = c.videoInfo ? c.videoInfo._sid : "";
        e.ct = c.v ? c.v.data ? c.v.data.video ? c.v.data.video.category_id : "" : "" : "";
        e.hd = f.hd ? f.hd : 0;
        c.v && c.v.data.network && (e.a = c.v ? c.v.data.network.area_code + "|" + c.v.data.network.dma_code : "");
        b = "";
        c.initConfig.vvlogconfig && c.initConfig.vvlogconfig.pvid && (b = c.initConfig.vvlogconfig.pvid);
        e.pid = b;
        f.Log("http://v.l.youku.com/perror?" +
            t(e))
    };
    f.Load = function (b, e) {
        if ("js" == e) {
            var c = document.createElement("script");
            c.setAttribute("type", "text/javascript");
            c.setAttribute("src", b)
        } else"css" == e && (c = document.createElement("link"), c.setAttribute("rel", "stylesheet"), c.setAttribute("type", "text/css"), c.setAttribute("href", b));
        "undefined" != typeof c && document.getElementsByTagName("head")[0].appendChild(c)
    };
    f.showError = function (b, e) {
        var d = c.get("#x-player");
        d.innerHTML = e ? e : "\u8be5\u89c6\u9891\u683c\u5f0f\u7279\u6b8a\uff0c\u8bf7\u5728PC\u4e0a\u89c2\u770b";
        d.style.textAlign = "center";
        d.style.color = "white";
        d.style.lineHeight = d.offsetHeight + "px";
        f.onError(b, e)
    };
    f.onError = function (b, e) {
        b = b || {code: "1000", message: "\u672a\u5b9a\u4e49\u9519\u8bef"};
        if (c.playerEvents && c.playerEvents.onPlayError)c.playerEvents.onPlayError(e ? e : "\u8be5\u89c6\u9891\u683c\u5f0f\u7279\u6b8a\uff0c\u8bf7\u5728PC\u4e0a\u89c2\u770b", b)
    };
    (function () {
        var b = document.createElement("video"), e = {MP4: "video/mp4", OGG: "video/ogg", WEBM: "video/webm"}, c = {
            isWin: "Win",
            isMac: "Mac",
            isSafari: "Safari",
            isChrome: "Chrome",
            isIPAD: "iPad",
            isIPAD7: "iPad; CPU OS 7",
            isIPHONE: "iPhone",
            isIPOD: "iPod",
            isLEPAD: "lepad_hls",
            isMIUI: "MI-ONE",
            isAndroid: "Android",
            isAndroid4: "Android 4.",
            isAndroid41: "Android 4.1",
            isSonyDTV: "SonyDTV",
            isBlackBerry: "BlackBerry",
            isMQQBrowser: "MQQBrowser",
            isMobile: "Mobile",
            isSamSung: "SAMSUNG",
            isHTC: "HTC",
            isLumia: "Lumia",
            isVIVO: "vivo",
            isWeixin: "MicroMessenger"
        };
        f.supportHTML5Video = !1;
        f.isIOS = !1;
        f.os = "";
        if (b.canPlayType) {
            f.supportHTML5Video = !0;
            for (var d in e)f["canPlay" + d] = b.canPlayType(e[d]) ?
                !0 : !1
        }
        for (var q in c)if (-1 !== navigator.userAgent.indexOf(c[q]) ? (f[q] = !0, f.os += c[q] + " ") : f[q] = !1, -1 !== navigator.userAgent.indexOf("Android"))b = navigator.userAgent.indexOf("Android"), b = navigator.userAgent.substr(b, 10), b > c.isAndroid4 && (f.isAndroid4 = !0, f.os += b + " ");
        f.isMobileIOS = f.isIPAD || f.isIPHONE || f.isIPOD;
        f.isIOS = f.isMobileIOS || f.isMac;
        f.isSupportH5M3U8 = f.isMobileIOS || f.isMac && f.isSafari && !f.isChrome || f.isLEPAD || f.isSonyDTV;
        f.isSupportH5MP4 = (f.isChrome || f.isIE10 || f.isAndroid41 || f.isAndroid4 || f.isLumia) &&
            f.canPlayMP4;
        q = c = 0;
        try {
            if (document.all) {
                var n = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
                if (n) {
                    var c = 1, h = n.GetVariable("$version");
                    parseInt(h.split(" ")[1].split(",")[0])
                }
            } else if (navigator.plugins && 0 < navigator.plugins.length && (n = navigator.plugins["Shockwave Flash"]))for (var c = 1, j = n.description.split(" "), n = 0; n < j.length; ++n)isNaN(parseInt(j[n])) || parseInt(j[n])
        } catch (x) {
            q = c = 1
        }
        f.isSupportFlash = c && !q;
        f.isMQQBrowser && (f.isSupportFlash = !1);
        f.isLumia && (f.isSupportFlash = !1);
        f.isPhone = f.isIPHONE ||
            f.isIPOD || f.isAndroid && f.isMobile;
        f.isAndroidPad = f.isAndroid && !f.isMobile;
        f.isPad = f.isIPAD || f.isAndroidPad;
        f.isMobile = f.isIPAD || f.isIPHONE || f.isIPOD || f.isLEPAD || f.isMIUI || f.isAndroid4 || f.isSonyDTV || f.isLumia
    })();
    var S = function (b) {
        h.log("canplay mp4 = " + f.canPlayMP4);
        c.initConfig = b;
        this._vid = b.vid;
        this._target = b.target;
        this._partnerId = b.partnerId;
        b.client_id && (this._partnerId = b.client_id);
        !b.pkid && (!this._vid || !this._target || !this._partnerId) ? alert("[Fail]The params of {vid,target,client_id} are necessary !") :
            (this._events = b.events, c.playerEvents = b.events, f._target = this._target, this._paid = 0, null != b.paid && (this._paid = b.paid), this._id = b.id, null == this._id && (this._id = "youku-player"), f.playerId = this._id, this._width = b.width, this._height = b.height, this._expand = b.expand, null == b.width || null == b.height ? null == b.expand && (this._expand = 0) : null == b.expand && (this._expand = 1), this._prefer = b.prefer ? b.prefer.toLowerCase() : "flash", this._starttime = b.starttime, this._password = b.password, this._poster = b.poster, this._autoplay = !!b.autoplay,
                this._canWide = b.canWide, "undefined" != typeof b.show_related && (this._showRelated = !!b.show_related), this._winType = b.wintype, this._pkid = b.pkid, this._pkpid = b.pkpid, this._pkurl = b.pkurl, this._playlistconfig = b.playlistconfig, this._isMobile = f.isMobile, this._isMobileIOS = f.isMobileIOS, c.isWeixin = f.isWeixin, "undefined" != typeof b.weixin && (c.isWeixin = !!b.weixin), this._loop = !!b.loop || !1, this._playerType = "", c.mk = {}, c.mk.a3 = "b4et", c.mk.a4 = "boa4")
    };
    S.prototype = {
        isSupportH5MP4: function () {
            return f.isSupportH5MP4
        }, isSupportH5M3U8: function () {
            return f.isSupportH5M3U8
        },
        isSupportFlash: function () {
            return f.isSupportFlash
        }, playerType: function () {
            if ("" != this._playerType)return this._playerType;
            this._playerType = "h5" == this._prefer ? this.isSupportH5M3U8() ? "h5m3u8" : this.isSupportH5MP4() ? "h5mp4" : this.isSupportFlash() ? "flash" : "error" : "flash" == this._prefer ? this.isSupportFlash() ? "flash" : this.isSupportH5M3U8() ? "h5m3u8" : this.isSupportH5MP4() ? "h5mp4" : "error" : "error";
            if (("h5m3u8" == this._playerType || "h5mp4" == this._playerType) && void 0 != this._pkid)this._playerType = "h5pk";
            return this._playerType
        },
        select: function () {
            h.log("playerType = " + this.playerType());
            if (this.isThirdParty()) {
                var b = this;
                this.processThirdParty(function () {
                    b.selectHandler()
                })
            } else this.selectHandler()
        }, selectHandler: function () {
            "h5m3u8" == this.playerType() ? this.selectH5M3U8() : "h5mp4" == this.playerType() ? this.selectH5MP4() : "h5pk" == this.playerType() ? this.selectH5PK() : "flash" == this.playerType() ? this.selectFlash() : this.selectDirectUrl();
            if (this._events && this._events.onPlayerReady) {
                var b = this._events.onPlayerReady;
                if ("h5" == f.playerType)var e =
                    setInterval(function () {
                        if (r(f.playerId)) {
                            f.playerCurrentState = f.playerState.PLAYER_STATE_READY;
                            h.log(f.playerCurrentState);
                            clearInterval(e);
                            try {
                                i.appendItem("phase", "playerready"), b()
                            } catch (c) {
                            }
                        }
                    }, 500); else"flash" == f.playerType && (e = setInterval(function () {
                    if (1 == s.swfLoaded) {
                        f.playerCurrentState = f.playerState.PLAYER_STATE_READY;
                        h.log(f.playerCurrentState);
                        clearInterval(e);
                        try {
                            i.appendItem("phase", "playerready"), b()
                        } catch (c) {
                        }
                    }
                }, 500))
            }
        }, selectH5MP4: function () {
            f.playerType = "h5";
            var b = this._h5player = new YoukuHTML5Player({
                id: this._id,
                vid: this._vid,
                partnerId: this._partnerId,
                parentBox: this._target,
                events: this._events,
                width: this._width,
                height: this._height,
                poster: this._poster,
                autoplay: this._autoplay,
                isMobile: this._isMobile,
                isMobileIOS: this._isMobileIOS,
                content: "mp4",
                loop: this._loop,
                wintype: this._winType,
                expand: this._expand,
                partner_config: this.partner_config,
                canWide: this._canWide ? this._canWide : 0
            }, c.initConfig);
            o.playlistconfig = this._playlistconfig;
            o.start(this._vid, this._password, "mp4", function (e, d) {
                i.appendItem("phase", "vinfo_mp4");
                b.startPlay(e, d);
                if (c.initConfig.events && c.initConfig.events.onMediaSrcOK)c.initConfig.events.onMediaSrcOK(c.defaultVideoType, d._videoSegsDic.streams[d._videoSegsDic.lang][c.defaultVideoType][0].src)
            })
        }, selectH5M3U8: function () {
            f.playerType = "h5";
            var b = {
                id: this._id,
                vid: this._vid,
                partnerId: this._partnerId,
                parentBox: this._target,
                events: this._events,
                width: this._width,
                height: this._height,
                poster: this._poster,
                autoplay: this._autoplay,
                isMobile: this._isMobile,
                isMobileIOS: this._isMobileIOS,
                content: "m3u8",
                loop: this._loop,
                wintype: this._winType,
                expand: this._expand,
                partner_config: this.partner_config,
                canWide: this._canWide ? this._canWide : 0
            };
            if (f.isIPHONE || f.isIPOD)b.playType = "directsrc";
            var e = new YoukuHTML5Player(b, c.initConfig);
            this._h5player = e;
            o.playlistconfig = this._playlistconfig;
            o.start(this._vid, this._password, "m3u8", function (b, c) {
                i.appendItem("phase", "vinfo_m3u8");
                e.startPlay(b, c)
            })
        }, selectH5PK: function () {
            f.playerType = "h5";
            var b = {id: this._pkid, pid: this._pkpid, url: decodeURIComponent(this._pkurl), parentBox: this._target};
            this._h5player = new fa(b, c.initConfig)
        }, processThirdParty: function (b) {
            var e = new ba({
                type: "h5",
                client_id: c.initConfig.client_id,
                video_id: c.initConfig.vid,
                embsig: c.initConfig.embsig,
                refer: encodeURIComponent(window.location.href)
            }), d = this;
            e.addEventListener("openapiokyouku", function (e) {
                h.log("thirdparty res ok");
                d.partner_config = e.data;
                b()
            });
            e.addEventListener("openapierror", function () {
                h.log("thirdparty res error");
                b()
            });
            e.addEventListener("openapitimeoutyouku", function () {
                h.log("thirdparty res timeout");
                b()
            })
        }, selectH5VTag: function () {
            f.playerType = "h5";
            var b = "http://v.youku.com/player/getM3U8/vid/" + this._vid + "/type/mp4/ts/" + parseInt((new Date).getTime() / 1E3), b = b + (this._password ? "/password/" + this._password : ""), b = '<video src="' + (b + "/v.m3u8") + '" controls width=' + this._width + " height=" + this._height + " id=" + this._id + " autohide=false " + (this._poster ? "poster=" + this._poster : "") + " " + (!0 == this._autoplay ? "autoplay=true" : "") + "></video>";
            r(this._target).innerHTML = b
        }, isThirdParty: function () {
            if (void 0 != this._pkid)return !1;
            var b = c.initConfig.client_id;
            return null != b && 16 == (b + "").length ? !0 : !1
        }, selectFlash: function () {
            f.playerType = "flash";
            var b = {imglogo: this._poster || "", paid: this._paid, partnerId: c.initConfig.client_id};
            this._loop && (b.isLoop = "true");
            null != c.initConfig.firsttime && (b.firsttime = c.initConfig.firsttime);
            null != c.initConfig.embsig && (b.embsig = c.initConfig.embsig);
            null != c.initConfig.password && (b.passwordstr = c.initConfig.password);
            null != c.initConfig.styleid && (b.styleid = c.initConfig.styleid);
            null != c.initConfig.vext &&
            (b.vext = c.initConfig.vext);
            for (var e in c.initConfig.adconfig)b[e] = c.initConfig.adconfig[e];
            for (e in c.initConfig.flashconfig)b[e] = c.initConfig.flashconfig[e];
            var d = {
                sid: this._vid,
                isAutoPlay: this._autoplay,
                isShowRelatedVideo: this._showRelated,
                winType: this._winType,
                newPlayer: c.initConfig.newPlayer
            };
            this.isThirdParty() && !b.delayload && (d.partnerid = this._partnerId);
            null != c.initConfig.pkid && (b.VideoIDS = c.initConfig.pkid);
            null != c.initConfig.pkpid && (b.pkpid = c.initConfig.pkpid);
            null != c.initConfig.pkurl &&
            (b.pkurl = c.initConfig.pkurl);
            var g = ga + "/player.php/", q;
            for (q in d) {
                var n = d[q];
                "undefined" != typeof n && "" !== n && (g += q + "/" + n + "/")
            }
            g += "v.swf";
            c.initConfig.flashsrc && (g = c.initConfig.flashsrc);
            null != c.initConfig.pkid && (g = "youkupaike.swf");
            b = {allowFullScreen: !0, allowScriptAccess: "always", movie: g, flashvars: b};
            c.initConfig.wmode && (b.wmode = c.initConfig.wmode);
            if ((n = c.initConfig.flashparam) && "object" == typeof n)for (q in n)b[q] = n[q];
            q = "";
            for (e in b)n = b[e], "object" == typeof n && (n = t(n)), q += "<param name=" + e + " value=" +
                n + ">";
            q += decodeURI(c.initConfig.flashext || "");
            r(this._target).innerHTML = "<object type=application/x-shockwave-flash data= " + g + " width=100% height=100% id=" + this._id + ">" + q + "</object>";
            this._expand && (r(this._target).style.width = this._width + "px", r(this._target).style.height = this._height + "px")
        }, selectDirectUrl: function (b) {
            b = b || "mp4";
            h.log("select directsrc");
            f.playerType = "directsrc";
            var e = new ha({
                id: this._id,
                vid: this._vid,
                partnerId: this._partnerId,
                parentBox: this._target,
                events: this._events,
                width: this._width,
                height: this._height,
                poster: this._poster,
                autoplay: this._autoplay,
                isMobile: this._isMobile,
                isMobileIOS: this._isMobileIOS,
                content: b,
                playType: "directsrc",
                wintype: this._winType,
                expand: this._expand,
                canWide: this._canWide ? this._canWide : 0
            });
            this._h5player = e;
            o.playlistconfig = this._playlistconfig;
            o.start(this._vid, this._password, b, function (b, c) {
                e.startPlay(b, c)
            })
        }
    };
    s.Player = function (b, e) {
        e.target = b;
        this.select = new S(e);
        this.select.select();
        this._player = ""
    };
    s.Player.prototype = {
        player: function () {
            return "" != this._player ?
                this._player : this._player = "h5" == f.playerType ? new ea(this.select._h5player) : "flash" == f.playerType ? new aa : "error"
        }, resize: function (b, e) {
            this.player().resize(b, e)
        }, currentTime: function () {
            return this.player().currentTime()
        }, totalTime: function () {
            return this.player().totalTime()
        }, playVideo: function () {
            this.player().playVideo()
        }, startPlayVideo: function () {
            this.player().startPlayVideo()
        }, pauseVideo: function () {
            this.player().pauseVideo()
        }, seekTo: function (b) {
            this.player().seekTo(b)
        }, hideControls: function () {
            this.player().hideControls()
        },
        showControls: function () {
            this.player().showControls()
        }, playVideoById: function (b) {
            this.player().playVideoById(b)
        }, switchFullScreen: function () {
            try {
                this.player().switchFullScreen()
            } catch (b) {
            }
        }
    };
    var ia = function (b) {
        this.video = b;
        this.bindEvent()
    };
    ia.prototype = {
        onLoadedStartST: function () {
            v.log("onLoadedStartIPH");
            this._load_data = !0
        }, onLoadedDataST: function () {
            v.log("onLoadedDataST");
            this._load_data = !1
        }, onLoadedMetaDataST: function () {
            v.log("onLoadedMetaDataST");
            if (this.getM3U8XhrState) {
                var b = (new Date).getTime() -
                    y.m3u8;
                f.sendPlayServiceQuality("m3u8", 200, b);
                this.getM3U8XhrState = !1
            }
        }, onErrorST: function () {
            if (this.getM3U8XhrState) {
                var b = (new Date).getTime() - y.m3u8;
                f.sendPlayServiceQuality("m3u8", 404, b);
                this.getM3U8XhrState = !1
            }
        }, onPlayST: function () {
            v.log("onPlayST");
            if (!0 == this._load_data) {
                y.m3u8 = (new Date).getTime();
                f.sendPlayServiceQuality("m3u8", 0, 0);
                this.getM3U8XhrState = !0;
                var b = this;
                setTimeout(function () {
                    if (b.getM3U8XhrState) {
                        var e = (new Date).getTime() - y.m3u8;
                        f.sendPlayServiceQuality("m3u8", 408, e);
                        b.getM3U8XhrState = !1
                    }
                }, 1E4)
            }
        }, bindEvent: function () {
            "m3u8" == c.config.content && (c.addEventHandler(this.video, "play", c.bindAsEventListener(this, this.onPlayST)), c.addEventHandler(this.video, "loadstart", c.bindAsEventListener(this, this.onLoadedStartST)), c.addEventHandler(this.video, "loadedmetadata", c.bindAsEventListener(this, this.onLoadedMetaDataST)), c.addEventHandler(this.video, "error", c.bindAsEventListener(this, this.onErrorST)), c.addEventHandler(this.video, "loadeddata", c.bindAsEventListener(this, this.onLoadedDataST)))
        }
    };
    var r = function (b) {
        return document.getElementById(b)
    }, Ga = function (b) {
        b = parseInt(b);
        return Math.min(Math.max(b, 0), c.videoInfo.totalTime)
    }, Da = Object.prototype.hasOwnProperty, t = function (b) {
        var e = [], c;
        for (c in b)e.push(c + "=" + b[c]);
        return e.join("&")
    }, L = function (b) {
        if (!b)return "";
        var b = b.toString(), e, c, d, q, f, h;
        d = b.length;
        c = 0;
        for (e = ""; c < d;) {
            q = b.charCodeAt(c++) & 255;
            if (c == d) {
                e += "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(q >> 2);
                e += "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((q &
                    3) << 4);
                e += "==";
                break
            }
            f = b.charCodeAt(c++);
            if (c == d) {
                e += "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(q >> 2);
                e += "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((q & 3) << 4 | (f & 240) >> 4);
                e += "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((f & 15) << 2);
                e += "=";
                break
            }
            h = b.charCodeAt(c++);
            e += "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(q >> 2);
            e += "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((q &
                3) << 4 | (f & 240) >> 4);
            e += "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((f & 15) << 2 | (h & 192) >> 6);
            e += "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(h & 63)
        }
        return e
    };
    (function () {
        this.FX = function (e, c, d, l, f, h) {
            this.el = b.get(e);
            this.attributes = c;
            this.duration = d || 0.7;
            this.transition = l && l in FX.transitions ? l : "easeInOut";
            this.callback = f || function () {
                };
            this.ctx = h || window;
            this.units = {};
            this.frame = {};
            this.endAttr = {};
            this.startAttr = {}
        };
        this.FX.transitions = {
            linear: function (b,
                              e, c, d) {
                return c * b / d + e
            }, easeIn: function (b, e, c, d) {
                return -c * Math.cos(b / d * (Math.PI / 2)) + c + e
            }, easeOut: function (b, e, c, d) {
                return c * Math.sin(b / d * (Math.PI / 2)) + e
            }, easeInOut: function (b, e, c, d) {
                return -c / 2 * (Math.cos(Math.PI * b / d) - 1) + e
            }
        };
        this.FX.prototype = {
            start: function () {
                var b = this;
                this.getAttributes();
                this.duration *= 1E3;
                this.time = (new Date).getTime();
                this.animating = !0;
                this.timer = setInterval(function () {
                    var e = (new Date).getTime();
                    e < b.time + b.duration ? (b.elapsed = e - b.time, b.setCurrentFrame()) : (b.frame = b.endAttr, b.complete());
                    b.setAttributes()
                }, 10)
            }, ease: function (b, e) {
                return FX.transitions[this.transition](this.elapsed, b, e - b, this.duration)
            }, complete: function () {
                clearInterval(this.timer);
                this.timer = null;
                this.animating = !1;
                this.callback.call(this.ctx)
            }, setCurrentFrame: function () {
                for (var b in this.startAttr)if (this.startAttr[b] instanceof Array) {
                    this.frame[b] = [];
                    for (var e = 0; e < this.startAttr[b].length; e++)this.frame[b][e] = this.ease(this.startAttr[b][e], this.endAttr[b][e])
                } else this.frame[b] = this.ease(this.startAttr[b], this.endAttr[b])
            },
            getAttributes: function () {
                for (var e in this.attributes)switch (e) {
                    case "color":
                    case "borderColor":
                    case "border-color":
                    case "backgroundColor":
                    case "background-color":
                        this.startAttr[e] = c(this.attributes[e].from || b.getStyle(this.el, e));
                        this.endAttr[e] = c(this.attributes[e].to);
                        break;
                    case "scrollTop":
                    case "scrollLeft":
                        var d = this.el == document.body ? document.documentElement || document.body : this.el;
                        this.startAttr[e] = this.attributes[e].from || d[e];
                        this.endAttr[e] = this.attributes[e].to;
                        break;
                    default:
                        var f = this.attributes[e].to,
                            h = this.attributes[e].units || "px";
                        this.attributes[e].from ? d = this.attributes[e].from : (d = parseFloat(b.getStyle(this.el, e)) || 0, "px" != h && document.defaultView && (b.setStyle(this.el, e, (f || 1) + h), d *= (f || 1) / parseFloat(b.getStyle(this.el, e)), b.setStyle(this.el, e, d + h)));
                        this.units[e] = h;
                        this.endAttr[e] = f;
                        this.startAttr[e] = d
                }
            }, setAttributes: function () {
                for (var e in this.frame)switch (e) {
                    case "opacity":
                        b.setStyle(this.el, e, this.frame[e]);
                        break;
                    case "scrollLeft":
                    case "scrollTop":
                        (this.el == document.body ? document.documentElement ||
                        document.body : this.el)[e] = this.frame[e];
                        break;
                    case "color":
                    case "borderColor":
                    case "border-color":
                    case "backgroundColor":
                    case "background-color":
                        b.setStyle(this.el, e, "rgb(" + Math.floor(this.frame[e][0]) + "," + Math.floor(this.frame[e][1]) + "," + Math.floor(this.frame[e][2]) + ")");
                        break;
                    default:
                        b.setStyle(this.el, e, this.frame[e] + this.units[e])
                }
            }
        };
        var b = {
            get: function (b) {
                return "string" == typeof b ? document.getElementById(b) : b
            }, getStyle: function (b, c) {
                var c = e(c), d = document.defaultView;
                return d && d.getComputedStyle ?
                d.getComputedStyle(b, "")[c] || null : "opacity" == c ? (d = b.filters("alpha").opacity, isNaN(d) ? 1 : d ? d / 100 : 0) : b.currentStyle[c] || null
            }, setStyle: function (b, c, d) {
                "opacity" == c ? (b.style.filter = "alpha(opacity=" + 100 * d + ")", b.style.opacity = d) : (c = e(c), b.style[c] = d)
            }
        }, e = function () {
            var b = {};
            return function (e) {
                if (b[e])return b[e];
                var c = e.split("-"), d = c[0];
                if (1 < c.length)for (var l = 1, f = c.length; l < f; l++)d += c[l].charAt(0).toUpperCase() + c[l].substring(1);
                return b[e] = d
            }
        }(), c = function () {
            var b = /^#?(\w{2})(\w{2})(\w{2})$/, e = /^#?(\w{1})(\w{1})(\w{1})$/,
                c = /^rgb\((\d{1,3}),\s*(\d{1,3}),\s*(\d{1,3})\)$/;
            return function (d) {
                var l = d.match(b);
                if (l && 4 == l.length)return [parseInt(l[1], 16), parseInt(l[2], 16), parseInt(l[3], 16)];
                if ((l = d.match(c)) && 4 == l.length)return [parseInt(l[1], 10), parseInt(l[2], 10), parseInt(l[3], 10)];
                if ((l = d.match(e)) && 4 == l.length)return [parseInt(l[1] + l[1], 16), parseInt(l[2] + l[2], 16), parseInt(l[3] + l[3], 16)]
            }
        }()
    })();
    FX.transitions.quadIn = function (b, e, c, d) {
        return c * (b /= d) * b + e
    };
    FX.transitions.quadOut = function (b, e, c, d) {
        return -c * (b /= d) * (b - 2) + e
    };
    FX.transitions.quadInOut = function (b, e, c, d) {
        return 1 > (b /= d / 2) ? c / 2 * b * b + e : -c / 2 * (--b * (b - 2) - 1) + e
    };
    FX.transitions.cubicIn = function (b, e, c, d) {
        return c * (b /= d) * b * b + e
    };
    FX.transitions.cubicOut = function (b, e, c, d) {
        return c * ((b = b / d - 1) * b * b + 1) + e
    };
    FX.transitions.cubicInOut = function (b, e, c, d) {
        return 1 > (b /= d / 2) ? c / 2 * b * b * b + e : c / 2 * ((b -= 2) * b * b + 2) + e
    };
    FX.transitions.quartIn = function (b, e, c, d) {
        return c * (b /= d) * b * b * b + e
    };
    FX.transitions.quartOut = function (b, e, c, d) {
        return -c * ((b = b / d - 1) * b * b * b - 1) + e
    };
    FX.transitions.quartInOut = function (b,
                                          e, c, d) {
        return 1 > (b /= d / 2) ? c / 2 * b * b * b * b + e : -c / 2 * ((b -= 2) * b * b * b - 2) + e
    };
    FX.transitions.quintIn = function (b, e, c, d) {
        return c * (b /= d) * b * b * b * b + e
    };
    FX.transitions.quintOut = function (b, e, c, d) {
        return c * ((b = b / d - 1) * b * b * b * b + 1) + e
    };
    FX.transitions.quintInOut = function (b, e, c, d) {
        return 1 > (b /= d / 2) ? c / 2 * b * b * b * b * b + e : c / 2 * ((b -= 2) * b * b * b * b + 2) + e
    };
    FX.transitions.expoIn = function (b, e, c, d) {
        return 0 == b ? e : c * Math.pow(2, 10 * (b / d - 1)) + e - 0.0010 * c
    };
    FX.transitions.expoOut = function (b, e, c, d) {
        return b == d ? e + c : 1.001 * c * (-Math.pow(2, -10 * b / d) + 1) + e
    };
    FX.transitions.expoInOut =
        function (b, e, c, d) {
            return 0 == b ? e : b == d ? e + c : 1 > (b /= d / 2) ? c / 2 * Math.pow(2, 10 * (b - 1)) + e - 5.0E-4 * c : 1.0005 * (c / 2) * (-Math.pow(2, -10 * --b) + 2) + e
        };
    FX.transitions.circIn = function (b, e, c, d) {
        return -c * (Math.sqrt(1 - (b /= d) * b) - 1) + e
    };
    FX.transitions.circOut = function (b, e, c, d) {
        return c * Math.sqrt(1 - (b = b / d - 1) * b) + e
    };
    FX.transitions.circInOut = function (b, e, c, d) {
        return 1 > (b /= d / 2) ? -c / 2 * (Math.sqrt(1 - b * b) - 1) + e : c / 2 * (Math.sqrt(1 - (b -= 2) * b) + 1) + e
    };
    FX.transitions.backIn = function (b, e, c, d, f) {
        f = f || 1.70158;
        return c * (b /= d) * b * ((f + 1) * b - f) + e
    };
    FX.transitions.backOut =
        function (b, e, c, d, f) {
            f = f || 1.70158;
            return c * ((b = b / d - 1) * b * ((f + 1) * b + f) + 1) + e
        };
    FX.transitions.backBoth = function (b, e, c, d, f) {
        f = f || 1.70158;
        return 1 > (b /= d / 2) ? c / 2 * b * b * (((f *= 1.525) + 1) * b - f) + e : c / 2 * ((b -= 2) * b * (((f *= 1.525) + 1) * b + f) + 2) + e
    };
    FX.transitions.elasticIn = function (b, e, c, d, f, h) {
        if (0 == b)return e;
        if (1 == (b /= d))return e + c;
        h || (h = 0.3 * d);
        !f || f < Math.abs(c) ? (f = c, c = h / 4) : c = h / (2 * Math.PI) * Math.asin(c / f);
        return -(f * Math.pow(2, 10 * (b -= 1)) * Math.sin((b * d - c) * 2 * Math.PI / h)) + e
    };
    FX.transitions.elasticOut = function (b, e, c, d, f, h) {
        if (0 ==
            b)return e;
        if (1 == (b /= d))return e + c;
        h || (h = 0.3 * d);
        if (!f || f < Math.abs(c))var f = c, m = h / 4; else m = h / (2 * Math.PI) * Math.asin(c / f);
        return f * Math.pow(2, -10 * b) * Math.sin((b * d - m) * 2 * Math.PI / h) + c + e
    };
    FX.transitions.elasticBoth = function (b, e, c, d, f, h) {
        if (0 == b)return e;
        if (2 == (b /= d / 2))return e + c;
        h || (h = d * 0.3 * 1.5);
        if (!f || f < Math.abs(c))var f = c, m = h / 4; else m = h / (2 * Math.PI) * Math.asin(c / f);
        return 1 > b ? -0.5 * f * Math.pow(2, 10 * (b -= 1)) * Math.sin((b * d - m) * 2 * Math.PI / h) + e : 0.5 * f * Math.pow(2, -10 * (b -= 1)) * Math.sin((b * d - m) * 2 * Math.PI / h) + c + e
    };
    FX.transitions.backIn =
        function (b, e, c, d, f) {
            "undefined" == typeof f && (f = 1.70158);
            return c * (b /= d) * b * ((f + 1) * b - f) + e
        };
    FX.transitions.backOut = function (b, e, c, d, f) {
        "undefined" == typeof f && (f = 1.70158);
        return c * ((b = b / d - 1) * b * ((f + 1) * b + f) + 1) + e
    };
    FX.transitions.backBoth = function (b, e, c, d, f) {
        "undefined" == typeof f && (f = 1.70158);
        return 1 > (b /= d / 2) ? c / 2 * b * b * (((f *= 1.525) + 1) * b - f) + e : c / 2 * ((b -= 2) * b * (((f *= 1.525) + 1) * b + f) + 2) + e
    };
    FX.transitions.bounceIn = function (b, e, c, d) {
        return c - FX.transitions.bounceOut(d - b, 0, c, d) + e
    };
    FX.transitions.bounceOut = function (b,
                                         c, d, g) {
        return (b /= g) < 1 / 2.75 ? d * 7.5625 * b * b + c : b < 2 / 2.75 ? d * (7.5625 * (b -= 1.5 / 2.75) * b + 0.75) + c : b < 2.5 / 2.75 ? d * (7.5625 * (b -= 2.25 / 2.75) * b + 0.9375) + c : d * (7.5625 * (b -= 2.625 / 2.75) * b + 0.984375) + c
    };
    FX.transitions.bounceBoth = function (b, c, d, g) {
        return b < g / 2 ? 0.5 * FX.transitions.bounceIn(2 * b, 0, d, g) + c : 0.5 * FX.transitions.bounceOut(2 * b - g, 0, d, g) + 0.5 * d + c
    };
    (function (b) {
        function c(b, e) {
            b.timer && clearTimeout(b.timer);
            b.clearAttributes ? b.clearAttributes() : b.onload = b.onreadystatechange = b.onerror = null;
            delete d[e];
            b.parentNode.removeChild(b)
        }

        var d = s.callback = s.callback || {};
        b.jsonp = function (b) {
            b = b || {};
            if (!b.url)throw new TypeError("Param Error");
            var f = b.callback || "callback", n = b.data || {}, m = parseInt(b.time) || 1E4, j = "cb_" + Y(6);
            n[f] = "YKU.callback." + j;
            var f = t(n), n = b.url, n = -1 === n.indexOf("?") ? n + ("?" + f) : n + ("&" + f), i = document.createElement("script");
            d[j] = function (c) {
                try {
                    "timeout" != i.jsonp && (i.jsonp = "success", b.success && b.success(c))
                } catch (e) {
                    h.log("-- JSONP --"), h.log(e)
                }
            };
            m && (i.timer = setTimeout(function () {
                    i.jsonp = "timeout";
                    b.fail && b.fail({message: "timeout"})
                },
                m));
            i.onreadystatechange = i.onload = function () {
                if (!this.readyState || this.readyState == "loaded" || this.readyState == "complete") {
                    i.jsonp !== "timeout" && i.jsonp != "success" && b.fail && b.fail({message: "fail"});
                    c(i, j)
                }
            };
            i.onerror = function () {
                i.jsonp = "error";
                c(i, j);
                b.fail && b.fail({message: "error"})
            };
            i.src = n;
            document.getElementsByTagName("head")[0].appendChild(i)
        }
    })(s);
    var ja = function () {
    };
    ja.prototype = {
        cookie: function (b, c, d, g) {
            "string" == typeof b && (g = d, d = c, c = b, b = window);
            if (void 0 !== d) {
                var g = g || {}, f;
                f = "";
                g.expires &&
                (g.expires.constructor == Date ? f = g.expires : (f = new Date, f.setTime(f.getTime() + 864E5 * g.expires)), f = "; expires=" + f.toGMTString());
                var h = g.path ? "; path=" + g.path : "", m = g.domain ? "; domain=" + g.domain : "", g = g.secure ? "; secure" : "";
                b.document.cookie = [c, "=", encodeURIComponent(d), f, h, m, g].join("")
            } else return (d = b.document.cookie.match(RegExp("(?:\\s|^)" + c + "\\=([^;]*)"))) ? decodeURIComponent(d[1]) : null
        }, pvid: function () {
            return this.rand(this.cookie("u_id"))
        }, Mash: function () {
            var b = 4022871197, c = function (c) {
                for (var c =
                    c.toString(), e = 0; e < c.length; e++) {
                    b += c.charCodeAt(e);
                    var d = 0.02519603282416938 * b;
                    b = d >>> 0;
                    d -= b;
                    d *= b;
                    b = d >>> 0;
                    d -= b;
                    b += 4294967296 * d
                }
                return 2.3283064365386963E-10 * (b >>> 0)
            };
            c.version = "Mash 0.9";
            return c
        }, MRG32k3a: function () {
            var b = this;
            return function (c) {
                var d = 12345, g = 12345, f = 123, h = 12345, m = 12345, j = 123;
                0 === c.length && (c = [+new Date]);
                for (var i = b.Mash(), o = 0; o < c.length; o++)d += 4294967296 * i(c[o]), g += 4294967296 * i(c[o]), f += 4294967296 * i(c[o]), h += 4294967296 * i(c[o]), m += 4294967296 * i(c[o]), j += 4294967296 * i(c[o]);
                var d =
                    d % 4294967087, g = g % 4294967087, f = f % 4294967087, h = h % 4294944443, m = m % 4294944443, j = j % 4294944443, i = null, r = function () {
                    var b, c;
                    b = 1403580 * g - 810728 * d;
                    b -= 4294967087 * (b / 4294967087 | 0);
                    0 > b && (b += 4294967087);
                    d = g;
                    g = f;
                    f = b;
                    c = 527612 * j - 1370589 * h;
                    c -= 4294944443 * (c / 4294944443 | 0);
                    0 > c && (c += 4294944443);
                    h = m;
                    m = j;
                    j = c;
                    return b <= c ? b - c + 4294967087 : b - c
                }, p = function () {
                    return 2.3283064365386963E-10 * r()
                };
                p.uint32 = r;
                p.fract53 = function () {
                    return p() + 1.1102230246251565E-16 * (r() & 2097151)
                };
                p.version = "MRG32k3a 0.9";
                p.args = c;
                return p
            }(Array.prototype.slice.call(arguments))
        },
        rand: function (b, c) {
            var c = "undefined" == typeof c ? "" : c, d = this.MRG32k3a(b || 0, location.href, Date.now());
            return c + (+new Date).toString(32) + parseInt(1E5 * d()).toString(32)
        }, seid: function () {
            var b = this.cookie("seid");
            if (!b || +new Date > (parseInt(this.cookie("seidtimeout")) || 0))b = this.rand(this.cookie("u_id"), "0"), this.cookie("seid", b, {
                domain: "youku.com",
                path: "/"
            }), b = this.cookie("seid") || 1;
            var c = this.cookie("referhost");
            if (!c || +new Date > (parseInt(this.cookie("seidtimeout")) || 0))c = (c = /^https?:\/\/[^\/]+/.exec(document.referrer ||
                "")) ? c[0] : "", this.cookie("referhost", c, {domain: "youku.com", path: "/"});
            this.cookie("seidtimeout", Date.now() + 18E5, {domain: "youku.com", path: "/"});
            return b
        }, juid: function () {
            var b = this.cookie("juid");
            b || (b = this.rand(this.cookie("u_id"), "0"), this.cookie("juid", b, {
                expires: 36500,
                domain: "youku.com",
                path: "/"
            }), b = this.cookie("juid") || 1);
            return window.juidStr = b
        }
    };
    var M = {
        "-100": "\u8be5\u89c6\u9891\u6b63\u5728\u8f6c\u7801\u4e2d... , \u8bf7\u7a0d\u5019",
        "-101": "\u8be5\u89c6\u9891\u6b63\u5728\u5ba1\u6838\u4e2d... , \u8bf7\u7a0d\u5019",
        "-102": "\u8be5\u89c6\u9891\u5df2\u88ab\u5c4f\u853d",
        "-103": "\u8be5\u89c6\u9891\u8f6c\u7801\u5931\u8d25",
        "-201": "\u8be5\u89c6\u9891\u88ab\u8bbe\u4e3a\u79c1\u5bc6",
        "-202": "\u8be5\u89c6\u9891\u5df2\u7ecf\u52a0\u5bc6",
        "-203": "\u5bf9\u4e0d\u8d77\uff0c\u60a8\u8f93\u5165\u7684\u5bc6\u7801\u9519\u8bef\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165",
        "-204": "\u7c89\u4e1d\u89c2\u770b\u89c6\u9891",
        "-301": "",
        "-302": "\u4ed8\u8d39\u89c6\u9891\u8d85\u8fc7\u89c2\u770b\u4e0a\u9650\u6b21\u6570",
        "-303": "\u4ed8\u8d39\u89c6\u9891\u4e0b\u7ebf",
        "-306": "\u8d26\u53f7\u5206\u4eab\u4e0d\u5408\u6cd5, IP\u4e0a\u9650",
        "-307": "\u4ed8\u8d39\u89c6\u9891, \u672a\u767b\u5f55",
        "-401": "\u96c6\u56e2\u64ad\u63a7\u7cfb\u7edf\u9650\u5236",
        "-402": "\u7528\u6237\u6ca1\u6709\u6743\u9650\u89c2\u770b(\u9632\u76d7\u94fenonce)",
        "-501": "\u670d\u52a1\u5668\u53d1\u751f\u9519\u8bef",
        "-601": "\u53c2\u6570\u9519\u8bef"
    }, T = function (b, e) {
        this.player = b;
        this._handle = {};
        this._feedback = c.get(".x-feedback");
        this._message = this._feedback.getElementsByClassName("x-message")[0];
        this._messagetxt =
            this._message.getElementsByClassName("x-message-txt")[0];
        this._messagebtn = this._message.getElementsByClassName("x-message-btn")[0];
        this._errorcode = this._error = null;
        this.init(e);
        this.bindEvent()
    };
    T.prototype = {
        init: function (b) {
            if (b && (b.data && b.data && b.data.error) && (c.hide(c.get(".x-video-button")), c.hide(c.get(".x-console")), this._vid = b.data.id, this._title = b.data.video.title, this._userid = b.data.video.userid, this._error = b.data.error, this._errorcode = parseInt(b.data.error.code), !(-301 == this._errorcode ||
                -307 == this._errorcode || -308 == this._errorcode))) {
                switch (this._errorcode) {
                    case -100:
                        this.setMessage(M["-100"]);
                        f.onError({
                            code: "1000",
                            message: "\u89c6\u9891\u8f6c\u7801\u4e2d"
                        }, "\u89c6\u9891\u8f6c\u7801\u4e2d");
                        break;
                    case -101:
                        this.setMessage(M["-101"]);
                        f.onError({
                            code: "1000",
                            message: "\u89c6\u9891\u5ba1\u6838\u4e2d"
                        }, "\u89c6\u9891\u5ba1\u6838\u4e2d");
                        break;
                    case -102:
                        this.setMessage(M["-102"]);
                        this.setButton("\u641c\u7d22", this.search);
                        f.onError({code: "2999", message: "\u89c6\u9891\u5df2\u88ab\u5c4f\u853d"},
                            "\u89c6\u9891\u5df2\u88ab\u5c4f\u853d");
                        break;
                    case -103:
                        this.setMessage(M["-103"]);
                        this.bind_feedback = c.bindAsEventListener(this, this.feedback);
                        this.setButton("\u5728\u7ebf\u53cd\u9988", this.bind_feedback);
                        f.onError({
                            code: "2999",
                            message: "\u89c6\u9891\u8f6c\u7801\u5931\u8d25"
                        }, "\u89c6\u9891\u8f6c\u7801\u5931\u8d25");
                        break;
                    case -201:
                        this.setMessage(M["-201"]);
                        this.bind_contact = c.bindAsEventListener(this, this.contactOwner);
                        this.setButton("\u8054\u7cfb\u4e0a\u4f20\u8005", this.bind_contact);
                        f.onError({
                            code: "1000",
                            message: "\u79c1\u5bc6\u89c6\u9891\uff0c\u7528\u6237\u6ca1\u6709\u6743\u9650\u89c2\u770b"
                        }, "\u79c1\u5bc6\u89c6\u9891\uff0c\u7528\u6237\u6ca1\u6709\u6743\u9650\u89c2\u770b");
                        break;
                    case -202:
                        this._messagetxt.innerHTML = "<input type=password placeholder=\u8f93\u5165\u5bc6\u7801\u89c2\u770b\u89c6\u9891 class=x-message-input>";
                        this.bind_inputpassword = c.bindAsEventListener(this, this.inputPassword);
                        this.setButton("\u786e\u5b9a", this.bind_inputpassword);
                        break;
                    case -203:
                        this._messagetxt.innerHTML = '<input type=password placeholder="\u5bf9\u4e0d\u8d77,\u60a8\u8f93\u5165\u7684\u5bc6\u7801\u9519\u8bef,\u8bf7\u91cd\u65b0\u8f93\u5165" class=x-message-input>';
                        this.bind_inputpassword = c.bindAsEventListener(this, this.inputPassword);
                        this.setButton("\u786e\u5b9a", this.bind_inputpassword);
                        break;
                    case -306:
                        this._messagetxt.innerHTML = '<a style="color:#3399e0;text-decoration:underline;position:relative;top:3px;" href="' + b.data.error.link + '" target="_blank">' + b.data.error.note + "</a>";
                        f.onError({
                            code: "2008",
                            message: "\u8d26\u53f7\u7591\u4f3c\u88ab\u5206\u4eab"
                        }, "\u5e10\u53f7\u5206\u4eab\u4e0d\u5408\u6cd5");
                        break;
                    default:
                        this.setMessage(b.data.error.note), f.onError({
                            code: "1000",
                            message: b.data.error.note
                        }, b.data.error.note)
                }
                this.show();
                this.showMessage()
            }
        }, bindEvent: function () {
        }, show: function () {
            c.show(this._feedback)
        }, hide: function () {
            c.hide(this._feedback)
        }, showMessage: function () {
            c.show(this._message)
        }, hideMessage: function () {
            c.hide(this._message)
        }, setMessage: function (b) {
            this._messagetxt.innerHTML = "<p>" + b + "</p>"
        }, setButton: function (b, e) {
            this._messagebtn.innerHTML = "<button type=button class=x-btn>" + b + "</button>";
            var d = this._message.getElementsByClassName("x-btn")[0];
            c.addEventHandler(d,
                "click", e)
        }, search: function () {
            window.location.href = "http://www.soku.com/search_video/q_" + this._title
        }, feedback: function () {
            window.location.href = "http://www.youku.com/service/feed/subtype/4/"
        }, contactOwner: function () {
            window.location.href = "http://i.youku.com/u/id_" + this._userid
        }, onPasswordConfirm: function () {
        }, inputPassword: function () {
            var b = this._messagetxt.getElementsByClassName("x-message-input")[0], e = b.value;
            if (null == e || 0 == e.replace(/\s/g, "").length)b.value = "", b.placeholder = "\u5bc6\u7801\u4e3a\u7a7a\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165";
            else {
                var d = this.player;
                c.password = e;
                o.start(this._vid, e, c.config.content, function (b, f) {
                    c.hide(c.get(".x-feedback"));
                    c.password = e;
                    c.show(c.get(".x-video-button"));
                    c.hide(c.get(".x-message"));
                    d.startPlay(b, f)
                })
            }
        }
    };
    var ka = function (b) {
        this._handler = {};
        this.player = b;
        this._fullflag = null;
        this.init();
        this._fullscreen = c.get(".x-fullscreen");
        this._btn = this._fullscreen.getElementsByTagName("button")[0];
        this._btnb = this._btn.getElementsByTagName("b")[0];
        this.bindEvent()
    };
    ka.prototype = {
        addEventListener: function (b,
                                    c) {
            this._handler[b] = c
        }, removeEventListener: function (b) {
            this._handler[b] = null
        }, dispatch: function (b) {
            b && this._handler[b.type] && (b._target = this, this._handler[b.type](b))
        }, init: function () {
        }, bindEvent: function () {
            this.bind_switch = c.bindAsEventListener(this, this.switchFullScreen);
            c.addEventHandler(this._fullscreen, "click", this.bind_switch, !0)
        }, removeEvent: function () {
            c.removeEventHandler(this._fullscreen, "click", this.bind_switch, !0)
        }, zoomStatus: function () {
            return this._btnb.className
        }, fullFlag: function () {
            if (null !==
                this._fullflag)return this._fullflag;
            var b = this.player.video.fullscreenchange;
            return this._fullflag = "undefined" != typeof b ? b : !1
        }, switchFullScreen: function (b) {
            var e = b.method || "c", d = this._btnb.className;
            c.config.events && c.config.events.onSwitchFullScreen ? (-1 === d.indexOf("in") ? (this._fullflag = !1, this._btnb.className = d.replace(/out/g, "in"), this.player.controls.hideShowListBtn(), this.player._reporter.sendUserActionReport("xexfs", e), this.player.adjustVideoRatio(1), this.dispatch({type: "exitfullscreen"})) :
                (this._fullflag = !0, this._btnb.className = d.replace(/in/g, "out"), this.player.controls.showShowListBtn(), this.player._reporter.sendUserActionReport("xenfs", e), this.player.adjustVideoRatio(), this.dispatch({type: "enterfullscreen"})), e = c.config.events.onSwitchFullScreen, e(b, d)) : (b = document.getElementById("x-player"), -1 === d.indexOf("in") ? (this.player._reporter.sendUserActionReport("xexfs", e), document.webkitCancelFullScreen && (this._btnb.className = d.replace(/out/g, "in"), this._fullflag = !1, document.webkitCancelFullScreen())) :
                (this.player._reporter.sendUserActionReport("xenfs", e), b.webkitRequestFullScreen ? (this._btnb.className = d.replace(/in/g, "out"), this._fullflag = !0, b.webkitRequestFullScreen()) : this.player.video.webkitSupportsFullscreen && 1 <= this.player.video.readyState && this.player.video.webkitEnterFullscreen()))
        }
    };
    var la = function (b, e) {
        this.handler = {};
        this.player = b;
        this.information = c.get(".x-video-info");
        this.title = this.information.getElementsByClassName("x-title")[0];
        this.videoState = this.information.getElementsByClassName("x-video-state")[0];
        c.hide(this.videoState);
        this.init(e)
    };
    la.prototype = {
        init: function (b) {
            this.title.innerHTML = b.data.show && b.data.show.title ? b.data.show.title.substr(0, 20) : b.data.video.title.substr(0, 20);
            if (b.data.trial || b.data.error)if ("episodes" == c.v.data.trial.type)this.show(); else return;
            this.videoState.innerHTML = "<span>\u65f6\u957f: " + c.getTime(parseInt(b.data.video.seconds)) + "</span>";
            this.show()
        }, show: function () {
            if (c.v.data.trial)if ("episodes" == c.v.data.trial.type)c.show(this.information); else return;
            c.show(this.information)
        },
        hide: function () {
            c.hide(this.information)
        }, bindEvent: function () {
        }
    };
    var ma = function (b) {
        this.player = b;
        this._tip = c.get(".x-prompt");
        this.init()
    };
    ma.prototype = {
        init: function () {
            this._tip.innerHTML = '<div class=x-prompt-mode><div class=x-prompt-time></div><div class=x-prompt-forward>\u5feb\u8fdb</div><div class=x-prompt-back>\u5feb\u9000</div><div class=x-mask></div></div><div class=x-prompt-status style="display:none"><div class=x-prompt-txt></div><div class=x-mask></div></div>';
            this._mode = this._tip.getElementsByClassName("x-prompt-mode")[0];
            this._time = this._tip.getElementsByClassName("x-prompt-time")[0];
            this._back = this._tip.getElementsByClassName("x-prompt-back")[0];
            this._forward = this._tip.getElementsByClassName("x-prompt-forward")[0];
            this._status = this._tip.getElementsByClassName("x-prompt-status")[0];
            this._statusTxt = this._tip.getElementsByClassName("x-prompt-txt")[0]
        }, setProgress_: function (b) {
            !0 != this._progressFlag && (this._time.innerHTML = c.getTime(parseInt(b)))
        }, setStatus: function (b) {
            this._statusTxt.innerHTML = b;
            this.showStatus()
        }, hideStatus: function () {
            c.hide(this._status);
            c.hide(this._tip)
        }, showStatus: function () {
            c.hide(this._mode);
            c.show(this._status);
            c.show(this._tip)
        }, setTip: function (b, e) {
            this._progressFlag = !0;
            this._time.innerHTML = c.getTime(Ga(b + e));
            0 >= e ? (c.show(this._back), c.hide(this._forward)) : (c.show(this._forward), c.hide(this._back));
            var d = this;
            setTimeout(function () {
                d._progressFlag = false
            }, 1E3)
        }, isVisible: function () {
            return "none" != this._tip.style.display
        }, hide: function () {
            c.hide(this._tip)
        }, show: function () {
            c.show(this._mode);
            c.hide(this._status);
            c.show(this._tip)
        },
        autoHide: function (b) {
            var c = this;
            setTimeout(function () {
                c.hide()
            }, b || 1E3)
        }
    };
    var na = function (b, e) {
        var d = !0;
        this._handler = {};
        if (c.isWeixin)c.get(".x-localization").style.display = "none"; else if (!e || !e.data || !e.data || !e.data.dvd || !e.data.dvd.audiolang)c.get(".x-localization").style.display = "none"; else {
            if (c.videoInfo._videoSegsDic) {
                var g = c.videoInfo._videoSegsDic.streams, f = !1, h;
                for (h in g) {
                    d = "";
                    for (k in g[h])d += k + ",";
                    (d = -1 < d.indexOf("3gphd") || -1 < d.indexOf("mp4") ? !1 : !0) && (f = !0)
                }
                if (d && f) {
                    c.get(".x-localization").style.display =
                        "none";
                    return
                }
            }
            this.player = b;
            this._language = c.get(".x-localization");
            this.init(e);
            this.bindEvent();
            this._button = this._language.getElementsByTagName("button")[0];
            this._panel = this._language.getElementsByTagName("div")[0];
            this._nodes = this._language.getElementsByTagName("li")
        }
    };
    na.prototype = {
        addEventListener: function (b, c) {
            this._handler[b] = c
        }, removeEventListener: function (b) {
            this._handler[b] = null
        }, dispatch: function (b) {
            b && this._handler[b.type] && (b._target = this, this._handler[b.type](b))
        }, init: function (b) {
            for (var b =
                b.data, c = b.dvd.audiolang, d = ["<button class=x-control-btn>", "", "</button>"], g = ["<div class=x-panel><ul>", "", "</ul><div class=x-mask></div>", "</div>"], f = [], h = 0; h < c.length; h++) {
                var m = "", m = m + ("<li data-vid=" + c[h].vid), m = m + (" data-language=" + c[h].lang), m = m + (" data-language-code=" + c[h].langcode);
                c[h].vid == b.video.encodeid ? (d[1] = c[h].lang, m += " class=selected>") : m += ">";
                m += c[h].lang + "</li>";
                f[h] = m
            }
            g[1] = f.join("");
            this._language.innerHTML = d.join("") + g.join("")
        }, bindEvent: function () {
            var b = this._language.getElementsByTagName("li");
            if (0 != b.length) {
                this.bind_toggle = c.bindAsEventListener(this, this.toggleLanguagePanel);
                c.addEventHandler(this._language, "click", this.bind_toggle);
                for (var e = 0; e < b.length; e++)c.addEventHandler(b[e], "click", c.bindAsEventListener(this, this.switchLanguage))
            }
        }, removeEvent: function () {
            null != this._language && c.removeEventHandler(this._language, "click", this.bind_toggle)
        }, hide: function (b) {
            if (this._language) {
                var c = this._panel;
                this._language.className = this._language.className.replace(/[\s]*pressed/g, "");
                c.style.display =
                    "none";
                b || this.dispatch({type: "settinghide"})
            }
        }, toggleLanguagePanel: function (b) {
            var c = this._panel;
            -1 === this._language.className.indexOf("pressed") ? (this._language.className += " pressed", c.style.display = "block", this.dispatch({type: "settingshow"}), this.player._reporter.sendUserActionReport("xcl", "c")) : (this.hide(), this.player._reporter.sendUserActionReport("xhl", "c"));
            this.dispatch(b)
        }, switchLanguage: function (b) {
            this.player._reporter.sendUserActionReport("xsl", "c");
            b.stopPropagation();
            var b = b.target,
                e = null;
            b.getAttribute ? (e = b.getAttribute("data-vid"), b.getAttribute("data-language"), b = b.getAttribute("data-language-code")) : (e = b.parentNode.getAttribute("data-vid"), b.parentNode.getAttribute("data-language"), b = b.parentNode.getAttribute("data-language-code"));
            for (var d = this._nodes, g = 0; g < d.length; g++)if (d[g].getAttribute("data-vid") == e) {
                if (-1 !== d[g].className.indexOf("selected")) {
                    this.toggleLanguagePanel();
                    return
                }
                d[g].innerHTML = d[g].getAttribute("data-language");
                d[g].className += " selected";
                this._button.innerHTML =
                    d[g].getAttribute("data-language")
            } else d[g].innerHTML = d[g].getAttribute("data-language"), d[g].className = d[g].className.replace(/[\s]*selected/g, "");
            this.toggleLanguagePanel();
            this.dispatch({type: "settingdone"});
            var f = this.player, h = this.player.currentTime;
            v.log("switchLanguage vid = " + e);
            c.config.nextAutoPlay = 1;
            if (null != c.videoInfo._videoSegsDic && null != c.videoInfo._videoSegsDic.streams[b]) {
                if ("m3u8" == c.config.content)c.defaultLanguage = b, f.video.src = c.m3u8src_v2(e, c.defaultVideoType); else {
                    e = c.videoInfo._videoSegsDic.streams[b];
                    if (e[c.defaultVideoType])c.defaultLanguage = b; else {
                        d = ["3gphd", "mp4"];
                        for (g = 0; g < d.length; g++)if (e[d[g]]) {
                            c.defaultVideoType = d[g];
                            c.defaultLanguage = b;
                            break
                        }
                    }
                    f.video.src = c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType][0].src
                }
                f.video.load();
                f.video.play()
            }
            var m = 0;
            f.video.addEventListener("canplay", function () {
                if (m !== 1) {
                    m = 1;
                    f.seek(h)
                }
            })
        }
    };
    var i = {
        setItem: function (b, c) {
            try {
                window.localStorage.setItem(b, c)
            } catch (d) {
            }
        }, appendItem: function (b, c) {
            "phase" == b && !this.phaseTag && (this.phaseTag = !0, i.removeItem("phase"));
            try {
                var d = i.getItem(b);
                null !== d && (c = d + "-" + c);
                window.localStorage.setItem(b, c)
            } catch (g) {
            }
        }, getItem: function (b) {
            try {
                return window.localStorage.getItem(b)
            } catch (c) {
                return null
            }
        }, removeItem: function (b) {
            try {
                window.localStorage.removeItem(b)
            } catch (c) {
            }
        }
    }, oa = function (b) {
        this.player = b;
        this._progress = c.get(".x-progress-mini");
        this._track = this._progress.getElementsByClassName("x-progress-track-mini")[0];
        this._play = this._progress.getElementsByClassName("x-progress-play-mini")[0];
        this._load =
            this._progress.getElementsByClassName("x-progress-load-mini")[0];
        this._handler = {};
        this.bindEvent();
        this.resetProgress();
        this.hide()
    };
    oa.prototype = {
        addEventListener: function (b, c) {
            this._handler[b] = c
        }, removeEventListener: function (b) {
            this._handler[b] = null
        }, bindEvent: function () {
        }, removeEvent: function () {
        }, dispatch: function (b) {
            if (b && this._handler[b.type])this._handler[b.type]()
        }, setProgress: function (b, e) {
            var d = Math.min(b, c.videoInfo.totalTime);
            this.playTime = d;
            var g = d / c.videoInfo.totalTime;
            this._play.style.width =
                100 * g + "%";
            !0 !== e && (this.loadTime = d += Math.max(this.player.bufferedEnd() - b, 0), g = d / c.videoInfo.totalTime + 0.05, this._load.style.width = 100 * Math.min(Math.max(g, 0), 1) + "%")
        }, resetProgress: function () {
            this._play.style.width = "0%";
            this._load.style.width = "0%"
        }, show: function () {
            this._progress.style.display = "block"
        }, hide: function () {
            this._progress.style.display = "none"
        }
    };
    var pa = function (b, e) {
        this._handler = {};
        this._hasPayInfo = !1;
        this._payInfo = c.get(".x-pay", b.root);
        this._text = c.get(".x-pay-txt", this._payInfo);
        this._title =
            this._text.getElementsByTagName("h1")[0];
        this._vip = this._text.getElementsByTagName("em")[0];
        this._tip = c.get(".x-pay-tips", this._payInfo);
        this._button = c.get(".x-pay-btn", this._payInfo);
        this._tryBtn = c.get(".x-btn-try", this._button);
        this._payBtn = c.get(".x-btn-pay", this._button);
        this._panel = c.get(".x-pay-panel", b.root);
        this._panelTitle = c.get(".x-pay-txt", this._panel);
        this._panelTip = c.get(".x-pay-title", this._panel);
        this._panelTry = c.get(".x-try", this._panel);
        this._panelPay = c.get(".x-buy", this._panel);
        this.player = b;
        this.init(e)
    };
    pa.prototype = {
        addEventListener: function (b, c) {
            this._handler[b] = c
        }, removeEventListener: function (b) {
            this._handler[b] = null
        }, dispatch: function (b) {
            b && this._handler[b.type] && (b._target = this, this._handler[b.type](b))
        }, bindEvent: function () {
            this.bind_try = c.bindAsEventListener(this, this.play);
            this.bind_pay = c.bindAsEventListener(this, this.pay);
            c.addEventHandler(this._tryBtn, "touchend", this.bind_try);
            c.addEventHandler(this._payBtn, "touchend", this.bind_pay);
            c.addEventHandler(this._panelTry,
                "touchend", this.bind_try);
            c.addEventHandler(this._panelPay, "touchend", this.bind_pay)
        }, removeEvent: function () {
            c.removeEventHandler(this._tryBtn, "touchend", this.bind_try);
            c.removeEventHandler(this._payBtn, "touchend", this.bind_pay);
            c.addEventHandler(this._panelTry, "touchend", this.bind_try);
            c.addEventHandler(this._panelPay, "touchend", this.bind_pay)
        }, init: function (b) {
            var c = this, d = {}, g = !0, f = b.data.video;
            if (b.data.trial && f.type && "episodes" != b.data.trial.type) {
                f = f.type.join(",") + ",";
                if (!(0 <= f.indexOf("fee,")))if (0 <=
                    f.indexOf("channel_vip,"))g = !1; else return;
                "vod" == this._type ? (d.product_desc = "<\u4ed8\u8d39\u5f71\u7247>", d.buy_desc = "\u7acb\u5373\u8d2d\u4e70") : (d.product_desc = "<\u4ed8\u8d39\u5305\u6708\u5f71\u7247>", d.buy_desc = "\u5f00\u901a\u4f1a\u5458");
                this._type = b.data.show.pay_type;
                if (!g && b.data.error)switch (parseInt(b.data.error.code)) {
                    case -301:
                        break;
                    case -307:
                        d.product_desc = "<\u4ed8\u8d39\u8282\u76ee\uff0c\u8bf7\u5148\u767b\u5f55\uff01>";
                        d.buy_desc = "";
                        break;
                    default:
                        d.product_desc = "<\u81ea\u9891\u9053\u4f1a\u5458\u89c6\u9891>",
                            d.buy_desc = ""
                }
                c._hasPayInfo = !0;
                this._tryDuration = parseInt(b.data.trial.time || 0);
                this.player.tryDuration = this._tryDuration;
                h.log("try = " + this._tryDuration);
                this.processData(d, b);
                this.bindEvent();
                this._payData = d;
                g && c.getData({vid: b.data.id}, function (d) {
                    c._payData = d;
                    c.processData(d, b);
                    b.data.error && c.showTip()
                }, function () {
                    b.data.error && c.showTip()
                });
                0 >= this._tryDuration ? this.showTip() : this.show()
            }
        }, processData: function (b, e) {
            var d = b.product_desc || "", g = e.data.video.title;
            12 < g.length && (g = g.substr(0, 12) +
                "...");
            d = " " + d.replace(/<[^(<||>)]{0,}>/g, function (b) {
                    var c = b.indexOf("<"), e = b.indexOf(">");
                    return b.substring(0, c) + " <span class=x-vip>" + b.substring(c + 1, e) + "</span> " + b.substring(e + 1) + " "
                });
            this._title.innerHTML = g + d;
            this._payBtn.innerHTML = b.buy_desc;
            this._panelTitle.innerHTML = "<h1>" + e.data.video.title + "</h1>";
            this._panelTip.innerHTML = '<i class="x-icon-prompt"></i>' + d;
            (this._panelPay.innerHTML = b.buy_desc) ? (this._payBtn.style.cssText = "", this._panelPay.style.cssText = "") : (c.hide(this._payBtn), c.hide(this._panelPay))
        },
        getData: function (b, c, d) {
            s.jsonp({
                url: "http://vip.youku.com/",
                data: {c: "xhr", a: "h5_player_get_pay_info", video_id: b.vid},
                success: function (b) {
                    b.code && b.result ? c(b.result) : d(b)
                },
                fail: function (b) {
                    d(b)
                }
            })
        }, play: function () {
            if ((f.isIPHONE || f.isIPOD) && null != c.v.data.trial)this.player.video.style.display = "block";
            0 === this.activeTime || this.player.currentTime >= this._tryDuration ? (this.player.replay(), this.activeTime = -1) : this.player.video.load();
            this.player._reporter.sendUserActionReport("xtry", "c");
            c.hide(this._panel)
        },
        pay: function () {
            this.player.video.pause();
            this._payData.buy_link ? window.open(this._payData.buy_link, "", "", !1) : (c.v.data.pay && c.v.data.pay.h5_caseurl && window.open(c.v.data.pay.h5_caseurl, "", "", !1), this.player._reporter.sendUserActionReport("xbuy", "c"))
        }, hide: function () {
            this._payInfo && (this._payInfo.style.display = "none")
        }, show: function () {
            !1 != this._hasPayInfo && (this._payInfo.style.display = "block", 0 >= this._tryDuration && c.hide(this._tryBtn))
        }, isBlock: function () {
            return "block" == this._payInfo.style.display
        },
        showTip: function () {
            this._hasPayInfo && (this._panel.style.display = "block", 0 >= this._tryDuration && c.hide(this._panelTry))
        }, hideTip: function () {
            this._hasPayInfo && c.hide(this._panel)
        }, clearTip: function () {
            this._tip.innerHTML = ""
        }, hasPayInfo: function () {
            return this._hasPayInfo
        }, tryDuration: function () {
            return this._tryDuration
        }
    };
    var qa = function (b, e, d) {
        this.player = b;
        this._videoInfo = e;
        this.hasPausead = !1;
        this._limitTime = 1E5;
        this._html5_disable = !1;
        b = this._videoInfo.data;
        this.cateId = b.video.category_id;
        this.statCommon =
            new ja;
        this._limitVip = !0 == b.user.vip || (b.pay && !0 == b.pay.can_play ? !0 : !1);
        this.needOpen = this._isLimit = !1;
        var e = parseInt(c.videoInfo.totalTime, 10), g = b.show && b.show.showkind, h = !1;
        if (g)for (var n = g.length; n--;)if (-1 != g[n].indexOf("PGC")) {
            h = !0;
            break
        }
        if (f.isIPAD) {
            if (h || b.controller && !0 == b.controller.app_disable) {
                this._isLimit = !1;
                return
            }
            this._isLimit = !0;
            b.trial && "h5" == b.trial.type ? (this.needOpen = !0, this._limitTime = b.trial.time || 600) : (this.needOpen = !1, this._limitTime = e);
            if (!this._limitVip) {
                if (600 <= e)switch (this.cateId) {
                    case 100:
                    case 103:
                    case 176:
                    case 95:
                    case 90:
                        this.needOpen = !0, this._limitTime = 600
                }
                99 == this.cateId && 300 <= e && (this.needOpen = !0, this._limitTime = 300)
            }
            try {
                v.log(playPageUrl)
            } catch (m) {
                d && (this._isLimit = !1), this.needOpen = !1, this._limitTime = e
            }
        }
        this._isLimitNormal = b.controller && !0 == b.controller.html5_disable;
        this._isEpisodes = b.trial && "episodes" == b.trial.type;
        this._isCreated = !1;
        this.xplayer = c.get("#x-player");
        c.videoInfo.totalTime = e > this._limitTime ? this._limitTime : e;
        if (this._isLimit || this._isLimitNormal)this._html5_disable = !0;
        this._html5_disable && (this.player.tryDuration =
            this._limitTime)
    };
    qa.prototype = {
        create: function () {
            var b = this;
            if (!this._isCreated) {
                var e;
                e = 99 == this.cateId && this.needOpen ? "x-app-guide-5min" : 600 == c.videoInfo.totalTime ? "x-app-guide" : "x-app-guide-other";
                this._isLimit && (this.xplayer.appendChild(this._createTemplate("x-app-bg", "&nbsp;")), this.xplayer.appendChild(this._createTemplate(e, '<div class="x-app-guide-par"><p class="x-btn-closep">&nbsp;</p><div class="x-app-openapp"></div></div>')), this._content = c.get("." + e), this._bg = document.querySelectorAll(".x-app-bg")[0],
                    this._closeBtn = this._content.getElementsByClassName("x-btn-closep")[0], this._downloadBtn = this._content.getElementsByClassName("x-app-openapp")[0], c.addEventHandler(this._closeBtn, "click", function () {
                    b.limitAppHide()
                }), c.addEventHandler(this._downloadBtn, "touchend", function () {
                    b.openApp()
                }), this._isCreated = !0)
            }
        }, _createTemplate: function (b, c) {
            var d = document.createElement("div");
            d.className = b;
            d.innerHTML = c;
            return d
        }, limitAppShow: function () {
            this._isLimit && (this.player.video.pause(), this._isCreated ? (this._content.style.display =
                "block", this._bg.style.display = "block") : this.create())
        }, limitAppEnd: function () {
            if (this._isLimit && this._content) {
                this.player.video.pause();
                for (var b = "x-video-logo x-video-loading x-video-info x-video-button x-feedback x-pay x-pay-panel x-advert x-ad-pause x-prompt x-dashboard x-showlist x-tips x-trigger".split(" "), c = 0, d = b.length; c < d; c++) {
                    var g = document.querySelectorAll("." + b[c]);
                    g && this.xplayer.removeChild(g[0])
                }
                this._content.style.display = "block";
                this._bg.style.display = "block";
                this.needOpen && this.openApp()
            }
        },
        limitAppHide: function () {
            this._isLimit && (this._content.style.display = "none", this._bg.style.display = "none")
        }, onDownloadClick: function () {
            f.isAndroid ? window.open("http://dl.m.cc.youku.com/android/phone/Youku_Android_xianbobofangqi.apk") : window.location.href = "http://hz.youku.com/red/click.php?tp=1&cp=4007554&cpp=1000673&url=https://itunes.apple.com/cn/app/id394075284?l=cn&mt=8"
        }, toReload: function () {
            f.Log("http://hz.youku.com/red/click.php?tp=1&cp=4009216&cpp=1000752&url=");
            setTimeout(function () {
                    window.location.reload()
                },
                500)
        }, getYoukuAppSchema: function (b, c) {
            var d, g = [];
            c ? d = "http://iosport.youku.com/ipad/ulink?" : (d = "youku://", "ipad" == b.client ? d = "youkuhd://" : "isAndroidChrome" == b.client && (d = "intent://"), d += "play?");
            g.push("vid=" + b.id);
            g.push("pid=" + b.pid);
            g.push("guid=" + b.guid);
            g.push("idfa=" + b.idfa);
            g.push("ouid=" + b.ouid);
            g.push("source=" + b.source);
            g.push("ua=" + b.ua);
            g.push("ver=" + b.ver);
            g.push("datetime=" + b.datetime);
            g.push("from=" + encodeURIComponent(window.location.href));
            g.push("pagetype=1");
            g.push("cookieid=" + b.cookieid);
            g.push("tuid=" + b.tuid);
            g.push("refer=" + (b.refer || "87c959fb273378eb"));
            g.push("special=" + b.special);
            g.push("sender=" + b.sender);
            d += g.join("&");
            "isAndroidChrome" == b.client && (d += "#Intent;scheme=youku;package=com.youku.phone;end;");
            return d
        }, openApp: function () {
            var b = navigator.userAgent, c = b.toLowerCase();
            /iPhone/.test(b) || /iPod/.test(b) || /iPad/.test(b);
            var d = function (b) {
                if (!b)return null;
                b = /OS (\d_\d(_\d)?)/.exec(b);
                if (!b)return null;
                b = b[1].split("_").map(function (b) {
                    return parseInt(b, 10)
                });
                return {
                    major: b[0],
                    minor: b[1], patch: b[2] || 0
                }
            }(b);
            document.addEventListener("visibilitychange", function n() {
                document.removeEventListener("visibilitychange", n, !1)
            }, !1);
            var c = b.toLocaleLowerCase(), c = -1 < c.indexOf("micromessenger") ? "wechat" : -1 < c.indexOf("ucbrowser") ? "uc" : -1 < c.indexOf("weibo") ? "weibo" : "other", g = !1;
            8 < d.major && (g = !0);
            this.YoukuAppSchema = this.getYoukuAppSchema({
                id: this._videoInfo.data.video.encodeid,
                client: f.isIPAD && "ipad" || -1 != b.indexOf("Android") && -1 < b.indexOf("Chrome") && null == b.match(/Chrome\/\d+\.0\.0\.0/i) &&
                "androidChrome",
                pid: "87c959fb273378eb",
                guid: this.statCommon.seid(),
                idfa: "",
                ouid: "",
                source: "mplaypage4",
                ua: c,
                ver: "",
                datetime: Math.floor(+new Date / 1E3),
                refer: "pad-play",
                cookieid: this.statCommon.juid(),
                tuid: 0,
                special: 0,
                sender: 1
            }, g);
            8 >= d.major ? (b = document.createElement("iframe"), b.height = 0, b.width = 0, b.frameBorder = "no", b.src = this.YoukuAppSchema, document.getElementsByTagName("body")[0].appendChild(b)) : window.location.href = this.YoukuAppSchema
        }, isLimit: function () {
            return this._isLimit
        }, limitTime: function () {
            return this._limitTime
        }
    };
    var I = {2: "2\u500d", "1.5": "1.5\u500d", 1: "\u5e38\u901f", "0.8": "0.8\u500d"}, ra = function (b, e) {
        this._handler = {};
        !c.isWeixin && f.isIPAD7 && (this.player = b, this.playRate = c.get(".x-playspeed"), this.init(e), this.bindEvent(), this.button = this.playRate.getElementsByTagName("button")[0], this.panel = this.playRate.getElementsByTagName("div")[0], this.nodes = this.playRate.getElementsByTagName("li"), c.show(this.playRate))
    };
    ra.prototype = {
        addEventListener: function (b, c) {
            this._handler[b] = c
        }, removeEventListener: function (b) {
            this._handler[b] =
                null
        }, dispatch: function (b) {
            b && this._handler[b.type] && (b._target = this, this._handler[b.type](b))
        }, init: function () {
            for (var b = ["<button class=x-control-btn>", "", "</button>"], c = ['<div class=x-panel style="display:none"><ul>', "", "</ul><div class=x-mask></div>", "</div>"], d = "", g = [], f = ["2", "1.5", "1", "0.8"], h = 0; h < f.length; h++) {
                var m = f[h], j = "", i = "";
                "1" == m && (j = "", b[1] = I[m], i = " class=selected");
                d += "<li data-vtype=" + m + i + ">" + j + I[m] + "</li>";
                g.push(I[m])
            }
            c[1] = d;
            this.playRate.innerHTML = b.join("") + c.join("")
        }, bindEvent: function () {
            var b =
                this.playRate.getElementsByTagName("li");
            if (0 != b.length) {
                this.bind_toggle = c.bindAsEventListener(this, this.toggleRatePanel);
                c.addEventHandler(this.playRate, "click", this.bind_toggle);
                for (var e = 0; e < b.length; e++)c.addEventHandler(b[e], "click", c.bindAsEventListener(this, this.switchRate))
            }
        }, removeEvent: function () {
            null != this.playRate && c.removeEventHandler(this.playRate, "click", this.bind_toggle)
        }, hide: function (b) {
            if (this.playRate) {
                var c = this.panel;
                this.playRate.className = this.playRate.className.replace(/[\s]*pressed/g,
                    "");
                c.style.display = "none";
                b || this.dispatch({type: "settinghide"})
            }
        }, toggleRatePanel: function (b) {
            var c = this.panel;
            -1 === this.playRate.className.indexOf("pressed") ? (this.playRate.className += " pressed", c.style.display = "block", this.player._reporter.sendUserActionReport("xcra", "c"), this.dispatch({type: "settingshow"})) : (this.hide(), this.player._reporter.sendUserActionReport("xhra", "c"));
            this.dispatch(b)
        }, switchRate: function (b) {
            b.stopPropagation();
            var c = b.target, b = null, b = c.getAttribute ? c.getAttribute("data-vtype") :
                c.parentNode.getAttribute("data-vtype");
            this.player._reporter.sendUserActionReport("xsra", "c", {rate: b});
            for (var c = this.button, d = this.nodes, g = 0; g < d.length; g++)if (d[g].getAttribute("data-vtype") == b) {
                if (-1 !== d[g].className.indexOf("selected")) {
                    this.toggleRatePanel();
                    return
                }
                d[g].innerHTML = I[b];
                d[g].className += " selected";
                c.innerHTML = I[b]
            } else {
                var f = d[g].getAttribute("data-vtype");
                d[g].innerHTML = I[f];
                d[g].className = d[g].className.replace(/selected/, "")
            }
            this.toggleRatePanel();
            this.dispatch({type: "settingdone"});
            this.player.video.pause();
            this.player.video.playbackRate = parseFloat(b);
            this.player.video.play()
        }
    };
    var sa = function (b) {
        this.player = b;
        this.maybeSeek = !1;
        this._progress = c.get(".x-progress");
        this._track = this._progress.getElementsByClassName("x-progress-track")[0];
        this._play = this._progress.getElementsByClassName("x-progress-play")[0];
        this._load = this._progress.getElementsByClassName("x-progress-load")[0];
        this._seek = this._progress.getElementsByClassName("x-progress-seek")[0];
        this._seekHandle = this._seek.getElementsByClassName("x-seek-handle")[0];
        this._handler = {};
        this.bindEvent()
    };
    sa.prototype = {
        addEventListener: function (b, c) {
            this._handler[b] = c
        }, removeEventListener: function (b) {
            this._handler[b] = null
        }, bindEvent: function () {
            this.bind_seek = c.bindAsEventListener(this, this.seek);
            this.bind_touchstart = c.bindAsEventListener(this, this.onTouchStart);
            c.addEventHandler(this._track, "click", this.bind_seek, !0);
            c.addEventHandler(this._seek, "touchstart", this.bind_touchstart)
        }, removeEvent: function () {
            c.removeEventHandler(this._track, "click", this.bind_seek, !0);
            c.removeEventHandler(this._seek, "touchstart", this.bind_touchstart)
        }, removeClickEvent: function () {
            c.removeEventHandler(this._track, "click", this.bind_seek, !0)
        }, addClickEvent: function () {
            c.addEventHandler(this._track, "click", this.bind_seek, !0)
        }, dispatch: function (b) {
            if (b && this._handler[b.type])this._handler[b.type](b)
        }, setProgress: function (b, e) {
            var d = Math.min(Math.max(b, 0), c.videoInfo.totalTime);
            this.playTime = d;
            var g = d / c.videoInfo.totalTime, f = this._track.offsetWidth, h = this._seek.offsetWidth;
            this._play.style.width =
                Math.min(100 * (g + h / f / 2), 100) + "%";
            this._seek.style.left = g * f > f - h ? f - h + "px" : 100 * Math.min(Math.max(g, 0), 1) + "%";
            this.uCurrentTime.innerHTML = c.getTime(d);
            !0 !== e && (this.loadTime = d += Math.max(this.player.bufferedEnd() - b, 0), g = d / c.videoInfo.totalTime, this._load.style.width = 100 * Math.min(Math.max(g + 0.05, 0), 1) + "%")
        }, resetProgress: function () {
            this._seek.style.left = this._seek.style.width;
            this._load.style.width = "0";
            this._play.style.width = "0"
        }, getRate: function (b, e) {
            var d = 1, g = c.get(".x-fs-console");
            g && (d = parseFloat(c.getCurrentStyle(g).zoom));
            return b / (e * d)
        }, seek: function (b) {
            var e = (new Date).getTime() - ta;
            if (b.srcElement == this._seek || e < ua)return h.log(e + "," + ua), !1;
            this.player._reporter.sendUserActionReport("xcs", "c");
            e = b.offsetX || b.changedTouches[0].clientX - this._track.clientX;
            h.log("x = " + e);
            var e = this.getRate(e, this._track.offsetWidth), d = e * c.videoInfo.totalTime;
            h.log("progress bar time = " + d + "rate = " + e + "total = " + c.videoInfo.totalTime);
            this.setProgress(d, !0);
            this.dispatch({type: "progressend"});
            this.player.seek(d);
            this.dispatch(b)
        }, handleX: function () {
            return 0
        },
        onTouchStart: function (b) {
            this.maybeSeek = !0;
            if (1 != b.targetTouches.length || this.isTouching)return !1;
            this.startX = b.targetTouches[0].clientX;
            b.preventDefault();
            this.isTouching = !0;
            this.startTime = this._currentTime = this.player.currentTime || 0;
            "m3u8" == c.config.content && (this._prepaused = this.player.video.paused, this.player.video.pause(), this.startTime = this.player.currentTime);
            if ("mp4" == c.config.content) {
                this.player.video.pause();
                this.startTime = this.player.video.currentTime;
                for (b = 0; b < z; b++)this.startTime += parseInt(c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType][b].seconds)
            }
            this.bind_onTouchMove =
                c.bindAsEventListener(this, this.onTouchMove);
            this.bind_onTouchEnd = c.bindAsEventListener(this, this.onTouchEnd);
            c.addEventHandler(this._seek, "touchmove", this.bind_onTouchMove);
            c.addEventHandler(this._seek, "touchend", this.bind_onTouchEnd)
        }, onTouchMove: function (b) {
            this.maybeSeek = !0;
            if (1 != b.targetTouches.length)return !1;
            b.preventDefault();
            b.stopPropagation();
            b = this.startTime + this.getRate(b.targetTouches[0].clientX - this.startX, this._track.offsetWidth) * c.videoInfo.totalTime;
            this.dispatch({
                type: "progressing",
                st: this._currentTime, dt: b - this._currentTime
            });
            this._currentTime = b;
            this.setProgress(Math.min(Math.max(this._currentTime, 0), c.videoInfo.totalTime), !0);
            return !1
        }, onTouchEnd: function (b) {
            this.dispatch({type: "progressend"});
            this.isTouching = !1;
            if (1 < b.changedTouches.length)return !1;
            var e = {tb: parseInt(100 * this.startTime) / 100, to: parseInt(100 * this._currentTime) / 100};
            h.log("tb=" + e.tb);
            this.player._reporter.sendUserActionReport("xds", "d", e);
            b.preventDefault();
            b.stopPropagation();
            c.removeEventHandler(this._seek,
                "touchmove", this.bind_onTouchMove);
            c.removeEventHandler(this._seek, "touchend", this.bind_onTouchEnd);
            b = Math.min(Math.max(this._currentTime, 0), c.videoInfo.totalTime - 5);
            this.player.controls.onPlay();
            var d = this.player;
            this.player.seek(b, function () {
                d.video.play()
            });
            return this.maybeSeek = !1
        }
    };
    var va = function (b, e) {
        this._handler = {};
        c.isWeixin && (c.get(".x-quality").style.display = "none");
        "m3u8" != c.config.content ? c.get(".x-quality").style.display = "none" : !e || !e.data || !e.data.stream[0] || !e.data.stream[0].stream_type ?
            c.get(".x-quality").style.display = "none" : (this.player = b, this._quality = c.get(".x-quality"), this.init(e), this.bindEvent(), this._button = this._quality.getElementsByTagName("button")[0], this._panel = this._quality.getElementsByTagName("div")[0], this._nodes = this._quality.getElementsByTagName("li"))
    };
    va.prototype = {
        addEventListener: function (b, c) {
            this._handler[b] = c
        }, removeEventListener: function (b) {
            this._handler[b] = null
        }, dispatch: function (b) {
            b && this._handler[b.type] && (b._target = this, this._handler[b.type](b))
        },
        init: function () {
            var b = c.videoInfo._videoSegsDic.typeArr, e = ["<button class=x-control-btn title=\u753b\u8d28\u8bbe\u7f6e>", "", "</button>"], d = ['<div class=x-panel style="display:none"><ul>', "", "</ul><div class=x-mask></div>", "</div>"], g = "", f = [], h;
            for (h in C)if (-1 !== b[c.defaultLanguage].indexOf(h) && -1 === f.indexOf(C[h])) {
                var m = "", j = "";
                h == c.defaultVideoType && (m = "", e[1] = C[h], j = " class=selected");
                g += "<li data-vtype=" + h + j + ">" + m + C[h] + "</li>";
                f.push(C[h])
            }
            "" == e[1] && (e[1] = f[0]);
            d[1] = g;
            this._quality.innerHTML =
                e.join("") + d.join("")
        }, bindEvent: function () {
            var b = this._quality.getElementsByTagName("li");
            if (0 != b.length) {
                this.bind_toggle = c.bindAsEventListener(this, this.toggleQualityPanel);
                c.addEventHandler(this._quality, "click", this.bind_toggle);
                for (var e = 0; e < b.length; e++)c.addEventHandler(b[e], "click", c.bindAsEventListener(this, this.switchQuality))
            }
        }, removeEvent: function () {
            null != this._quality && c.removeEventHandler(this._quality, "click", this.bind_toggle)
        }, hide: function (b) {
            if (this._quality) {
                var c = this._panel;
                this._quality.className = this._quality.className.replace(/[\s]*pressed/g, "");
                c.style.display = "none";
                b || this.dispatch({type: "settinghide"})
            }
        }, toggleQualityPanel: function (b) {
            var c = this._panel;
            -1 === this._quality.className.indexOf("pressed") ? (this._quality.className += " pressed", c.style.display = "block", this.player._reporter.sendUserActionReport("xcq", "c"), this.dispatch({type: "settingshow"})) : (this.hide(), this.player._reporter.sendUserActionReport("xhq", "c"));
            this.dispatch(b)
        }, switchQuality: function (b) {
            this.player._reporter.sendUserActionReport("xsq",
                "c");
            b.stopPropagation();
            for (var e = b.target, b = null, b = e.getAttribute ? e.getAttribute("data-vtype") : e.parentNode.getAttribute("data-vtype"), e = this._button, d = this._nodes, g = 0; g < d.length; g++)if (d[g].getAttribute("data-vtype") == b) {
                if (-1 !== d[g].className.indexOf("selected")) {
                    this.toggleQualityPanel();
                    return
                }
                d[g].innerHTML = C[b];
                d[g].className += " selected";
                e.innerHTML = C[b];
                i.setItem("defaultVideoType", b);
                c.defaultVideoType = b
            } else {
                var f = d[g].getAttribute("data-vtype");
                d[g].innerHTML = C[f];
                d[g].className = d[g].className.replace(/selected/,
                    "")
            }
            h.log("q1");
            this.toggleQualityPanel();
            this.dispatch({type: "settingdone"});
            var n = this.player.currentTime, m = c.m3u8src_v2(c.v.data.id, b);
            c.unitedTag = null;
            this.player.video.src = m;
            var j = this, o = 0;
            this.player.video.addEventListener("canplay", function () {
                1 === o ? h.log("XXXXXXXXXXXXXXXXXXXXX") : (o = 1, h.log("q2 nsrc=" + m), j.player.seek(n), h.log("q3"))
            });
            this.player.video.load();
            this.player.video.play()
        }
    };
    var wa = function (b, e) {
        this._handler = {};
        this.player = b;
        this._panel = document.createElement("div");
        this._panel.className =
            "x-recommend";
        this.init(e);
        this.request(e);
        window.relatedpanel = this;
        c.get("#x-player").appendChild(this._panel);
        this._panel.style.display = "box"
    };
    wa.prototype = {
        bindDynamicEvent: function () {
            var b = this._listinner.getElementsByClassName("x-item");
            this.bind_itemclick = c.bindAsEventListener(this, this.onItemClick);
            for (var e = 0; e < b.length; e++)c.addEventHandler(b[e], "click", this.bind_itemclick, !0)
        }, onItemClick: function (b) {
            b = b.currentTarget.getAttribute("data-i");
            "x" == b ? this.replay() : this.player._reporter.sendRecommendLog(this.getReportParam(b))
        },
        init: function () {
            this._panel.innerHTML = "<div class=x-pages></div>";
            this._listinner = this._panel.getElementsByClassName("x-pages")[0]
        }, request: function (b) {
            var e = {};
            e.vid = b.data.id;
            e.uid = b.data.video.userid;
            b.data.show && b.data.show.id && (e.sid = b.data.show.id);
            e.cate = b.data.video.category_id;
            e.site = "1";
            e.module = "2";
            b = b.data.controller.play_mode;
            e.pg = "1";
            e.pg = {normal: 1, show: 3, folder: 4}[b];
            "interior" == c.config.winType ? e.apptype = 12 : (e.apptype = 12, e.pg = 1);
            e.pl = 36;
            for (var d in c.initConfig.playlistconfig)e[d] =
                c.initConfig.playlistconfig[d];
            var g = this;
            s.jsonp({
                url: "http://ykrec.youku.com/video/packed/list.json?", data: e, success: function (b) {
                    g.parseResponse(b)
                }, fail: function () {
                }
            });
            this._apt = e.apptype;
            this._pg = e.pg;
            this._md = e.module
        }, parseResponse: function (b) {
            this._info = b;
            this.buildPanel(this._info)
        }, buildPanel: function (b) {
            var b = b.data, e = b.length;
            h.log("realted len = " + e);
            var d = [];
            d.push('<ul class="x-item" data-i="x"><li class="x-item-img"><img src="http://player.youku.com/h5player/img/replay.png"></li></ul>');
            for (var g = Math.floor((this._panel.offsetWidth - 60 + 16) / 166) * Math.floor((this._panel.offsetHeight - 120 + 12) / 97), g = (g > e ? e : g) - 1, g = 0 > g ? 0 : g, e = 0; e < g; e++) {
                var f = b[e].picUrl, n = b[e].title.substr(0, 20), m = "http://v.youku.com/v_show/id_" + b[e].codeId + ".html?from=", j = "y7", j = "interior" == c.config.winType ? j + ".2-1-" : j + ".7-1-", j = j + c.v.data.video.category_id, j = j + ".4", j = j + ("." + (e + 1) + "-1"), j = j + ("." + this._apt + "-" + this._pg + "-" + this._md + "-" + e), m = m + j;
                h.log(m);
                "myoukucom" == c.initConfig.client_id && (m = "http://m.youku.com/smartphone/detail?vid=" +
                    b[e].codeId);
                d.push('<ul class="x-item" data-i=' + e + '><li class="x-item-img"><img src=' + f + '></li><li class="x-item-info"><div class="x-item-title">' + n + '</div><div class="x-item-bg"></div></li><li class="x-item-url"><a href=' + m + ' target="_blank"></a></li><li class="x-item-loading"><div class="x-play-loading"></div></li></ul>')
            }
            this._listinner.innerHTML = d.join("");
            this.bindDynamicEvent();
            this.buildImgEvent()
        }, getReportParam: function (b) {
            var e = {};
            e.pos = "" + b;
            e.sct = c.v.data.video.category_id;
            e.dct = this._info.data[b].dct;
            e.ord = this._info.ord;
            e.req_id = this._info.req_id;
            e.abver = this._info.ver;
            e.dma = this._info.data[b].dma;
            e.algInfo = this._info.data[b].algInfo;
            e.apt = this._apt;
            e.md = this._md;
            e.pg = this._pg;
            e.r = (new Date).getTime();
            e.vid = c.v.data.video.encodeid;
            e.uid = c.v.data.video.userid;
            1 == this._info.data[b].type ? e.dvid = this._info.data[b].id : e.dsid = this._info.data[b].id;
            e.sid = "";
            c.v.data.show && c.v.data.show.id && (e.sid = c.v.data.show.id);
            return e
        }, buildImgEvent: function () {
            for (var b = this._listinner.getElementsByClassName("x-item-img"),
                     e = 0; e < b.length; e++)c.addEventHandler(b[e], "error", c.bindAsEventListener(this, this.onLoadImgError)), c.addEventHandler(b[e], "abort", c.bindAsEventListener(this, this.onLoadImgError))
        }, onLoadImgError: function (b) {
            h.log("img error");
            b = b.target;
            c.addClass(b.parentNode.parentNode, "x-no-pic");
            b.src = "http://player.youku.com/h5player/img/no_pic.png"
        }, replay: function (b) {
            this.player.controls.rePlay(b)
        }, onResize: function () {
            var b = this;
            setTimeout(function () {
                b.buildPanel(b._info)
            }, 500)
        }
    };
    var xa = function (b, e) {
        this._handler =
        {};
        this.player = b;
        this._showbtn = c.get(".x-playshow");
        this._showlist = c.get(".x-showlist");
        this.init(e);
        this._inner = this._showlist.getElementsByClassName("x-showlist-inner");
        this._bullet = this._showlist.getElementsByClassName("x-showlist-bullet");
        this.bindEvent()
    };
    xa.prototype = {
        init: function (b) {
            this._showlist.innerHTML = '<div class=x-showlist-inner><div class=x-showlist-hd></div><div class=x-showlist-bd></div><div class=x-showlist-ft style="display:none"></div><div class=x-mask></div></div>';
            this._slhd =
                this._showlist.getElementsByClassName("x-showlist-hd")[0];
            this._slbd = this._showlist.getElementsByClassName("x-showlist-bd")[0];
            this._slft = this._showlist.getElementsByClassName("x-showlist-ft")[0];
            this._slhd.innerHTML = "<label>\u9009\u96c6</label><div class=x-showlist-close></div>";
            this._closeHandle = this._slhd.getElementsByClassName("x-showlist-close")[0];
            if (b.data.videos) {
                for (var e = b.data.videos.list, d = ["<ul class=x-showlist-bullet>", "", "</ul>"], g = [], f = 0; f < e.length; f++) {
                    var h = e[f], m = "http://v.youku.com/v_show/id_" +
                        h.encodevid + ".html";
                    c.v.folder && (m = m + "?f=" + c.v.folder.folderId);
                    var j = "";
                    h.encodevid == c.v.data.video.encodeid && (j = " class=selected");
                    h = "<li" + j + "><a touchstart=\"this.parentNode.className='selected'\" href=" + m + ">" + h.title.substr(0, 20) + "</a></li>";
                    g.push(h)
                }
                d[1] = g.join("");
                this._slbd.innerHTML = d.join("");
                d = "<div class=x-showlist-pages>;<span class=x-showlist-pre></span>;<ul>;;</ul>;<span class=x-showlist-next></span>".split(";");
                g = [];
                j = b.data.videos.previous ? parseInt(b.data.videos.previous.seq / 60) :
                    0;
                for (f = 0; f < (e.length - 1) / 60 + 1; f++)b = "", f == j && (b = " class=current"), h = "<li" + b + "><em>" + (f + 1) + "</em></li>", g.push(h);
                d[3] = g.join("");
                this._slft.innerHTML = d.join("")
            }
        }, addEventListener: function (b, c) {
            this._handler[b] = c
        }, removeEventListener: function (b) {
            this._handler[b] = null
        }, dispatch: function (b) {
            b && this._handler[b.type] && (b._target = this, this._handler[b.type](b))
        }, bindEvent: function () {
            this.bind_close = c.bindAsEventListener(this, this.hide);
            c.addEventHandler(this._closeHandle, "click", this.bind_close);
            this.bind_toggle =
                c.bindAsEventListener(this, this.toggle);
            c.addEventHandler(this._showbtn, "click", this.bind_toggle)
        }, removeEvent: function () {
            c.removeEventHandler(this._closeHandle, "click", this.bind_close)
        }, hide: function () {
            this._showbtn.className = this._showbtn.className.replace(/[\s]*pressed/g, "");
            c.hide(this._showlist)
        }, show: function () {
            this._showbtn.className += " pressed";
            c.show(this._showlist)
        }, showListBtn: function () {
            if (c.v.data.videos) {
                var b = c.v.data.videos.list;
                null == b || 1 >= b.length || c.show(this._showbtn)
            } else c.hide(this._showbtn)
        },
        hideListBtn: function () {
            if (c.v.data.videos) {
                var b = c.v.data.videos.list;
                null == b || 0 == b.length || (c.hide(this._showbtn), this.hide())
            }
        }, toggle: function (b) {
            "block" != this._showlist.style.display ? (this.show(), this.player._reporter.sendUserActionReport("xshl", "c")) : this.hide();
            this.dispatch(b)
        }, touchStart: function (b) {
            this._sx = b.targetTouches[0].clientX;
            this._sy = b.targetTouches[0].clientY;
            this._ex = this._sx;
            this._ey = this._ey
        }, touchEnd: function () {
        }, touchMove: function (b) {
            this._ex = b.targetTouches[0].clientX;
            this._ey =
                b.targetTouches[0].clientY;
            this._dx = this._ex - this._sx;
            this._dy = this._ey - this._sy;
            Math.abs(this._dx) > Math.abs(this._dy) || b.preventDefault()
        }
    };
    var ya = function (b) {
        this.player = b;
        this._handle = {};
        this._tips = c.get(".x-tips");
        c.hide(this._tips);
        this._tips.innerHTML = "<div class=x-tips-txt></div><div class=x-tips-close><a href=#><em>\u5173\u95ed</em></a></div><div class=x-tips-mask></div>";
        this._ptip = this._tips.getElementsByClassName("x-tips-txt")[0];
        this._ctip = this._tips.getElementsByClassName("x-tips-close")[0];
        null == i.getItem("youku_conf_skip") && i.setItem("youku_conf_skip", !0);
        this.bindEvent()
    };
    ya.prototype = {
        bindEvent: function () {
            c.addEventHandler(this._ctip, "click", c.bindAsEventListener(this, this.closeTip))
        }, closeTip: function () {
            c.hide(this._tips);
            this.keepLastTime()
        }, autoHide: function (b) {
            var c = this;
            setTimeout(function () {
                c.closeTip()
            }, b)
        }, keepLastTime: function () {
        }, ignoreLastTime: function () {
        }, isShowTimeTip: function () {
            var b = i.getItem("youku_keep_lasttime"), b = parseInt(b), c = i.getItem("youku_ignore_lasttime"),
                c = parseInt(c);
            return 3 <= b || 3 <= c ? !1 : !0
        }, showLastTimeTip: function (b) {
            b = c.getTime(b);
            h.log("last = " + b);
            !1 != this.isShowTimeTip() && (this._ptip.innerHTML = "\u4f18\u9177\u8bb0\u5fc6\u60a8\u4e0a\u6b21\u64ad\u653e\u5230<span class=x-tips-time>" + b + "</span>, <a class=x-tip-timebegin href=#>\u4ece\u5934\u89c2\u770b</a>", this._playBegin = this._ptip.getElementsByClassName("x-tip-timebegin")[0], c.addEventHandler(this._playBegin, "click", c.bindAsEventListener(this, this.seekBegin)), c.show(this._tips), this.autoHide(5E3))
        },
        onSkipTail: function () {
            "true" == i.getItem("youku_conf_skip") ? (this._ptip.innerHTML = "\u5373\u5c06\u4e3a\u60a8\u8df3\u8fc7\u7247\u5c3e, <a class=x-tip-skipnoway href=#>\u4e0d\u518d\u8df3\u8fc7</a>", this._skipnowtail = this._ptip.getElementsByClassName("x-tip-skipnoway")[0], c.addEventHandler(this._skipnowtail, "click", c.bindAsEventListener(this, this.skipNoway))) : (this._ptip.innerHTML = "\u662f\u5426\u8df3\u8fc7\u7247\u5934\u7247\u5c3e? <a class=x-tip-skipalways href=#>\u59cb\u7ec8\u8df3\u8fc7</a>", this._skipalwtail =
                this._ptip.getElementsByClassName("x-tip-skipalways")[0], c.addEventHandler(this._skipalwtail, "click", c.bindAsEventListener(this, this.skipAlways)));
            c.show(this._tips);
            this.autoHide(1E4)
        }, onSkipHead: function () {
            "true" == i.getItem("youku_conf_skip") ? (this._ptip.innerHTML = "\u5df2\u7ecf\u4e3a\u60a8\u8df3\u8fc7\u7247\u5934, <a class=x-tip-skipnoway href=#>\u4e0d\u518d\u8df3\u8fc7</a>", this._skipnow = this._ptip.getElementsByClassName("x-tip-skipnoway")[0], c.addEventHandler(this._skipnow, "click", c.bindAsEventListener(this,
                this.skipNoway))) : (this._ptip.innerHTML = "\u662f\u5426\u8df3\u8fc7\u7247\u5934\u7247\u5c3e? <a class=x-tip-skipalways href=#>\u59cb\u7ec8\u8df3\u8fc7</a>", this._skipalw = this._ptip.getElementsByClassName("x-tip-skipalways")[0], c.addEventHandler(this._skipalw, "click", c.bindAsEventListener(this, this.skipImediately)));
            c.show(this._tips);
            this.autoHide(5E3)
        }, onUglyAdPlay: function () {
            this._ptip.innerHTML = "\u5c0a\u656c\u7684\u4f1a\u5458\uff0c\u56e0\u7248\u6743\u539f\u56e0\uff0c\u8bf7\u70b9\u51fb\u53f3\u4e0a\u89d2 \u5173\u95ed\u5e7f\u544a ";
            c.show(this._tips);
            var b = this;
            setTimeout(function () {
                c.hide(b._tips)
            }, 15E3)
        }, closeUglyHint: function () {
            c.hide(this._tips)
        }, skipImediately: function () {
            h.log("skip imediately");
            this.player._reporter.sendUserActionReport("xskh", "c");
            i.setItem("youku_conf_skip", !0);
            var b = parseInt((c.v.data.dvd || "").head) / 1E3;
            this.onSkipHead();
            this.player.seek(b);
            return !1
        }, skipNoway: function () {
            this.player._reporter.sendUserActionReport("xnsk", "c");
            i.setItem("youku_conf_skip", !1);
            this._ptip.innerHTML = "\u8bbe\u7f6e\u6210\u529f";
            return !1
        }, skipAlways: function () {
            this.player._reporter.sendUserActionReport("xask", "c");
            i.setItem("youku_conf_skip", !0);
            this._ptip.innerHTML = "\u8bbe\u7f6e\u6210\u529f";
            return !1
        }, seekBegin: function () {
            this.player._reporter.sendUserActionReport("xseb", "c");
            c.hide(this._tips);
            this.ignoreLastTime();
            this.player.seek(0);
            return !1
        }
    };
    var Z = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""), U = function (b, c, d) {
        this.player = b;
        this.v = c;
        this.sid = d;
        this.isSendedConsumeReport = !1;
        f.hd = this.getHDFlag();
        if (0 < K.length)for (b = 0; b < K.length; b++)this.sendUepReport(K[b].type, K[b].time);
        this.dimension = {
            w: document.getElementById("x-player").offsetWidth,
            h: document.getElementById("x-player").offsetHeight
        };
        this.screenDim = {w: screen.availWidth, h: screen.availHeight}
    };
    U.prototype = {
        sendRecommendLog: function (b) {
            f.Log("http://r.l.youku.com/recclick?" + t(b))
        }, tsInit: function () {
            this.tsSn = null
        }, sendTSLog: function (b) {
            null == this.tsSn && (this.tsSn = 0);
            var e = 5, e = 24 < this.tsSn ? 20 : 12 < this.tsSn ? 10 : 5, d = this;
            this.tstimer = setTimeout(function () {
                    d.sendTSLog(60)
                },
                1E3 * e);
            if (61 == b)clearTimeout(this.tstimer), this.tstimer = null; else if (d.player.video.paused)return;
            if (0 == this.tsSn)this.tsSn++; else {
                var g = c.v.data;
                g.sid = c.videoInfo._sid;
                c.initConfig.tslogconfig = c.initConfig.tslogconfig || {};
                var h = {};
                this.rs && (h.rs = 1, this.rs = 0);
                h.vvid = g.sid;
                h.vid = g.id;
                h.cf = this.getHDFlag();
                h.cpt = this.player.currentTime ? Math.floor(this.player.currentTime) : 0;
                h.full = this.player.controls.fullscreenPanel.fullFlag() ? 1 : 0;
                h.lang = this.getLanguage();
                h.pc = 60 == b ? 0 : 1;
                h.clb = 0;
                h.iku = "m";
                h.pt = this.getPlayTime();
                h.sn = this.tsSn++;
                h.hi = e;
                h.uid = c.v.data.user.uid;
                h.r = this.signTS(h.vvid + h.vid + h.cpt + h.pt + h.sn);
                f.Log("http://p-log.ykimg.com/tslog?" + t(h))
            }
        }, signTS: function (b) {
            if (null == b)return 0;
            for (var c = 0, d = b.length, g = 0; g < d; g++)c = 43 * c + b.charCodeAt(g), c %= 1E10;
            return c
        }, getPlayTime: function () {
            var b = 0;
            return b = 24 < this.tsSn ? 180 + 20 * (this.tsSn - 24) : 12 < this.tsSn ? 60 + 10 * (this.tsSn - 12) : 5 * this.tsSn
        }, tslogparse: function () {
        }, sendTSErrorLog: function () {
        }, getSubCategories: function (b) {
            for (var c = "", d = 0; d < b.length; d++)c += b[d].id +
                "|";
            return c.substring(0, c.length - 1)
        }, getLanguage: function () {
            null == this.langMap && (this.langMap = {
                "default": 1,
                guoyu: 1,
                yue: 2,
                chuan: 3,
                tai: 4,
                min: 5,
                en: 6,
                ja: 7,
                kr: 8,
                "in": 9,
                ru: 10,
                fr: 11,
                de: 12,
                it: 13,
                es: 14,
                th: 15,
                po: 16,
                man: 17,
                baby: 18
            });
            return this.langMap[c.defaultLanguage || "default"]
        }, getExtString: function (b) {
            var e = {iku: "m"};
            e.full = this.player.controls.fullscreenPanel.fullFlag();
            e.lang = this.getLanguage();
            e.num = b;
            e.ctp = 0;
            e.pc = 60 == b ? 0 : 1;
            e.clb = 0;
            e.ctype = "12";
            e.ev = "1";
            e.tk = f.userCache.token;
            e.oip = c.v.data.security.ip;
            e.isvip = c.v.data.user.vip ? "1" : "0";
            e.paystate = this.getPayState();
            e.playstate = null == c.v.data.trial ? "1" : "2";
            return encodeURI(t(e))
        }, getPlayByType_: function (b) {
            var e = 0;
            62 == b && (e = 2);
            c.initConfig.vvlogconfig.pb && (e = c.initConfig.vvlogconfig.pb);
            return e
        }, getMaxFileType: function () {
            var b = o._videoInfo._videoSegsDic;
            return b.hd2 ? 2 : b.mp4 ? 1 : 0
        }, getHDFlag: function () {
            if (null == this.player)return 0;
            var b = null, e = this.player.video.src;
            -1 != e.indexOf("m3u8") ? (b = {flv: 0, flvhd: 0, mp4: 1, hd2: 2, hd3: 3}, e = c.defaultVideoType) : b =
            {"030020": 4, "030004": 0, "030008": 1, "030080": 3};
            for (var d in b)if (-1 !== e.indexOf(d))return b[d];
            return 0
        }, getParentUrl: function () {
            var b = null;
            if (parent !== window)try {
                b = parent.location.href
            } catch (c) {
                b = document.referrer
            }
            return b
        }, addPlayerDurationReport: function (b) {
            var e = c.videoInfo;
            if (!(null == e || null == e._playListData)) {
                if (null == this.drtimer) {
                    var d = this;
                    this.drtimer = setInterval(function () {
                        d.player.video.paused || d.addPlayerDurationReport(60)
                    }, 6E4)
                }
                61 == b && (clearInterval(this.drtimer), this.drtimer = null);
                var g =
                {viewUserId: 0};
                g.sid = e._sid;
                g.videoOwnerId = c.v.data.video.userid;
                c.v.data.user.uid && (g.viewUserId = c.v.data.user.uid);
                g.videoid = c.v.data.id;
                g.ct = c.v.data.video.category_letter_id;
                g.cs = this.getSubCategories(c.v.data.video.subcategories);
                g.number = b;
                g.rnd = ((new Date).getTime() - e.abstarttime) / 1E3;
                null != e._playListData.show ? (g.showid_v2 = null == e._playListData.show ? "" : e._playListData.show.id, g.showid_v3 = null == e._playListData.show ? "" : e._playListData.show.encodeid, g.show_videotype = e._playListData.show.video_type,
                    g.stg = e._playListData.show.stage, g.Copyright = e._playListData.show.copyright) : (g.showid_v2 = "", g.Copyright = "");
                g.Tid = "";
                g.hd = 0;
                g.ikuflag = "m";
                g.hd = {flv: 0, flvhd: 0, mp4: 1, hd2: 2, hd3: 3}[c.defaultVideoType];
                g.winType = c.WIN_TYPE;
                g.mtype = $();
                g.totalsec = e.totalTime;
                g.fullflag = this.player.controls.fullscreenPanel.fullFlag();
                g.playComplete = 0;
                61 == b && (g.playComplete = 1);
                59 == b && (g.referUrl = (c.initConfig.vvlogconfig || "").rurl, g.url = encodeURIComponent(window.location.href), g.starttime = 0);
                g.currentPlayTime = parseInt(this.player.currentTime ||
                    0);
                g.continuationPlay = 0;
                g.pid = c.initConfig.client_id;
                g.timestamp = (new Date).getTime();
                g.ctype = "12";
                g.ev = "1";
                g.tk = f.userCache.token;
                g.oip = c.v.data.security.ip;
                g.isvip = c.v.data.user.vip ? "1" : "0";
                g.paystate = this.getPayState();
                g.playstate = null == c.v.data.trial ? "1" : "2";
                f.Log("http://stat.youku.com/player/addPlayerDurationReport?" + t(g))
            }
        }, addPlayerStaticReport: function () {
            var b = {};
            b.videoid = this.v.data.id;
            this.v.data.token && (b.t = this.v.data.token.vv);
            b.totalsec = parseInt(this.v.data.video.seconds);
            b.ikuflag =
                "m_" + this.getShowFlag();
            b.url = encodeURIComponent(this.getParentUrl() ? this.getParentUrl() : window.location.href);
            b.fullflag = this.player.controls.fullscreenPanel.fullFlag();
            b.source = "video";
            b.referer = (c.initConfig.vvlogconfig || "").rurl;
            b.sid = this.sid;
            b.uid = this.v.data.user.uid;
            for (var e = b.t, d = !1, g = ""; !d;)g = Y(20), "00" == Fa(e + g).substring(0, 2) && (d = !0);
            b.h = g;
            b.totalseg = c.pieceLength();
            b = t(b);
            f.Log("http://stat.youku.com/player/addPlayerStaticReport?" + b)
        }, sendUserActionReport: function (b, e, d) {
            e = {
                t: 1002, e: b,
                v: e
            };
            e.d = L($());
            var g = {
                v: "h5player",
                vid: c.v.data.id,
                ssid: c.videoInfo._sid,
                ct: c.v.data.video.category_letter_id,
                cs: c.v.data.video.subcategories,
                uid: 0
            };
            c.v.data.user && (g.uid = c.v.data.user.uid);
            g.sid = "";
            c.v.data.show && (g.sid = c.v.data.show.id);
            g.tc = this.player.currentTime || 0;
            g.w = c.get("#x-player").offsetWidth;
            g.h = c.get("#x-player").offsetHeight;
            g.f = this.player.video.fullscreenchange ? "on" : "off";
            g.q = this.player.getQuality();
            g.ver = "1.0.0";
            for (var h in d)g[h] = d[h];
            e.x = L(t(g));
            d = t(e);
            if ("xre" == b)this.checkPlayerResize("http://e.stat.ykimg.com/red/ytes.php?",
                d); else {
                if ("xenfs" == b || "xexfs" == b) {
                    this._giveupReTag = !0;
                    var n = this;
                    setTimeout(function () {
                        n._giveupReTag = false
                    }, 800)
                }
                f.Log("http://p-log.ykimg.com/event?" + d)
            }
        }, checkScreenRotate: function (b, c) {
            var d = screen.availWidth, g = screen.availHeight;
            h.log("<hr/>rota w,h = " + d + "," + g);
            if (this.screenDim.w != d || this.screenDim.h != g)this.screenDim.w = d, this.screenDim.h = g, h.log("<b><font color=red>rotate</font></b>"), f.Log(b + c)
        }, checkPlayerResize: function (b, c) {
            if (!0 === this._giveupReTag)h.log("give up xre after enfs or exfs");
            else {
                var d = document.getElementById("x-player");
                this._resizeList = this._resizeList || [];
                this._resizeList.push({str: c, time: (new Date).getTime(), w: d.offsetWidth, h: d.offsetHeight});
                var g = this;
                setTimeout(function () {
                    if (0 != g._resizeList.length) {
                        for (var c = g._resizeList[0].time, d = 0; d < g._resizeList.length; d++) {
                            var e = g._resizeList[d].w, l = g._resizeList[d].h, h = g._resizeList[d].time;
                            if (e != g.dimension.w || l != g.dimension.h)g.dimension.w = e, g.dimension.h = l, (800 < h - c || d == g._resizeList.length - 1) && f.Log(b + g._resizeList[d].str)
                        }
                        g._resizeList =
                            []
                    }
                }, 1E3)
            }
        }, sendUepReport: function (b, d, l) {
            !1 !== l && 10 < 100 * Math.random() || (l = "", l = f.isIPAD ? "xplayer_ipad" : f.isIPHONE ? "xplayer_iphone" : "xplayer_android", b = {
                m: l,
                hd: this.getHDFlag(),
                t: b,
                s: d,
                u: encodeURIComponent(this.getParentUrl() ? this.getParentUrl() : window.location.href),
                p: 2,
                v: c.videoInfo._sid,
                ct: c.v.data.video.category_letter_id,
                cs: c.v.data.video.subcategories
            }, f.Log("http://v.l.youku.com/uep?" + t(b)))
        }, sendLoadedTime: function (b) {
            h.log("loaded cost = " + b);
            this.sendUepReport("videoload", b)
        }, sendComScoreReport: function (b) {
            if (!this._hasComScore) {
                for (var d =
                    document.getElementsByTagName("script"), f = 0; f < d.length; f++)if (-1 !== d[f].src.indexOf("scorecardresearch.com/beacon.js")) {
                    this._hasComScore = !0;
                    break
                }
                !0 !== this._hasComScore && (d = document.createElement("script"), f = document.getElementsByTagName("script")[0], d.async = !0, d.src = ("https:" == document.location.protocol ? "https://sb" : "http://b") + ".scorecardresearch.com/beacon.js", f.parentNode.insertBefore(d, f));
                this._hasComScore = !0
            }
            var g = setInterval(function () {
                if ("undefined" != typeof COMSCORE) {
                    clearInterval(g);
                    try {
                        COMSCORE.beacon({
                            c1: 1,
                            c2: 7293931, c3: b, c6: c.v.data.video.category_id
                        })
                    } catch (d) {
                        h.log("beacon exception")
                    }
                }
            }, 500)
        }, sendIResearchReport: function () {
        }, sendThirdPartyReport: function (b) {
            "xplayer_h5" == b && (b = f.isAndroid ? "xplayer_h5_android" : f.isIPAD ? "xplayer_h5_ipad" : "xplayer_h5_other");
            this.sendComScoreReport(b);
            this.sendIResearchReport(b)
        }, sendClientConsumeReport: function () {
            !0 != this.isSendedConsumeReport && (null != c.config.partner_config && 1 == c.config.partner_config.status && null != c.config.partner_config.token && "" != c.config.partner_config.token) &&
            (this.isSendedConsumeReport = !0, f.Log("https://api.youku.com/players/consume.json?token=" + c.config.partner_config.token))
        }, getPayState: function () {
            var b = 0;
            c.v.data.show && "vod" == c.v.data.show.pay_type && (b = 1);
            c.v.data.show && "mon" == c.v.data.show.pay_type && (b = 2);
            return b
        }, getShowFlag: function () {
            var b = "m";
            return b = c.v.data.show ? b + "1" : b + "0"
        }
    };
    var za = function (b, d) {
        this._handler = {};
        this.player = b;
        this.controls = b.controls;
        this.adplugin = this.controls._pauseAdPlugin;
        this.info = d.data.info;
        this.adjustIMGWH();
        this.adpause =
            c.get(".x-ad-pause");
        this.info.VAL[0].VT = parseInt(this.info.VAL[0].VT);
        2 != this.info.VAL[0].VT && (this.init(), this.bindEvent(), this._adreporter = new P(this, d.data));
        this.loadVC()
    };
    za.prototype = {
        addEventListener: function (b, c) {
            this._handler[b] = c
        }, removeEventListener: function (b) {
            this._handler[b] = null
        }, dispatch: function (b) {
            b && this._handler[b.type] && (b._target = this, this._handler[b.type](b))
        }, bindEvent: function () {
            c.addEventHandler(this.adcontent, "click", c.bindAsEventListener(this, this.adClick));
            c.addEventHandler(this.adclose,
                "click", c.bindAsEventListener(this, this.hide));
            var b = this;
            window.addEventListener("orientationchange", function () {
                setTimeout(function () {
                    c.isLandScape() || b.hide()
                }, 1E3)
            })
        }, adjustIMGWH: function () {
            var b = this.info.VAL[0].W, d = this.info.VAL[0].H, f = (c.get("#x-player").offsetHeight - 110) / d;
            if (1 < f || 0 >= f)f = 1;
            this.info.VAL[0].W = b * f;
            this.info.VAL[0].H = d * f;
            h.log("pause img adjusted w = " + this.info.VAL[0].W + " h = " + this.info.VAL[0].H)
        }, init: function () {
            this.adpause.innerHTML = "<div class=x-pause-content></div><div class=x-pause-close></div><div class=x-pause-prompt></div>";
            this.adcontent = this.adpause.getElementsByClassName("x-pause-content")[0];
            this.adcontent.innerHTML = " <img class=x-pause-img width=" + this.info.VAL[0].W + " height=" + this.info.VAL[0].H + " src=" + this.info.VAL[0].RS + ">";
            this.adclose = this.adpause.getElementsByClassName("x-pause-close")[0];
            this.adimg = this.adcontent.getElementsByClassName("x-pause-img")[0];
            this.adimg.style.height = this.info.VAL[0].H + "px";
            this.adimg.style.width = this.info.VAL[0].W + "px";
            this.adpause.style.marginLeft = "-" + this.info.VAL[0].W / 2 + "px";
            this.adpause.style.marginTop = "-" + this.info.VAL[0].H / 2 + "px"
        }, hide: function () {
            c.hide(this.adpause)
        }, play: function () {
            2 != this.info.VAL[0].VT && (c.show(this.adpause), this._adreporter.sendSUS())
        }, adClick: function () {
            window.open(this.info.VAL[0].CU, null);
            this._adreporter && this._adreporter.sendCUM()
        }, loadVC: function () {
            (2 == this.info.VAL[0].VT || 1 == this.info.VAL[0].VT) && F(this.info.VAL[0].VC, "js")
        }
    };
    var Aa = function (b, c) {
        this._handler = {};
        this._adinfo = b;
        this._info = {VAL: []};
        for (var d in b)"VAL" != d && (this._info[d] =
            b[d]);
        this._vt2nodes = c || []
    };
    Aa.prototype = {
        addEventListener: function (b, c) {
            this._handler[b] = c
        }, removeEventListener: function (b) {
            this._handler[b] = null
        }, dispatch: function (b) {
            b && this._handler[b.type] && (b._target = this, this._handler[b.type](b))
        }, buildAdRS: function () {
            for (var b = "http://pl.youku.com/playlist/m3u8?", d = {}, h = {}, g = this._adinfo.VAL, q = 0; q < g.length; q++) {
                var n = g[q];
                h["a" + (q + 1)] = n.VID + "_" + n.VQT
            }
            h.v = c.v.data.id + "_" + c.defaultVideoType;
            var q = encodeURI, n = [], i;
            for (i in h)n.push('"' + i + '":"' + h[i] + '"');
            h = "{" +
                n.join(",") + "}";
            d.ids = q(h);
            d.ts = parseInt((new Date).getTime() / 1E3);
            c.password && (d.password = c.password);
            c.password && (c.initConfig.client_id && c.config.partner_config && 1 == c.config.partner_config.status && 1 == c.config.partner_config.passless) && (d.client_id = c.initConfig.client_id);
            h = [];
            for (i = 0; i < g.length; i++)h.push(g[i].VID);
            h.push(c.v.data.id);
            g = encodeURIComponent(L(N(O(c.mk.a4 + "poz" + f.userCache.a2, [19, 1, 4, 7, 30, 14, 28, 8, 24, 17, 6, 35, 34, 16, 9, 10, 13, 22, 32, 29, 31, 21, 18, 3, 2, 23, 25, 27, 11, 20, 5, 15, 12, 0, 33, 26]).toString(),
                f.userCache.sid + "_" + h.join("") + "_" + f.userCache.token)));
            d.ep = g;
            d.sid = f.userCache.sid;
            d.token = f.userCache.token;
            d.ctype = "12";
            d.ev = "1";
            d.oip = c.v.data.security.ip;
            return b += t(D(d, c.getUCParam(c.v.data.id)))
        }, run: function () {
            if (!(null == this._adinfo || null == this._adinfo.VAL || 0 == this._adinfo.VAL.length)) {
                for (var b = {
                    SUS: [],
                    SU: [],
                    SUE: [],
                    CU: [],
                    CUM: [],
                    VTVC: []
                }, c = 0, d = 0; d < this._adinfo.VAL.length; d++) {
                    var g = this._adinfo.VAL[d];
                    if (!(null == g.VID || null == g.VQT)) {
                        null == g.SU && (g.SU = []);
                        null == g.SUE && (g.SUE = []);
                        if (0 == d)b.SUS =
                            g.SUS || []; else for (var f = 0; f < g.SUS.length; f++)b.SU.push({T: c, U: g.SUS[f].U});
                        for (f = 0; f < g.SU.length; f++) {
                            var h = g.SU[f].T + c;
                            b.SU.push({T: h, U: g.SU[f].U})
                        }
                        if (d == this._adinfo.VAL.length - 1)b.SUE = g.SUE; else for (f = 0; f < g.SUE.length; f++)h = c + g.AL, b.SU.push({
                            T: h,
                            U: g.SUE[f].U
                        });
                        c += g.AL;
                        b.CU.push({T: c, U: g.CU});
                        b.CUM.push({T: c, CUM: g.CUM});
                        1 == parseInt(g.VT) && b.VTVC.push({U: g.VC, T: c});
                        if (0 != this._vt2nodes.length)for (f = 0; f < this._vt2nodes.length; f++)g = this._vt2nodes[f].VC, h = this._vt2nodes[f].pos_, -1 == h && b.VTVC.push({
                            U: g,
                            T: 0
                        }), h == d && b.VTVC.push({U: g, T: c})
                    }
                }
                b.AL = c;
                b.RS = this.buildAdRS();
                this._info.VAL.push(b);
                this._info.src = b.RS
            }
            this.dispatch({type: R, data: this._info})
        }
    };
    var V = function (b, c) {
        this._handler = {};
        this.player = b;
        this.video = this.player.video;
        this.controls = this.player.controls;
        this._adplugin = this.player._adplugin;
        this._adplugin.adplayer = this;
        this.video.preload = "none";
        this.video.src = c.data.urls[0];
        h.log("ad src=" + this.video.src);
        this.video.style.display = "block";
        this._addata = c.data;
        this._addata.curnum = 0;
        this._playTag =
            [];
        this.bindAdEvent();
        this._adreporter = new P(this, this._addata)
    };
    V.prototype = {
        addEventListener: function (b, c) {
            this._handler[b] = c
        }, removeEventListener: function (b) {
            this._handler[b] = null
        }, dispatch: function (b) {
            b && this._handler[b.type] && (b._target = this, this._handler[b.type](b))
        }, bindAdEvent: function () {
            this.bind_fadtoplay = c.bindAsEventListener(this, this.onPlayClick);
            this.bind_fadplay = c.bindAsEventListener(this, this.onAdPlay);
            this.bind_fadended = c.bindAsEventListener(this, this.onAdEnded);
            this.bind_faderror =
                c.bindAsEventListener(this, this.onAdError);
            this.bind_fadpause = c.bindAsEventListener(this, this.onAdPause);
            this.bind_fadsuspend = c.bindAsEventListener(this, this.onAdSuspend);
            this.bind_fadstalled = c.bindAsEventListener(this, this.onAdStalled);
            this.bind_fadwaiting = c.bindAsEventListener(this, this.onAdWaiting);
            this.bind_fadloadedmetadata = c.bindAsEventListener(this, this.onAdLoadedMetaData);
            this.bind_fadtimeupdate = c.bindAsEventListener(this, this.onAdTimeUpdate);
            c.addEventHandler(this.video, "play", this.bind_fadplay);
            c.addEventHandler(this.video, "ended", this.bind_fadended);
            c.addEventHandler(this.video, "error", this.bind_faderror);
            c.addEventHandler(this.video, "pause", this.bind_fadpause);
            c.addEventHandler(this.video, "suspend", this.bind_fadsuspend);
            c.addEventHandler(this.video, "stalled", this.bind_fadstalled);
            c.addEventHandler(this.video, "waiting", this.bind_fadwaiting);
            c.addEventHandler(this.video, "loadedmetadata", this.bind_fadloadedmetadata);
            c.addEventHandler(this.video, "timeupdate", this.bind_fadtimeupdate);
            this.shadow =
                this.controls.buttons.shadow;
            this.videobtn = this.controls.buttons.videobtn;
            c.addEventHandler(this.videobtn, "click", this.bind_fadtoplay, !0)
        }, removeAdEvent: function () {
            c.removeEventHandler(this.video, "play", this.bind_fadplay);
            c.removeEventHandler(this.video, "ended", this.bind_fadended);
            c.removeEventHandler(this.video, "error", this.bind_faderror);
            c.removeEventHandler(this.video, "pause", this.bind_fadpause);
            c.removeEventHandler(this.video, "suspend", this.bind_fadsuspend);
            c.removeEventHandler(this.video, "stalled",
                this.bind_fadstalled);
            c.removeEventHandler(this.video, "waiting", this.bind_fadwaiting);
            c.removeEventHandler(this.video, "timeupdate", this.bind_fadtimeupdate);
            c.removeEventHandler(this.video, "loadedmetadata", this.bind_fadloadedmetadata);
            c.removeEventHandler(this.videobtn, "click", this.bind_fadtoplay, !0)
        }, onPlayClick: function () {
            this.video.play()
        }, checkVTVC: function (b) {
            var c = this._addata.vtvc;
            if (!(null == c || 0 === c.length))for (var d = 0; d < c.length; d++) {
                var g = c[d];
                g.pos_ == b - 1 && F(g.VC, "js")
            }
        }, play: function () {
            this.checkVTVC(0);
            this.video.load();
            this.video.play()
        }, leftSecond: function () {
            for (var b = this._addata.curnum, c = this._addata.seconds.length, d = this._addata.seconds[b] - this.video.currentTime, b = b + 1; b < c; b++)d += this._addata.seconds[b];
            return parseInt(d)
        }, clearTimer: function () {
            clearInterval(this._checkTimer);
            this._checkTimer = null
        }, checkPause: function () {
            if (!this._checkTimer) {
                var b = this;
                this._timelist = [];
                this._checkTimer = setInterval(function () {
                    if (b.video.paused)b.onAdPause(); else b._timelist.push(b.video.currentTime), 3 <= b._timelist.length &&
                    (1 > Math.abs(b._timelist[0] - b._timelist[2]) && (h.log("<b>ad unexpected pause</b>"), b.video.play(), 0 == b.leftSecond() && (h.log("<b>exception left = 0 </b>"), b.onAdEnded())), b._timelist = [])
                }, 1E3)
            }
        }, onAdPlay: function () {
            this.checkPause();
            var b = this.controls.container.poster;
            c.hide(this.controls.buttons.videobtn);
            c.hide(b);
            c.hide(c.get(".x-video-info"));
            this.video.style.display = "block";
            b = this._addata.curnum;
            h.log("left=" + this.leftSecond() + " curtotal=" + this._addata.seconds[b] + " curtime=" + this.video.currentTime);
            this._adplugin.setLeftSecond(this.leftSecond());
            var d = this;
            setTimeout(function () {
                h.log("ad media timeout check begin = " + d._adBegin);
                d._adBegin || (d.removeAdEvent(), d._adplugin.hide(), d._adplugin.reportTime("advideo", -1, !1), d.dispatch({
                    type: B,
                    data: !0
                }))
            }, 1E4);
            this._playTag[b] || (this._playTag[b] = !0, this._adfirsttu = !1, this._adplugin.recordTime("advideo"), i.appendItem("phase", "adplay"))
        }, uglyClose: function () {
            this.video.src = "";
            this.video.load();
            this.video.play()
        }, onAdError: function () {
            this.checkVTVC(this._addata.curnum +
                1);
            this.removeAdEvent();
            this._adplugin.hide();
            this._adplugin.reportTime("advideo", -1, !1);
            this.dispatch({type: B, data: !0})
        }, onAdEnded: function (b) {
            h.log("ad ended");
            this._adreporter.sendSUE();
            this.checkVTVC(this._addata.curnum + 1);
            if (this._addata.curnum < this._addata.urls.length - 1)this.onMiddleAdEnded(b); else this.removeAdEvent(), this._adplugin.hide(), this.clearTimer(), this.dispatch({
                type: E,
                data: !0
            }), i.appendItem("phase", "adend")
        }, onMiddleAdEnded: function () {
            h.log("onMiddleAdEnded");
            this._pauseLeftSec = !0;
            var b = this;
            setTimeout(function () {
                b._pauseLeftSec = !1
            }, 1E3);
            this._addata.curnum++;
            this.video.src = this._addata.urls[this._addata.curnum];
            this.video.load();
            this.video.play();
            this._adBegin = !1
        }, onAdPause: function () {
            this.player.video.ended || (c.show(this.controls.buttons.videobtn), c.hide(this.controls.buttons.shadow))
        }, onAdSuspend: function () {
            h.log("<font color=red>ad suspend</font>")
        }, onAdStalled: function () {
            h.log("<font color=red>ad stalled</font>")
        }, onAdWaiting: function (b) {
            this.controls.onWaiting(b)
        },
        onAdTimeUpdate: function () {
            c.hide(this.controls.buttons.loading);
            this._adBegin = !0;
            c.hide(this.controls.buttons.loading);
            this._pauseLeftSec || this._adplugin.setLeftSecond(this.leftSecond());
            this._adreporter.sendSU(this.video.currentTime);
            0.5 <= this.video.currentTime && this._adplugin.show();
            this._adfirsttu || (this._adfirsttu = !0, this._adreporter.sendSUS(), this._adreporter.sendVC(), this._adplugin.reportTime("advideo"), f.isNeedAdrTrick() && f.adrInvalidPauseCheck(this.video), 0 === this._adplugin.SKIP && this.dispatch({type: J}))
        },
        onAdLoadedMetaData: function () {
            this._adBegin = !0
        }, onAdClick: function () {
            this.video.pause();
            this._adreporter.sendCUM();
            var b = this._addata, b = b.info.VAL[b.curnum].CU;
            h.log("click cu=" + b);
            window.open(b, "", "", !1)
        }
    };
    E = "adend";
    B = "aderror";
    R = "frontAdinfoadapterok";
    J = void 0;
    var W = function (b, d, f) {
        this._handler = {};
        this.player = b;
        this.sid = f;
        this._advids = [];
        this._adsecs = [];
        this._adsrcs = [];
        this._vid = d.data.video.encodeid;
        this._advert = c.get(".x-advert");
        this._adskip = this._advert.getElementsByClassName("x-advert-skip")[0];
        this._adcount = this._advert.getElementsByClassName("x-advert-countdown")[0];
        this._adknowdet = this._advert.getElementsByClassName("x-advert-detail")[0];
        this.init(d);
        this.bindEvent()
    };
    W.prototype = {
        init: function (b) {
            this.initRequestParam(b);
            this._adskipTxt = this._adskip.getElementsByClassName("x-advert-txt")[0];
            this._adskipTxt.innerHTML = "\u8df3\u8fc7\u5e7f\u544a";
            this._adcountTxt = this._adcount.getElementsByClassName("x-advert-txt")[0];
            this._adcountTxt.innerHTML = "\u5e7f\u544a <span class=x-advert-sec></span> \u79d2";
            this._adsec = this._adcountTxt.getElementsByClassName("x-advert-sec")[0]
        }, getSubCategories: function (b) {
            for (var c = "", d = 0; d < b.length; d++)c += b[d].id + "|";
            return c.substring(0, c.length - 1)
        }, initRequestParam: function (b) {
            var d = {
                site: 1,
                p: 0,
                vl: parseInt(b.data.video.seconds),
                fu: 0,
                ct: b.data.video.category_letter_id,
                cs: this.getSubCategories(b.data.video.subcategories),
                d: 0,
                paid: b.data.show ? b.data.show.pay : 0,
                s: b.data.show ? b.data.show.id : 0,
                sid: this.sid,
                td: b.data.video.source ? b.data.video.source : 0,
                v: b.data.id,
                vip: b.data.user.vip ?
                    1 : 0,
                wintype: "xplayer_m3u8",
                u: b.data.video.userid,
                bt: f.isPad ? "pad" : "phone",
                os: f.isMobileIOS ? "ios" : "Android",
                rst: f.isMobileIOS ? "m3u8" : "3gphd",
                tict: 0,
                aw: "w",
                vs: "1.0"
            };
            null != c.config.partner_config && (d.partnerid = c.initConfig.client_id, d.atm = c.config.partner_config.atm);
            for (var h in c.initConfig.adconfig)d[h] = c.initConfig.adconfig[h];
            this._param = d;
            this._ti = encodeURIComponent(b.data.video.title);
            this._k = encodeURIComponent((b.data.video.tags || []).join("|"));
            this.loadPartnerParam()
        }, loadPartnerParam: function () {
        },
        partnerParse: function () {
        }, initRequestParam_: function (b) {
            var d = {
                ct: b.data.video.category_letter_id,
                cs: b.data.video.subcategories,
                v: b.data.id,
                t: parseInt(b.data.video.seconds),
                u: b.data.video.userid,
                fileid: "todo",
                winType: "xplayer_m3u8",
                partnerid: c.config.partnerId,
                sid: this.sid,
                k: "",
                td: "todo"
            };
            d.s = b.data.show ? b.data.show.id : "";
            b.user && (d.vip = b.data.user.vip ? 1 : 0);
            d.paid = b.data.show ? b.data.show.pay : 0;
            for (var f in c.initConfig.adconfig)d[f] = c.initConfig.adconfig[f];
            this._param = d
        }, bindEvent: function () {
            var b =
                this;
            this.fSkipAd = function () {
                b.adplayer.video.pause();
                window.open("http://cps.youku.com/redirect.html?id=000002bf", "", "", !1)
            };
            this._adskip.addEventListener("click", this.fSkipAd, !1);
            this._adknowdet.addEventListener("click", function () {
                h.log("detail clicked");
                b.adplayer.onAdClick("")
            }, !1)
        }, addEventListener: function (b, c) {
            this._handler[b] = c
        }, removeEventListener: function (b) {
            this._handler[b] = null
        }, dispatch: function (b) {
            b && this._handler[b.type] && (b._target = this, this._handler[b.type](b))
        }, show: function () {
            c.show(this._advert)
        },
        hide: function () {
            c.hide(this._advert)
        }, setLeftSecond: function (b) {
            h.log(b);
            this._adsec && (this._adsec.innerText = b)
        }, splitVTVC: function (b) {
            h.log("split adinfo vt vc");
            this._vtvc = [];
            var c = {}, d;
            for (d in b)"VAL" != d && (c[d] = b[d]);
            c.VAL = [];
            b = b.VAL;
            for (d = 0; d < b.length; d++)2 === parseInt(b[d].VT) ? (b[d].pos_ = d - 1 - this._vtvc.length, this._vtvc.push(b[d])) : null == b[d].RS || ("" == b[d].RS.trim() || null == b[d].VID || null == b[d].VQT) || c.VAL.push(b[d]);
            return c
        }, buildTestData: function () {
            return {
                VAL: [{
                    AL: 15,
                    VID: 147660115,
                    VQT: "flv",
                    SUS: [{U: "http://mytestdata.com1"}, {U: "http://mytestdata.com2"}],
                    SU: [],
                    SUE: [],
                    CU: "http://www.baidu.com",
                    CUM: [{U: "http://cum"}],
                    RS: "http://fasdfa"
                }, {
                    AL: 15,
                    VID: 15252,
                    VQT: "flv",
                    SUS: [{U: "http://mytestdata.com1"}, {U: "http://mytestdata.com2"}],
                    SU: [],
                    SUE: [],
                    CU: "http://www.bing.com",
                    CUM: [{U: "http://cum"}],
                    RS: "http://fasdfa",
                    VT: 2,
                    VC: "http://vc.com"
                }]
            }
        }, checkSkip: function (b) {
            b && 0 === parseInt(b.SKIP) && (c.hide(this._adskip), this.SKIP = 0)
        }, adParseUnited: function (b) {
            this.checkSkip(b);
            i.appendItem("phase", "adinfo");
            this.reportTime("adinfo");
            b && b.VAL && (h.log("<b>before split val length =  " + b.VAL.length + "</b>"), b = this.splitVTVC(b), h.log("<b>after : val length =  " + b.VAL.length + "</b>"));
            if (null == b || null == b.VAL || 0 == b.VAL.length)b = {VAL: []}, this.dispatch({
                type: "unitedfrontadinfook",
                data: {info: {VAL: []}, vtvc: this._vtvc || []}
            }); else {
                var b = new Aa(b, this._vtvc), c = this;
                b.addEventListener(R, function (b) {
                    h.log("ad info adapter ok");
                    c.dispatch({type: "unitedfrontadinfook", data: {info: b.data, vtvc: c._vtvc || []}})
                });
                b.run()
            }
        }, adParse: function (b) {
            this.checkSkip(b);
            i.appendItem("phase", "adinfo");
            this.reportTime("adinfo");
            if (b && b.VAL)for (var b = this.splitVTVC(b), c = b.VAL, d = 0; d < c.length; d++)this._adsrcs.push(c[d].RS), this._adsecs.push(parseInt(c[d].AL));
            h.log("frontad len =" + this._adsrcs.length);
            this.dispatch({
                type: "frontAdinfook",
                data: {
                    ids: this._advids || [],
                    urls: this._adsrcs,
                    seconds: this._adsecs,
                    info: b,
                    vtvc: this._vtvc || []
                }
            })
        }, buildPauseData: function () {
            return adinfo = {
                P: 10, VAL: [{
                    RS: "http://static.atm.youku.com/Youku2013/201307/0715/27896/600-430.jpg",
                    RST: "img",
                    AT: 73,
                    SU: [],
                    SUS: [{U: "http://mf.atm.youku.com/mshow?v=137006183&at=73&ct=d&cs=1003&ca=135159&ie=150597&uid=1234567&ck=137689524489061H&al=0&bl=1&s=&td=&st=1&vl=1200.0&ap=4&sid=1&cr=0&tvb=0&pr=100&oidtype=27896%7C1&tpa=null&rid=&os=1&dt=1&aw=a&avs="}],
                    SUE: [],
                    CU: "http://vid.atm.youku.com/mclick?v=137006183&at=73&ct=d&cs=1003&ca=135159&ie=150597&uid=1234567&ck=137689524489061H&al=0&bl=1&s=&td=&st=1&vl=1200.0&ap=4&sid=1&cr=0&tvb=0&pr=100&oidtype=27896%7C1&tpa=null&rid=&os=1&dt=1&aw=a&avs=&u=http://static.youku.com/pub/youku/fragment/panel_phone.html&md5=f2450cd80597324b57d986147dc1b3a9",
                    W: 400,
                    H: 300,
                    CF: "1"
                }]
            }
        }, adParsePause: function (b) {
            h.log("<b> ad parse pause </b>");
            i.appendItem("phase", "pauseadinfo");
            this.reportTime("adinfo");
            null == b || null == b.VAL || 0 == b.VAL.length || 10 != b.P ? this.dispatch({type: "pauseAdinfoerror"}) : (h.log("<b>pause ad len = " + b.VAL.length + "</b>"), this.dispatch({
                type: "pauseAdinfook",
                data: {info: b}
            }))
        }, frontAd: function () {
            this._param.fu = this.player.controls.fullscreenPanel.fullFlag() ? 1 : 0;
            this._param.p = 7;
            var b = this;
            s.jsonp({
                url: "http://mf.atm.youku.com/mf?", data: D(this._param,
                    {ti: this._ti, k: this._k}), time: 8E3, success: function (d) {
                    f.isIPAD ? (h.log("<font color=red> new m3u8 api</font>"), c.OLD_M3U8 = !1, b.adParseUnited(d)) : b.adParse(d)
                }, fail: function () {
                    h.log("adinfo timeout");
                    b.reportTime("adinfo", -1);
                    b.dispatch({type: "frontAdinfotimeout", data: {timeout: 8E3}})
                }
            });
            this.recordTime("adinfo")
        }, pauseAd: function () {
            this._param.r_ = parseInt(1E4 * Math.random());
            this._param.p = 10;
            this._param.fu = this.player.controls.fullscreenPanel.fullFlag() ? 1 : 0;
            var b = this;
            s.jsonp({
                url: "http://mp.atm.youku.com/mp?",
                data: D(this._param, {ti: this._ti, k: this._k}),
                callback: "callback",
                time: 1E3,
                success: function (c) {
                    b.adParsePause(c)
                },
                fail: function () {
                    h.log("pause ad info timeout");
                    b.reportTime("adinfo", -1);
                    b.dispatch({type: "pauseadinfotimeout", data: {timeout: 8E3}})
                }
            });
            this.recordTime("adinfo")
        }, recordTime: function (b) {
            null == this._timearr && (this._timearr = {});
            this._timearr[b] = (new Date).getTime()
        }, reportTime: function (b, c, d) {
            null == this._timearr && (this._timearr = {});
            c = c || (new Date).getTime() - this._timearr[b];
            this.player._reporter.sendUepReport({
                adinfo: "valfload",
                advideo: "adload"
            }[b], c, d)
        }, backAd: function () {
            this._param.fu = this.player.controls.fullscreenPanel.fullFlag();
            this._param.p = 9;
            this._param.ctu = 0;
            var b = this;
            s.jsonp({
                url: "http://mb.atm.youku.com/mb?",
                data: D(this._param, {ti: this._ti, k: this._k}),
                time: 5E3,
                success: function (c) {
                    b.adParse(c)
                },
                fail: function () {
                    h.log("adinfo timeout");
                    b.dispatch({type: " backAdinfotimeout", data: {timeout: 5E3}})
                }
            })
        }, insertAd: function () {
            this._param.ps = 0;
            this._param.pt = 0
        }
    };
    var P = function (b, c) {
        this.adplayer = b;
        this.addata = c;
        "undefined" == typeof c.curnum && (this.addata.curnum = 0)
    };
    P.prototype = {
        sendSUS: function () {
            var b = this.addata.info.VAL[this.addata.curnum].SUS;
            if ("undefined" != typeof b)for (var c = 0; c < b.length; c++)f.Log(b[c].U)
        }, sendUnitedVTVC: function (b) {
            var b = b + 2, c = this.addata.info.VAL[0].VTVC;
            this._vtccache || (this._vtccache = []);
            for (var d = null, g = 1E6, f = 1E5, i = 0; i < c.length; i++) {
                var m = c[i].U, j = parseInt(c[i].T), o = b - j;
                0 <= o && o < f && (f = o, d = m, g = j)
            }
            null != d && -1 == this._vtccache.indexOf(g) && (this._vtccache.push(g), h.log("<b> vc = " + d + "</b>"), F(d,
                "js"))
        }, sendVC: function () {
            var b = this.addata.info.VAL[this.addata.curnum];
            "undefined" != typeof b.VT && F(b.VC, "js")
        }, sendSUS_: function () {
            var b = this.addata.info, c = this.addata.curnum + 2, d = b["A" + c].ATMSU, g = b["A" + c].ISOSU;
            f.Log(b["A" + c].SU);
            f.Log(d);
            f.Log(g)
        }, sendSUE: function () {
            var b = this.addata.info.VAL[this.addata.curnum].SUE;
            if ("undefined" != typeof b)for (var c = 0; c < b.length; c++)f.Log(b[c].U)
        }, sendSUE_: function () {
            var b = this.addata.info, c = this.addata.curnum + 2, d = b["A" + c].COU;
            f.Log(b["A" + c].OU);
            f.Log(d)
        }, sendSU: function (b) {
            var c =
                this.addata.info.VAL[this.addata.curnum].SU;
            if ("undefined" != typeof c) {
                this._sucache || (this._sucache = []);
                for (var d = 1E4, g = 1E6, h = 0; h < c.length; h++) {
                    var i = parseInt(c[h].T), m = b - i;
                    0 <= m && m < d && (d = m, g = i)
                }
                if (1E6 != g && -1 == this._sucache.indexOf(g)) {
                    this._sucache.push(g);
                    for (h = 0; h < c.length; h++)parseInt(c[h].T) == g && f.Log(c[h].U)
                }
            }
        }, sendSU_: function (b) {
            var c = c + 2, d = this.addata.info["A" + c].MT;
            d && b >= parseInt(d) && (b = this.addata.info["A" + c].CMU, f.Log(this.addata.info["A" + c].MU), f.Log(b))
        }, sendCUM: function () {
            var b = this.addata.info.VAL[this.addata.curnum].CUM;
            if ("undefined" != typeof b)for (var c = 0; c < b.length; c++)f.Log(b[c].U)
        }, sendUnitedCUM: function (b) {
            var c = this.addata.info.VAL[0].CUM;
            if (!("undefined" == typeof c || 0 === c.length))for (var d = 0; d < c.length; d++)if (b < parseInt(c[d].T)) {
                for (b = 0; b < (c[d].CUM || []).length; b++)f.Log(c[d].CUM[b].U);
                break
            }
        }, sendCUM_: function () {
            var b = this.addata;
            f.Log(b.info["A" + (b.curnum + 2)].VCU)
        }
    };
    var Ba = function (b, c) {
        this._handler = {};
        this.player = b;
        this.video = this.player.video;
        this.controls = this.player.controls;
        this._adplugin = this.player._adplugin;
        this._adplugin.adplayer = this;
        this._addata = c.data.info;
        this.video.preload = "none";
        this.video.src = this._addata.VAL[0].RS;
        h.log("ad src=" + this.video.src);
        this.video.style.display = "block";
        this._playTag = [];
        this.bindAdEvent();
        this._adreporter = new P(this, {curnum: 0, info: this._addata})
    };
    Ba.prototype = {
        addEventListener: function (b, c) {
            this._handler[b] = c
        }, removeEventListener: function (b) {
            this._handler[b] = null
        }, dispatch: function (b) {
            b && this._handler[b.type] && (b._target = this, this._handler[b.type](b))
        }, bindAdEvent: function () {
            this.bind_fadtoplay =
                c.bindAsEventListener(this, this.onPlayClick);
            this.bind_fadplay = c.bindAsEventListener(this, this.onAdPlay);
            this.bind_fadended = c.bindAsEventListener(this, this.onAdEnded);
            this.bind_faderror = c.bindAsEventListener(this, this.onAdError);
            this.bind_fadpause = c.bindAsEventListener(this, this.onAdPause);
            this.bind_fadsuspend = c.bindAsEventListener(this, this.onAdSuspend);
            this.bind_fadstalled = c.bindAsEventListener(this, this.onAdStalled);
            this.bind_fadwaiting = c.bindAsEventListener(this, this.onAdWaiting);
            this.bind_fadloadedmetadata =
                c.bindAsEventListener(this, this.onAdLoadedMetaData);
            this.bind_fadtimeupdate = c.bindAsEventListener(this, this.onAdTimeUpdate);
            this.bind_fademptied = c.bindAsEventListener(this, this.onEmptied);
            c.addEventHandler(this.video, "play", this.bind_fadplay);
            c.addEventHandler(this.video, "error", this.bind_faderror);
            c.addEventHandler(this.video, "pause", this.bind_fadpause);
            c.addEventHandler(this.video, "suspend", this.bind_fadsuspend);
            c.addEventHandler(this.video, "stalled", this.bind_fadstalled);
            c.addEventHandler(this.video,
                "waiting", this.bind_fadwaiting);
            c.addEventHandler(this.video, "loadedmetadata", this.bind_fadloadedmetadata);
            c.addEventHandler(this.video, "timeupdate", this.bind_fadtimeupdate);
            c.addEventHandler(this.video, "emptied", this.bind_fademptied);
            this.shadow = this.controls.buttons.shadow;
            this.videobtn = this.controls.buttons.videobtn;
            c.addEventHandler(this.videobtn, "click", this.bind_fadtoplay, !0)
        }, removeAdEvent: function () {
            c.removeEventHandler(this.video, "play", this.bind_fadplay);
            c.removeEventHandler(this.video,
                "ended", this.bind_fadended);
            c.removeEventHandler(this.video, "error", this.bind_faderror);
            c.removeEventHandler(this.video, "pause", this.bind_fadpause);
            c.removeEventHandler(this.video, "suspend", this.bind_fadsuspend);
            c.removeEventHandler(this.video, "stalled", this.bind_fadstalled);
            c.removeEventHandler(this.video, "waiting", this.bind_fadwaiting);
            c.removeEventHandler(this.video, "timeupdate", this.bind_fadtimeupdate);
            c.removeEventHandler(this.video, "loadedmetadata", this.bind_fadloadedmetadata);
            c.removeEventHandler(this.video,
                "loadedmetadata", this.bind_fademptied);
            c.removeEventHandler(this.videobtn, "click", this.bind_fadtoplay, !0)
        }, onPlayClick: function () {
            this.video.play()
        }, play: function () {
            this.video.load();
            this.video.play()
        }, onEmptied: function () {
            this.checkPause()
        }, leftSecond: function () {
            return parseInt(Math.max(0, this._addata.VAL[0].AL - this.video.currentTime))
        }, clearTimer: function () {
            clearInterval(this._checkTimer);
            this._checkTimer = null
        }, checkPause: function () {
            if (!this._checkTimer) {
                var b = this;
                this._timelist = [];
                this._checkTimer =
                    setInterval(function () {
                        if (b.video.paused)b.onAdPause(); else b._timelist.push(b.video.currentTime), 3 <= b._timelist.length && (1 > Math.abs(b._timelist[0] - b._timelist[2]) && (h.log("<b>ad unexpected pause</b>"), b.video.play(), 0 == b.leftSecond() && (h.log("<b>exception left = 0 </b>"), b.onAdEnded())), b._timelist = [])
                    }, 1E3)
            }
        }, onAdPlay: function () {
            this.checkPause();
            var b = this.controls.container.poster;
            c.hide(this.controls.buttons.videobtn);
            c.hide(b);
            c.hide(c.get(".x-video-info"));
            this.video.style.display = "block";
            this._adplugin.setLeftSecond(this.leftSecond());
            var d = this;
            setTimeout(function () {
                h.log("ad media timeout check begin = " + d._adBegin);
                d._adBegin || (d.removeAdEvent(), d._adplugin.hide(), d._adplugin.reportTime("advideo", -1, !1), d.dispatch({
                    type: B,
                    data: !0
                }))
            }, 15E3);
            this._playTag[0] || (this._playTag[0] = !0, this._adfirsttu = !1, this._adplugin.recordTime("advideo"), i.appendItem("phase", "adplay"))
        }, uglyClose: function () {
            h.log("united ugly close");
            this.onAdError()
        }, onAdError: function () {
            this.removeAdEvent();
            this._adplugin.hide();
            this._adplugin.reportTime("advideo", -1, !1);
            this.clearTimer();
            this.dispatch({type: B, data: !0})
        }, onAdEnded: function () {
            h.log("united ad ended");
            this._adreporter.sendSUE();
            this.removeAdEvent();
            this._adplugin.hide();
            this.clearTimer();
            this.dispatch({type: E, data: !0});
            i.appendItem("phase", "adend")
        }, onAdPause: function () {
            this.player.video.ended || (c.show(this.controls.buttons.videobtn), c.hide(this.controls.buttons.shadow))
        }, onAdSuspend: function () {
            h.log("<font color=red>ad suspend</font>")
        }, onAdStalled: function () {
            h.log("<font color=red>ad stalled</font>")
        },
        onAdWaiting: function (b) {
            this.controls.onWaiting(b)
        }, onAdTimeUpdate: function () {
            if (this.video.currentTime > this._addata.VAL[0].AL)this.onAdEnded(); else c.hide(this.controls.buttons.loading), this._adBegin = !0, c.hide(this.controls.buttons.loading), this._adplugin.setLeftSecond(this.leftSecond()), this._adreporter.sendSU(this.video.currentTime), this._adreporter.sendUnitedVTVC(this.video.currentTime), this._adfirsttu || (this._adplugin.show(), this._adreporter.sendSUS(), this._adfirsttu = !0, this._adplugin.reportTime("advideo"),
            0 === this._adplugin.SKIP && this.dispatch({type: J}))
        }, onAdLoadedMetaData: function () {
            this._adBegin = !0
        }, onAdClick: function () {
            this.video.pause();
            this._adreporter.sendUnitedCUM(this.video.currentTime || 0);
            for (var b = this._addata.VAL[0].CU, c = this.video.currentTime, d = 0; d < b.length; d++) {
                var g = b[d], f = g.U;
                if (c <= parseInt(g.T)) {
                    window.open(f, "", "", !1);
                    break
                }
            }
        }
    };
    var ha = function (b) {
        c.config = b;
        this.parentBox = r(c.config.parentBox);
        null == c.config.width && (c.config.width = this.parentBox.offsetWidth);
        this.buildDirectDom(this.parentBox)
    };
    ha.prototype = {
        buildDirectDom: function (b) {
            b.innerHTML = "<div id=x-player class=" + G(c.config.width) + '><div class="x-video-poster"><img id="x-img"/></div><div class="x-video-button"><div class="x-video-play-ico"></div></div><div class="x-video-info"><h1 class="x-title"></h1><div class="x-video-state" style="display:none"><span class="x-time-span"></span></div><div class="x-showmore"></div><div class="x-mask"></div></div>'
        }, bindEvent: function () {
            this._videobtn = c.get(".x-video-button", this.parentBox);
            c.addEventHandler(this._videobtn,
                "click", c.bindAsEventListener(this, this.redirect))
        }, startPlay: function (b, d) {
            c.v = b;
            c.videoInfo = d;
            c.videoInfo._playListData = b.data;
            this._pimg = c.get("#x-img", this.parentBox);
            this._pimg.src = b.data.video.logo;
            this._title = c.get(".x-title", this.parentBox);
            this._title.innerHTML = b.data.video.title;
            this._timespan = c.get(".x-time-span", this.parentBox);
            this._timespan.innerHTML = c.getTime(b.data.video.seconds);
            c.show(c.get(".x-video-poster", this.parentBox));
            c.show(c.get(".x-video-info", this.parentBox));
            this.adapterForReport();
            this._reporter = new U(this, c.v, c.videoInfo._sid);
            this.bindEvent()
        }, onPlayStart: function () {
            f.playerCurrentState = f.playerState.PLAYER_STATE_PLAYING;
            c.config.events && c.config.events.onPlayStart && (h.log(f.playerCurrentState), h.log("api:onplaystart"), c.config.events.onPlayStart())
        }, getSrc: function () {
            if (this.src)return this.src;
            "m3u8" == c.config.content ? this.src = c.videoInfo.src : null != c.videoInfo._videoSegsDic && null != c.videoInfo._videoSegsDic[c.defaultVideoType] && (this.src = c.videoInfo._videoSegsDic[c.defaultVideoType][0].src);
            return this.src
        }, redirect: function () {
            var b = this.getSrc();
            h.log("redirect play src=" + b);
            f.isMIUI ? window.location.href = b : window.open(b, "", "", !1);
            this.onPlayStart();
            this._reporter.addPlayerStaticReport();
            this._reporter.sendTSLog(60);
            this._reporter.sendUserActionReport("xps", "c");
            this._reporter.sendThirdPartyReport("xplayer_dl");
            this._reporter.sendClientConsumeReport()
        }, adapterForReport: function () {
            this.controls = {
                fullscreenPanel: {
                    fullFlag: function () {
                        return 1
                    }
                }
            };
            this.video = {src: this.getSrc()};
            this.getQuality =
                function () {
                    return "m"
                }
        }
    };
    (function (b) {
        b.getCurrAbsPath = function () {
            if (document.currentScript)return document.currentScript.src;
            var b;
            try {
                a.b.c()
            } catch (c) {
                b = c.fileName || c.sourceURL || c.stack || c.stacktrace, !b && window.opera && (b = (("" + c).match(/of linked script \S+/g) || []).join(" "))
            }
            if (b)return b = b.split(/[@ ]/g).pop(), b = "(" == b[0] ? b.slice(1, -1) : b, b.replace(/(:\d+)?:\d+$/i, "");
            b = -1 === ("" + document.querySelector).indexOf("[native code]");
            for (var d = document.scripts, f = d.length - 1, h; h = d[f--];)if ("interactive" ===
                h.readyState)return b ? h.getAttribute("src", 4) : h.src
        }
    })(window);
    var ga = /(http|https|file):\/\/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?/.exec(getCurrAbsPath())[0];
    F(ga + "/h5player/play.css?ver=" + d.replace(/[-:]/g, ""), "css");
    var fa = function (b) {
        this._id = b.id;
        this._pid = b.pid || "";
        this._url = b.url;
        this._box = r(b.parentBox);
        this._gotInfo = !1;
        b.width = r(b.parentBox).offsetWidth;
        b.height = r(b.parentBox).offsetHeight;
        c.config = b;
        this.request()
    };
    fa.prototype = {
        request: function () {
            var b = this;
            s.jsonp({
                url: this._url + "/h5/videos/play.json",
                data: {vid: this._id, pid: this._pid},
                time: 5E3,
                success: function (c) {
                    b.parse(c)
                },
                fail: function () {
                    b.showError()
                }
            })
        }, parse: function (b) {
            this._gotInfo = !0;
            0 == b.error ? (this.videoSrc = b.results.url, this.imgSrc = b.results.cover, this.buildDom()) : this.showError()
        }, buildDom: function () {
            this._box.innerHTML = '<div id="x-player class="' + this.cssAdapt(parseInt(c.config.width)) + '><video class="x-video-player" id="youku-html5player-video" style="width: 100%; height: 100%; position: relative; display: none; top: -1000px;" src="' +
                this.videoSrc + '"></video><div class=x-video-poster><img src="' + this.imgSrc + '"></img></div><div class="x-video-loading"></div><div id="x-video-button" class="x-video-button"><div class="x-video-play-ico"></div></div></div>';
            this.video = c.get(".x-video-player", this._box);
            this.cover = c.get(".x-video-poster", this._box);
            this.videoBtn = c.get(".x-video-button", this._box);
            this.loading = c.get(".x-video-loading", this._box);
            c.addEventHandler(this.videoBtn, "click", c.bindAsEventListener(this, this.onOverBtnClick));
            c.addEventHandler(this.video, "ended", c.bindAsEventListener(this, this.onEnded));
            c.addEventHandler(this.video, "timeupdate", c.bindAsEventListener(this, this.onTimeUpdate));
            c.addEventHandler(this.video, "waiting", c.bindAsEventListener(this, this.onWaiting))
        }, showError: function () {
            this._box.innerHTML = '<div style="background:#000; color:#FFF; text-align:center; color: white; line-height:' + this._box.offsetHeight + 'px " >\u89c6\u9891\u4fe1\u606f\u51fa\u9519 <a href="http://m.youku.com/webapp/dl?app=youku&amp;source=webqr" title="\u4e0b\u8f7d\u4f18\u9177\u5ba2\u6237\u7aef" target="_blank"><button type="button" class="x-btn" style="background: #3bb4fc;text-align: center;color: #fff;border-radius: 1rem;">\u7528app\u89c2\u770b</button></a></div>'
        },
        onOverBtnClick: function () {
            this.video.play();
            this.loading.style.display = "block";
            this.videoBtn.style.display = "none"
        }, onEnded: function () {
            this.cover.style.display = "block";
            this.videoBtn.style.display = "block";
            this.loading.style.display = "none";
            this.video.style.display = "none"
        }, onTimeUpdate: function () {
            "none" != this.loading.style.display && (this.loading.style.display = "none");
            "block" != this.video.style.display && (this.video.style.display = "block");
            "none" != this.cover.style.display && (this.cover.style.display = "none")
        },
        onWaiting: function () {
            this.loading.style.display = "block"
        }, cssAdapt: function (b) {
            return f.isIPAD && 0 <= window.location.href.indexOf("v.youku.com") ? "x-player" : 200 >= b ? "x-player x-player-200" : 300 >= b ? "x-player x-player-200-300" : 660 >= b ? "x-player x-player-300-660" : 800 >= b ? "x-player x-player-660-800" : "x-player"
        }
    };
    var Ca = function (b, d) {
        this.setting = {
            debug: !1,
            controls: c.get(".x-console"),
            feedback: c.get(".x-feedback"),
            container: {poster: c.get(".x-video-poster"), logo: c.get(".x-video-logo")},
            buttons: {
                pointVideo: c.get("#point-video"),
                playControl: c.get(".x-play-control"),
                play: c.get("#x-playbtn"),
                loading: c.get(".x-video-loading"),
                videobtn: c.get(".x-video-button"),
                videoinfo: c.get(".x-video-info"),
                shadow: c.get(".x-trigger"),
                currentTime: c.get(".x-time-current"),
                totalTime: c.get(".x-time-duration"),
                fullscreen: c.get(".x-fullscreen")
            },
            classNames: {play: "x-playing", pause: "x-pause"},
            init: function () {
            }
        };
        c.extend(this.setting, d);
        this.player = b;
        this.dashboard = this.setting.controls;
        this.container = this.setting.container;
        this.progressBar = new sa(b);
        this.progressBar.uCurrentTime = this.setting.buttons.currentTime;
        this.miniProgressBar = new oa(b);
        this.fullscreenPanel = new ka(b);
        this.interactionPanel = new ma(b);
        this.xplayer = c.get("#x-player");
        this.buttons = this.setting.buttons
    };
    Ca.prototype = {
        init: function (b, d) {
            this.playLimit = new qa(this.player, b, this.setting.client_id);
            this.buttons.totalTime.innerHTML = d.totalTime ? c.getTime(d.totalTime) : "00:00";
            this.resetProgress();
            this.buttons.play.className = this.setting.classNames.play;
            var f = this.container.poster.getElementsByTagName("img")[0];
            c.config.poster ? f.src = c.config.poster : b.data.trial && "episodes" != b.data.trial.type || b.data.error ? null != b.data.error && -203 == b.data.error.code && (this.container.poster.style.backgroundColor = "black", f.parentNode.removeChild(f), c.show(this.container.poster)) : (f.src = b.data.video.logo, this.container.poster.style.display = "block");
            this._qualityPanel = new va(this.player, b);
            this._languagePanel = new na(this.player, b);
            this._playratePanel = new ra(this.player, b);
            this._payPanel = new pa(this.player, b);
            this._feedbackPanel =
                new T(this.player, b);
            this._informationPanel = new la(this.player, b);
            this.tipPanel = new ya(this.player, b);
            this.showlistPanel = new xa(this.player, b);
            this.bindDynamicEvent();
            this._isShowLogo = !1;
            c.hide(this.container.logo);
            this.playLimit.limitAppShow();
            b.data.show && (b.data.show.exclusive && "none" == b.data.stream[0].logo) && (this._isShowLogo = !0)
        }, bindDynamicEvent: function () {
            this.bind_mutualHide = c.bindAsEventListener(this, this.mutualHide);
            c.addEventHandler(this._languagePanel, "click", this.bind_mutualHide);
            c.addEventHandler(this._qualityPanel,
                "click", this.bind_mutualHide);
            c.addEventHandler(this.showlistPanel, "click", this.bind_mutualHide);
            c.addEventHandler(this._playratePanel, "click", this.bind_mutualHide);
            this.bind_progress = c.bindAsEventListener(this, this.onProgress);
            c.addEventHandler(this.progressBar, "progressing", this.bind_progress);
            c.addEventHandler(this.progressBar, "progressend", c.bindAsEventListener(this, this.onProgressEnd));
            c.addEventHandler(this._languagePanel, "settingdone", c.bindAsEventListener(this, this.onSettingDone));
            c.addEventHandler(this._qualityPanel,
                "settingdone", c.bindAsEventListener(this, this.onSettingDone));
            c.addEventHandler(this._playratePanel, "settingdone", c.bindAsEventListener(this, this.onSettingDone));
            c.addEventHandler(this._languagePanel, "settingshow", c.bindAsEventListener(this, this.onSettingShow));
            c.addEventHandler(this._qualityPanel, "settingshow", c.bindAsEventListener(this, this.onSettingShow));
            c.addEventHandler(this._playratePanel, "settingshow", c.bindAsEventListener(this, this.onSettingShow));
            c.addEventHandler(this._languagePanel, "settinghide",
                c.bindAsEventListener(this, this.onSettingHide));
            c.addEventHandler(this._qualityPanel, "settinghide", c.bindAsEventListener(this, this.onSettingHide));
            c.addEventHandler(this._playratePanel, "settinghide", c.bindAsEventListener(this, this.onSettingHide));
            c.addEventHandler(this.fullscreenPanel, "enterfullscreen", c.bindAsEventListener(this, this.onEnterFullScreen));
            c.addEventHandler(this.fullscreenPanel, "exitfullscreen", c.bindAsEventListener(this, this.onExitFullScreen))
        }, retimer: function () {
            h.log("retimer");
            this.autoHideDashBoard()
        },
        hideDashBoard: function () {
            var b = this._payPanel, c = this._informationPanel, d = this.miniProgressBar, g = this.interactionPanel, f = this._languagePanel;
            this.setting.controls.style.display = "none";
            d.show();
            b.hide();
            c.hide();
            g.hideStatus();
            f.hide();
            this._qualityPanel.hide();
            this._playratePanel.hide()
        }, autoHideDashBoard: function (b) {
            this.dashboardTimer && clearTimeout(this.dashboardTimer);
            var d = this;
            this.dashboardTimer = setTimeout(function () {
                var f = c.get(".x-showlist");
                f && "block" == f.style.display ? d.autoHideDashBoard(b) :
                d.player.video.paused || d.hideDashBoard()
            }, b || 2E3)
        }, onMultiTouch: function () {
        }, showUglyHint: function () {
        }, closeUglyHint: function () {
        }, showBoardInfo: function () {
            c.show(this.setting.controls);
            this.miniProgressBar.hide();
            this._informationPanel.show();
            this._payPanel.hasPayInfo() && this._payPanel.show()
        }, toggleDashBoard: function (b) {
            if (!("touchend" == b.type && 1 < b.changedTouches.length)) {
                this._sx = this._sx || 0;
                this._sy = this._sy || 0;
                b.changedTouches = b.changedTouches || [{clientX: this._sx, clientY: this._sy}];
                var c = {
                    x: this._sx,
                    y: this._sy
                }, b = {x: b.changedTouches[0].clientX, y: b.changedTouches[0].clientY};
                !this._stmtag && (1 !== this._sactionType && this.isTouchTooShort(c, b, 100)) && (c = this.setting.controls.style.display, "none" == c || "" == c ? (this.player._reporter.sendUserActionReport("xcd", "c"), this.showBoardInfo(), this.autoHideDashBoard(), ta = (new Date).getTime()) : (this.player._reporter.sendUserActionReport("xhd", "c"), clearTimeout(this.dashboardTimer), this.hideDashBoard()))
            }
        }, bindAdVideoBtnEvent: function () {
            c.addEventHandler(this.buttons.videobtn,
                "touchstart", c.bindAsEventListener(this, this.onVideoBtnTouchStart));
            c.addEventHandler(this.buttons.videobtn, "touchend", c.bindAsEventListener(this, this.onVideoBtnTouchEnd));
            c.initConfig.autoplay && !c.isIPHONE && c.fire(this.buttons.videobtn, "touchend")
        }, bindVideoBtnEvent: function () {
            c.addEventHandler(this.buttons.videobtn, "click", c.bindAsEventListener(this, this.onVideoBtnClick), !0)
        }, startPlay: function () {
            if (this._isShowLogo) {
                var b = this, d = function () {
                    c.show(b.container.logo);
                    b.player.video.removeEventListener("canplay",
                        d)
                };
                b.player.video.addEventListener("canplay", d)
            }
        }, bindEvent: function () {
            h.log("bind event");
            this.bind_uireinit = c.bindAsEventListener(this, this.uiInit);
            this.bind_play = c.bindAsEventListener(this, this.play);
            this.bind_redirect = c.bindAsEventListener(this, this.redirect);
            this.bind_showTimeTip = c.bindAsEventListener(this, this.showTimeTip);
            this.bind_hideTimeTip = c.bindAsEventListener(this, this.hideTimeTip);
            this.bind_changeVolume = c.bindAsEventListener(this, this.changeVolume);
            this.bind_toggleVolume = c.bindAsEventListener(this,
                this.toggleVolume);
            this.bind_gestureChange = c.bindAsEventListener(this, this.onGestureChange);
            this.bind_toggleDashBoard = c.bindAsEventListener(this, this.toggleDashBoard);
            this.bind_retimer = c.bindAsEventListener(this, this.retimer);
            c.addEventHandler(this.progressBar, "click", this.bind_uireinit);
            c.addEventHandler(this.setting.controls, "click", this.bind_retimer);
            c.addEventHandler(this.setting.controls, "touchstart", this.bind_retimer);
            c.addEventHandler(this.buttons.playControl, "click", this.bind_play);
            "directsrc" ==
            c.config.playType && (!f.isIPHONE && !f.isIPOD ? c.addEventHandler(this.buttons.videobtn, "click", this.bind_redirect, !0) : c.addEventHandler(this.buttons.videobtn, "click", c.bindAsEventListener(this, this.playIPH), !0));
            c.addEventHandler(this.buttons.shadow, "touchstart", c.bindAsEventListener(this, this.shadowTouchStart));
            c.addEventHandler(this.buttons.shadow, "touchmove", c.bindAsEventListener(this, this.shadowTouchMove));
            c.addEventHandler(this.buttons.shadow, "touchend", c.bindAsEventListener(this, this.shadowTouchEnd));
            c.addEventHandler(this.buttons.shadow, "click", this.bind_toggleDashBoard);
            c.addEventHandler(this.buttons.shadow, "touchend", c.bindAsEventListener(this, this.onMultiTouch));
            c.addEventHandler(this.buttons.shadow, "gesturechange", this.bind_gestureChange)
        }, removeEvent: function () {
            h.log("remove event begin");
            c.removeEventHandler(this.progressBar, "click", this.bind_uireinit);
            c.removeEventHandler(this.buttons.playControl, "click", this.bind_play);
            c.removeEventHandler(this.buttons.shadow, "click", this.bind_toggleDashBoard);
            c.removeEventHandler(this.progressBar, "touchstart", this.bind_uireinit);
            c.removeEventHandler(this._languagePanel, "click", this.bind_mutualHide);
            c.removeEventHandler(this._qualityPanel, "click", this.bind_mutualHide);
            c.removeEventHandler(this._playratePanel, "click", this.bind_mutualHide);
            this.progressBar.removeEvent();
            this.fullscreenPanel.removeEvent();
            this._languagePanel.removeEvent();
            this._qualityPanel.removeEvent();
            h.log("remove event end")
        }, onGestureChange: function (b) {
            b.preventDefault();
            var c = -1 !==
                this.fullscreenPanel.zoomStatus().indexOf("in");
            if (1.1 < b.scale && c || 0.9 > b.scale && !c)b.method = "m", this.fullscreenPanel.switchFullScreen(b)
        }, toggleVolume: function () {
        }, changeVolume: function () {
        }, rePlay: function () {
            h.log("replay");
            this.player._reporter.sendUserActionReport("xrp", "c");
            A = !1;
            (this._recommend = c.get(".x-recommend")) && c.get("#x-player").removeChild(this._recommend);
            this.resetProgress();
            this._first = !1;
            this.player.replay();
            h.log("replay func end")
        }, redirect: function (b) {
            this.player.redirect(b)
        },
        hideFacade: function () {
            var b = this.container.poster;
            c.hide(this.buttons.videobtn);
            c.hide(b);
            c.hide(c.get(".x-feedback"));
            h.log("<font color=blue>hide facade</font>")
        }, onVideoBtnTouchStart: function (b) {
            this._vtsx = b.targetTouches[0].clientX;
            this._vtsy = b.targetTouches[0].clientY
        }, onVideoBtnTouchEnd: function (b) {
            h.log("<font color=red>video btn clicked</font>");
            b = b || {};
            A ? this.rePlay() : b && b.changedTouches && 50 < Math.abs(b.changedTouches[0].clientY - this._vtsy) ? h.log("videobtn too long y") : (this.playLimit.isLimit() &&
            this.playLimit.limitAppHide(), this.player._reporter.addPlayerDurationReport(59), this.player._reporter.sendUserActionReport("xps", "c"), this.hideFacade(), !0 === this._hasAdReq ? this.player.video.play() : (this._hasAdReq = !0, this.player.video.load(), h.log("active src=" + this.player.video.src), this.player.requestAd()))
        }, onVideoBtnClick: function () {
            if ((f.isIPHONE || f.isIPOD) && c.v.data.trial)this.player.video.style.display = "block";
            if (A)this.rePlay(); else if (!c.v.data.trial || !("episodes" != c.v.data.trial.type && 0 ==
                c.v.data.trial.time))this.player.video.load(), this.player.video.play()
        }, playIPH: function () {
            if (!this.iphTag) {
                this.player.video.load();
                var b = this;
                this.player.video.addEventListener("timeupdate", function (c) {
                    4 == c.target.readyState && (b.iphTag = !0)
                })
            }
            this.player.video.play()
        }, play: function (b) {
            b = b || {};
            if (A)this.rePlay(); else {
                var c = this.player.video.paused;
                h.log("m3u8 isPause = " + c + " e = " + b);
                c ? (0 === this._payPanel.activeTime ? (this._payPanel.activeTime = -1, this.player.seek(0)) : this.player.video.play(), this.player._reporter.sendUserActionReport("xpl",
                    "c"), this.interactionPanel.setStatus("\u64ad\u653e")) : (this.player.video.pause(), this.player._reporter.sendUserActionReport("xpa", "c"), this.interactionPanel.setStatus("\u6682\u505c"));
                this.checkPauseAd()
            }
        }, isProperWH: function (b, d) {
            var f = c.get("#x-player");
            return f.offsetWidth >= b && f.offsetHeight >= d
        }, isNeedPauseAd: function () {
            return this.player.video.paused && c.isLandScape()
        }, checkPauseAd: function () {
            this.isNeedPauseAd() ? (this._pauseAdPlugin = new W(this.player, c.v, c.videoInfo._sid), this._pauseAdPlugin.addEventListener("pauseAdinfook",
                c.bindAsEventListener(this, this.onPauseAdInfoOK)), this._pauseAdPlugin.addEventListener("pauseadinfotimeout", c.bindAsEventListener(this, this.onPauseAdInfoTimeout)), this._pauseAdPlugin.addEventListener("pauseAdinfoerror", c.bindAsEventListener(this, this.onPauseAdInfoERROR)), window.adpluginobject = this._pauseAdPlugin, this._pauseAdPlugin.pauseAd(), h.log("send pause ad request<br/>")) : (h.log("<font color=blue> donot need pause ad </font>"), this.hidePauseAd(), this.playLimit.isLimit() && (this.playLimit.hasPausead = !1))
        }, hidePauseAd: function () {
            c.hide(c.get(".x-ad-pause"))
        }, onPauseAdInfoOK: function (b) {
            h.log("pause info ok");
            this._pauseAdStart || (this._pauseAdStart = !0);
            this.playLimit.isLimit() && this.playLimit._content && (this.playLimit.limitAppHide(), this.playLimit.hasPausead = !0);
            this._pauseAdPlayer = new za(this.player, b);
            this._pauseAdPlayer.play()
        }, onPauseAdInfoTimeout: function (b) {
            h.log("pause info timeout = " + b.data.timeout);
            this._pauseAdStart || (this._pauseAdStart = !0)
        }, onPauseAdInfoERROR: function () {
            h.log("<font color=blue>pause info error no info</font>");
            this._pauseAdStart || (this._pauseAdStart = !0)
        }, autoShow: function () {
            this.show();
            var b = this;
            setTimeout(function () {
                b.hide()
            }, 5E3)
        }, mutualHide: function (b) {
            b._target == this._languagePanel ? (this._qualityPanel.hide(!0), this._playratePanel.hide(!0), this.showlistPanel.hide()) : b._target == this._qualityPanel ? (this._languagePanel.hide(!0), this._playratePanel.hide(!0), this.showlistPanel.hide()) : b._target == this.showlistPanel ? (this._qualityPanel.hide(!0), this._languagePanel.hide(!0), this._playratePanel.hide(!0)) : b._target ==
            this._playratePanel && (this._qualityPanel.hide(!0), this._languagePanel.hide(!0), this.showlistPanel.hide())
        }, show: function (b) {
            b ? c.show(this.buttons[b]) : c.show(this.setting.controls)
        }, hide: function (b) {
            b ? c.hide(this.buttons[b]) : c.hide(this.setting.controls)
        }, backAdPrepare: function () {
            this.dashboard.style.display = "none";
            this.buttons.shadow.display = "none"
        }, onEnded: function () {
            this.dashboard.style.display = "none";
            this.buttons.shadow.display = "none";
            this.buttons.videobtn.style.display = "block";
            this._isShowLogo &&
            c.hide(this.container.logo);
            this.container.poster.style.display = "block";
            this._informationPanel.show();
            this.miniProgressBar.hide();
            this.interactionPanel.hide();
            null == c.v.data.trial && !1 != c.initConfig.show_related && (this.playLimit.isLimit() && (this.playLimit.needOpen ? this.playLimit.limitAppEnd() : this.playLimit.limitAppHide()), this._relatedPanel = new wa(this.player, c.v))
        }, onPlay: function () {
            this.playLimit._isLimit && this.playLimit.limitAppHide();
            this.player.video.style.display = "block";
            this.buttons.play.className =
                this.setting.classNames.pause;
            this.buttons.videobtn.style.display = "none";
            this.container.poster.style.display = "none";
            this.hidePauseAd();
            this.buttons.shadow.style.display = "block";
            (this._recommend = c.get(".x-recommend")) && c.get("#x-player").removeChild(this._recommend);
            A = !1;
            this._first || (this._first = !0, this._informationPanel.show(), this.setting.controls.style.display = "block");
            this.autoHideDashBoard()
        }, onPause: function () {
            !this.playLimit.hasPausead && (this.playLimit.isLimit() && (!this.playLimit._content || !this.progressBar.maybeSeek)) && this.playLimit.limitAppShow();
            this.buttons.play.className = this.setting.classNames.play;
            c.hide(this.buttons.loading);
            this.interactionPanel.isVisible() || (this.showBoardInfo(), (!this.playLimit.isLimit() || !allow) && this.interactionPanel.setStatus("\u6682\u505c"))
        }, onWaiting: function () {
            !this.player.video.paused && "none" == this.buttons.videobtn.style.display && (this.buttons.loading.style.display = "block")
        }, onError: function () {
            this._isShowLogo && c.hide(this.container.logo)
        }, onTryPlayEnded: function () {
            h.log("try end");
            this.player.video.pause();
            this._payPanel.activeTime = 0;
            A = !0;
            this._payPanel.showTip()
        }, onTimeUpdate: function (b) {
            this.buttons.loading.style.display = "none";
            if (b.target == this.player.video) {
                var c = this.player.currentTime;
                4 == b.target.readyState && this.setProgress(c);
                this._payPanel.hasPayInfo() && c >= this._payPanel.tryDuration() && (this.onTryPlayEnded(), this.onPlayEnd());
                this.playLimit.isLimit() && c >= this.playLimit.limitTime() && this.playLimit.needOpen && this.playLimit.limitAppEnd()
            }
        }, onPlayEnd: function () {
            if (f.playerCurrentState !=
                f.playerState.PLAYER_STATE_END && (this.playLimit._limitVip && (this.playLimit._isLimit = !1), f.playerCurrentState = f.playerState.PLAYER_STATE_END, c.config.events && c.config.events.onPlayEnd))h.log("callback: on play end"), c.config.events.onPlayEnd()
        }, checkPlayLimit: function () {
            return this.playLimit.needOpen
        }, removeControls: function () {
            this.video.controls = !1
        }, loadControls: function () {
            this.video.controls = !0
        }, setProgress: function (b) {
            b = Math.min(Math.max(b, 0), c.videoInfo.totalTime);
            this.progressBar.setProgress(b);
            this.miniProgressBar.setProgress(b);
            this.buttons.currentTime.innerHTML = c.getTime(this.progressBar.playTime)
        }, resetProgress: function () {
            this.progressBar.resetProgress();
            this.miniProgressBar.resetProgress();
            this.buttons.currentTime.innerHTML = "00:00"
        }, hideTimeTip: function (b) {
            if (b.srcElement.id == this.buttons.progressHandler.id)return !1;
            this.buttons.progressTime.style.display = "none"
        }, showTimeTip: function (b) {
            if (b.srcElement.id == this.buttons.progressHandler.id || b.srcElement.id == this.buttons.progressTime.id ||
                b.srcElement.id == this.buttons.pointVideo.id)return !1;
            b = b.offsetX / this.buttons.progressBar.offsetWidth;
            this.buttons.progressTime.innerHTML = c.getTime(b * c.videoInfo.totalTime);
            this.buttons.progressTime.style.left = 100 * Math.min(Math.max(b, 0.023), 0.977) + "%";
            this.buttons.progressTime.style.display = "block"
        }, shadowTouchStart: function (b) {
            this.progressBar.maybeSeek = !0;
            1 < b.targetTouches.length ? this.interactionPanel.hide() : (this._sx = b.targetTouches[0].clientX, this._sy = b.targetTouches[0].clientY, this._smx = this._sx,
                this._smy = this._sy, this._presmx = this._sx, this._presmy = this._sy, this._deltaxs = [], this._ttime = this._stime = this.player.currentTime || 0, this._spretag = this._stmtag = !1, this._presmt = this._sactionTime = (new Date).getTime(), this._stmlrtag = this._sactionType = 0)
        }, shadowTouchMove: function (b) {
            this.progressBar.maybeSeek = !0;
            if (1 < b.targetTouches.length)this.interactionPanel.hide(); else {
                this._smx = b.targetTouches[0].clientX;
                this._smy = b.targetTouches[0].clientY;
                this._smt = (new Date).getTime();
                var c = Math.abs(this._smx - this._sx),
                    d = Math.abs(this._smy - this._sy), g = this._smt - this._sactionTime;
                0 === this._stmlrtag && (this._stmlrtag = c > d ? 1 : -1);
                1 == this._stmlrtag && b.preventDefault();
                if (1 != this._sactionType)if (100 < c && c > d && 500 > g)h.log("quick seek moving"), this.player.video.pause(), this._sactionType = 1, g = this._smx > this._sx ? 30 : -30, this.interactionPanel.setTip(this._stime, g), this.interactionPanel.show(); else if (200 > c && (100 > d && 1E3 < g) && (this._spretag = !0), this._spretag && c > d || this._stmtag)h.log("stmtag =" + this._stmtag), this._sactionType = 2, this._stmtag = !0, this.player.video.pause(), this.dragging(b)
            }
        }, shadowTouchEnd: function (b) {
            1 < b.changedTouches.length ? this.interactionPanel.hide() : (this.progressBar.maybeSeek = !1, this.adrAdapt(b), this.isShadowTouchTooShort() && !this._stmtag && 1 != this._sactionType ? h.log("too short or horizontal") : (b = Math.abs(this._smy - this._sy) > Math.abs(this._smx - this._sx) ? "xdud" : "xdlr", h.log("shadow action = " + b), this.player._reporter.sendUserActionReport(b, "d"), 2 == this._sactionType ? (h.log("<br/><b>normal seek</b>"), this.player.video.play(),
                this.player.seek(this._ttime), this.interactionPanel.hide(), this.player._reporter.sendUserActionReport("xtseek", "d")) : 1 == this._sactionType && (b = 0 < this._smx - this._sx ? 30 : -30, h.log("<br/><font color=red>quick seek deltat = " + b + " cur=" + this._stime + "</font>"), this.setProgress(this._stime + b), this.interactionPanel.setTip(this._stime, b), this.interactionPanel.show(), this.interactionPanel.autoHide(), this.player.video.play(), this.player.seek(this._stime + b), this.player._reporter.sendUserActionReport("xqseek", "d"),
                h.log("<br/>"))))
        }, dragging_: function (b) {
            var d = this._smx - this._presmx;
            this._deltaxs.push(10 < d ? d / 2 : d);
            for (var f = d = 0; f < this._deltaxs.length; f++)d += this._deltaxs[f];
            b = Math.min(Math.max(d / b.currentTarget.offsetWidth * c.videoInfo.totalTime + this._stime, 0), c.videoInfo.totalTime);
            this.setProgress(b);
            this.interactionPanel.show();
            this._ttime = b;
            this._presmx = this._smx;
            this._presmy = this._smy;
            this._presmt = this._smt
        }, dragging: function (b) {
            b = Math.min(Math.max(60 * ((this._smx - this._sx) / b.currentTarget.offsetWidth) + this._stime,
                0), c.videoInfo.totalTime);
            this.setProgress(b);
            this.interactionPanel.setTip(this._ttime, b - this._ttime);
            this.interactionPanel.show();
            this._ttime = b;
            this._presmx = this._smx;
            this._presmy = this._smy;
            this._presmt = this._smt
        }, onProgress: function (b) {
            this.interactionPanel.setTip(b.st || 0, b.dt || 0);
            this.interactionPanel.show()
        }, onProgressEnd: function () {
            this.interactionPanel.hide()
        }, onSettingDone: function () {
            this.interactionPanel.setStatus("\u8bbe\u7f6e\u6210\u529f")
        }, onSettingShow: function () {
            h.log("<b>setting show</b>");
            clearTimeout(this.pbarClickTimer);
            this.progressBar.removeClickEvent()
        }, onSettingHide: function () {
            h.log("<b>setting hide</b>");
            var b = this;
            this.pbarClickTimer = setTimeout(function () {
                b.progressBar.addClickEvent()
            }, 1E3)
        }, onEnterFullScreen: function () {
            f.isIPAD && c.addClass(this.setting.controls, "x-fs-console")
        }, onExitFullScreen: function () {
            f.isIPAD && c.removeClass(this.setting.controls, "x-fs-console")
        }, adrAdapt: function (b) {
            f.isAndroid && (this._smx = b.changedTouches[0].clientX, this._smy = b.changedTouches[0].clientY,
                h.log("<hr/>adr smy= " + this._smy + " y = " + this._sy))
        }, isShadowTouchTooShort: function (b) {
            return this.isTouchTooShort({x: this._sx, y: this._sy}, {x: this._smx, y: this._smy}, b)
        }, isTouchTooShort: function (b, c, d) {
            var g = Math.abs(c.x - b.x), g = g || 1.0E-6, b = (b = Math.abs(c.y - b.y)) || 1.0E-6;
            h.log(g + "," + b);
            d = d || 100;
            return g < d && b < d ? !0 : !1
        }, showShowListBtn: function () {
            this.showlistPanel.showListBtn()
        }, hideShowListBtn: function () {
            this.showlistPanel.hideListBtn()
        }, showLastTimeTip: function (b) {
            0 >= b || this.tipPanel.showLastTimeTip(b)
        },
        uiInit: function () {
            h.log("uiInit");
            A && (A = !1, this.buttons.videobtn.style.display = "block")
        }, onResize: function (b) {
            var d = r(c.config.parentBox).offsetWidth, f = r(c.config.parentBox).offsetHeight;
            if (d && (f && c.resizeTag) && (f = this.xplayer.className, this.xplayer && (-1 === f.indexOf("fullscreen") ? this.xplayer.className = G(d) : (d = window.innerWidth, this.xplayer.className = G(d) + " x-player-fullscreen")), this._relatedPanel))this._relatedPanel.onResize(b)
        }
    };
    var X = function (b) {
        this.video = b;
        this._startPlayTime = -1;
        this.currentTime =
            this._waitTry = 0
    };
    X.prototype = {
        getVideo: function () {
            return this.video
        }, show: function () {
            c.show(this.video)
        }, hide: function () {
            c.hide(this.video)
        }, play: function () {
            c.v && c.v.data.trial && 0 == c.v.data.trial.time ? h.log("<b> trial time = 0  </b>") : this.video.play()
        }, pause: function () {
            this.video.pause()
        }, setupControls: function (b, c) {
            this.controls && this.controls.removeEvent();
            return new Ca(b, c)
        }, hideControls: function () {
            this.controls.hide()
        }, showControls: function () {
            this.controls.show()
        }, removeControls: function () {
            this.controls.removeControls()
        },
        loadControls: function () {
            this.controls.loadControls()
        }, retry: function () {
        }, showError: function (b) {
            this.errorBox || (this.errorBox = document.createElement("div"), this.errorBox.style.cssText = "position:absolute;width:100%;top:50%;display:none;text-align:center;", this.video.parentNode.appendChild(this.errorBox));
            this.errorBox.innerHTML = b;
            this.errorBox.style.marginTop = "-" + this.errorBox.offsetHeight / 2 + "px";
            this.errorBox.style.display = "block"
        }, onLoadStart: function () {
        }, onCanPlay: function () {
        }, onLoadedData: function () {
        },
        onLoadedMetaData: function () {
        }, onAbort: function () {
        }, onError: function () {
            this._reporter.sendUserActionReport("xve", "e");
            this._reporter.sendUepReport("videoload", -1, !1);
            f.sendErrorReport(2001);
            if (0 <= this._retry--)-1 !== this.video.src.indexOf("m3u8") && (this.video.src = c.m3u8src_v2(c.v.data.id, c.defaultVideoType)), h.log("video onerror retry it ,time=" + this.currentTime + " src=" + this.video.src), this.video.load(), this.video.play(), this.seek(this.currentTime), this.controls.onError(); else if (!(this.isOnePiece() &&
                !0 == this.controls.checkPlayLimit()))if (this._errorTag)this.controls.onError(); else this._errorTag = !0, f.playerCurrentState = f.playerState.PLAYER_STATE_ERROR, f.showError({
                code: "2001",
                message: "\u62b1\u6b49\uff0c\u89c6\u9891\u51fa\u9519\uff0c\u8bf7\u5237\u65b0"
            }, "\u62b1\u6b49\uff0c\u89c6\u9891\u51fa\u9519\uff0c\u8bf7\u5237\u65b0")
        }, onPause: function () {
            this.controls.onPause()
        }, onPlayIPH: function () {
            h.log("onplayiph");
            this.onPlayStart();
            this._firstPlayTag ? !0 == this._endedIPH && (this._reporter.tsInit(), this._reporter.sendTSLog(60),
                this._reporter.addPlayerDurationReport(62)) : (this._firstPlayTag = !0, this._reporter.addPlayerStaticReport(), this._reporter.rs = 1, this._reporter.sendTSLog(60), this._reporter.sendUserActionReport("xps", "c"), this._reporter.sendLoadedTime(3), this._reporter.sendThirdPartyReport("xplayer_iph"), this._reporter.sendClientConsumeReport())
        }, onTimeUpdateIPH: function () {
            this.currentTime = this.video.currentTime
        }, onEndedIPH: function () {
            this.onPlayEnd();
            this._reporter.addPlayerDurationReport(61);
            this._reporter.sendTSLog(61);
            this._endedIPH = !0
        }, onPlay: function () {
            h.log("onplay");
            this.controls.onPlay();
            if (this._firstPlayTag) {
                if (f.playerCurrentState == f.playerState.PLAYER_STATE_END || f.playerCurrentState == f.playerState.PLAYER_STATE_ERROR)this.onPlayStart()
            } else this._firstPlayTag = !0, this.onPlayStart(), c.initConfig.firsttime ? (h.log("starttime = " + c.initConfig.firsttime), this.seek(c.initConfig.firsttime)) : this.seekToLastPoint() || this.skipHead(), this._startPlayTime = (new Date).getTime(), this._reporter.addPlayerStaticReport(), this._reporter.rs =
                1, this._reporter.sendTSLog(60), this._reporter.sendClientConsumeReport();
            i.appendItem("phase", "videoplay")
        }, onVolumeChange: function () {
        }, onPlaying: function () {
        }, onStalled: function (b) {
            h.log("<b>stalled</b>");
            if (this.isOnePiece() || b.target == this.video)this.controls.onWaiting(b)
        }, onSuspend: function () {
        }, onWaiting: function (b) {
            if (this.isOnePiece() || b.target == this.video)this.controls.onWaiting(b)
        }, onSeeked: function () {
            h.log("onSeeked waitSkip=" + this._waitSeek + " try= " + this._waitTry);
            if (!isNaN(this._waitSeek)) {
                var b =
                    this._waitSeek;
                10 < Math.abs(this.video.currentTime - b) && 5 >= this._waitTry ? (this._waitTry += 1, this.seek(b)) : this._waitSeek = "NaN"
            }
        }, onSeeking: function (b) {
            h.log("seeking");
            if (this.isOnePiece() || b.target == this.video) {
                var c = this;
                setTimeout(function () {
                    c.controls.onWaiting(b)
                }, 100)
            }
        }, onDurationChange: function () {
        }, onProgress: function () {
        }, onRateChange: function () {
        }, customWaiting: function () {
            var b = this;
            !1 == this.video.paused && this._lastTime === this.currentTime && (h.log("custom waiting!:) networkstate=" + this.video.networkState),
                this.controls.onWaiting());
            this._lastTime = this.currentTime;
            setTimeout(function () {
                b.customWaiting()
            }, 5E3)
        }, sendLoadedTime: function () {
            var b = 0, b = -1 == this._startPlayTime ? 0 : (new Date).getTime() - this._startPlayTime;
            this._reporter.sendLoadedTime(b)
        }, onTimeUpdate: function (b) {
            if (this.isOnePiece())this.currentTime = this.video.currentTime, c.unitedTag && (this.currentTime -= c.unitedTag.offset); else {
                for (var d = 0, h = 0; h < z; h++)d += parseInt(c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType][h].seconds);
                this.currentTime = d + this.video.currentTime
            }
            this.controls.onTimeUpdate(b);
            this._firstflag || (this._firstflag = !0, this.customWaiting(), this.recordLocalPlayPoint(), this.sendLoadedTime(), i.appendItem("phase", "videotimeupdate"), f.isNeedAdrTrick() && f.adrInvalidPauseCheck(this.video));
            this._comscoreflag || (this._comscoreflag = !0, this._reporter.sendThirdPartyReport("xplayer_h5"));
            this.skipTail(this.currentTime)
        }, curVideo: function () {
            return this.video
        }, getQuality: function () {
            if ("m3u8" == c.config.content) {
                var b = this.video.src;
                if (-1 !== b.indexOf("mp4"))return "m";
                if (-1 !== b.indexOf("flv"))return "f";
                if (-1 !== b.indexOf("hd2"))return "h"
            } else return "m"
        }, bufferedEnd: function () {
            var b = this.curVideo().buffered;
            return 0 == b.length ? 0 : b.end(b.length - 1)
        }, loadNextVideo: function () {
            var b = c.v.data.videos.next, d = this;
            h.log("loadNextVideo vid = " + b.encodevid);
            if (b.encodevid) {
                var f = {isFullScreen: !0, vid: b.vid, encodevid: b.encodevid, Pt: 2 == window.playmode ? b.seq : null};
                c.config.nextAutoPlay = !0;
                o.start(b.encodevid, "", c.config.content, function (b, c) {
                    d.startPlay(b,
                        c);
                    try {
                        onPlayerStart(f)
                    } catch (h) {
                        v.log("onPlayerStart error")
                    }
                })
            }
        }, onPlayEnd: function () {
            if (f.playerCurrentState != f.playerState.PLAYER_STATE_END)if (f.playerCurrentState = f.playerState.PLAYER_STATE_END, h.log(f.playerCurrentState), c.config.events && c.config.events.onPlayEnd && c.v.data.videos)h.log("callback: on play end"), c.config.events.onPlayEnd(c.v.data.videos.next); else if (c.config.loop && this.replay && (v.log("--loop--"), this.replay()), c.config.events && c.config.events.onPlayEnd)h.log("callback: on play end"),
                c.config.events.onPlayEnd()
        }, onPlayStart: function () {
            f.playerCurrentState = f.playerState.PLAYER_STATE_PLAYING;
            c.config.events && c.config.events.onPlayStart && (h.log(f.playerCurrentState), h.log("callback: on play start"), c.config.events.onPlayStart())
        }, onMiddleEnded: function () {
            z++;
            this.video.src = c.multiPieceSrc(z);
            this.video.load();
            this.video.play();
            this.video.style.display = "block";
            h.log("middle src = " + this.video.src)
        }, onEnded: function (b) {
            if (!(this.isOnePiece() && !0 == this.controls.checkPlayLimit()))if (this.isOnePiece() ||
                z == c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType].length - 1)A = !0, this._reporter.addPlayerDurationReport(61), this._reporter.sendTSLog(61), this.clearLocalPlayPoint(), this.showEndCard(b), i.appendItem("phase", "videoended"); else this.onMiddleEnded(b)
        }, showEndCard: function (b) {
            this.video.style.display = "none";
            this.controls.onEnded(b);
            this.onPlayEnd()
        }, onBeginFullscreen: function () {
        }, onEndFullscreen: function () {
            if ((f.isIPHONE || f.isIPOD) && null != c.v.data.trial)this.video.style.display =
                "none"
        }, detectIsPlaying: function (b) {
            var c = b || 0, d = this;
            clearTimeout(this.timeoutTimer);
            0 === this.video.currentTime && 60 >= c && (this.video.load(), this.play(), this.timeoutTimer = setTimeout(function () {
                d.detectIsPlaying(++c)
            }, 1E3))
        }, isOnePiece: function () {
            return "m3u8" == c.config.content || "mp4" == c.config.content && 1 == c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType].length
        }, removeEvent: function () {
        }, bindEvent: function () {
            if (!c.v.data.error)if ("directsrc" == c.config.playType && !1 == c.isWeixin)c.addEventHandler(this.video,
                "play", c.bindAsEventListener(this, this.onPlayIPH)), c.addEventHandler(this.video, "timeupdate", c.bindAsEventListener(this, this.onTimeUpdateIPH)), c.addEventHandler(this.video, "ended", c.bindAsEventListener(this, this.onEndedIPH)), c.addEventHandler(this.video, "webkitendfullscreen", c.bindAsEventListener(this, this.onEndFullscreen)); else {
                var b = {
                    loadstart: "onLoadStart",
                    canplay: "onCanPlay",
                    loadeddata: "onLoadedData",
                    loadedmetadata: "onLoadedMetaData",
                    abort: "onAbort",
                    error: "onError",
                    pause: "onPause",
                    waiting: "onWaiting",
                    stalled: "onStalled",
                    suspend: "onSuspend",
                    play: "onPlay",
                    volumechange: "onVolumeChange",
                    playing: "onPlaying",
                    seeked: "onSeeked",
                    seeking: "onSeeking",
                    durationchange: "onDurationChange",
                    progress: "onProgress",
                    ratechange: "onRateChange",
                    timeupdate: "onTimeUpdate",
                    ended: "onEnded",
                    webkitbeginfullscreen: "onBeginFullscreen",
                    webkitendfullscreen: "onEndFullscreen"
                }, d;
                for (d in b)c.addEventHandler(this.video, d, c.bindAsEventListener(this, this[b[d]]))
            }
        }
    };
    var z = -1, A = !1, ta = 0, ua = 600, y = [], C = {
        flvhd: "\u6807\u6e05", flv: "\u6807\u6e05",
        mp4: "\u9ad8\u6e05", hd2: "\u8d85\u6e05"
    };
    c.WIN_TYPE = 30;
    c.defaultVideoType = null;
    c.defaultLanguage = "guoyu";
    c.resizeTag = !0;
    c.extend = function (b, c) {
        for (var d in c)b[d] = c[d]
    };
    c.inherits = function (b, c) {
        var d = function () {
        };
        d.prototype = c.prototype;
        b.prototype = new d;
        b.prototype.constructor = b
    };
    c.fire = function (b, c) {
        if (document.createEventObject) {
            var d = document.createEventObject();
            return b.fireEvent("on" + c, d)
        }
        d = document.createEvent("HTMLEvents");
        d.initEvent(c, !0, !0);
        return !b.dispatchEvent(d)
    };
    c.bind = function (b, c) {
        return function () {
            return c.apply(b,
                arguments)
        }
    };
    c.bindAsEventListener = function (b, c) {
        var d = Array.prototype.slice.call(arguments).slice(2);
        return function (f) {
            return c.apply(b, [f || window.event].concat(d))
        }
    };
    c.getCurrentStyle = function (b) {
        return b.currentStyle || document.defaultView.getComputedStyle(b, null)
    };
    c.addEventHandler = function (b, d, f, g) {
        c.config.isMobile && ("click" == d && !g) && (d = "touchend");
        b.addEventListener ? b.addEventListener(d, f, !1) : b.attachEvent ? b.attachEvent("on" + d, f) : b["on" + d] = f
    };
    c.removeEventHandler = function (b, d, f, g) {
        c.config.isMobile &&
        ("click" == d && !g) && (d = "touchend");
        b.removeEventListener ? b.removeEventListener(d, f, !1) : b.detachEvent ? b.detachEvent("on" + d, f) : b["on" + d] = null
    };
    c.show = function (b) {
        b.style.display = "video" === b.tagName.toLowerCase() ? "" : "block"
    };
    c.hide = function (b) {
        b && (b.style.display = "none")
    };
    c.getLeftPosition = function (b) {
        for (var c = b.offsetLeft; b.offsetParent;)b = b.offsetParent, c += b.offsetLeft;
        return c
    };
    c.get = function (b, c) {
        return (c || document).querySelector(b)
    };
    c.pieceLength = function () {
        return "m3u8" == c.config.content ? 1 : c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType].length
    };
    c.multiPieceSrc = function (b) {
        return b >= c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType].length ? "" : c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType][b].src
    };
    c.getTime = function (b) {
        if (!b)return "00:00";
        var c = Math.floor(b), b = c % 60, c = Math.floor(c / 60);
        return (10 > c ? "0" + c : c) + ":" + (10 > b ? "0" + b : b)
    };
    c.addClass = function (b, d) {
        c.hasClass(b, d) || (b.className += " " + d)
    };
    c.hasClass = function (b, c) {
        return RegExp("(^| )" + c + "( |$)").test(b.className)
    };
    c.removeClass = function (b, c) {
        b.className =
            b.className.replace(RegExp("(^| )" + c + "( |$)"), " ").replace(/^\s+|\s+$/g, "")
    };
    c.m3u8src = function (b, c) {
        var d = "http://v.youku.com/player/getM3U8/vid/" + b + "/type/" + c + "/ts/" + parseInt((new Date).getTime() / 1E3);
        if (f.isIPHONE || f.isIPOD)d += "/useKeyFrame/0";
        return d + "/v.m3u8"
    };
    c.m3u8src_v2 = function (b, d) {
        if (c.OLD_M3U8)return c.m3u8src(b, d);
        var h = {vid: b, type: d, ts: parseInt((new Date).getTime() / 1E3), keyframe: f.isIPHONE ? 0 : 1};
        c.password && (h.password = c.password);
        c.password && (c.initConfig.client_id && c.config.partner_config &&
        1 == c.config.partner_config.status && 1 == c.config.partner_config.passless) && (h.client_id = c.initConfig.client_id);
        var g = encodeURIComponent(L(N(O(c.mk.a4 + "poz" + f.userCache.a2, [19, 1, 4, 7, 30, 14, 28, 8, 24, 17, 6, 35, 34, 16, 9, 10, 13, 22, 32, 29, 31, 21, 18, 3, 2, 23, 25, 27, 11, 20, 5, 15, 12, 0, 33, 26]).toString(), f.userCache.sid + "_" + b + "_" + f.userCache.token)));
        h.ep = g;
        h.sid = f.userCache.sid;
        h.token = f.userCache.token;
        h.ctype = "12";
        h.ev = "1";
        h.oip = c.v.data.security.ip;
        return "http://pl.youku.com/playlist/m3u8?" + t(D(h, c.getUCParam(b)))
    };
    c.isLandScape = function () {
        return 90 == window.orientation || -90 == window.orientation
    };
    c.getUCParam = function (b) {
        var d = {};
        "undefined" != typeof getUCSecret ? d.xk = getUCSecret(b) : "undefined" != typeof uckey ? (d.uc_param_str = "xk", d.xk = uckey.getUCKey(b)) : !0 == c.isUCBrowserAndValidVersion() && (d.uc_param_str = "xk");
        return d
    };
    c.isUCBrowserAndValidVersion = function () {
        var b = navigator.userAgent, c = b.search(/ucbrowser/i);
        return -1 != c && 9.8 <= parseFloat(b.substr(c + 10, 4)) ? !0 : !1
    };
    YoukuHTML5Player = function (b, d) {
        null == b.parentBox &&
        (b.parentBox = "parentBox");
        b.expand && 0 < parseInt(b.width) ? (r(b.parentBox).style.width = b.width + "px", r(b.parentBox).style.height = b.height + "px") : (b.width = r(b.parentBox).offsetWidth, b.height = r(b.parentBox).offsetHeight);
        c.config = b;
        this.setting = d || {};
        this.root = r(c.config.parentBox);
        var f = r(c.config.parentBox), g = "", h = parseInt(c.config.width);
        c.isWeixin && (g += "webkit-playsinline");
        if (c.initConfig.iswifi || c.initConfig.autoplay)g += " autoplay";
        g = '<div id="x-player" class="' + G(h) + '"><video class="x-video-player"' +
            g + '></video><div class="x-video-poster"><img/></div><div class="x-video-logo"><div class="x-icon-exclusive"></div></div><div class="x-video-loading"></div><div class="x-video-info"><h1 class="x-title"></h1><div class="x-video-state"></div><div class="x-showmore"></div><div class="x-mask"></div></div><div id="x-video-button" class="x-video-button"><div class="x-video-play-ico"></div></div><div class="x-feedback"><div class="x-message"><div class="x-message-txt"></div><div class="x-message-btn"></div></div><div class="x-mask"></div></div><div class="x-pay"><div class="x-pay-txt"><h1><em class="vip"></em></h1><p class="x-pay-tips"></p></div><div class="x-pay-btn"><button type="button" class="x-btn x-btn-try">\u514d\u8d39\u8bd5\u770b</button><button type="button" class="x-btn x-btn-pay"></button></div></div><div class="x-pay-panel" style="display: none;"><div class="x-pay-txt"></div><div class="x-pay-content"><div class="x-pay-title"></div><div><button class="x-button x-try">\u514d\u8d39\u8bd5\u770b</button><button class="x-button x-buy"></button></div></div></div><div class="x-advert"><div class="x-advert-info"><div class="x-advert-skip"><div class="x-advert-txt"></div><div class="x-mask"></div></div><div class="x-advert-countdown"><div class="x-advert-txt"></div><div class="x-mask"></div></div></div><div class="x-advert-detail"><div class="x-advert-txt">\u8be6\u7ec6\u4e86\u89e3<span class="x-ico-detail"></span></div><div class="x-mask"></div></div></div><div class="x-ad-pause"></div><div class="x-prompt"></div><div class="x-dashboard"><div class="x-progress-mini"><div class="x-progress-track-mini"></div><div class="x-progress-load-mini"></div><div class="x-progress-play-mini"></div></div><div class="x-console"><div class="x-progress"><div class="x-progress-track"><div class="x-progress-load"></div><div class="x-progress-play"></div><div class="x-progress-seek"><div class="x-seek-handle"></div></div></div></div><div class="x-controls"><div class="x-play-control"><button class="x-control-btn"><b id=x-playbtn class="x-playing"><em>\u64ad\u653e</em></b></button></div><div class="x-time-display"><span class="x-time-current">00:00</span><span class="x-time-splite">/</span><span class="x-time-duration">00:00</span></div><div class="x-settings"><div class="x-playspeed"></div><div class="x-playshow" style="display:none"><button class="x-control-btn" title="\u9009\u96c6">\u9009\u96c6</button></div><div class="x-localization"></div><div class="x-quality"></div><div class="x-fullscreen"><button class="x-control-btn" type="button" title="\u5168\u5c4f\u6a21\u5f0f" rol="button"><b class="x-zoomin"><em>\u5168\u5c4f</em></b></button></div></div></div></div></div><div class="x-showlist"></div><div class="x-tips"></div><div class="x-trigger"></div></div>';
        f.innerHTML = g;
        f = c.get("video", this.root);
        X.apply(this, [f].concat([].slice.call(arguments)));
        this.video.style.width = "100%";
        this.video.style.height = "100%";
        this.video.style.display = "none";
        this.video.style.position = "relative";
        this._firstPlayTag = !1;
        this._retry = 2;
        this.uiAdapter()
    };
    c.inherits(YoukuHTML5Player, X);
    c.extend(YoukuHTML5Player.prototype, {
        startPlay: function (b, d, f) {
            if (b && b.data && (b.data.show = b.data.show || {}, d.abstarttime = (new Date).getTime(), d._playListData = b.data, d._user = b.user, c.v = b, c.videoInfo =
                    d, c.extend(this.setting, f), !b.data.error || !this.processError(b, d, f))) {
                this._reporter = new U(this, c.v, c.videoInfo._sid);
                this.controls = this.setupControls(this, this.setting);
                this.controls.init(c.v, c.videoInfo);
                this.mpieceReport();
                this.createIdNode();
                if (this.isNeedAdRequest())this.processAd(); else if (this.controls.bindVideoBtnEvent(), this.realStartPlay(), 1 == c.initConfig.ucautoplay)this.controls.onVideoBtnClick({});
                "m3u8" == c.config.content && new ia(this.video)
            }
        }, isNeedAdRequest: function () {
            "undefined" == typeof this._frontAdTag &&
            (this._frontAdTag = !1);
            f.isNeedFrontAd = !this._frontAdTag && "directsrc" != c.config.playType;
            return f.isNeedFrontAd
        }, processAd: function () {
            if (this.isNeedAdRequest() && (this._frontAdTag = !0, this._adplugin = new W(this, c.v, c.videoInfo._sid), this.bind_frontAd = c.bindAsEventListener(this, this.onFrontAdInfoOK), this.bind_frontAdInfoTimeout = c.bindAsEventListener(this, this.onFrontAdInfoTimeout), this._adplugin.addEventListener("frontAdinfook", this.bind_frontAd, !1), this._adplugin.addEventListener("frontAdinfotimeout",
                    this.bind_frontAdInfoTimeout), this.bind_unitedFrontAd = c.bindAsEventListener(this, this.onUnitedFrontAdInfoOK), this._adplugin.addEventListener("unitedfrontadinfook", this.bind_unitedFrontAd, !1), this.bind_backAdInfoOK = c.bindAsEventListener(this, this.onBackAdInfoOK), this.bind_backAdInfoTimeout = c.bindAsEventListener(this, this.onBackAdInfoTimeout), this._adplugin.addEventListener("backAdinfook", this.bind_backAdInfoOK, !1), this._adplugin.addEventListener(" backAdinfotimeout", this.bind_backAdInfoTimeout), this.bind_uglyCloseAd =
                    c.bindAsEventListener(this, this.onUglyCloseAd), this._adplugin.addEventListener("uglyclosead", this.bind_uglyCloseAd), this.controls.bindAdVideoBtnEvent(), window.adpluginobject = this._adplugin, 1 == c.initConfig.ucautoplay))this.controls.onVideoBtnTouchEnd({})
        }, requestAd: function () {
            this._adplugin && this._adplugin.frontAd()
        }, onUglyCloseHint: function () {
            this.controls.showUglyHint()
        }, onUglyCloseAd: function () {
            h.log("ugly close");
            this.controls.closeUglyHint();
            this.adplayer.uglyClose()
        }, onFrontAdInfoTimeout: function () {
            this._hasStartPlay = !0;
            this.realStartPlay(!0)
        }, onUnitedFrontAdInfoOK: function (b) {
            h.log("<b>on united front adinfo ok</b>");
            var d = b.data.info;
            if (0 == b.data.info.VAL.length)h.log("<b>onUnitedFrontAdInfoOK val length == 0 </b>"), this.loadVTVC(b.data.vtvc), this.video.src = c.m3u8src_v2(c.v.data.id, c.defaultVideoType), this.unitedStartPlay(d, !0); else {
                this.adplayer = new Ba(this, b);
                var f = this;
                this.adplayer.addEventListener(E, function () {
                    f.controls.startPlay();
                    h.log("<font color=red>united ad end</font>");
                    f._realFlag || (f._realFlag = !0, f.adplayer.clearTimer(), f.unitedStartPlay(d))
                }, !1);
                this.adplayer.addEventListener(B, function () {
                    h.log("<font color=red>united ad error</font>");
                    f._realFlag || (c.unitedTag = null, f._realFlag = !0, f.adplayer.clearTimer(), f.video.src = c.m3u8src_v2(c.v.data.id, c.defaultVideoType), f.unitedStartPlay(d, !0))
                }, !1);
                this.adplayer.addEventListener(J, function () {
                    h.log("<b>ugly hint</b>");
                    f.onUglyCloseHint()
                }, !1);
                this.adplayer.play();
                this.createIdNode()
            }
        }, loadVTVC: function (b) {
            for (var c = 0; c < b.length; c++)F(b[c].VC, "js")
        },
        onFrontAdInfoOK: function (b) {
            h.log("onFrontAdInfoOK");
            if (!0 !== this._hasStartPlay)if (0 == b.data.urls.length)this.loadVTVC(b.data.vtvc), this.realStartPlay(!0); else {
                f.playerCurrentState = f.playerState.PLAYER_STATE_AD;
                h.log(f.playerCurrentState);
                this.adplayer = new V(this, b);
                var c = this;
                this.adplayer.addEventListener(E, function (b) {
                    h.log("ad end");
                    c._realFlag || (c._realFlag = !0, c.adplayer.clearTimer(), c.realStartPlay(b.data))
                }, !1);
                this.adplayer.addEventListener(B, function (b) {
                    h.log("<font color=red>ad error</font>");
                    c._realFlag || (c._realFlag = !0, c.adplayer.clearTimer(), c.realStartPlay(b.data))
                }, !1);
                this.adplayer.addEventListener(J, function () {
                    h.log("<b>ugly hint</b>");
                    c.onUglyCloseHint()
                }, !1);
                this.adplayer.play();
                this.createIdNode()
            }
        }, onBackAdInfoTimeout: function () {
            h.log("onBackAdInfoTimeout");
            this.showEndCard()
        }, onBackAdInfoOK: function (b) {
            h.log("onBackAdInfoOK");
            if (0 == b.data.urls.length)this.showEndCard(); else {
                this.adplayer = new V(this, b);
                var c = this;
                this.adplayer.addEventListener(E, function () {
                    c.showEndCard()
                });
                this.adplayer.addEventListener(B, function () {
                    c.showEndCard()
                });
                this.adplayer.play()
            }
        }, prepareVideoTag: function () {
            this.video.preload = "none";
            "m3u8" == c.config.content ? this.video.src = c.videoInfo.src : null != c.videoInfo._videoSegsDic && null != c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType] && (this.video.src = c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType][0].src);
            c.v.data.trial && ("episodes" != c.v.data.trial.type && 0 == c.v.data.trial.time) && (this.video.src = null);
            this.createIdNode()
        }, createIdNode: function () {
            if (!r(c.config.id)) {
                var b = document.createElement("div");
                b.id = c.config.id;
                r(c.config.parentBox).appendChild(b)
            }
        }, redirect: function () {
            var b = "";
            "m3u8" == c.config.content ? b = c.videoInfo.src : null != c.videoInfo._videoSegsDic && null != c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType] && (b = c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType][0].src);
            h.log("redirect play src=" + b);
            this._reporter.addPlayerStaticReport();
            this._reporter.sendTSLog(60);
            this._reporter.sendUserActionReport("xps", "c");
            window.open(b, "", "", !1);
            this._reporter.sendClientConsumeReport();
            this.onPlayStart()
        }, realStartPlay: function (b) {
            h.log("realStartPlay " + b);
            this.controls.bindEvent();
            this.controls.startPlay();
            this.bindEvent();
            this.prepareVideoTag();
            this.playVideos(b)
        }, unitedStartPlay: function (b, d) {
            h.log("<b>united start play </b>");
            c.unitedTag = {offset: b.VAL.length ? b.VAL[0].AL : 0};
            this.controls.bindEvent();
            this.bindEvent();
            if (!0 === d)this.video.load(), this.video.play(), this.controls.startPlay();
            else this.onPlay();
            this.controls.onPlay()
        }, playVideos: function (b) {
            h.log("playVideos " + b);
            A = !1;
            z = 0;
            this.video.style.display = (f.isIPHONE || f.isIPOD) && null != c.v.data.trial ? "none" : "block";
            if (c.config.autoplay || c.config.nextAutoPlay || b)h.log("src= " + this.video.src + " auto = " + b), this.video.load(), this.video.play()
        }, processError: function (b) {
            var d = b.data.error.code;
            if (-301 == d || -307 == d || -308 == d)return b.data.trial ? b.data.trial.time = 0 : b.data.trial = {time: 0}, !1;
            c.hide(c.get(".x-video-poster"), this.root);
            this.feedbackPanel =
                new T(this, b);
            return !0
        }, mpieceReport: function () {
            "mp4" == c.config.content && (c.videoInfo._videoSegsDic && null != c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType] && 1 < c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType].length) && h.log("mpiece report")
        }, resize_: function (b, d, f) {
            h.log("resize=" + c.resizeTag);
            d && (f && c.resizeTag && this.controls) && (b = this.controls.xplayer.className, this.controls && this.controls.xplayer && (-1 === b.indexOf("fullscreen") ? this.controls.xplayer.className =
                G(d) : (d = window.innerWidth, this.controls.xplayer.className = G(d) + " x-player-fullscreen")))
        }, uiAdapter: function () {
            "index" == c.config.wintype && (c.hide(c.get(".x-localization", this.root)), c.hide(c.get(".x-quality", this.root)));
            c.get("#x-video-button", this.root).className = "x-video-button";
            "m3u8" != c.config.content && c.hide(c.get(".x-quality", this.root));
            var b = this;
            window.addEventListener("resize", function (c) {
                h.log("window.resize");
                if (b.controls)b.controls.onResize(c)
            }, !1)
        }, isOutTryDuration: function (b) {
            return this.tryDuration ?
            b >= this.tryDuration : !1
        }, replay: function () {
            z = 0;
            this._ireflag = this._comscoreflag = !1;
            this._firstflag = f.adrPlayTrick = !1;
            this.video.style.display = "block";
            this.isOnePiece() || (this.video.src = c.multiPieceSrc(z));
            f.isIPAD && (this.video.src = c.m3u8src_v2(c.v.data.id, c.defaultVideoType), c.unitedTag = null);
            this.video.load();
            this.video.play();
            this._reporter.tsInit();
            this._reporter.sendTSLog(60);
            this._reporter.addPlayerDurationReport(62)
        }, seekToLastPoint: function () {
            if (f.isAndroid)return !1;
            var b = c.v.data.id, d = -1;
            c.v.data.playlog &&
            (d = c.v.data.playlog.lastpoint / 1E3);
            var l = parseInt(i.getItem(b + "_playpoint")) || -1, b = -1;
            -1 != d && -1 != l ? (b = d, 60 > Math.abs(d - l) && (b = l)) : (b = d, -1 == d && (b = l));
            h.log("lastpoint=" + b);
            d = i.getItem("youku_ignore_lasttime");
            d = parseInt(d) || 0;
            return -1 !== b && 120 <= b && 3 > d && !c.v.data.trial && b < c.videoInfo.totalTime - 120 ? (this.controls.showLastTimeTip(b), f.isAndroid && (this._waitSeek = b), this.seek(b), !0) : !1
        }, clearLocalPlayPoint: function () {
            var b = c.v.data.id;
            clearTimeout(this._recordLPPTimer);
            i.removeItem(b + "_playpoint")
        }, recordLocalPlayPoint: function () {
            var b =
                c.v.data.id + "", d = this.currentTime || 0, f = this;
            this._recordLPPTimer = setTimeout(function () {
                f.recordLocalPlayPoint()
            }, 1E4);
            i.removeItem(b + "_playpoint");
            if (600 <= c.v.data.video.seconds && (d < c.videoInfo.totalTime - 120 && !c.v.data.trial && 120 <= d) && (i.setItem(b + "_playpoint", d), !this.updatePPVids)) {
                this.updatePPVids = !0;
                d = i.getItem("youku_playpoint_vids") || "";
                if ("" == d)d = b; else {
                    for (var d = d.split(":"), g = 0; g < d.length; g++)d[g] == b && (d[g] = "");
                    d.push(b);
                    d = d.join(":");
                    for (b = 0; ":" == d.charAt(b);)b++;
                    d = d.substring(b);
                    d = d.replace(/:(:)+/g,
                        ":")
                }
                b = d.split(":");
                30 < b.length && (h.log("slice"), i.removeItem(b[0] + "_playpoint"), d = b.slice(1).join(":"));
                h.log("youku_playpoint_vids=" + d);
                i.setItem("youku_playpoint_vids", d)
            }
        }, skipHead: function () {
            if (!f.isAndroid) {
                var b = parseInt((c.v.data.dvd || {}).head || -1);
                h.log("skiphead = " + b);
                -1 != b && (this.controls.tipPanel.onSkipHead(), "true" == i.getItem("youku_conf_skip") && (f.isAndroid && (this._waitSeek = b / 1E3), this.seek(b / 1E3)))
            }
        }, skipTail: function (b) {
            if (!f.isAndroid) {
                var d = parseInt((c.v.data.dvd || {}).tail || -1);
                -1 != d && (b >= d / 1E3 - 10 && !this._tailTip) && (h.log("skiptail(act before 10) =" + d), this._tailTip = !0, this.controls.tipPanel.onSkipTail());
                -1 != d && (b >= d / 1E3 && !this._tailSkipped) && (this._tailSkipped = !0, "true" == i.getItem("youku_conf_skip") && this.seek(parseInt(c.v.data.video.seconds) - 1))
            }
        }, assistSkipTail: function (b) {
            var d = parseInt((c.v.data.dvd || {}).tail || -1);
            this._tailTip = b >= d / 1E3 ? this._tailSkipped = !0 : this._tailSkipped = !1
        }, seek: function (b, d) {
            b = b || 0;
            b = Math.max(b, 0);
            c.videoInfo.totalTime && (b = Math.min(b, c.videoInfo.totalTime -
                5));
            this.isOutTryDuration(b) && (b = this.tryDuration - 1);
            this.assistSkipTail(b);
            var f = this;
            this.switchTimer && clearTimeout(this.switchTimer);
            this.currentTime = b;
            if (this.isOnePiece()) {
                var g = this.video.seekable, i = 1 == g.length ? g.end(0) : 0;
                c.unitedTag && (b += c.unitedTag.offset, i += c.unitedTag.offset);
                1 == g.length && b < i ? (h.log("seek ct = " + b + ",end = " + g.end(0)), this.seekTo(b, d)) : (this.controls.onWaiting(), this.switchTimer = setTimeout(function () {
                    f.seek(b, d)
                }, 100))
            } else h.log("multi seek"), this.multiSeekTo(b)
        }, seekTo: function (b,
                             c) {
            if (this.isOnePiece()) {
                h.log("is one piece");
                var d = this;
                try {
                    d.video.currentTime = b
                } catch (f) {
                    var i = 0;
                    this.video.addEventListener("canplay", function () {
                        1 !== i && (i = 1, h.log("canplay time=" + b), d.video.currentTime = b)
                    })
                }
                "function" == typeof c && (h.log("<b>seekto callback(mayby play)</b>"), c())
            }
        }, multiSeekTo_: function () {
            h.log("YoukuHTML5 ")
        }, multiSeekTo: function (b) {
            h.log("YoukuHTML5Player multiSeekTo !");
            for (var d = 0, f = 0, g = 0, i = 0; i < c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType].length; i++) {
                var n =
                    parseInt(c.videoInfo._videoSegsDic.streams[c.defaultLanguage][c.defaultVideoType][i].seconds), d = d + n;
                if (d > b) {
                    f = i;
                    g = n - (d - b);
                    break
                } else if (d == b) {
                    f = i + 1;
                    g = 0;
                    break
                }
            }
            this.video.pause();
            if (f == z) {
                h.log(" piece time = " + g);
                try {
                    this.video.currentTime = g
                } catch (m) {
                }
                this.video.play()
            } else {
                z = f;
                var j = 0, o = this;
                this.video.addEventListener("canplay", function () {
                    1 !== j && (j = 1, h.log("canplay time=" + g), o.video.currentTime = g)
                });
                (b = c.multiPieceSrc(z)) ? (this.video.src = b, this.video.load(), this.video.play()) : this.video.pause()
            }
            this.video.style.display =
                "block"
        }, adjustVideoRatio: function (b) {
            if (!f.isIOS) {
                if (("onorientationchange" in window || "orientation" in window) && !this._avrTag) {
                    this._avrTag = !0;
                    var d = this;
                    window.addEventListener("orientationchange", function () {
                        !0 === d.controls.fullscreenPanel.fullFlag() && d.adjustVideoRatio()
                    })
                }
                var d = this, h = this.video;
                setTimeout(function () {
                    if (1 === b)h.style.width = "100%", h.style.height = "100%", h.style.top = null, h.style.left = null; else {
                        var d = c.get(".x-player", this.root), d = d.offsetWidth / d.offsetHeight, e = h.videoWidth / h.videoHeight;
                        isNaN(e) || isNaN(d) || !isFinite(d) || !isFinite(e) ? (h.style.width = "100%", h.style.height = "100%", h.style.top = null, h.style.left = null) : d < e ? (h.style.width = "100%", h.style.height = 100 * (d / e) + "%", h.style.top = 100 * (1 / d - 1 / e) / 2 * d + "%", h.style.left = null) : (h.style.height = "100%", h.style.width = 100 * (e / d) + "%", h.style.left = 100 * ((d - e) / 2 / d) + "%", h.style.top = null)
                    }
                }, 2E3)
            }
        }
    });
    window.YoukuPlayerSelect = S;
    window.BuildVideoInfo = o;
    window.QS = function () {
        var b = {}, c = window.location.search.match(RegExp("[?&][^?&]+=[^?&]+", "g"));
        if (null !=
            c)for (var d = 0; d < c.length; d++) {
            var f = c[d], h = f.indexOf("="), i = f.substring(1, h), f = f.substring(h + 1);
            try {
                f = decodeURI(f)
            } catch (m) {
            }
            if ("true" == f)f = !0; else if ("false" != f)if (isNaN(f)) {
                if ("string" == typeof f && /{[^{^}]{0,}}/.test(f))try {
                    f = JSON.parse(f)
                } catch (j) {
                }
            } else f = +f;
            "undefined" == typeof b[i] ? b[i] = f : b[i] instanceof Array ? b[i].push(f) : b[i] = [b[i], f]
        }
        return b
    };
    window.YKP = f;
    window.YKU = s;
    window.YoukuHTML5Player = YoukuHTML5Player;
    for (var d = document.getElementsByTagName("script"), Q = 0; Q < d.length; Q++)if (-1 !== d[Q].src.indexOf("player.youku.com/jsapi")) {
        eval(d[Q].innerHTML);
        break
    }
    window.notifyYKU = function () {
        s.swfLoaded = 1
    };
    window.onPlayerReady = function () {
        c.initConfig.events && c.initConfig.events.onPlayerReady && (f.playerCurrentState = f.playerState.PLAYER_STATE_READY, h.log(f.playerCurrentState), h.log("api:flash play ready"), c.initConfig.events.onPlayerReady())
    };
    window.onPlayerStart = function () {
        c.initConfig.events && c.initConfig.events.onPlayStart && (f.playerCurrentState = f.playerState.PLAYER_STATE_PLAYING, h.log(f.playerCurrentState), h.log("api:flash play start"), c.initConfig.events.onPlayStart())
    };
    window.onPlayerComplete = function () {
        c.initConfig.events && c.initConfig.events.onPlayEnd && (f.playerCurrentState = f.playerState.PLAYER_STATE_END, h.log(f.playerCurrentState), h.log("api:flash play end"), c.initConfig.events.onPlayEnd())
    };
    window.onPlayerError = function (b) {
        if (c.initConfig.events && c.initConfig.events.onPlayError && (f.playerCurrentState = f.playerState.PLAYER_STATE_ERROR, h.log(f.playerCurrentState), "4000" != (b.code || "1000")))h.log("api:flash play error"), c.initConfig.events.onPlayError(b.message ||
            "\u64ad\u653e\u51fa\u9519", b)
    }
})();
