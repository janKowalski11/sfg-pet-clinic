package guru.springframework.sfgpetclinic.model;
/*
Author: BeGieU
Date: 14.10.2018
*/

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vets")
public class Vet extends Person
{
    //laduje wszystko od razu, a nie kiedy musi
    //prawdopodobnie wtedy pewne specjalizacje byly by nullem
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Speciality> specialities;

    public Vet()
    {
        this.specialities = new HashSet<>();
    }

    public Set<Speciality> getSpecialities()
    {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities)
    {
        this.specialities = specialities;
    }


}
