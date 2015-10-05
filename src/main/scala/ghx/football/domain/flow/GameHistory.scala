package ghx.football.domain.flow

case class GameHistory(turns: Seq[Turn] = Seq.empty) {
  def +(turn: Turn) = copy(turns :+ turn)
}
