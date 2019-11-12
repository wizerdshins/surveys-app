package com.wizerdshins.surveys.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column(name = "begin_date")
    private LocalDateTime beginDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "activity")
    private boolean isActivity;

    @OneToMany(
            mappedBy = "survey",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    private List<Question> questions;

    @Override
    public String toString() {
        return "Id: " + id +
                ", title: " + title +
                ", begin: " + beginDate +
                ", end: " + endDate +
                ", activity: " + isActivity;
    }

}
