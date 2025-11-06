package com.smartHomeAutomationSystem.entity;

import com.smartHomeAutomationSystem.enums.AutomationAction;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name="automation_rules")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AutomationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name = "rule_condition")
    private String condition;

    @Enumerated(EnumType.STRING)
    private AutomationAction action;

    private LocalTime triggerTime;

    private boolean enabled;
}
