package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.CareerDTO;
import java.util.List;

public interface ICareerService {
    List<CareerDTO> getAllCareers();
    CareerDTO setCareer(CareerDTO careerDTO);
}
