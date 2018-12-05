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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        Mockito.when(ownerRepository.findByLastName(LAST_NAME)).thenReturn(returnedOwner);

        Owner smith = ownerService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME,smith.getLastName());
    }

    @Test
    void findAll()
    {
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