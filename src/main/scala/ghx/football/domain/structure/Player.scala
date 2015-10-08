package ghx.football.domain.structure

import ghx.football.domain.flow.GameHistory

case class Player(name: String) {
  def move(game: Game): Game = {
    val possibilities = game.possibleMoves
    game.copy(history = game.history + possibilities.head)
  }
}

object Player {
  val one = Player("One")
  val two = Player("Two")
}