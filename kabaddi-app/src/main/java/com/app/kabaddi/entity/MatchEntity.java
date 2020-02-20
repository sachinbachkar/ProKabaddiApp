package com.app.kabaddi.entity;

import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.app.kabaddi.util.LocalDateTimeAttributeConverter;

import lombok.Data;

@Entity(name = "kabddi_match")
@Data
public class MatchEntity
{
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long homeTeamId;
  private Long awayTeamId;
  
  @Convert(converter=LocalDateTimeAttributeConverter.class)
  private LocalDateTime matchDate;
  
  /**
   * 
   * is below data-member needed ? 
   * we have home team here with city right?
   * but wait!!, we can have another city as location ?
   * 
   * debatable ?
   * FIXME
   */
  private String location;
}
