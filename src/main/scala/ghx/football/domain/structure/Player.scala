package ghx.football.domain.structure

import ghx.football.domain.flow.Move
import ghx.football.domain.structure.Directions.{Down, Up}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

case class Player(name: String, direction: Direction) {
  def move(game: Game): Game = {
    val possibilities = DecisionMaker(game.field, game.history.passes)
      .possibleMoves
      .map{ passChain => Move(direction, predictPassResult(passChain, game), passChain) }
    if (possibilities.isEmpty) {
      println(s"$name lost")
      GameLost(this, game.field, game.history)
    } else if (possibilities.exists(_.isWinning)){
      println(s"$name won")
      GameWon(this, game.field, game.history + possibilities.find(_.isWinning).get)
    } else {
      chooseBest(game, possibilities)
    }
  }

  private def chooseBest(game: Game, possibilities: Seq[Move]) = {
    val n = possibilities.filterNot(_.isLosing)
      .groupBy(_.passChain.last)
      .values
      .map(_.maxBy(_.passChain.length))
      .filterNot {
        move =>
            DecisionMaker(game.field, game.history.passes ++ move.passChain)
              .possibleMoves
              .map{ passChain => Move(direction, predictPassResult(passChain, game + move), passChain) }
              .exists(_.isWinning)
      }

    val r = (if (n.nonEmpty) n.toList else possibilities).sortBy(_.passChain.length)
    if (n.isEmpty)
      println("opponent always wins")
    println(s"possible moves: ${r.length}, winning: ${r.count(_.isWinning)}, losing: ${r.count(_.isLosing)}")

    direction match {
      case Up => game + r.maxBy(m => m.passChain.last.to.y)
      case Down => game + r.minBy(m => m.passChain.last.to.y)
    }
  }

  def predictPassResult(pass: Passes, game: Game) = {
    if (isWinningPass(pass, game)) {
      Win
    } else if (isLosingPass(pass, game)) {
      Loss
    } else {
      Continuation
    }
  }

  def isWinningPass(pass: Passes, game: Game) = {
    game.field.isWinningPass(pass.last)
  }

  def isLosingPass(pass: Passes, game: Game) = {
    game.field.isLosingPass(pass.last) || DecisionMaker(game.field, game.history.passes ++ pass).possibleBeginnings.isEmpty
  }
}

object Player {
  val one = Player("Player One", Up)
  val two = Player("Player Two", Down)
}