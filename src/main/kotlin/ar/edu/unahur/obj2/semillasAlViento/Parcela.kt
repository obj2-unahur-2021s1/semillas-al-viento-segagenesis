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
}


// agricultora se podria poner en otro archivo aparte de parcela para no mezclar, asi como plantas esta separado de parcela
// Mutacion controlada: En el enunciado nos aclara que las
// parcelas ya tienen que estar definidas por lo que tendria que ser una lista inmutable.
class Agricultora(val parcelas: MutableList<Parcela>) {
  var ahorrosEnPesos = 20000
  // Simplicidad YAGNI: No hace falta agregar funcionalidades que no nos ayuden
  // a resolver la problematica actual.
  // Suponemos que una parcela vale 5000 pesos
  fun comprarParcela(parcela: Parcela) {
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  }

  fun parcelasSemilleras() =
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }

  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas() }!!
    laElegida.plantas.add(planta)
  }
}
