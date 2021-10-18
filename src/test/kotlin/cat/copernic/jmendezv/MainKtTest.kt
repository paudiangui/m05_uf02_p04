package cat.copernic.jmendezv

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.time.Duration
import java.util.stream.Stream
import kotlin.test.assertFailsWith

internal class MainKtTest {
    companion object {

        @JvmStatic
        @BeforeAll
        fun start() {
            println("*** Inicio tests... ")

        }

        @JvmStatic
        @AfterAll
        fun end() {
            println(" Final tests... ***")

        }

        @JvmStatic
        fun provideParametersForIMC(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(10.0, 2.0, 2.5),
                Arguments.of(20.0, 2.0, 5.0),
                Arguments.of(40.0, 2.0, 10.0),
                Arguments.of(80.0, 2.0, 20.0),
            )
        }
    }

    @BeforeEach
    fun setUp() {
        println("*** Inicio test... ")
    }

    @AfterEach
    fun tearDown() {
        println(" Final test... ***")
    }

    @DisplayName("Indice Masa Corporal")
    @ParameterizedTest
    @MethodSource("provideParametersForIMC")
    fun IMCTest(a: Double, b: Double, c: Double){
        Assertions.assertEquals(c, cat.copernic.jmendezv.IMC(a, b), 1.0e-3)
        println("test Okey")
    }

    @DisplayName("Indice Masa Corporal 2")
    @ParameterizedTest
    @ValueSource(doubles = [10.0])
    fun IMCTest2(a: Double){
        Assertions.assertEquals(2.5, cat.copernic.jmendezv.IMC(a, 2.0), 1.0e-3)
        println("test Okey")
    }

    @DisplayName("Equacion segundo grado")
    @ParameterizedTest
    @CsvSource("1.0,-5.0,6.0,3.0,2.0", "1,4,-21,3,-7")
    fun equSegundoGradoTest(a: Double, b: Double, c: Double, d: Double, e:Double){
        Assertions.assertEquals(Pair(d, e), cat.copernic.jmendezv.equSegundoGrado(a, b, c))
    }

    @DisplayName("Distacina entre dos puntos")
    @Test
    fun distanciaEntre2PuntosTest(){
        assertEquals(1.41, distanciaEntre2Puntos(Punto(1.0,2.0), Punto(2.0,3.0)),0.01)
    }

    @DisplayName("Pendiente de una recta")
    @Test
    fun PendienteTest(){
        assertEquals(0.33, Pendiente(Punto(2.0,1.0), Punto(5.0, 2.0)), 0.01)
    }

    @DisplayName("Punto medio de dos puntos TimeOut")
    @Test
    fun puntoMedioTest(){
        val result =
            org.junit.jupiter.api.assertTimeout(
                Duration.ofMillis(30)) {
                puntoMedio(Punto(3.0,9.0), Punto(7.0,2.0))
            }
        assertEquals(Punto(5.0,5.5), result)


    }

    @DisplayName("Calificacion de nota ")
    @Test
    fun calificacionGradoTest(){
        assertEquals("Excel·lent", calificacion(9.0))
    }

    //[2,3,1,4,7,6,5] = (1,7)
    //* [] = IllegalArgumentException

    @DisplayName("Numero minimo y maximo")
    @Test
    fun maxMinTest(){
        assertEquals(Pair(1,7),maxMin(listOf(2,3,1,4,7,6,5)))

    }

    @DisplayName("Punto mas cercano")
    @Test
    fun masCercanoTest(){
        assertEquals(Punto(3.0,7.0), masCercano(Punto(2.0,1.0),Punto(3.0,7.0),Punto(5.0,3.0),Punto(7.0,5.0)))
    }

}