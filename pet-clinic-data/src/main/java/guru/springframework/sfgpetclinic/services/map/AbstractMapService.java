package guru.springframework.sfgpetclinic.services.map;
/*
Author: BeGieU
Date: 16.10.2018
*/

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

/*
 *Id jest czyms co dziedziczy z longa, type musi byc czyms co dziedziczy z base entity
 *czyli do nawiasu diamentowego mozna podac tylko typ dziedziczacy z abse entity  i
 *id dziedziczace z longa (Long dziedziczy z Longa, BaseEntity dziedziczy BaseEntity itd)

* Z kolei ze slowem super jest na odwrot jesli by bylo T super BaseEntity
* to znaczy ze T musi byc nadklasa(superklasa) dla base entity lub byc BaseEntityitd

Przyklady:
List<? extends Number> foo3 = new ArrayList<Number>();  // Number "extends" Number (in this context)
List<? extends Number> foo3 = new ArrayList<Integer>(); // Integer extends Number
List<? extends Number> foo3 = new ArrayList<Double>();  // Double extends Number

List<? super Integer> foo3 = new ArrayList<Integer>();  // Integer is a "superclass" of Integer (in this context)
List<? super Integer> foo3 = new ArrayList<Number>();   // Number is a superclass of Integer
List<? super Integer> foo3 = new ArrayList<Object>();   // Object is a superclass of Integer
*/
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long>
{

    protected Map<Long, T> map = new HashMap<>();

    private Long getNextId()
    {
        Long nextId = null;
        try
        {
            nextId = Collections.max(map.keySet()) + 1;
        }
        catch (NoSuchElementException e)
        {
            nextId = 1L;
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
                element -> element.getValue().equals(object));
    }


}
