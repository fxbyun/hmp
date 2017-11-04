<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/19
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>新增进货单</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/validity.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jquery.form/jquery.form.js" type="js"/>
    <script>
        $(function () {
            $("#iaiDetail").load("${ctx}/adv/fragment/validityManage/showIaiDetails?iaiIntoId=" +${iaiInto.id});
        })
    </script>
</head>
<body>
<%@ include file="/WEB-INF/layouts/advheader.jsp" %>
<div class="container">
    <ul class="navigation">
        <li><a href="${ctx}/adv/validityManage" class="btn btn-default">库存管理</a></li>
        <li><a href="${ctx}/adv/validityManage/lossRecord" class="btn btn-default">损耗记录</a></li>
        <li class="active"><a href="${ctx}/adv/validityManage/entryRecord" class="btn btn-default">入货库记录</a></li>
    </ul>
    <div class="validityManage saveValList" id="RK_HTML" style="margin-top: 20px;">
        <div class="val-list-h4"><h4 id="html_title">新增入库单</h4></div>
        <form id="saveIaiInto" method="post">
            <div class="clearfix" style="margin-top: 15px;">
                <div class="pull-left" style="width: 50%; padding-right: 5%;">
                    <div class="form-group text-right">
                        <span>进货单单号：</span>
                        <input type="text" id="iaiIntoId" name="id" value="${iaiInto.id}" style="display: none"/>
                        <input type="text" name="goodsNo" class="form-control" value="${iaiInto.goodsNo}"
                               style="display: inline-block; width: 75%;">
                        <i class="icon-val-order" title="进货单号" onclick="orderDetails()"></i>
                    </div>
                    <div class="form-group text-right">
                        <span>供应商：</span>
                        <select type="text" name="supplierId" class="form-control"
                                style="display: inline-block; width: 75%;">
                            <c:if test="${not empty supplierList}">
                                <c:forEach items="${supplierList}" var="supplier">
                                    <c:if test="${supplier.id==iaiInto.supplier.id}">
                                        <option value="${supplier.id}" selected="selected">${supplier.name}</option>
                                    </c:if>

                                    <c:if test="${supplier.id!=iaiInto.supplier.id}">
                                        <option value="${supplier.id}">${supplier.name}</option>
                                    </c:if>

                                </c:forEach>
                            </c:if>
                        </select>
                        <i class="icon-val-per" title="编辑供应商" onclick="supplierManage(this)"></i>
                    </div>
                </div>
                <div class="pull-right" style="width: 50%; padding-right: 5%;">
                    <div class="form-group text-right">
                        <span>进货日期：</span>
                        <input type="text" name="createDate" class="form-control form_day"
                               style="display: inline-block; width: 80%;"
                               value="<fmt:formatDate value="${iaiInto.createDate}" type="date" pattern="yyyy/MM/dd"/>"
                               readonly>
                    </div>
                    <div class="form-group text-right">
                        <span>填表人：</span>
                        <input name="whoCreate" value="${doctor.name}" type="text" class="form-control"
                               style="display: inline-block; width: 80%;">
                    </div>
                </div>
            </div>
        </form>
        <div class="val-list-medicine">
            <div class="clearfix">
                <input type="text" id="medNameInput" class="form-control"
                       style="display: inline-block; width: 15%;"
                       placeholder="输入您要查询的药品名称">
                <button class="btn btn-defalut" type="button" onclick="searchMed(${iaiInto.id})">搜索</button>
                <a href="javascript:" onclick="addMedicine()" class="btn btn-success">新增药品</a>
                <div class="list-medicine clearfix">
                    <a class="prev fa fa-chevron-left" onclick="scrollPageTag('left',${iaiInto.id})"></a>
                    <div class="list-tab" id="medListDiv">
                        <div class="scroll_demo" onmousewheel="showKey()">
                            <li>
                                <a href="javascript:" onclick="saveMedicine()">
                                    <span class="tag tag-i span-tag">5%葡萄糖液</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(1234,22)">
                                    <span class="tag tag-i span-tag">人参</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(1047,22)">
                                    <span class="tag tag-i span-tag">半夏</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(2702,22)">
                                    <span class="tag tag-i span-tag">女贞</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(2703,22)">
                                    <span class="tag tag-i span-tag">旱莲</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(2704,22)">
                                    <span class="tag tag-i span-tag">败酱</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(1841,22)">
                                    <span class="tag tag-i span-tag">氢化可的松</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(1915,22)">
                                    <span class="tag tag-i span-tag">双八面体蒙脱石（思密达）</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(1942,22)">
                                    <span class="tag tag-i span-tag">双歧三联活菌片（金双歧）</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(959,22)">
                                    <span class="tag tag-i span-tag">*九香虫</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(941,22)">
                                    <span class="tag tag-i span-tag">*合欢花</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(2354,22)">
                                    <span class="tag tag-i span-tag"> 阿昔洛韦膏</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(985,22)">
                                    <span class="tag tag-i span-tag">*三丫苦</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(1838,22)">
                                    <span class="tag tag-i span-tag">盐酸氨溴索口服液</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(677,22)">
                                    <span class="tag tag-i span-tag">头孢拉定胶囊</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(3200,22)">
                                    <span class="tag tag-i span-tag">必咳平</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(3153,22)">
                                    <span class="tag tag-i span-tag">焦三仙</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(3519,22)">
                                    <span class="tag tag-i span-tag">射干麻黄汤</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(1726,22)">
                                    <span class="tag tag-i span-tag">普伐他汀胶囊</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:" onclick="saveMedicine(4829,22)">
                                    <span class="tag tag-i span-tag">小儿抗病毒 口服 液</span>
                                </a>
                            </li>
                        </div>
                    </div>
                    <a class="next fa fa-chevron-right" onclick="scrollPageTag('right',${iaiInto.id})"></a>
                </div>
            </div>
        </div>

        <div id="iaiDetail">
            <table class="rp-table" width="100%">
                <colgroup width="5%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="11%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="7%"></colgroup>
                <colgroup width="7%"></colgroup>
                <colgroup width="8%"></colgroup>
                <colgroup width="5%"></colgroup>
                <colgroup width="6%"></colgroup>
                <colgroup width="6%"></colgroup>
                <colgroup width="8%"></colgroup>
                <colgroup width="7%"></colgroup>
                <thead>


                <%----%>
                <tr style="background-color: #eeeeee;">
                    <th>序号</th>
                    <th>药品名称</th>
                    <th>药厂</th>
                    <th>条码</th>
                    <th>有效期</th>
                    <th>进货价(元)</th>
                    <th>零售价(元)</th>
                    <th>产品数量</th>
                    <th>预警线</th>
                    <th>规格</th>
                    <th>统计单位</th>
                    <th>成本总额</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="tb_page">
                <c:forEach items="${iaiDetails}" var="detail" varStatus="status">
                    <tr onclick="saveMedicine(${detail.medicine.medicine.id},${detail.iaiInto.id},${detail.id},'saveValListPage')">
                        <td>${status.index}</td>
                        <td>${detail.medicine.name}</td>
                        <td><span class="val-text">${companyMap[detail.id]}</span></td>
                        <td>${detail.barCode}</td>
                        <td><fmt:formatDate value="${detail.validityDate}" pattern="yyyy/MM/dd"/></td>
                        <td>${detail.bayingPrice}</td>
                        <td>${detail.medicine.price}</td>
                        <td>${detail.totalNumber}</td>
                        <td>${detail.warnLine}</td>
                        <td>${detail.medicine.standard}</td>
                        <td>${medicineUnits[detail.medicine.unit]}</td>
                        <td>${detail.bayingPrice*detail.totalNumber}</td>
                        <td><a href="javascript:" onclick="deleteValListTr(this,${detail.id})">删除</a></td>
                    </tr>
                </c:forEach>
                <tr onclick="saveMedicine()">
                    <td>1</td>
                    <td>感冒灵</td>
                    <td><span class="val-text">太和药业</span></td>
                    <td>12313</td>
                    <td>2016-12-12</td>
                    <td>88.0</td>
                    <td>12.0</td>
                    <td>88</td>
                    <td>20</td>
                    <td>10g</td>
                    <td>盒</td>
                    <td>500.0</td>
                    <td><a href="javascript:" onclick="deleteValListTr(this,${detail.id})">删除</a></td>
                </tr>

                </tbody>

            </table>
        </div>
    </div>
    <div class="text-center" style="margin-bottom: 20px;">
        <button type="button" onclick="fn_LoadRpList(${page.number - 1})" class="btn btn-default">上一页</button>
        <button type="button" onclick="fn_LoadRpList(${page.number + 1},${page.totalPages})" class="btn btn-default">
            下一页
        </button>
        <button id="btnSaveIaiInto" type="button" class="btn btn-success" style="width: 80px;">保存</button>
        <button type="button" class="btn btn-success">打印入库单</button>
        <button type="button" class="btn btn-default" style="width: 80px;" onclick="javascript:history.go(-1);">
            返回
        </button>
    </div>
</div>
<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>
<script>
    $(function () {
        $("#btnSaveIaiInto").click(function () {
            $("#saveIaiInto").submit();
        });
    })

</script>

</body>
</html>
