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

  test("Weapon can fire at start") {
    new TestSimulation() {
      assert(gauss.canFire(time0))
    }
  }
  
  test("weapon tracks history") {
    new TestSimulation() {
    
      assert(gauss.canFire(time0))
      val updatedWeapon = gauss.fire(time0)
      assert(updatedWeapon.history === List((Fired("0"))))
    }
  }

  test("Weapon can not fire during cooldown") {
    new TestSimulation() {
      val instant = new Weapon(damage ="1.0", heat = "1.0", cooldown = "2.0", history = List(Fired("0.0")))
      assert(!instant.canFire(time0))
      assert(!instant.canFire(Time("1.0")))
      assert(!instant.canFire(Time("1.99")))
    }
  }

  test("Weapon can fire after cooldown") {
    new TestSimulation() {
      val weapon= gauss.fire(time0)
      assert(weapon.canFire(Time("4.0")))
    }
  }
  
  test("Mech can fire an available weapon with heat capacity spare") {
    new TestSimulation() {
      assert(initialMech.canFire(gauss,time0))
    }
  }
  
  test("Mech can fire a weapon multiple times after cooldowns") {
    new TestSimulation() {
    	assert(gauss.fire(Time("0")).fire(Time("5")).canFire(Time("10")) === true)
    }
  }
  
  test("Mech can not fire an unavailable weapon") {
    new TestSimulation() {
      val updatedMech = initialMech.fire(gauss,time0)
      assert(updatedMech.element.current === gauss.heat)
      
      assert(!updatedMech.canFire(gauss,time0))
    }
  }
  
  test("Mech firing a weapon contains updated weapon with history of firing") {
    new TestSimulation {
      val updatedMech = initialMech.fire(gauss,time0)
      
      val firedGauss = gauss.fire(time0)
      assert(updatedMech.weapons===List(firedGauss,mlaser) )
    }
  }
  
  test("Mech can not fire when heat capacity would be exceeded") {
    new TestSimulation() {
    	val hotMech = new Mech(Element(capacity= "10",cooling = "1",current="9.0"), weapons = List(mlaser))
    	
    	assert(!hotMech.canFire(mlaser,time0))
    }
  }
  
  test("Step the simulation and confirm all weapons fired and generated heat") {
    new TestSimulation() {
      val capableMech = new Mech(Element(capacity= "40",cooling = "1"), weapons = List(gauss,mlaser,mlaser2))
      val updatedMech = step(capableMech,time0)
      
      assert(updatedMech.weapons.forall(_.wasLastFiredAt(time0)) === true)
      assert(updatedMech.element.current === gauss.heat + mlaser.heat+ mlaser2.heat - initialMech.element.cooling*step)
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
  
  test("Accumulate weapons again after cooldown") {
    new TestSimulation {
      override def initialMech = new Mech(Element(capacity= "40",cooling = "1"), weapons = List(gauss,mlaser,mlaser2))
      val results = simulate(Time("90"))
      val r = results
    }
  }
}