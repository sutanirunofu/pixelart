package surofu.pixelart.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AuthException extends Exception {
    private HttpStatus status;

    public AuthException(String message) {
        super(message);
        this.status = HttpStatus.UNAUTHORIZED;
    }

    public AuthException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
