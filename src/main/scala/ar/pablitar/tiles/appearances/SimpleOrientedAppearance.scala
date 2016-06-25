package ar.pablitar.tiles.appearances

import scala.Vector
import com.uqbar.vainilla.appearances.Appearance
import ar.pablitar.tiles.Resources
import ar.pablitar.tiles.components.Character
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceAnimation
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance
import ar.pablitar.vainilla.commons.math.Orientation
import ar.pablitar.vainilla.commons.math.Orientation._
import ar.pablitar.tiles.Resources.AnimFactory
import com.uqbar.vainilla.GameComponent
import ar.pablitar.tiles.components.Oriented

//TODO: Encontrar alguna forma de reutilizar orientation, teniendo en cuenta que multianimationappearance resetea las animaciones al mismo nivel. 
class SimpleOrientedAppearance[T <: WorldSpaceAppearance, C <: GameComponent[_] with Oriented](c: C, east: T, west: T)
(implicit val camera: Camera) extends MultiAppearance[T, C](c) with OrientationBasedMultiAppearance[T, C] {
  
  override val appearances = Vector(east, west)

  def doCopy: Appearance = {
    new SimpleOrientedAppearance(c, east, west)
  }

}
