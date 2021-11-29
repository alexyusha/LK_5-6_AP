package spring.model;

import lombok.*;

import java.util.Calendar;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InsuredPerson {

    private int numberContract;
    private String firstName;
    private String lastName;
    private String middleName;
    //private Calendar birthday;
    private String INN;
    private double price;
}
