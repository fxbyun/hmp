<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>历史病历</title>
    <script>
        $(function () {
            $("#nav-manage").addClass("active");

            var colorCon = "";
            if (${isExt}) {
                colorCon = "#4c89ed";
            } else {
                colorCon = "#218e3f";
            }
            var diagnosis_months = [];
            var diagnosis_datas = [];
            var diagnosisChart = new Highcharts.Chart({
                chart: {
                    renderTo: 'admiss-datas',
                    type: 'column',
                    margin: [50, 50, 100, 80]
                },
                title: {
                    text: false
                },
                xAxis: {
                    categories: diagnosis_months,
                    labels: {
                        rotation: 0,
                        align: 'center',
                        style: {
                            fontSize: '16px',
                            fontFamily: 'Verdana, sans-serif'
                        }
                    }
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '单位 (次)',
                        style: {
                            fontSize: '16px'
                        }
                    }
                },
                legend: {
                    enabled: false
                },
                tooltip: {
                    pointFormat: '共有: <b>{point.y:.1f} 人</b>'
                },
                series: [{
                    name: 'Population',
                    data: diagnosis_datas,
                    color: colorCon,
                    dataLabels: {
                        enabled: true,
                        rotation: 0,
                        color: '#666',
                        align: 'center',
                        x: 0,
                        y: 0,
                        style: {
                            fontSize: '16px',
                            fontFamily: 'Verdana, sans-serif',
                            textShadow: '0 0 0px black'
                        }
                    }
                }]
            });
            /*接诊数数据来源*/
            /*$.getJSON("/diagnosisStatistics", function (result) {
                $.each(result.data, function (i, n) {
                    diagnosis_months.push(n.name);
                    diagnosis_datas.push(n.value);
                });

                diagnosisChart.series[0].setData(diagnosis_datas);
                diagnosisChart.xAxis[0].setCategories(diagnosis_months);
            });*/
            builderDate(diagnosisChart,"${ctx}/diagnosisStatistics","");
            ////////////////////////////////////////////////////////////////////////////////////
            var disease_names = [];
            var disease_datas = [];
            var diseaseChart = new Highcharts.Chart({
                chart: {
                    renderTo: 'illness-datas',
                    type: 'column',
                    margin: [50, 50, 100, 80]
                },
                title: {
                    text: false
                },
                xAxis: {
                    categories: disease_names,
                    labels: {
                        rotation: -45,
                        align: 'right',
                        style: {
                            fontSize: '14px',
                            fontFamily: 'Verdana, sans-serif',
                            fontWeight: '600'
                        }
                    }
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '单位 (人)',
                        style: {
                            fontSize: '18px'
                        }
                    }
                },
                legend: {
                    enabled: false
                },
                tooltip: {
                    pointFormat: '共有: <b>{point.y:.1f} 人</b>'
                },
                series: [{
                    name: 'Population',
                    data: disease_datas,
                    color: colorCon,
                    dataLabels: {
                        enabled: true,
                        rotation: 0,
                        color: '#666',
                        align: 'center',
                        x: 0,
                        y: 0,
                        style: {
                            fontSize: '16px',
                            fontFamily: 'Verdana, sans-serif',
                            textShadow: '0 0 0px black'
                        }
                    }
                }]
            });
            /*$.getJSON("/diseaseStatistics", function (result) {
                $.each(result.data, function (i, n) {
                    disease_names.push(n.name);
                    disease_datas.push(n.value);
                });
                diseaseChart.series[0].setData(disease_datas);
                diseaseChart.xAxis[0].setCategories(disease_names);
            });*/
            builderDate(diseaseChart,"${ctx}/diseaseStatistics","");
            $('input[name="month1"]').change(function () {

                var month = $('input[name="month1"]:checked').val();
                disease_names = [];
                disease_datas = [];
                /*$.getJSON("/diseaseStatistics?month=" + month, function (result) {
                    $.each(result.data, function (i, n) {
                        disease_names.push(n.name);
                        disease_datas.push(n.value);
                    });
                    diseaseChart.series[0].setData(disease_datas);
                    diseaseChart.xAxis[0].setCategories(disease_names);
                });*/
                var doctorId = $("#BZ").val()
                builderDate(diseaseChart,"${ctx}/diseaseStatistics",[{"key":"doctorId","val":doctorId},{"key":"month","val":month}]);
            });
            /////////////////////////////////////////////////////////////////////////////
            var western_names = [];
            var western_datas = [];
            var westernChart = new Highcharts.Chart({
                chart: {
                    renderTo: 'western-datas',
                    type: 'column'
                },
                title: {
                    text: false
                },
                subtitle: {
                    text: false
                },
                xAxis: {
                    categories: western_names,
                    labels: {
                        rotation: -45,
                        align: 'right',
                        style: {
                            fontSize: '14px',
                            fontFamily: 'Verdana, sans-serif',
                            fontWeight: '600'
                        }
                    }
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '',
                        style: {
                            fontSize: '18px'
                        }
                    }
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    name: '西药累计',
                    data: western_datas,
                    color: colorCon
                }]
            });
            /*$.getJSON("/westernStatistics", function (result) {
                $.each(result.data, function (i, n) {
                    western_names.push(n.name);
                    western_datas.push(n.qty);
                });
                westernChart.series[0].setData(western_datas);
                westernChart.xAxis[0].setCategories(western_names);
            });*/
            builderDate(westernChart,"/westernStatistics","","qty");





            $('input[name="month2"]').change(function () {
                var month = $('input[name="month2"]:checked').val();
                /*western_names = [];
                western_datas = [];
                $.getJSON("/westernStatistics?month=" + month, function (result) {
                    $.each(result.data, function (i, n) {
                        western_names.push(n.name);
                        western_datas.push(n.qty);
                    });
                    westernChart.series[0].setData(western_datas);
                    westernChart.xAxis[0].setCategories(western_names);
                });*/
                var doctorId = $("#YYSL_Western").val()
                builderDate(westernChart,"${ctx}/westernStatistics",[{"key":"doctorId","val":doctorId},{"key":"month","val":month}],"qty");
            });
            /////////////////////////////////////////////////////////////////////////////////////////////
            var chinese_names = [];
            var chinese_datas = [];
            var chineseChart = new Highcharts.Chart({
                chart: {
                    renderTo: 'chinese-datas',
                    type: 'column'
                },
                title: {
                    text: false
                },
                subtitle: {
                    text: false
                },
                xAxis: {
                    categories: chinese_names,
                    labels: {
                        style: {
                            fontSize: '16px'
                        }
                    }
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '单位(克)',
                        style: {
                            fontSize: '18px'
                        }
                    }
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    name: '中药累计',
                    data: chinese_datas,
                    color: colorCon
                }]
            });
            /*$.getJSON("/chineseStatistics", function (result) {
                $.each(result.data, function (i, n) {
                    chinese_names.push(n.name);
                    chinese_datas.push(n.qty);
                });
                chineseChart.series[0].setData(chinese_datas);
                chineseChart.xAxis[0].setCategories(chinese_names);
            });*/
            builderDate(chineseChart,"/chineseStatistics","","qty")


            $('input[name="month3"]').change(function () {
                var month = $('input[name="month3"]:checked').val();
                /*chinese_names = [];
                chinese_datas = [];
                $.getJSON("/chineseStatistics?month=" + month, function (result) {
                    $.each(result.data, function (i, n) {
                        chinese_names.push(n.name);
                        chinese_datas.push(n.qty);
                    });
                    chineseChart.series[0].setData(chinese_datas);
                    chineseChart.xAxis[0].setCategories(chinese_names);
                });*/
                var doctorId = $("#YYSL_China").val()
                builderDate(chineseChart,"${ctx}/chineseStatistics",[{"key":"doctorId","val":doctorId},{"key":"month","val":month}],"qty");
            });

            /**/
            $(".subDoctor").change(function () {

                var doctorId = $(this).val();
                var selectId = $(this).attr("id");
                var char;
                var url;
                var json=[];
                var flag;
                switch (selectId){
                    case 'JZS':
                        url = "${ctx}/diagnosisStatistics";
                        char=diagnosisChart;
                        json.push({"key":"doctorId","val":doctorId});
                        flag="";
                        break;
                    case 'BZ':
                        url = "${ctx}/diseaseStatistics";
                        char = diseaseChart;
                        json.push({"key":"doctorId","val":doctorId});
                        flag="";
                        break;
                    case 'YYSL_Western':
                        url = "${ctx}/westernStatistics";
                        char = westernChart;
                        json.push({"key":"doctorId","val":doctorId});
                        flag="qty";
                        break;

                    case 'YYSL_China':
                        url = "${ctx}/chineseStatistics";
                        char = chineseChart;
                        json.push({"key":"doctorId","val":doctorId});
                        flag="qty";
                        break;

                    default:
                        alert("前端出错");
                        break;
                }

                builderDate(char,url,json,flag);
            });



        });
