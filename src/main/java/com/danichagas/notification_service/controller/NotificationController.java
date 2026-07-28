package com.danichagas.notification_service.controller;

import com.danichagas.notification_service.domain.Notification;
import com.danichagas.notification_service.dto.NotificationRequestDTO;
import com.danichagas.notification_service.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Notificações", description = "Endpoints para gerenciamento e envio de notificações")
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    @Operation(summary = "Envia uma nova notificação", description = "Recebe o payload e enfileira a notificação para envio assíncrono.")
    public ResponseEntity<Notification> createNotification(@RequestBody @Valid NotificationRequestDTO requestDTO) {
        Notification notification = service.sendNotification(requestDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(notification);
    }
}
