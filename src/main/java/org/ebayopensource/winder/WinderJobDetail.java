package org.ebayopensource.winder;

import org.ebayopensource.common.util.Parameters;

import java.util.List;

/**
 * Winder Job Detail, it has all the information of the job
 *
 *

 *
 * @author Sheldon Shao xshao@ebay.com on 10/12/16.
 * @version 1.0
 */
public interface WinderJobDetail<TI extends TaskInput, TR extends TaskResult> {

    /**
     * Return current jobId
     *
     * @return
     */
    JobId getJobId();

    /**
     * Return parent jobid
     *
     * @return
     */
    JobId getParentJobId();

    /**
     * return child jobIds if there is any.
     *
     * @return null when there is no child job id.
     */
    JobId[] getChildJobIds();

    /**
     * Add new child job id
     *
     * @param jobId
     */
    void addChildJobIds(JobId jobId);

    /**
     * JobIds
     *
     * @param jobIds
     */
    void setChildJobIds(JobId[] jobIds);

    String getDescription();

    boolean isDurable();

    boolean isPersistJobDataAfterExecution();

    boolean isConcurrentExectionDisallowed();

    boolean requestsRecovery();

    /**
     * Auto Pause flag is to make the job pause when it finished part of job
     *
     * @return is AutoPause flag set
     */
    boolean isAutoPause();

    /**
     * Set Auto Pause flag
     *
     * @param autoPause Auto Pause flag
     */
    void setAutoPause(boolean autoPause);

    String getEndDate();

    void setEndDate(String date);

    StatusEnum getStatus();

    void setStatus(StatusEnum status);


    TI getInput();


    TR getResult();

    /**
     * Return all user actions
     *
     * @return all user actions, if there is no UserAction, it returns Collections.EMPTY_LIST
     */
    List<UserAction> getUserActions();

    /**
     * Add new user action
     *
     * @param type UserActionType
     * @param message Messgae
     * @param owner Owner
     */
    UserAction addUserAction(UserActionType type, String message, String owner);

    /**
     * For merging
     *
     * @param userAction User Action
     * @return
     */
    UserAction addUserAction(UserAction userAction);

    /**
     * Is the job waiting for User Action
     *
     * @return waiting for User Action
     */
    boolean isAwaitingForAction();

    /**
     * Set awaiting for action flag
     *
     * @param awaitingForAction awaiting for action flag
     */
    void setAwaitingForAction(boolean awaitingForAction);

    /**
     * Return all status updates
     * @return status updates, if there is no UserAction, it returns Collections.EMPTY_LIST
     */
    List<StatusUpdate> getUpdates();

    /**
     * Add a new status update
     *
     * @param status StatusEnum
     * @param message Message
     */
    StatusUpdate addUpdate(StatusEnum status, String message);
}
