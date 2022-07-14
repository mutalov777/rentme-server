package uz.unicorn.rentme.service.auth;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.unicorn.rentme.dto.auth.SmsSenderDTO;
import uz.unicorn.rentme.entity.Otp;
import uz.unicorn.rentme.repository.OtpRepository;
import uz.unicorn.rentme.response.AppErrorDTO;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.BaseService;
import uz.unicorn.rentme.utils.OtpUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OtpService implements BaseService {

    private final OtpRepository otpRepository;
    private final OtpUtils otpUtils;

    public ResponseEntity<DataDTO<String>> sendSms(String phoneNumber) {
        try {
//            int random = otpUtils.randomCode();
            int random = 1;
            String jsonInputString = getString(phoneNumber, random);
            HttpRequest request = getHttpRequest(jsonInputString);
            sendClient(request);
            Optional<Otp> optional = otpRepository.findByPhoneNumber(phoneNumber);
            if (optional.isPresent()) {
                saveOtp(random, optional.get());
            } else {
                otpRepository.save(new Otp(phoneNumber, LocalDateTime.now().plusMinutes(otpUtils.getExpiry()), random));
            }
            return new ResponseEntity<>(new DataDTO<>("success"));
        } catch (IOException | InterruptedException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .message(e.getLocalizedMessage())
                    .message(e.getMessage())
                    .status(HttpStatus.CONFLICT)
                    .build()), HttpStatus.CONFLICT);
        }

    }

    private String getString(String phoneNumber, int random) {
        return (new Gson()).toJson(new SmsSenderDTO(phoneNumber,
                "RentMe", "Hello, your code is: %d\nDon't share anyone".formatted(random)));
    }

    private void sendClient(HttpRequest request) throws IOException, InterruptedException {
        var client = java.net.http.HttpClient.newHttpClient();
        client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
    }

    private void saveOtp(int random, Otp otp) {
        otp.setCode(random);
        otp.setExpiry(LocalDateTime.now().plusMinutes(otpUtils.getExpiry()));
        otpRepository.save(otp);
    }

    private HttpRequest getHttpRequest(String jsonInputString) {
        return HttpRequest.newBuilder()
                .uri(URI.create(otpUtils.getBaseUrl()))
                .header("Authorization", otpUtils.getAuthorization())
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                .build();
    }

}
