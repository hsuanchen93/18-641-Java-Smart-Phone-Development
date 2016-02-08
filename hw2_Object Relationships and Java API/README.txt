src: contains the source code for the assignment

input_1.txt: given input
input_2.txt: input with more than 40 students
input_3.txt: normal 5 inputs
input_4.txt: normal 10 inputs

output.txt: result for given input

Class Diagram: class diagram for assignment 2

Architecture:
Both Student and Statistics classes implement the Info interface and 
overwrite the printInfo() function to print out relevant information. 
The CustomException class extends from the Exception abstract class 
and overwrite the getLocalMessage() function to return a specific error message.