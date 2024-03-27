package com.project.planner.service;

import com.project.planner.dto.PlanDto;
import com.project.planner.dto.SignUpDto;
import com.project.planner.entity.PlanItemEntity;
import com.project.planner.repository.MemberRepository;
import com.project.planner.repository.PlanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service    // SpringBoot
@Transactional  // SpringBoot
public class PlanService {

    @Autowired  // SpringBoot
    private PlanRepository planRepository;

    @Autowired
    private ModelMapper modelMapper;

    public String insert(@ModelAttribute PlanDto planDto) {
        PlanItemEntity plan = new PlanItemEntity();
        modelMapper.map(planDto, plan);

        if( planDto != null){
            planRepository.save(plan);
        }

        return "";
    }
}
