package guru.springframework.sfgpetclinic.bootstrap;
/*
Author: BeGieU
Date: 22.10.2018
*/

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
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
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, SpecialtyService specialtyService,
                      VisitService visitService)
    {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    private void loadData()
    {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(dog);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialtyService.save(dentistry);


        Owner owner1 = new Owner();
        owner1.setFirstName("Bosska");
        owner1.setLastName("Romka");
        owner1.setAddress("ul.Podmostowa 997");
        owner1.setCity("Krakow");
        owner1.setTelephone("997997997");


        Pet bosskasPet = new Pet();
        bosskasPet.setPetType(savedDogPetType);
        bosskasPet.setOwner(owner1);
        bosskasPet.setBirthDate(LocalDate.now());
        bosskasPet.setName("pomidor");
        owner1.getPets().add(bosskasPet);

        ownerService.save(owner1);


        Owner owner2 = new Owner();
        owner2.setFirstName("Oskar");
        owner2.setLastName("PamPam");
        owner2.setAddress("ul.Konfidentow 60");
        owner2.setCity("Czestohowa");
        owner2.setTelephone("606060606");
        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(bosskasPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Prawilny kocur z ulicy");

        /*
         *UWAGA: miejsce w ktorym znajduje sie zapis do repo nie przypadkowy
         * metoda repo.save() KOPIUJE obiekt do bazy danych:
         * W TAKIM STANIE JAKIM OBJEKT JEST W MIEJSCU WYWYOLANIA LINII.
         * tzn ze jesli uzyyli bysmy sava kilka linii wyzej to kilka pol moglo by byc null
         * czy w jakim obiekcie id moze byc rowne null co mialo miejsce wczesniej*/
        visitService.save(catVisit);

        Pet oskarsPet = new Pet();
        oskarsPet.setPetType(savedCatPetType);
        oskarsPet.setOwner(owner2);
        oskarsPet.setBirthDate(LocalDate.now());
        oskarsPet.setName("tadzia");
        owner2.getPets().add(oskarsPet);

        Vet vet1 = new Vet();
        vet1.setFirstName("Tadzia");
        vet1.setLastName("Patriotka");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Bonus");
        vet2.setLastName("Erpeka");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);
    }

    @Override
    public void run(String... args) throws Exception
    {
        int count = petTypeService.findAll().size();
        if (count == 0)
            loadData();
    }

}
