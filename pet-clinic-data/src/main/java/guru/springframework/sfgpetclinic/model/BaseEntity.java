package guru.springframework.sfgpetclinic.model;
/*
Author: BeGieU
Date: 16.10.2018
*/

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/*sygnalizuje ze z tej klasy bedzie tylko dziedziczone
 * i nie chcemy tego obiektu w zadnych tabelach i
 * A mapped superclass
 * has no separate table defined for it.*/
@MappedSuperclass
public class BaseEntity implements Serializable
{
    //hibernejt rekomenduje uzywanie typow Long zamiast lng itd
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
