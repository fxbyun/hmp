<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<div id="body" style="height: 100%">
    <section id="main">
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-box">
                        <div class="content-box-header">
                            <i class="icon-dashboard"></i> 平台统计
                        </div>
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>医生总数</th>
                                <th>病历总数</th>
                                <th>患者总数</th>
                                <th>患者绑定微信数</th>
                                <th>卡总数</th>
                                <th>卡激活数</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><span style="color: #003bb3">${doctorTotal}</span></td>
                                    <td><span style="color: #003bb3">${emrTotal}</span></td>
                                    <td><span style="color: #003bb3">${patientTotal}</span></td>
                                    <td><span style="color: #003bb3">${bindTotal}</span></td>
                                    <td><span style="color: #003bb3">${cardTotal}</span></td>
                                    <td><span style="color: #003bb3">${patientTotal}</span></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <footer class="footer">
        <h5 class="transparent-text center">
            <div>©2015 深圳市乔北科技有限责任公司版权所有 <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action">粤ICP备15089657号-2</a></div>
        </h5>
    </footer>
</div>