import com.soywiz.korge.tests.*

import kotlin.test.*

class GraphenTest : KorgeTest() {

    val greifenberg = Knoten ("Greifenberg")
    val stegen = Knoten ("Stegen")
    val schondorf = Knoten ("Schondorf")
    val herrsching = Knoten ("Herrsching")
    val utting = Knoten ("Utting")
    val andechs = Knoten ("Andechs")
    val diessen = Knoten ("Diessen")
    val fischen = Knoten ("Fischen")

    val knoten = listOf(greifenberg, stegen, schondorf, herrsching, utting, andechs, diessen, fischen)

    val greifenBergStegen = Kante(greifenberg, stegen, 7)
    val schondorfGreifenberg = Kante(schondorf, greifenberg, 5)
    val uttingSchondorf = Kante(utting, schondorf, 5)
    val uttingHerrsching = Kante(utting, herrsching, 31)
    val diessenUtting = Kante(diessen, utting, 11)
    val diessenFischen = Kante(diessen, fischen, 7)
    val fischenAndechs = Kante(fischen, andechs, 10)
    val andechsHerrsching = Kante(andechs, herrsching, 9)
    val herrschingStegen = Kante(herrsching, stegen, 15)
    val diessenStegen = Kante(diessen, stegen, 107)
    val diessenHerrsching = Kante(diessen, herrsching, 39)

    val kanten = listOf(greifenBergStegen, schondorfGreifenberg, uttingHerrsching, uttingSchondorf, diessenFischen, diessenUtting, fischenAndechs, andechsHerrsching, herrschingStegen, diessenStegen, diessenHerrsching)

    val ammerseeRundfahrt = Graph(knoten, kanten)

    @Test
    fun breitenSuche() {
        val gesuchterKnoten = fischen
        val startKnoten = greifenberg
        val bfsErgebnis = ammerseeRundfahrt.breitenSuche(startKnoten, gesuchterKnoten)
        assertEquals("-->Greifenberg-->Schondorf-->Stegen-->Utting-->Herrsching-->Diessen-->Andechs==>Fischen",bfsErgebnis )
    }

    @Test
    fun tiefenSuche() {
        val gesuchterKnoten = fischen
        val startKnoten = greifenberg
        val tfsErgebnis = ammerseeRundfahrt.tiefenSuche(startKnoten, gesuchterKnoten)

        assertEquals("-->Greifenberg-->Stegen-->Diessen-->Herrsching-->Andechs==>Fischen",tfsErgebnis )
    }


}