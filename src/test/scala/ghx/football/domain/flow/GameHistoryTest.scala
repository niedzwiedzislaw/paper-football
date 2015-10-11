package ghx.football.domain.flow

import ghx.football.domain.structure.{Continuation, Directions, Location}
import org.scalatest.{FlatSpec, Matchers}

class GameHistoryTest extends FlatSpec with Matchers {
  "+" should "append element" in {
    (1 to 100).foldLeft((GameHistory.beginning, List.empty[Move])) {
      case ((gameHistory, moves), i) =>
        val nextMove = Move(Directions.Up, Continuation, List(Pass(Location(i, i), Location(i + 1, i + 1))))
        val newGameHistory = gameHistory + nextMove
        val newMoves = moves :+ nextMove

        gameHistory.moves should be(moves)
        for (i <- gameHistory.moves.indices) {
          gameHistory.moves(i) should be(moves(i))
        }

        (newGameHistory, newMoves)
    }
  }
}
