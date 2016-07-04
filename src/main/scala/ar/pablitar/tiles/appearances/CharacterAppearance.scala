package ar.pablitar.tiles.appearances

import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.appearances.worldspace.ReseteableAppearance
import ar.pablitar.tiles.components.Character
import com.uqbar.vainilla.appearances.Appearance
import ar.pablitar.tiles.components.Grounded
import ar.pablitar.tiles.components.Jumping

class CharacterAppearance(val component: Character)(implicit val camera: Camera)
  extends MultipleReseteableAppearance[ReseteableAppearance, Character] {
  val grounded = new GroundedAppearance(component)
  val onAir = new AirAppearance(component)
  
  override val appearances = Vector(grounded, onAir)

  def doCopy: Appearance = {
    new CharacterAppearance(component)
  }
  
  def resetFiring() = {
    grounded.resetFiring()
    onAir.resetFiring()
  }

  def appearanceFor(c: Character): ReseteableAppearance = {
    c.characterState match {
      case Grounded => grounded
      case _ => onAir
    }
  }
}