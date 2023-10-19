package se.lexicon.workshopjpa.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.workshopjpa.entity.AppUser;

import java.util.Collection;

@Repository
public interface AppUserDao{

    AppUser findById(int id);
    Collection<AppUser> findAll();
    AppUser create(AppUser appUser);
    AppUser update(AppUser appUser);
    void delete(int id);

}
