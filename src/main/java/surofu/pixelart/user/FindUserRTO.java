package surofu.pixelart.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindUserRTO implements Serializable {
    private Long id;
    private String firstname;
    private String username;
    private String role;
    private ZonedDateTime registrationDate;
}
