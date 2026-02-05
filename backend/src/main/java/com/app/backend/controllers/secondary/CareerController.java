package com.app.backend.controllers.secondary;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.backend.dtos.secondary.CareerDTO;
import com.app.backend.services.secondary.ICareerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/secondary/careers")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CareerController {
    private final ICareerService careerService;

    @GetMapping
    public ResponseEntity<List<CareerDTO>> getAll() {
        return ResponseEntity.ok(careerService.getAllCareers());
    }

    @PostMapping
    public ResponseEntity<CareerDTO> create(@RequestBody CareerDTO careerDTO) {
        CareerDTO savedCareer = careerService.setCareer(careerDTO);
        return ResponseEntity.ok(savedCareer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CareerDTO> update(@PathVariable Integer id, @RequestBody CareerDTO careerDTO) {
        careerDTO.setIdCarrera(id);
        CareerDTO updateCareer = careerService.setCareer(careerDTO);
        return ResponseEntity.ok(updateCareer);
    }
}
