package es.javierserrano.domain.sping_validator;


import es.javierserrano.domain.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class DtoValidator {

    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .buildValidatorFactory();
        validator = factory.getValidator();
    }

    public static <T> void validate(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }
}