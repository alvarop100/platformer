package ar.pablitar.tiles.appearances

import scala.Vector
import com.uqbar.vainilla.appearances.Appearance
import ar.pablitar.tiles.Resources
import ar.pablitar.tiles.components.Character
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceAnimation
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance
import ar.pablitar.vainilla.commons.math.Orientation

//TODO: Encontrar alguna forma de reutilizar orientation 
class GroundedAppearance(c: Character)(implicit val camera: Camera) extends MultiAppearance[WorldSpaceAnimation](c) with MultiAnimationAppearance[WorldSpaceAnimation] {
  override val appearances = Vector(
    Resources.standingAnimationLeft(camera),
    Resources.standingAnimationRight(camera),
    Resources.runningLeftAnimation(camera),
    Resources.runningRightAnimation(camera))

  def doCopy: Appearance = {
    new GroundedAppearance(c)
  }

  def appearanceFor(c: Character): WorldSpaceAnimation = {
    (c.speed.x1, c.facingDirection) match {
      case (sp, _) if sp < 0 => appearances(2)
      case (sp, _) if sp > 0 => appearances(3)
      case (0, Orientation.EAST) => appearances(0)
      case (0, Orientation.WEST) => appearances(1)
    }
  }

}
