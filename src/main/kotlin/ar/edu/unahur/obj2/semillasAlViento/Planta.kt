package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, var altura: Double) {
  fun esFuerte() = this.horasDeSolQueTolera() > 10

  abstract fun horasDeSolQueTolera(): Int
  abstract fun daSemillas(): Boolean
}

class Menta(anioObtencionSemilla: Int, altura: Double) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = this.esFuerte() || altura > 0.4
}

open class Soja(anioObtencionSemilla: Int, altura: Double) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera(): Int {
    return when {
      altura < 0.5 -> 6
      altura < 1 -> 7
      else -> 9
    }
  }

  override fun daSemillas(): Boolean  {
    return this.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }
}

class SojaTransgenica(anioObtencionSemilla: Int,altura: Double): Soja(anioObtencionSemilla,altura){
  override fun horasDeSolQueTolera(): Int  {
    val horasBase = when {
      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }
    return horasBase * 2
  }
  override fun daSemillas(): Boolean  {
     return false
  }
}
