package com.app.kabaddi.entity.model;

import com.app.kabaddi.entity.inter.model.RequestModel;
import com.app.kabaddi.entity.inter.model.ResponseModel;
import lombok.Data;

/**
 *  Team details.  
 */
@Data
public class Team implements RequestModel,ResponseModel
{
  private static final long serialVersionUID = 1L; 
  private long id;
  private String name;
  private String description;
  private String city;

}
