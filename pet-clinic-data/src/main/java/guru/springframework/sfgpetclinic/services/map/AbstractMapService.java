package guru.springframework.sfgpetclinic.services.map;
/*
Author: BeGieU
Date: 16.10.2018
*/

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

//Id jest czyms co dziedziczy z longa, type musi byc czyms co dziedziczy z base entity
//czyli do nawiasu diamentowego mozna podac tylko typ dziedziczacy z abse entity  i
//id dziedziczace z longa
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long>
{

    protected Map<Long, T> map = new HashMap<>();

    private Long getNextId()
    {
        Long nextId = null;
        try
        {
            nextId=Collections.max(map.keySet()) + 1;
        }
        catch (NoSuchElementException e)
        {
            nextId=1L;
        }

        return nextId;
    }


    Set<T> findAll()
    {
        return new HashSet<>(map.values());
    }


    T findById(ID id)
    {
        return map.get(id);
    }

    T save(T object)
    {
        if (object != null)
        {
            if (object.getId() == null)
            {
                object.setId(this.getNextId());
            }
            map.put(object.getId(), object);
        }
        else
        {
            throw new RuntimeException("Object cannot be null");
        }


        return object;
    }

    void deleteById(ID id)
    {
        map.remove(id);
    }

    void delete(T object)
    {
        map.entrySet().removeIf(
                entry -> entry.getValue().equals(object));
    }


}
