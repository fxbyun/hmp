package com.qiaobei.hmp.web;

import com.google.zxing.WriterException;
import com.qiaobei.hmp.modules.entity.MsgMoney;
import com.qiaobei.hmp.modules.entity.MsgRechargeDetail;
import com.qiaobei.hmp.modules.entity.PayType;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.MsgMoneyService;
import com.qiaobei.hmp.service.MsgRechargeDetailService;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.SMSUtil;
import com.qiaobei.hmp.wxpay.WxNotifyImpl;
import com.qiaobei.hmp.wxpay.payService.PayService;
import com.qiaobei.hmp.wxpay.utils.PayUtil;
import com.qiaobei.hmp.wxpay.utils.QrCodeUtil;
import com.qiaobei.hmp.wxpay.utils.WxConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA 15.0
 * User ZW_Teemoer
 * Date 2016/7/11 0011
 * Time 13:31
 */
@Controller
public class WxPayController {
    @Resource
    MsgRechargeDetailService msgRechargeDetailService;
    @Resource
    private MsgMoneyService msgMoneyService;

    /**
     * 微信回调地址
     */
    @RequestMapping(value = "/wx/callBack/")
    @ResponseBody
    public Object weixinNotify(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        System.out.println("微信回调开始！！！！");
        BufferedOutputStream out;
        String resXml;
        resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        WxNotifyImpl wxNotify;
        String key;
        MsgRechargeDetail msgRechargeDetail;
        try {
            wxNotify = new WxNotifyImpl(request);
            Map<String, Object> resmap = wxNotify.getMap();//拿到所有返回结果的map
            if (null == resmap || resmap.size() <= 0 || wxNotify.getOut_trade_no() == null) {
                return "fail";
            }


            //根据 商户订单号 获取订单对象
            msgRechargeDetail = msgRechargeDetailService.getMsgRecByRechargeNum(wxNotify.getOut_trade_no());//获取订单
            if (msgRechargeDetail == null) {
                return "fail";
            }

            if (msgRechargeDetail.getHavePay() == PayType.PAY) {
                out = new BufferedOutputStream(response.getOutputStream());
                out.write(resXml.getBytes());
                out.flush();
                out.close();
                return resXml;
            }


            key = WxConfig.KEY;
            boolean sign = wxNotify.verfySign(resmap, key);//验证返回结果的签名是否正确
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            if (sign) {//返回的消息验证正确
                if (wxNotify.verfyPaySeccuss()) {//支付成功
                    //设置 session中的 payMap为空
                    session.setAttribute("payMap", null);
                    session.setAttribute("payNo", null);
                    msgRechargeDetail.setHavePay(PayType.PAY);
                    msgRechargeDetail.setUpdateDate(new Date());
                    msgRechargeDetail.setWxInfoBack(resmap.toString());
                    MsgMoney msgMoney = msgMoneyService.getByDoctor(msgRechargeDetail.getDoctor());
                    msgMoney.setDeposit(msgMoney.getDeposit() + msgRechargeDetail.getAddMoney());
                    msgMoneyService.updateOrSave(msgMoney);
                    //System.out.println("支付完毕");
                    //System.out.println("微信支付订单号:" + (String) resmap.get("transaction_id"));
                    //System.out.println("支付时间:" + sdf.parse((String) resmap.get("time_end")));
                    sendMsgCallSuccess(msgMoney, msgRechargeDetail);
                    //charge.setTransactionNo((String) resmap.get("transaction_id"));//微信支付订单号
                    //charge.setPaidTime(sdf.parse((String) resmap.get("time_end")));//支付时间
                    //charge.setPaid(ChargePayType.PAY);//已支付

                } else {
                    //charge = checkd.WX_QR_T(charge.getId());
                    //charge.setReMark("接口通知支付失败，进行重新查询订单...");
                    System.out.println("接口通知支付失败，进行重新查询订单...");

                }
            } else {
                //charge = checkd.WX_QR_T(charge.getId());
                //charge.setReMark("回调通知签名错误");
                System.out.println("回调通知签名错误");

            }
            //charge.setUpdateTime(new Date());//更新时间
            //chargeService.update(charge);//对订单表进行更新

            //通知微信 回调成功
            out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
        return resXml;
    }

    /**
     * 微信回调成功以后 给医生发送充值成功提示
     */
    private void sendMsgCallSuccess(MsgMoney msgMoney, MsgRechargeDetail msgRechargeDetail) {
        String msg = "尊敬的" + msgMoney.getDoctor().getName() + "医生，您已经于"
                + new Date().toLocaleString() + "成功充值" +
                msgRechargeDetail.getAddMoney() + "元，现可用余额为：" + msgMoney.getDeposit() + "。";

        //发送短信
        try {
            SMSUtil.sendSMS(SMSUtil.CUSTOMIZE_ALONE_TEMLATE, msgMoney.getDoctor().getMobile(), msg);
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println("充值提示短信发送失败, 错误原因为:" + e.getMessage());
        }
    }

    /**
     * 获得二维码
     *
     * @param response
     * @param httpSession
     * @return
     */
    @RequestMapping("/pub/getWzewm")
    public String getEwm(HttpServletResponse response, HttpSession httpSession) {

        ServletOutputStream out = null;

        Map<String, Object> payMap = (Map<String, Object>) httpSession.getAttribute("payMap");
        String ewmUrl = (String) payMap.get("code_url");
        System.out.println("ewmUrl:" + ewmUrl);
        try {
            //获得  response 的输出流  用来向客户端输出图片
            out = response.getOutputStream();

            ImageIO.write(QrCodeUtil.getBufImg(ewmUrl, 500, 500), "png", out);

            out.flush();
            out.close();
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
        //new File("d:" + sdf.format(new Date()) + ".png"));//生成二维码到D盘
        return null;
    }


    /**
     * 创建微信订单  并且把微信返回的信息放到session
     */
    @RequestMapping("/pub/wx/payMoney")
    public String payMonery(HttpSession httpSession, @RequestParam("monery") Long monery, Model model) {
        String payNo = PayUtil.getRandomString(30);

        Map<String, Object> payMap = PayService.wxpay(monery, payNo);
        //二维码 未获取到
        if (payMap.get("code_url") == null) {
            model.addAttribute("error", "充值失败,请重新尝试!");
            return "/wx/error";
        }

        MsgMoney msgMoney = msgMoneyService.getByDoctor(SecuritySupport.getDoctor());
        if (msgMoney == null) {
            msgMoney = new MsgMoney();
            msgMoney.setDeposit(0D);
            msgMoney.setDoctor(SecuritySupport.getDoctor());
            msgMoneyService.updateOrSave(msgMoney);
        }

        MsgRechargeDetail msgRechargeDetail = new MsgRechargeDetail();
        //设置订单为 未付款
        msgRechargeDetail.setHavePay(PayType.NOT_PAY);

        msgRechargeDetail.setDoctor(SecuritySupport.getDoctor());
        msgRechargeDetail.setCreateDate(new Date());
        msgRechargeDetail.setRechargeNum(payNo);
        msgRechargeDetail.setRechargeWay("微信");
        msgRechargeDetail.setMoney(msgMoney.getDeposit());
        msgRechargeDetail.setAfterMoney(msgMoney.getDeposit() + monery);
        msgRechargeDetail.setAddMoney(Double.valueOf(monery));
        msgRechargeDetailService.saveOrUpdate(msgRechargeDetail);


        httpSession.setAttribute("payMap", payMap);
        httpSession.setAttribute("payNo", payNo);
        return "/wx/showEwm";
    }

    @RequestMapping("/wx/isPay")
    @ResponseBody
    public Result isPay(HttpSession httpSession, @RequestParam("payNo") String keyNo) {

        String payNo = (String) httpSession.getAttribute("payNo");
        if (payNo != null) {
            if (!"".equals(payNo)) {
                MsgRechargeDetail msgRechargeDetail = msgRechargeDetailService.getMsgRecByRechargeNum(payNo);
                if (msgRechargeDetail != null) {
                    if (msgRechargeDetail.getHavePay() == PayType.PAY) {
                        //如果数据库 返回已经支付 通知前台  支付完毕
                        return Result.ok();
                    }
                }
            }
        }
        return Result.fail();
    }

}
