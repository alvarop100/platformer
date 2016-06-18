package ar.pablitar.tiles.appearances

import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceAnimation
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance
import ar.pablitar.tiles.components.Character

trait MultiAnimationAppearance[T <: WorldSpaceAnimation] {
  this: MultiAppearance[T] =>
  var lastAnimation: WorldSpaceAnimation = null

  override def update(delta: Double) {
    val animation = appearanceFor(c)
    if (animation != lastAnimation) {
      lastAnimation = animation
      animation.reset()
    }
    appearanceFor(c).update(delta)
  }
}