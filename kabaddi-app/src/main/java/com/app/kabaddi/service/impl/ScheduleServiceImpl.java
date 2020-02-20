package com.app.kabaddi.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.kabaddi.entity.MatchEntity;
import com.app.kabaddi.entity.TeamEntity;
import com.app.kabaddi.entity.model.Match;
import com.app.kabaddi.entity.model.Team;
import com.app.kabaddi.entity.repository.MatchEntityRepository;
import com.app.kabaddi.entity.repository.TeamEntityRepository;
import com.app.kabaddi.service.IScheduleService;

@Component("ScheduleServiceImpl")
public class ScheduleServiceImpl implements IScheduleService
{
  @Autowired
  private TeamEntityRepository teamRepository;
  @Autowired
  private MatchEntityRepository matchRepository;
  @Autowired
  private DozerBeanMapper mapper;
  
  public Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class.getSimpleName());
  
  /**
   *  localDateTime is tournament start date.
   *  Can we accept this from user ?
   *  What will be the  default value ?.
   */
  private  LocalDateTime startDateTime;
  
  /**
   *  Can you break generate logic ? No time for that now 
   */
  
  
  
 
  private  List<Match> generate ( )
  {
    logger.info("Generating schedule ...");
    Iterator<TeamEntity> homeTeamIterator = teamRepository.findAll().iterator();
    List<Team> homeTeamList = new ArrayList<>();
    while ( homeTeamIterator.hasNext() ){
      homeTeamList.add(mapper.map(homeTeamIterator.next(), Team.class));
    }
    
    List<Team> awayTeamList = new ArrayList<>(homeTeamList);
    List<Match> matches = new ArrayList<>();
    
    /**
     * create a cross-coupling [matches]
     * Team will act here once as home and once away
     */
    for ( Team homeTeam : homeTeamList )
    {
      for ( Team awayTeam : awayTeamList )
      {
        if ( homeTeam.getId() != awayTeam.getId() )
        {
          Match match = new Match();
          match.setAwayTeamId(awayTeam.getId());
          match.setHomeTeamId(homeTeam.getId());
          
          /**
           * will satisfied the home and away constraints
           */
          
          match.setLocation(homeTeam.getCity());
          MatchEntity matchEntity = mapper.map(match, MatchEntity.class);
          logger.info("Match Created : {}", matchEntity);
          matches.add(mapper.map(matchRepository.save(matchEntity), Match.class));
        }
      }
    }
    
    /**
     * Update the matchDate for cross match considering below constraints.
     * 
     * Maximum 2 matches per day are allowed
     * No team should play on consecutive days
     */
    while ( matches.stream().filter(match -> match.getMatchDate() == null).count() != 0 )
    {
      List<Match> pendingMatchList = matches.stream().filter(match -> match.getMatchDate() == null)
          .collect(Collectors.toList());
      
      for ( Match match : pendingMatchList )
      {
        /**
         * Collect the matches for localDateTime 
         */
        List<Match> currentDayMatchList = matches.stream()
            .filter(currentDayMatch -> currentDayMatch.getMatchDate() != null
                && currentDayMatch.getMatchDate().isEqual(startDateTime))
            .collect(Collectors.toList());
        
        /**
         * Collect the matches for localDateTime - 1  
         */
        List<Match> previousMatchList = matches.stream()
            .filter(previousDayMatch -> previousDayMatch.getMatchDate() != null
                && previousDayMatch.getMatchDate().isEqual(startDateTime.minusDays(1)))
            .collect(Collectors.toList());
        
        /**
         *  Max 2 matches per day.
         *  No team should play on consecutive days
         */
        if ( currentDayMatchList.size() < 2
            && currentDayMatchList.stream().filter(currentMatch -> currentMatch.isTeamMatch(match)).count() == 0
            && previousMatchList.stream().filter(prevMatch -> prevMatch.isTeamMatch(match)).count() == 0 ){
            match.setMatchDate(startDateTime);
            MatchEntity matchEntity = mapper.map(match, MatchEntity.class);
            matchRepository.save(matchEntity);
            logger.info(" MatchDateUpdated  : {}", match);
        }
      }
      /**
       * Let's move to next day
       */
      startDateTime = startDateTime.plusDays(1);
    }
    
    /**
     * sort list based on matchDate
     */
    matches.sort(Comparator.comparing(Match::getMatchDate));
    return matches;
  }

  private void initStartDate(LocalDateTime startDateTime)
  {
    this.startDateTime = startDateTime;
  }
  
  @Override
  public List<Match> initStartDateAndGenerateSchedule (LocalDateTime startDateTime )
  {
    initStartDate(startDateTime);
    return generate();
  }
  
}
