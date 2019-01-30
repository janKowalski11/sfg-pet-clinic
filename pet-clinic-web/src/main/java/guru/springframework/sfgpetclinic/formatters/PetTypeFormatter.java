package guru.springframework.sfgpetclinic.formatters;
/*
Author: BeGieU
Date: 30.01.2019
*/

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

/*Spring Mvc uses this component when it comes to parsing PetType*/
@Component
public class PetTypeFormatter implements Formatter<PetType>
{

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService)
    {
        this.petTypeService = petTypeService;
    }


    @Override
    public String print(PetType petType, Locale locale)
    {
        return petType.getName();
    }

    //metoda dostaje petType jako string i dopaowuje go do PetType
    @Override
    public PetType parse(String text, Locale locale) throws ParseException
    {
        //todo: refactor whole method to use petTypeService.findByName(name)
        /*inny lepszy sposob na implementacje tej calej klasy to petTypeService.findByName(name) !!!*/
        Collection<PetType> findPetTypes = petTypeService.findAll();


        for (PetType petType : findPetTypes)
        {
            if (petType.getName().equals(text))
            {
                return petType;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }
}
