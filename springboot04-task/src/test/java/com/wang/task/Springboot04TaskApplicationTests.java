package com.wang.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot04TaskApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void contextLoads() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("通知");//设置标题
        simpleMailMessage.setText("恭喜你获得了优秀员工奖");//内容
        simpleMailMessage.setTo("yun814752288@163.com"); //收件人
        simpleMailMessage.setFrom("814752288@qq.com"); //寄件人
        mailSender.send(simpleMailMessage);
    }
    //复杂邮件
    @Test
    public void compMail(){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject("重要通知");//设置标题
            mimeMessageHelper.setText("恭喜你获得了优秀员工奖");//内容
            mimeMessageHelper.setText("<b style='color:red'>年终奖翻倍</b>",true);//内容  第二个参数设置是否识别html
            mimeMessageHelper.addAttachment("1.jpg",new File("C:\\Users\\admin\\Desktop\\快递\\快递面单\\圆通.png")); //传输文件
            mimeMessageHelper.addAttachment("2.jpg",new File("C:\\Users\\admin\\Desktop\\workdown\\pic\\jvm运行时数据区域.jpg"));
            mimeMessageHelper.setTo("yun814752288@163.com"); //收件人
            mimeMessageHelper.setFrom("814752288@qq.com"); //寄件人
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}

