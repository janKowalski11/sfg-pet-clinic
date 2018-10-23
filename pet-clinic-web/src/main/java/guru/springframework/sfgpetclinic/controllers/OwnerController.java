package guru.springframework.sfgpetclinic.controllers;
/*
Author: BeGieU
Date: 17.10.2018
*/

import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping({"", "/", "/index,", "/index.html"})
    public String Owners(Model model)
    {
        model.addAttribute("owners", ownerService.findAll());




        return "Owners/index";
    }

}
