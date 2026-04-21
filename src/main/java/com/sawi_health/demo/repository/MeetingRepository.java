package com.sawi_health.demo.repository;

import com.sawi_health.demo.model.Consultation;
import com.sawi_health.demo.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}

