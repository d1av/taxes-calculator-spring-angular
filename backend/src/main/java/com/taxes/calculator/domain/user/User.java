package com.taxes.calculator.domain.user;

import com.taxes.calculator.domain.AggregateRoot;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.utils.InstantUtils;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.handler.Notification;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User extends AggregateRoot<UserID> {
    private String name;
    private String password;
    private Set<Role> roles;
    private Boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    public User(final UserID userID,
                final String name,
                final String password,
                final Boolean isActive,
                final Set<Role> roles,
                final Instant createdAt,
                final Instant updatedAt,
                final Instant deletedAt) {
        super(userID);
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.active = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;

        selfValidate();
    }

    public static User newUser(
            final String aName,
            final String aPassword,
            final boolean aActive
    ) {
        final var anId = UserID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = aActive ? null : now;
        return new User(
                anId,
                aName,
                aPassword,
                aActive,
                new HashSet<>(),
                now,
                now,
                deletedAt
        );
    }

    public static User with(
            final UserID anId,
            final String aName,
            final String aPassword,
            final Set<Role> roles,
            final boolean aActive,
            final Instant aCreatedAt,
            final Instant aUpdatedAt,
            final Instant aDeletedAt
    ) {
        return new User(
                anId,
                aName,
                aPassword,
                aActive,
                roles,
                aCreatedAt,
                aUpdatedAt,
                aDeletedAt
        );
    }

    public static User with(final User user) {
        return new User(
                user.id,
                user.name,
                user.password,
                user.active,
                user.roles,
                user.createdAt,
                user.updatedAt,
                user.deletedAt
        );
    }

    public void deactivate() {
        if (Objects.isNull(getDeletedAt())) {
            this.deletedAt = InstantUtils.now();
        }
        this.active = false;
        this.updatedAt = InstantUtils.now();
    }

    public void activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
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
        new UserValidator(this, handler).validate();
    }

    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Boolean getActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
