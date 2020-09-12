

package io.ericnjuguna.data_gads.background;

import java.util.concurrent.Executor;

/**
 *  Executor for background tasks
 */
public class BackGroundExecutor implements Executor {

    @Override
    public void execute(Runnable runnable) {
        new Thread(runnable).start();
    }
}
