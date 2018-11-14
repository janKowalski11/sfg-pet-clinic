package guru.springframework.sfgpetclinic.repositories;
/*
Author: BeGieU
Date: 14.11.2018
*/

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner,Long>
{
}
