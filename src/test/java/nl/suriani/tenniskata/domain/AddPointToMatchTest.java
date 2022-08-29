package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.enumeration.SetsToWin;
import nl.suriani.tenniskata.shared.testdata.TestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddPointToMatchTest {
	private static final TeamDefinition TEAM1 = TestData.federerAndNadal;
	private static final TeamDefinition TEAM2 = TestData.venusAndSerena;
	private static final Match MATCH = new Match(TEAM1, TEAM2, SetsToWin.TWO);

	@Test
	void addFirstPoint() {
		var updatedMatch = MATCH.addPoint(TEAM1.id());
		var score = MATCH.getScore();
		assertEquals(0, score.team1().value());
		assertEquals(0, score.team2().value());
		assertEquals(0, score.sets().get(0).player1().value());
		assertEquals(0, score.sets().get(0).player2().value());
		assertEquals("15", score.sets().get(0).games().get(0).player1().display());
		assertEquals("0", score.sets().get(0).games().get(0).player2().display());
	}

}
