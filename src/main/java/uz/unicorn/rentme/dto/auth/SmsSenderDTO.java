package uz.unicorn.rentme.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SmsSenderDTO {
    private String recipients;
    private String originator;
    private String body;
}
