package uz.unicorn.rentme.dto.picture;

import lombok.*;
import uz.unicorn.rentme.dto.base.BaseDTO;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PictureCreateDto implements BaseDTO {
    private String path;

    private Boolean main;
}
