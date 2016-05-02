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
  
  val jumping1 = spriteSheet.crop(15, 855, 78, 153);
  val jumping2 = spriteSheet.crop(134, 855, 70, 168);
  val jumping3 = spriteSheet.crop(255, 855, 69, 174);
  val jumping4 = spriteSheet.crop(360, 855, 81, 168);
  
  val jumpingSprites = List(jumping1, jumping2, jumping3, jumping4)
}