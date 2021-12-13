package spring.model;

import lombok.*;

import javax.validation.constraints.*;
import java.util.Calendar;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InsuredPerson {
    @NotEmpty(message = "First name should not be empty ")
    @Size(min = 1, max = 20, message =  "1 between 20")
    private String firstName;
    @NotEmpty(message = "Last name should not be empty ")
    @Size(min = 1, max = 20, message =  "1 between 20")
    private String lastName;
    @Size(min = 0, max = 20, message =  "1 between 20")
    private String middleName;
    @Size(min = 1, max = 12, message =  "1 between 12")
    private String inn;
    @Positive
    private double price;
    @NotEmpty(message = "Number should not be empty")
    @Size(min = 1, max = 10, message = "1 between 10")
    @Positive
    private String numberContract;
}
