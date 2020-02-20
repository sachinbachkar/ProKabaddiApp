package com.app.kabaddi.service;

import java.util.List;

import com.app.kabaddi.entity.inter.model.RequestModel;
import com.app.kabaddi.entity.inter.model.ResponseModel;
import com.app.kabaddi.entity.model.Team;

/**
 * Team crud-like-service specification.
 */
public interface ITeamService
{
   /**
    * persist team
    */
   public ResponseModel add(RequestModel model);  
   
   /**
    * persist team list
    */
   public List<Team> add(List<Team> model);

}
