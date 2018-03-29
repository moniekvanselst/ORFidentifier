package orfidentifier;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;

/**
 *
 * @author Moniek van Selst, Willem Korsten, Nicky van Bergen
 */
public class Sequentie {
    static public int seqID;
    private String seq;
    public String bestand;
    public String keuze;
    public String date;
    public String name;
    public String organism;
    static public ArrayList<ORF> ORFlist = new ArrayList<ORF>();
    static public HashMap<Integer, String> seqframeMap;

    public Sequentie(String seq, int seqID, String bestand) throws CompoundNotFoundException, IOException {
        
        if (seq.matches("^[ATCGN]+$")) {
            
            this.seq = seq;
            this.seqID = seqID;
            this.bestand = bestand;
            this.keuze = GUIopen.CodonDropDown.getSelectedItem().toString();
            this.date = GUIopen.DateTextField.getText();
            this.name = GUIopen.NameTextField.getText();
            this.organism = GUIopen.OrganismTextField.getText();
            new Logica().SEQopslaan(seqID, bestand, keuze, date, name, organism);
            new Logica().makeFrames(seq);
            
        } else {
            
            JOptionPane.showMessageDialog(null, "De gegeven sequentie bestaat NIET uit DNA", "Inane error", JOptionPane.ERROR_MESSAGE);

        }
        
    }

    static public void addORF(ORF orf){
        ORFlist.add(orf);
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
