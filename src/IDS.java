import java.util.ArrayList;
import java.util.List;

public class IDS extends GAME{ // Initialize variables
    protected int LIMIT = Integer.MAX_VALUE;
    public Node search() {

        Node startNode = new Node(0,null,this,0);

        return startNode;
    }
    private List<Node> generateChildren(Node parent){
        List<Node> children = new ArrayList<>();
        GAME parentstate = parent.state;
        return children;
    }
    private Node DFS(Node current){
        if (current.state.isGoal()) return current;
        if (current.depth == LIMIT) return null;

        List<Node> children = generateChildren(current);
        for (Node child : children) {
            Node result = DFS(child);
            if (result!=null) return result;
        }
        return null;
    }
}
