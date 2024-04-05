package br.imanage.service;

import br.imanage.entity.request.UserRequest;
import br.imanage.entity.response.UserResponse;

public interface UserService {
    public UserResponse createUser(UserRequest userRequest);


}