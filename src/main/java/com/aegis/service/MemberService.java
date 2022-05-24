package com.aegis.service;

import com.aegis.entity.Member;
import com.aegis.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Optional<Member> findById(Long callsign) {
        return memberRepository.findById(callsign);
    }

    @Transactional
    public Member createOrUpdate(Member member){
        return memberRepository.save(member);
    }

    @Transactional
    public void deleteById(Long callsign) {
        memberRepository.deleteById(callsign);
    }
}
