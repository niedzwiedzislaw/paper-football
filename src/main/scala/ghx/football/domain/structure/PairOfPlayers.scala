package ghx.football.domain.structure

case class PairOfPlayers(player1: Player, player2: Player) {
  var current: Player = player1
  def advance() = {
    if (current == player1)
      current = player2
    else
      current = player2
  }
}

object PairOfPlayers {
  val oneAndTwo = PairOfPlayers(Player.one, Player.two)
}