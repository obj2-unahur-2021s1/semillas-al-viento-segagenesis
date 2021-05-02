package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()
  var cantidadPlantas = 0 // aca pondria plantas.size para que me retorne la cantidad de plantas en la lista mutable

  fun superficie() = ancho * largo
  fun cantidadMaximaPlantas() =
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo
// para no mariar con una cuenta que ya esta en otra funcion pondria directamente this.superficie()
// if (ancho > largo) this.superficie() / 5
// else this.superficie() / 5
// aca ale se puede crear una funcion para modificar la superficie con el coeficiente


// fun parcelaTieneComplicaciones(parcela: Parcela) =
//    parcela.plantas.any { it.horasDeSolQueTolera() < parcela.horasSolPorDia }


  fun plantar(planta: Planta) {
    if (cantidadPlantas == this.cantidadMaximaPlantas()) {
      println("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      println("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
      cantidadPlantas += 1
    }
  }
}
// en plantar() se podria sacar el cantidadPlantas += 1 porque ya el atributo corre con el tamaño de la lista


// agricultora se podria poner en otro archivo aparte de parcela para no mezclar, asi como plantas esta separado de parcela
class Agricultora(val parcelas: MutableList<Parcela>) {
  var ahorrosEnPesos = 20000

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
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!
    laElegida.plantas.add(planta)
  }
}
