package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()
  // aca pondria plantas.size para que me retorne la cantidad de plantas en la lista mutable
  // Redundancia Minima: este conocimiento no es necesario porque se puede saber preguntandole
  // el tamaño de la lista a la coleccion plantas
  var cantidadPlantas = 0

  fun superficie() = ancho * largo
  fun cantidadMaximaPlantas() =
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo
// para no marear con una cuenta que ya esta en otra funcion pondria directamente this.superficie()
// if (ancho > largo) this.superficie() / 5
// else this.superficie() / 5

// se podria sacar el cantidadPlantas += 1 porque ya el atributo corre con el tamaño de la lista
//RRobustez: es mucho mejor un throw exception que un println porque
//asi informa correctamente al usuario que hay un error
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
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!
    laElegida.plantas.add(planta)
  }
}
