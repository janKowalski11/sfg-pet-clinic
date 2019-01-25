package guru.springframework.sfgpetclinic.services;
/*
Author: BeGieU
Date: 16.10.2018
*/


import guru.springframework.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long>
{
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);



}
