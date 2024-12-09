package surofu.pixelart.user;

import org.springframework.stereotype.Service;
import surofu.pixelart.auth.SignupDTO;

@Service
public class UserSerializerImpl implements UserSerializer {

    @Override
    public FindUserRTO userToFind(User user) {
        return FindUserRTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .username(user.getUsername())
                .role(user.getRole().getName())
                .registrationDate(user.getRegistrationDate())
                .build();
    }

    @Override
    public User signupToUser(SignupDTO signupDTO) {
        return User.builder()
                .firstname(signupDTO.getFirstname())
                .username(signupDTO.getUsername())
                .password(signupDTO.getPassword())
                .build();
    }
}
