package ibk.entrevista.demo.model.login;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  AuthRequest {
    private String username;
    private String password;

}
