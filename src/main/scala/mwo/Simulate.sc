package mwo

object Simulate {
  val atlasD = new MWOSimulation {
    val initialMech = Mech(Element(14,DoubleHeatSink()), weapons = List(gauss, ssrm2,ssrm2,llaser,llaser))
  }.analysisPoints                                //> atlasD  : String = 0s 43 damage
                                                  //| 10.00s 147 damage
                                                  //| 20.00s 276 damage
                                                  //| 90.00s 1109 damage
  val atlasRS = new MWOSimulation {
    val initialMech = Mech(Element(19,DoubleHeatSink()), weapons = List(gauss, ssrm2,ssrm2,mlaser,mlaser,mlaser,mlaser))
  }.analysisPoints                                //> atlasRS  : String = 0s 45 damage
                                                  //| 10.00s 155 damage
                                                  //| 20.00s 290 damage
                                                  //| 90.00s 1225 damage
  
  val atlasDC = new MWOSimulation {
    val initialMech = Mech(Element(19,DoubleHeatSink()), weapons = List(gauss,ssrm2,ssrm2,ssrm2,llaser,erppc))
  }.analysisPoints                                //> atlasDC  : String = 0s 49 damage
                                                  //| 10.00s 166 damage
                                                  //| 20.00s 313 damage
                                                  //| 90.00s 1297 damage
  val hbkSP = new MWOSimulation {
    val initialMech = Mech(Element(16,DoubleHeatSink()), weapons = List(mlaser,mlaser,mlaser,mlaser,srm6,srm4))
  }.analysisPoints                                //> hbkSP  : String = 0s 45 damage
                                                  //| 10.00s 155 damage
                                                  //| 20.00s 290 damage
                                                  //| 90.00s 1215 damage
  val hbkG = new MWOSimulation {
   val initialMech = Mech(Element(10,DoubleHeatSink()), weapons = List(mlaser,mlaser,ac20))
  }.analysisPoints                                //> hbkG  : String = 0s 30 damage
                                                  //| 10.00s 140 damage
                                                  //| 20.00s 250 damage
                                                  //| 90.00s 1050 damage
                                                  
  
}