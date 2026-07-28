package com.danichagas.notification_service.service;

import com.danichagas.notification_service.config.RabbitMQConfig;
import com.danichagas.notification_service.domain.Notification;
import com.danichagas.notification_service.domain.NotificationStatus;
import com.danichagas.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailProviderClient emailProviderClient;

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void consumeEmailNotification(Notification notification) {
        log.info("Consumindo notificação da fila: ID {}", notification.getId());

        try {
            emailProviderClient.sendEmail(notification.getDestination(), notification.getMessage());

            notification.setStatus(NotificationStatus.SENT);
            repository.save(notification);

        } catch (Exception e) {
            log.error("As 3 tentativas falharam. Erro final ao processar ID: {}", notification.getId());
            notification.setStatus(NotificationStatus.FAILED);
            repository.save(notification);
        }
    }
}
