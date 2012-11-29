package mwo

trait Weapons extends GameDef {
	def gauss = new Weapon(name="gauss",damage = "15", heat = "1", cooldown = "4")
	def ac20 = new Weapon(name="AC/20",damage = "20", heat = "6", cooldown = "2.5")
	
	def mlaser = new Weapon(name="Medium Laser",damage = "5", heat = "4", cooldown = "3",beam = "1")
	def llaser = new Weapon(name="Large Laser",damage = "9", heat = "7", cooldown = "3.25",beam = "1")
	def mplaser = new Weapon(name="Medium Pulse Laser",damage = "6", heat = "3", cooldown = "3",beam = "0.75")
	def lplaser = new Weapon(name="Large Pulse Laser",damage = "10", heat = "9", cooldown = "3.25",beam = "0.75")
	def ppc = new Weapon(name="PPC",damage = "10", heat = "9", cooldown = "3")
	def erppc = new Weapon(name="ER PPC",damage = "10", heat = "13", cooldown = "3")
	
	def ssrm2 = new Weapon(name="Streak SRM2",damage = "5", heat = "2", cooldown = "3.5")
	def srm2 = new Weapon(name="SRM2",damage = "5", heat = "2", cooldown = "3.5")
	def srm4 = new Weapon(name="SRM4",damage = "10", heat = "3", cooldown = "3.75")
	def srm6 = new Weapon(name="SRM6",damage = "15", heat = "4", cooldown = "4")
}