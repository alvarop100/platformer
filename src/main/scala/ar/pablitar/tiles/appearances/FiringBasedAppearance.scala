package ar.pablitar.tiles.appearances

import ar.pablitar.vainilla.appearances.worldspace.ReseteableAppearance
import ar.pablitar.tiles.components.Character
import ar.pablitar.vainilla.appearances.Camera
import com.uqbar.vainilla.appearances.Appearance

class FiringBasedAppearance[A <: ReseteableAppearance](val component: Character, notFiring: A, firing: A)(implicit val camera: Camera)
    extends MultipleReseteableAppearance[A, Character] {

  def appearances = Vector(notFiring, firing)

  def appearanceFor(c: Character): A = {
    if (c.firing) firing else notFiring
  }
  
  def resetFiring() = {
    firing.reset()
  }

  def doCopy: Appearance = {
    new FiringBasedAppearance(component, notFiring, firing)
  }
}