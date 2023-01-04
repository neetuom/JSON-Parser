package com.jsonparser.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 *   EventLogAlert Pojo used to insert the EventLogAlert into the EVENT_LOG_ALERT table in HSQLDB.
 */

@Entity
@Table(name="EVENT_LOG_ALERT",catalog="jsondb")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class EventLogAlert implements Serializable {

    /**
     * serialVersionUID - used to serialize and deserialize the object.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *  eventId - is type of String
     */
    @Id
    @Column(name="EVENT_ID", columnDefinition = "varchar(150)")
    private String eventId;

    /**
     *  state - type int
     */
    @Column(name="EVENT_DURATION", columnDefinition = "bigint")
    private long eventDuration;

    /**
     *  type - type String
     */
    @Column(name="EVENT_TYPE", columnDefinition = "varchar(50)")
    private String eventType;

    /**
     *  host - type String
     */
    @Column(name="EVENT_HOST", columnDefinition = "varchar(50)")
    private String eventHost;

    /**
     *  timestamp - is type of long
     */
    @Column(name="Alert", columnDefinition = "boolean default false")
    private boolean alert;

}
