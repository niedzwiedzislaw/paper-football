package ghx.football.domain.structure

case class Field(width: Int, height: Int) {
  require(width >= 2, "Width has to be bigger than 2 because goal width 2")
  require(width % 2 == 0, "Width should be an even number to make field symmetrical")
  
  require(height >= 2, "Height has to be bigger than 2 because goals shouldn't be at starting location")
  require(height %2 == 0, "Height should be an even number for fair play and symmetrical field")
}
