package org.hermez.common.util;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public abstract class EmailService {

  private static String host = "smtp.naver.com";

  @Value("${NAVER_EMAIL}")
  private static String from;

  @Value("${NAVER_EMAIL_PASSWORD}")
  private static String password;

  protected EmailService() {
    try {
      Context contextEnv = (Context) new InitialContext().lookup("java:comp/env");
      from = (String) contextEnv.lookup("mail.id");
      password = (String) contextEnv.lookup("mail.password");
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }

  public static String sendEmail(String to) throws Exception {
    String authenCode = null;

    Properties props = new Properties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");
    props.put("mail.smtp.ssl.enable", "true");
    props.put("mail.smtp.ssl.trust", host);

    Session session = Session.getDefaultInstance(props, new Authenticator() {

      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(from, password);
      }
    });
    try {
      authenCode = makeAuthenticationCode();
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from, "hermez"));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject("인증번호는 [ " + authenCode + " ] 입니다.");
      message.setText("hermez");
      Transport.send(message);
    } catch (MessagingException e) {
      log.info("메시지 에러",e);
    }
    log.info("메시지 서비스 완료");
    return authenCode;
  }

  private static String makeAuthenticationCode() {

    int pwdLength = 8;
    final char[] pwdTable = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&', '*',
        '(', ')', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    // System.currentTimeMillis(): 중복 방지 처리
    Random random = new Random(System.currentTimeMillis());

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < pwdLength; i++) {
      sb.append(pwdTable[random.nextInt(pwdTable.length)]);

    }

    return sb.toString();
  }
}