package ar.pablitar.tiles.components

import com.uqbar.vainilla.DeltaState
import com.uqbar.vainilla.events.constants.Key
import ar.pablitar.tiles.Resources
import ar.pablitar.tiles.TilesScene
import ar.pablitar.tiles.appearances.AirAppearance
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.commons.components.SpeedyComponent
import ar.pablitar.vainilla.commons.math.Orientation
import ar.pablitar.tiles.appearances.GroundedAppearance
import java.awt.Graphics2D
import ar.pablitar.vainilla.appearances.worldspace.Rectangle
import java.awt.Color
import com.uqbar.vainilla.GameComponent
import ar.pablitar.tiles.appearances.CharacterAppearance

object Character {
  val firingStateTime = 0.3
}

class Character(implicit val camera: Camera) extends SpeedyComponent[TilesScene] with Oriented {

  import Character._

  val charAppearance = new CharacterAppearance(this)

  this.setAppearance(charAppearance)

  var characterState: CharacterState = Falling

  var facingDirection: Orientation = Orientation.WEST

  override def acceleration = characterState.acceleration

  var frame = 0l

  override def update(state: DeltaState) = {
    this.checkCollisionWithFloors(state)
    cooldownElapsed = (cooldownElapsed + state.getDelta).min(firingStateTime)
    this.checkKeys(state)
    this.characterState.update(this, state)
    this.getAppearance.update(state.getDelta)

    if (this.getY > 4000) {
      this.speed = (0, 0)
      this.position = (0, 0)
    }
    frame += 1
  }

  this.setZ(20)

  override def height = Resources.standing(0).getHeight - 5
  override def width = Resources.standing(0).getWidth

  //  override def appearanceCenter = (width / 2, height)

  def checkKeys(state: DeltaState) = {
    speed.x1 = 0
    if (state.isKeyBeingHold(Key.LEFT)) {
      this.speed.x1 = -500
      this.facingDirection = Orientation.EAST
    }
    if (state.isKeyBeingHold(Key.RIGHT)) {
      this.speed.x1 = 500
      this.facingDirection = Orientation.WEST
    }

    if (state.isKeyPressed(Key.S)) {
      this.fireIfCooledDown()
    }

    if (state.isKeyPressed(Key.SPACE)) {
      this.characterState.jump(this)
    }

    if (state.isKeyReleased(Key.SPACE)) {
      this.characterState.stopJump(this)
    }
  }

  def checkCollisionWith(floor: Floor, state: DeltaState): Boolean = {
    val afterPosition = this.positionAfterSpeed(state)
    if ( //Rango horizontal
    this.bottomRight().x1 > floor.topLeft().x1 && this.bottomLeft().x1 < floor.topRight().x1 &&
      //Colisión vertical
      ((!floor.puntoEstaDetras(this.position) && floor.puntoEstaDetras(afterPosition))
        || floor.pared.puntoInterno.x2 == this.position.x2)) {

      this.speed.x2 = 0
      this.setY(floor.pared.puntoInterno.x2)
      this.characterState.grounded(this)
      true
    } else {
      false
    }
  }

  def checkCollisionWithFloors(state: DeltaState) = {
    if (!this.getScene.floors.exists(this.checkCollisionWith(_, state))) {
      this.characterState.falling(this)
    }
  }

  override def render(graphics: Graphics2D) = {
    super.render(graphics)
    val self = this
    val debugRect = new Rectangle(7, 7, Color.YELLOW)
    debugRect.render(new GameComponent() {
      override def getX() = self.getX()
      override def getY() = self.getY()
    }, graphics)
  }

  //TODO: Reuse cooldown behaviour

  val fireCooldownTime = 0.05
  var cooldownElapsed = Character.firingStateTime

  def fireIfCooledDown() = {
    if (cooldownElapsed >= fireCooldownTime) {
      this.fire()
      cooldownElapsed = 0
    }
  }

  override def appearanceCenter = (this.width / 2, this.height)

  def fire() = {
    charAppearance.resetFiring()
    this.getScene.addComponent(new Bolt(this.busterPosition, this.facingDirection))
  }

  def firing = {
    cooldownElapsed < firingStateTime
  }

  def busterPosition = {
    this.orientedCenter(this.facingDirection) + (0, -13)
  }
}