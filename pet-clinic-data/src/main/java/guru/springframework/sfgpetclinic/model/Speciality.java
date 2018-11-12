package guru.springframework.sfgpetclinic.model;
/*
Author: BeGieU
Date: 30.10.2018
*/

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "specialities")
public class Speciality extends BaseEntity
{
    private String description;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
