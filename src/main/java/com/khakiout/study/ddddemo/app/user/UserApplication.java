package com.khakiout.study.ddddemo.app.user;

import com.khakiout.study.ddddemo.app.BaseApplication;
import com.khakiout.study.ddddemo.domain.entity.UserEntity;
import com.khakiout.study.ddddemo.domain.exception.EntityValidationException;
import com.khakiout.study.ddddemo.infrastructure.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO: add reactive
 */
@Service
public class UserApplication implements BaseApplication<UserDTO> {

    Logger logger = LoggerFactory.getLogger(UserApplication.class);

    @Autowired
    private final UserRepository userRepository;

    public UserApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserEntity> users = userRepository.getAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        users.forEach(user -> {
            UserDTO dto = this.mapDTO(user);

            userDTOs.add(dto);
        });

        return userDTOs;
    }

    @Override
    public UserDTO findById(String id) {
        UserEntity user = userRepository.findById(id);
        UserDTO dto = this.mapDTO(user);

        return dto;
    }

    @Override
    public void create(UserDTO userDTO) throws EntityValidationException {
        UserEntity user = this.mapEntity(userDTO);
        userRepository.create(user);
    }

    @Override
    public void update(String id, UserDTO userDTO) {

    }

    @Override
    public void delete(String id) {

    }

    // TODO: move to a mapper class
    private UserDTO mapDTO(UserEntity user) {
        UserDTO dto = new UserDTO();

        if (user != null) {
            dto.setId(user.getId());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            String email = null;
            if (user.getEmail() != null) {
                email = user.getEmail().getEmail();
            }
            dto.setEmail(email);
        }

        return dto;
    }

    // TODO: move to a mapper class
    private UserEntity mapEntity(UserDTO userDTO) throws EntityValidationException {
        UserEntity userEntity = new UserEntity(null, userDTO.getFirstName(), userDTO.getLastName(),
            userDTO.getEmail(), null, null);

        return userEntity;
    }
}
