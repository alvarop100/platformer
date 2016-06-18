package ar.pablitar.tiles.appearances

import ar.pablitar.tiles.components.Character
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance
import ar.pablitar.vainilla.commons.math.Orientation


trait OrientationBasedMultiAppearance[T <: WorldSpaceAppearance] {
  this: MultiAppearance[T] =>
  def appearanceFor(c: Character): T = {
    c.facingDirection match {
      case Orientation.EAST => appearances(0)
      case _ => appearances(1)
    }
  }

}