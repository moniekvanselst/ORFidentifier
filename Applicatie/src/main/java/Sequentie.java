
import java.util.ArrayList;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author van Selst
 */
public class Sequentie {
    private int seqID;
    private String seq;
    private ArrayList<ORF> ORFlist;
    
          
    
    public Sequentie(String seq, int seqID){
    this.seq = seq;
    this.seqID = seqID;
    }

    public int getSeqID() {
        return seqID;
    }

    public void setSeqID(int seqID) {
        this.seqID = seqID;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
    
}
