package org.linlinjava.litemall.core.notify.config;

import com.github.qcloudsms.SmsSingleSender;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.notify.AliyunSmsSender;
import org.linlinjava.litemall.core.notify.NotifyService;
import org.linlinjava.litemall.core.notify.TencentSmsSender;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

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

        return notifyService;
    }

    public JavaMailSender mailSender() {
        NotifyProperties.Mail mailConfig = properties.getMail();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailConfig.getHost());
        mailSender.setUsername(mailConfig.getUsername());
        mailSender.setPassword(mailConfig.getPassword());
        mailSender.setPort(mailConfig.getPort());
        Properties properties = new Properties();
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.timeout",5000);
        properties.put("mail.smtp.starttls.enable",true);
        properties.put("mail.smtp.socketFactory.fallback", "false");
        //阿里云 必须加入配置 outlook配置又不需要 视情况而定.发送不成功多数是这里的配置问题
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.port", mailConfig.getPort());
        properties.put("debug", true);
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }

    public TencentSmsSender tencentSmsSender() {
        NotifyProperties.Sms smsConfig = properties.getSms();
        TencentSmsSender smsSender = new TencentSmsSender();
//        NotifyProperties.Sms.Tencent tencent = smsConfig.getTencent();
//        smsSender.setSender(new SmsSingleSender(tencent.getAppid(), tencent.getAppkey()));
//        smsSender.setSign(smsConfig.getSign());
        return smsSender;
    }

//    public AliyunSmsSender aliyunSmsSender() {
//        NotifyProperties.Sms smsConfig = properties.getSms();
//        AliyunSmsSender smsSender = new AliyunSmsSender();
//        NotifyProperties.Sms.Aliyun aliyun = smsConfig.getAliyun();
//        smsSender.setSign(smsConfig.getSign());
//        smsSender.setRegionId(aliyun.getRegionId());
//        smsSender.setAccessKeyId(aliyun.getAccessKeyId());
//        smsSender.setAccessKeySecret(aliyun.getAccessKeySecret());
//        return smsSender;
//    }

    @Bean
    public AliyunSmsSender aliyunSmsSender(){
        NotifyProperties.Sms config = properties.getSms();
        AliyunSmsSender sender = new AliyunSmsSender(config);

        return sender;
    }
}
