package com.wizerdshins.surveys.controller;

import com.wizerdshins.surveys.domain.Question;
import com.wizerdshins.surveys.domain.Survey;
import com.wizerdshins.surveys.repository.QuestionRepository;
import com.wizerdshins.surveys.repository.SurveyRepository;
import com.wizerdshins.surveys.service.SurveyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SurveysController {

    private SurveyRepository surveyRepository;
    private QuestionRepository questionRepository;
    private SurveyService surveyService;

    public SurveysController(SurveyRepository surveyRepository,
                             QuestionRepository questionRepository,
                             SurveyService surveyService) {
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.surveyService = surveyService;
    }

    @GetMapping("show{tag}")
    public Page<Survey> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                               @RequestParam(name = "tag", defaultValue = "", required = false) String tag) {

        System.out.println("Tag: " + tag);
        return surveyService.showAll(pageable, tag);
    }

    @GetMapping("{id}")
    public Survey getOne(@PathVariable("id") Survey survey) {
        return survey;
    }

    @PostMapping("add/survey")
    public Survey addSurvey(@RequestBody Survey survey) {
        return surveyRepository.save(survey);
    }


    @PostMapping("add/question/{id}")
    public Question addQuestion(@PathVariable("id") Long surveyId,
                                @RequestBody Question question) {
        question.setSurveyId(surveyId);
        return questionRepository.save(question);
    }

    @PostMapping("update/{id}")
    public Survey edit(@PathVariable("id") Survey persistSurvey,
                       @RequestBody Survey updatedSurvey) {
        BeanUtils.copyProperties(updatedSurvey, persistSurvey, "id", "beginDate");
        return surveyRepository.save(persistSurvey);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") Survey survey) {
        surveyRepository.delete(survey);
    }
}
