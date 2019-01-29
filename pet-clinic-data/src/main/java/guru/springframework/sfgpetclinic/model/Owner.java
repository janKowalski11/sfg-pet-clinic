package guru.springframework.sfgpetclinic.model;
/*
Author: BeGieU
Date: 14.10.2018
*/

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owners")
public class Owner extends Person
{
    private String address;
    private String city;
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public Owner()
    {

    }

    /*returns the pet with the given name or null if none exists for this owner*/
    public Pet getPet(String name, boolean ignoreNew)
    {
        name = name.toLowerCase();

        for (Pet pet : pets)
        {
            //todo: refactor this if
            /*jesli ignoreNew jest ustawione na false to ten if zawsze jest prawdziwy wiec to nic nie zmienia
             * jesli jest ustawwione na true  to wtedy sprawdza czy pet jest nowy  jesli nowy to go ignoruje
             * ptanie po co ?  prwadopodobnie zeby do controllera do funkcji update to uzyc bo jesli aktualizujemy
             * to mozemy zignorowac nowy obiekt ?*/
            if (!ignoreNew || !pet.isNew())
            {
                String compName = pet.getName();
                compName = compName.toLowerCase();

                if (compName.equals(name))
                {
                    return pet;
                }
            }
        }

        return null;
    }

    public Pet getPet(String name)
    {
        return getPet(name, false);
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public Set<Pet> getPets()
    {
        return pets;
    }

    public void setPets(Set<Pet> pets)
    {
        if (pets != null)
            this.pets = pets;
    }

}
