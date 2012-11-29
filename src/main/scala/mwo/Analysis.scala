package mwo

trait Analysis extends Simulation {
  def endTime:Time
  
  def basicSim:Mech = simulate(endTime)
  
  def firedWeapon(w:Weapon) : Map[Time,List[Weapon]] = w.history.map(f => (f.time,List(w))).toMap
  
  type DamagePoint = (Time,Damage)
  
  type DamagePoints = List[DamagePoint]
  
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
  
  def damage(mech:Mech):DamagePoints = {
    val weaponsMap = firedWeapons(mech.weapons)
    val damage = weaponsMap mapValues( ws => (ws map( w => w.damage)).sum ) withDefaultValue(Damage(0))
    
    timeSteps(Time("0"),endTime).map{ (t:Time) => (t,damage(t))}.toList
  }
  
  def accumulateDamage(timeSeries: DamagePoints):DamagePoints = {
    def accumulate(series:DamagePoints,damage:Damage,acc:DamagePoints) :DamagePoints = {
      series match {
        case Nil => acc.reverse
        case (t,d) :: rest => accumulate(rest,damage+d,(t,d+damage) :: acc)
      }
    }
    accumulate(timeSeries,Damage(0),List())
  }
  
  def analyse = accumulateDamage(damage(basicSim))
  
  def renderDamage(dp:DamagePoint) = dp._1.toString+"s "+dp._2.toString +" damage"
  
  def keyAnalysisPoints(damage:DamagePoints) = List(damage(0),damage((10/step).toInt),damage((20/step).toInt),damage.last).map(renderDamage).mkString("\n")
  
  def analysisPoints = keyAnalysisPoints(analyse)
}