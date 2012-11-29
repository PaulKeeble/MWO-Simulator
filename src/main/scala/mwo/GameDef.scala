package mwo

trait GameDef {
  def step : Time
  
  type Heat = BigDecimal
  type Damage = BigDecimal
  type Time = BigDecimal
  
  implicit def sToBD(s:String) = BigDecimal(s)
  
  def Time(s:String):Time = BigDecimal(s)
  def Time(i:Int):Time = BigDecimal(i)
  
  def Damage(s:String):Damage = BigDecimal(s)
  def Damage(i:Int):Damage = BigDecimal(i)
   
  def Heat(s:String):Heat = BigDecimal(s)
  def Heat(i:Int):Heat = BigDecimal(i)
  
  case class Fired(time:Time)
  
  case class Weapon(name:String="", damage : Damage, heat : Heat, cooldown: Time, beam:Time = Time("0"),history:List[Fired] = List()){
     def canFire(now:Time):Boolean = history match {
       case Nil => true
       case last :: rest => now - last.time >= cooldown 
     }
     
     def firedEvent(now:Time) :Weapon = this.copy(history = Fired(now) :: history)
     
     def fire(now:Time):Weapon = 
       if(canFire(now)) firedEvent(now)
       else throw new RuntimeException("Unable to fire weapon")
     
     def cloneNew = copy(name,damage,heat,cooldown,beam,List())
     
     def wasLastFiredAt(time:Time) : Boolean = lastFireEvent match {
         case Some(moment) => moment.time == time
         case None => false
       }
     
     def wasFiredAt(time:Time) : Boolean = history.contains(Fired(time))
     
     def lastFireEvent : Option[Fired] = history.headOption
  }
  
  /**
   * Cooling is in heat per second
   */
  case class Element(capacity:Heat,cooling:Heat,current:Heat=Heat("0")) {
    
    def hasCapacity(newHeat:Heat) : Boolean = newHeat< capacity - current
    
    def addHeat(newHeat:Heat) : Element = Element(capacity,cooling,current + newHeat)
    
    def coolHeat : Element = Element(capacity,cooling, BigDecimal(0).max(current - (cooling* step)))
  }
  
  abstract class HeatSink {
    def cooling:Heat
    def engineCooling:Heat
  }
  case class DoubleHeatSink extends HeatSink {
    val cooling = Heat("1.4")
    val engineCooling = Heat("2.0")
  }
  case class SingleHeatSink extends HeatSink {
    val cooling = Heat("1.0")
    val engineCooling = Heat("1.0")
  }
  
  def Element(sinks:Int,ty:HeatSink) : Element = Element(capacity = 30+(ty.cooling*sinks), cooling = (10*ty.engineCooling)+((ty.cooling)*sinks -10))
  
  case class Mech(element:Element,weapons:List[Weapon]) {
    def canFire(weapon:Weapon,now:Time) : Boolean = weapon.canFire(now) && element.hasCapacity(weapon.heat)
    
    def fire(weapon: Weapon,now:Time) : Mech = {
     val updatedWeapons = weapons updated (weapons.indexOf(weapon),weapon.fire(now))
      Mech(element.addHeat(weapon.heat),updatedWeapons)
    }
    
    def cool : Mech = new Mech(element.coolHeat,weapons)
  }
}