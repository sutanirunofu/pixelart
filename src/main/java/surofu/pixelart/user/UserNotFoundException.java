package surofu.pixelart.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(String.format("User `%s` not found", message));
    }
}
