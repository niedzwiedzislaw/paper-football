package ghx.football.domain.structure

import ghx.football.domain.flow.Pass

case class Field(width: Int, height: Int) {

  require(width >= 2, "Width has to be bigger than 2 because goal width 2")
  require(width % 2 == 0, "Width should be an even number to make field symmetrical")

  require(height >= 2, "Height has to be bigger than 2 because goals shouldn't be at starting location")
  require(height % 2 == 0, "Height should be an even number for fair play and symmetrical field")

  lazy val aspect = height / width

  lazy val top = height / 2
  lazy val bottom = -height / 2
  lazy val left = -width / 2
  lazy val right = width / 2
  lazy val corners = List(Location(top, left), Location(bottom, left), Location(bottom, right), Location(top, right))

  def canContinuePassing(pass: Pass) = destinationOnBorder(pass) && destinationNotInCorner(pass)

  def isPassCorrect(pass: Pass): Boolean = covers(pass) && notPassingOnBorder(pass)

  def isLosingPass(pass: Pass) = destinationInCorner(pass)

  def isWinningPass(pass: Pass) = passToGoal(pass)

  private def destinationOnBorder(pass: Pass) = pass.to.x == left || pass.to.x == right || ((pass.to.y == top || pass.to.y == bottom) && pass.to.x != 0)

  private def destinationNotInCorner(pass: Pass) = !corners.contains(pass.to)

  private def destinationInCorner(pass: Pass) = corners.contains(pass.to)

  private def covers(pass: Pass): Boolean = destinationInField(pass) || passToGoal(pass)

  private def destinationInField(pass: Pass): Boolean = left <= pass.to.x && pass.to.x <= right && bottom <= pass.to.y && pass.to.y <= top

  private def passToGoal(pass: Pass) = {
    val endsInGoal = (pass.to.y == top + 1 || pass.to.y == bottom - 1) && pass.to.x == 0
    val possibleToShoot = if (pass.from.x < -1 || 1 < pass.from.x)
      bottom < pass.from.y && pass.from.y < top
    else
      bottom == pass.from.y || pass.from.y == top

    endsInGoal && possibleToShoot
  }

  private def notPassingOnBorder(pass: Pass) =
    if (pass.from.x == pass.to.x)
      pass.from.x != left && pass.from.x != right
    else if (pass.from.y == pass.to.y)
      pass.from.y != top && pass.from.y != bottom
    else
      true
}
