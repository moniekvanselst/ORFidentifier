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

    static void BLAST(String seq) {
        String seq2 = "MELGLGGLSTLSHCPWPRQQAPLGLSAQPALWPTLAALALLSSVAEASLGSAPRSPAPREGPPPVLASPAGHLPGGRTARWCSGRARRPPPQPSRPAPPPPAPPSALPRGGRAARAGGPGSRARAAGARGCRLRSQLVPVRALGLGHRSDELVRFRFCSGSCRRARSPHDLSLASLLGAGALRPPPGSRPVSQPCCRPTRYEAVSFMDVNSTWRTVDRLSATACGCLG";
        
        NCBIQBlastService service = new NCBIQBlastService();
        NCBIQBlastAlignmentProperties props = new NCBIQBlastAlignmentProperties();
        props.setBlastProgram(BlastProgramEnum.blastp);
        props.setBlastDatabase("swissprot");
        props.setAlignmentOption(ENTREZ_QUERY, "\"serum albumin\"[Protein name] AND mammals[Organism]");
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
            System.out.println("Saving query results in file " + f.getAbsolutePath());
            writer = new FileWriter(f);

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            // clean up
            IOUtils.close(writer);
            IOUtils.close(reader);

            // delete given alignment results from blast server (optional operation)
            service.sendDeleteRequest(rid);
        }
    }

    // verander bastands naam
    static void BLASTparser() {
        try {
            File inputFile = new File("C:\\Users\\van Selst\\Documents\\blok 7\\basttest2\\blastres.txt");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            System.out.println("Root element :" + document.getRootElement().getName());
            Element classElement = document.getRootElement();
            List<Element> hitList = classElement.getChildren();
            System.out.println("----------------------------");

            for (int temp = 0; temp < hitList.size(); temp++) {
                Element hit = hitList.get(temp);
                Element hsps = hit.getChild("Hit_hsps");
                Element hsp = hsps.getChild("Hsp");
                //System.out.println(hsp.getChildren().toString());
                float startEiwit = Float.parseFloat(hsp.getChild("Hsp_query-from").getText());
                float eindEiwit = Float.parseFloat(hsp.getChild("Hsp_query-to").getText());
                float lenkte = Float.parseFloat(hit.getChild("Hit_len").getText());
                float coverage = startEiwit - eindEiwit / lenkte * 100;

                System.out.println("\nCurrent Element :"
                        + hit.getName());
                System.out.println("hit nr. : "
                        + hit.getChild("Hit_num").getText());
                System.out.println("start eiwit: "
                        + hsp.getChild("Hsp_query-from").getText());
                System.out.println("eind eiwit : "
                        + hsp.getChild("Hsp_query-to").getText());
                System.out.println("sequentie nieuw?? : "
                        + hsp.getChild("Hsp_hseq").getText());
                System.out.println("e-value : "
                        + hsp.getChild("Hsp_evalue").getText());
                System.out.println("coverage : " + coverage);

                System.out.println("identitie : "
                        + hsp.getChild("Hsp_identity").getText());
                System.out.println("accesiecode : "
                        + hit.getChild("Hit_accession").getText());

            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
