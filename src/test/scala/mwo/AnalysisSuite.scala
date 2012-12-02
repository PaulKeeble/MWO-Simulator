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

    val gauss = new Weapon(damage = "15.0", heat = "1.0", cooldown = "4.0", history = List())
    val gauss2 = gauss.cloneNew

    def initialMech = new Mech(Element(capacity = "2", cooling = "1"), weapons = List(gauss))
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
        (Time(0), Damage("15.0")),
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

      val dam15 = Damage("15.0")
      val dam30 = dam15 + dam15
      val expected = List(
        (Time(0), dam15),
        (Time(1), dam15),
        (Time(2), dam15),
        (Time(3), dam15),
        (Time(4), dam30),
        (Time(5), dam30))
      assert(expected === accumulated)
    }
  }

  //Weapons fire at Time0 for X heat and again at Time4 for 1 heat
  //cooling is 0.1 per second
  test("Heat overtime") {
    new TestAnalysis {
      val heatTrace = heat(basicSim)

      val dam15 = Damage("15.0")
      val dam30 = dam15 + dam15
      val expected = List(
        (Time(0), Heat("0.9")),
        (Time(1), Heat("0.8")),
        (Time(2), Heat("0.7")),
        (Time(3), Heat("0.6")),
        (Time(4), Heat("1.5")),
        (Time(5), Heat("1.4")))
      assert(expected === heatTrace)
    }
  }
}