import com.soywiz.kds.Queue
import com.soywiz.kds.Stack

class Graph(knoten : List<Knoten>, val kanten : List<Kante>) {

    val adjazenzListe = hashMapOf<Knoten, List<Knoten>>()

    init {
        knoten.forEach { aktuellerKnoten ->
            val alleZiele = kanten.filter { it.start == aktuellerKnoten }.map { it.end }
            val alleStarts = kanten.filter { it.end == aktuellerKnoten }.map { it.start }
            adjazenzListe.put(aktuellerKnoten, alleStarts+alleZiele)
        }
    }
    fun sucheKnoten(knoten : Knoten) : Boolean  = this.adjazenzListe.contains(knoten)

    fun sucheKnoten(knoten : String) : Boolean  = this.adjazenzListe.filter { it.key.inhalt.ortsname == knoten }.isNotEmpty()

    fun gibNachbarnFuer(knoten : Knoten) : List<Knoten>? = adjazenzListe[knoten]

    fun gibNachbarnFuer(knoten : String) : List<Knoten> = adjazenzListe.filter { it.key.inhalt.ortsname == knoten }.values.flatten()

    fun gibEntfernungZwischen(start : Knoten, ziel: Knoten) : Int {
        var entfernung = Int.MAX_VALUE
        kanten.forEach {
            if (it.start == start && it.end == ziel || it.start == ziel && it.end == start) {
                entfernung = it.gewicht
            }
        }
        return entfernung
    }

    fun gibEntfernungZwischen(start : String, ziel: String) : Int {
        var entfernung = Int.MAX_VALUE
        kanten.forEach {
            if (it.start.inhalt.ortsname == start && it.end.inhalt.ortsname == ziel || it.start.inhalt.ortsname == ziel && it.end.inhalt.ortsname == start) {
                entfernung = it.gewicht
            }
        }
        return entfernung
    }

    fun breitenSuche(start: Knoten, gesuchterKnoten: Knoten) : String{
        var returnString = ""
        var schlange = Queue<Knoten>(start)
        while (schlange.isNotEmpty()){
            val aktuellerKnoten = schlange.dequeue()
            if (!aktuellerKnoten.besucht){
                aktuellerKnoten.besucht = true

                if (aktuellerKnoten.inhalt.ortsname == gesuchterKnoten.inhalt.ortsname){
                    returnString+="==>${aktuellerKnoten.inhalt.ortsname}"
                    break
                }
                gibNachbarnFuer(aktuellerKnoten)?.forEach {
                    schlange.enqueue(it)
                }
                returnString+="-->${aktuellerKnoten.inhalt.ortsname}"
            }
            }

        return returnString
    }

    fun tiefenSuche(start: Knoten, gesuchterKnoten: Knoten) : String{
        var returnString = ""
        var schlange = Stack<Knoten>(start)
        while (schlange.isNotEmpty()){
            val aktuellerKnoten = schlange.pop()
            if (!aktuellerKnoten.besucht){
                aktuellerKnoten.besucht = true

                if (aktuellerKnoten.inhalt.ortsname == gesuchterKnoten.inhalt.ortsname){
                    returnString+="==>${aktuellerKnoten.inhalt.ortsname}"
                    break
                }
                gibNachbarnFuer(aktuellerKnoten)?.forEach {
                    schlange.push(it)
                }
                returnString+="-->${aktuellerKnoten.inhalt.ortsname}"
            }
        }

        return returnString
    }


}