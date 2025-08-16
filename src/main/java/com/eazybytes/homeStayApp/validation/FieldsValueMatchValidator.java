package com.eazybytes.homeStayApp.validation;

import com.eazybytes.homeStayApp.annotation.FieldsValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    String field;
    String fieldMatch;
    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {

        field = constraintAnnotation.field();
        fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        Object fieldValue = new BeanWrapperImpl(o).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(o).getPropertyValue(fieldMatch);

        if(fieldValue!=null) return (fieldValue.equals(fieldMatchValue));

        else return (fieldMatchValue==null);
    }
}
