# Snakes & Ladder

1. Only feature 1 has been Implemented (excluding the Dice functionality) given the time constraints

2. Over all design mainly demonstrate the use of Scala, Play & Mongo

   Below two rest services have been implemented,

    /snl/checkPlayerToken - To check the current token of the player

    /snl/movePlayerToken - To move the token to desired square

    Play 2.5 dependency mechanism has been used.

    SNLRepository - Abstracts the mongo interaction.

    PlayerService - Initialises the Player in the Mongo after the start up & clears the Player's data after shut down

3. Please run IntegrationSpec to see the results


