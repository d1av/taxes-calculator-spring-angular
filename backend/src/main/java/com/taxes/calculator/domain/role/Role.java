package com.taxes.calculator.domain.role;

import com.taxes.calculator.domain.AggregateRoot;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.utils.InstantUtils;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.handler.Notification;

import java.time.Instant;
import java.util.Optional;

public class Role extends AggregateRoot<RoleID> {
    private String authority;
    private Instant createdAt;
    private Instant updatedAt;

    private Role(final RoleID roleID,
                 final String aAuthority,
                 final Instant createdAt,
                 final Instant updatedAt) {
        super(roleID);
        this.authority = aAuthority;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        selfValidate();
        this.authority = authority.toUpperCase();
    }

    public static Role with(final String aStringAuthority) {
        final var anId = RoleID.unique();
        final var now = InstantUtils.now();
        return new Role(anId, aStringAuthority, now, now);
    }

    public static Role newRole(final String aAuthority) {
        final var now = InstantUtils.now();
        final var anId = RoleID.unique();
        return new Role(anId, aAuthority, now, now);
    }

    public Role update(String authority) {
        this.authority = authority;
        selfValidate();
        this.updatedAt = InstantUtils.now();
        this.authority = authority.toUpperCase();
        return this;
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);

        if (notification.hasError()) {
            throw new NotificationException(
                    "Failed to validate Aggregate Authority", notification);
        }
    }


    @Override
    public void validate(ValidationHandler handler) {
        new RoleValidator(this, handler).validate();
    }

    public RoleID getId() {
        return this.id;
    }

    public String getAuthority() {
        return authority;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