/*-----------------------看什么看，没看过分割线吗！--------------------------*/
        /**
         * 图表数据生成
         * char(图标模型)、
         * url（访问路径）、
         * Json(请求参数key-val)、
         * getData(获取哪些数据，因为有些是n.value或者n.qty,默认是value)
         * @param char
         * @param url
         * @param Json
         * @param getData
         */
        function builderDate(char,url,Json,getData){
//
            var newUrl;
            if(Json==''||Json==undefined){
                newUrl=url;
            }else {

                $.each(Json,function (i,p) {
                    if(i==0){
                        newUrl=url+"?"+p.key+"={0}".format(p.val);
                    }else{
                        newUrl+="&"+p.key+"={0}".format(p.val);
                    }
                    /*if(i==0){
                        if(p.val!='all'){
                            newUrl=url+"?"+p.key+"={0}".format(p.val);
                        }else {
                            newUrl=url;
                        }

                    }else{
                        newUrl+="&"+p.key+"={0}".format(p.val);
                    }*/
                })
            }
            if(getData==''||getData==undefined){
                getData="value";
            }
            $.getJSON(newUrl,function (result) {
                var name=[];
                var data=[];
                $.each(result.data, function (i, n) {

                    name.push(n.name);
                    if(getData=='value'){
                        data.push(n.value);
                    }else {
                        data.push(n.qty);
                    }

                });
                char.series[0].setData(data);
                char.xAxis[0].setCategories(name);
            })

        }

    </script>
