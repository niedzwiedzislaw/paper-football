package ghx.football.domain.structure

sealed trait Direction {
  def opposite: Direction
}

object Directions {

  case object Up extends Direction {
    def opposite = Down
  }

  case object Down extends Direction {
    def opposite = Up
  }

}