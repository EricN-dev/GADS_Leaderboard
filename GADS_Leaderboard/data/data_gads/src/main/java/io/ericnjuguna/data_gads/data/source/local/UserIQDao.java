

package io.ericnjuguna.data_gads.data.source.local;


import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import io.ericnjuguna.data_gads.data.UserIq;

@Dao
public abstract class UserIQDao implements BaseDao<UserIq> {

    @Query("SELECT * FROM `io.ericnjuguna.data_gads.data.source.iq_table_name` ORDER BY uid ASC ")
    abstract public List<UserIq> getIqList();

    @Query("DELETE FROM `io.ericnjuguna.data_gads.data.source.iq_table_name`")
    abstract public void deleteAll();
}
