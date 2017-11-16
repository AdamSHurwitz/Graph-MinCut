import java.io.File
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

object MinCutTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val hashMap = HashMap<Int, ArrayList<Int>>()
        hashMap.put(1, arrayListOf(2, 3))
        hashMap.put(2, arrayListOf(1, 3, 4))
        hashMap.put(3, arrayListOf(1, 2, 4))
        hashMap.put(4, arrayListOf(2, 3))
        test(hashMap)
        //test(arrayListOf<ArrayList<Int>>(arrayListOf(1, 2, 3), arrayListOf(2, 1, 3, 4), arrayListOf(3, 1, 2, 4), arrayListOf(4, 2, 3)))
    }

    //todo: file input search for FIRST SPACE
    fun test(graph: HashMap<Int, ArrayList<Int>>) {
        var hashMap = HashMap<Int, ArrayList<Int>>()
        /* var scanner = Scanner(File("src/kargerMinCut.txt"))
         while(scanner.hasNextLine()){
             print(scanner.nextInt())
         }
         scanner.nextLine()*/



        File("src/kargerMinCut.txt").inputStream().bufferedReader().useLines { lines ->
            lines.forEach {
                var row = ArrayList<Int>()
                var pattern = Pattern.compile("\\s")
                for (i in 0..it.length - 1) {
                    //println("i: " + i)
                    if (i == 0) {
                        row.add(it[i].toString().toInt())
                    } else {
                        var previousMatcher = pattern.matcher(it[i - 1].toString())
                        var currentMatcher = pattern.matcher(it[i].toString())

                        if (!currentMatcher.find()) {
                            //print("FIND: " + it.subSequence(i, it.length - 1).indexOf(" ") + " ")
                            if (previousMatcher.find()) {
                                row.add(it[i].toString().toInt())
                            } else {
                                //println(row)
                                row[row.size - 1] = (row[row.size - 1].toString() + it[i].toString()).toInt()
                            }
                        }

                        //current space is not empty and previous is empty
                        /*if (!currentMatcher.find() && previousMatcher.find()) {
                            row.add(it[i].toString().toInt())
                        }*/

                        //current space is not empty and previous has number
                        /*if (!currentMatcher.find() && !previousMatcher.find()){
                            //print("DIGIT")
                            row[i - 1] = (row[i - 1].toString() + it[i]).toInt()
                        }*/
                         /*else if (!row.isEmpty() && row[row.size - 1] == 0) {
                        row[row.size - 1] = s.toInt()
                    } else {
                        row.add(s.toString().toInt())
                    }*/
                    }
                }
                print(row)
                println()
            }
        }

        /*val lineList = mutableListOf<String>()
        File("src/kargerMinCut.txt").inputStream()
                .bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        println(lineList)*/

        println("The minimum cut of the graph is " + MinCut.minCut(graph))
    }
}