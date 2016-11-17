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
import com.uqbar.vainilla.sound.SoundBuilder

object Resources {
  val tileSet = TileSet.fromFile("tileset.png", 63, 63, 1)
  val spriteSheet = Sprite.fromImage("spritesheet.png")
  val goodFruit = Sprite.fromImage("good-fruit.png").scale(0.05)
  val badFruit = Sprite.fromImage("bad-fruit.png").scale(0.05)
  val poke = Sprite.fromImage("poke.png").scale(0.3)
  val death = Sprite.fromImage("death.png").scale(0.1)
 /* val goodItem = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("item-good.mp3"))
  val hit = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("danio2.mp3"))
  val deathSound = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("gameover.wav"))
  val pikachu = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("pikachu.wav"))
  val loop = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("loop-principal.wav"))
   */
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

  val runningShooting = Seq[Sprite](
    (15, 1620, 135, 147),
    (165, 1622, 150, 147),
    (345, 1629, 144, 147),
    (510, 1626, 161, 147),
    (900, 1635, 132, 147),
    (1050, 1635, 117, 147),
    (1200, 1632, 147, 147),
    (1365, 1632, 150, 147),
    (1530, 1628, 159, 147),
    (1875, 1632, 150, 147),
    (2190, 1632, 138, 147),
    (2355, 1638, 123, 147))

  def orientedAnimationFactory(mean: Double, sprites: Seq[Sprite], eastOriented: Boolean = false): Map[Orientation, AnimFactory] = {
    val originals: AnimFactory = (mean, sprites)
    Map(
      Orientation.EAST -> {
        if (eastOriented) originals else originals.andThen(_.flipHorizontally())
      },
      Orientation.WEST -> {
        if (eastOriented) originals.andThen(_.flipHorizontally()) else originals
      })
  }

  val standingAnimation = orientedAnimationFactory(0.15, standing)
  val runningAnimation = orientedAnimationFactory(0.04, running)
  val runningShootingAnimation = orientedAnimationFactory(0.04, runningShooting)

  val jumpingSpritesRight = Seq[Sprite](
    (15, 834, 78, 174),
    (134, 849, 70, 174),
    (255, 855, 69, 174),
    (360, 849, 81, 174))

  val jumpingSpritesLeft = jumpingSpritesRight.map(_.flipHorizontally())
  
  val jumpingShootingSpritesRight = Seq[Sprite](
    (2655, 1578, 102, 174),
    (2775, 1584, 102, 174),
    (2895, 1584, 101, 174),
    (3015, 1575, 120, 174))
    
    val jumpingShootingSpritesLeft = jumpingShootingSpritesRight.map(_.flipHorizontally())

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
  
  val citySprite = Sprite.fromImage("city.png")
  val deepCitySprite = Sprite.fromImage("city-deeper.png").scale(0.8)
  val deepestCitySprite = Sprite.fromImage("city-deepest.png").scale(0.6)

}