package com.jsonparser;


import com.jsonparser.model.EventLog;
import com.jsonparser.service.EventLogAlertServiceInt;
import com.jsonparser.service.EventLogServiceInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.Assert.assertNotNull;


/**
 *
 * This class is used to test the JUnit methods for EventLog
 *
 */
@SpringBootTest
class JsonParserEventLogTests {

	// Logging injection
	private static final Logger LOG = LogManager.getLogger(JsonParserEventLogTests.class);

	/**
	 * EventLogServiceInt  - Interface injection has been used.
	 *
	 */
	@Autowired
	private EventLogServiceInt eventLogServiceInt;

	/**
	 * This methods fetch the eventId and test this method return value is not null.
	 *
	 */

	//@Test
	public void getEventLogByIDTest() {
		String eventId = "scsmbstgra";

		LOG.info("JsonParserApplicationTests ->  getEventLogByIDTest()");
		System.out.println("JsonParserApplicationTests ->  getEventLogsTest()");

		assertNotNull(eventLogServiceInt.getEventLogs(eventId));
	}


	/**
	 * This methods fetch the listOfEventLogs and test this method return value is not null.
	 *
	 */
	//@Test
	public void listOfEventLogTest(){
		LOG.info("JsonParserApplicationTests ->  listOfEventLogTest()");
		assertNotNull(eventLogServiceInt.getListOfEvents());
	}

	/**
	 * This method saves the EventLog object into the HSQLDB
	 *
	 */

	//@Test
	public void saveEventLog() {
		LOG.info("JsonParserEventLogTests-> saveEventLog");

		EventLog eventLogObj = new EventLog();
		eventLogObj.setEventId("scsmbstgrf");
		eventLogObj.setEventState("STARTED");
		eventLogObj.setEventType("APPLICATION_LOG");
		eventLogObj.setEventHost("12345");
		eventLogObj.setEventTimestamp(1491377495246L);

		eventLogServiceInt.saveEventLog(eventLogObj);
	}

	/**
	 *  This method deleteEventLog by eventId
	 *
	 */
	//@Test
	public void deleteEventLog() {
		LOG.info("JsonParserEventLogTests-> deleteEventLog");

		String eventId = "scsmbstgra";
		eventLogServiceInt.deleteEventLog(eventId);
	}

}
