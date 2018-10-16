package guru.springframework.sfgpetclinic.services;
/*
Author: BeGieU
Date: 16.10.2018
*/


import guru.springframework.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner,Long>
{
    Owner findByLastName(String lastName);
}
