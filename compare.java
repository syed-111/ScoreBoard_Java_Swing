import java.util.Comparator;

class compare implements Comparator<Pair> {

    public int compare(Pair a, Pair b){

        if( a.y < b.y ){
            return 1;
        }else if( a.y > b.y ){
            return -1;
        }else{
            return 0;
        }

    }
}