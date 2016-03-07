util.FileIO: used AutoException.fix(int errorno) to fix internal “missing base price” Exception by asking an user-entered base price (keep asking the user until the user has entered a valid number).

adapter: proxyAutomobile implements all 4 interfaces so implemented all functions within the proxyAutomobile abstract class. Then to create and use API, created the class BuildAuto to extend from proxyAutomobile.
adapter.UpdateAuto: added delete and set APIs.
adapter.InfoAuto: added a new interface to deal with printing out and getting different kinds of information on Automobile.
adapter.proxyAutomobile: with a LinkedHashMap, had to use an Iterator to access and print out all the available models.

model.Automobile: when deleting/updating OptionSet or Option, always check if present value is in choice and make adjustment as well.
model.Automobile: class to tackle CRUD operation on a group of Automobiles

Driver: tests all create/update/delete/set/print APIs (along with old tests of Exceptions)


src: contains the source code for the assignment

FordZTW.txt: given input, used to test API
FordZTW_2.txt: no base price, used to test catching and fixing Exception 2
FordZTW_3.txt: missing an OptionSet, used to test catching Exception 3
FordZTW_4.txt: missing an Option, used to test catching Exception 4

output.txt: output of the Driver

Exceptions.txt: list of all possible Custom Exceptions
ExceptionLog.txt: log file of exceptions

Class Diagram: class diagram for Project 1 Unit 2 Part A