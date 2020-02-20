package com.app.kabaddi;

import java.util.Collections;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *  @author Sachin Bachkar [sachinbachkar333@gmail.com] 
 *  
 *  Application - to generate schedule for pro-kabaddi matches.
 *  
 */
@SpringBootApplication
public class KabaddiappApplication
{
  
  public static void main ( String[] args )
  {
    SpringApplication.run(KabaddiappApplication.class, args);
  }
  
  @Bean
  public DozerBeanMapper dozerBean()
  {
    DozerBeanMapper dozerBean= new DozerBeanMapper();
    dozerBean.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
    return dozerBean;
  }
}
