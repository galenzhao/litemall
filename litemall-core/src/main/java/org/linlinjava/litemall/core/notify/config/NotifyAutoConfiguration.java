package org.linlinjava.litemall.core.notify.config;

import com.github.qcloudsms.SmsSingleSender;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.notify.AliyunSmsSender;
import org.linlinjava.litemall.core.notify.NotifyService;
import org.linlinjava.litemall.core.notify.TencentSmsSender;
import org.linlinjava.litemall.core.notify.WxTemplateSender;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@EnableConfigurationProperties(NotifyProperties.class)
public class NotifyAutoConfiguration {
    private final Log logger = LogFactory.getLog(NotifyAutoConfiguration.class);

    private final NotifyProperties properties;

    public NotifyAutoConfiguration(NotifyProperties properties) {
        this.properties = properties;
    }

    @Bean
    public NotifyService notifyService() {
        NotifyService notifyService = new NotifyService();

        NotifyProperties.Mail mailConfig = properties.getMail();
        if (mailConfig.isEnable()) {
            notifyService.setMailSender(mailSender());
            notifyService.setSendFrom(mailConfig.getSendfrom());
            notifyService.setSendTo(mailConfig.getSendto());
            logger.warn("config mail sender: "+ new Gson().toJson(mailConfig));
        }

        NotifyProperties.Sms smsConfig = properties.getSms();
        if (smsConfig.isEnable()) {
            if(smsConfig.getType()!=null && smsConfig.getType().equalsIgnoreCase("aliyun")){
                notifyService.setSmsSender(aliyunSmsSender());
            }else {
                notifyService.setSmsSender(tencentSmsSender());
            }
            notifyService.setSmsTemplate(smsConfig.getTemplate());
        }

        NotifyProperties.Wx wxConfig = properties.getWx();
        if (wxConfig.isEnable()) {
            notifyService.setWxTemplateSender(wxTemplateSender());
            notifyService.setWxTemplate(wxConfig.getTemplate());
        }
        return notifyService;
    }

    @Bean
    public JavaMailSender mailSender() {
        NotifyProperties.Mail mailConfig = properties.getMail();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailConfig.getHost());
        mailSender.setUsername(mailConfig.getUsername());
        mailSender.setPassword(mailConfig.getPassword());
        return mailSender;
    }

    @Bean
    public WxTemplateSender wxTemplateSender() {
        WxTemplateSender wxTemplateSender = new WxTemplateSender();
        return wxTemplateSender;
    }

    @Bean
    public TencentSmsSender tencentSmsSender() {
        NotifyProperties.Sms smsConfig = properties.getSms();
        TencentSmsSender smsSender = new TencentSmsSender();
        smsSender.setSender(new SmsSingleSender(smsConfig.getAppid(), smsConfig.getAppkey()));
        return smsSender;
    }

    @Bean
    public AliyunSmsSender aliyunSmsSender(){
        NotifyProperties.Sms config = properties.getSms();
        AliyunSmsSender sender = new AliyunSmsSender(config);

        return sender;
    }
}
