package br.com.seasonjavateam.membersmanagement.service;

import br.com.seasonjavateam.membersmanagement.model.Person;
import java.util.List;

/**
 * This interface force create minimal methods to CRUD Person
 * @author danielhidekicassi
 */
public interface PersonService {
    
    Person findById(int id);
     
    void save(Person person);
     
    void update(Person person);
     
    void deleteByCpf(String cpf);
 
    List<Person> findAll();
    
    Person findByCpf(String cpf);

    boolean isPersonUnique(Integer id, String cpf);
    
}
