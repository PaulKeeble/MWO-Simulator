package mwo

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class SimulationSuite extends FunSuite {
  trait TestSimulation extends Simulation {
	val step = Time("1") 
    val time0 = Time("0")
    
	 val gauss = new Weapon(damage = "15.0", heat = "1.0", cooldown = "4.0")
	 val mlaser = new Weapon(damage = "5.0", heat = "4.0", cooldown = "3.0")
	 val mlaser2 = mlaser.cloneNew
	 
	 def initialMech = new Mech(Element(capacity= "2",cooling = "1"), weapons = List(gauss,mlaser))
  }
  
  test("Step the simulation and confirm all weapons fired and generated heat") {
    new TestSimulation() {
      val capableMech = new Mech(Element(capacity= "40",cooling = "1"), weapons = List(gauss,mlaser,mlaser2))
      val updatedMech = step(capableMech,time0)
      
      assert(updatedMech.weapons.forall(_.wasLastFiredAt(time0)) === true)
      assert(updatedMech.element.current === gauss.heat + mlaser.heat+ mlaser2.heat - initialMech.element.cooling/10*step)
    }
  }
  
  test("Step simulation a second time during weapon cooldown, weapons are not fired") {
    new TestSimulation() {
      val capableMech = new Mech(Element(capacity= "40",cooling = "1"), weapons = List(gauss,mlaser,mlaser2))
      val updatedMech = step(step(capableMech,time0), Time("1"))
      
      assert(updatedMech.weapons.forall(_.wasLastFiredAt(Time("1"))) === false)
    }
  }
  
  test("Step simulation a second after weapon cooldown, weapons should fire") {
    new TestSimulation() {
      val capableMech = new Mech(Element(capacity= "40",cooling = "1"), weapons = List(gauss,mlaser,mlaser2))
      val updatedMech = step(step(capableMech,time0), Time("10"))
      
      assert(updatedMech.weapons.forall(_.wasFiredAt(Time("10"))) === true)
      assert(updatedMech.weapons.forall(_.wasFiredAt(Time("0"))) === true)
    }
  }
}