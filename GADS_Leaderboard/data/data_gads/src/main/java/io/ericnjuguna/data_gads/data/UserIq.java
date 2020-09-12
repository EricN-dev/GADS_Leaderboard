
package io.ericnjuguna.data_gads.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


/**
 *  POJO representing an item of data from API response for IQ
 *  also Entity for Room DB
 */

@Entity( tableName = "io.ericnjuguna.data_gads.data.source.iq_table_name" )
public class UserIq {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo
    @SerializedName("name")
    public String name;

    @ColumnInfo
    @SerializedName("score")
    public int score;

    @ColumnInfo
    @SerializedName("country")
    public String country;

    @ColumnInfo
    @SerializedName("badgeUrl")
    public String badgeUrl;

    public UserIq( String name, int score, String country, String badgeUrl){
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
        this.uid = 0;

    }

    @NonNull
    @Override
    public String toString() {
        return "UserIq("+ "uid: "+ uid + ", name: " + name + ", score: "+ score + ", country: " + country + ", badgeUrl: " +
                badgeUrl +")";
    }
}
