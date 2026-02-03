package com.app.backend.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.backend.dtos.CareerDTO;
import com.app.backend.services.ICareerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/careers")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CareerController {
    private final ICareerService careerService;

    @GetMapping
    public ResponseEntity<List<CareerDTO>> getAll(){
        return ResponseEntity.ok(careerService.getAllCaeers());
    }

    @PostMapping
    public ResponseEntity<CareerDTO> create(@RequestBody CareerDTO careerDTO){
        CareerDTO savedCareer = careerService.setCareer(careerDTO);
        return ResponseEntity.ok(savedCareer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CareerDTO> update(@PathVariable Integer id, @RequestBody CareerDTO careerDTO){
        careerDTO.setIdFacultad(id);
        CareerDTO updateCareer = careerService.setCareer(careerDTO);
        return ResponseEntity.ok(updateCareer);
    }
}
