/*
package com.jsonparser.helper;

import com.jsonparser.model.EventEnum;
import com.jsonparser.model.EventLog;
import com.jsonparser.model.EventLogAlert;
import com.jsonparser.service.EventLogAlertServiceImpl;
import com.jsonparser.service.EventLogAlertServiceInt;
import com.jsonparser.service.EventLogServiceImpl;
import com.jsonparser.service.EventLogServiceInt;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

@NoArgsConstructor
public class EventLogProcessor {

    Map<String,Long> eventStartIdAndTimeStampTreeMap = new TreeMap<String,Long>();

    Map<String,Long> eventFinishIdAndTimeStampTreeMap = new TreeMap<String,Long>();


    */
/**
     *  EventLogServiceInt interface bean injection used here
     *//*

    @Bean
    public EventLogServiceInt injectEventLogServiceInt() {
        EventLogServiceImpl eventLogServiceImpl= new EventLogServiceImpl();
        return eventLogServiceImpl;
    }


    */
/**
     *  EventLogServiceInt interface bean injection used here
     *//*

    @Bean
    public EventLogAlertServiceInt injectEventLogAlertServiceInt() {
        EventLogAlertServiceImpl eventLogAlertService = new EventLogAlertServiceImpl();
        return eventLogAlertService;
    }

    */
/**
     * processEventLog method called from the CommandLineRunner -> run method
     *
     * @param jsonFilePath
     *//*

    public void processEventLog(String jsonFilePath)  {

        // Map<String,Long> stateAndTimeTreeMap = new TreeMap<String,Long>();

        File jsonFile = new File(jsonFilePath);

        JsonFactory jsonFactory = new JsonFactory();  // Init factory

        int  noOfJsonRecords = 0;

        try {

            JsonParser jsonParser = jsonFactory.createJsonParser(jsonFile); //Create JSON parser

            EventLog eventLog = new EventLog();

            JsonToken jsonToken = jsonParser.nextToken();

            // While loop iterate the json file till the end of json array
            while(jsonToken != JsonToken.END_ARRAY) {

                String fieldName = jsonParser.getCurrentName();

                if (EventEnum.valueOf("ID").getEvent().equals(fieldName)) {
                    jsonToken = jsonParser.nextToken(); //read next token
                    eventLog.setEventId(jsonParser.getText());
                }

                if (EventEnum.valueOf("STATE").getEvent().equals(fieldName)) {
                    jsonToken = jsonParser.nextToken(); //read next token
                    eventLog.setEventState(jsonParser.getText());
                }

                if (EventEnum.valueOf("TYPE").getEvent().equals(fieldName)) {
                    jsonToken = jsonParser.nextToken(); //read next token
                    eventLog.setEventType(jsonParser.getText());
                }

                if (EventEnum.valueOf("HOST").getEvent().equals(fieldName)) {
                    jsonToken = jsonParser.nextToken(); //read next token
                    eventLog.setEventHost(jsonParser.getText());
                }

                if (EventEnum.valueOf("TIMESTAMP").getEvent().equals(fieldName)) {
                    jsonToken = jsonParser.nextToken(); //read next token
                    eventLog.setEventTimestamp(jsonParser.getValueAsLong());
                }

                if (jsonToken == JsonToken.END_OBJECT) {

                    System.out.println("EventLog Object :" + eventLog);

                    // Called saveEventLogRecord method to persist object in HSQLDB
                    saveEventLogRecord(eventLog, eventStartIdAndTimeStampTreeMap, eventFinishIdAndTimeStampTreeMap);

                    eventLog = new EventLog();

                    noOfJsonRecords++;
                }

                jsonToken = jsonParser.nextToken();
            }
            System.out.println("Total no. of EventLog JSON records :- " + noOfJsonRecords);
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

    }

    */
/**
     * This method has been written EventLog to HSQLDB
     *
     * @param eventLog
     *//*

    private void saveEventLogRecord(EventLog eventLog, Map<String,Long> eventStartIdAndTimeStampTreeMap, Map<String,Long> eventFinishIdAndTimeStampTreeMap) {

        if(eventLog!=null) {

            // Persisting EventLog object here into EVENT_LOG table
            injectEventLogServiceInt().saveEventLog(eventLog);


            */
