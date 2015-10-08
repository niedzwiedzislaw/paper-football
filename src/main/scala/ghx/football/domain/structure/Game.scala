package ghx.football.domain.structure

import ghx.football.domain.flow.{Pass, Move, GameHistory}

case class Game(field: Field, history: GameHistory) {
  def possibleMoves: Seq[Move] = {
    Seq(Move(Seq(Pass(Location(0, 0), Location(1, 1)))))
  }
  def +(move: Move) = copy(history = history + move)
}

object Game {
  def newGame(field: Field) = Game(field, GameHistory.beginning)
}