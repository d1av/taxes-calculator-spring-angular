package com.taxes.calculator.domain.user;

import com.taxes.calculator.domain.AggregateRoot;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.handler.Notification;

import java.time.Instant;
import java.util.Set;

public class User extends AggregateRoot<UserID> {
    private String name;
    private Set<Role> role;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    public User(final UserID userID,
                final String name,
                final Set<Role> role,
                final Instant createdAt,
                final Instant updatedAt,
                final Instant deletedAt) {
        super(userID);
        this.name = name;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;

        selfValidate();
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);

        if (notification.hasError()) {
            throw new NotificationException(
                    "Failed to validate Aggregate User", notification);
        }
    }

    @Override
    public void validate(ValidationHandler handler) {

    }
}
