model.Mortgage: The mortgage model where it can hold all the values relevant to performing the calculation. It also has two methods were one is to perform the actual calculation and the other is to save all the relevant data to the local SQLite database using DatabaseConnector.

ui.MainActivity: The main Activity of the Android application where it can let the user enter inputs such as purchase price, mortgage term, interest rate, and first payment date.

ui.ResultActivity: This Activity displays the result from the mortgage calculation. After receiving the Intent from MainActivity, it extract the extra data from the intent bundle and dynamically load monthly payment, total payment, and payoff date into their respective TextViews.

util.DatabaseConnector: The DatabaseConnector that allows the Android application to communicate with the local SQLite database. 

Design choices:
- Separated into 4 packages, exception, model, ui, and util.
- Use Intent and bundle to send the result of mortgage calculation to another Activity to dynamically load the result for the user to see.


app/src/main/java/com/example/amychen/hw2_mortgageapp: contains the source code for the assignment

Database Content: screenshot to show the content of the database after storing the data (I used Log in the code to print the content of database to console)

UI_1.png, UI_2.png, UI_3.png: screenshots of the UI of the application

Class Diagram: class diagram