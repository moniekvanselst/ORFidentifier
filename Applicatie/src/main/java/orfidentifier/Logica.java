package orfidentifier;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.biojava3.core.sequence.io.IUPACParser;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static orfidentifier.ORF.ORFid;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;

import org.biojava.nbio.core.sequence.io.util.IOUtils;
import org.biojava.nbio.core.sequence.transcription.TranscriptionEngine;
import static org.biojava.nbio.ws.alignment.qblast.BlastAlignmentParameterEnum.ENTREZ_QUERY;
import org.biojava.nbio.ws.alignment.qblast.BlastProgramEnum;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastAlignmentProperties;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastOutputProperties;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastService;

/**
 *
 * @author Moniek van Selst, Willem Korsten, Nicky van Bergen OWE7pg6 Klas
 * Bin-2B
 */
public class Logica {

    public static int BLASTid = 0;
    public static int organismID = 0;

    /**
     * Deze methode leest het gekozen bestand in en split op de enter.
     * Vervolgens wordt van de Sequentie een object aangemaakt in Sequentie
     * class mbv de constructor.
     */
    public void readFile(String bestand) {
        try {
            BufferedReader infile = new BufferedReader(new FileReader(bestand));
            String line;
            int seqID = getSeqID();

            boolean firstline = true;
            String sequentie = "";
            while ((line = infile.readLine()) != null) {
                if (!firstline) {
                    sequentie = sequentie += line;
                } else {
                    firstline = false;
                }
            }

            sequentie = sequentie.toUpperCase();
            Sequentie seqObject = new Sequentie(sequentie, seqID, bestand);
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException exc) {
            JOptionPane.showMessageDialog(null, "Het gekozen bestand kan niet gelezen worden" + exc.toString(), "Insane error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Er is een onbekende fout opgetreden", "Insane error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Deze methode zet de gegeven sequentie om in de verschillende frames
     */
    public String[] makeFrames(String seq) throws CompoundNotFoundException {
        String seqframe1, seqframe2, seqframe3, seqframerev1, seqframerev2, seqframerev3, seqframecomp1, seqframecomp2, seqframecomp3;

        seqframe1 = new StringBuilder(seq.substring(0, seq.length() - 1)).toString();
        seqframe2 = new StringBuilder(seq.substring(1, seq.length() - 1)).toString();
        seqframe3 = new StringBuilder(seq.substring(2, seq.length() - 1)).toString();
        String seqreverse = new StringBuilder(seq).reverse().toString();

        seqframerev1 = new StringBuilder(seqreverse.substring(0, seq.length() - 1)).toString();
        seqframerev2 = new StringBuilder(seqreverse.substring(1, seq.length() - 1)).toString();
        seqframerev3 = new StringBuilder(seqreverse.substring(2, seq.length() - 1)).toString();

        seqframecomp1 = seqframerev1.replaceAll("A", "t").replaceAll("T", "a").replaceAll("G", "c").replaceAll("C", "g").toUpperCase();
        seqframecomp2 = seqframerev2.replaceAll("A", "t").replaceAll("T", "a").replaceAll("G", "c").replaceAll("C", "g").toUpperCase();
        seqframecomp3 = seqframerev3.replaceAll("A", "t").replaceAll("T", "a").replaceAll("G", "c").replaceAll("C", "g").toUpperCase();
        String[] frames = {seqframe1, seqframe2, seqframe3, seqframecomp1, seqframecomp2, seqframecomp3, seq};

        toProtein(frames);
        return frames;

    }

    public void toProtein(String[] frames) throws CompoundNotFoundException {
        String keuze = GUIopen.CodonDropDown.getSelectedItem().toString();

        String[] keuzes = keuze.split("\\.");
        int nr = Integer.parseInt(keuzes[0]);

        TranscriptionEngine.Builder b = new TranscriptionEngine.Builder();
        b.table(nr).initMet(false).trimStop(false);
        TranscriptionEngine engine = b.build();

        String proteinframe1 = new DNASequence(frames[0]).getRNASequence().getProteinSequence(engine).toString();
        String proteinframe2 = new DNASequence(frames[1]).getRNASequence().getProteinSequence(engine).toString();
        String proteinframe3 = new DNASequence(frames[2]).getRNASequence().getProteinSequence(engine).toString();
        String proteinframecomp1 = new DNASequence(frames[3]).getRNASequence().getProteinSequence(engine).toString();
        String proteinframecomp2 = new DNASequence(frames[4]).getRNASequence().getProteinSequence(engine).toString();
        String proteinframecomp3 = new DNASequence(frames[5]).getRNASequence().getProteinSequence(engine).toString();

        HashMap<Integer, String> seqframeMap = new HashMap<Integer, String>();
        seqframeMap.put(1, proteinframe1);
        seqframeMap.put(2, proteinframe2);
        seqframeMap.put(3, proteinframe3);
        seqframeMap.put(-1, proteinframecomp1);
        seqframeMap.put(-2, proteinframecomp2);
        seqframeMap.put(-3, proteinframecomp3);

        GUI.SEQtextArea.append("Frame: 1, Sequence:" + proteinframe1 + "\n");
        GUI.SEQtextArea.append("Frame: 2, Sequence: " + proteinframe2 + "\n");
        GUI.SEQtextArea.append("Frame: 3, Sequence:  " + proteinframe3 + "\n");
        GUI.SEQtextArea.append("Original Sequence:" + frames[6] + "\n");
        GUI.SEQtextArea.append("Frame: -1, Sequence:" + proteinframecomp1 + "\n");
        GUI.SEQtextArea.append("Frame: -2, Sequence: " + proteinframecomp2 + "\n");
        GUI.SEQtextArea.append("Frame: -3, Sequence:   " + proteinframecomp3 + "\n");

        Sequentie.seqframeMap = seqframeMap;
    }

    public void findORF() {
        int seqID = Sequentie.seqID;

        HashMap<Integer, String> seqframeMap = Sequentie.seqframeMap;
        IUPACParser iup = new IUPACParser();
        Object table = iup.getTable(1);
        int[] frames = {1, 2, 3, -1, -2, -3};
        int count = 0;

        for (String sequ : seqframeMap.values()) {

            String orf = "";
            int frame = frames[count];
            count++;
            int eindpositie = 0;
            int startpositie = 0;
            int tel = 0;
            for (int i = 0; i < sequ.length(); i++) { // loopt over elke char+3 van de string heen

                eindpositie = eindpositie + 1;
                String codon = sequ.substring(i, i + 1);
                if (codon.equals("*")) {

                    if (50 < orf.length()) {

                        startpositie = sequ.indexOf(orf);

                        tel++;
                        ORF orfObject = new ORF(frame, startpositie, eindpositie, orf, seqID);

                        Sequentie.ORFlist.add(orfObject);

                        GUI.ORFtextArea.append("f" + frame + " orf: " + tel + ": " + orfObject.getSeqorf() + "\n");
                        //new GUI().ORFtextArea.append("ORF "+i+": "+orfObject.getSeqorf()+"/n");mjhk
                        GUI.ORFdropDown.addItem("f" + frame + " orf: " + tel);

                    }
                    orf = "";

                } else {
                    orf = orf + codon;
                }

            }
        }

    }

    public String BLAST(String seq) {
        NCBIQBlastService service = new NCBIQBlastService();
        NCBIQBlastAlignmentProperties props = new NCBIQBlastAlignmentProperties();
        props.setBlastProgram(BlastProgramEnum.blastp);
        props.setBlastDatabase("swissprot");
        //props.setAlignmentOption(ENTREZ_QUERY,"\"serum albumin\"[Protein name] AND mammals[Organism]");
        NCBIQBlastOutputProperties outputProps = new NCBIQBlastOutputProperties();
        // outputProps.setAlignmentNumber(200); // outputProps.setOutputOption(BlastOutputParameterEnum.ALIGNMENTS, “200”);

        String rid = null;          // blast request ID
        FileWriter writer = null;
        BufferedReader reader = null;

        try {
            // send blast request and save request id
            rid = service.sendAlignmentRequest(seq, props);

            // wait until results become available. Alternatively, one can do other computations/send other alignment requests
            while (!service.isReady(rid)) {
                System.out.println("Waiting for results. Sleeping for 5 seconds");
                Thread.sleep(5000);
            }

            // read results when they are ready
            InputStream in = service.getAlignmentResults(rid, outputProps);
            reader = new BufferedReader(new InputStreamReader(in));

            // write blast output to specified file
            File f = new File("BLAST_OUTPUT_FILE.txt");
            String path = f.getAbsolutePath();
            System.out.println("Saving query results in file " + f.getAbsolutePath());
            writer = new FileWriter(f);

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + System.getProperty("line.separator"));
            }

            return path;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return " ";
        } finally {
            // clean up
            IOUtils.close(writer);
            IOUtils.close(reader);

            // delete given alignment results from blast server (optional operation)
            service.sendDeleteRequest(rid);

        }
    }

    // verander bestandsnaam
    public void BLASTparser(String path, ORF orf) {

        try {
            File inputFile = new File(path);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);

            Element classElement = document.getRootElement();
            Element blastoutput_iteration = classElement.getChild("BlastOutput_iterations");
            Element iteration = blastoutput_iteration.getChild("Iteration");
            Element iteration_hits = iteration.getChild("Iteration_hits");
            List<Element> hitList = iteration_hits.getChildren();
            System.out.println("----------------------------");

            for (int temp = 0; temp < hitList.size(); temp++) {
                Element hit = hitList.get(temp);
                Element hsps = hit.getChild("Hit_hsps");
                Element hsp = hsps.getChild("Hsp");
                //System.out.println(hsp.getChildren().toString());
                float startEiwit = Float.parseFloat(hsp.getChild("Hsp_query-from").getText());
                float eindEiwit = Float.parseFloat(hsp.getChild("Hsp_query-to").getText());
                //float lengte = Float.parseFloat(hit.getChild("Hit_len").getText());
                float lengte = Float.parseFloat(classElement.getChild("BlastOutput_query-len").getText());
                float coverage = (((eindEiwit - startEiwit) + 1) / lengte) * 100;

                String id = hit.getChild("Hit_id").getText();
                String[] idlist = id.split("\\|");
                String organism = idlist[4];
                String hitSeq = hsp.getChild("Hsp_hseq").getText();
                String querySeq = hsp.getChild("Hsp_qseq").getText();
                String midline = hsp.getChild("Hsp_midline").getText();
                String Evalue = hsp.getChild("Hsp_evalue").getText();
                String identitie = hsp.getChild("Hsp_identity").getText();
                String accessie = hit.getChild("Hit_accession").getText();
                String eiwitNaam = hit.getChild("Hit_def").getText().split("=")[1].split(";")[0];
                String sequentie = orf.getSeqorf();

                Object[] row = {eiwitNaam, Evalue, coverage, identitie, accessie, startEiwit, eindEiwit, lengte, organism, hitSeq, querySeq, midline};
                orf.table.add(row);
                organismeOpslaan(organism);

                try (
                        Connection conn = DriverManager.getConnection(
                                "jdbc:derby://localhost:1527/ORF Identifier DB", "owe7pg6", "bks");
                        Statement stmt = conn.createStatement();) {

                    String selectLastRecord = "SELECT * FROM OWE7PG6.\"blast_resultaten\" WHERE \"blast_id\" = (SELECT MAX(\"blast_id\") FROM OWE7PG6.\"blast_resultaten\")";

                    ResultSet rset = stmt.executeQuery(selectLastRecord);

                    rset.next();
                    BLASTid = rset.getInt("blast_id");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                BLASTid++;

                BLASTopslaan(BLASTid, eiwitNaam, startEiwit, eindEiwit, sequentie, hitSeq, querySeq, midline, ORF.ORFid, organismID, Evalue, coverage, identitie, accessie);

            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public int getSeqID() {
        int seqID = 0;
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:derby://localhost:1527/ORF Identifier DB", "owe7pg6", "bks");
                Statement stmt = conn.createStatement();) {

            //String selectLastRecord = "SELECT * FROM OWE7PG6.\"sequentie\" ORDER BY \"seq_id\" DESC";
            //String selectLastRecord = "SELECT TOP 1 * FROM OWE7PG6.\"sequentie\" ORDER BY \"seq_id\" DESC";
            String selectLastRecord = "SELECT * FROM OWE7PG6.\"sequentie\" WHERE \"seq_id\" = (SELECT MAX(\"seq_id\") FROM OWE7PG6.\"sequentie\")";

            ResultSet rset = stmt.executeQuery(selectLastRecord);

            rset.next();
            seqID = rset.getInt("seq_id");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        seqID++;
        return seqID;
    }

    public void SEQopslaan(int seqID, String bestand, String keuze, String date, String name, String organism) throws IOException {
        String[] keuzes = keuze.split("\\.");
        int ct_id = Integer.parseInt(keuzes[0]);
        byte[] fileContent = null;
        // initialize string buffer to hold contents of file
        StringBuffer fileContentStr = new StringBuffer("");
        BufferedReader reader = null;
        try {
            // initialize buffered reader  
            reader = new BufferedReader(new FileReader(bestand));
            String line = null;
            // read lines of file
            while ((line = reader.readLine()) != null) {
                //append line to string buffer
                fileContentStr.append(line).append("\n");
            }
            // convert string to byte array
            fileContent = fileContentStr.toString().trim().getBytes();
        } catch (IOException e) {
            throw new IOException("Unable to convert file to byte array. " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:derby://localhost:1527/ORF Identifier DB", "owe7pg6", "bks");
                Statement stmt = conn.createStatement();) {

            String fillSequentie = "INSERT INTO OWE7PG6.\"sequentie\" (\"seq_id\",\"file\" ,\"datum\", \"organisme\", \"naam\", \"codontable_id\") VALUES(" + seqID + ",\'" + fileContent + "\',\'" + date + "\', \'" + organism + "\',\'" + name + "\', " + ct_id + ")";

            stmt.executeUpdate(fillSequentie);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    //(int BLASTid, String eiwitNaam, float startEiwit, float eindEiwit, String sequentie, String hitSeq, String querySeq, String midline, int ORF.ORFid, int organismID, String Evalue, float coverage, String identitie, String accessie)

    public void BLASTopslaan(int BLASTid, String eiwitNaam, float startEiwit, float eindEiwit, String sequentie, String hitSeq, String querySeq, String midline, int ORFid, int organismID, String Evalue, float coverage, String identitie, String accessie) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:derby://localhost:1527/ORF Identifier DB", "owe7pg6", "bks");
                Statement stmt = conn.createStatement();) {

            String fillBLAST = "INSERT INTO OWE7PG6.\"blast_resultaten\" (\"blast_id\",\"eiwitnaam\" ,\"start_eiwit\", \"eind_eiwit\", \"sequentie\", \"hitsequentie\", \"querysequentie\", \"alignsequentie\", \"ORF's_ORF_ID\", \"organisme_organisme_id\", \"e-value\", \"coverage\", \"identity\", \"accessiecode\") VALUES( " + BLASTid + ",\'" + eiwitNaam + "\' ," + startEiwit + ", " + eindEiwit + ", \'" + sequentie + "\', \'" + hitSeq + "\', \'" + querySeq + "\', \'" + midline + "\', " + ORFid + ", " + organismID + ", \'" + Evalue + "\', " + coverage + ", \'" + identitie + "\', \'" + accessie + "\')";
            System.out.println("Query is: " + fillBLAST);
            stmt.executeUpdate(fillBLAST);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void ORFopslaan(int ORFid, int startpositie, int eindpositie, int seqID, int frame) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:derby://localhost:1527/ORF Identifier DB", "owe7pg6", "bks");
                Statement stmt = conn.createStatement();) {

            String fillORFs = "INSERT INTO OWE7PG6.\"ORF's\" (\"orf_id\",\"start_positie\",\"eind_positie\" ,\"sequentie_seq_id\", \"frame\") VALUES(" + ORFid + "," + startpositie + "," + eindpositie + "," + seqID + ", " + frame + ")";

            stmt.executeUpdate(fillORFs);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void organismeOpslaan(String organisme) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:derby://localhost:1527/ORF Identifier DB", "owe7pg6", "bks");
                Statement stmt = conn.createStatement();) {
            String selectLastRecord = "SELECT * FROM OWE7PG6.\"organisme\" WHERE \"organisme_id\" = (SELECT MAX(\"organisme_id\") FROM OWE7PG6.\"organisme\")";

            ResultSet rset = stmt.executeQuery(selectLastRecord);

            rset.next();
            organismID = rset.getInt("organisme_id");
            organismID++;
            String fillOrganism = "INSERT INTO OWE7PG6.\"organisme\" (\"organisme_id\", \"naam\") VALUES(" + organismID + ",\'" + organisme + "\')";

            stmt.executeUpdate(fillOrganism);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
