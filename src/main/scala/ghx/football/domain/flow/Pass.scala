package ghx.football.domain.flow

import ghx.football.domain.structure.{Passes, Location}

case class Pass(from: Location, to: Location) {

  private def invert = Pass(from = to, to = from)

  def ===(that: Pass) = from == that.from && to == that.to

  def +(passes: Passes) = this +: passes

  override def equals(o: Any) = o match {
    case that: Pass => this === that || this.invert === that
    case _ => false
  }
}
