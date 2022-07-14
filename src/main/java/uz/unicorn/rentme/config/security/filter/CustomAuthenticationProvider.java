package uz.unicorn.rentme.config.security.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import uz.unicorn.rentme.entity.AuthUser;
import uz.unicorn.rentme.entity.Otp;
import uz.unicorn.rentme.repository.OtpRepository;
import uz.unicorn.rentme.service.auth.AuthService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final AuthService authService;
    private final OtpRepository otpRepository;

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String phoneNumber = authentication.getPrincipal().toString();
        String code = authentication.getCredentials().toString();
        Otp otp = getOtp(phoneNumber);
        checkPhoneAndCode(phoneNumber, code, otp);
        List<GrantedAuthority> authorities = getAuthorities(phoneNumber);
        final UserDetails principal = new User(phoneNumber, code, authorities);
        return new UsernamePasswordAuthenticationToken(principal, code, authorities);
    }

    private List<GrantedAuthority> getAuthorities(String phoneNumber) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Optional<AuthUser> optional = authService.getOptionalByPhoneNumber(phoneNumber);
        optional.ifPresent(authUser -> authorities.add(new SimpleGrantedAuthority(authUser.getRole().toString())));
        return authorities;
    }

    private void checkPhoneAndCode(String phoneNumber, String code, Otp otp) {
        if (!phoneNumber.equals(otp.getPhoneNumber()) || Integer.parseInt(code) != otp.getCode())
            throw new RuntimeException("Phone number and/or otp code is incorrect");
        if (otp.getExpiry().isBefore(LocalDateTime.now())) throw new RuntimeException("Otp code is invalid");
    }

    private Otp getOtp(String phoneNumber) {
        Otp otp = otpRepository.findByPhoneNumber(phoneNumber).orElse(null);
        if (Objects.isNull(otp)) throw new RuntimeException("Bad credentials");
        return otp;
    }

}
