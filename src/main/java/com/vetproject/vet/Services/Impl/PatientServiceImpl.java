package com.vetproject.vet.Services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vetproject.vet.DTO.PatientUpdateDTO;
import com.vetproject.vet.Exceptions.PatientValidationException;
import com.vetproject.vet.Models.PatientModel;
import com.vetproject.vet.Repository.PatientRepository;
import com.vetproject.vet.Services.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientModel> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public PatientModel getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public PatientModel createPatient(PatientModel patient) {
        // Validate incoming patient data
        if (patient.getNombre() == null || patient.getNombre().trim().isEmpty()) {
            throw new PatientValidationException("Patient name cannot be null or empty");
        }
        if (patient.getEmail() == null || patient.getEmail().trim().isEmpty()) {
            throw new PatientValidationException("Patient email cannot be null or empty");
        }
        if(patient.getPropietario() == null || patient.getPropietario().trim().isEmpty()) {
            throw new PatientValidationException("Patient owner cannot be null or empty");
        }
        if (patient.getFecha_de_alta() == null) {
            throw new PatientValidationException("Patient registration date cannot be null");
        }
        if (patient.getSintomas() == null || patient.getSintomas().trim().isEmpty()) {
            throw new PatientValidationException("Patient symptoms cannot be null or empty");
        }
    
        PatientModel patientModel = new PatientModel();
        patientModel.setNombre(patient.getNombre());
        patientModel.setEmail(patient.getEmail());
        patientModel.setPropietario(patient.getPropietario());
        patientModel.setFecha_de_alta(patient.getFecha_de_alta());
        patientModel.setSintomas(patient.getSintomas());
    
        return patientRepository.save(patientModel);
    }
    
    

    @Override
    public PatientModel updatePatient(Long id, PatientUpdateDTO patientUpdateDTO) {
        PatientModel existingPatient = patientRepository.findById(id).orElse(null);
        if (existingPatient != null) {
            // Update fields of the existing patient with values from the input DTO
            if (patientUpdateDTO.getNombre() != null) {
                existingPatient.setNombre(patientUpdateDTO.getNombre());
            }
            if (patientUpdateDTO.getEmail() != null) {
                existingPatient.setEmail(patientUpdateDTO.getEmail());
            }
            if(patientUpdateDTO.getPropietario() != null){
                existingPatient.setPropietario(patientUpdateDTO.getPropietario());
            }
            if (patientUpdateDTO.getFechaDeAlta() != null) {
                existingPatient.setFecha_de_alta(patientUpdateDTO.getFechaDeAlta());
            }
            if (patientUpdateDTO.getSintomas() != null) {
                existingPatient.setSintomas(patientUpdateDTO.getSintomas());
            }
            // Save and return the updated patient
            return patientRepository.save(existingPatient);
        }
        return null;
    }
    

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
