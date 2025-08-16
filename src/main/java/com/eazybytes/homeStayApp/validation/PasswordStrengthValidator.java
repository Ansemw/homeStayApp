package com.eazybytes.homeStayApp.validation;

import com.eazybytes.homeStayApp.annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator,String> {

    HashSet<String> weakPasswords = new HashSet<>();
    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        weakPasswords.add("12345");
        weakPasswords.add("password");
        weakPasswords.add("qwerty");
    }

    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext constraintValidatorContext) {
        return (passwordField!=null)&&(!weakPasswords.contains(passwordField)) ;
    }
}
