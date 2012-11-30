package mwo

object Simulate {
  val atlasD = new MWOSimulation {
    val initialMech = Mech(Element(14,DoubleHeatSink()), weapons = List(gauss, ssrm2,ssrm2,llaser,llaser))
  }.analysisPoints                                //> atlasD  : String = 0s 43 damage
                                                  //| 10.00s 147 damage
                                                  //| 20.00s 249 damage
                                                  //| 90.00s 827 damage
  val atlasRS = new MWOSimulation {
    val initialMech = Mech(Element(19,DoubleHeatSink()), weapons = List(gauss, ssrm2,ssrm2,mlaser,mlaser,mlaser,mlaser))
  }.analysisPoints                                //> atlasRS  : String = 0s 45 damage
                                                  //| 10.00s 155 damage
                                                  //| 20.00s 260 damage
                                                  //| 90.00s 925 damage
  
  val atlasDC = new MWOSimulation {
    val initialMech = Mech(Element(19,DoubleHeatSink()), weapons = List(gauss,ssrm2,ssrm2,ssrm2,llaser,erppc))
  }.analysisPoints                                //> atlasDC  : String = 0s 49 damage
                                                  //| 10.00s 156 damage
                                                  //| 20.00s 258 damage
                                                  //| 90.00s 970 damage
  val hbkSP = new MWOSimulation {
    val initialMech = Mech(Element(16,DoubleHeatSink()), weapons = List(mlaser,mlaser,mlaser,mlaser,srm6,srm4))
  }.analysisPoints                                //> hbkSP  : String = 0s 45 damage
                                                  //| 10.00s 150 damage
                                                  //| 20.00s 205 damage
                                                  //| 90.00s 590 damage
  val hbkG = new MWOSimulation {
   val initialMech = Mech(Element(10,DoubleHeatSink()), weapons = List(mlaser,mlaser,ac20))
  }.analysisPoints                                //> hbkG  : String = 0s 30 damage
                                                  //| 10.00s 140 damage
                                                  //| 20.00s 185 damage
                                                  //| 90.00s 395 damage
                                                  
  val atlasDPPC = new MWOSimulation {
    val initialMech = Mech(Element(20,DoubleHeatSink()), weapons = List(erppc,erppc,mlaser,mlaser))
  }.analysisPoints                                //> atlasDPPC  : String = 0s 30 damage
                                                  //| 10.00s 85 damage
                                                  //| 20.00s 125 damage
                                                  //| 90.00s 410 damage
}