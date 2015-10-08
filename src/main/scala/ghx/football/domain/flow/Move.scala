package ghx.football.domain.flow

import ghx.football.domain.structure.Player

case class Move(passes: Seq[Pass]) {
  def +(move: Pass) = copy(passes = passes :+ move)
}
