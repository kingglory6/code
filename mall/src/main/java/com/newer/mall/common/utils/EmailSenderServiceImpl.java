package com.newer.mall.common.utils;

import java.io.File;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String from;

	@Override
	public void sendTextMail(String subject, String content, String... to) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(content);
		mailMessage.setSentDate(new Date());
		javaMailSender.send(mailMessage);
	}

	@Override
	public void sendAttachmentMail(String subject, String content, String filePath, String fileName, String... to) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		 try {
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	            helper.setFrom(from);
	            helper.setTo(to);
	            helper.setSubject(subject);
	            helper.setText(content);
	            //附件
	            FileSystemResource file = new FileSystemResource(new File(filePath));
	            helper.addAttachment(fileName, file);
	            javaMailSender.send(mimeMessage);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	}
}
