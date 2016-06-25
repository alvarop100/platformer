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

//TODO: Encontrar alguna forma de reutilizar orientation, teniendo en cuenta que multianimationappearance resetea las animaciones al mismo nivel. 
class GroundedAppearance(c: Character)(implicit val camera: Camera) extends MultiAppearance[WorldSpaceAnimation, Character](c)
  with MultiAnimationAppearance[WorldSpaceAnimation, Character] {
  
  override val appearances = Vector(
    Resources.standingAnimation(WEST)(camera),
    Resources.standingAnimation(EAST)(camera),
    Resources.runningAnimation(WEST)(camera),
    Resources.runningAnimation(EAST)(camera),
    Resources.firingAnimation(WEST)(camera),
    Resources.firingAnimation(EAST)(camera))

  def doCopy: Appearance = {
    new GroundedAppearance(c)
  }
  
  def resetFiring() = {
    appearances(4).reset()
    appearances(5).reset()
  }

  def appearanceFor(c: Character): WorldSpaceAnimation = {
    (c.speed.x1, c.facingDirection, c.firing) match {
      case (sp, _, _) if sp < 0 => appearances(2)
      case (sp, _, _) if sp > 0 => appearances(3)
      case (0, EAST, true) => appearances(4)
      case (0, WEST, true) => appearances(5)
      case (0, EAST, false) => appearances(0)
      case (0, WEST, false) => appearances(1)
    }
  }

}
