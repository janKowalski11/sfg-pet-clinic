package guru.springframework.sfgpetclinic.bootstrap;
/*
Author: BeGieU
Date: 22.10.2018
*/

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner
{
    private final OwnerService ownerService;
    private final VetService vetService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService)
    {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception
    {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Bosska");
        owner1.setLastName("Romka");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Oskar");
        owner2.setLastName("PamPam");
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Tadzia");
        vet1.setLastName("Patriotka");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Bonus");
        vet2.setLastName("Erpeka");
        vetService.save(vet2);



    }
}
