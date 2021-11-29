package spring.dao;

import org.springframework.stereotype.Component;
import spring.model.InsuredPerson;

import java.util.ArrayList;
import java.util.List;

@Component
public class InsuredPersonDao {
    private List<InsuredPerson> insuredPeopleList;

   {
        insuredPeopleList = new ArrayList<>();
        insuredPeopleList.add(new InsuredPerson(1,"alex", "gnilitsky",null,"1",100.0));
        insuredPeopleList.add(new InsuredPerson(2,"alex", "gnilitsky",null,"2",100.0));
    }

    public List<InsuredPerson> getInsuredPerson(){
        return insuredPeopleList;
    }

    public List<InsuredPerson> getInsuredPersonList(int numberContract){
        List<InsuredPerson> insuredPersonList = new ArrayList<>();

        for (InsuredPerson insuredPerson : insuredPeopleList){
            if (insuredPerson.getNumberContract() == numberContract){
                insuredPersonList.add(insuredPerson);
            }
        }

        return insuredPersonList;
    }

    public void save(InsuredPerson insuredPerson){
        insuredPeopleList.add(insuredPerson);
    }

    public InsuredPerson show(String INN){
        return insuredPeopleList.stream().filter(person -> person.getINN().equals(INN)).findAny().orElse(null);
    }

    public void update(String INN, InsuredPerson updatedInsuredPerson){
        InsuredPerson insuredPerson = show(INN);

        insuredPerson.setFirstName(updatedInsuredPerson.getFirstName());
        insuredPerson.setLastName(updatedInsuredPerson.getLastName());
        insuredPerson.setMiddleName(updatedInsuredPerson.getMiddleName());
        insuredPerson.setINN(updatedInsuredPerson.getINN());
        insuredPerson.setPrice(updatedInsuredPerson.getPrice());
    }

    public void delete(String INN){
        insuredPeopleList.removeIf(person -> person.getINN().equals(INN));
    }
}
