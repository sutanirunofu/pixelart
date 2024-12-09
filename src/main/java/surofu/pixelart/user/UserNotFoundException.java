package surofu.pixelart.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(String.format("User with ID `%s` not found", id));
    }

    public UserNotFoundException(String username) {
        super(String.format("User `%s` not found", username));
    }
}
