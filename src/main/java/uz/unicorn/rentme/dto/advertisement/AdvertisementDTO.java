package uz.unicorn.rentme.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.unicorn.rentme.dto.base.GenericDTO;
import uz.unicorn.rentme.dto.location.LocationDTO;
import uz.unicorn.rentme.dto.price.PriceDTO;
import uz.unicorn.rentme.dto.transport.TransportDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementDTO extends GenericDTO {
    private String description;
    private List<PriceDTO> prices;
    private String category;
    private LocationDTO location;
    private LocalDate startDate;
    private Long minDuration;
    private Long maxDuration;
    private TransportDTO transport;
    private LocalDateTime createdAt;
    private Long createdBy;
}