</head>
<body>
<div class="manage">
    <div class="container">
        <ul class="navigation">
            <li class="active"><a href="${ctx}/manage" class="btn btn-default">用药分析</a></li>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/message" class="btn btn-default" onclick="isDebug()">短信发送</a></li>
            </c:if>
            <li><a href="${ctx}/rp" class="btn btn-default">我的药方</a></li>
            <li><a href="${ctx}/rplib" class="btn btn-default">药方库</a></li>
            <li><a href="${ctx}/config/symptom" class="btn btn-default">数据整理</a></li>
            <li><a href="${ctx}/appointment" class="btn btn-default">预约挂号</a></li>
            <%--<c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/appointment" class="btn btn-default">预约挂号</a></li>
            </c:if>--%>
            <%--是否开始有效期，记得注释--%>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/validityManage" class="btn btn-default">有效期管理</a></li>
            </c:if>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/financial" class="btn btn-default">财务统计</a></li>
            </c:if>

            <div class="analysis">
                <p>${currentMonth}月累计医治病人：${opCountForMonth}人</p>

                <p>历史总计医治病人：${opCount}人</p>

                <p>积分总值：${doctor.integral} 分</p>
            </div>
        </ul>

        <div class="analysis-data">
            <div class="data-head">
                <h3>
                    <img src="${ctx}/assets/styles/images/d01.png" alt="img">
                    接诊数统计
                </h3>
                <div style="float: right;">
                    <select id="JZS" class="form-control subDoctor" style="width: 90px; display: inline-block;">
                        <option value="0">全部</option>
                        <c:forEach items="${subDoctorList}" var="sub">
                            <option value="${sub.id}">${sub.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div id="admiss-datas" style="min-width:800px;height:400px"></div>
        </div>
        <div class="analysis-data">
            <div class="data-head up-dpwn-bt clearfix">
                <h3>
                    <img src="${ctx}/assets/styles/images/d02.png" alt="img">
                    病症分析
                </h3>
                <div style="float: right;">
                    <select id="BZ" class="form-control subDoctor" style="width: 90px; display: inline-block;">
                        <option value="0">全部</option>
                        <c:forEach items="${subDoctorList}" var="sub">
                            <option value="${sub.id}">${sub.name}</option>
                        </c:forEach>
                    </select>
                    <a href="${ctx}/admissDetail" class="btn btn-info pull-right"><i
                            class="glyphicon glyphicon-align-justify data-i"></i>详细列表</a>
                </div>

            </div>

            <div id="illness-datas" style="min-width:800px;height:400px"></div>
            <div class="panel-body text-center">
                <div class="btn-group" data-toggle="buttons">
                    <c:forEach var="m" varStatus="status" items="${months}">
                        <label class="btn btn-success <c:if test='${month==m}'>active</c:if>">
                            <input type="radio" name="month1" value="<fmt:formatDate value='${m}' pattern='yyyy/MM'/>"
                                   autocomplete="off" <c:if test='${month==m}'>checked</c:if>>
                            <fmt:formatDate value='${m}' pattern='yy年MMM'/>
                        </label>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="analysis-data">
            <div class="data-head up-dpwn-bt clearfix">
                <h3>
                    <img src="${ctx}/assets/styles/images/d03.png" alt="img">
                    用药数量分析-西药房
                </h3>
                <div style="float: right;">
                    <select id="YYSL_Western" class="form-control subDoctor"
                            style="width: 90px; display: inline-block;">
                        <option value="0">全部</option>
                        <c:forEach items="${subDoctorList}" var="sub">
                            <option value="${sub.id}">${sub.name}</option>
                        </c:forEach>
                    </select>
                    <a href="${ctx}/westernDetail" class="btn btn-info pull-right"><i
                            class="glyphicon glyphicon-align-justify data-i"></i>详细列表</a>
                </div>
            </div>
            <div id="western-datas" style="min-width:800px;height:400px"></div>
            <div class="panel-body text-center">
                <div class="btn-group" data-toggle="buttons">
                    <c:forEach var="m" varStatus="status" items="${months}">
                        <label class="btn btn-success <c:if test='${month==m}'>active</c:if>">
                            <input type="radio" name="month2" value="<fmt:formatDate value='${m}' pattern='yyyy/MM'/>"
                                   autocomplete="off" <c:if test='${month==m}'>checked</c:if>>
                            <fmt:formatDate value='${m}' pattern='yy年MMM'/>
                        </label>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="analysis-data">
            <div class="data-head up-dpwn-bt clearfix">
                <h3>
                    <img src="${ctx}/assets/styles/images/d03.png" alt="img">
                    用药数量分析-中药房
                </h3>
                <div style="float: right;">
                    <select id="YYSL_China" class="form-control subDoctor" style="width: 90px; display: inline-block;">
                        <option value="0">全部</option>
                        <c:forEach items="${subDoctorList}" var="sub">
                            <option value="${sub.id}">${sub.name}</option>
                        </c:forEach>
                    </select>
                    <a href="${ctx}/chineseDetail" class="btn btn-info pull-right"><i
                            class="glyphicon glyphicon-align-justify data-i"></i>详细列表</a>
                </div>

            </div>
            <div id="chinese-datas" style="min-width:800px;height:400px"></div>
            <div class="panel-body text-center">
                <div class="btn-group" data-toggle="buttons">
                    <c:forEach var="m" varStatus="status" items="${months}">
                        <label class="btn btn-success <c:if test='${month==m}'>active</c:if>">
                            <input type="radio" name="month3" value="<fmt:formatDate value='${m}' pattern='yyyy/MM'/>"
                                   autocomplete="off" <c:if test='${month==m}'>checked</c:if>>
                            <fmt:formatDate value='${m}' pattern='yy年MMM'/>
                        </label>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>