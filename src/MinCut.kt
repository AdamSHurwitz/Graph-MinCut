import java.util.*

class MinCut {

    companion object {
        var mergeNode: Int = 1
        var removeNode: Int = 3

        fun minCut(graph: ArrayList<ArrayList<Int>>): Int {
            println("graph: " + graph.toString())
            mergeNode(graph, mergeNode, removeNode)
            mergeNode(graph, 2, 4)
            println("graph: " + graph.toString())
            return graph[0].size - 1
        }

        /*
        merge chosen adjacent node into chosen node to merge
        */
        private fun mergeNode(graph: ArrayList<ArrayList<Int>>, mergeNodeNum: Int, removeNodeNum: Int) {
            //todo: choose random node and adjacent node to removeNode
            var randomNode = Random().nextInt(graph.size)
            var randomAdjacentNode = Random().nextInt(graph[randomNode].size)
            println("graphSize: " + (graph.size - 1) + " randomGraph: " + randomNode
                    + " nodeSize: " + (graph[randomNode].size - 1) + " randomAdjacentNode: " + randomAdjacentNode)
            //todo: set while loop to only run when there are more than 2 nodes

            //todo: run n - 1 times
            //todo: file input

            var mergeNode = getNode(graph, mergeNodeNum)
            var removeNode = getNode(graph, removeNodeNum)
            println("merge: " + mergeNode + " remove: " + removeNode)

            for (i in 1 .. removeNode.second.size - 1) {
                println("merge: " + removeNode.second[i])

                //add edges from chosen removed node into chosen merged node
                if (removeNode.second[i] != mergeNode.second[0]) {
                    mergeNode.second.add(removeNode.second[i])
                }
            }

            //replace occurence of edges for chosen removed node with chosen merged node
            for (g in graph){
                if (g[0] != mergeNodeNum && g.contains(removeNodeNum)) {
                    g[g.indexOf(removeNodeNum)] = mergeNodeNum
                }
            }

            //remove occurence of chosen removed node from chosen merged node
            mergeNode.second.removeAt(mergeNode.second.indexOf(removeNodeNum))

            //remove chosen removed node from graph
            graph.removeAt(removeNode.first)
        }

        //todo: refactor into HashMap
        private fun getNode(graph: ArrayList<ArrayList<Int>>, node: Int): Pair<Int, ArrayList<Int>> {
            for (i in 0 .. graph.size) {
                if (graph[i][0] == node) {
                    return Pair(i , graph[i])
                }
            }
            return Pair(0 , arrayListOf())
        }

    }
}