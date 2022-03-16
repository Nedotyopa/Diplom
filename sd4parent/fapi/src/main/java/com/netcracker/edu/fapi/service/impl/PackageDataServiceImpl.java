package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.PackageViewModel;
import com.netcracker.edu.fapi.service.PackageDataService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class PackageDataServiceImpl implements PackageDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<PackageViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        PackageViewModel[] packageViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/packages/", PackageViewModel[].class);
        return packageViewModelResponse == null ? Collections.emptyList() : Arrays.asList(packageViewModelResponse);
    }

    @Override
    public PackageViewModel getPackageById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/packages/" + id, PackageViewModel.class);
    }

    @Override
    public PackageViewModel savePackage(PackageViewModel bundle) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/packages", bundle, PackageViewModel.class).getBody();
    }

    @Override
    public void deletePackage(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/packages/" + id);
    }

    @Override
    public List<PackageViewModel> getPackagesByPage(int page, int quantity) {
        RestTemplate restTemplate = new RestTemplate();
        PackageViewModel[] packageViewModelResponse = restTemplate.getForObject(backendServerUrl + "api/packages/page/" + page + "?qt=" + quantity, PackageViewModel[].class);
        return packageViewModelResponse == null ? Collections.emptyList() : Arrays.asList(packageViewModelResponse);
    }

    @Override
    public int getTotalPages(int quantity) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "api/packages/total-pages?qt=" + quantity, Integer.class);
    }

}
