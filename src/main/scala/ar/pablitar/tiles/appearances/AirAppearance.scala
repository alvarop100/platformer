package ar.pablitar.tiles.appearances

import scala.Vector
import com.uqbar.vainilla.appearances.Appearance
import com.uqbar.vainilla.appearances.Sprite
import ar.pablitar.tiles.Resources
import ar.pablitar.tiles.components.Character
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance

class AirAppearance(component: Character)(implicit camera: Camera) extends 
  FiringBasedAppearance(component, 
      new OrientedAirAppearance(component, Resources.jumpingSpritesLeft,Resources.jumpingSpritesRight),
      new OrientedAirAppearance(component, Resources.jumpingShootingSpritesLeft,Resources.jumpingShootingSpritesRight))