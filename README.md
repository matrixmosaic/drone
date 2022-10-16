

<h1> 1.Getting Started. </h1>

<h2>A. The drone app is built using Java Spring boot application. To get the source code for the drone up write the commanad beelow </h2>

  `git clone https://github.com/matrixmosaic/drone.git`
  
 
<p>

i.  You can run the source in development mode using any editor of your choice. 
ii.  H2 is an inmerory database in this case. The schema are auto populated in the database during initialization stage.

- the default database user credentials are configured in application.properies file as below

` spring.datasource.username=musala `
` spring.datasource.password=musala2022`

- The schema definition (with all the tables) file is placed in ` scr/main/resource/db/schema.sql `. 
- Some initial configuration data such as admin credentials and model definitions are also auto populate into the database during the initial bootup stage. The initial data file is place   in ` scr/main/resource/db/data.sql `.

iii.  The entire database schema for the drone app is place in ` src/resurce/drone_db.sql `. This one is only for review purposes. With the data provided in 1 .a.ii above the application is completely ready to go.
- With the files above, we have a complete package to work with our drone application as with any other spring boot application.

iv. All the potential API are protected / authenticated via JWT with Spring Security.
- You must timed API token to access each service.
- A default user with a role is added into the system. The credentials for the admin user are as follows.
` username: musala `
` password: musala@2022 `


- The default domain url on the local machine where the drone app is configure to  run  is at a  `  http://localhost:9090/drones `
- We will use this default url for all our examples below. ( All the values are configuratie via the application.properties file)

- To get the auth token post the json with the correct user details, default admin credentials in this case. The authentication end point is at
` http://localhost:9090/drones/api/auth/authenticate`

see the image below, a post request to get the auth token

