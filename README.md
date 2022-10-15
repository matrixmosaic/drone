
Drone app service End-points
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