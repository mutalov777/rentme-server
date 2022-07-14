package uz.unicorn.rentme.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.unicorn.rentme.dto.base.GenericDTO;
import uz.unicorn.rentme.dto.location.LocationUpdateDTO;
import uz.unicorn.rentme.dto.price.PriceUpdateDTO;
import uz.unicorn.rentme.dto.transport.TransportUpdateDTO;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementUpdateDTO extends GenericDTO {
    private String description;

    private List<PriceUpdateDTO> prices;

    private AdvertisementCategory category;

    private LocationUpdateDTO location;

    private LocalDate startDate;

    private Long minDuration;

    private Long maxDuration;

    private TransportUpdateDTO transport;
}
