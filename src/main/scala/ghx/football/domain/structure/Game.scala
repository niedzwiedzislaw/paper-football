package ghx.football.domain.structure

import ghx.football.domain.flow.GameHistory

case class Game(field: Field, moveCounter: Int = 0, state: GameHistory = GameHistory()) {
  
  def start() = {
    require(moveCounter == 0, "Cannot start in the middle")
    ???
    nextMove()
  }
  
  def nextMove(): Game = {
    ???
    copy(moveCounter = moveCounter + 1)
  }
}
