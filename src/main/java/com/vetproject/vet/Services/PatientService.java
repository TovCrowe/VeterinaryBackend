package com.vetproject.vet.Services;

import java.util.List;

import com.vetproject.vet.DTO.PatientUpdateDTO;
import com.vetproject.vet.Models.PatientModel;

public interface PatientService {
    List<PatientModel> getAllPatients();
    PatientModel getPatientById(Long id);
    PatientModel createPatient(PatientModel patient);
    PatientModel updatePatient(Long id, PatientUpdateDTO patient);
    void deletePatient(Long id);
}
