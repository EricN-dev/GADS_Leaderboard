

package io.ericnjuguna.data_gads.data.source.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import io.ericnjuguna.data_gads.data.UserIq;
import io.ericnjuguna.data_gads.data.UserTime;

@Database(entities = {UserIq.class, UserTime.class}, version = 1)
public abstract class GadsDatabase extends RoomDatabase {

    abstract public UserIQDao userIQDao();

    abstract public UserTimeDao userTimeDao();

    public static GadsDatabase providegadsDatabase(Context context){
        return Room.databaseBuilder(
                context.getApplicationContext(),
                GadsDatabase.class,
                "gads_db"
        ).build();
    }

    public static GadsDatabase provideInMemoryDatabase(Context context){
        return Room.inMemoryDatabaseBuilder(
                context, GadsDatabase.class
        ).allowMainThreadQueries().build();
    }
}
