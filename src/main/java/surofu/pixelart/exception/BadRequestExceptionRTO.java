package surofu.pixelart.exception;

import lombok.*;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
public class BadRequestExceptionRTO implements Serializable {
    private String message;
    private String cause;

    public BadRequestExceptionRTO(String message, String cause) {
        this.message = message;
        this.cause = cause;
    }

    public BadRequestExceptionRTO(String message) {
        this.message = message;
    }
}
