package mwo

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class AnalysisSuite extends FunSuite {
  trait TestAnalysis extends Analysis {
    val endTime = Time("5")
    override val step = Time("1")
    val time0 = Time(0)

    val gauss = new Weapon(damage = "15.0", heat = "1.0", cooldown = "4.0")
    val gauss2 = gauss.cloneNew
    val llaser = new Weapon(damage = "9", heat = "7", cooldown = "3.25")

    def initialMech = new Mech(Element(capacity = "10", cooling = "1"), weapons = List(gauss,llaser))
  }

  test("firedWeapons map") {
    new TestAnalysis {
      val gauss1fired = gauss.fire(Time(0)).fire(Time(4))
      val gauss2fired = gauss2.fire(Time(0)).fire(Time(5))

      val expected = Map(
        (time0, List(gauss2fired, gauss1fired)),
        (Time(4), List(gauss1fired)),
        (Time(5), List(gauss2fired)))
      assert(firedWeapons(List(gauss1fired, gauss2fired)) === expected)
    }
  }

  test("weapons firing converted to damage") {
    new TestAnalysis {
      val damagePoints = damage(basicSim)
      val expected = List(
        (Time(0), Damage("24.0")),
        (Time(1), Damage(0)),
        (Time(2), Damage(0)),
        (Time(3), Damage(0)),
        (Time(4), Damage("15.0")),
        (Time(5), Damage(0)))
      assert(expected === damagePoints)
    }
  }

  test("Accumulate Damage") {
    new TestAnalysis {
      val accumulated = accumulate(damage(basicSim))

      val dam24 = Damage("24")
      val dam39 = dam24 + Damage("15")
      val expected = List(
        (Time(0), dam24),
        (Time(1), dam24),
        (Time(2), dam24),
        (Time(3), dam24),
        (Time(4), dam39),
        (Time(5), dam39))
      assert(expected === accumulated)
    }
  }

  //Weapons fire at Time0 for X heat and again at Time4 for 1 heat
  //cooling is 0.1 per second
  test("Heat overtime") {
    new TestAnalysis {
      val heatTrace = heat(basicSim)

      val expected = List(
        (Time(0), Heat("7.9")),
        (Time(1), Heat("7.8")),
        (Time(2), Heat("7.7")),
        (Time(3), Heat("7.6")),
        (Time(4), Heat("8.5")),
        (Time(5), Heat("8.4")))
      assert(expected === heatTrace)
    }
  }
  
  test("Weapons fired trace") {
    new TestAnalysis {
      val firedTrace = fired(basicSim)
      
      
    }
  }
}