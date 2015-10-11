package ghx.football.domain.structure

import ghx.football.domain.structure.Location.Δ

case class Location(x: Int, y: Int) {
  def +(δ: (Int, Int)) = Location(x + δ._1, y + δ._2)

  def +(δ: Δ) = Location(x + δ.x, y + δ.y)
}

object Location {

  case class Δ(x: Int, y: Int)

}