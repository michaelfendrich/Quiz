package quiz.entity.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    @Email(message = "⛔ Not valid email")
    @NotBlank(message = "⛔ Cannot be empty")
    @NotNull(message = "⛔ Cannot be empty")
    private String email;

    @NotBlank(message = "⛔ Cannot be empty")
    @NotNull(message = "⛔ Cannot be empty")
    private String password;

    @NotBlank(message = "⛔ Cannot be empty")
    @NotNull(message = "⛔ Cannot be empty")
    private String confirmPassword;
}
