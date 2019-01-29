package guru.springframework.sfgpetclinic.controllers;
/*
Author: BeGieU
Date: 29.01.2019
*/

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController
{
    private static final String VIEW_PET_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    @Autowired
    public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService)
    {
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder)
    {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("types") //types refers to petType
    public Collection<PetType> populatePetTypes()
    {
        return petTypeService.findAll();
    }

    /*ta metoda poprostu dodaje ownera do modelu */
    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId)
    {
        return ownerService.findById(ownerId);
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model)
    {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        model.addAttribute("pet", pet);

        return VIEW_PET_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet,
                                      BindingResult bindingResult, Model model)
    {
        /*
        jesli pet ma imię, i pet jest nowy, i pet jest dodany do ownera i ponad to nie jest nowy czyli ma id
        wtedy to duplikat i nie dodajemy go ponownie
        */
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null)
        {
            bindingResult.rejectValue("name", "duplicate", "already exists");
        }

        owner.getPets().add(pet);
        if (bindingResult.hasErrors())
        {
            model.addAttribute("pet", pet);
            return VIEW_PET_CREATE_OR_UPDATE_FORM;
        }
        else
        {
            petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }
    }


    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable("petId") Long petId, Model model)
    {
        model.addAttribute("pet", petService.findById(petId));
        return VIEW_PET_CREATE_OR_UPDATE_FORM;
    }


    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult bindingResult,
                                    Owner owner, Model model)
    {
        if (bindingResult.hasErrors())
        {
            pet.setOwner(owner);
            model.addAttribute(owner);
            model.addAttribute("pet", pet);
            return VIEW_PET_CREATE_OR_UPDATE_FORM;
        }
        else
        {
            owner.getPets().add(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId() ;
        }
    }
}
