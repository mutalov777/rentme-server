package uz.unicorn.rentme.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.unicorn.rentme.dto.base.BaseDTO;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DateSearchDTO implements BaseDTO {
    private Date startDate;
    private Date endDate;
}
