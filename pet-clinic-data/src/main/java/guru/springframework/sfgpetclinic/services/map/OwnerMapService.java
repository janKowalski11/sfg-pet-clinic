package guru.springframework.sfgpetclinic.services.map;
/*
Author: BeGieU
Date: 16.10.2018
*/


import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService
{
    private final PetTypeService petTypeService;
    private final PetService petService;

    @Autowired
    public OwnerMapService(PetTypeService petTypeService, PetService petService)
    {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll()
    {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id)
    {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner)
    {
        if (owner != null)
        {
            if (owner.getPets() != null)
            {
                owner.getPets().forEach(pet ->
                {
                    if (pet.getPetType() != null)
                    {
                        if (pet.getPetType().getId() == null)
                        {
                            /*
                             * klasy typu ...MapService dziedzicza po Abstract Map service
                             * to znaczy ze jesli zostaly dodane do setu( zostaly zmapowane)
                             * to maja id bo metda save im zawsze przydziela.
                             * z teg wniosek zejesli pet.getPetType().getId()==null
                             * czyli pet type id jest null to nie ma go w secie wiec
                             * trzeba go dodac, co robi linijka ponizej. ESSA
                             * */
                            PetType savedPetType = petTypeService.save(pet.getPetType());
                            /*
                             * ta linia prawdopodobnie nie potrzebna dodana poto
                             * zeby sie upewnic ze  pet ma takisam pet type jak
                             * w pet service
                             * */
                            pet.setPetType(savedPetType);
                        }
                    }
                    else
                    {
                        throw new RuntimeException("Pet type is required");
                    }

                    if (pet.getId() == null)
                    {
                        /*analogiczna sytuacja jak w ifie powyzej */
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }

                });
            }
            return super.save(owner);
        }
        else
        {
            return null;
        }
    }

    @Override
    public void delete(Owner object)
    {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName)
    {
        return
                this.findAll()
                        .stream()
                        .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                        .findFirst()
                        .orElse(null);


    }
}
