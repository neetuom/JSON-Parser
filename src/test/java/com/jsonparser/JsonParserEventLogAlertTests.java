package com.jsonparser;


import com.jsonparser.model.EventLog;
import com.jsonparser.model.EventLogAlert;
import com.jsonparser.service.EventLogAlertServiceInt;
import com.jsonparser.service.EventLogServiceInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;

/**
 *
 * This class is used to test the JUnit methods for EventLogAlert
 *
 */
@SpringBootTest
public class JsonParserEventLogAlertTests {


    // Logging injection
    private static final Logger LOG = LogManager.getLogger(JsonParserEventLogAlertTests.class);

    /**
     * EventLogAlertServiceInt  - Interface injection has been used.
     *
     */
    @Autowired
    private EventLogAlertServiceInt eventLogAlertServiceInt;



    /**
     * This methods fetch the eventLogAlert object and test this method return value is not null.
     *
     */

    //@Test
    public void getEventLogAlertByIDTest() {
        String eventId = "scsmbstgra";

        LOG.info("JsonParserEventLogAlertTests ->  getEventLogAlertByIDTest()");

        assertNotNull(eventLogAlertServiceInt.getEventLogsAlert(eventId));
    }


    /**
     * This methods fetch the listOfEventLogAlert and test this method return value is not null.
     *
     */
    //@Test
    public void listOfEventLogAlertTest(){

        LOG.info("JsonParserEventLogAlertTests ->  listOfEventLogAlertTest()");
        assertNotNull(eventLogAlertServiceInt.getListOfEventsAlert());
    }

    /**
     * This method saves the EventLog object into the HSQLDB
     *
     */

    //@Test
    public void saveEventLogAlert() {
        LOG.info("JsonParserEventLogAlertTests-> saveEventLogAlert");

        EventLogAlert eventLogAlertObj = new EventLogAlert();
        eventLogAlertObj.setEventId("scsmbstgre");
        eventLogAlertObj.setEventDuration(8);
        eventLogAlertObj.setEventType("APPLICATION_LOG");
        eventLogAlertObj.setEventHost("12345");
        eventLogAlertObj.setAlert(true);

        eventLogAlertServiceInt.saveEventLogAlert(eventLogAlertObj);
    }

    /**
     *  This method deleteEventLog by eventId
     *
     */
    //@Test
    public void deleteEventLog() {
        LOG.info("JsonParserEventLogAlertTests-> deleteEventLog");

        String eventId = "scsmbstgra";
        eventLogAlertServiceInt.deleteEventLogAlert(eventId);
    }
}
