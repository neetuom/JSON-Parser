package com.jsonparser.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 *  EventLog Pojo used to perform CRUD operation on the EVENT_LOG table in HSQLDB .
 */

@Entity
@Table(name="EVENT_LOG",catalog="jsondb")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class EventLog implements Serializable {

    /**
     * serialVersionUID - used to serialize and deserialize the object.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *  eventId - is type of String
     */
    @Column(name="EVENT_ID", columnDefinition = "varchar(150)", nullable = false, unique = false)
    private String eventId;

    /**
     *  state - type String
     */
    @Column(name="EVENT_STATE", columnDefinition = "varchar(50)")
    private String eventState;

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
    @Id
    @Column(name="EVENT_TIMESTAMP", columnDefinition = "bigint")
    private long eventTimestamp;

}
