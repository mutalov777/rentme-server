package uz.unicorn.rentme.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.unicorn.rentme.dto.base.GenericDTO;
import uz.unicorn.rentme.enums.auth.Gender;
import uz.unicorn.rentme.enums.auth.Language;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserUpdateDTO extends GenericDTO {
    @NotBlank(message = "First name cannot be blank")
    @Pattern(regexp = "[A-Z]+", message = "First name is invalid")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "[A-Z]+", message = "Last name is invalid")
    private String lastName;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+\\d{12}(\\d{2})?$", message = "Phone number is invalid")
    private String phoneNumber;

    @NotBlank(message = "Language cannot be blank")
    private Language language;

    @Email(regexp = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$", message = "Email is invalid")
    private String email = "example@gmail.com";

    @NotBlank(message = "Gender cannot be blank")
    private Gender gender;
}
