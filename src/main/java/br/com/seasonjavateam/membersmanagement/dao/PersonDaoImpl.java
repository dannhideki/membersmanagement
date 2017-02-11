package br.com.seasonjavateam.membersmanagement.dao;

import br.com.seasonjavateam.membersmanagement.model.Person;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * This class is the concret class of the AbstractGenericDao implementation
 * @author danielhidekicassi
 */
@Repository
public class PersonDaoImpl extends AbstractGenericDao<Integer, Person> implements PersonDao{

    @Override
    public Person findById(int id) {
        return getById(id);
    }

    @Override
    public void save(Person person) {
        persist(person);
    }

    @Override
    public void deleteByCpf(String cpf) {
        Query query = getSession().createSQLQuery("delete from Person where cpf = :cpf");
        query.setString("cpf", cpf);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Person> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<Person>) criteria.list();
    }

    @Override
    public Person findByCpf(String cpf) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("cpf", cpf));
        return (Person) criteria.uniqueResult();
    }
    
}
