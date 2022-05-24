package com.aegis.controller;


import com.aegis.entity.Member;
import com.aegis.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/members"})
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> findAll() {
        return memberService.findAll();
    }

    @GetMapping("/{callsign}")
    public ResponseEntity<Member> findById(@PathVariable long callsign) {
        Optional<Member> member = memberService.findById(callsign);

        if (!member.isPresent()) {
            log.error("Member with callsign " + callsign + " does not exist.");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(member.get());
    }

    @PostMapping
    public ResponseEntity<Member> create(@Valid @RequestBody Member member) {
       return ResponseEntity.ok(memberService.createOrUpdate(member));
    }

    @PutMapping("/{callsign}")
    public ResponseEntity<Member> update(@PathVariable long callsign, @Valid @RequestBody Member member) {
        Optional<Member> tempMember = memberService.findById(callsign);

        if (!tempMember.isPresent()) {
            log.error("Member with callsign " + callsign + " does not exist for updating.");
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(memberService.createOrUpdate(member));
    }

    @DeleteMapping("/{callsign}")
    public void deleteById(@PathVariable long callsign) {
        Optional<Member> tempMember = memberService.findById(callsign);

        if (!tempMember.isPresent()) {
            log.error("Member with callsign " + callsign + " does not exist for deletion.");
            return;
        }

        memberService.deleteById(callsign);
    }

}
