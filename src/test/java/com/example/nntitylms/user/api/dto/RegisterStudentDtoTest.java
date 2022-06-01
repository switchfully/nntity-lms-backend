package com.example.nntitylms.user.api.dto;

import org.assertj.core.api.Assertions;
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

    @Test
    void givenNullDisplayName_ThenViolationConstraintNotEmpty() {
        //GIVEN
        RegisterStudentDto registerStudentDto = new RegisterStudentDto(null, "cinderella@disney.com", "FairyGodmother");
        Set<ConstraintViolation<RegisterStudentDto>> violations = validator.validate(registerStudentDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenBlankDisplayName_ThenViolationConstraintNotEmpty() {
        //GIVEN
        RegisterStudentDto registerStudentDto = new RegisterStudentDto("  ", "cinderella@disney.com", "FairyGodmother");
        Set<ConstraintViolation<RegisterStudentDto>> violations = validator.validate(registerStudentDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenNULLEmail_ThenViolationConstraintNotEmpty() {
        //GIVEN
        RegisterStudentDto registerStudentDto = new RegisterStudentDto("Cinderella", null, "FairyGodmother");
        Set<ConstraintViolation<RegisterStudentDto>> violations = validator.validate(registerStudentDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenImproperFormatEmail_ThenViolationConstraintNotEmpty() {
        //GIVEN
        RegisterStudentDto registerStudentDto = new RegisterStudentDto("Cinderella", "disney.com", "FairyGodmother");
        Set<ConstraintViolation<RegisterStudentDto>> violations = validator.validate(registerStudentDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }


    @Test
    void givenNULLPassword_ThenViolationConstraintNotEmpty() {
        //GIVEN
        RegisterStudentDto registerStudentDto = new RegisterStudentDto("Cinderella", "cinderella@disney.com", null);
        Set<ConstraintViolation<RegisterStudentDto>> violations = validator.validate(registerStudentDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenShortThan8Password_ThenViolationConstraintNotEmpty() {
        //GIVEN
        String passwordShorterThan8Char = "Pas0rd!";
        RegisterStudentDto registerStudentDto = new RegisterStudentDto("Cinderella", "cinderella@disney.com", passwordShorterThan8Char);
        Set<ConstraintViolation<RegisterStudentDto>> violations = validator.validate(registerStudentDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }


    @Test
    void givenPasswordWithoutUppercaseLetter_ThenViolationConstraintNotEmpty() {
        //GIVEN
        String passwordwithoutUppercase = "passw0rd!";
        RegisterStudentDto registerStudentDto = new RegisterStudentDto("Cinderella", "cinderella@disney.com", passwordwithoutUppercase);
        Set<ConstraintViolation<RegisterStudentDto>> violations = validator.validate(registerStudentDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenPasswordWithoutLowercaseLetter_ThenViolationConstraintNotEmpty() {
        //GIVEN
        String passwordwithoutLowercase = "PASSW0RD!";
        RegisterStudentDto registerStudentDto = new RegisterStudentDto("Cinderella", "cinderella@disney.com", passwordwithoutLowercase);
        Set<ConstraintViolation<RegisterStudentDto>> violations = validator.validate(registerStudentDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }


    @Test
    void givenPasswordWithoutNumber_ThenViolationConstraintNotEmpty() {
        //GIVEN
        String passwordWithoutNumber = "Password!";
        RegisterStudentDto registerStudentDto = new RegisterStudentDto("Cinderella", "cinderella@disney.com", passwordWithoutNumber);
        Set<ConstraintViolation<RegisterStudentDto>> violations = validator.validate(registerStudentDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }


    @Test
    void givenPasswordWithoutSpecialCharacter_ThenViolationConstraintNotEmpty() {
        //GIVEN
        String passwordWithoutSpecialCharacter = "Passw0rd";
        RegisterStudentDto registerStudentDto = new RegisterStudentDto("Cinderella", "cinderella@disney.com", passwordWithoutSpecialCharacter);
        Set<ConstraintViolation<RegisterStudentDto>> violations = validator.validate(registerStudentDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

}