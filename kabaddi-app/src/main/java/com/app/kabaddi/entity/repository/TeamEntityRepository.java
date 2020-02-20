package com.app.kabaddi.entity.repository;

import org.springframework.data.repository.CrudRepository;
import com.app.kabaddi.entity.TeamEntity;


/**
 *  Team Repo - to persist Team
 */
public interface TeamEntityRepository extends CrudRepository<TeamEntity, Long>
{
  
}
