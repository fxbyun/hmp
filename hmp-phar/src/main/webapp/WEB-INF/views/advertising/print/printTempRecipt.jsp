<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/20
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<!DOCTYPE html>
<html>
<head>
    <title>打印收据模板</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/print.css" type="css"/>
</head>
<body style="font-family: '微软雅黑','Microsoft YaHei'">
<div class="container print-temp">
    <div class="text-center" style="margin-bottom: 1.38em;">
        <span class="print-title">&nbsp;</span>
    </div>
    <p class="text-right" style="font-size: 12px; margin-bottom: 5px;">
        <span style="padding: 0 23px;">2016</span>
        <span></span>
        <span style="padding: 0 24px;">12</span>
        <span></span>
        <span style="padding: 0 20px;">20</span>
        <span></span>
    </p>
    <table width="100%" border="0">
        <colgroup width="10%"></colgroup>
        <colgroup width="14%"></colgroup>
        <colgroup width="3%"></colgroup>

        <colgroup width="9%"></colgroup>
        <colgroup width="14%"></colgroup>
        <colgroup width="3%"></colgroup>

        <colgroup width="9%"></colgroup>
        <colgroup width="14%"></colgroup>
        <colgroup width="12%"></colgroup>
        <colgroup width="13%"></colgroup>
        <tr>
            <td colspan="2"><span>张三</span></td>
            <td colspan="5"></td>
            <td colspan="3"><span></span></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td colspan="2"></td>
            <td></td>
            <td colspan="2"></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>12.0</td>
            <td colspan="2"></td>
            <td>22.0</td>
            <td colspan="2"></td>
            <td>22.0</td>
            <td></td>
            <td>22.0</td>
        </tr>
        <tr>
            <td></td>
            <td>12.0</td>
            <td colspan="2"></td>
            <td>22.0</td>
            <td colspan="2"></td>
            <td>22.0</td>
            <td></td>
            <td>22.0</td>
        </tr>
        <tr>
            <td></td>
            <td>12.0</td>
            <td colspan="2"></td>
            <td>22.0</td>
            <td rowspan="5"></td>
            <td></td>
            <td>22.0</td>
            <td></td>
            <td>22.0</td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td rowspan="6"></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="2"></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="2"></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr style="height: 19.2px;">
            <td colspan="10">
                <span style="padding-left: 4.8em;">柒</span>
                <span style="padding-left: 2.4em;">陆</span>
                <span style="padding-left: 2.4em;">伍</span>
                <span style="padding-left: 2.4em;">肆</span>
                <span style="padding-left: 2.4em;">叁</span>
                <span style="padding-left: 2.4em;">贰</span>
                <span style="padding-left: 2.4em; padding-right: 2em;">壹</span>
                <span style="padding-left: 4em;">5000.0</span>
            </td>
        </tr>
        <tr style="height: 19.2px;">
            <td colspan="5"><span style="padding-left: 5em;">200.0</span></td>
            <td colspan="5"><span style="padding-left: 5em;">200.0</span></td>
        </tr>
        <tr style="height: 19.2px;">
            <td colspan="3"><span style="padding-left: 1em;">20.0</span></td>
            <td colspan="2"><span style="padding-left: 1em;">20.0</span></td>
            <td colspan="3"><span style="padding-left: 1em;">20.0</span></td>
            <td colspan="2"><span style="padding-left: 1em;">20.0</span></td>
        </tr>
    </table>
    <div class="form-group" style="margin-top: 0.35em;">
        <div class="row">
            <div class="col-xs-4 col-sm-4"><span>&nbsp;</span><span style="padding-left: 10em;"></span></div>
            <div class="col-xs-4 col-sm-4"><span>&nbsp;</span><span style="padding-left: 5em;"></span></div>
            <div class="col-xs-4 col-sm-4" style="padding: 0;"><span>&nbsp;</span><span
                    style="padding-left: 5em;">张三</span></div>
        </div>
    </div>
</div>
</body>
</html>
