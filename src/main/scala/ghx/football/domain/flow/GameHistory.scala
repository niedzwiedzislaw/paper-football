package ghx.football.domain.flow

import ghx.football.domain.structure.{Rules, Location}

case class GameHistory(moves: List[Move]) {
  def +(move: Move) = add(move)
  def add(move: Move) = copy(moves :+ move)

  def lastLocation = moves.lastOption.flatMap(move => Some(move.passChain.last.to)).getOrElse(Rules.startingLocation)

  def passes = for {
    move <- moves
    pass <- move.passChain
  } yield pass

  def locations = List(Rules.startingLocation) ++ (for {
    move <- moves
    pass <- move.passChain
    locations <- List(pass.to)
  } yield locations)

  def canPassOn(pass: Pass) = locations.contains(pass.to)

  def isPassRecorded(pass: Pass) = passes.contains(pass)
}

object GameHistory {
  val beginning = GameHistory(List.empty)
}