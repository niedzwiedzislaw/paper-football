package ghx.football.ui.desktop

import javafx.application.{Platform, Application}
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.stage.Stage

import ghx.football.domain.structure.{GameLost, GameWon, Direction, Player, PairOfPlayers, Session, Field}
import ghx.football.logging.Logging
import org.slf4j.helpers.BasicMarkerFactory

class App extends Application with Logging {
  logger.info("Starting app")

  override def start(primaryStage: Stage) {
    primaryStage.setTitle("Sup!")

    val root = new StackPane
    val session: Session = Session.newSession(PairOfPlayers.oneAndTwo, Field(10, 10))
    val view = new SessionView(session)
    new Thread(new Runnable() {
      def run(): Unit = {
        var done = false
        while (!done) {
          Platform.runLater(new Runnable() {
            def run(): Unit = {
              view.session = view.session.advance()
              view.draw()
              view.session.game match {
                case GameWon(_, _, _) | GameLost(_, _, _)  => done = true
                case _ =>
              }
            }
          })
          Thread.sleep(1000)
        }
      }
    }).start()
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