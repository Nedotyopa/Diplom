package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Package;
import com.netcracker.edu.backend.repository.PackageRepository;
import com.netcracker.edu.backend.service.PackageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PackageServiceImpl implements PackageService {

    private PackageRepository repository;

    @Autowired
    PackageServiceImpl(PackageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Package savePackage(Package bundle) {
        return repository.save(bundle);
    }

    @Override
    public Optional<Package> getPackageById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Package> getAllPackages() {
        return repository.findAll();
    }

    @Override
    public void deletePackage(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Package> getPackagesByPage(int page, int quantity) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = new PageRequest(page-1, quantity, sort);
        return repository.findAll(pageable);
    }

}
