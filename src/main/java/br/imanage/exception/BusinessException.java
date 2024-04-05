package br.imanage.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

}
