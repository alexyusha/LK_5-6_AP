package spring.dao;

import org.springframework.stereotype.Component;
import spring.model.Contract;
import spring.model.InsuredPerson;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContractDao {
    private List<Contract> contractList;

    {
        contractList = new ArrayList<>();
        List<InsuredPerson> test = new ArrayList<>();
        test.add(new InsuredPerson(3,"alex", "gnilitsky",null,"3",100.0));
        contractList.add(new Contract(1, null));
        contractList.add(new Contract(2, null));
        contractList.add(new Contract(3, test));
    }


    public List<Contract> getContracts(){
        return contractList;
    }

    public Contract show(int number){
            return contractList.stream().filter(contract -> contract.getNumber()==number).findAny().orElse(null);
    }
}
