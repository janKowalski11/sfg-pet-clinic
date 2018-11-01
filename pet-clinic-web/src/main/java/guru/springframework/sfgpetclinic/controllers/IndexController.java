package guru.springframework.sfgpetclinic.controllers;
/*
Author: BeGieU
Date: 17.10.2018
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
    @RequestMapping({"","/","index","index.html"})
    public String index()
    {
        return "index";
    }

    @RequestMapping({"/oups"})
    public String errorLapHandler()
    {
        return "notimplemented";
    }
}
