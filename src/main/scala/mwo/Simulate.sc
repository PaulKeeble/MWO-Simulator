package mwo

object Simulate {
  val atlasD = new MWOSimulation {
    val initialMech = Mech(Element(14,DoubleHeatSink()), weapons = List(gauss, ssrm2,ssrm2,llaser,llaser))
  }.analysisPoints                                //> atlasD  : String = 0s 43 damage 18.9704 heat
                                                  //| 10.00s 147 damage 41.3704 heat
                                                  //| 20.00s 249 damage 47.7704 heat
                                                  //| 90.00s 827 damage 44.5704 heat
  val atlasRS = new MWOSimulation {
    val initialMech = Mech(Element(19,DoubleHeatSink()), weapons = List(gauss, ssrm2,ssrm2,mlaser,mlaser,mlaser,mlaser))
  }.analysisPoints                                //> atlasRS  : String = 0s 45 damage 20.9634 heat
                                                  //| 10.00s 155 damage 42.3634 heat
                                                  //| 20.00s 260 damage 55.7634 heat
                                                  //| 90.00s 925 damage 53.5634 heat
  
  val atlasRSPPC = new MWOSimulation {
    val initialMech = Mech(Element(19,DoubleHeatSink()), weapons = List(ppc,ppc,ppc,ppc))
  }.analysisPoints                                //> atlasRSPPC  : String = 0s 40 damage 35.9634 heat
                                                  //| 10.00s 100 damage 53.3634 heat
                                                  //| 20.00s 140 damage 52.7634 heat
                                                  //| 90.00s 420 damage 48.5634 heat
  
  val atlasDC = new MWOSimulation {
    val initialMech = Mech(Element(19,DoubleHeatSink()), weapons = List(gauss,ssrm2,ssrm2,ssrm2,llaser,erppc))
  }.analysisPoints                                //> atlasDC  : String = 0s 49 damage 26.9634 heat
                                                  //| 10.00s 156 damage 51.3634 heat
                                                  //| 20.00s 258 damage 55.7634 heat
                                                  //| 90.00s 970 damage 55.5634 heat
  val hbkSP = new MWOSimulation {
    val initialMech = Mech(Element(16,DoubleHeatSink()), weapons = List(mlaser,mlaser,mlaser,mlaser,srm6,srm4))
  }.analysisPoints                                //> hbkSP  : String = 0s 45 damage 22.9676 heat
                                                  //| 10.00s 150 damage 48.5676 heat
                                                  //| 20.00s 205 damage 50.1676 heat
                                                  //| 90.00s 590 damage 51.3676 heat
  val hbkG = new MWOSimulation {
   val initialMech = Mech(Element(10,DoubleHeatSink()), weapons = List(mlaser,mlaser,ac20))
  }.analysisPoints                                //> hbkG  : String = 0s 30 damage 13.976 heat
                                                  //| 10.00s 140 damage 37.976 heat
                                                  //| 20.00s 185 damage 39.976 heat
                                                  //| 90.00s 395 damage 39.976 heat
                                                  
  val atlasDPPC = new MWOSimulation {
    val initialMech = Mech(Element(20,DoubleHeatSink()), weapons = List(erppc,erppc,mlaser,mlaser))
  }.analysisPoints                                //> atlasDPPC  : String = 0s 30 damage 33.962 heat
                                                  //| 10.00s 85 damage 54.962 heat
                                                  //| 20.00s 125 damage 53.962 heat
                                                  //| 90.00s 410 damage 50.962 heat
  val recon = new MWOSimulation {
    val initialMech = Mech(Element(12,DoubleHeatSink()), weapons = List(ac2,mplaser,ssrm2))
  }.analysisPoints                                //> recon  : String = 0s 13 damage 5.9732 heat
                                                  //| 10.00s 81 damage 12.1732 heat
                                                  //| 20.00s 154 damage 20.3732 heat
                                                  //| 90.00s 594 damage 44.7732 heat
  val chaosPhract = new MWOSimulation {
    val initialMech = Mech(Element(15,DoubleHeatSink()), weapons = List(ppc,ppc,gauss))
  }.analysisPoints                                //> chaosPhract  : String = 0s 35 damage 18.969 heat
                                                  //| 10.00s 125 damage 43.969 heat
                                                  //| 20.00s 200 damage 42.969 heat
                                                  //| 90.00s 685 damage 49.969 heat
  
