package orfidentifier;


/**
 *
 * @author Moniek van Selst, Willem Korsten, Nicky van Bergen
 */
public class ORF {
    int frame;
    int startpositie;
    int eindpositie;
    String seqorf;

    public ORF(int frame, int startpositie, int eindpositie, String seqorf) {
        this.frame = frame;
        this.startpositie = startpositie;
        this.eindpositie = eindpositie;
        this.seqorf = seqorf;
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

    public String getSeqorf() {
        return seqorf;
    }

    public void setSeqorf(String seqorf) {
        this.seqorf = seqorf;
    }

    
    
    
}
