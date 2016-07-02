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
import ar.pablitar.vainilla.appearances.worldspace.ReseteableAppearance

class SimpleOrientedAppearance[T <: ReseteableAppearance, C <: GameComponent[_] with Oriented]
(val component: C, factories: Map[Orientation,  Camera => T])
(implicit val camera: Camera) extends OrientationBasedMultiAppearance[T, C] with ReseteableAppearance{
  
  val appearancesMap = factories.mapValues(_.apply(camera))
  
  override val appearances = Vector(appearancesMap(EAST), appearancesMap(WEST))

  def doCopy: Appearance = {
    new SimpleOrientedAppearance(component, factories)
  }
  
  override def reset() = {
    appearanceFor(component).reset()
  }

}

//TODO: Implicit para construir uno de estos fácilmente
//object OrientedImplicits {
//  
//}
