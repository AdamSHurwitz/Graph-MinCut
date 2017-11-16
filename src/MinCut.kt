import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MinCut {

    companion object {
        var mergeNode: Int = 1
        var removeNode: Int = 1 //val is 3

        fun minCut(graph: HashMap<Int, ArrayList<Int>>): Int {
            var minCut = Int.MAX_VALUE
            for(i in 1 .. graph.size - 1){
                var result = mergeNode(graph)
                println("RESULT FROM TRIAL " + i + " : " + result)
                minCut = Math.min(result, minCut)
            }
            return minCut
        }

        /*
        merge chosen adjacent node into chosen node to merge
        */
        private fun mergeNode(graph: HashMap<Int, ArrayList<Int>>): Int {
            println("graph: " + graph.toString())
            while(graph.size > 2) {
                var randomNodeNum = getRandomNodeNum(graph) //getRandomNodeNum(Random().nextInt(graph.size + 1))
                println("randomNodeNum: " + randomNodeNum)
                var randomAdjacentNodeNum = Random().nextInt(graph.getValue(randomNodeNum).size)
                println("randomNode: " + randomNodeNum + "|" + graph.getValue(randomNodeNum) + " " + " randomAdjacentNodeNum: " + randomAdjacentNodeNum)

                var mergeNode = graph.getValue(randomNodeNum)
                var removeNodeNum = mergeNode[randomAdjacentNodeNum]

                println("merge: " + mergeNode)
                println("remove: " + removeNodeNum)

                for (i in 0..graph.getValue(removeNodeNum).size - 1) {
                    println("merge: " + graph.getValue(removeNodeNum)[i])

                    //add edges from chosen removed node into chosen merged node
                    if (graph.getValue(removeNodeNum)[i] != randomNodeNum) {
                        mergeNode.add(graph.getValue(removeNodeNum)[i])
                    }
                }

                //replace occurence of edges for chosen removed node with chosen merged node
                for (node in graph) {
                    if (node.key != randomNodeNum && node.value.contains(removeNodeNum)) {
                        node.value[node.value.indexOf(removeNodeNum)] = randomNodeNum
                    }
                }

                //remove occurence of chosen removed node from chosen merged node
                mergeNode.removeAt(mergeNode.indexOf(removeNodeNum))

                //remove chosen removed node from graph
                graph.remove(removeNodeNum)
                println("graph: " + graph.toString())
            }

            return getMinEdgesLeft(graph)
        }

        private fun getRandomNodeNum(graph: HashMap<Int, ArrayList<Int>>): Int {
            var random = Random().nextInt(graph.size + 1)
            if (graph.containsKey(random)){
                return random
            } else {
             return getRandomNodeNum(graph)
            }
        }

        private fun getMinEdgesLeft(graph: HashMap<Int, ArrayList<Int>>): Int {
            var min = Int.MAX_VALUE
            for (l in graph.values) {
                println("l.size: " + l.size)
                min = Math.min(l.size, min)
            }
            return min
        }

    }
}