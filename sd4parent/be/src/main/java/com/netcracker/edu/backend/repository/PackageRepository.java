package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Package;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends CrudRepository<Package, Long>, PagingAndSortingRepository<Package, Long> {
}
