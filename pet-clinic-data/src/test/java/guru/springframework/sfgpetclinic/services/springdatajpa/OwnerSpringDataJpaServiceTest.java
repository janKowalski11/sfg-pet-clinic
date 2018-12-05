package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSpringDataJpaServiceTest
{
    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSpringDataJpaService ownerService;


    @BeforeEach
    void setUp()
    {

    }

    @Test
    void findByLastName()
    {
        Owner returnedOwner = new Owner();
        returnedOwner.setId(1L);
        returnedOwner.setLastName(LAST_NAME);

        when(ownerRepository.findByLastName(LAST_NAME)).thenReturn(returnedOwner);

        Owner smith = ownerService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());
    }

    @Test
    void findAll()
    {
        Set<Owner> returnOwners = new HashSet<>();
        returnOwners.add(new Owner());

        when(ownerRepository.findAll()).thenReturn(returnOwners);

        Set<Owner> owners = ownerService.findAll();
        assertEquals(1, owners.size());


    }

    @Test
    void findById()
    {
    }

    @Test
    void save()
    {
    }

    @Test
    void delete()
    {
    }

    @Test
    void deleteById()
    {
    }
}