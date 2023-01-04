package com.jsonparser.service;

import com.jsonparser.JsonParserApplication;
import com.jsonparser.model.EventLog;
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
public class EventLogServiceImpl implements EventLogServiceInt {

    // Logging injection
    private static final Logger LOG = LogManager.getLogger(EventLogServiceImpl.class);

    /**
     * EventLogServiceInt interface injection
     */
    @Autowired
    private EventLogRepositoryInterface eventLogRepositoryInterface;

    /**
     * This method get getEventLogs by ID
     * @param id
     * @return EventLog
     */
    @Override
    public EventLog getEventLogs(String id) {
        return eventLogRepositoryInterface.findById(id).get();
    }

    /**
     * This method return the listOfEventLog
     *
     * @return List<EventLog>
     */
    @Override
    public List<EventLog> getListOfEvents() {
        return eventLogRepositoryInterface.findAll();
    }

    /**
     * This method saves the EventLog data into HSQLDB
     *
     * @param eventLog
     */
    @Override
    public void saveEventLog(EventLog eventLog) {
        LOG.info("EventLogServiceImpl-> saveEventLog");
        eventLogRepositoryInterface.save(eventLog);
    }

    /**
     *  This method deleteEventLog by ID
     *
     * @param id
     */
    @Override
    public void deleteEventLog(String id) {
        eventLogRepositoryInterface.deleteById(id);
    }
}
