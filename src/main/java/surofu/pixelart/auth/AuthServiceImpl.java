package surofu.pixelart.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import surofu.pixelart.user.User;
import surofu.pixelart.user.UserRepository;
import surofu.pixelart.user.UserSerializer;
import surofu.pixelart.user.UserService;
import surofu.pixelart.utils.JwtUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserSerializer userSerializer;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public SignupRTO signup(SignupDTO signupDTO) throws AuthException {
        Optional<User> candidate = userRepository.findByUsername(signupDTO.getUsername());

        if (candidate.isPresent())
            throw new AuthException(
                    String.format("User `%s` already exists", signupDTO.getUsername()),
                    HttpStatus.BAD_REQUEST
            );

        User user = userSerializer.signupToUser(signupDTO);
        user.setPassword(encoder.encode(signupDTO.getPassword()));

        userRepository.save(user);

        return new SignupRTO("Signup successful");
    }

    @Override
    public LoginRTO login(LoginDTO loginDTO) throws AuthException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new AuthException("Bad credentials", HttpStatus.UNAUTHORIZED);
        }

        Optional<User> candidate = userRepository.findByUsername(loginDTO.getUsername());

        if (candidate.isEmpty())
            throw new AuthException(
                    String.format("User `%s` not found", loginDTO.getUsername()),
                    HttpStatus.NOT_FOUND
            );

        UserDetails userDetails = userService.loadUserByUsername(loginDTO.getUsername());

        String accessToken = jwtUtils.generateAccessToken(userDetails);
        String refreshToken = jwtUtils.generateRefreshToken(userDetails);

        TokensDTO tokens = new TokensDTO(accessToken, refreshToken);

        return new LoginRTO(tokens, userSerializer.userToFind(candidate.get()));
    }
}
