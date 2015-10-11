package ghx.football.ui.desktop

import ghx.football.domain.flow.GameHistory
import ghx.football.domain.structure.{Location, Field, Session}

import scalafx.beans.property.DoubleProperty
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class SessionView(var session: Session) extends Canvas(600, 400) {

  import SessionView._

  def field = session.game.field

  def history = session.game.history

  def draw(): Unit = {
    graphicsContext2D.clearRect(0, 0, width, height)
    graphicsContext2D.stroke = Color(0, 0, 0, 1)
    graphicsContext2D.lineWidth = 2

    draw(field)
    draw(history)
  }

  def draw(field: Field): Unit = {
    val rectangle = fieldRectangle(field)
    val halfHeight = field.height / 2
    val halfWidth = field.width / 2

    val points = List(
      τ(field.left, field.bottom), // bottom left
      τ(field.left, field.top), // top left
      τ(-1, field.top),
      τ(-1, field.top + 1),
      τ(1, field.top + 1),
      τ(1, field.top),
      τ(field.right, field.top), // top right
      τ(field.right, field.bottom), // bottom right
      τ(1, field.bottom),
      τ(1, field.bottom - 1),
      τ(-1, field.bottom - 1),
      τ(-1, field.bottom)
    )

    graphicsContext2D.lineWidth = 3
    graphicsContext2D.stroke = Color.hsb(0, 0, 0)

    graphicsContext2D.strokeLine(points.head._1, points.head._2, points(1)._1, points(1)._2)
    for (point <- points.tail) {
      graphicsContext2D.lineTo(point._1, point._2)
    }
    graphicsContext2D.lineTo(points.head._1, points.head._2)
    graphicsContext2D.strokePath()

    graphicsContext2D.lineWidth = 0.5
    graphicsContext2D.stroke = Color.hsb(0, 0, 0, 0.2)

    for (x <- (center._1 - halfHeight * fieldUnit) to (center._1 + halfHeight * fieldUnit) by fieldUnit) {
      if (x == center._1)
        graphicsContext2D.strokeLine(x, center._2 - (1 + halfHeight) * fieldUnit, x, center._2 + (1 + halfHeight) * fieldUnit)
      else
        graphicsContext2D.strokeLine(x, center._2 - halfHeight * fieldUnit, x, center._2 + halfHeight * fieldUnit)
    }

    for (y <- (center._2 - halfHeight * fieldUnit) to (center._2 + halfHeight * fieldUnit) by fieldUnit) {
      if (y == center._2) {
        graphicsContext2D.stroke = Color.hsb(0, 0, 0, 0.4)
        graphicsContext2D.strokeLine(center._1 - halfHeight * fieldUnit, y, center._1 + halfHeight * fieldUnit, y)
        graphicsContext2D.stroke = Color.hsb(0, 0, 0, 0.2)
      } else
        graphicsContext2D.strokeLine(center._1 - halfHeight * fieldUnit, y, center._1 + halfHeight * fieldUnit, y)
    }
  }

  def draw(history: GameHistory): Unit = {
    graphicsContext2D.lineWidth = 2

    val colors = List(Color.hsb(0, 0.6, 0.8), Color.hsb(120, 0.6, 0.8)).toStream
    val colorsStream = Iterator.continually(colors).flatten

    history.moves.foreach {
      move => move.passChain.foreach {
        graphicsContext2D.stroke = colorsStream.next()
        pass =>
          graphicsContext2D.strokeLine(τx(pass.from), τy(pass.from), τx(pass.to), τy(pass.to))
      }
    }
    graphicsContext2D.drawImage(new Image("/football.png"), τx(history.lastLocation)-5, τy(history.lastLocation)-5, 11, 11)
  }


  def aspect = height.value / width.value

  def center = (width.value / 2, height.value / 2)

  lazy val fieldUnit = if (field.aspect < this.aspect) {
    val w = 0.8 * width.value
    val h = (field.height * w) / field.width
    0.8 * width / field.width
  } else {
    val h = 0.8 * height.value
    val w = (field.width * h) / field.height
    0.8 * height / field.height
  }

  private def fieldRectangle(field: Field): Rectangle = {
    Rectangle(
      center._1 - fieldUnit * field.width / 2,
      center._2 - fieldUnit * field.height / 2,
      fieldUnit * field.width,
      fieldUnit * field.height
    )
  }

  def τ(location: Location) = (τx(location.x), τy(location.y))
  def τ(x: Int, y: Int) = (τx(x), τy(y))
  def τx(x: Int) = center._1 + fieldUnit * x
  def τy(y: Int) = center._2 + fieldUnit * y
  def τx(location: Location) = center._1 + fieldUnit * location.x
  def τy(location: Location) = center._2 + fieldUnit * location.y
}

object SessionView {
  implicit def property2double(p: DoubleProperty): Double = p.value
}