package hotciv.standard.alphaCiv;

import hotciv.framework.*;

import hotciv.standard.GameImpl;
import hotciv.variants.actionStrategy.AlphaActionStrategy;
import hotciv.variants.agingStrategy.HundredYearStrategy;
import hotciv.variants.attackStrategy.AttackStrategy;
import hotciv.variants.attackStrategy.AttackerWinsStrategy;
import hotciv.variants.winnerStrategy.RedWinnerStrategy;
import hotciv.variants.worldStrategy.AlphaCivLayoutStrategy;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Skeleton class for AlphaCiv test cases
 * <p>
 * Updated Aug 2020 for JUnit 5 includes
 * Updated Oct 2015 for using Hamcrest matchers
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class TestAlphaCiv {
    private Game game;

    /**
     * Fixture for alphaciv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new RedWinnerStrategy(), new HundredYearStrategy(),
                new AlphaActionStrategy(), new AlphaCivLayoutStrategy(), new AttackerWinsStrategy() {
        });
    }

    private void endRound(int n) {
        for (int i = 0; i < n; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
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
    public void shouldBeBlueAfterRed() {
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    }

    @Test
    public void shouldBeRedAfterBlue() {
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
        //Given that:
        assertThat(game.getCityAt(new Position(1, 1)).getTreasury(), is(0));
        assertThat(game.getCityAt(new Position(4, 1)).getTreasury(), is(0));

        endRound(1);
        assertThat(game.getCityAt(new Position(1, 1)).getTreasury(), is(6));
        assertThat(game.getCityAt(new Position(4, 1)).getTreasury(), is(6));
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
        assertThat(game.getUnitAt(new Position(2, 0)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldBeBlueThatOwnsLegionAt3_2() {
        //Red owns the archer at 2_0
        assertThat(game.getUnitAt(new Position(3, 2)).getOwner(), is(Player.BLUE));
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
        assertThat(game.moveUnit(new Position(2, 0), new Position(2, 1)), is(true));

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
        game.moveUnit(new Position(2, 0), new Position(3, 1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(3, 1), new Position(3, 2));
        //Assert that the unit on the tile is owned by red (The winner)
        assertThat(game.getUnitAt(new Position(3, 2)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldBeAnotherPlayerThatGetsAttacked() {
        //Red's archer tries to attack red's Settler
        game.moveUnit(new Position(2, 0), new Position(3, 1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(3, 1), new Position(4, 2));
        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.moveUnit(new Position(4, 2), new Position(4, 3)), is(false));
    }

    @Test
    public void shouldNotBeAllowedToMoveOverOneDistance() {
        assertThat(game.moveUnit(new Position(2, 0), new Position(4, 0)), is(false));
    }

    @Test

    public void shouldHaveOneInMoveCountForArcher() {
        assertThat(game.getUnitAt(new Position(2, 0)).getMoveCount(), is(1));
    }

    @Test
    public void shouldHaveOnlyOneMoveCountEachTurnForArcher() {
        game.moveUnit(new Position(2, 0), new Position(3, 1));
        assertThat(game.getUnitAt(new Position(3, 1)).getMoveCount(), is(0));
    }

    @Test
    public void shouldHave1InMoveCountAfterOneRound() {
        assertThat(game.getUnitAt(new Position(2, 0)).getMoveCount(), is(1));
        game.moveUnit(new Position(2, 0), new Position(3, 1));
        assertThat(game.getUnitAt(new Position(3, 1)).getMoveCount(), is(0));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(3, 1)).getMoveCount(), is(1));
    }

    @Test
    public void shouldBeMaxOneMovementPerUnitPerTurn() {
        game.moveUnit(new Position(2, 0), new Position(3, 0));
        assertThat(game.moveUnit(new Position(3, 0), new Position(3, 1)), is(false));
    }

    @Test
    public void shouldNotMoveBluesLegionAt3_2to3_3InRedsTurn() {
        assertThat(game.moveUnit(new Position(3, 2), new Position(3, 3)), is(false));
    }

    @Test
    public void shouldBeArcherAsProductionInRedsCity() {
        game.changeProductionInCityAt(new Position(1, 1), (GameConstants.ARCHER));
        assertThat(game.getCityAt(new Position(1, 1)).getProduction(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldBeLegionAsProductionInRedsCity() {
        game.changeProductionInCityAt(new Position(1, 1), (GameConstants.LEGION));
        assertThat(game.getCityAt(new Position(1, 1)).getProduction(), is(GameConstants.LEGION));
    }

    @Test
    public void shouldPlaceArcherOnRedCityWhenTreasuryIs10() {
        game.changeProductionInCityAt(new Position(1, 1), (GameConstants.ARCHER));
        endRound(2);
        assertThat(game.getUnitAt(new Position(1, 1)).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldPlaceLegionOnBlueCityWhenTreasuryIs15() {
        game.endOfTurn();
        game.changeProductionInCityAt(new Position(4, 1), (GameConstants.LEGION));
        endRound(3);
        assertThat(game.getUnitAt(new Position(4, 1)).getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    public void shouldPlaceArcherOnBlueCityWhenTreasuryIs10() {
        game.endOfTurn();
        game.changeProductionInCityAt(new Position(4, 1), (GameConstants.ARCHER));
        endRound(2);
        assertThat(game.getUnitAt(new Position(4, 1)).getTypeString(), is(GameConstants.ARCHER));
    }

    // treasury is 2 after archer is produced in round 2
    @Test
    public void shouldHave2InTreasuryAfterArcherIsProducedInSecondRound() {
        game.changeProductionInCityAt(new Position(1, 1), (GameConstants.ARCHER));
        endRound(2);
        assertThat(game.getCityAt(new Position(1, 1)).getTreasury(), is(2));
    }

    @Test
    public void shouldHave0InTreasuryAfterSettlerIsProducedIn5Round() {
        game.changeProductionInCityAt(new Position(1, 1), (GameConstants.SETTLER));
        endRound(5);
        assertThat(game.getCityAt(new Position(1, 1)).getTreasury(), is(0));
    }

    @Test
    public void shouldNotBeAbleToChangeProductionOutOfTurn() {
        game.changeProductionInCityAt(new Position(1, 1), (GameConstants.SETTLER));
        game.endOfTurn();
        game.changeProductionInCityAt(new Position(1, 1), (GameConstants.ARCHER));
        assertThat(game.getCityAt(new Position(1, 1)).getProduction(), is(GameConstants.SETTLER));
    }

    @Test
    public void shouldPlaceUnitAboveCityIfTileIsOccupied() {
        game.changeProductionInCityAt(new Position(1, 1), (GameConstants.ARCHER));
        endRound(4);
        assertThat(game.getUnitAt(new Position(0, 1)).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldPlaceUnitClockwiseInThirdProductionIfNoMovesAreMade() {
        game.changeProductionInCityAt(new Position(1, 1), (GameConstants.ARCHER));
        endRound(6);
        assertThat(game.getUnitAt(new Position(0, 2)).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldNotMoveArcherFrom2_0ToOceanAt1_0() {
        Position archer = new Position(2,0);
        Position ocean = new Position(1, 0);
        assertThat(game.moveUnit(archer, ocean), is(false));
    }

    @Test
    public void shouldNotMoveLegionFrom3_2ToMountainAt2_2() {
        Position legion = new Position(3,2);
        Position mountain = new Position(2, 2);
        game.endOfTurn();
        assertThat(game.moveUnit(legion, mountain), is(false));
    }

    @Test
    public void shouldReturnAPopulationSizeInCity() {
        City redCity = game.getCityAt(new Position(1,1 ));
        assertThat(redCity.getSize(), is(notNullValue()));
    }

    @Test
    public void shouldHave1InPopulationSizeInCity() {
        City redCity = game.getCityAt(new Position(1,1 ));
        assertThat(redCity.getSize(), is(1));
    }

    @Test
    public void  shouldNotBuildCityWhenSettlerDoesAction(){
        Position settler = new Position(4,3);
        game.performUnitActionAt(settler);
        assertThat(game.getCityAt(settler), is(nullValue()));
        assertThat(game.getUnitAt(settler).getTypeString(), is(GameConstants.SETTLER));
    }

    @Test
    public void  shouldNotFortifyWhenArcherDoesAction(){
        Position archer = new Position(2,0);
        //Given that archers strength is 3
        assertThat(game.getUnitAt(archer).getDefensiveStrength(), is(3));
        game.performUnitActionAt(archer);
        //Hotciv would double the units defense, but Alphaciv does not do anything.
        assertThat(game.getUnitAt(archer).getDefensiveStrength(), is(3));
    }

    @Test
    public void shouldHave2InDefenseForLegion(){
        Position legion = new Position(3,2);
        assertThat(game.getUnitAt(legion).getDefensiveStrength(), is(2));
    }

    @Test
    public void shouldHave3InDefenseForSettler(){
        Position settler = new Position(4,3);
        assertThat(game.getUnitAt(settler).getDefensiveStrength(), is(3));
    }

    @Test
    public void shouldConquerBlueCityWhenArcherEnters() {
        Position archer = new Position(2,0);

        game.moveUnit(archer, new Position(3, 0));
        endRound(1);
        game.moveUnit(new Position(3, 0), new Position(4, 1));
        assertThat(game.getCityAt(new Position(4,1)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldChangeWorkForceFocusToFood(){
        Position redCity = new Position(1,1);
        game.changeWorkForceFocusInCityAt(redCity,GameConstants.foodFocus);
        assertThat(game.getCityAt(redCity).getWorkforceFocus(), is(GameConstants.foodFocus));
    }

    @Test
    public void shouldChangeWorkForceFocusToProduction(){
        Position redCity = new Position(1,1);
        game.changeWorkForceFocusInCityAt(redCity,GameConstants.productionFocus);
        assertThat(game.getCityAt(redCity).getWorkforceFocus(), is(GameConstants.productionFocus));
    }
}