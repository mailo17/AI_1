import java.util.*;

public class UCS extends GAME {
    public Node search() {
        // Priority Queue to hold nodes based on their cost (low cost has high priority)
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        HashSet<String> explored = new HashSet<>();

        // Initial node with cost 0, depth 0, and no parent
        Node startNode = new Node(0, null, this, 0);
        frontier.add(startNode);

        while (!frontier.isEmpty()) {
            // Get the node with the lowest cost
            Node currentNode = frontier.poll();
            GAME currentState = currentNode.state;

            //System.out.println("Depth: " + currentNode.depth);
           // currentState.PrintBoard();

            // Check if the current state is the goal
            if (currentState.isGoal()) {
    //                System.out.println("Goal reached!");
                System.out.println("Cost "+currentNode.cost);
                printSolutionPath(currentNode);
                System.out.println("Solved ");
                return currentNode; // Return the goal node to trace back the path
            }

            // Mark the current board configuration as explored
            explored.add(Arrays.toString(currentState.getBoard()));

            // Expand nodes by generating valid moves
            for (Node child : generateChildren(currentNode)) {
                String childBoardStr = Arrays.toString(child.state.getBoard());
                if (!explored.contains(childBoardStr)) {
                    frontier.add(child);
                }
            }
        }
        return null; // Return null if no solution is found
    }

    private List<Node> generateChildren(Node parent) {
        List<Node> children = new ArrayList<>();
        GAME parentState = parent.state;
        char[] parentBoard = parentState.getBoard();
        int emptyIndex = returnS(parentBoard);

        // Get valid moves for the empty space
        int[] moves = COST(emptyIndex);
        for (int i=0;i<moves.length;i++){
            System.out.print(moves[i]+" ");
            //System.out.println()

           // System.out.println("Cost: " + Math.abs(moves[i]));

        }
        System.out.println();
        parentState.PrintBoard();
        System.out.println();

        for (int move : moves) {
            int newIndex = emptyIndex + move;

            // Clone the parent's state to create a new child state
            GAME childState = new GAME();
            char[] childBoard = parentBoard.clone();
            childState.board = childBoard;

            // Perform the swap for the new state
            swap(childState.board, emptyIndex, newIndex);

            // Calculate the cost for the move
            int moveCost = Math.abs(move);

            // Create the child node
            Node child = new Node(parent.cost + moveCost, parent, childState, parent.depth + 1);
            children.add(child);
        }

        return children;
    }
    public int[] COST(int position){ //prepei na epsitrefei ton pinaka ton pithanon kinhsewn gia th nantistoixh thesh
                         // meta to costos einai h metakinhsh se apolyth timh giati  borei na metainhthei -3 -2 -1 1 1 2 3 thewrtika
        List<Integer> validMoves = new ArrayList<>();
        char[] boardClone = this.getBoardClone();

        for (int k = 3; k >= 1; k--) {
            if (position - k >= 0) {  // Ensure the move stays within bounds to the left
                validMoves.add(-k);  // Add the left-side move
            }
        }

        // Generate valid moves to the right (+1, +2, +3)
        for (int k = 1; k <= 3; k++) {
            if (position + k < boardClone.length) { // Ensure the move stays within bounds
                validMoves.add(k);
            }
        }
        int[] costs = new int[validMoves.size()];
        for ( int i = 0; i < validMoves.size(); i++) {
            costs[i] = validMoves.get(i);
        }
        /*for( int i=0 ;i < costs.length;i++){
            System.out.print("Cost"+costs[i]+" ");
        }*/
        return costs;
    }
    private void printSolutionPath(Node goalNode) {
        List<Node> path = new ArrayList<>();
        Node current = goalNode;

        // Trace back from goal to start
        while (current != null) {
            path.add(current);
            current = current.parent;
        }

        // Print the path in reverse (start to goal)
        Collections.reverse(path);
        System.out.println("Solution Path:");
        for (Node node : path) {
            node.state.PrintBoard();
        }
    }
}
