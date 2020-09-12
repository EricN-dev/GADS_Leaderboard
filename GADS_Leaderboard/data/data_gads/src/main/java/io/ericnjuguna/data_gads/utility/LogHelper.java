

package io.ericnjuguna.data_gads.utility;

import android.util.Log;

import io.ericnjuguna.data_gads.BuildConfig;

/**
 *  helper class to log only in debug mode
 */

public class LogHelper {

    private LogHelper(){}

    public static void log(String tag, String message){
        if(BuildConfig.DEBUG)
            Log.d(tag,message);
    }
}
