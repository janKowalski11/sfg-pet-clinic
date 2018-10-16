package guru.springframework.sfgpetclinic.services;
/*
Author: BeGieU
Date: 16.10.2018
*/

import guru.springframework.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService
{
    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
