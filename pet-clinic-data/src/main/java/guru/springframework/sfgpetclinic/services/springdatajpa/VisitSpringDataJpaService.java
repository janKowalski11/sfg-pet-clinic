package guru.springframework.sfgpetclinic.services.springdatajpa;
/*
Author: BeGieU
Date: 17.11.2018
*/

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitSpringDataJpaService implements VisitService
{
    private final VisitRepository visitRepository;

    @Autowired
    public VisitSpringDataJpaService(VisitRepository visitRepository)
    {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll()
    {
        Set<Visit> visits = new HashSet<>();
        //visitRepository.findAll().forEach(visit -> visits.add(visit));
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long aLong)
    {
        return visitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Visit save(Visit object)
    {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object)
    {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong)
    {
        visitRepository.deleteById(aLong);
    }
}
