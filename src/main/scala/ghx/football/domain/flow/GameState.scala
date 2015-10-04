package ghx.football.domain.flow

case class GameState(moves: Seq[Move]) {
  def +(move: Move) = copy(moves :+ move)
}
