# Rest web service

This repository contains a Java EE Spring Boot app wich handles REST GET and POST request.It is connected to a MySQL database wich stores a tree with each node having an ID,PID(Parent id),name and height field.Both the REST service and the MySQL database are running separately inside two docker containers  

-SQL_scripts folder contains initialization sql scripts for the database.  
-checkDb.sh is a script wich checks if the MySQL container has started and then starts web app inside the conatainer  
-Dockerfile is used to build the web app image with openjdk8  
-docker-compose.yml contains the config for web app and database containers  

# Prerequisites  

Docker version 18.09.0  
docker-compose version 1.21.2

# Build and run  

After database initialization the first 6 row of the tree table will look like this.PID column holds the parent of node.For example
node-1 parent is root-0 and node-3 parent is node-1.

TABLE tree

| ID  |  name   |  PID  | height|      
|:---:|  :---:  | :---: | :---: |   
| 1   | 'root-0'|   0   |   0   |   
| 2   | 'node-1'|   1   |   1   |  
| 3   | 'node-2'|   1   |   1   |  
| 4   | 'node-3'|   2   |   2   |  
| 5   | 'node-4'|   2   |   2   |  
| 6   | 'node-5'|   3   |   2   |  
  
 


###### Change directory to api folder 
**cd api**    
**mvn clean install**

###### The http web app service image is build from openjdk:8.    
**sudo docker -f Dockerfile -t docker-http .**  
**sudo docker-compose up**    

###### Get children for specified node      
**curl -i http://localhost:8080/getChildren/{ID}**

###### It will return the name of all the children nodes in JSON format   
**curl -i http://localhost:8080/getChildren/2**

###### Result  
{
  node-3 
  node-4 
}  

###### Change parent of a node      
**curl -i -X POST http://localhost:8080/changeParent/{nodeID}/{parentID}**    



