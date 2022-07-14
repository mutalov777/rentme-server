package uz.unicorn.rentme.dto.location;

import lombok.*;
import uz.unicorn.rentme.dto.base.BaseDTO;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationCreateDTO implements BaseDTO {

    @NotBlank(message = "Location name cannot be blank")
    private String name;

    @NotBlank(message = "Location longitude cannot be blank")
    private Double longitude;

    @NotBlank(message = "Location latitude cannot be blank")
    private Double latitude;

}
