package org.linlinjava.litemall.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "litemall.wx")
public class WxProperties {

    private String appId;

    private String appSecret;

    private String mchId;

    private String mchKey;

    private String notifyUrl;

    private String keyPath;
    private String callbackAuth;
    private String refCodeAuth;

    private String payjsmchid;
    private String payjskey;
    private String payjsTitle;
    private String payjsBody;
    private String payjsRedirect;
    private String payjsNotify;

    public String getPayjsTitle() {
        return payjsTitle;
    }

    public void setPayjsTitle(String payjsTitle) {
        this.payjsTitle = payjsTitle;
    }

    public String getPayjsBody() {
        return payjsBody;
    }

    public void setPayjsBody(String payjsBody) {
        this.payjsBody = payjsBody;
    }

    public String getPayjsRedirect() {
        return payjsRedirect;
    }

    public void setPayjsRedirect(String payjsRedirect) {
        this.payjsRedirect = payjsRedirect;
    }

    public String getPayjsNotify() {
        return payjsNotify;
    }

    public void setPayjsNotify(String payjsNotify) {
        this.payjsNotify = payjsNotify;
    }

    public String getPayjsmchid() {
        return payjsmchid;
    }

    public void setPayjsmchid(String payjsmchid) {
        this.payjsmchid = payjsmchid;
    }

    public String getPayjskey() {
        return payjskey;
    }

    public void setPayjskey(String payjskey) {
        this.payjskey = payjskey;
    }

    public String getRefCodeAuth() {
        return refCodeAuth;
    }

    public void setRefCodeAuth(String refCodeAuth) {
        this.refCodeAuth = refCodeAuth;
    }

    public String getCallbackAuth() {
        return callbackAuth;
    }

    public void setCallbackAuth(String callbackAuth) {
        this.callbackAuth = callbackAuth;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }
}
