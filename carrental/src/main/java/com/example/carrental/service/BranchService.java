package com.example.carrental.service;

import com.example.carrental.model.Branch;
import com.example.carrental.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;

    public Branch addBranch(Branch branch){
        log.info("Adding Branch :"+branch.getName());
        return branchRepository.save(branch);
    }

    public List<Branch> searchByName(String name){
        return branchRepository.searchByName(name);
    }

    public List<Branch>getAllBranches(){
        return branchRepository.findAll();
    }
}
