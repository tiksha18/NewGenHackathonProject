# NewGenHackathonProject


Vehicle Recommendation System


TechStack used - SpringBoot, JPA/Hibernate, MySQL


End Points exposed -
1. For adding Vehicle data  -  /add/vehicles - POST method
2. For fetching nearest vehicles data  -  /recommend/nearest/vehicles  - GET method


Parameters - 
1. Manadatory params - LocationId and Number of passengers
2. Optional param - Vehicle Category 


Added functionalities - 
1. If user only enters LocationId and Number of passengers, then he will get recommendation based on nearest distance and if the vehicles are available as per passenger requirements
2. If user enters vehicle category along with mandatory params, then he will get recommendation only for that particular vehicle category and nearest distance along with if the vehicles are available as per passenger requirements
3. Created Global Exception Handler
4. Handled edge cases while fetching nearest available vehicles
