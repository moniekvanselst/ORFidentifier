/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
import java.util.ArrayList;
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

    static void readFile(String bestand) {
        try {
            BufferedReader infile = new BufferedReader(new FileReader(bestand));
            String line;
            while ((line = infile.readLine()) != null) {
                String[] splitline = line.split("\n", -1);
                //Sequentie seqObject = new Sequentie(splitline[1]);

            }
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException exc) {
            System.out.println("Het gekozen bestand kan niet gelezen worden");
        } catch (Exception exc) {
            System.out.println("Er is een onbekende fout opgetreden");
        }
    }

    static String BLAST(String seq) {
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
               // GUI.waitLabel.setText("Blasting.");
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

    // verander bastands naam
    static ArrayList<Object[]> BLASTparser(String path) {
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
                float coverage = (((eindEiwit - startEiwit)+1) / lengte )* 100;

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
                
                Object[] row = {eiwitNaam ,Evalue , coverage,identitie, accessie, startEiwit, eindEiwit, lengte, organism, hitSeq, querySeq, midline};
                table.add(row);

//                System.out.println("\nCurrent Element :"
//                        + hit.getName());
//                System.out.println("hit nr. : "
//                        + hit.getChild("Hit_num").getText());
                
                
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally{
        return table;
    }
    }
    

}
