package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.PackageViewModel;

import java.util.List;

public interface PackageDataService {

    List<PackageViewModel> getAll();
    PackageViewModel getPackageById(Long id);
    PackageViewModel savePackage(PackageViewModel bundle);
    void deletePackage(Long id);
    List<PackageViewModel> getPackagesByPage(int page, int quantity);
    int getTotalPages(int quantity);

}
