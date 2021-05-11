package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, var altura: Float) {
  fun esFuerte() = this.horasDeSolQueTolera() > 10

  // Cohesion: aunque tenga algo que ver con la clase planta esta funcion
  // no esta relacionada con la misma sino con la clase Parcela
  // esta funcion iria en la clase Parcela , en la consigna se pide consultarle al objeto Parcela no al objeto Planta
  fun parcelaTieneComplicaciones(parcela: Parcela) =
    parcela.plantas.any { it.horasDeSolQueTolera() < parcela.horasSolPorDia }

  abstract fun horasDeSolQueTolera(): Int
  abstract fun daSemillas(): Boolean
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = this.esFuerte() || altura > 0.4
}

class Soja(anioObtencionSemilla: Int, altura: Float, val esTransgenica: Boolean) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera(): Int  {
    // ¡Magia de Kotlin! El `when` es como un `if` pero más poderoso:
    // evalúa cada línea en orden y devuelve lo que está después de la flecha.
    val horasBase = when {
      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }
    return if (esTransgenica) horasBase * 2 else horasBase
  }

  override fun daSemillas(): Boolean  {
    if (this.esTransgenica) {
      return false
    }
    return this.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }
}

// la soja transgenica la pondria en una subclase de soja, poniendo open en soja y generando una nueva clase
// Desacoplamiento : para evitar complejidades se deberia he
class SojaTransgenica(anioObtencionSemilla: Int, altura: Float, esTransgenica: Boolean = true): Soja(anioObtencionSemilla, altura, esTransgenica) {
  override fun daSemillas() = false
  override fun horasDeSolQueTolera(): Int {
    return super.horasDeSolQueTolera() * 2
  }
}