package windowCommitConstruction;

import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTabbedPane;

import javax.swing.*;
import java.awt.*;

import testSmellDetection.testSmellInfo.constructorInitialization.ConstructorInitializationInfo;
import testSmellDetection.testSmellInfo.eagerTest.EagerTestInfo;
import testSmellDetection.testSmellInfo.generalFixture.GeneralFixtureInfo;
import testSmellDetection.testSmellInfo.lackOfCohesion.LackOfCohesionInfo;
import testSmellDetection.testSmellInfo.magicNamberTest.MagicNumberTestInfo;

import java.util.ArrayList;

public class CommitWindowFactory {
    private static JPanel generalFixturePanel;
    private static JPanel eagerTestPanel;
    private static JPanel lackOfCohesionPanel;
    private static JPanel magicNumberPanel;
    private static JPanel constructorInitializationPanel;

    /* createWindow per GF-ET-LOC-MN */
    public static void createWindow(Boolean textual, Boolean structural,
                                    Project project,
                                    ArrayList<GeneralFixtureInfo> listGFI,
                                    ArrayList<EagerTestInfo> listETI,
                                    ArrayList<LackOfCohesionInfo> listLOCI,
                                    ArrayList<MagicNumberTestInfo> listMNI,
                                    ArrayList<ConstructorInitializationInfo> listCII) {
        CommitPrincipalFrame principalFrame = null;
        //Controllo per vedere se la window esiste già.
        boolean frameExist = false;
        Frame[] frames = JFrame.getFrames();
        for(Frame frame : frames){
            if(frame.getName().equals("DARTSCommitWindow")){
                principalFrame = (CommitPrincipalFrame) frame;
                frameExist = true;
            }
        }
        if(!frameExist){
            principalFrame = new CommitPrincipalFrame();
            principalFrame.setName("DARTSCommitWindow");
            principalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        JBTabbedPane detectionTp = (JBTabbedPane) principalFrame.getDetectionTp();
        if(textual){
            principalFrame.removeTextualPanel();
            principalFrame.addTextualPanel(createPanel(project, listGFI, listETI, listLOCI, listMNI, listCII));
        }
        if(structural){
            principalFrame.removeStructuralPanel();
            principalFrame.addStructuralPanel(createPanel(project, listGFI, listETI, listLOCI, listMNI, listCII));
        }
        principalFrame.add(detectionTp);
        // Mostra la schermata al centro dello schermo
        principalFrame.setLocationRelativeTo(null);

        //Imposta la dimensione della finestra in modo che si adatti al suo contenuto
        principalFrame.pack();

        principalFrame.setVisible(true);
    }
    /* createWindow per GF-ET-LOC */
    public static void createWindow(Boolean textual, Boolean structural,
                                    Project project,
                                    ArrayList<GeneralFixtureInfo> listGFI,
                                    ArrayList<EagerTestInfo> listETI,
                                    ArrayList<LackOfCohesionInfo> listLOCI) {
        CommitPrincipalFrame principalFrame = null;
        //Controllo per vedere se la window esiste già.
        boolean frameExist = false;
        Frame[] frames = JFrame.getFrames();
        for(Frame frame : frames){
            if(frame.getName().equals("DARTSCommitWindow")){
                principalFrame = (CommitPrincipalFrame) frame;
                frameExist = true;
            }
        }
        if(!frameExist){
            principalFrame = new CommitPrincipalFrame();
            principalFrame.setName("DARTSCommitWindow");
            principalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        JBTabbedPane detectionTp = (JBTabbedPane) principalFrame.getDetectionTp();
        if(textual){
            principalFrame.removeTextualPanel();
            principalFrame.addTextualPanel(createPanel(project, listGFI, listETI, listLOCI));
        }
        if(structural){
            principalFrame.removeStructuralPanel();
            principalFrame.addStructuralPanel(createPanel(project, listGFI, listETI, listLOCI));
        }
        principalFrame.add(detectionTp);
        // Mostra la schermata al centro dello schermo
        principalFrame.setLocationRelativeTo(null);

        //Imposta la dimensione della finestra in modo che si adatti al suo contenuto
        principalFrame.pack();

        principalFrame.setVisible(true);
    }
    /* createPanel per GF-ET-LOC-MN */
    private static JBTabbedPane createPanel(Project project,
                                            ArrayList<GeneralFixtureInfo> listGFI,
                                            ArrayList<EagerTestInfo> listETI,
                                            ArrayList<LackOfCohesionInfo> listLOCI,
                                            ArrayList<MagicNumberTestInfo> listMNI,
                                            ArrayList<ConstructorInitializationInfo> listCII){
        // Controllo se ho trovato degli smells.
        if (listGFI != null) {
            generalFixturePanel = new GeneralFixtureCP(listGFI, project);
        }
        if (listETI != null) {
            eagerTestPanel = new EagerTestCP(listETI, project);
        }
        if (listLOCI != null){
            lackOfCohesionPanel = new LackOfCohesionCP(listLOCI, project);
        }
        if(listMNI != null){
            magicNumberPanel = new MagicNumberCP(listMNI, project);
        }
        if(listCII != null){
            constructorInitializationPanel = new ConstructorInitializazionCP(listCII, project);
        }


        //In questa parte costruisco le tab della window.
        JBTabbedPane tp = new JBTabbedPane();
        tp.setPreferredSize(new Dimension(1000, 500));
        if(listGFI != null){
            JBScrollPane scroll = new JBScrollPane(generalFixturePanel);
            tp.add("GeneralFixture", scroll);
        }
        if (listETI != null){
            JBScrollPane scroll = new JBScrollPane(eagerTestPanel);
            tp.add("EagerTest", scroll);
        }
        if (listLOCI != null) {
            JBScrollPane scroll = new JBScrollPane(lackOfCohesionPanel);
            tp.add("LackOfCohesion", scroll);
        }
        if (listMNI != null) {
            JBScrollPane scroll = new JBScrollPane(magicNumberPanel);
            tp.add("MagicNumber", scroll);
        }
        if (listCII != null) {
            JBScrollPane scroll = new JBScrollPane(constructorInitializationPanel);
            tp.add("ConstructorInitialization", scroll);
        }
        return tp;
    }
    /* createPanel per GF-ET-LOC */
    private static JBTabbedPane createPanel(Project project,
                                            ArrayList<GeneralFixtureInfo> listGFI,
                                            ArrayList<EagerTestInfo> listETI,
                                            ArrayList<LackOfCohesionInfo> listLOCI){
        // Controllo se ho trovato degli smells.
        if (listGFI != null) {
            generalFixturePanel = new GeneralFixtureCP(listGFI, project);
        }
        if (listETI != null) {
            eagerTestPanel = new EagerTestCP(listETI, project);
        }
        if (listLOCI != null){
            lackOfCohesionPanel = new LackOfCohesionCP(listLOCI, project);
        }

        //In questa parte costruisco le tab della window.
        JBTabbedPane tp = new JBTabbedPane();
        tp.setPreferredSize(new Dimension(1000, 500));
        if(listGFI != null){
            JBScrollPane scroll = new JBScrollPane(generalFixturePanel);
            tp.add("GeneralFixture", scroll);
        }
        if (listETI != null){
            JBScrollPane scroll = new JBScrollPane(eagerTestPanel);
            tp.add("EagerTest", scroll);
        }
        if (listLOCI != null) {
            JBScrollPane scroll = new JBScrollPane(lackOfCohesionPanel);
            tp.add("LackOfCohesion", scroll);
        }
        return tp;
    }
}