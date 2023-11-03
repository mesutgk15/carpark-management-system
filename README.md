# carpark-management-system
- Is a REST Api developed in Java with Spring Boot (2.7.5)
### GOAL
Project aims to build
up the backend technology of an application which saves the data of the vehicles in a residential complex and
manage the carpark usage and set the effiency to utmost level while providing higher security for the area of
use.
#### How to Use
We have two main services to use the application:
- Gate-service
- Carpark-service
  
  There is a api gateway to route requests between these two services. Paths to determine are as following:
  ```
  - id: gate_service_route
    uri: lb://GATE-SERVICE
    predicates:
      - Path=/gate/**
  - id: carpark_service_route
    uri: lb://CARPARK-SERVICE
    predicates:
      - Path=/carparks/**, /vehicles/**, /residents/**
  ```

  Carpark service responsible of managing data of three main entities:
  - Carpark
  - Resident
  - Entity
 
  Below is the data transfer schema of the carpark-service:
  
![image](https://github.com/mesutgk15/carpark-management-system/assets/107197063/2b8004ed-65ea-4cb3-bbfa-b6d441e67175)

We have three controllers in carpark-service:

- CarparkController
- ResidentController
- VehicleController



**CarparkController has the endpoints for CRUD**:
![image](https://github.com/mesutgk15/carpark-management-system/assets/107197063/9248d0fc-7947-4925-bed0-f9bc91db3d15)


**ResidentController has the endpoints for CRUD**:
![image](https://github.com/mesutgk15/carpark-management-system/assets/107197063/622fca5f-1a92-4823-bbcf-29017268aaf0)


**VehicleController has the endpoints for CRUD**:
![image](https://github.com/mesutgk15/carpark-management-system/assets/107197063/f259ccbe-db7e-4b1d-ae7d-c81bde9f2e3d)


**Create a carpark without defining vehicles in the carpark yet:**
![image](https://github.com/mesutgk15/carpark-management-system/assets/107197063/4ceea7ce-0279-46af-b2a3-557b21dd4ef7)


**Create new Resident object from ResidentController**
(It does not let setting a vehicle list from ResidentController, can be done via setting the owner from Vehicle object):
![image](https://github.com/mesutgk15/carpark-management-system/assets/107197063/fcef7f9d-b651-4d0c-9dce-c8f6ebbdbece)


**Response returned with the created objects DTO:** 
![image](https://github.com/mesutgk15/carpark-management-system/assets/107197063/8d6360d8-a2dc-4d34-8c06-77763438fcb3)


**Create new Vehicle object:**
![image](https://github.com/mesutgk15/carpark-management-system/assets/107197063/a2d06e50-1df4-43d8-a5eb-a2306ac8831d)


**Response returned with the created objects DTO:**
![image](https://github.com/mesutgk15/carpark-management-system/assets/107197063/f57c2a0a-12cf-4b73-9f7d-64e4f5c288d7)


**Setting owner for the vehicle:**
![image](https://github.com/mesutgk15/carpark-management-system/assets/107197063/c8171fcf-24d0-4076-9c86-c86488ad3583)


**We can see the vehicle in resident's vehicle list**
![image](https://github.com/mesutgk15/carpark-management-system/assets/107197063/40c22d5f-e03b-404c-b77c-337326129537)


**Try to initiate an entry to carpark with the vehicle created:**
![image](https://github.com/mesutgk15/carpark-management-system/assets/107197063/7c806cc4-da2d-4011-979e-e86c44e8b841)


**We get a response shows that entry rejected with the highlighted reason code:** 
![Screenshot 2023-11-02 at 16 47 32](https://github.com/mesutgk15/carpark-management-system/assets/107197063/66e597d1-d332-48a9-adae-8cb49eca1e84)


**Authorization to get in the carpark has been set to 'TRUE':**
![Screenshot 2023-11-03 at 14 31 50](https://github.com/mesutgk15/carpark-management-system/assets/107197063/5703d522-eef8-4532-8016-7746aece7216)


**Once entry initiated again we get a response with the status code with success this time:** 
![Screenshot 2023-11-03 at 14 33 36](https://github.com/mesutgk15/carpark-management-system/assets/107197063/9d69d3c1-0506-4da9-968a-73586dbae31a)


**When we add another vehicle for the resident and initate entry for that vehicle, it fails since the carpark allowance was set to "1" and the previous car is still in the carpark:**
![Screenshot 2023-11-03 at 14 39 45](https://github.com/mesutgk15/carpark-management-system/assets/107197063/d2340ce8-f10c-412d-be79-8712930419b2)



**We add another Vehicle to the same resident, Subtype is Motorbike this time, if we initiate entry for this vehicle we can see that it will be resulted with success. 
The reason is Motorbike subtype will be created with its "consumesSpace" field set to "FALSE" (can be altered after creation)**  
![Screenshot 2023-11-03 at 14 45 20](https://github.com/mesutgk15/carpark-management-system/assets/107197063/b42eff51-e8ea-495e-9203-38bbf18922c5)
![Screenshot 2023-11-03 at 15 05 50](https://github.com/mesutgk15/carpark-management-system/assets/107197063/e1e13992-d0d2-4956-b4cd-597776fccb35)


**If we inquire the carpark, we can see the vehicles we got inside with gate-service. We see that Carparks has two different collections for vehicles got inside, one of them only contains the vehicles which their "consumesSpace" field is "TRUE",
which is the one will be cheked when a Vehicle consumes space tries to enter** 
![Screenshot 2023-11-03 at 15 08 40](https://github.com/mesutgk15/carpark-management-system/assets/107197063/564fe0a0-73f2-4373-8b23-403db15c412a)
![Screenshot 2023-11-03 at 15 09 16](https://github.com/mesutgk15/carpark-management-system/assets/107197063/453f98fd-9d73-45f1-8617-1811b385d527)


**Gate-servie has a GateLog entity which is not exposed to RESTApi keeps track of the entry and exits to carpark. We can see that all our transactions has been persisted to db by the gate-service:**
![image](https://github.com/mesutgk15/carpark-management-system/assets/107197063/bdc9a4f4-1b83-484e-9ee9-66adc629bca2)

 
