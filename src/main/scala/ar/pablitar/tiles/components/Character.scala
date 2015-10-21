package ar.pablitar.tiles.components

import com.uqbar.vainilla.DeltaState
import com.uqbar.vainilla.events.constants.Key

import ar.pablitar.tiles.Resources
import ar.pablitar.tiles.TilesScene
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.commons.components.SpeedyComponent
import ar.pablitar.vainilla.commons.math.Vector2D

class Character(implicit val camera: Camera) extends SpeedyComponent[TilesScene] {
  this.setAppearance(Resources.standingAnimation(camera))
  
  var characterState: CharacterState = Falling
  
  override def acceleration = characterState.acceleration
  
  override def update(state: DeltaState) = {
    this.checkCollisionWith(this.getScene.floor, state)
    this.checkKeys(state)
    this.characterState.update(this, state)
  }
  
  this.setZ(20)
  
  override def height = super.height - 5

  def checkKeys(state: DeltaState) = {
    speed.x1 = 0
    if(state.isKeyBeingHold(Key.LEFT)) {
      this.speed.x1 = -200
    }
    if(state.isKeyBeingHold(Key.RIGHT)) {
      this.speed.x1 = 200
    }
    
    if(state.isKeyPressed(Key.SPACE)) {
      this.characterState.jump(this)
    }
    
    if(state.isKeyReleased(Key.SPACE)) {
      this.characterState.stopJump(this)
    }
  }
  
  def checkCollisionWith(floor: Floor, state: DeltaState) = {
    val afterPosition = this.positionAfterSpeed(state)
    //TODO: Detect fall. From grounded to not grounded
    if(!floor.puntoEstaDetras(this.bottomLeft()) && floor.puntoEstaDetras(this.bottomLeft(afterPosition))
        && this.getX > floor.topLeft().x1 && this.getX < floor.topRight().x1) {
      this.speed.x2 = 0
      this.position.x2 = floor.pared.puntoInterno.x2 - this.height
      this.characterState.grounded(this)
    }
  }
}