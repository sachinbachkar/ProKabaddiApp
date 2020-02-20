package com.app.kabaddi;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.kabaddi.entity.model.Match;
import com.app.kabaddi.entity.model.Team;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class KabaddiappApplicationHttpRequestTest
{
   @LocalServerPort
   private int port;
   
   @Autowired
   private TestRestTemplate restTemplate;
    
   /**
    * dummy test for adding single team
    */
   @Test
   public void add()
   {
     Team team = new Team();
     team.setId(1);
     team.setName("TestTeam");
     team.setDescription("TestDescription");
     team.setCity("testCity");
     HttpEntity<Team> request = new HttpEntity<Team>(team);
     assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/prokabadi/team/add", request,
         Team.class)).isEqualTo(team);
   }
   
   /**
    * test for bulk add
    */
   @Test
   public void addAll() throws JsonParseException, JsonMappingException, IOException
   {
      ClassLoader loader = getClass().getClassLoader();
      File dataFourTeamJson = new File(loader.getResource("data_4_teams.json").getFile());
      ObjectMapper objectMapper = new ObjectMapper();
      List<Team> teamList = objectMapper.readValue(dataFourTeamJson, new TypeReference<List<Team>>(){});
      HttpEntity<List<Team>> dataRequest = new HttpEntity<List<Team>>(teamList); 
      ResponseEntity<List<Team>> returnTeamList = restTemplate.exchange("http://localhost:" + port + "/prokabadi/team/add/all", HttpMethod.POST, dataRequest,new ParameterizedTypeReference<List<Team>>() {});
      assertThat(teamList).isEqualTo(returnTeamList.getBody());
      
   }
   
   /**
    * 
    *  test for bulk add
    *  test generate schedule.
    */
   @Test
   public void generateSchedule() throws JsonParseException, JsonMappingException, IOException
   {
      ClassLoader loader = getClass().getClassLoader();
      File dataFourTeamJson = new File(loader.getResource("data_4_teams.json").getFile());
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.findAndRegisterModules();
      List<Team> teamList = objectMapper.readValue(dataFourTeamJson, new TypeReference<List<Team>>(){});
      HttpEntity<List<Team>> dataRequest = new HttpEntity<List<Team>>(teamList); 
      ResponseEntity<List<Team>> returnTeamList = restTemplate.exchange("http://localhost:" + port + "/prokabadi/team/add/all", HttpMethod.POST, dataRequest,new ParameterizedTypeReference<List<Team>>() {});
      assertThat(teamList).isEqualTo(returnTeamList.getBody());
      File dataFourTeamScheduleJson = new File(loader.getResource("data_4_team_schedule.json").getFile());
      List<Match> matchList = objectMapper.readValue(dataFourTeamScheduleJson,  new TypeReference<List<Match>>(){});
      ResponseEntity<List<Match>> returnMatchList = restTemplate.exchange("http://localhost:" + port + "/prokabadi/schedule/generate/2019-03-04 11:30", HttpMethod.GET, null, new ParameterizedTypeReference<List<Match>>() {});
      assertThat(matchList).isEqualTo(returnMatchList.getBody());
   }
   
    /**
     * 
     * FIXME generate for schedule for dynamic date
     */
}
