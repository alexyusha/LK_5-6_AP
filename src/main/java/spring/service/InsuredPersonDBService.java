package spring.service;

import org.springframework.stereotype.Service;
import spring.dao.ContractDao;
import spring.dao.InsuredPersonDao;
import spring.model.Contract;
import spring.model.InsuredPerson;

import java.util.List;
@Service
public class InsuredPersonDBService {

    private final InsuredPersonDao insuredPersonDao;

    public InsuredPersonDBService(InsuredPersonDao insuredPersonDao) {
        this.insuredPersonDao = insuredPersonDao;
    }

    public List<InsuredPerson> getPeople(){
        return insuredPersonDao.getInsuredPerson();
    }

    public List<InsuredPerson> getPeopleContract(Contract contract){
        return insuredPersonDao.readAll(contract);
    }

    public InsuredPerson getPerson(String INN){
        return  insuredPersonDao.read(INN);
    }

    public void add(InsuredPerson person){
        insuredPersonDao.create(person);
    }

    public void save(InsuredPerson person){
        insuredPersonDao.update(person);
    }

    public void remove(String INN){
        insuredPersonDao.delete(INN);
    }
}
