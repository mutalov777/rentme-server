package uz.unicorn.rentme.dto.transportModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.unicorn.rentme.dto.base.GenericDTO;
import uz.unicorn.rentme.enums.AdvertisementCategory;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportModelUpdateDTO extends GenericDTO {
    private String name;
    private AdvertisementCategory category;
    private String imagePath;
}
