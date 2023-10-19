package se.lexicon.workshopjpa.data;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.workshopjpa.entity.AppUser;
import se.lexicon.workshopjpa.entity.Details;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional //What is the purpose on putting this on class layer?
class AppUserDaoImplTest {

    @Autowired
    private AppUserDaoImpl appUserDao;
    @Autowired
    private DetailsDaoImpl detailsDao;

    private AppUser appUser1;
    private AppUser appUser2;
    private AppUser appUser3;
    private AppUser appUser4;

    private Details details;

    @BeforeEach
    public void setup() {
        appUser1 = new AppUser("john", "1234");
        appUser2 = new AppUser("mike", "abc123");
        appUser3 = new AppUser("russel", "SuperSafePassword");
        appUser4 = new AppUser("jason", "MySecretPassword");

        details = new Details("test@email.com", "John Doe", LocalDate.of(1974, 4, 2));
    }

    @Test
    void findById() {
        //Arrange
        appUserDao.create(appUser1);
        appUserDao.create(appUser2);
        appUserDao.create(appUser3);
        appUserDao.create(appUser4);

        //Act
        AppUser foundAppUser1 = appUserDao.findById(appUser1.getAppUserId());
        AppUser foundAppUser2 = appUserDao.findById(appUser2.getAppUserId());
        AppUser foundAppUser3 = appUserDao.findById(appUser3.getAppUserId());
        AppUser foundAppUser4 = appUserDao.findById(appUser4.getAppUserId());

        //Assert
        System.out.println("Expected username: " + appUser1.getUsername());
        System.out.println("Expected username: " + appUser2.getUsername());
        System.out.println("Expected username: " + appUser3.getUsername());
        System.out.println("Expected username: " + appUser4.getUsername());
        System.out.println("Actual username: " + foundAppUser1.getUsername());
        System.out.println("Actual username: " + foundAppUser2.getUsername());
        System.out.println("Actual username: " + foundAppUser3.getUsername());
        System.out.println("Actual username: " + foundAppUser4.getUsername());
        Assertions.assertEquals(appUser1.getUsername(), foundAppUser1.getUsername());
        Assertions.assertEquals(appUser2.getUsername(), foundAppUser2.getUsername());
        Assertions.assertEquals(appUser3.getUsername(), foundAppUser3.getUsername());
        Assertions.assertEquals(appUser4.getUsername(), foundAppUser4.getUsername());
    }

    @Test
    void findAll() {
        //Arrange
        appUserDao.create(appUser1);
        appUserDao.create(appUser2);
        appUserDao.create(appUser3);
        appUserDao.create(appUser4);

        //Act
        List<AppUser> foundAppUsers = new ArrayList<>(appUserDao.findAll());

        //Assert
        System.out.println("There should be 4 elements in the list below: ");
        foundAppUsers.forEach(System.out::println);
        Assertions.assertFalse(foundAppUsers.isEmpty());
    }

    @Test
    void create() {
        //already tested in tests above
    }

    @Test
    void update() {
        //Arrange
        appUserDao.create(appUser1);

        //Act
        appUser1.setDetails(details);
        appUserDao.update(appUser1);
        AppUser foundAppUser = appUserDao.findById(appUser1.getAppUserId());

        //Assert
        System.out.println("Expected Details: " + appUser1.getDetails());
        System.out.println("Actual Details: " + foundAppUser.getDetails());
        Assertions.assertEquals(appUser1.getDetails(), foundAppUser.getDetails());

    }

    @Test
    void delete() {
        //Arrange
        appUserDao.create(appUser1);
        appUserDao.create(appUser2);
        appUserDao.create(appUser3);
        appUserDao.create(appUser4);

        System.out.println("All elements before Delete: ");
        List<AppUser> foundAppUsersBeforeDelete = new ArrayList<>(appUserDao.findAll());
        foundAppUsersBeforeDelete.forEach(System.out::println);

        //Act
        appUserDao.delete(appUser1.getAppUserId());
        List<AppUser> foundAppUsersAfterDelete = new ArrayList<>(appUserDao.findAll());

        //Assert
        System.out.println("All elements after Delete: ");
        foundAppUsersAfterDelete.forEach(System.out::println);

    }
}