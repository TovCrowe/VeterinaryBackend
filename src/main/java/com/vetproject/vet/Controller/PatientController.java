package com.vetproject.vet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vetproject.vet.Models.PatientModel;
import com.vetproject.vet.Services.Impl.PatientServiceImpl;
import com.vetproject.vet.DTO.PatientUpdateDTO;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientServiceImpl patientServiceImpl;

    // Create a new patient
    @PostMapping
    @CrossOrigin
    public ResponseEntity<PatientModel> createPatient(@RequestBody PatientModel patientModel) {
        PatientModel newPatient = patientServiceImpl.createPatient(patientModel);
        return ResponseEntity.ok(newPatient);
    }
    // Retrieve all patients
    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<PatientModel>> getAllPatients() {
        List<PatientModel> patients = patientServiceImpl.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    // Retrieve a patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<PatientModel> getPatientById(@PathVariable Long id) {
        PatientModel patient = patientServiceImpl.getPatientById(id);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        }
        return ResponseEntity.notFound().build();
    }

    // Update a patient by ID
    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<PatientModel> updatePatient(@PathVariable Long id,@RequestBody PatientUpdateDTO patientUpdateDTO) {
        PatientModel updatedPatient = patientServiceImpl.updatePatient(id, patientUpdateDTO);
        if (updatedPatient != null) {
            return ResponseEntity.ok(updatedPatient);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a patient by ID
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        PatientModel existingPatient = patientServiceImpl.getPatientById(id);
        if (existingPatient != null) {
            patientServiceImpl.deletePatient(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
