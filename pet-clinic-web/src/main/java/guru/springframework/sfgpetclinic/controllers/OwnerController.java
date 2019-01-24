package guru.springframework.sfgpetclinic.controllers;
/*
Author: BeGieU
Date: 17.10.2018
*/

import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping({"", "/", "/index,", "/index.html"})
    public String listOwners(Model model)
    {
        model.addAttribute("owners", ownerService.findAll());
        System.out.println("loading owners list...");

        return "owners/index";
    }

    @RequestMapping({"/find"})
    public String findOwners()
    {
        return "notimplemented";
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
