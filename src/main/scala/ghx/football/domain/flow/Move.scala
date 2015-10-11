package ghx.football.domain.flow

import ghx.football.domain.structure.{MoveResult, Direction, PassChain}

case class Move(direction: Direction, result: MoveResult, passChain: PassChain) {
  def +(move: Move) = {
    require(this.direction == move.direction, "Cannot merge moves in different directions")
    copy(result = move.result, passChain = this.passChain ++ move.passChain)
  }

  /**
   * @param passChains collection of moves to append
   * @return  collection of moves starting with this move and ending with moves from parameter
   */
  def >>>(passChains: Seq[Move]) = passChains.map(move => this + move)
}
