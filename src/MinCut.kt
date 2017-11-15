import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MinCut {

    companion object {
        var mergeNode: Int = 1
        var removeNode: Int = 1 //val is 3

        fun minCut(graph: HashMap<Int, ArrayList<Int>>) {
        //fun minCut(graph: HashMap<Int, ArrayList<Int>>): Int {
            println("graph: " + graph.toString())
            mergeNode(graph, mergeNode, removeNode)
            //mergeNode(graph, 2, 4)
            println("graph: " + graph.toString())
            //return graph[0].size - 1
        }

        /*
        merge chosen adjacent node into chosen node to merge
        */
        private fun mergeNode(graph: HashMap<Int, ArrayList<Int>>, mergeNodeNum: Int, adjacentNodeNum: Int) {
            //todo: choose random node and adjacent node to removeNode
            /*var randomNode = Random().nextInt(graph.size)
            var randomAdjacentNode = Random().nextInt(graph[randomNode].size)
            println("graphSize: " + (graph.size - 1) + " randomGraph: " + randomNode
                    + " nodeSize: " + (graph[randomNode].size - 1) + " randomAdjacentNode: " + randomAdjacentNode)*/
            //todo: set while loop to only run when there are more than 2 nodes

            //todo: run n - 1 times
            //todo: file input

            var mergeNode = graph.getValue(mergeNodeNum)
            var removeNodeNum = mergeNode[adjacentNodeNum]

            println("merge: " + mergeNode + " remove: " + removeNodeNum)

            for (i in 0 .. graph.getValue(removeNodeNum).size - 1) {
                println("merge: " + graph.getValue(removeNodeNum)[i])

                //add edges from chosen removed node into chosen merged node
                if (graph.getValue(removeNodeNum)[i] != mergeNodeNum) {
                    mergeNode.add(graph.getValue(removeNodeNum)[i])
                }
            }

            //replace occurence of edges for chosen removed node with chosen merged node
            for (node in graph){
                if (node.key != mergeNodeNum && node.value.contains(removeNodeNum)) {

                    node.value[node.value.indexOf(removeNodeNum)] = mergeNodeNum
                }
            }

            //remove occurence of chosen removed node from chosen merged node
            mergeNode.removeAt(mergeNode.indexOf(removeNodeNum))

            //remove chosen removed node from graph
            graph.remove(removeNodeNum)
        }

    }
}