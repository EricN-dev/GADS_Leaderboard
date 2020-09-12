
package io.ericnjuguna.data_gads.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 *  POJO representing an item of data from API response
 *  also Entity for Room DB
 */

@Entity( tableName = "io.ericnjuguna.data_gads.data.source.time_table_name")
public class UserTime {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @SerializedName("name")
    public String name;

    @SerializedName("hours")
    public int hours;

    @SerializedName("country")
    public String country;

    @SerializedName("badgeUrl")
    public String badgeUrl;

    public UserTime(String name, int hours, String country, String badgeUrl){
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
        this.uid = 0;
    }
}
