<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/8
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>

<footer class="footer">
    <div class="foot-btn">
        <div class="form-group clearfix" style="margin-bottom: 0;">
            <div class="col-xs-6" id="reservation">
                <%--<i class="icon-regis"></i>--%>
                <div><a href="${ctx}/outpatient/clinicIndex"><i class="fa fa-clock-o"></i></a></div>
                <a href="${ctx}/outpatient/clinicIndex">挂号</a>
            </div>
            <%--<div class="col-xs-4">
                <div><i class="fa fa-comments-o"></i></div>
                <a href="${ctx}/consultation/conPatient">咨询</a>
            </div>--%>
            <div class="col-xs-6" id="person">
                <div><a href="${ctx}/personal/perHomepage"><i class="fa fa-user"></i></a></div>
                <a href="${ctx}/personal/perHomepage">个人</a>
            </div>
        </div>
    </div>
</footer>