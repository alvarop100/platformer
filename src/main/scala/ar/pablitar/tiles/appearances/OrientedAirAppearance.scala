package ar.pablitar.tiles.appearances

import scala.Vector
import com.uqbar.vainilla.appearances.Appearance
import com.uqbar.vainilla.appearances.Sprite
import ar.pablitar.tiles.Resources
import ar.pablitar.tiles.components.Character
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance
import ar.pablitar.vainilla.appearances.worldspace.ReseteableAppearance

class OrientedAirAppearance(val component: Character, west: Seq[Sprite], east: Seq[Sprite])(implicit val camera: Camera) extends MultiAppearance[SimpleAirAppearance, Character]
    with OrientationBasedMultiAppearance[SimpleAirAppearance, Character] with ReseteableAppearance {
  override val appearances = Seq(
    new SimpleAirAppearance(component, west),
    new SimpleAirAppearance(component, east))

  def doCopy: Appearance = {
    new OrientedAirAppearance(component, west, east)
  }
}