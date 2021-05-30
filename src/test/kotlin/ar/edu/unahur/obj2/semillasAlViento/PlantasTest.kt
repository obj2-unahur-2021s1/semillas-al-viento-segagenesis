package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe


class PlantasTest: DescribeSpec({
  describe("Menta") {
    val mentita = Menta(2010,0.6)
    it("Da semilla"){
      mentita.daSemillas().shouldBeTrue()
    }
    it("Es Fuerte"){
      mentita.esFuerte().shouldBeFalse()
    }

  }
  describe("Soja"){
    val sojaNormal = Soja(2010,2.0)
    it("Da semilla"){
      sojaNormal.daSemillas().shouldBeTrue()
    }
    it("Es Fuerte"){
      sojaNormal.esFuerte().shouldBeFalse()
    }

  }
  describe("Soja Transgenica"){
    val sojaTrans = SojaTransgenica(2010,2.0)
    it("Da semilla"){
      sojaTrans.daSemillas().shouldBeFalse()
    }
    it("Es Fuerte"){
      sojaTrans.esFuerte().shouldBeTrue()
    }

  }
})
