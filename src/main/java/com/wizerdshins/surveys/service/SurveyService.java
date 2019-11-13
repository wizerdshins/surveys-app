package com.wizerdshins.surveys.service;

import com.wizerdshins.surveys.domain.Survey;
import com.wizerdshins.surveys.repository.SurveyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

    private SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Page<Survey> showAll(Pageable pageable, String tag) {

        if (tag != null && !tag.isEmpty()) {
            if (tag.equalsIgnoreCase("title")) {
                return surveyRepository.findAllOrderByTitle(pageable);
            } else if (tag.equalsIgnoreCase("date")) {
                return surveyRepository.findAllOrderByBeginDate(pageable);
            }
        }
        return surveyRepository.findAll(pageable);
    }


}
