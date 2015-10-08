package ghx.football.domain.flow

import ghx.football.domain.structure.Location

case class GameHistory(moves: Seq[Move]) {
  val startingLocation = Location(0, 0)
  def +(move: Move) = copy(moves :+ move)
}

object GameHistory {
  val beginning = GameHistory(Seq.empty)
}