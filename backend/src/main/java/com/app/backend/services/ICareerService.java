package com.app.backend.services;

import java.util.List;

import com.app.backend.dtos.CareerDTO;

public interface ICareerService {
    List<CareerDTO> getAllCaeers();
    CareerDTO setCareer(CareerDTO careerDTO);
}
