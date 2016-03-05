Architecture:
Automotive Class encapsulates both OptionSet Class and Option Class where Option Class is an inner class of the OptionSet Class.
I designed the architecture so that when the Driver runs simulation and creates Automotive objects, it would not be able to access both OptionSet and Option. Thus, you need getters and setters in order to modify the content of Automotive.
As for the structure of the model input file, I have the first line to have the model name with the base price in parentheses. Then, the second line gives an overview of how many different OptionSets there are. I designed it this way so that I know how big the OptionSet array is supposed to be. Finally, for each option, it starts the line with character ‘>’ so it’s easy to read. After ‘>’, it will have the name of the OptionSet and number of Options in parentheses so I can create the right size for Option array. After ‘:’, the list of different Options and theirs prices in parentheses follow.
For the find methods, I return an empty string because there are multiple places where I use String.equals() to compare the strings. Thus, there will be null exceptions if I return null. Also, I set the name property to an empty string in the delete methods due to the same reason.

src: contains the source code for the assignment

FordZTW.txt: given input
FordZTW_2.txt.txt: input with 0 Options for Color
FordZTW_3.txt.txt: input with 0 OptionSets for FordZTW

output.txt: FordZTW with given input
output_2.txt: result of all 4 test cases

Class Diagram: class diagram for Project 1 Unit 1