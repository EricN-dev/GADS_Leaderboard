
package io.ericnjuguna.data_gads.data;

import java.util.List;
import java.util.concurrent.Executor;

/**
 *  Blueprint for repository
 */

public interface GadsRepository {

    /**
     *  make API call for {@link UserTime} list , update Database
     */
    void updateUserHoursFromApi();

    /**
     *  make API call for {@link UserIq} list , update Database
     */
    void updateUserIqFromApi();

    /**
     *  clears database first then save data from API
     * @param userIqList updated data from API
     */
    void updateUserIqDb(List<UserIq> userIqList);

    /**
     * clears database first then save data from API
     * @param userTimeList updated data from API
     */
    void updateUserTimeDb(List<UserTime> userTimeList);

    /**
     * @return list of {@link UserTime} from database
     */
    List<UserTime> getUserHours();

    /**
     * @return list of {@link UserIq} from database
     */
    List<UserIq> getSkillIqs();

    /**
     *
     * @return io.ericnjuguna.data_gads.background.BackGroundExecutor for
     * running background tasks
     */
    Executor getExecutor();

    /**
     *
     * @param listener listener for repository
     */
    void setUpdateActionListener(DefaultRepository.UpdateDataActionListener listener);

}
