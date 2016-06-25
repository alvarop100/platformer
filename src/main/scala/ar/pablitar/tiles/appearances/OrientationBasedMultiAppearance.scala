package ar.pablitar.tiles.appearances

import ar.pablitar.tiles.components.Character
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance
import ar.pablitar.vainilla.commons.math.Orientation
import com.uqbar.vainilla.GameComponent
import ar.pablitar.tiles.components.Oriented


trait OrientationBasedMultiAppearance[T <: WorldSpaceAppearance, C <: GameComponent[_] with Oriented] {
  this: MultiAppearance[T, C] =>
  def appearanceFor(c: C): T = {
    c.facingDirection match {
      case Orientation.EAST => appearances(0)
      case _ => appearances(1)
    }
  }

}