package guru.springframework.sfgpetclinic.services;
/*
Author: BeGieU
Date: 16.10.2018
*/

import guru.springframework.sfgpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService
{
    Owner findByLastName(String lastName);

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
