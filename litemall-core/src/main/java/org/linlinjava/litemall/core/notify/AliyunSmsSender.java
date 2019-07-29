package org.linlinjava.litemall.core.notify;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.notify.config.NotifyProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AliyunSmsSender implements SmsSender {
    private final Log logger = LogFactory.getLog(AliyunSmsSender.class);

    private NotifyProperties.Sms config;

    private IAcsClient client;

    public IAcsClient getClient() {
        if (client == null){
            DefaultProfile profile = DefaultProfile.getProfile("default", config.getAppkey(), config.getSecret());
            client = new DefaultAcsClient(profile);
        }
        return client;
    }

    public AliyunSmsSender(NotifyProperties.Sms config) {
        this.config = config;
        logger.warn("aliyun sms config:\n" + new Gson().toJson(config));
    }

    @Override
    public SmsResult send(String phone, String content) {
        return null;
    }


    @Override
    public SmsResult sendWithTemplate(String phone, int templateId, String[] params){
        return null;
    }

    @Override
    public SmsResult sendWithTemplate(String phone, int templateId, Map<String, String> smsTemplate, String[] params) {

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "default");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", config.getSign());
        request.putQueryParameter("TemplateCode", "SMS_" + templateId);
//        request.putQueryParameter("TemplateParam", "{     \"status\": \"\",      \"remark\": \"\" }");

        Map<String, String> values = new HashMap<String, String>();
        for (int i = 0; i < params.length; i++) {
            values.put("v"+i, params[i]);
        }
//        values.put("value", x);
//        values.put("column", y);
        StrSubstitutor sub = new StrSubstitutor(values, "%(", ")");
//        String result = sub.replace("There's an incorrect value '%(value)' in column # %(column)");
        String result = sub.replace(smsTemplate.get("templateParam"));
        request.putQueryParameter("TemplateParam", result);

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }


        return null;
    }
}
