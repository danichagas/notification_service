package com.danichagas.notification_service.repository;

import com.danichagas.notification_service.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {}