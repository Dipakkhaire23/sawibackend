package com.sawi_health.demo.service;

import com.sawi_health.demo.dto.DashboardStatsDto;
import com.sawi_health.demo.model.Consultation;
import com.sawi_health.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepo;
    @Autowired private InvestmentRepository investmentRepo;
    @Autowired private MeetingRepository meetingRepo;
    @Autowired private ConsultationRepository consultationRepo;
    @Autowired private RiskResultRepository reportRepo;

    // 🔥 STATS
    public DashboardStatsDto getStats() {

        DashboardStatsDto dto = new DashboardStatsDto();

        dto.setTotalUsers(userRepo.count());
        dto.setTotalInvestments(investmentRepo.count());
        dto.setTotalMeetings(meetingRepo.count());
        dto.setTotalConsultations(consultationRepo.count());
        dto.setTotalReports(reportRepo.count());

        return dto;
    }

    // 🔥 RECENT CONSULTATIONS
    public List<Consultation> getRecentConsultations() {
        return consultationRepo
                .findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .limit(5)
                .toList();
    }
}
