package surofu.pixelart.auth;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import surofu.pixelart.exception.BadRequestExceptionRTO;
import surofu.pixelart.user.FindUserRTO;
import surofu.pixelart.user.UserService;
import surofu.pixelart.utils.JwtUtils;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO) {
        try {
            return new ResponseEntity<>(authService.signup(signupDTO), HttpStatus.OK);
        } catch (AuthException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            return new ResponseEntity<>(authService.login(loginDTO), HttpStatus.OK);
        } catch (AuthException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        try {
            Optional<FindUserRTO> user = userService.findByUsername(principal.getName());

            if (user.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/update_access_token")
    public ResponseEntity<?> updateAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return new ResponseEntity<>(new BadRequestExceptionRTO("Jwt bad signature"), HttpStatus.BAD_REQUEST);
        }

        String accessToken = authorization.substring(7);

        try {
            String username = jwtUtils.getUsername(accessToken);
            UserDetails userDetails = userService.loadUserByUsername(username);
            String newAccessToken = jwtUtils.generateAccessToken(userDetails);
            return new ResponseEntity<>(new UpdateAccessTokenRTO(newAccessToken), HttpStatus.OK);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>(new BadRequestExceptionRTO("Jwt has been expired", e.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (SignatureException e) {
            return new ResponseEntity<>(new BadRequestExceptionRTO("Jwt bad signature", e.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new BadRequestExceptionRTO("Jwt could not be parsed", e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
}
