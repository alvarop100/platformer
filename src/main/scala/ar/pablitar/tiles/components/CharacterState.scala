package ar.pablitar.tiles.components

import ar.pablitar.vainilla.commons.math.Vector2D
import com.uqbar.vainilla.DeltaState

trait CharacterState {
  def grounded(character:Character) = {}
  def jump(character: Character) = {}

  def stopJump(character: Character) = {}

  def update(character: Character, state: DeltaState) = {
    character.applySpeed(state)
    character.applyAcceleration(state)
  }

  val acceleration:Option[Vector2D] = Some(Vector2D(0, 2000))
}

case object Grounded extends CharacterState {
  override def jump(character: Character) = {
    character.speed += Vector2D(0, -800)
    character.characterState = Jumping(character.getY()) 
  }
}

case object Falling extends CharacterState {
  override def grounded(character:Character) = {
    character.characterState = Grounded
  }
}
case class Jumping(startingHeight: Double) extends CharacterState {
  val maxHeight = 250
  
  override def stopJump(character: Character) = {
    character.characterState = Falling
  }
  
  override def update(character: Character, state: DeltaState) {
    super.update(character, state)
    if(startingHeight - character.getY > maxHeight) {
      stopJump(character)
    }
  }
  
  override val acceleration = Some(Vector2D(0, 1000))
}