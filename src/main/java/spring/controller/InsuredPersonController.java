package spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.dao.InsuredPersonDao;
import spring.model.InsuredPerson;

import java.io.StringReader;

@Controller
@RequestMapping("/insuredPerson")
public class InsuredPersonController {

    private final InsuredPersonDao insuredPersonDao;

    @Autowired
    public InsuredPersonController(InsuredPersonDao insuredPersonDao){
        this.insuredPersonDao = insuredPersonDao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", insuredPersonDao.getInsuredPerson());
        return "insuredPerson/people";
    }

    @GetMapping("/{INN}")
    public String show(@PathVariable("INN") String INN, Model model){
        model.addAttribute("person", insuredPersonDao.show(INN));
        return "insuredPerson/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") InsuredPerson insuredPerson){
        return "insuredPerson/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") InsuredPerson insuredPerson){
        insuredPersonDao.save(insuredPerson);
        return "redirect:/insuredPerson";
    }

    @GetMapping("/{INN}/edit")
    public String edit(Model model, @PathVariable("INN") String INN){
        model.addAttribute("person", insuredPersonDao.show(INN));
        return "insuredPerson/edit";
    }

    @PatchMapping("/{INN}")
    public String update(@ModelAttribute("person") InsuredPerson person, @PathVariable("INN") String INN){
        insuredPersonDao.update(INN, person);
        return "redirect:/insuredPerson";
    }

    @DeleteMapping("/{INN}")
    public String delete(@PathVariable("INN") String INN){
        insuredPersonDao.delete(INN);
        return "redirect:/insuredPerson";
    }
}
