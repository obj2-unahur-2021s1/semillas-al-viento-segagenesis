package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()

  fun superficie() = ancho * largo

  fun cantidadMaximaPlantas() =
    if (ancho > largo) this.superficie() / 5 else this.superficie() / 3 + largo

  fun plantar(planta: Planta) {
    if (this.cantidadPlantas() == this.cantidadMaximaPlantas()) {
      throw Exception("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      throw java.lang.Exception("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
    }
  }

  fun cantidadPlantas() = this.plantas.size

  fun tieneComplicaciones() = this.plantas.any { it.horasDeSolQueTolera() < this.horasSolPorDia }

  fun esSemillera() = this.plantas.all { it.daSemillas() }
}


class Agricultora(val parcelas: List<Parcela>) {

  fun parcelasSemilleras() = this.parcelas.filter { it.esSemillera() }

  fun parcelaEstrategica() = this.parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas() }!!

  fun plantarEstrategicamente(planta: Planta) {
    this.parcelaEstrategica().plantar(planta)
  }
}
