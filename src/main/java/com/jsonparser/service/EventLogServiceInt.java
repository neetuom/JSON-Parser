package com.jsonparser.service;

import com.jsonparser.model.EventLog;

import java.util.List;

/**
 *
 *  This class used to define the EventLogServiceInterface interface methods prototype
 *
 */
public interface EventLogServiceInt {

    /**
     * This method return EventLog by ID
     * @param id
     * @return EventLog
     */
    public EventLog getEventLogs(String id);

    /**
     * This method return List of EventLog
     * @return List<EventLog>
     */
    public List<EventLog> getListOfEvents();

    /**
     * This methods saves the EventLog Object
     *
     * @param eventLog
     */
    public void saveEventLog(EventLog eventLog);

    /**
     * This method deletes the EventLog by ID from database.
     *
     * @param id
     */
    public void deleteEventLog(String id);
}
