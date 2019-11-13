package com.wizerdshins.surveys.service;

import com.wizerdshins.surveys.domain.Question;
import com.wizerdshins.surveys.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question add(Long surveyId, Question question) {
        question.setSurveyId(surveyId);
        return questionRepository.save(question);
    }
}
