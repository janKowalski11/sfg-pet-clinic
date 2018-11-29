package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest
{
    private OwnerMapService ownerMapService;


    private final Long ownerId = 1l;
    private final String lastName = "Dick";

    //uruchamia sie beforeeach method :
    @BeforeEach
    void setUp()
    {
        ownerMapService = new OwnerMapService(new PetTypeMapService(),
                new PetMapService());

        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setLastName(lastName);

        ownerMapService.save(owner);
    }

    @Test
    void findAll()
    {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById()
    {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId()
    {
        Owner owner2 = new Owner();
        Long id = 2L;
        owner2.setId(id);

        Owner savedOwner = ownerMapService.save(owner2);
        assertEquals(id, savedOwner.getId());

    }

    @Test
    void savedNoId()
    {
        Owner savedOwner = ownerMapService.save(new Owner());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete()
    {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById()
    {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName()
    {
        Owner smith = ownerMapService.findByLastName(lastName);

        assertNotNull(smith);

        assertEquals(ownerId, smith.getId());
    }

    @Test
    void findByLastNameNotFound()
    {
        Owner smith = ownerMapService.findByLastName("doesn't exist");

        assertNull(smith);
    }
}