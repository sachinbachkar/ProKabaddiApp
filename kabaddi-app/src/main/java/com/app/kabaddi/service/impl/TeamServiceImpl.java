package com.app.kabaddi.service.impl;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.kabaddi.entity.TeamEntity;
import com.app.kabaddi.entity.inter.model.RequestModel;
import com.app.kabaddi.entity.inter.model.ResponseModel;
import com.app.kabaddi.entity.model.Team;
import com.app.kabaddi.entity.repository.TeamEntityRepository;
import com.app.kabaddi.service.ITeamService;

@Component("TeamServiceImpl")
public class TeamServiceImpl implements ITeamService
{
  
  @Autowired
  private TeamEntityRepository repository;
  
  @Autowired
  private DozerBeanMapper mapper;
  
  private Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
  
  @Override
  public ResponseModel add ( RequestModel model )
  {
    TeamEntity entity = mapper.map(model, TeamEntity.class);
    repository.save(entity);
    logger.info(" Persisted entity : {}", entity);
    return mapper.map(entity, Team.class);
  }
  
  
  @Override
  public List<Team> add ( List<Team> teamList )
  {
    teamList.forEach( team -> add(team));
    return teamList;
  }
  
}
