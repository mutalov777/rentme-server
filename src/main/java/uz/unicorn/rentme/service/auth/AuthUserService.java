package uz.unicorn.rentme.service.auth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.unicorn.rentme.criteria.AuthUserCriteria;
import uz.unicorn.rentme.dto.auth.AuthUserCreateDTO;
import uz.unicorn.rentme.dto.auth.AuthUserDTO;
import uz.unicorn.rentme.dto.auth.AuthUserUpdateDTO;
import uz.unicorn.rentme.entity.AuthUser;
import uz.unicorn.rentme.enums.auth.AuthRole;
import uz.unicorn.rentme.enums.auth.Status;
import uz.unicorn.rentme.exceptions.NotFoundException;
import uz.unicorn.rentme.mapper.AuthUserMapper;
import uz.unicorn.rentme.repository.AuthUserRepository;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.AbstractService;
import uz.unicorn.rentme.service.base.GenericCrudService;

import java.util.List;

@Service
public class AuthUserService extends AbstractService<AuthUserMapper, AuthUserRepository>
        implements GenericCrudService<AuthUserDTO, AuthUserCreateDTO, AuthUserUpdateDTO, AuthUserCriteria> {

    public AuthUserService(@Qualifier("authUserMapperImpl") AuthUserMapper mapper, AuthUserRepository repository) {
        super(mapper, repository);
    }

    @Override
    public ResponseEntity<DataDTO<Long>> create(AuthUserCreateDTO dto) {
        checkPhoneNumber(dto.getPhoneNumber());
        AuthUser authUser = makeAuthUser(dto);
        AuthUser save = repository.save(authUser);
        return new ResponseEntity<>(new DataDTO<>(save.getId()));
    }

    @Override
    public ResponseEntity<DataDTO<Long>> update(AuthUserUpdateDTO dto) {
        AuthUser authUser = getAuthUserById(dto.getId());
        AuthUser updatedAuthUser = mapper.fromUpdateDTO(dto, authUser);
        repository.save(updatedAuthUser);
        return new ResponseEntity<>(new DataDTO<>(dto.getId()));
    }

    @Override
    public ResponseEntity<DataDTO<Boolean>> delete(Long id) {
        AuthUser authUser = getAuthUserById(id);
        authUser.setDeleted(true);
        repository.save(authUser);
        return new ResponseEntity<>(new DataDTO<>(true));
    }

    @Override
    public ResponseEntity<DataDTO<AuthUserDTO>> get(Long id) {
        AuthUser authUser = getAuthUserById(id);
        AuthUserDTO authUserDTO = mapper.toDTO(authUser);
        return new ResponseEntity<>(new DataDTO<>(authUserDTO));
    }

    @Override
    public ResponseEntity<DataDTO<List<AuthUserDTO>>> getAll(AuthUserCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<AuthUser> authUserList = repository.findAll(pageable).getContent();
        List<AuthUserDTO> authUserDTOList = mapper.toDTO(authUserList);
        return new ResponseEntity<>(new DataDTO<>(authUserDTOList, (long) authUserList.size()));
    }

    private AuthUser makeAuthUser(AuthUserCreateDTO dto) {
        AuthUser authUser = mapper.fromCreateDTO(dto);
        authUser.setRole(AuthRole.USER);
        authUser.setStatus(Status.INACTIVE);
        return authUser;
    }

    private AuthUser getAuthUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("User not found");
        });
    }

    private void checkPhoneNumber(String phoneNumber) {
        if (repository.existsByPhoneNumber(phoneNumber))
            throw new RuntimeException("%s phone number already exists".formatted(phoneNumber));
    }

}
