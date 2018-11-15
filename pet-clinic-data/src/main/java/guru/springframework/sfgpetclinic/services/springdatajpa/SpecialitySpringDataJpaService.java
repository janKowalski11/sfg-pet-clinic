package guru.springframework.sfgpetclinic.services.springdatajpa;
/*
Author: BeGieU
Date: 15.11.2018
*/

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialitySpringDataJpaService implements SpecialtyService
{
    private final SpecialtyRepository specialtyRepository;

    @Autowired
    public SpecialitySpringDataJpaService(SpecialtyRepository specialtyRepository)
    {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Speciality> findAll()
    {
        Set<Speciality> specialities = new HashSet<>();
        for (Speciality speciality : specialtyRepository.findAll())
        {
            specialities.add(speciality);
        }

        return specialities;
    }

    @Override
    public Speciality findById(Long aLong)
    {
        return specialtyRepository.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality object)
    {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Speciality object)
    {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong)
    {
        specialtyRepository.deleteById(aLong);
    }
}
