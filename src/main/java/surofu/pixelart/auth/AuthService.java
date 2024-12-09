package surofu.pixelart.auth;

public interface AuthService {
    SignupRTO signup(SignupDTO signupDTO) throws AuthException;
    LoginRTO login(LoginDTO loginDTO) throws AuthException;
}