  val chaosAtlas = new MWOSimulation {
    val initialMech = Mech(Element(16,DoubleHeatSink()), weapons = List(gauss,srm6,srm6,srm6,mlaser,mlaser))
  }.analysisPoints                                //> chaosAtlas  : String = 0s 70 damage 20.9676 heat
                                                  //| 10.00s 220 damage 38.5676 heat
                                                  //| 20.00s 365 damage 52.1676 heat
                                                  //| 90.00s 1415 damage 51.3676 heat
 val chaosAtlas2 = new MWOSimulation {
    val initialMech = Mech(Element(17,DoubleHeatSink()), weapons = List(srm6,srm6,srm6,gauss,ppc))
  }.analysisPoints                                //> chaosAtlas2  : String = 0s 70 damage 21.9662 heat
                                                  //| 10.00s 220 damage 41.1662 heat
                                                  //| 20.00s 375 damage 52.3662 heat
                                                  //| 90.00s 1405 damage 45.7662 heat
 val chaosAtlas3 = new MWOSimulation {
    val initialMech = Mech(Element(17,DoubleHeatSink()), weapons = List(slaser,slaser,srm6,srm6,srm6,lbx10))
  }.analysisPoints                                //> chaosAtlas3  : String = 0s 61 damage 17.9662 heat
                                                  //| 10.00s 215 damage 32.1662 heat
                                                  //| 20.00s 389 damage 52.3662 heat
                                                  //| 90.00s 1054 damage 51.7662 heat
 val atlasdctest = new MWOSimulation {
    val initialMech = Mech(Element(20,DoubleHeatSink()), weapons = List(gauss,ppc,ppc,srm6,srm6,srm6))
  }.analysisPoints                                //> atlasdctest  : String = 0s 80 damage 30.962 heat
                                                  //| 10.00s 240 damage 54.962 heat
                                                  //| 20.00s 385 damage 52.962 heat
                                                  //| 90.00s 1435 damage 53.962 heat
 val streakapult = new MWOSimulation {
    val initialMech = Mech(Element(21,SingleHeatSink()), weapons = List(ssrm2,ssrm2,ssrm2,ssrm2,ssrm2,ssrm2))
  }.analysisPoints                                //> streakapult  : String = 0s 30 damage 11.979 heat
                                                  //| 10.00s 90 damage 14.979 heat
                                                  //| 20.00s 180 damage 29.979 heat
                                                  //| 90.00s 595 damage 48.979 heat
  val atlasHeadshot = new MWOSimulation {
    val initialMech = Mech(Element(19,SingleHeatSink()), weapons = List(gauss,ppc,ppc))
  }.analysisPoints                                //> atlasHeadshot  : String = 0s 35 damage 18.981 heat
                                                  //| 10.00s 115 damage 46.981 heat
                                                  //| 20.00s 165 damage 47.981 heat
                                                  //| 90.00s 555 damage 40.981 heat
  
  val practACs = new MWOSimulation {
    val initialMech = Mech(Element(11,DoubleHeatSink()), weapons = List(ac5,ac5,ac2,ac2))
  }.analysisPoints                                //> practACs  : String = 0s 14 damage 3.9746 heat
                                                  //| 10.00s 144 damage 28.5746 heat
                                                  //| 20.00s 264 damage 45.1746 heat
                                                  //| 90.00s 828 damage 44.3746 heat
  val practUACs = new MWOSimulation {
    val initialMech = Mech(Element(11,DoubleHeatSink()), weapons = List(uac5,ac2,ac2))
  }.analysisPoints                                //> practUACs  : String = 0s 14 damage 3.9746 heat
                                                  //| 10.00s 184 damage 36.5746 heat
                                                  //| 20.00s 264 damage 45.1746 heat
                                                  //| 90.00s 618 damage 44.3746 heat
  val pract2X = new MWOSimulation {
    val initialMech = Mech(Element(19,DoubleHeatSink()), weapons = List(gauss,srm6,srm6,mlaser,mlaser,mlaser))
  }.analysisPoints                                //> pract2X  : String = 0s 60 damage 20.9634 heat
                                                  //| 10.00s 195 damage 38.3634 heat
                                                  //| 20.00s 330 damage 55.7634 heat
                                                  //| 90.00s 1195 damage 53.5634 heat
  val atlasDMlaser = new MWOSimulation {
    val initialMech = Mech(Element(16,DoubleHeatSink()), weapons = List(gauss,uac5,ssrm2,ssrm2,mlaser,mlaser,mlaser,mlaser))
  }.analysisPoints                                //> atlasDMlaser  : String = 0s 55 damage 22.9676 heat
                                                  //| 10.00s 225 damage 48.5676 heat
                                                  //| 20.00s 370 damage 50.1676 heat
                                                  //| 90.00s 1435 damage 51.3676 heat
}