package com.aegis.controller;

import com.aegis.entity.Qualification;
import com.aegis.service.QualificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/qualifications"})
@Slf4j
public class QualificationController {

    @Autowired
    private QualificationService qualificationService;

    @GetMapping
    public List<Qualification> findAll() {
        return qualificationService.findAll();
    }

    @PostMapping
    public ResponseEntity<Qualification> create(@Valid @RequestBody Qualification qualification) {

        return ResponseEntity.ok(qualificationService.createOrUpdate(qualification));
    }

    @GetMapping("/{callsign}")
    public ResponseEntity<Qualification> findById(@PathVariable long callsign) {

        Optional<Qualification> qualification = qualificationService.findById(callsign);

        if (!qualification.isPresent()) {
            log.error("Qualification for callsign " + callsign + " does not exist.");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(qualification.get());
    }

    @PutMapping(value="/{callsign}")
    public ResponseEntity<Qualification> update(@PathVariable long callsign, @Valid @RequestBody Qualification qualification) {

        Optional<Qualification> q = qualificationService.findById(callsign);

        if (!q.isPresent()) {
            log.error("Qualification for callsign " + callsign + " does not exist.");
            return ResponseEntity.notFound().build();
        }

        q.get().setName(qualification.getName());
        q.get().setQualified(qualification.isQualified());

        return ResponseEntity.ok(qualificationService.createOrUpdate(q.get()));
    }

    @DeleteMapping("/{callsign}")
    public ResponseEntity deleteById(@PathVariable long callsign) {
        Optional<Qualification> q = qualificationService.findById(callsign);

        if (!q.isPresent()) {
            log.error("Qualification for callsign " + callsign + " does not exist.");
            return ResponseEntity.notFound().build();
        }

        qualificationService.deleteById(callsign);
        return ResponseEntity.ok().build();
    }
}
