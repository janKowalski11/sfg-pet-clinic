package guru.springframework.sfgpetclinic.bootstrap;
/*
Author: BeGieU
Date: 22.10.2018
*/

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
        owner1.setAddress("ul.Podmostowa 997");
        owner1.setCity("Krakow");
        owner1.setTelephone("997997997");
        ownerService.save(owner1);

        Pet bosskasPet=new Pet();
        bosskasPet.setPetType(savedDogPetType);
        bosskasPet.setOwner(owner1);
        bosskasPet.setBirthDate(LocalDate.now());
        bosskasPet.setName("pomidor");
        owner1.getPets().add(bosskasPet);

        Owner owner2 = new Owner();
        owner2.setFirstName("Oskar");
        owner2.setLastName("PamPam");
        owner2.setAddress("ul.Konfidentow 60");
        owner2.setCity("Czestohowa");
        owner2.setTelephone("606060606");
        ownerService.save(owner2);

        Pet oskarsPet=new Pet();
        oskarsPet.setPetType(savedCatPetType);
        oskarsPet.setOwner(owner2);
        oskarsPet.setBirthDate(LocalDate.now());
        oskarsPet.setName("tadzia");
        owner2.getPets().add(oskarsPet);

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
