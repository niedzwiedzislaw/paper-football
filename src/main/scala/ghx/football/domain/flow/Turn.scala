package ghx.football.domain.flow

import ghx.football.domain.structure.Player

case class Turn(player: Player, moves: Seq[Move]) {
  def +(move: Move) = copy(moves = moves :+ move)
}
