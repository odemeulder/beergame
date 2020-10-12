# Beergame application

This program is an implementation of the [beer game](https://en.wikipedia.org/wiki/Beer_distribution_game), a game that illustrates concepts form the field of System Dynamics. 

The supply chain has the following actors: Retailer, Distributor, Wholesaler and Factory. Each actor starts with an inventory of 4. Demand is generated randomly and fluctuates between 2 and 7.

In real life actors have to place orders every week. In this implementation the actors are played by the program.

## Run it

```bash
./gradlew run
```