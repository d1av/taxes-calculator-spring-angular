package com.taxes.calculator.domain.user;

import com.taxes.calculator.domain.AggregateRoot;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.utils.InstantUtils;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.handler.Notification;

import java.time.Instant;
import java.util.*;

public class User extends AggregateRoot<UserID> {
    private String name;
    private String password;
    private Boolean active;
    private Set<Role> roles;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private User(final UserID userID,
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
        this.deletedAt = isActive ? null : deletedAt;

        selfValidate();
    }

    public static User newUser(
            final String aName,
            final String aPassword,
            final Boolean aActive
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
    
    public static User newUser(
	    final UserID anId,
	    final String aName,
	    final String aPassword,
	    final Boolean aActive
	    ) {
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
            final String aName,
            final String aPassword,
            final boolean aActive,
            final Set<Role> roles
    ) {
        final var now = InstantUtils.now();
        final var anId = UserID.unique();
        return new User(
                anId,
                aName,
                aPassword,
                aActive,
                roles,
                now,
                now,
                now
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

    public User update(String aName, String aPassword, boolean isActive, Set<Role> roles) {
        if (isActive) activate();
        else deactivate();

        this.name = aName;
        this.password = aPassword;
        this.roles = new HashSet<>(!Objects.isNull(roles) ? roles : Collections.emptySet());
        this.updatedAt = InstantUtils.now();
        selfValidate();
        return this;
    }

    public User addRole(final Role aRole) {
        if (aRole == null) return this;

        this.roles.add(aRole);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public User removeRole(final Role aRole) {
        if (aRole == null) return this;

        this.roles.remove(aRole);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public User addRoles(Set<Role> aRoles) {
        if (aRoles == null) return this;
        if (aRoles.size() == 0) return this;

        this.roles.addAll(aRoles);
        this.updatedAt = InstantUtils.now();

        return this;
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

    public UserID getId() {
        return id;
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
