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

class Character(implicit val camera: Camera) extends SpeedyComponent[TilesScene] {
  val groundedAppearance = new GroundedAppearance(this)
  val airAppearance = new AirAppearance(this)

  this.setAppearance(groundedAppearance)

  var characterState: CharacterState = Falling

  var facingDirection: Orientation = Orientation.WEST

  override def acceleration = characterState.acceleration

  override def update(state: DeltaState) = {
    this.getScene.floors.foreach(this.checkCollisionWith(_, state))
    cooldownElapsed += state.getDelta
    this.checkKeys(state)
    this.characterState.update(this, state)
    this.setAppearanceFor(this.characterState)
    this.getAppearance.update(state.getDelta)
    
    if(this.getY > 4000) {
      this.speed = (0, -300)
      this.position = (0,0)
    }
  }

  this.setZ(20)

  override def height = Resources.standing(0).getHeight - 5
  override def width = Resources.standing(0).getWidth

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

  def checkCollisionWith(floor: Floor, state: DeltaState) = {
    val afterPosition = this.positionAfterSpeed(state)
    //TODO: Detect fall. From grounded to not grounded
    if (!floor.puntoEstaDetras(this.bottomLeft()) && floor.puntoEstaDetras(this.bottomLeft(afterPosition))
      && this.getX > floor.topLeft().x1 && this.getX < floor.topRight().x1) {
      this.speed.x2 = 0
      this.position.x2 = floor.pared.puntoInterno.x2 - this.height
      this.characterState.grounded(this)
    } else if(this.speed.x2 != 0){
      this.characterState.falling(this)
    }
  }

  def setAppearanceFor(state: CharacterState) = {
    setAppearance(state match {
      case Grounded => groundedAppearance
      case Jumping(_) => airAppearance
      case Falling => airAppearance
    })
  }
  
  //TODO: Reuse cooldown behaviour
  
  val fireCooldownTime = 0.1
  var cooldownElapsed = fireCooldownTime

  def fireIfCooledDown() = {
    if(cooldownElapsed >= fireCooldownTime) {
      this.fire()
      cooldownElapsed = 0
    }
  }

  def fire() = {
    this.getScene.addComponent(new Bolt(this.busterPosition, this.facingDirection))
  }

  def busterPosition = {
    this.center(this.facingDirection)
  }
}