package guru.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/*
Author: BeGieU
Date: 14.10.2018
*/

/*tylko dziedziczymy z klasy person nie chcemy jej w DB
* bezposrednio*/
@MappedSuperclass
public class Person extends BaseEntity
{
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}
