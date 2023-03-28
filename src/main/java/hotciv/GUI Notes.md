The User Interface

1. moveUnit is invoked when a user drags a unit from one tile to the next.
2. endOfTurn is invoked when the user clicks the top shield with the player’s color
   on in the age section of the status panel.
3. changeProductionInCityAt is invoked when the user first has clicked on one of his/her cities and next clicks on the unit symbol, marked “Produce”, in the city section of the status panel. Clicking the symbol iterates between (archer, legion, settler) and back.
4. changeWorkForceFocusInCityAt is invoked when the user first has clicked on one of his/her cities and next clicks on the hammer (production focus) or apple (food focus) icon in the city section marked “Balance.” Clicking the symbol toggles between the two types of focus.
5. performUnitActionAt is invoked when the user clicks on a unit while holding down the shift key.
