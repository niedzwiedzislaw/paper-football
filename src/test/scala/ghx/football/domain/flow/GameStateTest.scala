package ghx.football.domain.flow

import ghx.football.domain.structure.Player
import org.scalatest.{FlatSpec, Matchers}

class GameStateTest extends FlatSpec with Matchers {
  "Class" should "append element" in {
    val result = GameState(Seq.empty) + Move(Player("1"), (0, 0), (0, 0))
    result.moves.length should be 1
  }
}
