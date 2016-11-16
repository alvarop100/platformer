package ar.pablitar.tiles.components

import ar.pablitar.vainilla.commons.components.RichGameComponent
import ar.pablitar.vainilla.commons.math.Vector2D
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.tiles.TilesScene
import com.uqbar.vainilla.DeltaState
import com.uqbar.vainilla.appearances.SimpleAppearance
import ar.pablitar.tiles.Resources
import ar.pablitar.vainilla.commons.math.Semiplane

class BuffItem (width: Int, height: Int, buffType : BuffType)(aPosition: Vector2D = (0, 0))(implicit val camera: Camera) extends RichGameComponent[TilesScene]{
  
  var alive =true
   this.position = aPosition
   
   def pared = Semiplane(aPosition, Vector2D(0, -1))

  def puntoEstaDetras(aPoint: Vector2D) = {
    pared.puntoEstaDetras(aPoint)
  }
  
   override def update(state: DeltaState) = {
    if(!alive) {
      this.destroy()
    } else {
      super.update(state)
    }
  }
  def die(){
    alive=false
  }
  
  this.setAppearance(buffType.getAppearance.crop(0, 0, height, width).center())
 
}