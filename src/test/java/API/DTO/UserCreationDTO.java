package API.DTO;

import API.Pojo.Roles;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreationDTO {
    private String login;
    private String password;
    private String email;
    private List<Roles> roles;
}
