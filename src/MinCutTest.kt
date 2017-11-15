object MinCutTest{
    @JvmStatic
    fun main(args: Array<String>){
        val hashMap = HashMap<Int, ArrayList<Int>>()
        hashMap.put(1, arrayListOf(2, 3))
        hashMap.put(2, arrayListOf(1, 3, 4))
        hashMap.put(3, arrayListOf(1, 2, 4))
        hashMap.put(4, arrayListOf(2, 3))
        test(hashMap)
        //test(arrayListOf<ArrayList<Int>>(arrayListOf(1, 2, 3), arrayListOf(2, 1, 3, 4), arrayListOf(3, 1, 2, 4), arrayListOf(4, 2, 3)))
    }
    //todo: run n - 1 times
    //todo: file input
    fun test(graph: HashMap<Int, ArrayList<Int>>){
        println("The minimum cut of the graph is " + MinCut.minCut(graph))
    }
}