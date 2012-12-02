package mwo

import scala.annotation.tailrec
import scala.math.max
import scala.math.BigDecimal

trait Simulation extends GameDef {
  def step : Time
  
  def initialMech : Mech
  
  def cool(now:Time,mech:Mech) : Mech = {
    mech.cool(now)
  }
  
  def step(mech:Mech,moment:Time) : Mech = {
    def fireWeapons(mech:Mech,weapons:List[Weapon]) : Mech = weapons match {
      case Nil => mech
      case weapon :: rest => if(mech.canFire(weapon, moment)) {
    	  	val updatedMech = mech.fire(weapon,moment)
    	  	fireWeapons(updatedMech,rest)
	      } else {
	        fireWeapons(mech,rest)
	      }
    }

    cool(moment,fireWeapons(mech,mech.weapons))
  }
  
  def timeSteps(from:Time,to:Time) : Stream[Time] = 
    if(from>to) Stream.Empty 
    else from #:: timeSteps(from+step,to)

  def simulate(end: Time): Mech = timeSteps(Time("0"),end).foldLeft(initialMech)((m,t) => step(m,t))

}

//ammo
//beam duration
//other weapon priorities than order
//longer cooldowns based on real combat scenario
//LRMs fire slower on some mechs as they fire in small batches