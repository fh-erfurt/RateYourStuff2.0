package de.fourzerofournotfound.rateyourstuff.rays.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDto {
    private Long id;
    private String email;
    private String passwordHashed;
}
