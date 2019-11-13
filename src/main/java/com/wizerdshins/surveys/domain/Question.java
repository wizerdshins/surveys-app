package com.wizerdshins.surveys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column
    private String text;
    @Column(name = "survey_id")
    private Long surveyId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "survey_id", insertable = false, updatable = false)
    private Survey survey;

    @Override
    public String toString() {
        return "Id: " + id +
                ", text: " + text;
    }

}
