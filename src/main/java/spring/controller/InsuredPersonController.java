package spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.dao.InsuredPersonDao;
import spring.model.InsuredPerson;
import spring.service.InsuredPersonDBService;

import javax.validation.Valid;
import java.io.StringReader;

@Controller
@RequestMapping("/insuredPerson")
public class InsuredPersonController {

    @Autowired
    private InsuredPersonDBService insuredPersonDBService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", insuredPersonDBService.getPeople());
        return "insuredPerson/people";
    }

    @GetMapping("/{inn}")
    public String show(@PathVariable("inn") String INN, Model model) {
        model.addAttribute("person", insuredPersonDBService.getPerson(INN));
        return "insuredPerson/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") InsuredPerson insuredPerson) {
        return "insuredPerson/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid InsuredPerson insuredPerson, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "insuredPerson/new";
        }
        insuredPersonDBService.add(insuredPerson);
        return "redirect:/insuredPerson";
    }

    @GetMapping("/{inn}/edit")
    public String edit(@PathVariable("inn") String INN, Model model) {
        model.addAttribute("person", insuredPersonDBService.getPerson(INN));
        return "insuredPerson/edit";
    }

    @PatchMapping("/{inn}")
    public String update(@ModelAttribute("person") @Valid InsuredPerson person,BindingResult result, @PathVariable("inn") String INN) {
        if (result.hasErrors()){
            return "insuredPerson/edit";
        }
        insuredPersonDBService.save(person);
        return "redirect:/insuredPerson";
    }

    @DeleteMapping("/{inn}")
    public String delete(@PathVariable("inn") String INN) {
        insuredPersonDBService.remove(INN);
        return "redirect:/insuredPerson";
    }
}
