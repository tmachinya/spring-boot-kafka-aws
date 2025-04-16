package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    List<PatientResponseDTO> getPatients();
    PatientResponseDTO createPatient(PatientRequestDTO patient);
    PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patient);
    void deletePatient(UUID id);
}
