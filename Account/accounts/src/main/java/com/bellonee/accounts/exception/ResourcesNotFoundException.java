package com.bellonee.accounts.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourcesNotFoundException extends RuntimeException{
    public ResourcesNotFoundException(String resourceName, String resourceField, String resourceValue){
        super(String.format("%s not found with the given input data %s : %s", resourceName, resourceField,resourceValue));
    }
}
