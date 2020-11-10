package com.ygl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot09TestApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;


    @Test
    void contextLoads() {
        //一个简单邮件发送
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("这是邮件主题");
        simpleMailMessage.setText("这是邮件内容");
        simpleMailMessage.setFrom("1539855225@qq.com");
        simpleMailMessage.setTo("yangaoling0110@163.com");
        javaMailSender.send(simpleMailMessage);
    }
    @Test
    void contextLoads2() throws MessagingException {
        //一个复杂邮件发送
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //正文  类似选择文件大小 字体  颜色等等
        helper.setSubject("测试邮件主题plus");
        helper.setText("<p style='color:red'>测试内容</p>",true);

        //附件
        helper.addAttachment("1.jpg",new File("C:\\Users\\ygl\\Pictures\\2.jpg"));
        helper.addAttachment("2.jpg",new File("C:\\Users\\ygl\\Pictures\\2.jpg"));
        helper.setFrom("1539855225@qq.com");
        helper.setTo("1539855225@qq.com");
        javaMailSender.send(mimeMessage);
    }

}
