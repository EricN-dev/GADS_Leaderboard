

package io.ericnjuguna.data_gads.data.source.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.List;

/**
 *  BaseDao for Room Db
 */

@Dao
public interface BaseDao<T> {

    /**
     *  save entity in database
     * @param obj class to be saved
     */
    @Insert
    void save(T obj);

    /**
     *  save list of entity in database
     * @param data list to be saved
     */
    @Insert
    void saveAll(List<T> data);

    /**
     *  update entity in database
     * @param obj class to be updated
     * @return the number of entity updated
     */
    @Update
    int update(T obj);

    /**
     * delete an object from database
     * @param obj object to be deleted
     */
    @Delete
    void delete(T obj);

}
