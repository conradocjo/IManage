package br.imanage.handler;

import br.imanage.exception.BusinessException;
import br.imanage.exception.TechnicalException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CommonsLog
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDto>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        List<ErrorDto> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorDto("Wrong value in the field: " + error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorDto>> handleBusinessException(BusinessException exception) {
        log.error("Business Exception: " + exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonList(new ErrorDto(exception.getMessage())));
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<List<ErrorDto>> handleTechnicalException(TechnicalException exception) {
        log.error("Technical Exception: " + exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonList(new ErrorDto(exception.getMessage())));
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class ErrorDto {

        private String message;

        private String details;

        public ErrorDto(String message) {
            this.message = message;
        }

    }
}