![ drone app auth token](https://musala-drones.s3.amazonaws.com/models/generate-token.PNG) 

- To be authhenticated and authorized to access the protected end points send each request having the header with and attribute Authorization whose value is generated from the valid user above. See the sample below on how to incooperate auth token on each request.

![general schema](https://musala-drones.s3.amazonaws.com/models/auth_header.PNG) 

- To test all the implemented end point and understand their request and response structure and datatypes you could use swagger whose end points are permitted for testing purposes. Swagger will give all the registered end points and populate the fieds with dummy data for testing purposes.
-Swagger end point is configured to be at;
`http://localhost:9090/drones/docs/swagger-ui/swagger-ui/index.html`

Snapshop of swagger interface(Each end point could be expanded to view the both the schema and sample values for both request and response data)

![swagger](https://musala-drones.s3.amazonaws.com/models/drone-app-swagger.PNG) 

v. All the unit testing are also within the folder ` source/test/java`  in the base package ` com.musala.drones `

</p>


<h2>B. To build the solution(producing a jar file), Navigate to the source base directory and type the command </h2>
<p>

mvn clean install.

 A jar file should be generated( containes embedded h2 database with it).

 </p>







<h1> 2.MODEL </h1>
There are three shemas for the drone database tables.


<h2>A. DN_MAIN </h2>

- Here is where we have placed all the main domain models (prefixed with dm i.e dm_{tableName} }
-The entity relationship design for the models uder this schema is as displayed below.

![dn_main schema](https://musala-drones.s3.amazonaws.com/models/dn_main_schema.png)  

<h2>B. DN_AUD</h2>
<p>- Here is where we have placed all the audit tables that corresponds to each domain models which requires audit, their names corresponds to the domain models in the DN_MAIN schema but differ only in postfix ( postfixed with aud i.e aud_{tableName} }
-The entity relationship design for the models uder this schema is as displayed below.
</p>

![dn_aud schema](https://musala-drones.s3.amazonaws.com/models/dn_aud_schema.png) 





<h2>C. DN_DICT </h2>
<p>
- Here is where we have placed all definition tables (prefixed with df i.e df_{tableName} } , the common attributes in these tables are name, code and description 


</p>


Generally the entity relationship diagram below shows the design of the entire database.


![general schema](https://musala-drones.s3.amazonaws.com/models/drone_db_general.png) 



  





<h1> 3. The Drone App API End point services </h1>

Drone app has several service End-points implemented per the documented requirements.

- To be authenticated and authorized to access the protected end points send each request having the header with and attribute Authorization whose value is generated from the valid user above. See the sample below on how to incooperate auth token on each request.

- To test all the implemented end point and understand their request and response structure and datatypes you could use swagger whose end points are permitted for testing purposes. Swagger will give all the registered end points and populate the fieds with dummy data for testing purposes.
-Swagger end point is configured to be at;
`http://localhost:9090/drones/docs/swagger-ui/swagger-ui/index.html`

- To incooperate the authorization token with header in swagger put toen in the Authentication parameter as described here  `https://swagger.io/docs/specification/describing-parameters/#header-parameters`

Drone app has the following end points.

1. /api/ops/drone/register

This method exposes the service end point for registering a new drone
With default settings , the request and response schema can be viewed at 
[http://localhost:9090/drones/docs/swagger-ui/swagger-ui/index.html#/dispatch-controller/getDroneWithLoadItems ]( http://localhost:9090/drones/docs/swagger-ui/swagger-ui/index.html#/dispatch-controller/registerDrone )

- Descriptiom

```json

{
        "handler": "com.musala.drones.controller.operation.DispatchController#registerDrone(DroneDto)",
        "predicate": "{POST [/api/ops/drone/register], produces [application/json]}",
        "details": {
            "handlerMethod": {
                "className": "com.musala.drones.controller.operation.DispatchController",
                "name": "registerDrone",
                "descriptor": "(Lcom/musala/drones/dto/operation/DroneDto;)Lorg/springframework/http/ResponseEntity;"
            },
            "requestMappingConditions": {
                "methods": [
                    "POST"
                ],
                "patterns": [
                    "/api/ops/drone/register"
                ],
                 "consumes": [
                    {
                        "mediaType": "application/json",
                       
                    },
                "produces": [
                    {
                        "mediaType": "application/json",
                       
                    }
                ]
            }
        }
    }
```




2. /api/ops/drone/load

This method exposes the service end point for loading a drone with medication items;
With default settings , the request and response schema can be viewed at 
[http://localhost:9090/drones/docs/swagger-ui/swagger-ui/index.html#/dispatch-controller/getDroneWithLoadItems]( http://localhost:9090/drones/docs/swagger-ui/swagger-ui/index.html#/dispatch-controller/loadDrone )

- Descriptiom

```json
{
        "handler": "com.musala.drones.controller.operation.DispatchController#loadDrone(TripDto)",
        "predicate": "{POST [/api/ops/drone/load], produces [application/json]}",
        "details": {
            "handlerMethod": {
                "className": "com.musala.drones.controller.operation.DispatchController",
                "name": "loadDrone",
                "descriptor": "(Lcom/musala/drones/dto/operation/TripDto;)Lorg/springframework/http/ResponseEntity;"
            },
            "requestMappingConditions": {
                "methods": [
                    "POST"
                ],
                "patterns": [
                    "/api/ops/drone/load"
                ],
                 "consumes": [
                    {
                        "mediaType": "application/json",
                       
                    },
                "produces": [
                    {
                        "mediaType": "application/json",
                        "negated": false
                    }
                ]
            }
      
```



3. /api/ops/drone/items/get/{serialNumber}


This method exposes the service end point for checking loaded medication items for a given drone. It takes the drone serial Number as path parameter
With default settings , the request and response schema can be viewed at  
[http://localhost:9090/drones/docs/swagger-ui/swagger-ui/index.html#/dispatch-controller/getDroneWithLoadItems ]( http://localhost:9090/drones/docs/swagger-ui/swagger-ui/index.html#/dispatch-controller/getDroneWithLoadItems )

- Descriptiom

```json
{
        "handler": "com.musala.drones.controller.operation.DispatchController#getDroneWithLoadItems(String)",
        "predicate": "{GET [/api/ops/drone/items/get/{serialNumber}], produces [application/json]}",
        "details": {
            "handlerMethod": {
                "className": "com.musala.drones.controller.operation.DispatchController",
                "name": "getDroneWithLoadItems",
                "descriptor": "(Ljava/lang/String;)Lorg/springframework/http/ResponseEntity;"
            },
            "requestMappingConditions": {
                "methods": [
                    "GET"
                ],
                "patterns": [
                    "/api/ops/drone/items/get/{serialNumber}"
                ],
                "consumes": [
                    {
                        "mediaType": "application/json",
                       
                    },
                "produces": [
                    {
                        "mediaType": "application/json",
                        "negated": false
                    }
                ]
            }
        }
    }
```



4. /api/ops/drone/state/get/{droneState}


This method exposes the service end point for checking available drones for loading;. It takes the drone drone State (IDLE) as path parameter
With default settings , the request and response schema can be viewed at  
[http://localhost:9090/drones/docs/swagger-ui/swagger-ui/index.html#/dispatch-controller/getDroneWithLoadItems ]( http://localhost:9090/drones/docs/swagger-ui/swagger-ui/index.html#/dispatch-controller/getDroneWithLoadItems )

- Descriptiom

```json
{
        "handler": "com.musala.drones.controller.operation.DispatchController#getDroneByState(DroneDto)",
        "predicate": "{GET [/api/ops/drone/state/get/{droneState}], produces [application/json]}",
        "details": {
            "handlerMethod": {
                "className": "com.musala.drones.controller.operation.DispatchController",
                "name": "getDroneByState",
                "descriptor": "(Lcom/musala/drones/dto/operation/DroneDto;)Lorg/springframework/http/ResponseEntity;"
            },
            "requestMappingConditions": {
                "methods": [
                    "GET"
                ],
                "patterns": [
                    "/api/ops/drone/state/get/{droneState}"
                ],
                 "consumes": [
                    {
                        "mediaType": "application/json",
                       
                    },
                "produces": [
                    {
                        "mediaType": "application/json",
                        "negated": false
                    }
                ]
            }
        }
    }
```


5. /api/ops/drone/capacity/get/{serialNumber}


This method exposes the service end point for checking drone battery level for a given drone. It takes the drone drone serialNumber as path parameter
With default settings , the request and response schema can be viewed at 
[http://localhost:9090/drones/docs/swagger-ui/swagger-ui/index.html#/dispatch-controller/getDroneWithLoadItems ]( http://localhost:9090/drones/docs/swagger-ui/swagger-ui/index.html#/dispatch-controller/getDroneByState ) 

- Descriptiom

```json
{
        "handler": "com.musala.drones.controller.operation.DispatchController#getDroneBatteryCapacityBySerialNumber(DroneDto)",
        "predicate": "{GET [/api/ops/drone/capacity/get/{serialNumber}], produces [application/json]}",
        "details": {
            "handlerMethod": {
                "className": "com.musala.drones.controller.operation.DispatchController",
                "name": "getDroneBatteryCapacityBySerialNumber",
                "descriptor": "(Lcom/musala/drones/dto/operation/DroneDto;)Lorg/springframework/http/ResponseEntity;"
            },
            "requestMappingConditions": {
                "methods": [
                    "GET"
                ],
                "patterns": [
                    "/api/ops/drone/capacity/get/{serialNumber}"
                ],
                    "consumes": [
                    {
                        "mediaType": "application/json",
                       
                    },
                "produces": [
                    {
                        "mediaType": "application/json",
                        "negated": false
                    }
                ]
            }
        }
    }
```

6. /api/auth/authenticate
This method takes in the principal credentials, validates them and issues timed tokens for authorized users.

With default settings , the request and response schema can be viewed at 
[http://localhost:9090/drones/docs/swagger-ui/swagger-ui/index.html#/lands-remote-call-auth-controller/authenticateRemotePrincipal]( http://localhost:9090/drones/docs/swagger-ui/swagger-ui/index.html#/lands-remote-call-auth-controller/authenticateRemotePrincipal ) 
- Descriptiom

```json

 {
        "handler": "com.musala.drones.controller.auth.LandsRemoteCallAuthController#authenticateRemotePrincipal(RemoteUserReq)",
        "predicate": "{POST [/api/auth/authenticate], produces [application/json]}",
        "details": {
            "handlerMethod": {
                "className": "com.musala.drones.controller.auth.LandsRemoteCallAuthController",
                "name": "authenticateRemotePrincipal",
                "descriptor": "(Lcom/musala/drones/dto/remote/RemoteUserReq;)Lorg/springframework/http/ResponseEntity;"
            },
            "requestMappingConditions": {
                "methods": [
                    "POST"
                ],
                "patterns": [
                    "/api/auth/authenticate"
                ],
                "produces": [
                    {
                        "mediaType": "application/json",
                        "negated": false
                    }
                ]
            }
        }
    }

```



