package ar.pablitar.tiles.appearances

import scala.Vector
import com.uqbar.vainilla.appearances.Appearance
import com.uqbar.vainilla.appearances.Sprite
import ar.pablitar.tiles.Resources
import ar.pablitar.tiles.components.Character
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance
import ar.pablitar.vainilla.appearances.worldspace.ReseteableAppearance
import ar.pablitar.vainilla.commons.math.Orientation._

class StandingAppearance(component: Character)(implicit camera: Camera) extends 
  FiringBasedAppearance(component, 
      new SimpleOrientedAppearance(component, Resources.standingAnimation), 
      new SimpleOrientedAppearance(component, Resources.firingAnimation))