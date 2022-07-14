package uz.unicorn.rentme.dto.price;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.enums.PriceType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceSearchDTO implements BaseDTO {
    private PriceType type;
    private Long minPrice = 0L;
    private Long maxPrice = Long.MAX_VALUE;
}
