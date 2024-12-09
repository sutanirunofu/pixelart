package surofu.pixelart.user;

import surofu.pixelart.auth.SignupDTO;

public interface UserSerializer {
    FindUserRTO userToFind(User user);
    User signupToUser(SignupDTO signupDTO);
}
