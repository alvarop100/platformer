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

class GroundedAppearance(val component: Character)(implicit val camera: Camera) extends 
MultipleReseteableAppearance[ReseteableAppearance, Character] {
  val running = new RunningAppearance(component)
  val standing = new StandingAppearance(component)
  
  override val appearances = Vector(running,standing)

  def doCopy: Appearance = {
    new GroundedAppearance(component)
  }
  
  def resetFiring() = {
    standing.resetFiring()
  }

  def appearanceFor(c: Character): ReseteableAppearance = {
    if (c.speed.x1.abs > 0) running else standing
  }

}
