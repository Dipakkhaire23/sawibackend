package com.sawi_health.demo.service;

import com.sawi_health.demo.dto.InvestmentRequestDto;
import com.sawi_health.demo.dto.InvestmentResponseDto;
import com.sawi_health.demo.model.Investment;
import com.sawi_health.demo.model.User;
import com.sawi_health.demo.repository.InvestmentRepository;
import com.sawi_health.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepository repo;



    @Autowired
    private UserRepository userRepo;

    public InvestmentResponseDto create(InvestmentRequestDto dto) {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        double total = dto.getEquityPercent() + dto.getDebtPercent() + dto.getGoldPercent();
        if (total != 100) {
            throw new RuntimeException("Allocation must be 100%");
        }

        Investment inv = new Investment();

        inv.setName(dto.getName());
        inv.setRiskType(user.getRiskType());

        inv.setExpectedReturn(dto.getExpectedReturn());
        inv.setMinInvestment(dto.getMinInvestment());

        inv.setGoal(dto.getGoal());
        inv.setHorizonYears(dto.getHorizonYears());

        inv.setEquityPercent(dto.getEquityPercent());
        inv.setDebtPercent(dto.getDebtPercent());
        inv.setGoldPercent(dto.getGoldPercent());
inv.setUser(user);
        inv.setStrategy(dto.getStrategy());
        inv.setStatus("ACTIVE");

        Investment saved = repo.save(inv);

        // 🔥 MAP TO DTO
        InvestmentResponseDto res = new InvestmentResponseDto();

        res.setId(saved.getId());
        res.setName(saved.getName());
        res.setRiskType(saved.getRiskType());

        res.setExpectedReturn(saved.getExpectedReturn());
        res.setMinInvestment(saved.getMinInvestment());

        res.setGoal(saved.getGoal());
        res.setHorizonYears(saved.getHorizonYears());

        res.setEquityPercent(saved.getEquityPercent());
        res.setDebtPercent(saved.getDebtPercent());
        res.setGoldPercent(saved.getGoldPercent());

        res.setStrategy(saved.getStrategy());
        res.setStatus(saved.getStatus());

        return res;
    }
    public List<InvestmentResponseDto> getAll() {

        return repo.findAll()
                .stream()
                .map(inv -> {
                    InvestmentResponseDto dto = new InvestmentResponseDto();
                    // 🔥 GET USER DIRECTLY
                    User user = inv.getUser();

                    if (user != null) {
                        dto.setUsername(user.getName()); // add this field in DTO
                    }
                    dto.setId(inv.getId());
                    dto.setName(inv.getName());
                    dto.setRiskType(inv.getRiskType());

                    dto.setExpectedReturn(inv.getExpectedReturn());
                    dto.setMinInvestment(inv.getMinInvestment());

                    dto.setGoal(inv.getGoal());
                    dto.setHorizonYears(inv.getHorizonYears());

                    dto.setEquityPercent(inv.getEquityPercent());
                    dto.setDebtPercent(inv.getDebtPercent());
                    dto.setGoldPercent(inv.getGoldPercent());

                    dto.setStrategy(inv.getStrategy());
                    dto.setStatus(inv.getStatus());

                    return dto;
                })
                .toList();
    }

    public List<InvestmentResponseDto> getPlansByUser(User user) {

        return repo.findByUser(user)
                .stream()
                .map(inv -> {
                    InvestmentResponseDto dto = new InvestmentResponseDto();

                    dto.setId(inv.getId());
                    dto.setName(inv.getName());
                    dto.setRiskType(inv.getRiskType());

                    dto.setExpectedReturn(inv.getExpectedReturn());
                    dto.setMinInvestment(inv.getMinInvestment());

                    dto.setGoal(inv.getGoal());
                    dto.setHorizonYears(inv.getHorizonYears());

                    dto.setEquityPercent(inv.getEquityPercent());
                    dto.setDebtPercent(inv.getDebtPercent());
                    dto.setGoldPercent(inv.getGoldPercent());

                    dto.setStrategy(inv.getStrategy());
                    dto.setStatus(inv.getStatus());

                    return dto;
                })
                .toList();
    }





}