package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.PackageViewModel;
import com.netcracker.edu.fapi.service.PackageDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pa")
public class PackageDataController {

    @Autowired
    private PackageDataService packageDataService;

    @RequestMapping
    public ResponseEntity<List<PackageViewModel>> getAllPackages() {
        return ResponseEntity.ok(packageDataService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PackageViewModel> getPackageById(@PathVariable String id) {
        return ResponseEntity.ok(packageDataService.getPackageById(Long.valueOf(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PackageViewModel> savePackage(@RequestBody PackageViewModel bundle) {
        if (bundle != null) {
            return ResponseEntity.ok(packageDataService.savePackage(bundle));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePackage(@PathVariable String id) {
        packageDataService.deletePackage(Long.valueOf(id));
    }

    @RequestMapping(value = "/page/{num}", method = RequestMethod.GET)
    public ResponseEntity<List<PackageViewModel>> getPackagesByPage(@PathVariable(name = "num") int num, @RequestParam("qt") int quantity) {
        return ResponseEntity.ok(packageDataService.getPackagesByPage(num, quantity));
    }

    @RequestMapping(value = "/total-pages", method = RequestMethod.GET)
    public int getTotalPages(@RequestParam("qt") int quantity) {
        return packageDataService.getTotalPages(quantity);
    }

}
