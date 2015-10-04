package ghx.football.domain.flow

import ghx.football.domain.structure.Player

case class Move(player: Player, from: (Int, Int), to: (Int, Int))
