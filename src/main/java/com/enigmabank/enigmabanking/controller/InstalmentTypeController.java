package com.enigmabank.enigmabanking.controller;

import com.enigmabank.enigmabanking.dto.request.InstalmentTypeRequest;
import com.enigmabank.enigmabanking.dto.response.CommonResponse;
import com.enigmabank.enigmabanking.entity.InstalmentType;
import com.enigmabank.enigmabanking.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instalment-types")
@RequiredArgsConstructor
public class InstalmentTypeController {
    private final InstalmentTypeService instalmentTypeService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    @PostMapping
    public ResponseEntity<?> createNewInstalmentType(@RequestBody InstalmentTypeRequest instalmentTypeRequest){
        InstalmentType instalmentType = instalmentTypeService.createInstalmentType(instalmentTypeRequest);
        CommonResponse<InstalmentType> commonResponse = CommonResponse.<InstalmentType>builder()
                .message("Successfully create new instalment type")
                .statusCode(HttpStatus.OK.value())
                .data(instalmentType)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInstalmentTypeById(@PathVariable String id){
        InstalmentType instalmentType = instalmentTypeService.getInstalmentTypeById(id);
        CommonResponse<InstalmentType> commonResponse = CommonResponse.<InstalmentType>builder()
                .message("Succesfully get instalment type by id")
                .statusCode(HttpStatus.OK.value())
                .data(instalmentType)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllInstalmentTypes(){
        List<InstalmentType> instalmentTypes = instalmentTypeService.getAllInstalmentTypes();
        CommonResponse<List<InstalmentType>> commonResponse = CommonResponse.<List<InstalmentType>>builder()
                .message("Successfully fetch instalment types")
                .statusCode(HttpStatus.OK.value())
                .data(instalmentTypes)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    @PutMapping
    public ResponseEntity<?> updateInstalmentTypes(@RequestBody InstalmentType instalmentTypeRequest){
        InstalmentType instalmentTypeUpdated = instalmentTypeService.updateInstalmentType(instalmentTypeRequest);
        CommonResponse<InstalmentType> commonResponse = CommonResponse.<InstalmentType>builder()
                .message("Succesfully update instalment type")
                .statusCode(HttpStatus.OK.value())
                .data(instalmentTypeUpdated)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstalmentType(@PathVariable String id){
        instalmentTypeService.deleteInstalmentType(id);
        CommonResponse<?> commonResponse = CommonResponse.builder()
                .message("Successfully delete instalment type by id")
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }
}
