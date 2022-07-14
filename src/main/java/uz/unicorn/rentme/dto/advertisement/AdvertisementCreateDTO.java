package uz.unicorn.rentme.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.dto.location.LocationCreateDTO;
import uz.unicorn.rentme.dto.price.PriceCreateDTO;
import uz.unicorn.rentme.dto.transport.TransportCreateDTO;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementCreateDTO implements BaseDTO {

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Prices cannot be blank")
    private List<PriceCreateDTO> prices;

    @NotBlank(message = "Category cannot be blank")
    private AdvertisementCategory category;

    @NotBlank(message = "Location cannot be blank")
    private LocationCreateDTO location;

    @NotBlank(message = "Starting date cannot be blank")
    private LocalDate startDate;

    @NotBlank(message = "Minimum duration cannot be blank")
    private Long minDuration;

    @NotBlank(message = "Maximum duration cannot be blank")
    private Long maxDuration;

    @NotBlank(message = "Transport cannot be blank")
    private TransportCreateDTO transport;

}
