adapter.CreateAuto: Added another input “fileType” to distinguish between building an Automobile from actual text files and Properties files.

adapter.BuildAuto: It now also implements the AutoServer Interface.

adapter.proxyAutomobile: It does the actual implementation of AutoServer Interface.

server.BuildCarModelOptions: This class implements the AutoServer Interface. It has a BuildAuto object as an instance variable and calls the AutoServer methods inside its own methods.

server.DefaultSocketClient: The server spawns a new thread to handle each user action. This thread will communicate with the client to handle the user action before closing its connection.

server.Server: It will start a server that will continuously listen for incoming client requests. Whenever there is a client request, it will spawn a new thread to handle each user action.

Design choices:
- When user wants to upload a Properties file and build an Automobile model from it, the server will receive the Properties object from client. The server will first deserialize the object and creates a Properties file to be stored on the server. Then, it will use BuildAuto with fileType 2 (Properties file) to load and parse the Properties file and create a Automobile object.
- When server sends the client the list of all available models, I chose to send it in the form of ArrayList<Sting> since ArrayList implements Serializable so I can do serialization/deserialization over the ObjectStream.

Driver:
- It starts the server that will continuously listen for incoming client requests until termination.

src: contains the source code for the assignment

Class Diagram: class diagram for Project 1 Unit 4 (Server)