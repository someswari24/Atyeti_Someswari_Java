package com.smartHomeAutomationSystem.repository;

import com.smartHomeAutomationSystem.entity.AutomationRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutomationRuleRepository extends JpaRepository<AutomationRule,Long> {
    List<AutomationRule> findByUserId(Long userId);
    List<AutomationRule> findByDeviceId(Long deviceId);
    List<AutomationRule> findByDeviceIdIn(List<Long> deviceIds);
}
