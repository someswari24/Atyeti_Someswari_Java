package com.smartHomeAutomationSystem.controller;

import com.smartHomeAutomationSystem.dto.request.AutomationRuleRequest;
import com.smartHomeAutomationSystem.dto.response.ApiResponse;
import com.smartHomeAutomationSystem.dto.response.AutomationRuleResponse;
import com.smartHomeAutomationSystem.entity.AutomationRule;
import com.smartHomeAutomationSystem.service.AutomationRuleService;
import com.smartHomeAutomationSystem.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartHomeAutomationSystem.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rules")
@RequiredArgsConstructor
public class AutomationRuleController {

    private final AutomationRuleService automationRuleService;
    private final DeviceService deviceService;

    @GetMapping
    public ResponseEntity<List<AutomationRuleResponse>> getAllRules(@AuthenticationPrincipal CustomUserDetails userDetails) {
        List<AutomationRule> rules = automationRuleService.getRulesByUser(userDetails.getId());
        List<AutomationRuleResponse> responses = rules.stream().map(rule -> {
            AutomationRuleResponse r = new AutomationRuleResponse();
            r.setId(rule.getId());
            r.setRuleName(rule.getRuleName());
            r.setDeviceId(rule.getDevice().getId());
            r.setCondition(rule.getCondition());
            r.setAction(rule.getAction());
            r.setTriggerTime(rule.getTriggerTime());
            r.setEnabled(rule.isEnabled());
            return r;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createRule(@RequestBody AutomationRuleRequest request) {
        AutomationRule rule = new AutomationRule();
        rule.setRuleName(request.getRuleName());
        rule.setDevice(deviceService.findById(request.getDeviceId()));
        rule.setCondition(request.getCondition());
        rule.setAction(request.getAction());
        rule.setTriggerTime(request.getTriggerTime());
        rule.setEnabled(request.isEnabled());
        automationRuleService.save(rule);
        return ResponseEntity.ok(new ApiResponse(true, "Rule created", null));
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<ApiResponse> toggleRule(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse(true, "Rule toggled", null));
    }
}