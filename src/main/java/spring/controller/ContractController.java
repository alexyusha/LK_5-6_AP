package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.dao.ContractDao;
import spring.dao.InsuredPersonDao;

@Controller
@RequestMapping("/contract")
public class ContractController {


    private final ContractDao contractDao;
    private final InsuredPersonDao insuredPersonDao;

    @Autowired
    public  ContractController(ContractDao contractDao, InsuredPersonDao insuredPersonDao){
        this.contractDao = contractDao;
        this.insuredPersonDao = insuredPersonDao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("contracts", contractDao.getContracts());
        model.addAttribute("insuredPeople", insuredPersonDao.getInsuredPerson());
        return "contract/contracts";
    }

    @GetMapping("/{number}")
    public String show(@PathVariable("number") int number, Model model){
        model.addAttribute("contract", contractDao.show(number));
        model.addAttribute("list", insuredPersonDao.getInsuredPersonList(number));
        return "contract/contract";
    }
}
