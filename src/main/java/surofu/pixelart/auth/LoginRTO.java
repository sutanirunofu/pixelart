package surofu.pixelart.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import surofu.pixelart.user.FindUserRTO;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRTO implements Serializable {
    private TokensDTO tokens;
    private FindUserRTO user;
}
