package mwo

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GameDefSuite extends FunSuite {
  trait TestGame extends GameDef {
    override val step = Time(1)

    val gauss = new Weapon(damage = "15.0", heat = "1.0", cooldown = "4.0", history = List())
    val gauss2 = gauss.cloneNew
    
    val mlaser = new Weapon(damage = "5.0", heat = "4.0", cooldown = "3.0")
	val mlaser2 = mlaser.cloneNew
    
    def initialMech = new Mech(Element(capacity= "2",cooling = "1"), weapons = List(gauss,mlaser))

    val time0 = Time(0)
  }
  
    test("Weapon can fire at start") {
    new TestGame() {
      assert(gauss.canFire(time0))
    }
  }
  
  test("weapon tracks history") {
    new TestGame() {
    
      assert(gauss.canFire(time0))
      val updatedWeapon = gauss.fire(time0)
      assert(updatedWeapon.history === List((Fired("0"))))
    }
  }

  test("Weapon can not fire during cooldown") {
    new TestGame() {
      val instant = new Weapon(damage ="1.0", heat = "1.0", cooldown = "2.0", history = List(Fired("0.0")))
      assert(!instant.canFire(time0))
      assert(!instant.canFire(Time("1.0")))
      assert(!instant.canFire(Time("1.99")))
    }
  }

  test("Weapon can fire after cooldown") {
    new TestGame() {
      val weapon= gauss.fire(time0)
      assert(weapon.canFire(Time("4.0")))
    }
  }
  
  test("Mech can fire an available weapon with heat capacity spare") {
    new TestGame() {
      assert(initialMech.canFire(gauss,time0))
    }
  }
  
  test("Mech can fire a weapon multiple times after cooldowns") {
    new TestGame() {
    	assert(gauss.fire(Time("0")).fire(Time("5")).canFire(Time("10")) === true)
    }
  }
  
  test("Mech can not fire an unavailable weapon") {
    new TestGame() {
      val updatedMech = initialMech.fire(gauss,time0)
      assert(updatedMech.element.current === gauss.heat)
      
      assert(!updatedMech.canFire(gauss,time0))
    }
  }
  
  test("Mech firing a weapon contains updated weapon with history of firing") {
    new TestGame {
      val updatedMech = initialMech.fire(gauss,time0)
      
      val firedGauss = gauss.fire(time0)
      assert(updatedMech.weapons===List(firedGauss,mlaser) )
    }
  }
  
  test("Mech can not fire when heat capacity would be exceeded") {
    new TestGame() {
    	val hotMech = new Mech(Element(capacity= "10",cooling = "1",current="9.0"), weapons = List(mlaser))
    	
    	assert(!hotMech.canFire(mlaser,time0))
    }
  }

  test("A hot mech can't fire weapons") {
    new TestGame {
      def mech = new Mech(Element(capacity = "40", cooling = "1", current = "39.9"), weapons = List(gauss))
      assert(mech.canFire(gauss, Time("5")) === false)
    }
  }

  test("cooling") {
    new TestGame {
      val expectedHeat = Heat("39.8")
      def mech = new Mech(Element(capacity = "40", cooling = "1", current = "39.9"), weapons = List(gauss))
      def cooledMech = mech.cool(time0)

      assert(cooledMech.element.current === expectedHeat)
      assert(cooledMech.element.history(time0) === expectedHeat)
    }
  }

  test("repeated heating at same time") {
    new TestGame {
      val expectedHeat = gauss.heat + gauss2.heat
      def mech = new Mech(Element(capacity = "40", cooling = "1", current = "0"), weapons = List(gauss, gauss2))
      val heatedMech = mech.fire(gauss, time0).fire(gauss2, time0)

      assert(heatedMech.element.current === expectedHeat)
      assert(heatedMech.element.history(time0) === expectedHeat)
      assert(heatedMech.element.history.size === 1)
    }
  }
}