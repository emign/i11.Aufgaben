class Knoten (val inhalt : Datenelement, var besucht : Boolean = false){
    constructor(ort : String) : this(Datenelement(ort, 0,""))
}