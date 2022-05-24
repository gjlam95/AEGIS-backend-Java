package com.aegis.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@EntityListeners(value = AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String callsign;

    @NonNull
    private String squadron;

    @NonNull
    private String role;

    private int duty_count;

    @OneToMany(mappedBy = "member")
    private Set<QualificationMemberJoin> qualificationMemberQuantity;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

}
