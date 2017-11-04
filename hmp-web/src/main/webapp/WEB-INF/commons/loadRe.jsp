<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: teemoer@cntv.cn
  Date: 2016/7/5 0005
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<hmp:HmpLoadFile ctx="${ctx}" url="/assets/jquery/jquery-1.11.2.min.js" type="js"/>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/layer/layer.js" type="js"/>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/toastr/toastr.css" type="css"/>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/toastr/toastr.js" type="js"/>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/toastr/toastr.ext.js" type="js"/>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/select2/select2.css" type="css"/>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/select2/select2.js" type="js"/>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/select2/select2.ext.js" type="js"/>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/base.js" type="js"/>