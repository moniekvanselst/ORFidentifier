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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.biojava.nbio.core.sequence.io.util.IOUtils;
import static org.biojava.nbio.ws.alignment.qblast.BlastAlignmentParameterEnum.ENTREZ_QUERY;
import org.biojava.nbio.ws.alignment.qblast.BlastProgramEnum;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastAlignmentProperties;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastOutputProperties;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastService;

/**
 *
 * @author van Selst
 */
public class Logica {

    public void readFile(String bestand) {
        try {
            BufferedReader infile = new BufferedReader(new FileReader(bestand));
            String line;
            int seqID = getSeqID();
            System.out.println(seqID);
            boolean firstline = true;
            String sequentie = "";
            while ((line = infile.readLine()) != null) {
                if (!firstline) {
                    sequentie = sequentie += line;
                } else {
                    firstline = false;
                }
            }
            GUIopen.waitLabel.setText("is aan het openen....");
            sequentie = sequentie.toUpperCase();
            Sequentie seqObject = new Sequentie(sequentie, seqID);
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException exc) {
            JOptionPane.showMessageDialog(null, "Het gekozen bestand kan niet gelezen worden", "Inane error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Er is een onbekende fout opgetreden", "Inane error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void makeFrames(String seq) {
        //String seq = "ATCCCACCAGCACGACGACGAGCAGCAGCGACGAGCGAGCACAGCGAAGCAGC"; // nog vervangen door Sequentie.getseq     
        String seqframe1, seqframe2, seqframe3, seqframerev1, seqframerev2, seqframerev3, seqframecomp1, seqframecomp2, seqframecomp3;

        seqframe1 = new StringBuilder(seq.substring(0, seq.length() - 1)).toString();
        seqframe2 = new StringBuilder(seq.substring(1, seq.length() - 1)).toString();
        seqframe3 = new StringBuilder(seq.substring(2, seq.length() - 1)).toString();
        seqframerev1 = new StringBuilder(seq.substring(0, seq.length() - 1)).reverse().toString();
        seqframerev2 = new StringBuilder(seq.substring(1, seq.length() - 1)).reverse().toString();
        seqframerev3 = new StringBuilder(seq.substring(2, seq.length() - 1)).reverse().toString();
        seqframecomp1 = seqframerev1.replaceAll("A", "t").replaceAll("T", "a").replaceAll("G", "c").replaceAll("C", "g").toUpperCase();
        seqframecomp2 = seqframerev2.replaceAll("A", "t").replaceAll("T", "a").replaceAll("G", "c").replaceAll("C", "g").toUpperCase();
        seqframecomp3 = seqframerev3.replaceAll("A", "t").replaceAll("T", "a").replaceAll("G", "c").replaceAll("C", "g").toUpperCase();

        // hier frames omzetten naar eiwitten
        HashMap<Integer, String> seqframeMap = new HashMap<Integer, String>();
        seqframeMap.put(1, seqframe1);
        seqframeMap.put(2, seqframe2);
        seqframeMap.put(3, seqframe3);
        seqframeMap.put(-1, seqframecomp1);
        seqframeMap.put(-2, seqframecomp2);
        seqframeMap.put(-3, seqframecomp3);

        Sequentie.seqframeMap = seqframeMap;

    }

    public void findORF() {
        System.out.println("een");
        HashMap<Integer, String> seqframeMap = Sequentie.seqframeMap;
        IUPACParser iup = new IUPACParser();
        Object table = iup.getTable(1);
        int[] frames = {1, 2, 3, -1, -2, -3};
        int count = 0;
        System.out.println("twee");
        String stop1 = "TAA";
        String stop2 = "TAG";
        String stop3 = "TGA";
        for (String sequ : seqframeMap.values()) {
            GUI.waitLabel.setText("is aan het openen....");
            System.out.println("drie");
            int a = sequ.length() % 3;
            sequ = sequ.substring(0, sequ.length() - a);
            String orf = "";
            int frame = frames[count];
            count++;
            int eindpositie = 0;
            int startpositie = 0;
            for (int i = 0; i < sequ.length(); i = i + 3) { // loopt over elke char+3 van de string heen
                System.out.println("vier");
                eindpositie = eindpositie + 3;
                String codon = sequ.substring(i, i + 3);
                if (codon.equals(stop1) | codon.equals(stop2) | codon.equals(stop3)) {
                    System.out.println("vijf");
                    if (100 < orf.length()) {
                        System.out.println("zes");
                        startpositie = sequ.indexOf(orf);
                        System.out.println("sez . vijf");
                        ORF orfObject = new ORF(frame, startpositie, eindpositie, orf);
                       // Sequentie.ORFlist.add(orfObject);
                        System.out.println(orfObject.getSeqorf());
                        GUI.ORFtextArea.setText("ORF "+i);//": "+orfObject.getSeqorf()+"/n");
                      //new GUI().ORFtextArea.append("ORF "+i+": "+orfObject.getSeqorf()+"/n");
                        GUI.ORFdropDown.addItem("ORF"+frame);
                        System.out.println("zeven");
                    }
                    orf = "";
                    System.out.println("acht");
                } else {
                    orf = orf + codon;
                }

            }
        }

    }

    public String BLAST(String seq) {
        String seq2 = "MELGLGGLSTLSHCPWPRQQAPLGLSAQPALWPTLAALALLSSVAEASLGSAPRSPAPREGPPPVLASPAGHLPGGRTARWCSGRARRPPPQPSRPAPPPPAPPSALPRGGRAARAGGPGSRARAAGARGCRLRSQLVPVRALGLGHRSDELVRFRFCSGSCRRARSPHDLSLASLLGAGALRPPPGSRPVSQPCCRPTRYEAVSFMDVNSTWRTVDRLSATACGCLG";

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
            rid = service.sendAlignmentRequest(seq2, props);

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
    public ArrayList<Object[]> BLASTparser(String path) {
        ArrayList<Object[]> table = new ArrayList<Object[]>();
        try {
            File inputFile = new File(path);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            System.out.println("Root element :" + document.getRootElement().getName());
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

                Object[] row = {eiwitNaam, Evalue, coverage, identitie, accessie, startEiwit, eindEiwit, lengte, organism, hitSeq, querySeq, midline};
                table.add(row);

                BLASTopslaan(eiwitNaam, Evalue, coverage, identitie, accessie, startEiwit, eindEiwit, lengte, organism, hitSeq, querySeq, midline);

            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            return table;
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
            System.out.println(seqID);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        seqID++;
        return seqID;
    }

    public void SEQopslaan(String bestand, String codonTable, String date, String name, String organism) {
        // zet dit ook in db
    }

    public void BLASTopslaan(String eiwitNaam, String Evalue, float coverage, String identitie, String accessie, float startEiwit, float eindEiwit, float lengte, String organism, String hitSeq, String querySeq, String midline) {
        //zet alles in db
    }

}
