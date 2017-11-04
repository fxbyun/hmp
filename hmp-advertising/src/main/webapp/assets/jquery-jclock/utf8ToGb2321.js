/**
 * Created by IntelliJ IDEA 2016.2
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 16/8/18
 * Time 下午4:54
 */
//把编码转换成 gb2312编码
function UrlEncode(str) {
    var i, c, ret = "", strSpecial = "!\"#$%&'()*+,/:;<=>?@[\]^`{|}~%";
    for (i = 0; i < str.length; i++) {
        //alert(str.charCodeAt(i));

        c = str.charAt(i);
        if (c == " ")
            ret += "+";
        else if (strSpecial.indexOf(c) != -1)
            ret += "%" + str.charCodeAt(i).toString(16);
        if (z[str.charCodeAt(i)] != null) {
            d = z[str.charCodeAt(i)];
            try {
                ret += "%" + d.slice(0, 2) + "%" + d.slice(-2);
            }
            catch (e) {
                alert(" $$ error name = " + e.name + ", message = " + e.message + ", d " + i + "= " + str.charCodeAt(i))
            }
        }
        else
            ret += c;
    }
    return ret;
}

function getSpell(str, sp) {
    var i, c, t, ret = "";
    if (sp == null)
        sp = "";

    for (i = 0; i < str.length; i++) {
        if (str.charCodeAt(i) >= 0x4e00) {
            c = parseInt(z[str.charCodeAt(i)], 16);
            if (c < 55290) {
                for (t = qswhSpell.length - 1; t > 0; t = t - 2)
                    if (qswhSpell[t] <= c)
                        break;
                if (t > 0)
                    ret += qswhSpell[t - 1] + sp;
            }
        }
    }
    a
    return ret.substr(0, ret.length - sp.length);
}