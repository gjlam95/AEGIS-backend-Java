package com.aegis.service;

import com.aegis.entity.Qualification;
import com.aegis.repository.QualificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QualificationService {

    @Autowired
    private QualificationRepository qualificationRepository;

    public List<Qualification> findAll() {
        return qualificationRepository.findAll();
    }

    public Optional<Qualification> findById(long callsign) {
        return qualificationRepository.findById(callsign);
    }

    @Transactional
    public Qualification createOrUpdate(Qualification qualification) {
        return qualificationRepository.save(qualification);
    }

    @Transactional
    public void deleteById(long callsign) {
        qualificationRepository.deleteById(callsign);
    }
}
