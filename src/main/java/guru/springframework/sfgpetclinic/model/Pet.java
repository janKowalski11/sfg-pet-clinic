package guru.springframework.sfgpetclinic.model;
/*
Author: BeGieU
<<<<<<< HEAD
Date: 11.10.2018
=======
Date: 14.10.2018
>>>>>>> 01- Adding POJOs - creating Basic Model
*/

import java.time.LocalDate;

public class Pet
{
    private PetType petType;
    private Owner owner;
    private LocalDate birthDate;

    public PetType getPetType()
    {
        return petType;
    }

    public void setPetType(PetType petType)
    {
        this.petType = petType;
    }

    public Owner getOwner()
    {
        return owner;
    }

    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }
}
