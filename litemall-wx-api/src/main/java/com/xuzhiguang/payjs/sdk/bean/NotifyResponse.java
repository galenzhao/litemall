package com.xuzhiguang.payjs.sdk.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author xuzhiguang
 * @date 2018/12/20
 */
@Data
@ToString(callSuper = true)
public class NotifyResponse extends PayJsResponse {

    /**
     * 金额。单位：分
     */
    private Integer totalFee;

    /**
     * 用户侧订单号
     */
    private String outTradeNo;

    /**
     * payjs端订单号
     */
    private String payjsOrderId;

    /**
     * 微信侧支付订单号
     */
    private String transactionId;

    /**
     * 订单结束时间
     */
    private String timeEnd;

    /**
     * 用户openid
     */
    private String openid;

    /**
     * 用户自定义数据
     */
    private String attach;

    /**
     * 商户号
     */
    private String mchid;

    /**
     * 签名
     */
    private String sign;

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getPayjsOrderId() {
        return payjsOrderId;
    }

    public void setPayjsOrderId(String payjsOrderId) {
        this.payjsOrderId = payjsOrderId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
