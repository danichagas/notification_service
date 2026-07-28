package com.danichagas.notification_service.service;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailProviderClient {

    private final JavaMailSender mailSender;

    @Retry(name = "emailProvider")
    public void sendEmail(String destination, String message) {
        log.info(">> Preparando envio de e-mail real via Mailtrap para: {}", destination);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("system@omninotify.com");
        simpleMailMessage.setTo(destination);
        simpleMailMessage.setSubject("Alerta do Sistema - OmniNotify");
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);

        log.info(">> E-mail disparado com sucesso! Confere a caixa de entrada do Mailtrap.");
    }
}
