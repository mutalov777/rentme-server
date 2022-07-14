package uz.unicorn.rentme.dto.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.unicorn.rentme.dto.base.GenericDTO;
import uz.unicorn.rentme.dto.picture.PictureCreateDto;
import uz.unicorn.rentme.enums.transport.TransportColor;
import uz.unicorn.rentme.enums.transport.TransportFuel;
import uz.unicorn.rentme.enums.transport.TransportTransmission;

import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransportUpdateDTO extends GenericDTO {
    private String model;

    @Pattern(regexp = "\\d{4}")
    private Integer year;

    private TransportTransmission transmission;

    private TransportFuel fuelType;

    private TransportColor color;

    private List<PictureCreateDto> pictures;

    private Boolean wellEquipped = false;
}
