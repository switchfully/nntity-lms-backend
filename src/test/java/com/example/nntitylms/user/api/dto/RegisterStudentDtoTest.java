package com.example.nntitylms.user.api.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

class RegisterStudentDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

//    @Test
//    void givenNULLCustomerId_ThenViolationConstraintNotEmpty() {
//        //GIVEN
//        CreateOrderDto createOrderDto = new CreateOrderDto(null, createItemGroupDtoList);
//        Set<ConstraintViolation<CreateOrderDto>> violations = validator.validate(createOrderDto);
//        //THEN
//        Assertions.assertThat(violations.isEmpty()).isFalse();
//    }
}