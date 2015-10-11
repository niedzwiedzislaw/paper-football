package ghx.football.domain.structure

sealed trait MoveResult

case object Win extends MoveResult
case object Loss extends MoveResult
case object Continuation extends MoveResult
