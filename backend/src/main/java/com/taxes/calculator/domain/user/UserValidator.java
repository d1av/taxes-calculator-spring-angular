package com.taxes.calculator.domain.user;

import com.taxes.calculator.domain.utils.IdUtils;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.Validator;

import java.util.Objects;

public class UserValidator extends Validator {

    private static final int NAME_MAX_LENGTH = 200;
    private static final int NAME_MIN_LENGTH = 1;
    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 20;
    private final User user;

    public UserValidator(final User user,
                         final ValidationHandler aHandler) {
        super(aHandler);
        this.user = user;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkPasswordConstraints();
        checkIdConstraints();
    }

    private void checkIdConstraints() {
        final var id = this.user.getId().getValue();
        if (Objects.isNull(id)) {
            this.validationHandler().append(new Error("'id' should not be null"));
        }
        int idLength = IdUtils.uuid().length();
        if(id.length()!= idLength){
            this.validationHandler().append(new Error("'id' should be valid with %s".formatted(idLength)));
        }
    }

    private void checkNameConstraints() {
        final var name = this.user.getName();
        if (Objects.isNull(name)) {
            this.validationHandler().append(new Error("'name' should not be null"));
        } else {
            if (name.trim().isBlank()) {
                this.validationHandler().append(new Error("'name' should not be empty"));
            }
            final int length = name.length();
            if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
                this.validationHandler()
                        .append(new Error("'name' must be between %s and %s characters"
                                .formatted(NAME_MIN_LENGTH, NAME_MAX_LENGTH)));
            }
        }
    }

    private void checkPasswordConstraints() {
        final var password = this.user.getPassword();
        if (Objects.isNull(password)) {
            this.validationHandler().append(new Error("'password' should not be null"));
        } else {
            if (password.trim().isBlank()) {
                this.validationHandler().append(new Error("'password' should not be empty"));
            }
            final int length = password.length();
            if (length > PASSWORD_MAX_LENGTH || length < PASSWORD_MIN_LENGTH) {
                this.validationHandler()
                        .append(new Error("'password' must be between %s and %s characters"
                                .formatted(PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH)));
            }
        }
    }
}
