package com.landingis.api.exception;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.enumeration.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 Error - No Handler Found
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiMessageDto<Void>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ApiMessageDto<Void> response = new ApiMessageDto<>(
                false,
                ErrorCode.NOT_FOUND.getCode(),
                null,
                "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiMessageDto<List<HashMap<String, String>>>> handleValidationException(
            MethodArgumentNotValidException ex) {
        List<HashMap<String, String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> {
                    HashMap<String, String> error = new HashMap<>();
                    error.put("field", fieldError.getField());
                    error.put("message", fieldError.getDefaultMessage());
                    return error;
                })
                .collect(Collectors.toList());

        ApiMessageDto<List<HashMap<String, String>>> response = new ApiMessageDto<>(
                false,
                ErrorCode.INVALID_FORM.getCode(),
                errors,
                ErrorCode.INVALID_FORM.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 404 Error - Resource Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiMessageDto<Void>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiMessageDto<Void> response = new ApiMessageDto<>(
                false,
                ErrorCode.NOT_FOUND.getCode(),
                null,
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Business Errors
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiMessageDto<Void>> handleBusinessException(BusinessException ex) {
        ApiMessageDto<Void> response = new ApiMessageDto<>(
                false,
                ErrorCode.BUSINESS_ERROR.getCode(),
                null,
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Other Errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiMessageDto<Void>> handleGlobalException(Exception ex) {
        ApiMessageDto<Void> response = new ApiMessageDto<>(
                false,
                ErrorCode.UNKNOWN_ERROR.getCode(),
                null,
                ErrorCode.UNKNOWN_ERROR.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
