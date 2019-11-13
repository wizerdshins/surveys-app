package com.wizerdshins.surveys.controller;

import com.wizerdshins.surveys.domain.Question;
import com.wizerdshins.surveys.domain.Survey;
import com.wizerdshins.surveys.repository.QuestionRepository;
import com.wizerdshins.surveys.repository.SurveyRepository;
import com.wizerdshins.surveys.service.QuestionService;
import com.wizerdshins.surveys.service.SurveyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survey")
@Api(value = "Survey Controller", description = "Main controller allows to use basic CRUD operations")
public class SurveysController {

    private SurveyService surveyService;
    private QuestionService questionService;

    public SurveysController(SurveyService surveyService,
                             QuestionService questionService) {
        this.surveyService = surveyService;
        this.questionService = questionService;
    }

    @ApiOperation(value = "Show all surveys. " +
            "Tag 'title' or 'data' will be sorting results (f.e. 'tag=?'). " +
            "It's not required",
            response = Page.class)
    @GetMapping("show{tag}")
    public Page<Survey> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                               @RequestParam(name = "tag", defaultValue = "", required = false) String tag) {
        return surveyService.showAll(pageable, tag);
    }

    @ApiOperation(value = "Get Survey by ID", response = Survey.class)
    @GetMapping("{id}")
    public Survey getOne(@PathVariable("id") Survey survey) {
        return survey;
    }

    @ApiOperation(value = "Add new Survey")
    @PostMapping("add/survey")
    public Survey addSurvey(@RequestBody Survey survey) {
        return surveyService.save(survey);
    }

    @ApiOperation(value = "Add new Question; param ID points to parent Survey")
    @PostMapping("add/question/{id}")
    public Question addQuestion(@PathVariable("id") Long surveyId,
                                @RequestBody Question question) {
        return questionService.add(surveyId, question);
    }

    @ApiOperation(value = "Updated Survey by ID")
    @PostMapping("update/{id}")
    public Survey edit(@PathVariable("id") Survey persistSurvey,
                       @RequestBody Survey updatedSurvey) {
        return surveyService.edit(persistSurvey, updatedSurvey);
    }

    @ApiOperation(value = "Delete Survey by ID")
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") Survey survey) {
        surveyService.delete(survey);
    }
}
