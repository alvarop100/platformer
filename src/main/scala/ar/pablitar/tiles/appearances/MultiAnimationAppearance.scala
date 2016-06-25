package ar.pablitar.tiles.appearances

import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceAnimation
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance
import ar.pablitar.tiles.components.Character
import com.uqbar.vainilla.GameComponent

trait MultiAnimationAppearance[T <: WorldSpaceAnimation, C <: GameComponent[_]] {
  this: MultiAppearance[T, C] =>
  var lastAnimation: WorldSpaceAnimation = null

  override def update(delta: Double) {
    val animation = appearanceFor(c)
    if (animation != lastAnimation) {
      lastAnimation = animation
      animation.reset()
    }
    appearanceFor(c).update(delta)
  }
  
  override def reset() = {
    appearanceFor(c).reset()
  }
}