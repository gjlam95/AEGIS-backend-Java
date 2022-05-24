package com.aegis.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NonNull for the RequiredArgConstructor
    @NonNull private String name;
    private boolean qualified;

    @OneToMany(mappedBy = "qualification")
    private Set<QualificationMemberJoin> qualificationMemberQuantity;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
