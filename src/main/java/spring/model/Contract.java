package spring.model;

import lombok.*;

import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Contract {

    private int number;
    //private Calendar dateConclusion;
    //private Calendar startContract;
    //private Calendar finishContract;
    private List<InsuredPerson> insuredPeoples;
}
