package guru.springframework.sfgpetclinic.controllers;
/*
Author: BeGieU
Date: 30.01.2019
*/

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

@Controller
public class VisitController
{
    private final PetService petService;
    private final VisitService visitService;

    public VisitController(PetService petService, VisitService visitService)
    {
        this.petService = petService;
        this.visitService = visitService;
    }

    @InitBinder
    public void setDisallowedFields(WebDataBinder dataBinder)
    {
        dataBinder.setDisallowedFields("id");

        //definiuje przypisanie wartosci dla localData
        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text) throws IllegalArgumentException
            {
                setValue(LocalDate.parse(text));
            }
        });
    }

    /*@ModelAttribute is going to run with every request against
     * this controller..Called before each and every @RequestMapping annotated method.
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use the session scope, make sure that Pet object always has an id
     * (Even though id is not part of the form fields)*/
    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Model model)
    {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);

        Visit visit = new Visit();

        //satisfying the bidirectional connection
        pet.getVisits().add(visit);
        visit.setPet(pet);

        return visit;
    }

    @GetMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") Long petId, Model model)
    {
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "pets/createOrUpdateVisitForm";
        }
        else
        {
            visitService.save(visit);

            return "redirect:/owners/{ownerId}";
        }
    }
}
