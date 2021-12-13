package spring.model;

import lombok.*;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Contract {

    private long id;
    @NotEmpty(message = "Number should not be empty")
    @Size(min = 1, max = 10, message = "1 between 10")
    @Positive
    private String number;
    private List<InsuredPerson> insuredPersons;
}
