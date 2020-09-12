

package io.ericnjuguna.data_gads.data;

import com.google.gson.annotations.SerializedName;

public class SubmitResponse {

    @SerializedName("success")
    final String success;

    public SubmitResponse(String success) {
        this.success = success;
    }
}
