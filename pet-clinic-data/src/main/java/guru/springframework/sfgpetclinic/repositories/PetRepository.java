package guru.springframework.sfgpetclinic.repositories;
/*
Author: BeGieU
Date: 14.11.2018
*/

import guru.springframework.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet,Long>
{
}
