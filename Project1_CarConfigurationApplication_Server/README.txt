Usage:
1) Start the server by running the Server Driver
2) Run the Server Driver (since I’m adding Automobiles using txt files from Unit 1, I don’t necessarily need a client).

Architecture:
- Created a new package “database” for the database functionalities. I have a JDBCAdapter class to establish connection with the database. I have 3 interfaces (CreateAutoDB, UpdateAutoDB, DeleteAutoDB) that are implemented in Database class so that I have APIs for different functionalities.

Database Table Architectures:
- automobiles: id, make, model, price (pkey=id)
- optionsets: id, name (pkey=id)
- options: id, name, price (pkey=id)
- models:
	- autoid (fkey references automobiles(id))
	- optsetid (fkey references optionsets(id))
	- optid (fkey references options(id))

Design choices:
- When an Automobile is being inserted, it will not insert an OptionSet into optionsets if one with the same name already exists. Also, it will not insert an Option into options if one with the same name and price already exists. For Options, I check both the name and the price since an Option with the same name can have different prices (ex: Present for air bags and power moonroof have different prices of $350 and $595). This is to avoid repeating.
- When deleting an Automobile, it will also delete the entry from OptionSets/Options tables if no other Automobile is using that OptionSet/Option. This way it cleans up the tables.
- JDBCAdapter opens a new connection whenever it insert, update, or delete then closes it. Designed it this way since the server will be running in the background forever, don’t want to create a new connection with JDBCAdapter in the Driver.

Driver:
1) test creating tables
2) test adding Automobiles
3) test update OptionSet name (just one model using the OptionSet)
4) test update OptionSet name (more than one models using the OptionSet)
5) test update Option price (just one model using the Option)
6) test update Option price (more than one models using the Option)
7) test delete Automobiles
8) test deleting tables

src: contains the source code for the assignment

auto_db.txt: contains url, driverName, user, password for SQL

FordFocus, LexusIS250, ToyotaPrius.txt: 3 different car models for input

output.txt: output of Driver

output_pics: folder that has all the screenshots of database

result.pdf: output of tests

Class Diagram: class diagram for Project 1 Unit 6 (Server)

Database Schema: schema of my database