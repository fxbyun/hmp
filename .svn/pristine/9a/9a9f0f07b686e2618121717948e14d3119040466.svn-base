<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/28
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="${ctx}/assets/styles/default.min.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <link rel="stylesheet" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/getPinYin.js" type="js"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/assets/laypage/laypage.js"></script>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });
        });
    </script>
</head>
<body style="background-color: #fff;">
     <div style="margin: 15px;">
              <p>
                  感谢您<br>&nbsp;&nbsp;&nbsp;&nbsp;于<span>12月30日</span>选择<span>桥东李焕生卫生室</span>就诊，
                  不知您是否已经康复，若有任何需要，可到门诊复查或电话咨询<span>xxxxxxxxxxxx</span>

              </p>
              <p class="text-right">
                  <span >祝您生活健康开心！</span>
              </p>
         <div style="text-align: center; margin-top: 50px;">
             <button id="btnClose" class="btn btn-default" style="width: 80px;"><i class="fa fa-times" style="padding-right: 5px;"></i>关闭</button>
         </div>

     </div>
</body>
</html>
