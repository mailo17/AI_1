import java.util.HashMap;

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
}
