<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="navbar navbar-fixed-top navbar-inverse top-nav">
	<div class="navbar-inner">
		<div class="container">
			<div class="row-fluid">
				<div class="span12">
					<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<div class="nav-collapse collapse">
						<ul class="nav primary">
							<%--<li class="brand">--%>
							<%--<span class="title"> <i class="icon-cogs"></i> 易佳诊健康管理平台 </span>--%>
							<%--</li>--%>
							<li><a href="${ctx}/index"><b style="font-size: 18px">易佳诊健康管理平台</b></a></li>
							<li><a href="${ctx}/card/list"><i class="icon-tags"></i> 卡管理</a></li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<i class="icon-list"></i> 业务管理
									<b class="caret"></b>
								</a>
								<ul class="dropdown-menu secondary">
									<li><a href="${ctx}/doctor/list"><i class="icon-user"></i> 医生管理</a></li>
									<li><a href="${ctx}/patient/list"><i class="icon-user"></i> 患者管理</a></li>
									<li><a href="${ctx}/rezept/list"><i class="icon-file"></i> 药方管理</a></li>
									<li><a href="${ctx}/rplib/list"><i class="icon-file"></i> 药方库管理</a></li>
									<li><a href="${ctx}/apply/list"><i class="icon-check"></i> 药品换算审核</a></li>
									<li><a href="${ctx}/suggest/list"><i class="icon-check"></i> 诊后建议管理</a></li>
									<li><a href="${ctx}/recharge/list"><i class="icon-check"></i> 充值记录管理</a></li>
                                    <li><a href="${ctx}/fragment/medicine/medicineDataImport"><i class="icon-check"></i>药品导入管理</a>
                                    </li>
								</ul>
							</li>
							<li><a href="${ctx}/notice/list"><i class="icon-envelope"></i> 通知管理</a></li>
							<li><a href="${ctx}/advert/list"><i class="icon-magnet"></i> 广告管理</a></li>
                                <li><a href="${ctx}/adverting/list"><i class="icon-retweet"></i> 广告机管理</a></li>
						</ul>
						<ul class="nav pull-right primary">
							<li class="dropdown profile">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<img class="menu-avatar" src="${ctx}/assets/styles/images/profile-thumb.png" />
									<shiro:user><shiro:principal property="name"/></shiro:user>
									<b class="caret"></b>
								</a>
								<ul class="dropdown-menu secondary ">
									<li><a href="${ctx}/pwd"><i class="icon-lock"></i> 修改密码</a></li>
									<li class="divider"></li>
									<li><a href="javascript:;" id="logout"><i class="icon-off"></i> 退出</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>