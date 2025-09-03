package com.example.carrental.service;

import com.example.carrental.model.Branch;
import com.example.carrental.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;

    public Branch addBranch(Branch branch){
        return branchRepository.save(branch);
    }

    public List<Branch> searchByName(String name){
        return branchRepository.searchByName(name);
    }

    public List<Branch>getAllBranches(){
        return branchRepository.findAll();
    }
}
