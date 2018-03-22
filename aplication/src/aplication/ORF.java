/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication;

/**
 *
 * @author van Selst
 */
public class ORF extends Sequentie {
    int frame;
    int startpositie;
    int eindpositie;
    
    public ORF(String seq, int seqID) {
        super(seq, seqID);
    }

    public ORF(int frame, int startpositie, int eindpositie, String seq, int seqID) {
        super(seq, seqID);
        this.frame = frame;
        this.startpositie = startpositie;
        this.eindpositie = eindpositie;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public int getStartpositie() {
        return startpositie;
    }

    public void setStartpositie(int startpositie) {
        this.startpositie = startpositie;
    }

    public int getEindpositie() {
        return eindpositie;
    }

    public void setEindpositie(int eindpositie) {
        this.eindpositie = eindpositie;
    }
    
    
}
