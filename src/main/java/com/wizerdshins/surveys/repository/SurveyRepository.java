package com.wizerdshins.surveys.repository;

import com.wizerdshins.surveys.domain.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Query("from Survey s order by s.title asc")
    Page<Survey> findAllOrderByTitle(Pageable pageable);

    @Query("from Survey s order by s.beginDate asc")
    Page<Survey> findAllOrderByBeginDate(Pageable pageable);

}
