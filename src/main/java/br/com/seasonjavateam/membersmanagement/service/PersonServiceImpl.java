package br.com.seasonjavateam.membersmanagement.service;

import br.com.seasonjavateam.membersmanagement.dao.PersonDao;
import br.com.seasonjavateam.membersmanagement.model.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is the concret class of the PersonService implementation
 * @author danielhidekicassi
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService{
    
    @Autowired
    private PersonDao personDao;

    @Override
    public Person findById(int id) {
        return personDao.findById(id);
    }

    @Override
    public void save(Person person) {
        personDao.save(person);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends. 
     */
    @Override
    public void update(Person person) {
        Person updatePerson = personDao.findById(person.getId());
        if(updatePerson != null){
            updatePerson.setName(person.getName());
            updatePerson.setCpf(person.getCpf());
        }
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public boolean isPersonUnique(Integer id, String cpf) {
        Person person = personDao.findByCpf(cpf);
        return ( person == null || ((id != null) && (person.getId() == id)));
    }

    @Override
    public Person findByCpf(String cpf) {
        return personDao.findByCpf(cpf);
    }

    @Override
    public void deleteByCpf(String cpf) {
        personDao.deleteByCpf(cpf);
    }
    
}
