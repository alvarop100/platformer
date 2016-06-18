package ar.pablitar.tiles.appearances

import scala.Vector
import com.uqbar.vainilla.appearances.Appearance
import com.uqbar.vainilla.appearances.Sprite
import ar.pablitar.tiles.Resources
import ar.pablitar.tiles.components.Character
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance

class AirAppearance(c: Character)(implicit val camera: Camera) extends MultiAppearance[SimpleAirAppearance](c)
    with OrientationBasedMultiAppearance[SimpleAirAppearance] {
  override val appearances = Seq(
    new SimpleAirAppearance(c, Resources.jumpingSpritesLeft),
    new SimpleAirAppearance(c, Resources.jumpingSpritesRight))

  def doCopy: Appearance = {
    new AirAppearance(c)
  }
}