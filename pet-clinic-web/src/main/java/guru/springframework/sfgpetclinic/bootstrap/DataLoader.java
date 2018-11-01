package guru.springframework.sfgpetclinic.bootstrap;
/*
Author: BeGieU
Date: 22.10.2018
*/

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner
{
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService,
                      VetService vetService,
                      PetTypeService petTypeService)
    {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception
    {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType=petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType=petTypeService.save(dog);


        Owner owner1 = new Owner();
        owner1.setFirstName("Bosska");
        owner1.setLastName("Romka");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Oskar");
        owner2.setLastName("PamPam");
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Tadzia");
        vet1.setLastName("Patriotka");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Bonus");
        vet2.setLastName("Erpeka");
        vetService.save(vet2);


    }

}
