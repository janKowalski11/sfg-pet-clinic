package guru.springframework.sfgpetclinic.controllers;
/*
Author: BeGieU
Date: 17.10.2018
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController
{

    @RequestMapping({"","/","/index,","/index.html"})
    public String Owners()
    {
        return "Owners/index";
    }

}