/**
             *  This block of code will check the EventState == "STARTED" then add the EventID, EventTimestamp into the eventStartIdAndTimeStampTreeMap.
             * 	If the EventID containsKey found into eventFinishIdAndTimeStampTreeMap then totalEventTime = eventStopTimeStamp - eventStartTimeStamp will be calculated.
             *
             * 	eventFinishIdAndTimeStampTreeMap will be populated into the if-else block because Events are not logging sequentially in the LogFile.
             *//*

            if(eventLog.getEventState().equals("STARTED")) {

                if(eventFinishIdAndTimeStampTreeMap.containsKey(eventLog.getEventId())) {

                    Long eventStartTimeStamp = eventLog.getEventTimestamp();
                    System.out.println("STARTED BLOCK -  eventStartTimeStamp :-" + eventStartTimeStamp);

                    Long eventStopTimeStamp =  eventFinishIdAndTimeStampTreeMap.get(eventLog.getEventId());
                    System.out.println("STARTED BLOCK -  eventStopTimeStamp :-" + eventStopTimeStamp);

                    Long totalEventTime =  eventStopTimeStamp  - eventStartTimeStamp;
                    System.out.println("STARTED BLOCK -  totalEventTime :- " + totalEventTime);


                    // Persisting EventLogAlert object here into EVENT_LOG_ALERT table, if alert is true
                    EventLogAlert eventLogAlertObj = calculateTotalEventAlertTime(eventLog,totalEventTime);
                    injectEventLogAlertServiceInt().saveEventLogAlert(eventLogAlertObj);


                    // Remove the key/value which is found into the eventFinishIdAndTimeStampTreeMap
                    eventStartIdAndTimeStampTreeMap.remove(eventLog.getEventId());
                    eventFinishIdAndTimeStampTreeMap.remove(eventLog.getEventId());
                }

                eventStartIdAndTimeStampTreeMap.put(eventLog.getEventId(), eventLog.getEventTimestamp());


                */
/**
                 *  This block of code will check the EventState == "FINISHED" then add the EventID, EventTimestamp into the eventFinishIdAndTimeStampTreeMap
                 *  at the end of else-if block.
                 *
                 * 	If the EventID containsKey found into eventStartIdAndTimeStampTreeMap then totalEventTime = eventStopTimeStamp - eventStartTimeStamp will be calculated.
                 *//*

            } else if(eventLog.getEventState().equals("FINISHED")) {

                if(eventStartIdAndTimeStampTreeMap.containsKey(eventLog.getEventId())) {

                    Long eventStartTimeStamp = eventStartIdAndTimeStampTreeMap.get(eventLog.getEventId());
                    System.out.println("FINISHED BLOCK -  eventStartTimeStamp :-" + eventStartTimeStamp);

                    Long eventStopTimeStamp = eventLog.getEventTimestamp();
                    System.out.println("FINISHED BLOCK -  eventStopTimeStamp :-" + eventStopTimeStamp);

                    Long totalEventTime =  eventStopTimeStamp - eventStartTimeStamp;
                    System.out.println("FINISHED BLOCK -  totalEventTime :- " + totalEventTime);

                    // Persisting EventLogAlert object here into EVENT_LOG_ALERT table, if alert is false
                    EventLogAlert eventLogAlertObj = calculateTotalEventAlertTime(eventLog,totalEventTime);
                    injectEventLogAlertServiceInt().saveEventLogAlert(eventLogAlertObj);

                    // Remove the key/value which is found into the eventStartIdAndTimeStampTreeMap
                    eventStartIdAndTimeStampTreeMap.remove(eventLog.getEventId());
                }
                eventFinishIdAndTimeStampTreeMap.put(eventLog.getEventId(),eventLog.getEventTimestamp());
            }
            System.out.println("eventStartIdAndTimeStampTreeMap.size()" + eventStartIdAndTimeStampTreeMap.size());
            System.out.println("eventFinishIdAndTimeStampTreeMap.size()" + eventFinishIdAndTimeStampTreeMap.size());
        }
    }


    private @NotNull EventLogAlert calculateTotalEventAlertTime(@NotNull EventLog eventLog, Long totalEventTime) {
        EventLogAlert eventLogAlertObj = new EventLogAlert();
        eventLogAlertObj.setEventId(eventLog.getEventId());
        eventLogAlertObj.setEventDuration(totalEventTime);
        eventLogAlertObj.setEventType(eventLog.getEventType());
        eventLogAlertObj.setEventHost(eventLog.getEventHost());

        if(totalEventTime > 4) {
            eventLogAlertObj.setAlert(true);
        } else {
            eventLogAlertObj.setAlert(false);
        }
        return eventLogAlertObj;
    }
}
*/
