package com.danichagas.notification_service.dto;

import com.danichagas.notification_service.domain.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NotificationRequestDTO(
    @NotBlank(message = "O destino é obrigatório")
    String destination,

    @NotBlank(message = "A mensagem não pode estar vazia")
    String message,

    @NotNull(message = "O tipo de notificação é obrigatório")
    NotificationType type
    ) {}
