import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MinCut {

    companion object {

        fun minCut(graph: HashMap<Int, ArrayList<Int>>): Int {
            var minCut = Int.MAX_VALUE
            for(i in 1 .. graph.size - 1){
                var result = mergeNode(copyGraph(graph))
                minCut = Math.min(result, minCut)
            }
            return minCut
        }

        /*
        merge chosen adjacent node into chosen node to merge
        */
        private fun mergeNode(graph: HashMap<Int, ArrayList<Int>>): Int {
            while(graph.size > 2) {
                var randomNodeNum = getRandomNodeNum(graph) //getRandomNodeNum(Random().nextInt(graph.size + 1))
                var randomAdjacentNodeNum = Random().nextInt(graph.getValue(randomNodeNum).size)
                var mergeNode = graph.getValue(randomNodeNum)
                var removeNodeNum = mergeNode[randomAdjacentNodeNum]

                for (i in 0..graph.getValue(removeNodeNum).size - 1) {

                    //add edges from chosen removed node into chosen merged node
                    if (graph.getValue(removeNodeNum)[i] != randomNodeNum) {
                        mergeNode.add(graph.getValue(removeNodeNum)[i])
                    }
                }

                //replace occurence of edges for chosen removed node with chosen merged node
                for (node in graph) {
                    while(node.key != randomNodeNum && node.value.contains(removeNodeNum)){
                        var removeNodeNumIndex = node.value.indexOf(removeNodeNum)
                        node.value[removeNodeNumIndex] = randomNodeNum
                    }
                }

                //remove occurence of chosen removed node from chosen merged node
                while(mergeNode.contains(removeNodeNum)){
                    var removeNodeNumIndex = mergeNode.indexOf(removeNodeNum)
                    mergeNode.removeAt(removeNodeNumIndex)
                }

                //remove chosen removed node from graph
                graph.remove(removeNodeNum)
                println("graph: " + graph)
            }

            return getMinEdgesLeft(graph)
        }

        private fun copyGraph(graph: HashMap<Int, ArrayList<Int>>): HashMap<Int, ArrayList<Int>> {
            var newGraph = HashMap<Int, ArrayList<Int>>()
            for (node in graph){
                newGraph.put(node.key, ArrayList(node.value))
            }
            return newGraph
        }

        private fun getRandomNodeNum(graph: HashMap<Int, ArrayList<Int>>): Int {
            var keyArray = graph.keys.toIntArray()
            var randomIndex = Random().nextInt(keyArray.size)
            return keyArray[randomIndex]
        }

        private fun getMinEdgesLeft(graph: HashMap<Int, ArrayList<Int>>): Int {
            var min = Int.MAX_VALUE
            for (l in graph.values) {
                min = Math.min(l.size, min)
            }
            return min
        }

    }
}