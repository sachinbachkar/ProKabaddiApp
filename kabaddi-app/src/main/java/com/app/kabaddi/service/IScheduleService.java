package com.app.kabaddi.service;

import java.time.LocalDateTime;
import java.util.List;

import com.app.kabaddi.entity.model.Match;
/**
 * 
 * Match Schedule Service specification.
 *
 */
public interface IScheduleService
{
  /**
   * generate schedule for deafult tournament start date 
   * default tournament-start date 
   * defaultDate - LocalDateTime.now().plusDays(1)
   */
  public List<Match> initStartDateAndGenerateSchedule(LocalDateTime startDateTime); 
}
