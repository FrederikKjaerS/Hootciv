# Backlog
We are missing these implementations:

* Unit actions. No associated actions are supported by any unit. Specifically, the settler’s action does nothing.
* Cities. The player may select to produce either archers, legions, or settlers. Cities do not grow but stay at population size 1. 
* Unit Production. When a city has accumulated enough production it produces the unit selected for production, and the unit’s cost is deducted from the city’s treasury of production. The unit is placed on the city tile if no other unit is present, otherwise it is placed on the first non-occupied adjacent tile, starting from the tile just north of the city and moving clockwise