package guru.springframework.sfgpetclinic.services;
/*
Author: BeGieU
Date: 16.10.2018
*/

import guru.springframework.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VetService
{
    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
