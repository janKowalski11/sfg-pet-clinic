package guru.springframework.sfgpetclinic.controllers;
/*
Author: BeGieU
Date: 17.10.2018
*/

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController
{
    private final OwnerService ownerService;

    /*
     * Elegancko wyswietla strona rzeczy z naszego ownerservice
     * bo Scope jest ustawiony na singleton czyli
     * ten owner service tutaj jest tym samym co w naszym Dataloaderze
     * wiec to co dodane w data loaderze znajduje sie takze
     * w tym owner service :)*/

    @Autowired
    public OwnerController(OwnerService ownerService)
    {
        this.ownerService = ownerService;
    }

    /*Zabezpieczenie ktore sprawia ze nie ma mozliwosci wykonac metody @Post
     * na polu Id. Czyli uzytkownik strony nie moze ustawic w zaden
     * sposob id obiektu wysylanego do DB. Tylko DB moze zmieniac id*/
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder)
    {
        dataBinder.setDisallowedFields("id");
    }

    //todo: make this available for admin use only !
    @GetMapping("/allOwners")
    public String listOwners(Model model)
    {
        model.addAttribute("owners", ownerService.findAll());
        System.out.println("loading owners list...");

        return "owners/index";
    }

    @RequestMapping({"/find"})
    public String findOwners(Model model)
    {
        model.addAttribute("owner", new Owner());
        return "owners/findOwners";
    }


    /* BindingResult holds the result of the validation and binding and contains errors that may have occurred. */
    @GetMapping("/")
    public String processFindForm(@ModelAttribute("owner") Owner owner, BindingResult bindingResult, Model model)
    {
        // if owner is empty then return all entities
        if (owner.getLastName() == null)
        {
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName()+"%");
        if (results.isEmpty())
        {
            bindingResult.rejectValue("lastName", "notFound", "notFound");
            return "owners/findOwners";
        }
        else if (results.size() == 1)
        {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }
        else
        {
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }


    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId)
    {
        //adding view
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");

        modelAndView.addObject("owner", ownerService.findById(ownerId));
        return modelAndView;

    }


}
