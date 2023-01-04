package com.jsonparser.service;

import com.jsonparser.model.EventLog;
import com.jsonparser.model.EventLogAlert;

import java.util.List;

/**
 *
 *  This class used to define the EventLogServiceInterface interface methods prototype
 *
 */
public interface EventLogAlertServiceInt {

    /**
     * This method return EventLogALert by ID
     * @param id
     * @return EventLogAlert
     */
    public EventLogAlert getEventLogsAlert(String id);

    /**
     * This method return List of EventLogAlert
     * @return List<EventLogAlert>
     */
    public List<EventLogAlert> getListOfEventsAlert();

    /**
     * This methods saves the EventLogAlert Object
     *
     * @param eventLogAlert
     */
    public void saveEventLogAlert(EventLogAlert eventLogAlert);

    /**
     * This method deletes the EventLogAlert by ID from database.
     *
     * @param id
     */
    public void deleteEventLogAlert(String id);
}
