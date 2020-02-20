package com.app.kabaddi.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.kabaddi.entity.model.Match;
import com.app.kabaddi.service.IScheduleService;

@RestController
@RequestMapping(path = "/prokabadi/schedule")
public class MatchScheduleController
{
  
  @Autowired
  private IScheduleService scheduleService;
  
  /**
   * below invocation generate schedule of matches. 
   * if there no team added then what? 
   * for time being empty array enough?
   * will do useful using client code ?
   * 
   *  default tournament start date ?
   *  will accept from client startDate ?
   *  
   *  FIXME
   */
  @GetMapping({"/generate","/generate/{startDate}"})
  public List<Match> generate(@PathVariable Optional<String> startDate)
  {
    /**
     *  @ScopeCodeImprovment
     *  ? Pattern can be statically defined
     *  ? Can we handle ParseException here 
     *  @Ans Sure we will, but not now 
     *  
     *  FIXME
     */
    LocalDateTime startDateTime = startDate.isPresent() ? LocalDateTime.parse(startDate.get(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")): LocalDateTime.now().plusDays(1);
    return scheduleService.initStartDateAndGenerateSchedule(startDateTime);
  }
  
}
