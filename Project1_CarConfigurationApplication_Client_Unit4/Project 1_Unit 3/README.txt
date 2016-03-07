scale.EditOptions: Edit OptionSets and Options in its own thread, synchronize the methods by using “synchronized(Automobile)” in EditOptions because need to lock the actual object being changed (aka Automobile instance) whenever a new thread is created to edit the instance (secure the lock before making changes so other threads can’t access the static instance at the same time).

adapter.EditAuto: API/interface for editing OptionSets and Options in their own threads.

Driver:
->(Test 1): Tests the API and EditOptions by calling editOptionSetName and editOptionPrice under normal circumstances and working code.
->(Test 2): Tests the success of the synchronization by creating 2 threads where the first one will change OptionSet Name and the second thread will change the Option Price of the new OptionSet. By letting thread editOptionSetName sleep 3000, we can see if synchronization is achieved because thread editOptionPrice is called right after in the main thread. Thus, under synchronization, thread 2 will wait for thread 1 finish the sleeping and the editing before being able to edit the Automobile instance itself. As shown in output, I also added a print statement immediately to show that both threads have already started but nothing has changed due to the sleep. Then, I print out the result again after waiting another ~3 seconds, and it shows correct edits in the Automobile instance.
->(Test 3): By using the same test code taking out the “synchronized(Automobile)” in EditOptions, I can show that synchronization cannot work without the “synchronized” keyword. With the 3000 sleep in thread editOptionSetName, the OptionSet Name wasn’t able to changed to “NEW NAME YAY” before editOption Price was called. Thus, printing out “OPTION SET NOT FOUND” while the final result shows that only OptionSet Name was successfully edited while the price remained $0.


src: contains the source code for the assignment

FordZTW.txt: given input, used to test API
Thread.txt: input to test threads

output.txt: output of the Driver tests

Overview: an overview of the app
Class Diagram: class diagram for Project 1 Unit 3
