package ghx.football.domain.flow

import ghx.football.domain.structure.{Location, Player}
import org.scalatest.{FlatSpec, Matchers}

class GameHistoryTest extends FlatSpec with Matchers {
  "Class" should "append element" in {
    val result = GameHistory(Seq.empty) + Move(Seq(Pass(Location(0, 0), Location(0, 0))))
    result.moves.length should be(1)
  }
}
