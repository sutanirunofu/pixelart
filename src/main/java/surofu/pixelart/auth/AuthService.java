package surofu.pixelart.auth;

public interface AuthService {
    SignupRTO signup(SignupDTO signupDTO) throws AuthException, Exception;
    LoginRTO login(LoginDTO loginDTO) throws AuthException;
}
