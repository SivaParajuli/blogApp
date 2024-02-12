package com.sipra.blogapplication.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    Long fieldValue;
    String stringField;

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    public ResourceNotFoundException(String resourceName, String fieldName, String stringField) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,stringField));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.stringField = stringField;
    }
}
