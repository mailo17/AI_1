import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public  class GAME {
    public char[] board;
    private HashMap<Character,Integer> map =new HashMap<>(); //HASHMAP gia ton elegxo toy goal
    public GAME(){
        this.board = new char[7];
        if(board.length != 7){
            throw new IllegalArgumentException("Invalid board");
        }
        else {
            for( int i=0;i<7;i++){
                if(i<3){
                    this.board[i]='B'; //BLACK
                }
                if(i>=3 && i<6){
                    this.board[i]='W'; //WHITE
                }
                if(i==6){
                    this.board[i]='S'; //keno
                }
                map.put('B',-10);
                map.put('W',10);
                map.put('S',0);
            }
        }

    }

    public char[] getBoardClone(){
        return this.board.clone(); //epistrefei clone gia thn periprwths poy den theloyme modifications

    }
    public char[] getBoard(){
        return this.board;
    }
    public int BoardLength(){
        return board.length;
    }
    public boolean isGoal(){

        int sum=0;
        for(int i=0;i<7;i++){
            sum += map.get(board[i]);
            if(sum==30){
                return true;
            }
        }
        return false;
    }
    public int returnS(char [] board){
        for (int i=0;i<board.length;i++){
            if(board[i]=='S'){
                return i;
            }
        }
        return -1;
    }
    public void swap(char[] board, int i,int j){
        char temp= board[i];
        board[i] = board[j];
        board[j] = temp;
    }
    public void PrintBoard(){
        for(int i=0;i<board.length;i++){
            System.out.print(board[i]+" ");
        }
        System.out.println();
    }
    public void printSolutionPath(Node goalNode) {
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
}
