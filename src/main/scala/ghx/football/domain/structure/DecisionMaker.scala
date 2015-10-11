package ghx.football.domain.structure

import ghx.football.domain.flow.{Move, Pass, GameHistory}

import scala.concurrent.duration.Duration
import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global

case class DecisionMaker(field: Field, previousPasses: PassChain) {

  private def lastLocation = previousPasses.map(_.to).lastOption.getOrElse(Rules.startingLocation)

  def locations = List(Rules.startingLocation) ++ (for {
    pass <- previousPasses
    locations <- List(pass.to)
  } yield locations)

  def possibleBeginnings: Seq[Pass] = {
    val theoreticalPasses = for {
      x <- -1 to 1
      y <- -1 to 1 if x != 0 || y != 0
    } yield Pass(lastLocation, lastLocation +(x, y))

    theoreticalPasses.filterNot(previousPasses.contains).filter(field.isPassCorrect)
  }

  private def possibleContinuations(pass: Pass): Seq[PassChain] = {
    if (locations.contains(pass.to) || field.canContinuePassing(pass)) {
      for {
        possibleContinuation <- (this + pass).possibleMoves
      } yield pass + possibleContinuation
    } else {
      List(List(pass))
    }
  }

  def possibleMoves: Seq[PassChain] = {
    Await.result(Future{
      for {
        pass <- possibleBeginnings
        continuedChains <- possibleContinuations(pass)
      } yield {
        pass + continuedChains
      }
    }, Duration("300s"))
  }

  def +(pass: Pass) = copy(previousPasses = previousPasses :+ pass)
}
