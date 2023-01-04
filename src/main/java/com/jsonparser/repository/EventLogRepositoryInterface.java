package com.jsonparser.repository;


import com.jsonparser.model.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;

 /*
  * This class implement the JpaRepository interface to perform the CRUD operations on EventLog Object Entity
  *
  */
public interface EventLogRepositoryInterface extends JpaRepository<EventLog, String> {
}
