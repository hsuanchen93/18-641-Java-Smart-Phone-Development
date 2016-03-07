client.DefaultSocketClient: The client spawns a new thread to handle each user action. This thread will try to establish a connection with the server and handle the user action before closing its connection.

client.CarModelOptionsIO: The client can use this class to create a Properties object from path given by the user so the client can send the Properties object to the server.

client.SelectCarOption: This class has several methods that can perform multiple functions such as prompting the user with list of available models, allowing the user to select a model, printing out the Configure Menu, and allowing the user to configure a model.

Design choices:
- When user wants to upload a Properties file and build an Automobile model from it, the client will load the Properties object from the user given path. Then, the client will serialize the Properties object to the server through ObjectStream.
- When server sends the client the list of all available models, I chose to send it in the form of ArrayList<Sting> since ArrayList implements Serializable so I can do serialization/deserialization over the ObjectStream.
- When client receives the Automobile object that the user has selected from the server, it will add it to its own thread’s LinkedHashMap. I designed it this way since all the APIs I have created for previous assignments all interact with LinkedHashMap instead of just one instance of Automobile. This makes it easier to perform user selection when configuring.

Driver:
- Uses a while loop to keep spawning a new DefaultSocketClient thread and prompting the user with the main menu. It will wait until the previous thread/action is done before spawning/prompting the user again.
- Tested by manually entering inputs like a user. Broke up the different tests into different sections for easier viewing purpose.
(1) Test invalid user input non-number option
(2) Test invalid user input number option
(3) Test invalid user input file
(4) Test uploading Properties file “ToyotaPrius.properties”
(5) Test uploading Properties file “FortFocus.properties”
(6) Test uploading Properties file “NoOptions.properties”
(7) Test invalid user input non-existent model for configuration
(8) Test configuring a model
(9) Test displaying all possible options for a model
(10) Test setting OptionSet “Transmission” to “Manual”
(11) Test displaying all the selected options so far
(12) Test setting OptionSet “Brakes” to “ABS”
(13) Test displaying all selected options
(14) Test exiting the Configure Menu

src: contains the source code for the assignment

ToyotaPrius.properties: given input
FortFocus.properties: only 1 OptionSet with multiple Options
NoOptions.properties: no options (only make, model, and base price)

output.txt: output of the Driver tests

Class Diagram: class diagram for Project 1 Unit 4 (Client)
