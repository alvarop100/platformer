package ar.pablitar.tiles.appearances

import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceAnimation
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance
import ar.pablitar.tiles.components.Character
import com.uqbar.vainilla.GameComponent
import ar.pablitar.vainilla.appearances.worldspace.ReseteableAppearance

trait MultipleReseteableAppearance[T <: ReseteableAppearance, C <: GameComponent[_]] extends MultiAppearance[T, C] with ReseteableAppearance {
  var lastAppearance: T = null.asInstanceOf[T]

  override def update(delta: Double) {
    val newAppearance = appearanceFor(component)
    if (newAppearance != lastAppearance) {
      onAppearanceChanged(lastAppearance, newAppearance)
      lastAppearance = newAppearance
    }
    appearanceFor(component).update(delta)
  }
  
  def onAppearanceChanged(lastAppearance: T, newAppearance: T): Unit = {
     newAppearance.reset()
  }
 
  override def reset() = {
    appearanceFor(component).reset()
  }
}