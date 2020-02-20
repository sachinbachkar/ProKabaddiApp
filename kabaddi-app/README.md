# ======== Problem Statement =======
Build a RESTful service that generates the round robin schedule for the PRO-Kabaddi event that conforms to the following constraints
- Accept N number of teams
- Each team must play against every other team once home and away
- Maximum 2 matches per day are allowed
- No team should play on consecutive days

# ======== Solution =======

# Step-1: Build
- Clone GIT Repository locally using this URL ->  https://github.com/pankaj358/kabaddiapp.git
- Import project in eclipse as maven/spring/springboot
- Open console for parent folder 
- Fire "run mvn clean & build"
- After build success you will have package inside {otherPath}/kabaddiapp/target


# Step-2: Run
- Pick the jar from target [kabaddiapp-0.0.1.jar]
- move where you want to run provided we have java8 install on target location
- run using java -jar kabaddiapp-0.0.1.jar

# Step-3: Test
- Add team using below api 
  - "http://localhost:8080/prokabadi/team/add/" -> single Add - HttpMethodType.POST
  - "http://localhost:8080/prokabadi/team/add/all" -> bulk addd - HttpMethodType.POST
    e.g 
      [  
         {  
           "id":1,
           "name":"TeamMumbai",
           "description":"Mumbai Team",
           "city":"Mumbai"
         },
         {  
           "id":2,
           "name":"TeamPune",
           "description":"Pune Team",
           "city":"Pune"
         },
         {  
           "id":3,
           "name":"TeamNagpur",
           "description":"Nagpur Team",
           "city":"Nagpur"
         },
         {  
           "id":4,
           "name":"TeamBhandara",
           "description":"Bhandara Team",
           "city":"Bhandara"
        }
     ]
- Generate Schedule using below url
   "http://localhost:8080/prokabadi/schedule/generate/2019-03-04 11:30" - HttpMethodType - Get