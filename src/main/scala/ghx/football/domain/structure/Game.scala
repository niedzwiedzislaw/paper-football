package ghx.football.domain.structure

import ghx.football.domain.flow.{GameHistory, Move}

sealed trait Game {
  def field: Field

  def history: GameHistory

  def +(move: Move): Game
}

case class NewGame(field: Field) extends Game {
  val history = GameHistory.beginning

  def +(move: Move): Game = GameInProgress(field, GameHistory.beginning) + move
}

case class GameInProgress(field: Field, history: GameHistory) extends Game {
  def +(move: Move) = copy(history = history + move)
}

case class GameWon(winner: Player, field: Field, history: GameHistory) extends Game {
  def +(move: Move): Game = this
}

case class GameLost(loser: Player, field: Field, history: GameHistory) extends Game {
  def +(move: Move): Game = this
}