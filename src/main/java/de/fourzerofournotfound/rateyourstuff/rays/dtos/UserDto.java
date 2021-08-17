package de.fourzerofournotfound.rateyourstuff.rays.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private Long loginId;
    private String loginEmail;
    private Boolean loginIsEnabled;
}
