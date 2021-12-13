package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.dao.ContractDao;
import spring.dao.InsuredPersonDao;
import spring.model.Contract;
import spring.service.ContractDBService;
import spring.service.InsuredPersonDBService;

@Controller
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractDBService contractDBService;
    @Autowired
    private InsuredPersonDBService insuredPersonDBService;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("contracts", contractDBService.getContracts());
        model.addAttribute("insuredPeople", insuredPersonDBService.getPeople());
        return "contract/contracts";
    }

    @GetMapping("/{number}")
    public String show(@PathVariable("number") String number, Model model){
        Contract contract = contractDBService.getContract(number);
        model.addAttribute("contract", contract);
        model.addAttribute("list", insuredPersonDBService.getPeopleContract(contract));
        return "contract/contract";
    }
}
