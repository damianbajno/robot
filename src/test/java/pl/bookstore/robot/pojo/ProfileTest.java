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

        Object[][] profileTable = {
                {new Profile(), new Profile(), true},
                {ProfileBuilder.build("Comedy"), ProfileBuilder.build("Comedy"), true},
                {ProfileBuilder.build("Comedy1"), ProfileBuilder.build("Comedy"), false},
                {ProfileBuilder.build("Comedy"), null, false},
                {profile, profile, true},
        };
        return profileTable;
    }

    @Test(dataProvider = "profilesProvider")
    public void testProfileEqualMethod(Profile firstProfile, Profile secondProfile, Boolean equal) throws NoCategorySelectedException {
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

