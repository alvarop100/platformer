package ar.pablitar.tiles.appearances

import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceAnimation
import ar.pablitar.tiles.Resources
import ar.pablitar.vainilla.commons.math.Orientation._
import ar.pablitar.vainilla.appearances.Camera
import com.uqbar.vainilla.appearances.Appearance
import ar.pablitar.tiles.components.Character

class RunningAppearance(c: Character)(implicit val camera: Camera) extends MultiAppearance[WorldSpaceAnimation, Character](c)
    with OrientationBasedMultiAppearance[WorldSpaceAnimation, Character] with MultipleReseteableAppearance[WorldSpaceAnimation, Character] {
  override val appearances: Seq[WorldSpaceAnimation] = Seq(
    Resources.runningAnimation(EAST)(camera),
    Resources.runningAnimation(WEST)(camera))

  def doCopy: Appearance = {
    new RunningAppearance(c)
  }
}