package br.com.seasonjavateam.membersmanagement.dao;

import br.com.seasonjavateam.membersmanagement.model.Person;
import java.util.List;

/**
 *  This interface force create minimal methods to CRUD Person
 * @author danielhidekicassi
 */
public interface PersonDao {
    
    Person findById(int id);
 
    void save(Person person);
     
    void deleteByCpf(String cpf);
     
    List<Person> findAll();

    public Person findByCpf(String cpf);
    
}
