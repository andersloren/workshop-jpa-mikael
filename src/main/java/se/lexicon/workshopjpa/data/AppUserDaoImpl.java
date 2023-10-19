package se.lexicon.workshopjpa.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.lexicon.workshopjpa.entity.AppUser;

import java.util.Collection;

@Repository
public class AppUserDaoImpl implements AppUserDao {

    //fields
    @PersistenceContext
    private EntityManager entityManager;

    //methods
    @Override
    public AppUser findById(int id) {
        return entityManager.find(AppUser.class, id);
    }

    @Override
    public Collection<AppUser> findAll() {
        return entityManager.createQuery("select a from AppUser a", AppUser.class)
                .getResultList();
    }

    @Override
    @Transactional
    public AppUser create(AppUser appUser) {
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser) {
        entityManager.merge(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public void delete(int id) {
        AppUser foundAppuser = entityManager.find(AppUser.class, id);
        if (foundAppuser != null) {
            entityManager.remove(foundAppuser);
        } else throw new NullPointerException("User does not exist.");
    }
}