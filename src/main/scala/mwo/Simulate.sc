package mwo

object Simulate {
  object AtlasDSim extends Simulation with Weapons with Analysis {
    override val step=Time("0.01")
    
    val endTime = Time("90")

    val initialMech = new Mech(Element(14,DoubleHeatSink()), weapons = List(gauss, ssrm2,ssrm2,llaser,llaser))
  }

  val analysis = AtlasDSim.analyse                //> analysis  : mwo.Simulate.AtlasDSim.DamagePoints = List((0,29), (0.01,29), (0
                                                  //| .02,29), (0.03,29), (0.04,29), (0.05,29), (0.06,29), (0.07,29), (0.08,29), (
                                                  //| 0.09,29), (0.10,29), (0.11,29), (0.12,29), (0.13,29), (0.14,29), (0.15,29), 
                                                  //| (0.16,29), (0.17,29), (0.18,29), (0.19,29), (0.20,29), (0.21,29), (0.22,29),
                                                  //|  (0.23,29), (0.24,29), (0.25,29), (0.26,29), (0.27,29), (0.28,29), (0.29,29)
                                                  //| , (0.30,29), (0.31,29), (0.32,29), (0.33,29), (0.34,29), (0.35,29), (0.36,29
                                                  //| ), (0.37,29), (0.38,29), (0.39,29), (0.40,29), (0.41,29), (0.42,29), (0.43,2
                                                  //| 9), (0.44,29), (0.45,29), (0.46,29), (0.47,29), (0.48,29), (0.49,29), (0.50,
                                                  //| 29), (0.51,29), (0.52,29), (0.53,29), (0.54,29), (0.55,29), (0.56,29), (0.57
                                                  //| ,29), (0.58,29), (0.59,29), (0.60,29), (0.61,29), (0.62,29), (0.63,29), (0.6
                                                  //| 4,29), (0.65,29), (0.66,29), (0.67,29), (0.68,29), (0.69,29), (0.70,29), (0.
                                                  //| 71,29), (0.72,29), (0.73,29), (0.74,29), (0.75,29), (0.76,29), (0.77,29), (0
                                                  //| .78,29), (0.79,29), (0.8
                                                  //| Output exceeds cutoff limit.
  analysis.last                                   //> res0: (mwo.Simulate.AtlasDSim.Time, mwo.Simulate.AtlasDSim.Damage) = (90.00,
                                                  //| 727)
  analysis(2000)                                  //> res1: (mwo.Simulate.AtlasDSim.Time, mwo.Simulate.AtlasDSim.Damage) = (20.00,
                                                  //| 183)
  
}