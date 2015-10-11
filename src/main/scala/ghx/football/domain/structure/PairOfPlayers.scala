package ghx.football.domain.structure

case class PairOfPlayers(current: Player, next: Player) {
  def advance = PairOfPlayers(next, current)
}

object PairOfPlayers {
  val oneAndTwo = PairOfPlayers(Player.one, Player.two)
}