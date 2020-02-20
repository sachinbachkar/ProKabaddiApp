package com.app.kabaddi.entity.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.kabaddi.entity.MatchEntity;

/**
 * Match Repo to persist match - details 
 */
public interface MatchEntityRepository extends CrudRepository<MatchEntity, Long>
{
  
}
