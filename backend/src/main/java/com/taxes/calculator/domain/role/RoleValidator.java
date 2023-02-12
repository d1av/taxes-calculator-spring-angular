package com.taxes.calculator.domain.role;

import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.Validator;

import java.util.Objects;

public class RoleValidator extends Validator {
    private static final int ROLE_MAX_LENGTH = 20;
    private static final int ROLE_MIN_LENGTH = 4;

    private final Role role;

    public RoleValidator(final Role role,
                         final ValidationHandler aHandler) {
        super(aHandler);
        this.role = role;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var authority = this.role.getAuthority();
        if (Objects.isNull(authority)) {
            this.validationHandler().append(new Error("'authority' should not be null"));
        } else {
            if (authority.trim().isBlank()) {
                this.validationHandler().append(new Error("'authority' should not be empty"));
            } else {
                final int length = authority.length();
                if (length > ROLE_MAX_LENGTH || length < ROLE_MIN_LENGTH) {
                    this.validationHandler()
                            .append(new Error("'authority' must be between %s and %s characters"
                                    .formatted(ROLE_MIN_LENGTH, ROLE_MAX_LENGTH)));
                }
            }
        }
    }
}
