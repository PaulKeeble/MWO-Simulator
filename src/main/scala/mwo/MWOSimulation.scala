package mwo

abstract class MWOSimulation extends Simulation with Analysis with Weapons {
	val step=Time("0.01")
    val endTime = Time("90")
}