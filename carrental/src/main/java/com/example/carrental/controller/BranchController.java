package com.example.carrental.controller;

import com.example.carrental.model.Branch;
import com.example.carrental.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
@Slf4j
public class BranchController {
    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<Branch> addBranch(@RequestBody Branch branch) {
        Branch savedBranch = branchService.addBranch(branch);
        log.info("Branch created: "+savedBranch.getName());
        return ResponseEntity.created(URI.create("/branches/" + savedBranch.getId()))
                .body(savedBranch);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Branch>> searchByName(@PathVariable String name) {
        List<Branch> branches = branchService.searchByName(name);
        if (branches.isEmpty()) {
            log.warn("No branches found with name: {}", name);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(branches);
    }

    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }
}

