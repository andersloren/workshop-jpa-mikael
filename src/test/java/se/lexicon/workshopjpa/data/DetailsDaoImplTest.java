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
class DetailsDaoImplTest {

    @Autowired
    private AppUserDaoImpl appUserDao;
    @Autowired
    private DetailsDaoImpl detailsDao;

    private AppUser appUser1;
    private AppUser appUser2;
    private AppUser appUser3;
    private AppUser appUser4;

    private Details details1;
    private Details details2;
    private Details details3;
    private Details details4;

    @BeforeEach
    public void setup() {
        appUser1 = new AppUser("john", "1234");
        appUser2 = new AppUser("mike", "abc123");
        appUser3 = new AppUser("russel", "SuperSafePassword");
        appUser4 = new AppUser("jason", "MySecretPassword");

        details1 = new Details("john@petrucci.com", "john petrucci", LocalDate.of(1974, 4, 2));
        details2 = new Details("mike@portnoy.com", "mike portnoy", LocalDate.of(1966, 5, 13));
        details3 = new Details("russel@allen.com", "russel allen", LocalDate.of(1980, 1, 7));
        details4 = new Details("jason@rullo.com", "jason rullo", LocalDate.of(1971, 11, 26));
    }

    @Test
    void findById() {
        //Arrange
        appUserDao.create(appUser1);
        appUserDao.create(appUser2);
        appUserDao.create(appUser3);
        appUserDao.create(appUser4);

        detailsDao.create(details1);
        detailsDao.create(details2);
        detailsDao.create(details3);
        detailsDao.create(details4);

        //Act
        appUser1.setDetails(details1);
        appUser2.setDetails(details2);
        appUser3.setDetails(details3);
        appUser4.setDetails(details4);

        AppUser foundAppUser1 = appUserDao.findById(appUser1.getAppUserId());
        AppUser foundAppUser2 = appUserDao.findById(appUser2.getAppUserId());
        AppUser foundAppUser3 = appUserDao.findById(appUser3.getAppUserId());
        AppUser foundAppUser4 = appUserDao.findById(appUser4.getAppUserId());

        //Assert
        System.out.println("Expected details: " + appUser1.getDetails().getDetailsId());
        System.out.println("Expected details: " + appUser2.getDetails().getDetailsId());
        System.out.println("Expected details: " + appUser3.getDetails().getDetailsId());
        System.out.println("Expected details: " + appUser4.getDetails().getDetailsId());
        System.out.println("Actual username: " + foundAppUser1.getDetails().getDetailsId());
        System.out.println("Actual username: " + foundAppUser2.getDetails().getDetailsId());
        System.out.println("Actual username: " + foundAppUser3.getDetails().getDetailsId());
        System.out.println("Actual username: " + foundAppUser4.getDetails().getDetailsId());
        Assertions.assertEquals(details1.getDetailsId(), foundAppUser1.getDetails().getDetailsId());
        Assertions.assertEquals(details2.getDetailsId(), foundAppUser2.getDetails().getDetailsId());
        Assertions.assertEquals(details3.getDetailsId(), foundAppUser3.getDetails().getDetailsId());
        Assertions.assertEquals(details4.getDetailsId(), foundAppUser4.getDetails().getDetailsId());
    }

    @Test
    void findAll() {
        //Arrange
        detailsDao.create(details1);
        detailsDao.create(details2);
        detailsDao.create(details3);
        detailsDao.create(details4);

        //Act
        List<Details> foundDetails = new ArrayList<>(detailsDao.findAll());

        //Assert
        System.out.println("There should be 4 elements in the list below: ");
        foundDetails.forEach(System.out::println);
        Assertions.assertFalse(foundDetails.isEmpty());
    }

    @Test
    void create() {
        //already tested in tests above
    }

    @Test
    void update() {
        //Arrange
        detailsDao.create(details1);
        detailsDao.create(details2);

        //Act
        System.out.println("Old Details name: " + details1.getName());

        String newName = "New Name";

        System.out.println("Expected Updated Details name: " + newName);

        details1.setName(newName);
        detailsDao.update(details1);

        Details foundDetails = detailsDao.findById(details1.getDetailsId());

        //Assert
        System.out.println("Actual Updated Details name: " + foundDetails.getName());
        Assertions.assertEquals(newName, foundDetails.getName());

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