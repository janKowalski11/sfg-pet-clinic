package guru.springframework.sfgpetclinic.model;
/*
Author: BeGieU
Date: 16.10.2018
*/

import java.io.Serializable;

public class BaseEntity implements Serializable
{
    //hibernejt rekomenduje uzywanie typow Long zamiast lng itd
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
