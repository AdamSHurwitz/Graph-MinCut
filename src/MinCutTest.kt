import java.io.File
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

object MinCutTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val hashMap = HashMap<Int, ArrayList<Int>>()
        hashMap.put(1, arrayListOf(2, 3))
        hashMap.put(2, arrayListOf(1, 3, 4))
        hashMap.put(3, arrayListOf(1, 2, 4))
        hashMap.put(4, arrayListOf(2, 3))
        test(hashMap)
    }

    //todo: file input search for FIRST SPACE
    fun test(graph: HashMap<Int, ArrayList<Int>>) {
        var hashMap = HashMap<Int, ArrayList<Int>>()

        File("src/kargerMinCut.txt").inputStream().bufferedReader().useLines { lines ->
            lines.forEach {
                var row = ArrayList<Int>()
                var pattern = Pattern.compile("\\s")
                for (i in 0..it.length - 1) {
                    if (i == 0) {
                        row.add(it[i].toString().toInt())
                    } else {
                        var previousMatcher = pattern.matcher(it[i - 1].toString())
                        var currentMatcher = pattern.matcher(it[i].toString())

                        if (!currentMatcher.find()) {
                            if (previousMatcher.find()) {
                                row.add(it[i].toString().toInt())
                            } else {
                                row[row.size - 1] = (row[row.size - 1].toString() + it[i].toString()).toInt()
                            }
                        }
                    }
                }
                hashMap.put(row[0], ArrayList(row.subList(1, row.size)))
                print(hashMap)
                println()
            }
        }

        //println("The minimum cut of the graph is " + MinCut.minCut(hashMap))
        println("The minimum cut of the graph is " + MinCut.minCut(graph))
    }
}