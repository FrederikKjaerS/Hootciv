package hotciv.standard;

import hotciv.framework.*;

import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/** Skeleton class for AlphaCiv test cases

   Updated Aug 2020 for JUnit 5 includes
   Updated Oct 2015 for using Hamcrest matchers

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public class TestAlphaCiv {
  private Game game;

  /**
   * Fixture for alphaciv testing.
   */
  @BeforeEach
  public void setUp() {
    game = new GameImpl();
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    //Red is the starting player
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldBeRedWinnerAtYear3000BC() {
    //Red should win at year -3000
    for (int i = 0; i < 20; i++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(-3000));
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void shouldBeBlueafterRed() {
    //It's blue's turn after red
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  @Test
  public void shouldBeRedafterBlue() {
    //It's blue's turn after red
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldBeYear4000InFirstRound() {
    //Game should start in year -4000.
    assertThat(game.getAge(), is(-4000));
  }

  @Test
  public void shouldBeYear3900AfterOneRound() {
    //Should be year 3900 after one round.
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getAge(), is(-3900));
  }

  @Test
  public void shouldBeRedCityAtPosition1_1() {
    //Red City is placed at position (1,1).
    Position redPosition = new Position(1, 1);
    City redCity = game.getCityAt(redPosition);
    assertThat(redCity.getOwner(), is(Player.RED));
  }

  @Test
  public void shouldBeBlueCityAtPosition4_1() {
    //Red City is placed at position (1,1).
    Position bluePosition = new Position(4, 1);
    City blueCity = game.getCityAt(bluePosition);
    assertThat(blueCity.getOwner(), is(Player.BLUE));
  }

  @Test
  public void shouldAdd6ProductionToCitiesAfterOneRound() {
    //Every round each city gets 6 production
    //We initiate a new turn
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getCityAt(new Position(1,1)).getTreasury(), is(6));
    assertThat(game.getCityAt(new Position(4,1)).getTreasury(), is(6));
  }

  @Test
  public void shouldBeOceanAt1_0() {
    //Tests that there is ocean at (1,0).
    assertThat(game.getTileAt(new Position(1, 0)).getTypeString(), is(GameConstants.OCEANS));
  }

  @Test
  public void shouldBeHillsAt0_1() {
    //Tests that there is ocean at (1,0).
    assertThat(game.getTileAt(new Position(0, 1)).getTypeString(), is(GameConstants.HILLS));
  }

  @Test
  public void shouldBeMountainsAt2_2() {
    //Tests that there is ocean at (1,0).
    assertThat(game.getTileAt(new Position(2, 2)).getTypeString(), is(GameConstants.MOUNTAINS));
  }

  @Test
  public void shouldBeArcherAt2_0() {
    //Tests that there is an archer at (2,0).
    assertThat(game.getUnitAt(new Position(2, 0)).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void shouldBeLegionAt3_2() {
    //Tests that there is a legion at (3,2).
    assertThat(game.getUnitAt(new Position(3, 2)).getTypeString(), is(GameConstants.LEGION));
  }

  @Test
  public void shouldBeSettlerAt4_3() {
    //Tests that there is a Settler at (4,3).
    assertThat(game.getUnitAt(new Position(4, 3)).getTypeString(), is(GameConstants.SETTLER));
  }

  @Test
  public void shouldBeRedThatOwnsArcherAt2_0() {
    //Red owns the archer at 2_0
    assertThat(game.getUnitAt(new Position(2,0)).getOwner(), is(Player.RED));
  }

  @Test
  public void shouldBeBlueThatOwnsLegionAt3_2() {
    //Red owns the archer at 2_0
    assertThat(game.getUnitAt(new Position(3,2)).getOwner(), is(Player.BLUE));
  }

  @Test
  public void shouldMoveUnitFrom2_0To2_1() {
    //Unit should move from 2.0 to 2.1
    //Fist we get the unit at 2.0.
    Unit u = game.getUnitAt(new Position(2, 0));
    //Asserts that the unit is not null
    assertThat(u, is(notNullValue()));

    //Asserts that there is not a unit on position 2.1
    Unit u2 = game.getUnitAt(new Position(2, 1));
    assertThat(u2, is(nullValue()));

    //Asserts that the movement is allowed
    assertThat(game.moveUnit(new Position(2, 0), new Position(2,1)), is(true));

    //Asserts that the unit is removed from position 2.0
    u = game.getUnitAt(new Position(2, 0));
    assertThat(u, is(nullValue()));

    //Asserts that there is a unit at position 2.1
    u2 = game.getUnitAt(new Position(2, 1));
    assertThat(u2, is(notNullValue()));
  }

  @Test
  public void shouldBeAttackerThatWins() {
    //Red owns archer at (2,1) it moves to blue's legion at (3,2) and attacks.
    game.moveUnit(new Position(2, 0),new Position(3,1));
    game.endOfTurn();
    game.endOfTurn();
    game.moveUnit(new Position(3, 1),new Position(3,2));
    //Assert that the unit on the tile is owned by red (The winner)
    assertThat(game.getUnitAt(new Position(3,2)).getOwner(), is(Player.RED));
  }

  @Test
  public void shouldBeAnotherPlayerThatGetsAttacked() {
    //Red's archer tries to attack red's Settler
    game.moveUnit(new Position(2, 0),new Position(3,1));
    game.endOfTurn();
    game.endOfTurn();
    game.moveUnit(new Position(3, 1),new Position(4,2));
    game.endOfTurn();
    game.endOfTurn();

    assertThat(game.moveUnit(new Position(4,2),new Position(4,3)),is(false));
  }

 @Test
  public void shouldNotBeAllowedToMoveOverOneDistance(){
    assertThat(game.moveUnit(new Position(2, 0), new Position(4, 0)), is(false));
 }
  @Test

  public void shouldHaveOneInMoveCountForArcher(){
    assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(), is(1));
  }

  @Test
  public void shouldHaveOnlyOneMoveCountEachTurnForArcher(){
    game.moveUnit(new Position(2, 0),new Position(3,1));
    assertThat(game.getUnitAt(new Position(3,1)).getMoveCount(), is(0));
  }

  @Test
  public void shouldHave1InMoveCountAfterOneRound(){
    assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(), is(1));
    game.moveUnit(new Position(2, 0),new Position(3,1));
    assertThat(game.getUnitAt(new Position(3,1)).getMoveCount(), is(0));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getUnitAt(new Position(3,1)).getMoveCount(), is(1));
  }

 @Test
  public void shouldBeMaxOneMovementPerUnitPerTurn() {
   game.moveUnit(new Position(2, 0), new Position(3, 0));
   assertThat(game.moveUnit(new Position(3, 0), new Position(3, 1)), is(false));
 }

}