package utils

class BinaryTree<T: Number>(
    val root: BinaryTreeNode<T>
) {
    companion object {
        fun fromString(tree: String): BinaryTree<Int> {
            val lines = tree.lineSequence().iterator()
            val root = BinaryTreeNode(lines.next().toInt())
            var lastLine = mutableListOf(root)
            while(lines.hasNext()) {
                val line = lines.next()
                val newLine = line.split(" ").map { v -> BinaryTreeNode(v.toInt()) }
                lastLine.forEachIndexed { i, node ->
                    node.left = newLine[i]
                    node.right = newLine[i + 1]
                }
                lastLine = newLine.toMutableList()
            }
            return BinaryTree(root)
        }
    }

    fun postOrderIterator(): Iterator<BinaryTreeNode<T>> {
        return postOrderIterator(root).iterator()
    }

    private fun postOrderIterator(n: BinaryTreeNode<T>): Sequence<BinaryTreeNode<T>> {
        return sequence {
            if (n.left != null) yieldAll(postOrderIterator(n.left!!))
            if (n.right != null) yieldAll(postOrderIterator(n.right!!))
            yield(n)
        }
    }
}

data class BinaryTreeNode<T>(
    val value: T,
    var left: BinaryTreeNode<T>? = null,
    var right: BinaryTreeNode<T>? = null,
) {
    fun isLeaf() = left == null && right == null
}