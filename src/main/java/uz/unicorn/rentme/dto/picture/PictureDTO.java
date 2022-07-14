package uz.unicorn.rentme.dto.picture;

import lombok.*;
import uz.unicorn.rentme.dto.base.BaseDTO;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PictureDTO implements BaseDTO {

    private String path;

    private Boolean main;

}
