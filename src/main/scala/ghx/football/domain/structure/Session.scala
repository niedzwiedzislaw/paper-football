package ghx.football.domain.structure

case class Session(players: PairOfPlayers, game: Game) {
  def advance(): Session = {
    copy(game = players.current.move(game))
  }
}

object Session {
  def newSession(players: PairOfPlayers, field: Field) = Session(players, Game.newGame(field))
}