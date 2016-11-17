package ar.pablitar.tiles.components

import ar.pablitar.vainilla.commons.components.RichGameComponent
import ar.pablitar.vainilla.commons.math.Vector2D
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.tiles.TilesScene
import com.uqbar.vainilla.DeltaState
import com.uqbar.vainilla.colissions.Bounds
import ar.pablitar.tiles.Resources
import ar.pablitar.vainilla.commons.math.RectangularBounds
import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceSimpleAppearance
import com.uqbar.vainilla.colissions.CollisionDetector

class BuffItem (buffType : BuffType)(aPosition: Vector2D = (0, 0))(implicit val camera: Camera) extends RichGameComponent[TilesScene]{
  
  var alive =true
   this.position = aPosition
   

  def collideWIthChar(char: Character) = {
    char.getX>=getX && char.getX <= getX+ getAppearance.getWidth && char.getY >= getY && char.getY <= getY+ getAppearance.getHeight
  }
   override def update(state: DeltaState) = {
    if(!alive) {
      this.getScene.buffs.-=(this)
      this.getScene.removeComponent(this)
      this.destroy()
    } else {
      super.update(state)
    }
  }
  def isBad()={
    buffType.isBad()
  }
  def die(){
    alive=false
  }
  def takeEffectOn(char : Character){
    buffType.takeEffectOn(char,this)
  }
  this.setAppearance(WorldSpaceSimpleAppearance(buffType.getAppearance.copy.center()))
 
}