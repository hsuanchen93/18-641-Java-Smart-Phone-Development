Usage:
1) Start the server by running the Server Driver
2) Start the client by running the Client Driver
3) Upload Automobile models using client
4) Start Tomcat Server to enable Web Browser for client
5) Use the Web Browser to configure an Automobile model

Architecture:
- Server remains the same

Design choices:
- When user wants to upload a Properties file and build an Automobile model from it, the server will receive the Properties object from client. The server will first deserialize the object and creates a Properties file to be stored on the server. Then, it will use BuildAuto with fileType 2 (Properties file) to load and parse the Properties file and create a Automobile object.
- When server sends the client the list of all available models, I chose to send it in the form of ArrayList<Sting> since ArrayList implements Serializable so I can do serialization/deserialization over the ObjectStream.

Driver:
- It starts the server that will continuously listen for incoming client requests until termination.

src: contains the source code for the assignment

Class Diagram: class diagram for Project 1 Unit 5 (Server)