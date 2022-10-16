

<h1> 1.Getting Started. </h1>

<h2>A. The drone app is built using Java Spring boot application. To get the source code for the drone up write the commanad beelow </h2>

 git clone https://github.com/matrixmosaic/drone.git
 
<p>

i.  You can run the source in development mode using any editor of your choice. 
ii.  H2 is an inmerory database in this case. The schema are auto populated in the database during initialization stage.
- The schema definition (with all the tables) file is placed in scr/main/resource/db/schema.sql . 
- Some initial configuration data such as admin credentials and model definitions are also auto populate into the database during the initial bootup stage. The initial data file is place   in scr/main/resource/db/data.sql.
iii.  The entire database schema for the drone app is place in src/resurce/drone_db.sql. This one is only for review purposes. With the data provided in 1 .a.ii above the application is completely ready to go.
- With the files above, we have a complete package to work with our drone application as with any other spring boot application.

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

Drone app service End-points

<pre>
[
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
                "produces": [
                    {
                        "mediaType": "application/json",
                        "negated": false
                    }
                ]
            }
        }
    },
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
    },
    {
        "handler": "com.musala.drones.controller.exceptions.DroneAppErrorController#handleError(HttpServletRequest)",
        "predicate": "{ [/error]}",
        "details": {
            "handlerMethod": {
                "className": "com.musala.drones.controller.exceptions.DroneAppErrorController",
                "name": "handleError",
                "descriptor": "(Ljavax/servlet/http/HttpServletRequest;)Ljava/lang/String;"
            },
            "requestMappingConditions": {
                "methods": [],
                "patterns": [
                    "/error"
                ],
                "produces": []
            }
        }
    },
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
                "produces": [
                    {
                        "mediaType": "application/json",
                        "negated": false
                    }
                ]
            }
        }
    },
    {
        "handler": "com.musala.drones.controller.operation.DispatchController#getDroneBatteryCapacityBySerialNumber(DroneDto)",
        "predicate": "{POST [/api/ops/drone/capacity/get], produces [application/json]}",
        "details": {
            "handlerMethod": {
                "className": "com.musala.drones.controller.operation.DispatchController",
                "name": "getDroneBatteryCapacityBySerialNumber",
                "descriptor": "(Lcom/musala/drones/dto/operation/DroneDto;)Lorg/springframework/http/ResponseEntity;"
            },
            "requestMappingConditions": {
                "methods": [
                    "POST"
                ],
                "patterns": [
                    "/api/ops/drone/capacity/get"
                ],
                "produces": [
                    {
                        "mediaType": "application/json",
                        "negated": false
                    }
                ]
            }
        }
    },
    {
        "handler": "com.musala.drones.controller.operation.DispatchController#updateDrone(DroneDto)",
        "predicate": "{POST [/api/ops/drone/update], produces [application/json]}",
        "details": {
            "handlerMethod": {
                "className": "com.musala.drones.controller.operation.DispatchController",
                "name": "updateDrone",
                "descriptor": "(Lcom/musala/drones/dto/operation/DroneDto;)Lorg/springframework/http/ResponseEntity;"
            },
            "requestMappingConditions": {
                "methods": [
                    "POST"
                ],
                "patterns": [
                    "/api/ops/drone/update"
                ],
                "produces": [
                    {
                        "mediaType": "application/json",
                        "negated": false
                    }
                ]
            }
        }
    },
    {
        "handler": "com.musala.drones.controller.operation.DispatchController#getDroneByState(DroneDto)",
        "predicate": "{POST [/api/ops/drone/state/get], produces [application/json]}",
        "details": {
            "handlerMethod": {
                "className": "com.musala.drones.controller.operation.DispatchController",
                "name": "getDroneByState",
                "descriptor": "(Lcom/musala/drones/dto/operation/DroneDto;)Lorg/springframework/http/ResponseEntity;"
            },
            "requestMappingConditions": {
                "methods": [
                    "POST"
                ],
                "patterns": [
                    "/api/ops/drone/state/get"
                ],
                "produces": [
                    {
                        "mediaType": "application/json",
                        "negated": false
                    }
                ]
            }
        }
    },
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
                "produces": [
                    {
                        "mediaType": "application/json",
                        "negated": false
                    }
                ]
            }
        }
    }
]

</pre>