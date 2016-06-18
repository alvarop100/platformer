package ar.pablitar.tiles

import ar.pablitar.vainilla.appearances.TileSet
import com.uqbar.vainilla.appearances.Sprite
import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceAnimation
import com.uqbar.vainilla.appearances.Animation
import ar.pablitar.vainilla.appearances.Camera

object Resources {
  val tileSet = TileSet.fromFile("tileset.png", 63, 63, 1)
  val spriteSheet = Sprite.fromImage("spritesheet.png")

  implicit def toupleToSprite = (t: (Int, Int, Int, Int)) => spriteSheet.crop(t._1, t._2, t._3, t._4)

  val standing = Seq[Sprite](
    (1515, 66, 105, 147), (1650, 63, 105, 147), (1785, 60, 105, 147))

  val running = Seq[Sprite](
    (7, 477, 102, 147),
    (143, 480, 103, 147),
    (270, 471, 121, 147),
    (435, 474, 117, 147),
    (585, 471, 144, 147),
    (920, 474, 100, 147),
    (1035, 480, 96, 147),
    (1155, 477, 120, 147),
    (1305, 477, 114, 147),
    (1455, 474, 126, 147),
    (1800, 477, 114, 147),
    (2072, 477, 106, 147),
    (2193, 483, 96, 147))

  val standingAnimationRight = WorldSpaceAnimation(new Animation(0.15, standing: _*))(_: Camera)
  val standingAnimationLeft = standingAnimationRight.andThen(_.flipHorizontally())
  val runningRightAnimation = WorldSpaceAnimation(new Animation(0.05, running: _*))(_: Camera)
  val runningLeftAnimation = runningRightAnimation.andThen(_.flipHorizontally())

  val jumpingSpritesRight = Seq[Sprite](
    (15, 855, 78, 153),
    (134, 855, 70, 168),
    (255, 855, 69, 174),
    (360, 855, 81, 168))

  val jumpingSpritesLeft = jumpingSpritesRight.map(_.flipHorizontally())
  
  val bolt = Seq[Sprite](
    (2376, 27, 45, 24),
    (2376, 63, 45, 24),
    (2376, 102, 45, 24),
    (2376, 140, 45, 24)
  )
  
  val boltAnimation = WorldSpaceAnimation(new Animation(0.1, bolt:_*))(_:Camera)
  
}