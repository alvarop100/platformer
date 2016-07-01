package ar.pablitar.tiles

import ar.pablitar.vainilla.appearances.TileSet
import com.uqbar.vainilla.appearances.Sprite
import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceAnimation
import com.uqbar.vainilla.appearances.Animation
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceAnimation
import ar.pablitar.vainilla.commons.math.Orientation
import ar.pablitar.tiles.components.Character
import ar.pablitar.vainilla.commons.math.Vector2D

object Resources {
  val tileSet = TileSet.fromFile("tileset.png", 63, 63, 1)
  val spriteSheet = Sprite.fromImage("spritesheet.png")

  implicit class SpriteExtensions(s: Sprite) {

    def offset: Vector2D = (s.getX, s.getY)
    def offset_=(v: Vector2D) = {
      s.setX(v.x1)
      s.setY(v.x2)
    }

    def centerBottom = {
      s.offset = (-s.getWidth / 2, -s.getHeight)
      s
    }

    def addOffset(v: Vector2D) = { s.offset += v; s }
  }

  implicit def toupleToSprite = (t: (Int, Int, Int, Int)) => spriteSheet.crop(t._1, t._2, t._3, t._4).centerBottom.addOffset(0, 5)
  implicit def toupleToAnimationFactory = (t: (Double, Seq[Sprite])) => WorldSpaceAnimation(new Animation(t._1, t._2: _*))(_: Camera)

  type AnimFactory = Camera => WorldSpaceAnimation

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

  def orientedAnimationFactory(mean: Double, sprites: Seq[Sprite], eastOriented: Boolean = false) = {
    val originals: AnimFactory = (mean, sprites)
    Map(
      Orientation.EAST -> {
        if (eastOriented) originals else originals.andThen(_.flipHorizontally())
      },
      Orientation.WEST -> {
        if(eastOriented) originals.andThen(_.flipHorizontally()) else originals
      })
  }

  val standingAnimation = orientedAnimationFactory(0.15, standing)
  val runningAnimation = orientedAnimationFactory(0.04, running)

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
    (2376, 140, 45, 24))

  val firingFrame1: Sprite = (855, 1269, 123, 147)

  val firing = Seq[Sprite](
    firingFrame1,
    firingFrame1,
    firingFrame1,
    firingFrame1,
    (1005, 1269, 114, 147),
    (1155, 1272, 105, 147))

  val firingAnimation = orientedAnimationFactory(Character.firingStateTime / firing.length, firing)

  val boltAnimation = orientedAnimationFactory(0.1, bolt)

}