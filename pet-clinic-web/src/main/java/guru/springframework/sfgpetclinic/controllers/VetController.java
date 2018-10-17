package guru.springframework.sfgpetclinic.controllers;
/*
Author: BeGieU
Date: 17.10.2018
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController
{
    @RequestMapping({"/vets", "/vets/index", "/vets/index.html"})
    public String listVets()
    {
        return "vets/index";
    }

}
