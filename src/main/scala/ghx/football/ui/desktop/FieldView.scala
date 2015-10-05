package ghx.football.ui.desktop

import ghx.football.domain.flow.{Turn, Move}
import ghx.football.domain.structure.Field

import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.{Color, Paint}
import scalafx.scene.shape.Rectangle

class FieldView extends Canvas(600, 400) {
  def draw(field: Field) = {
    graphicsContext2D.stroke = Color(0, 0, 0, 1)
    graphicsContext2D.lineWidth = 2

    val rectangle = fieldRectangle(field)
    graphicsContext2D.strokeRect(rectangle.x.value, rectangle.y.value, rectangle.width.value, rectangle.height.value)
  }

  def drawMove(move: Move) = ???
  def drawTurn(turn: Turn) = ???

  def aspect = height.value / width.value

  private def fieldRectangle(field: Field): Rectangle = {
    if (field.aspect < this.aspect) {
      val w = 0.8 * width.value
      val h = (field.height * w) / field.width
      Rectangle(0.1 * width.value, Math.abs(height.value - h) / 2, w, h)
    } else {
      val h = 0.8 * height.value
      val w = (field.width * h) / field.height
      Rectangle(Math.abs(width.value - w) / 2, 0.1 * height.value, w, h)
    }
  }
}
