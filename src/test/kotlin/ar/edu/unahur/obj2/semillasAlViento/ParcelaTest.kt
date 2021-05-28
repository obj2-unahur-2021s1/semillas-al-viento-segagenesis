package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.shouldBe

class ParcelaTest : DescribeSpec({
    describe("Una Parcela") {
        it("Su superficie, que se calcula multiplicando ancho por largo"){
            val parcela = Parcela(50,50,2)
            parcela.superficie().shouldBe(2500)
        }
        describe("Cantidad maxima de plantas que tolera") {
            it("si el ancho es mayor que el largo, la cuenta es superficie / 5") {
                val parcela = Parcela(100,50,2)
                parcela.cantidadMaximaPlantas().shouldBe(1000)
            }
            it( "Si no la cuenta es superficie / 3 + largo") {
                val parcela = Parcela(50,100,2)
                parcela.cantidadMaximaPlantas().shouldBe(1766)
            }
        }
        describe( "Si tiene complicaciones"){
            val plantaMenta = Menta(2021,3.3f)
            it("Si alguna de sus plantas tolera menos sol del que recibe la parcela") {
                val parcela = Parcela(50,50,7)
                parcela.plantar(plantaMenta)
                parcela.tieneComplicaciones().shouldBeTrue()
            }
            it("Si alguna de sus plantas tolera mas sol del que recible la parcele") {
                val parcela = Parcela(50,50,2)
                parcela.plantar(plantaMenta)
                parcela.tieneComplicaciones().shouldBeFalse()
            }
        }
        describe("Al Plantar una planta ocurre un error ") {
            it("Si al plantar se supera la cantidad máxima de plantas que tolera") {
                val parcela = Parcela(5,2,2)
                val plantaMenta1 = Menta(2021,3.3f)
                val plantaMenta2 = Menta(2021,3.4f)
                val plantaMenta3 = Menta(2021,3.5f)
                parcela.plantar(plantaMenta1)
                parcela.plantar(plantaMenta2)
                shouldThrowAny { parcela.plantar(plantaMenta3) }
            }
            it("O bien si la parcela recibe al menos 2 horas más de sol que los que la planta tolera.") {
                val parcela = Parcela(50,50,9)
                val plantaMenta = Menta(2021,3.3f)
                shouldThrowAny { parcela.plantar(plantaMenta) }
            }
        }

    }


})