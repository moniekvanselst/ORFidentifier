package orfidentifier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author Moniek van Selst, Willem Korsten, Nicky van Bergen
 */
public class ORF {
    static int ORFid =0;
    int frame;
    int startpositie;
    int eindpositie;
    String seqorf;
    int seqID;
    public ArrayList<Object[]> table = new ArrayList<Object[]>();

    public ORF(int frame, int startpositie, int eindpositie, String seqorf, int seqID) {
        this.frame = frame;
        this.startpositie = startpositie;
        this.eindpositie = eindpositie;
        this.seqorf = seqorf;
        this.seqID = seqID;
        ORFid = getORFid();
        ORFid++;
        new Logica().ORFopslaan(ORFid, startpositie, eindpositie, seqID, frame);
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
    public int getORFid(){
    try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:derby://localhost:1527/ORF Identifier DB", "owe7pg6", "bks");
                Statement stmt = conn.createStatement();) {

            //String selectLastRecord = "SELECT * FROM OWE7PG6.\"sequentie\" ORDER BY \"seq_id\" DESC";
            //String selectLastRecord = "SELECT TOP 1 * FROM OWE7PG6.\"sequentie\" ORDER BY \"seq_id\" DESC";
            String selectLastRecord = "SELECT * FROM OWE7PG6.\"ORF's\" WHERE \"orf_id\" = (SELECT MAX(\"orf_id\") FROM OWE7PG6.\"ORF's\")";

            ResultSet rset = stmt.executeQuery(selectLastRecord);

            rset.next();
            ORFid = rset.getInt("orf_id");
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return ORFid;}

    
    
    
}
