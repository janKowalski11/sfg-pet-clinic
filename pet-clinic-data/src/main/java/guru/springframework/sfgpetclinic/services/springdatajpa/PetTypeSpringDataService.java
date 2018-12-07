package guru.springframework.sfgpetclinic.services.springdatajpa;
/*
Author: BeGieU
Date: 15.11.2018
*/

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetTypeSpringDataService implements PetTypeService
{
    private final PetTypeRepository petTypeRepository;

    @Autowired
    public PetTypeSpringDataService(PetTypeRepository petTypeRepository)
    {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll()
    {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);

        return petTypes;
    }

    @Override
    public PetType findById(Long aLong)
    {
        return petTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public PetType save(PetType object)
    {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object)
    {
        petTypeRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong)
    {
        petTypeRepository.deleteById(aLong);
    }
}