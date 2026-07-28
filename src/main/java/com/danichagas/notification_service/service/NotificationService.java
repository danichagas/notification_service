package com.danichagas.notification_service.service;

import com.danichagas.notification_service.config.RabbitMQConfig;
import com.danichagas.notification_service.domain.Notification;
import com.danichagas.notification_service.domain.NotificationStatus;
import com.danichagas.notification_service.dto.NotificationRequestDTO;
import com.danichagas.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public Notification sendNotification(NotificationRequestDTO requestDTO) {
        Notification notification = Notification.builder()
                .destination(requestDTO.destination())
                .message(requestDTO.message())
                .type(requestDTO.type())
                .status(NotificationStatus.PENDING)
                .build();

        Notification savedNotification = repository.save(notification);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.NOTIFICATION_EXCHANGE,
                RabbitMQConfig.ROUTING_KEY_EMAIL,
                savedNotification
        );

        return savedNotification;
    }
}
