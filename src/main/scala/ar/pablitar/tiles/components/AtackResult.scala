package ar.pablitar.tiles.components

import com.uqbar.vainilla.GameComponent
import ar.pablitar.tiles.TilesScene

case class Attack(attacker: GameComponent[TilesScene] , defender: GameComponent[TilesScene] )

trait AttackResult {
  def attack: Attack
}

case class Hit(attack: Attack, damage: Int, critical: Boolean = false) extends AttackResult
