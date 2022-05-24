package com.aegis.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class QualificationMemberJoin {

    @EmbeddedId
    private QualificationMemberKey id;

    @ManyToOne
    @MapsId(value = "qualification_id")
    @JoinColumn(name = "qualification_id")
    private Qualification qualification;

    @ManyToOne
    @MapsId(value = "member_id")
    @JoinColumn(name = "member_id")
    private Member member;

    @NonNull
    private int quantity;
}
