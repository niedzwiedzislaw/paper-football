package ghx.football.ui.desktop

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.stage.Stage

import ghx.football.domain.structure.Field
import ghx.football.logging.Logging
import org.slf4j.helpers.BasicMarkerFactory

class App extends Application with Logging {
  logger.info("Starting app")

  override def start(primaryStage: Stage) {
    primaryStage.setTitle("Sup!")

    val root = new StackPane
    val view = new FieldView
    view.draw(Field(10, 10))
    root.getChildren.add(view)

    primaryStage.setScene(new Scene(root))
    primaryStage.show()
  }
}

object App {
  def main(args: Array[String]) {
    Application.launch(classOf[App], args: _*)
  }
}