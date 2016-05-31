package pl.bookstore.robot.pojo;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by damian on 5/11/16.
 */
public class ProfileTest {

    @DataProvider(name = "profilesProvider")
    public Object[][] getProfiles() throws NoCategorySelectedException {
        Profile profile = ProfileBuilder.build("DamianCategory");
        Profile profileBS1 = ProfileBuilder.build("DamianCategory");
        Profile profileBS2 = ProfileBuilder.build("DamianCategory");
        profileBS1.setBookStore(new BookStore("BS1"));
        profileBS2.setBookStore(new BookStore("BS2"));

        Profile profileBS11 = ProfileBuilder.build("DamianCategory");
        Profile profileBS22 = ProfileBuilder.build("DamianCategory");
        profileBS11.setBookStore(null);
        profileBS22.setBookStore(new BookStore("BS2"));

        Object[][] profileTable = {
                {new Profile(), new Profile(), true},
                {ProfileBuilder.build("Comedy"), ProfileBuilder.build("Comedy"), true},
                {ProfileBuilder.build("Comedy1"), ProfileBuilder.build("Comedy"), false},
                {ProfileBuilder.build("Comedy"), null, false},
                {ProfileBuilder.build("Comedy"), new BookStore() , false},
                {profile, profile, true},
                {profileBS1, profileBS2, false},
                {profileBS11, profileBS22, false},
        };
        return profileTable;
    }

    @Test(dataProvider = "profilesProvider")
    public void testIfProfileCategoriesAreEqualProfilesAreEqual(Profile firstProfile, Profile secondProfile, Boolean equal) throws NoCategorySelectedException {
        //when
        boolean areEqual = firstProfile.equals(secondProfile);

        //then
        Assertions.assertThat(areEqual).isEqualTo(equal);
    }

    @Test(dataProvider = "profilesProvider")
    public void testContractBetweenEqualAndHashCode(Profile firstProfile, Profile secondProfile, Boolean equal) {
        //when
        if (firstProfile != null && secondProfile != null) {
            int firstProfileHashCode = firstProfile.hashCode();
            int secondProfileHashCode = secondProfile.hashCode();
            boolean isEqual = (firstProfileHashCode == secondProfileHashCode);

            Assertions.assertThat(isEqual).isEqualTo(equal);
        }
    }

}

