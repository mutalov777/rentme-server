package uz.unicorn.rentme.dto.picture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.unicorn.rentme.dto.base.GenericDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PictureUpdateDto extends GenericDTO {
    private String path;

    private Boolean main;
}
