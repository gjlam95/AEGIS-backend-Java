package com.aegis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@Data
public class QualificationMemberKey implements Serializable {

    @Column(name = "qualification_id")
    private Long qualificationId;

    @Column(name = "member_id")
    private Long memberId;

}
