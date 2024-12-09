package surofu.pixelart.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO implements Serializable {
    private String firstname;
    private String username;
    private String password;
}
