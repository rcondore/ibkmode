package ibk.entrevista.demo.model.api;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Currency {

    private String code;
    private String description;
}
