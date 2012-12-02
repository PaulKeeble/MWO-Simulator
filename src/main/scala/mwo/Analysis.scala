package mwo

trait Analysis extends Simulation {
  def endTime:Time
  
  def basicSim:Mech = simulate(endTime)
  
  def firedWeapon(w:Weapon) : Map[Time,List[Weapon]] = w.history.map(f => (f.time,List(w))).toMap
  
  type DamagePoint = (Time,Damage)
  
  type DamagePoints = List[DamagePoint]
  
  type HeatPoint = (Time,Heat)
  
  type HeatPoints = List[HeatPoint]
  
  type KeyPoints = List[(Time,Damage,Heat)]
  
  //three cases, in m1 only, in m2 only, in m and m2
  def merge(m1:Map[Time,List[Weapon]],m2:Map[Time,List[Weapon]]):Map[Time,List[Weapon]]= {
    val m1UniqAndMerged = m1.map{ case (t,ws) => if(m2.isDefinedAt(t)) (t,m2(t) ++ ws) else (t,ws)}
    
    val m2Uniq = m2.filter{ case (t,ws) => !m1UniqAndMerged.isDefinedAt(t)}
    m1UniqAndMerged ++ m2Uniq
  }
  
  def firedWeapons(weapons : List[Weapon]) : Map[Time,List[Weapon]] = {
   val weaponFiringMaps = weapons.map(firedWeapon)
   weaponFiringMaps.foldLeft(Map[Time,List[Weapon]]())((m,m2) => merge(m,m2)) 
  }
  
  def byTime(mech:Mech)(f:Weapon => BigDecimal):List[(Time,BigDecimal)] = {
    val weaponsMap = firedWeapons(mech.weapons)
    val summationByTime = weaponsMap mapValues( ws => (ws map(f)).sum ) withDefaultValue(Damage(0))
    
    timeSteps(Time("0"),endTime).map{ (t:Time) => (t,summationByTime(t))}.toList
  }
  
  def damage(mech:Mech):DamagePoints = byTime(mech)(w=> w.damage)

  def heat(mech:Mech):HeatPoints = mech.element.history.toList.sortBy{ case (t,h) => t}
  
  def accumulate(timeSeries:List[(Time,BigDecimal)]):List[(Time,BigDecimal)] = {
    def accumulate(series:List[(Time,BigDecimal)],total:BigDecimal,acc:List[(Time,BigDecimal)]) :List[(Time,BigDecimal)] = {
      series match {
        case Nil => acc.reverse
        case (t,d) :: rest => accumulate(rest,total+d,(t,d+total) :: acc)
      }
    }
    accumulate(timeSeries,Heat(0),List())
  }
  
  def combine(damage:DamagePoints,heat:HeatPoints): KeyPoints = damage.zip(heat).map(v=> (v._1._1,v._1._2,v._2._2))
    
  def analyse = {
    val sim = basicSim
    
    val d = accumulate(damage(sim))
    val h = heat(sim)
    combine(d,h)
  }
  
  def render(dp:(Time,Damage,Heat)) = dp._1.toString+"s "+dp._2.toString +" damage " +dp._3.toString + " heat"
  
  def keyAnalysisPoints(damage:KeyPoints) = List(damage(0),damage((10/step).toInt),damage((20/step).toInt),damage.last).map(render).mkString("\n")
  
  def analysisPoints = keyAnalysisPoints(analyse)
}