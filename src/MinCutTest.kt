object MinCutTest{
    @JvmStatic
    fun main(args: Array<String>){
        test(arrayListOf<ArrayList<Int>>(arrayListOf(1, 2, 3), arrayListOf(2, 1, 3, 4), arrayListOf(3, 1, 2, 4), arrayListOf(4, 2, 3)))
    }

    fun test(graph: ArrayList<ArrayList<Int>>){
        println("The minimum cut of the graph is " + MinCut.minCut(graph))
    }
}