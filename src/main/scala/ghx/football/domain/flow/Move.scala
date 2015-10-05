package ghx.football.domain.flow

import ghx.football.domain.structure.{Location, Player}

case class Move(player: Player, from: Location, to: Location)
