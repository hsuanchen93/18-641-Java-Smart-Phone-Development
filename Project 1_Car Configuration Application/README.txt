util.FileIO: used AutoException.fix(int errorno) to fix internal “missing base price” Exception by asking an user-entered base price (keep asking the user until the user has entered a valid number).

adapter: proxyAutomobile implements all 3 interfaces so implemented all functions within the proxyAutomobile abstract class. Then to create and use API, created the class BuildAuto to extend from proxyAutomobile.
adapter.CreateAuto: added an extra method to get Automobile’s name since it’s needed in Driver to actually print and update.
adapter.proxyAutomobile: used FixAuto.fix(int errorno) to fix external “wrong filename” Exception by asking an user-entered filename (keep asking the user until the user has entered a valid filename).

exception.AutoException: logs Exceptions into file “ExceptionLog.txt” and all possible Custom Exceptions are listed in file “Exceptions.txt”.
exception.FixAutoException: fixes Exceptions 2 and 5 (missing base price and file not found).




src: contains the source code for the assignment

FordZTW.txt: given input, used to test API
FordZTW_2.txt: no base price, used to test catching and fixing Exception 2
FordZTW_3.txt: missing an OptionSet, used to test catching Exception 3
FordZTW_4.txt: missing an Option, used to test catching Exception 4

output.txt: output of the Driver

Exceptions.txt: list of all possible Custom Exceptions
ExceptionLog.txt: log file of exceptions

Class Diagram: class diagram for Project 1 Unit 2 Part A