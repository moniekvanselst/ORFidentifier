package orfidentifier;


import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

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
    static public ArrayList<ORF> ORFlist;
    static public HashMap<Integer, String> seqframeMap;

    public Sequentie(String seq, int seqID) {
        System.out.println(seq);
        if (seq.matches("^[ATCGN]+$")) {
            System.out.println("goed");
            this.seq = seq;
            this.seqID = seqID;
            //System.out.println("seq gemaakt");
            new GUIopen().waitLabel.setText("is aan het openen..");
            new Logica().makeFrames(this.seq);
            new GUIopen().waitLabel.setText("is aan het openen....");
            //System.out.println("frames gemaakt");
        } else {
            System.out.println("fout");
            JOptionPane.showMessageDialog(null, "De gegeven sequentie bestaat NIET uit DNA", "Inane error", JOptionPane.ERROR_MESSAGE);

        }
        
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
