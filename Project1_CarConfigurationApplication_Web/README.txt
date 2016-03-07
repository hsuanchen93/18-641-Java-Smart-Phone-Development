Usage:
1) Start the server by running the Server Driver
2) Start the client by running the Client Driver
3) Upload Automobile models using client
4) Start Tomcat Server to enable Web Browser for client
5) Use the Web Browser to configure an Automobile model

Architecture:
- Added two functions in the SocketClientInterface to help the Browser to retrieve information from the server. Hide the actual implementation away and only uses SocketClientInterface in the 2 Servlets (ModelList and OptionList).
- Has a total of 2 Servlets and 1 JSP. One Servlet is responsible for retrieving the list of available models and the other one is responsible for retrieving all the relevant data/options on user-selected Automobile. The default webpage is in HTML since JSP is not necessary for loading the model lists and all its options once the user has chosen. JSP is used for the webpage that needs to dynamically load all the options and the user’s selections along with calculating the total cost.

Design choices:
- I implemented the 2 Servlets and the JSP on the client side since the client already has all the functions needed to communicate with the server and it’s just modifying one of the function from last unit into a web browser.
- For the default webpage, I designed it so that it automatically loads the list of models but it won’t load the options until the user has chosen a model (this makes sense to me as an application).

servlet.ModelList: Servlet for retrieving the list of models by implementing doGet.

servlet.OptionList: Servlet for retrieving model’s information such as options by implementing doGet.

WebContent/index.html: Automatically loads the list of available models and waits for user’s configuration.

WebContent/result.jsp: Once user was done with configuring and hit “Done”, it will load the configuration result dynamically.


Driver:
- Uses a while loop to keep spawning a new DefaultSocketClient thread and prompting the user with the main menu. It will wait until the previous thread/action is done before spawning/prompting the user again.

src: contains the source code for the assignment

output_pics: some screenshots of the browser

output.txt: output from Client Driver

Class Diagram: class diagram for Project 1 Unit 5 (Client)