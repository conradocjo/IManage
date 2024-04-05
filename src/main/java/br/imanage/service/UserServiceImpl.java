package br.imanage.service;

import br.imanage.entity.User;
import br.imanage.entity.request.UserRequest;
import br.imanage.entity.response.UserResponse;
import br.imanage.exception.TechnicalException;
import br.imanage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) throws TechnicalException {
        try {
            var recordedUser = this.userRepository.save(getUser(userRequest));
            return new UserResponse(recordedUser);
        } catch (Exception e) {
            throw new TechnicalException(e.getMessage());
        }

    }

    private User getUser(UserRequest userRequest) {
        return User.builder()
                .password(new BCryptPasswordEncoder().encode(userRequest.getPassword()))
                .nameuser(userRequest.getUser())
                .name(userRequest.getName())
                .role(User.UserRole.ADMIN)
                .creationDate(LocalDateTime.now())
                .build();
    }

}