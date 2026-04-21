package com.sawi_health.demo.service;

import com.sawi_health.demo.dto.ConsultationDto;
import com.sawi_health.demo.dto.MeetingDto;
import com.sawi_health.demo.model.Consultation;
import com.sawi_health.demo.model.Meeting;
import com.sawi_health.demo.repository.ConsultationRepository;
import com.sawi_health.demo.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private ConsultationRepository consultationRepository;
    // 🔥 CREATE
    public MeetingDto create(MeetingDto dto) {

        Meeting m = new Meeting();

        m.setTopic(dto.getTopic());
        m.setDescription(dto.getDescription());
        m.setMeetingTime(dto.getMeetingTime());
        m.setMeetLink(dto.getMeetLink());
        m.setStatus("ACTIVE");

        Meeting saved = meetingRepository.save(m);

        dto.setId(saved.getId());
        return dto;
    }

    // 🔥 GET ALL
    public List<MeetingDto> getAll() {
        return meetingRepository.findAll()
                .stream()
                .map(m -> {
                    MeetingDto dto = new MeetingDto();
                    dto.setId(m.getId());
                    dto.setTopic(m.getTopic());
                    dto.setDescription(m.getDescription());
                    dto.setMeetingTime(m.getMeetingTime());
                    dto.setMeetLink(m.getMeetLink());
                    return dto;
                })
                .toList();
    }

    public void save(ConsultationDto dto) {

        Consultation c = new Consultation();

        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());
        c.setMessage(dto.getMessage());
        c.setCreatedAt(LocalDateTime.now());

        consultationRepository.save(c);
    }

    public List<Consultation> getAllConsultation() {
        return consultationRepository.findAll();
    }
}