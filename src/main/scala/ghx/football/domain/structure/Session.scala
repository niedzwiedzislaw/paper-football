package ghx.football.domain.structure

case class Session(players: PairOfPlayers, game: Game) {
  def advance(): Session = {
    println("advancing")
    copy(players.advance, players.current.move(game))
  }
}

object Session {
  def newSession(players: PairOfPlayers, field: Field) = Session(players, NewGame(field))
}