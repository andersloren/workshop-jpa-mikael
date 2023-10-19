package se.lexicon.workshopjpa.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.lexicon.workshopjpa.entity.Details;

import java.util.Collection;

@Repository
public class DetailsDaoImpl implements DetailsDao {

    //fields
    @PersistenceContext
    private EntityManager entityManager;

    //methods
    @Override
    public Details findById(int id) {
        return entityManager.find(Details.class, id);
    }

    @Override
    public Collection<Details> findAll() {
        return entityManager.createQuery("select d from Details d", Details.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Details create(Details details) {
        entityManager.persist(details);
        return details;
    }

    @Override
    @Transactional
    public Details update(Details details) {
        entityManager.merge(details);
        return details;
    }

    @Override
    @Transactional
    public void delete(int id) {
        Details foundDetails = entityManager.find(Details.class, id);
        if (foundDetails != null) {
            entityManager.remove(foundDetails);
        } else throw new NullPointerException("Details does not exist!");
    }
}
