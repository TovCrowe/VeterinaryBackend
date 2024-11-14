package com.vetproject.vet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetproject.vet.Models.PatientModel;
@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long> {


}
