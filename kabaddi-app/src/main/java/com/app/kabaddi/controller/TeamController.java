package com.app.kabaddi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.kabaddi.entity.inter.model.ResponseModel;
import com.app.kabaddi.entity.model.Team;
import com.app.kabaddi.service.ITeamService;

@RestController
@RequestMapping(path = "/prokabadi/team")
public class TeamController
{
  
  @Autowired
  private ITeamService service;
  
  /**
   * persist single team
   */
  @PostMapping(path = "/add", consumes="application/json", produces="application/json")
  public ResponseModel add ( @RequestBody Team team )
  {
    return service.add(team);
  }
  
  /**
   * persist team list
   */
  @PostMapping(path="/add/all", consumes="application/json", produces="application/json")
  public List<Team> addAll(@RequestBody List<Team> teamList)
  {
     return service.add(teamList);
  }
  
  /**
   * FIXME get, getAll, update, updateAll
   */
}
