package ar.pablitar.tiles

import ar.pablitar.vainilla.appearances.TileSet
import com.uqbar.vainilla.appearances.Sprite
import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceAnimation
import com.uqbar.vainilla.appearances.Animation
import ar.pablitar.vainilla.appearances.Camera

object Resources {
  val tileSet = TileSet.fromFile("tileset.png", 63, 63, 1)
  val spriteSheet = Sprite.fromImage("spritesheet.png")
  val standing1 = spriteSheet.crop(1515, 66, 105, 147)
  val standing2 = spriteSheet.crop(1650, 63, 105, 147)
  val standing3 = spriteSheet.crop(1785, 60, 105, 147)
  val standingAnimation = WorldSpaceAnimation(new Animation(0.15, standing1,standing2, standing3))(_:Camera)
}