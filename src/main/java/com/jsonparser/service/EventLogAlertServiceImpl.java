package com.jsonparser.service;

import com.jsonparser.JsonParserApplication;
import com.jsonparser.model.EventLog;
import com.jsonparser.model.EventLogAlert;
import com.jsonparser.repository.EventLogAlertRepositoryInterface;
import com.jsonparser.repository.EventLogRepositoryInterface;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *  This class used to implement the EventLogServiceInt  interface methods prototype
 *
 */
@NoArgsConstructor
@Service
@Transactional
public class EventLogAlertServiceImpl implements EventLogAlertServiceInt {

    // Logger injection
    private static final Logger LOG = LogManager.getLogger(EventLogAlertServiceImpl.class);

    /**
     * EventLogServiceInt interface injection
     */
    @Autowired
    private EventLogAlertRepositoryInterface eventLogAlertRepositoryInterface;

    /**
     * This method get getEventLogs by ID
     * @param id
     * @return EventLogAlert
     */
    @Override
    public EventLogAlert getEventLogsAlert(String id) {

        return eventLogAlertRepositoryInterface.findById(id).get();
    }

    /**
     * This method return the listOfEventLogAlert
     *
     * @return List<EventLogAlert>
     */
    @Override
    public List<EventLogAlert> getListOfEventsAlert() {
        return eventLogAlertRepositoryInterface.findAll();
    }

    /**
     * This method saves the EventLogAlert data into HSQLDB
     *
     * @param eventLogAlert
     */
    @Override
    public void saveEventLogAlert(EventLogAlert eventLogAlert) {
        LOG.info("EventLogAlertServiceImpl-> saveEventLogAlert");
        eventLogAlertRepositoryInterface.save(eventLogAlert);
    }

    /**
     *  This method deleteEventLog by ID
     *
     * @param id
     */
    @Override
    public void deleteEventLogAlert(String id) {
        eventLogAlertRepositoryInterface.deleteById(id);
    }
}
