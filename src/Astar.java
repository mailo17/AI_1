import java.util.*;

public class Astar extends GAME{
    public int Nodenums;
    public Node Asearch(){
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        //HashSet<String> explored = new HashSet<>();
        Nodenums=0;
        //arxiko Node
        Node tempNode =new Node(0,null,this,0);// temporary node gia na ypologisoyme to heuristic cost
        Node start = new Node(heuristic(tempNode),null,this,0);
        int tempHeuristic=heuristic(tempNode);
        frontier.add(start);

        while (!frontier.isEmpty()) {
            // Get the node with the lowest cost
            Node currentNode = frontier.poll();
            GAME currentState = currentNode.state;

            //System.out.println("Depth: " + currentNode.depth);
            // currentState.PrintBoard();

            // Check if the current state is the goal
            if (currentState.isGoal()) {
                System.out.println("the Starting heuristic cost is "+tempHeuristic);
                System.out.println("Number of total Nodes "+Nodenums);
                //                System.out.println("Goal reached!");
                System.out.println("Cost "+currentNode.cost);
                printSolutionPath(currentNode);
                System.out.println("Solved ");
                return currentNode; // Return the goal node to trace back the path
            }

            // Mark the current board configuration as explored
           //explored.add(Arrays.toString(currentState.getBoard()));

            // Expand nodes by generating valid moves
            for (Node child : generateChildrenAstar(currentNode)) {

                String childBoardStr = Arrays.toString(child.state.getBoard());
               // if (!explored.contains(childBoardStr)) {
                    Nodenums++;
                    frontier.add(child);
               // }
            }
        }
        return null; // Return null if no solution is found
    }
    private List<Node> generateChildrenAstar(Node parent) {
        List<Node> children = new ArrayList<>();
        GAME parentState = parent.state;
        char[] parentBoard = parentState.getBoard();
        int emptyIndex = returnS(parentBoard);

        // Get valid moves for the empty space
        int[] moves = COST(emptyIndex);
        for (int i=0;i<moves.length;i++){
            System.out.print(moves[i]+" ");

        }
        System.out.println();
        parentState.PrintBoard();
        System.out.println();

        for (int move : moves) {
            int newIndex = emptyIndex + move;
            //gia to kainourgio move prosthetoyme to Empty index , me to move wste na bgoyn oi theseies stin opoia borei na paei

            // Clone the parent's state to create a new child state
            GAME childState = new GAME();
            char[] childBoard = parentBoard.clone();
            childState.board = childBoard;

            // Perform the swap for the new state
            swap(childState.board, emptyIndex, newIndex);

            // Calculate the cost for the move
            int moveCost = Math.abs(move);
            int heuristic = heuristic(parent);
            // Create the child node

            Node child = new Node(parent.cost + moveCost + heuristic, parent, childState, parent.depth + 1);
            //System.out.println("the Heuristic cost is "+heuristic);
            children.add(child);
        }

        return children;
    }
    private int heuristic(Node currentNode){
        int heuristicCost =0;
        char[] BoardReference = currentNode.getBoard();
        for(int i=0;i<this.BoardLength();i++){
            if(BoardReference[i]=='W'){
                heuristicCost+=i+1;
            }
        }
       // System.out.println("the Heuristic cost is "+heuristicCost);
        return heuristicCost;
    }

}
