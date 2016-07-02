package ar.pablitar.tiles.appearances

import scala.Vector
import com.uqbar.vainilla.appearances.Appearance
import com.uqbar.vainilla.appearances.Sprite
import ar.pablitar.tiles.Resources
import ar.pablitar.tiles.components.Character
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance

class AirAppearance(val component: Character)(implicit val camera: Camera) extends MultiAppearance[SimpleAirAppearance, Character]
    with OrientationBasedMultiAppearance[SimpleAirAppearance, Character] {
  override val appearances = Seq(
    new SimpleAirAppearance(component, Resources.jumpingSpritesLeft),
    new SimpleAirAppearance(component, Resources.jumpingSpritesRight))

  def doCopy: Appearance = {
    new AirAppearance(component)
  }
}