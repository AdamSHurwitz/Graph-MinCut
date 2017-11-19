import java.io.File
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

object MinCutTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val hashMapPrototype = HashMap<Int, ArrayList<Int>>()
        hashMapPrototype.put(1, arrayListOf(2, 3))
        hashMapPrototype.put(2, arrayListOf(1, 3, 4))
        hashMapPrototype.put(3, arrayListOf(1, 2, 4))
        hashMapPrototype.put(4, arrayListOf(2, 3))
        test(hashMapPrototype)

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
            }
        }
        test(hashMap)
    }

    fun test(graph: HashMap<Int, ArrayList<Int>>) {
        println("The minimum cut of the graph is " + MinCut.minCut(graph))
    }
}