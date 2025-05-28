package com.StudentManagementSystem.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String resourceName;

    public ResourceNotFoundException(String resourceName) {
        super(String.format("Resource not found with name %s", resourceName));
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }
}
