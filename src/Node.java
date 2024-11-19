import jdk.jfr.DataAmount;

import java.util.PriorityQueue;


class Node {
    public int cost;
    public Node parent;
    public GAME state;
    public int depth;



    public Node(int cost,Node parent,GAME state,int depth){
        this.cost=cost;
        this.parent=parent;
        this.state=state;
        this.depth=depth;


    }
    public void steNextNode(Node node){
        this.parent=node;
    }
    public Node getNextNode(Node node){
        this.parent=node;
        return null;
    }
    public char[] getBoard() {
        return state.getBoard(); // Calls getBoard() from GAME to get a clone of the board array
    }
    public Node getParentNode( ){
        return this.parent;
    }




}
