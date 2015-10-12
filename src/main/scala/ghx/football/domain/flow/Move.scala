package ghx.football.domain.flow

import ghx.football.domain.structure.{Loss, Win, MoveResult, Direction, Passes}

case class Move(direction: Direction, result: MoveResult, passChain: Passes) {
  def +(move: Move) = {
    require(this.direction == move.direction, "Cannot merge moves in different directions")
    copy(result = move.result, passChain = this.passChain ++ move.passChain)
  }

  def isWinning = result == Win
  def isLosing = result == Loss
}
