package br.imanage.entity.response;


import br.imanage.entity.User;
import lombok.Data;

@Data
public class UserResponse {

    public UserResponse(User createdUser) {
        this.setId(createdUser.getId());
        this.setName(createdUser.getName());
        this.setUser(createdUser.getNameuser());
    }

    private Integer id;
    private String name;
    private String user;

}