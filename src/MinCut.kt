import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MinCut {

    companion object {

        fun minCut(graph: HashMap<Int, ArrayList<Int>>): Int {
            var minCut = Int.MAX_VALUE
            for (i in 1..graph.size - 1) {
                var result = mergeNode(copyGraph(graph))
                minCut = Math.min(result, minCut)
            }
            return minCut
        }

        /*
        merge chosen adjacent node into chosen node to merge
        */
        private fun mergeNode(graph: HashMap<Int, ArrayList<Int>>): Int {
            while (graph.size > 2) {
                var randomMergeNodeNum = getRandomNodeNum(graph) //getRandomNodeNum(Random().nextInt(graph.size + 1))
                var randomAdjacentNodeNum = Random().nextInt(graph.getValue(randomMergeNodeNum).size)
                var mergeNode = graph.getValue(randomMergeNodeNum)
                var adjacentNode = mergeNode[randomAdjacentNodeNum]

                //add edges from chosen adjacent node into chosen merged node
                for (i in 0..graph.getValue(adjacentNode).size - 1) {
                    if (graph.getValue(adjacentNode)[i] != randomMergeNodeNum) {
                        mergeNode.add(graph.getValue(adjacentNode)[i])
                    }
                }

                //remove occurence of chosen adjacent node from chosen merged node
                while (mergeNode.contains(adjacentNode)) {
                    var removeNodeNumIndex = mergeNode.indexOf(adjacentNode)
                    mergeNode.removeAt(removeNodeNumIndex)
                }

                //replace occurence of edges for chosen adjacent node with chosen merged node
                for (node in graph) {
                    if (node.key != randomMergeNodeNum) {
                        while (node.value.contains(adjacentNode)) {
                            var removeNodeNumIndex = node.value.indexOf(adjacentNode)
                            node.value[removeNodeNumIndex] = randomMergeNodeNum
                        }
                    }
                }

                //remove chosen adjacent node from graph
                graph.remove(adjacentNode)
            }

            return getMinEdgesLeft(graph)
        }

        private fun copyGraph(graph: HashMap<Int, ArrayList<Int>>): HashMap<Int, ArrayList<Int>> {
            var newGraph = HashMap<Int, ArrayList<Int>>()
            for (node in graph) {
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