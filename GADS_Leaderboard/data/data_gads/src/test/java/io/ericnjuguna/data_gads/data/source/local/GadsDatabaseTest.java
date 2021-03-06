
package io.ericnjuguna.data_gads.data.source.local;


import android.content.Context;
import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.ericnjuguna.data_gads.data.UserIq;
import io.ericnjuguna.data_gads.data.UserTime;

import static com.google.common.truth.Truth.assertThat;


@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.P})
public class GadsDatabaseTest {

    private GadsDatabase db;

    @Before
   public void init(){
        Context context = ApplicationProvider.getApplicationContext();
        db = GadsDatabase.provideInMemoryDatabase(context);

   }

   @After
   public void reset(){
        db.close();
   }

   @Test
   public void saveUserTimeToDb_ConfirmSaved(){
        // Arrange: create userTime data & save to DB
       UserTime karabo = new UserTime("Karabo Makeba", 19, "South Africa",
               "http://whogobuy.com/store/karabo1.jpg");
       UserTime efe = new UserTime("efe kabasa", 23, "Nigeria",
               "http://whogobuy.com/store/efe101.jpg");

       db.userTimeDao().save(karabo);
       db.userTimeDao().save(efe);

       // Act: get all userTime data from db
       List<UserTime> userHourListResult = db.userTimeDao().getTimeList();

       // Assert:
       assertThat(userHourListResult.isEmpty()).isEqualTo(false);
   }

   @Test
    public void saveUserTime_getAllHours(){
       // Arrange: create userTime data & save to DB
       UserTime karabo = new UserTime("Karabo Makeba", 19, "South Africa",
               "http://whogobuy.com/store/karabo1.jpg");
       db.userTimeDao().save(karabo);

       // Act: get all userTime data from db
       List<UserTime> userHourListResult = db.userTimeDao().getTimeList();

       // Assert: only 1 object in database, confirm expected values
       assertThat(userHourListResult.size()).isEqualTo(1);
       assertThat(userHourListResult.get(0).name).isEqualTo(karabo.name);
       assertThat(userHourListResult.get(0).hours).isEqualTo(karabo.hours);
       assertThat(userHourListResult.get(0).country).isEqualTo(karabo.country);
       assertThat(userHourListResult.get(0).badgeUrl).isEqualTo(karabo.badgeUrl);
   }

    @Test
    public void saveUserIqToDb_ConfirmSaved(){
        // Arrange: create userTime data & save to DB
        UserIq karabo = new UserIq("Karabo Makeba", 19, "South Africa",
                "http://whogobuy.com/store/karabo1.jpg");
        UserIq efe = new UserIq("efe kabasa", 23, "Nigeria",
                "http://whogobuy.com/store/efe101.jpg");

        db.userIQDao().save(karabo);
        db.userIQDao().save(efe);

        // Act: get all userTime data from db
        List<UserIq> userHourListResult = db.userIQDao().getIqList();

        // Assert:
        assertThat(userHourListResult.isEmpty()).isEqualTo(false);
    }

    @Test
    public void saveUserIq_getAllHours(){
        // Arrange: create userTime data & save to DB
        UserIq karabo = new UserIq("Karabo Makeba", 19, "South Africa",
                "http://whogobuy.com/store/karabo1.jpg");
        db.userIQDao().save(karabo);

        // Act: get all userTime data from db
        List<UserIq> userHourListResult = db.userIQDao().getIqList();

        // Assert: only 1 object in database, confirm expected values
        assertThat(userHourListResult.size()).isEqualTo(1);
        assertThat(userHourListResult.get(0).name).isEqualTo(karabo.name);
        assertThat(userHourListResult.get(0).score).isEqualTo(karabo.score);
        assertThat(userHourListResult.get(0).country).isEqualTo(karabo.country);
        assertThat(userHourListResult.get(0).badgeUrl).isEqualTo(karabo.badgeUrl);
    }

    @Test
    public void saveAllUserTimeDb_ConfirmSaved(){
        // Arrange: create userTime data & save to DB
        UserTime karabo = new UserTime("Karabo Makeba", 19, "South Africa",
                "http://whogobuy.com/store/karabo1.jpg");
        UserTime efe = new UserTime("efe kabasa", 23, "Nigeria",
                "http://whogobuy.com/store/efe101.jpg");
        UserTime chika = new UserTime("Chika Okporoko", 27,"Kenya",
                "http://whogobuy.com/store/karabo1.jpg" );
        List<UserTime> users = Stream.of(karabo,efe, chika).collect(Collectors.toList());

        db.userTimeDao().saveAll(users);

        // Act: get all userTime data from db
        List<UserTime> userHourListResult = db.userTimeDao().getTimeList();

        // Assert:
        assertThat(userHourListResult.isEmpty()).isEqualTo(false);
        assertThat(userHourListResult.size()).isEqualTo(3);
    }

    @Test
    public void deleteUserIqDb_ConfirmSaved(){
        // Arrange: create userTime data & save to DB
        UserIq karabo = new UserIq("Karabo Makeba", 19, "South Africa",
                "http://whogobuy.com/store/karabo1.jpg");
        UserIq efe = new UserIq("efe kabasa", 23, "Nigeria",
                "http://whogobuy.com/store/efe101.jpg");

        db.userIQDao().save(karabo);
        db.userIQDao().save(efe);

        // Act: delete all then get all userIq data from db
        db.userIQDao().deleteAll();
        List<UserIq> userIqList = db.userIQDao().getIqList();

        // Assert:
        assertThat(userIqList.isEmpty()).isEqualTo(true);
        assertThat(userIqList.size()).isEqualTo(0);
    }

    @Test
    public void deleteUserTimeDb_ConfirmSaved(){
        // Arrange: create userTime data & save to DB
        UserTime karabo = new UserTime("Karabo Makeba", 19, "South Africa",
                "http://whogobuy.com/store/karabo1.jpg");
        UserTime efe = new UserTime("efe kabasa", 23, "Nigeria",
                "http://whogobuy.com/store/efe101.jpg");
        UserTime chika = new UserTime("Chika Okporoko", 27,"Kenya",
                "http://whogobuy.com/store/karabo1.jpg" );
        List<UserTime> users = Stream.of(karabo,efe, chika).collect(Collectors.toList());

        db.userTimeDao().saveAll(users);

        // Act: delete all then get all userTime data from db
        db.userTimeDao().deleteAll();
        List<UserTime> userHourListResult = db.userTimeDao().getTimeList();

        // Assert:
        assertThat(userHourListResult.isEmpty()).isEqualTo(true);
        assertThat(userHourListResult.size()).isEqualTo(0);
    }
}