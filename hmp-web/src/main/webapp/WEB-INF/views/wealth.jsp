<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>门诊管理</title>
    <script>
        $(function () {
            $("#nav-manage").addClass("active");
        });
    </script>
</head>
<body>
<div class="manage wealth">
    <div class="container">
        <ul class="navigation">
            <li><a href="${ctx}/manage">用药分析</a></li>
            <li><a href="${ctx}/infro">信息设置</a></li>
            <li class="active"><a href="${ctx}/wealth">我的财富</a></li>
            <li><a href="${ctx}/rezept">药方管理</a></li>
        </ul>
        <div class="integration">
            <div class="row text-center">
                <div class="col-md-6 col-sm-6">
                    <p>本月诊疗数：<span>600</span> 次</p>

                    <p>本 月 积 分：<span>900</span> 分</p>
                </div>
                <div class="col-md-6 col-sm-6">
                    <p>总计诊疗数：<span>5200</span> 次</p>

                    <p>积 分 总 值：<span>9654</span> 分</p>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="balance text-center">
        <h4>用户余额:<span>1000</span>RMB</h4>

        <p>累计提现:<span>3650</span>RMB</p>
    </div>
    <div class="withdraw">
        <div class="withdraw-content">
            申请提现
            <a href="javascript:void(0)"><img src="css/images/s01.png" alt="img">支付宝</a>
            <a href="javascript:void(0)"><img src="css/images/s02.png" alt="img">微信</a>
            <a href="javascript:void(0)"><img src="css/images/s03.png" alt="img"></a>

            <div class="bank-card">
                <img src="css/images/s04.png" alt="img">
                建设银行储蓄卡（9075）
                <a href="#"><i class="fa fa-plus"></i></a>
            </div>
        </div>
        <div class="manage">
            <div class="container">
                <ul class="navigation">
                    <li class="active"><a href="javascript:void(0)">财富消息</a></li>
                </ul>
                <p>为表感谢，患者 XXX 给您送来 XX 元打赏金，已成功存入财富余额！<span class="pull-right">2015/7/5 10:10</span></p>

                <p>为表感谢，患者 XXX 给您送来 XX 元打赏金，已成功存入财富余额！<span class="pull-right">2015/7/5 10:10</span></p>

                <p>为表感谢，患者 XXX 给您送来 XX 元打赏金，已成功存入财富余额！<span class="pull-right">2015/7/5 10:10</span></p>

                <p>为表感谢，患者 XXX 给您送来 XX 元打赏金，已成功存入财富余额！<span class="pull-right">2015/7/5 10:10</span></p>

                <p>为表感谢，患者 XXX 给您送来 XX 元打赏金，已成功存入财富余额！<span class="pull-right">2015/7/5 10:10</span></p>

                <p>为表感谢，患者 XXX 给您送来 XX 元打赏金，已成功存入财富余额！<span class="pull-right">2015/7/5 10:10</span></p>

                <p>为表感谢，患者 XXX 给您送来 XX 元打赏金，已成功存入财富余额！<span class="pull-right">2015/7/5 10:10</span></p>

            </div>
        </div>
    </div>
</div>
</body>
</html>