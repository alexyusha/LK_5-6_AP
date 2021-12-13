package spring.service;

import org.springframework.stereotype.Service;
import spring.dao.ContractDao;
import spring.dao.InsuredPersonDao;
import spring.model.Contract;
import spring.model.InsuredPerson;

import java.util.List;

@Service
public class ContractDBService {

    private final ContractDao contractDao;

    public ContractDBService(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    public void add(Contract contract) {
        contractDao.create(contract);
    }

    public Contract getContract(String number) {
        Contract contract1 = contractDao.read(number);
        return contract1;
    }

    public void save(Contract contract) {
        contractDao.update(contract);
    }

    public void remove(Contract contract) {
        contractDao.delete(contract.getNumber());

    }

    public List<Contract> getContracts(){
         return contractDao.getContracts();
    }
}
