package com.app.kabaddi.entity.model;

import java.time.LocalDateTime;

import com.app.kabaddi.entity.inter.model.ResponseModel;

import lombok.Data;

/**
 *  Match Details
 */
@Data
public class Match implements ResponseModel
{
  
  private static final long serialVersionUID = 1004193081628327382L;
  private long id;
  private long homeTeamId;
  private long awayTeamId;
  private LocalDateTime matchDate;
  private String location;
  
  /**
   *  check team relation
   *  like two matches are related based on there teams? 
   */
  public boolean isTeamMatch ( Match other )
  {
    return (other != null && other instanceof Match)
        && (this.homeTeamId == other.homeTeamId || this.homeTeamId == other.awayTeamId
            || this.awayTeamId == other.awayTeamId || this.awayTeamId == other.homeTeamId);
  }
  
}
