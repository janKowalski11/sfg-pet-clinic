package guru.springframework.sfgpetclinic.services;
/*
Author: BeGieU
Date: 16.10.2018
*/

import java.util.Set;

public interface CrudService<T, ID>
{
    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);


}
