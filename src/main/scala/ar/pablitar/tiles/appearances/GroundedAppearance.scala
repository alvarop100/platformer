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
import ar.pablitar.vainilla.appearances.worldspace.ReseteableAppearance

//TODO: Encontrar alguna forma de reutilizar orientation, teniendo en cuenta que multianimationappearance resetea las animaciones al mismo nivel. 
class GroundedAppearance(c: Character)(implicit val camera: Camera) extends MultiAppearance[ReseteableAppearance, Character](c)
  with MultipleReseteableAppearance[ReseteableAppearance, Character] {
  val running = new RunningAppearance(c)
  override val appearances = Vector(running,
    Resources.standingAnimation(EAST)(camera),
    Resources.standingAnimation(WEST)(camera),
    Resources.firingAnimation(EAST)(camera),
    Resources.firingAnimation(WEST)(camera))

  def doCopy: Appearance = {
    new GroundedAppearance(c)
  }
  
  def resetFiring() = {
    appearances(3).reset()
    appearances(4).reset()
  }

  def appearanceFor(c: Character): ReseteableAppearance = {
    (c.speed.x1.abs, c.facingDirection, c.firing) match {
      case (sp, _, _) if sp > 0 => appearances(0)
      case (0, EAST, true) => appearances(3)
      case (0, WEST, true) => appearances(4)
      case (0, EAST, false) => appearances(1)
      case (0, WEST, false) => appearances(2)
    }
  }

}
