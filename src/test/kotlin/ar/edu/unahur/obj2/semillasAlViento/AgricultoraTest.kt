package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class AgricultoraTest: DescribeSpec({
    describe("Una agricultora") {
        val parceleSinPlantas = Parcela(50,50,2)
        val agricultora = Agricultora(mutableListOf(parceleSinPlantas))
        it("Sus parcelas semilleras, una parcela es semillera si todas sus plantas dan semillas.") {
            agricultora.parcelasSemilleras().shouldBe(listOf(parceleSinPlantas))
        }
        it("Puede plantar estrategicamente una planta") {
            val plantaMenta = Menta(2021,3.3)
            agricultora.plantarEstrategicamente(plantaMenta)
            parceleSinPlantas.plantas.size.shouldBe(1)
        }
    }
})