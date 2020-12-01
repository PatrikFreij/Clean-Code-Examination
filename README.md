# Clean-Code-Examination
Examination showcasing what we learned in our Clean Code course

Goal of the examination was to show how I could tidy up the existing code and make it cleaner.
The initial project contained only one big class that contained and did everything, communicating with View, working/connecting with the database and executing the game logic.
I have renamed variables, classes and methods to give it a clearer meaning behind it's intent.
Separated the code into it's own classes where they belong and putting them in proper packages. Game logic, database, controller and view.
In turn letting the controller handle the dependency injections.
