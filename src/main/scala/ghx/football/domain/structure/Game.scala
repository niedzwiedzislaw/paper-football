package ghx.football.domain.structure

import ghx.football.domain.flow.{GameHistory, Move, Pass}

sealed trait Game {
  def field: Field

  def history: GameHistory

  def possibleMoves(direction: Direction): Seq[Move]

  def +(move: Move): Game
}

case class NewGame(field: Field) extends Game {
  val history = GameHistory.beginning

  def possibleMoves(direction: Direction): Seq[Move] = GameInProgress(field, GameHistory.beginning).possibleMoves(direction)

  def +(move: Move): Game = GameInProgress(field, GameHistory.beginning) + move
}

case class GameInProgress(field: Field, history: GameHistory) extends Game {

  def possibleMoves(direction: Direction): Seq[Move] = {
    for {
      passChain <- DecisionMaker(field, history.passes).possibleMoves
    } yield Move(direction, predictPassResult(passChain), passChain)
  }

  def +(move: Move) = copy(history = history + move)

  def predictPassResult(pass: PassChain) = {
    if (isWinningPass(pass)) {
      Win
    } else if (isLosingPass(pass)) {
      Loss
    } else {
      Continuation
    }
  }

  def isWinningPass(pass: PassChain) = {
    field.isWinningPass(pass.last)
  }

  def isLosingPass(pass: PassChain) = {
    field.isLosingPass(pass.last)
  }
}

case class GameFinished(winner: Player, field: Field, history: GameHistory) extends Game {
  def possibleMoves(direction: Direction): Seq[Move] = Seq.empty

  def +(move: Move): Game = this
}