package ghx.football.domain.structure

import ghx.football.domain.structure.Directions.{Down, Up}

case class Player(name: String, direction: Direction) {
  def move(game: Game): Game = {
    val possibilities = game.possibleMoves(direction).filterNot(_.result == Loss)
//    val possibilities2 = DecisionMaker(game.field, game.history.passes).possibleMoves
    if (possibilities.isEmpty)
      game
    else if (possibilities.exists(_.result == Win)){
      println(s"$name won")
      GameFinished(this, game.field, game.history + possibilities.find(_.result == Win).get)
    } else {
      direction match {
        case Up => game + possibilities.maxBy(m => m.passChain.last.to.y)
        case Down => game + possibilities.minBy(m => m.passChain.last.to.y)
      }
    }
  }
}

object Player {
  val one = Player("Player One", Up)
  val two = Player("Player Two", Down)
}