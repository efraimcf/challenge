**Challenge**
===========

Service to calculate the shortest path between two points and the cost.

Register Graph
------------------

Service receives a file with the data of a map of graphs. 

> Request URI: http://[servername]/registergraph
>
> Method: **POST**
>
> **Example using curl:** curl -v -include --form file=@map.txt http://[servername]/registergraph

Each line contains a source vertex, a target vertex and a weight for the edge, like the example below:

> Node1 Node2 10
> Node1 Node3 15
> Node3 Node4 5
> Node2 Node4 15

The system will run the file's processor asynchronously.

Delivery Cost
----------------

After the Register Graph process, the system may provide the shortest path between two vertex and calculate the cost for this operation.

> Request URI: http://[servername]/delivery
> Method: **GET**
> **Request Parameters:**
> - **source**: [String] Source vertex name;
> - **target**: [String] Target vertex name;
> - **autonomy**: [Double] Consumption in Litres per Kilometers;
> - **gasCost**: [Double] Price for 1 Liter;
> **Response:**
> - **result:** [DeliveryCostResponse]
> - **errorMessages**: [Collection{String}] List of errors;

> - **DeliveryCostResponse:** 
> **cost:** [Double] Cost to get from the source to target;
> **path:** [Collection{String}] Ordenal list of vertexes to get from the source to target;

> **Example using curl:** curl -XGET 'http://localhost:8080/challenge/delivery?source=Node1&target=Node4&autonomy=10&gasCost=2.5'
> **Response exemple:** {"result":{"cost":5,"path":["Node1","Node3","Node4"]}


---------

System Requiriments:
-------------------------

- Java 8
- Apache Tomcat 8
- MySQL
