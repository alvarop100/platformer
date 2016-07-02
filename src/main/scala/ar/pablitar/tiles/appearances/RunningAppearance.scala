package ar.pablitar.tiles.appearances

import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceAnimation
import ar.pablitar.tiles.Resources
import ar.pablitar.vainilla.commons.math.Orientation._
import ar.pablitar.vainilla.appearances.Camera
import com.uqbar.vainilla.appearances.Appearance
import ar.pablitar.tiles.components.Character
import ar.pablitar.vainilla.appearances.worldspace.ReseteableAppearance
import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceAnimation
import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceAnimation

class RunningAppearance(component: Character)(implicit camera: Camera) extends FiringBasedAppearance[
  SimpleOrientedAppearance[WorldSpaceAnimation, Character]](component, 
      new SimpleOrientedAppearance(component, Resources.runningAnimation), 
      new SimpleOrientedAppearance(component, Resources.runningShootingAnimation)) {
  
  override def resetFiring() {
    
  }
  
  override def onAppearanceChanged(lastAppearance: SimpleOrientedAppearance[WorldSpaceAnimation, Character],
      newAppearance: SimpleOrientedAppearance[WorldSpaceAnimation, Character]) = {
    if(lastAppearance != null)
      newAppearance.appearances.zip(lastAppearance.appearances).foreach { t =>
        t._1.copyProgressFrom(t._2)
      }
  }
}