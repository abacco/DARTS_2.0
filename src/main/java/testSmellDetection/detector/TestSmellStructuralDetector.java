package testSmellDetection.detector;

import com.intellij.openapi.project.Project;
import contextualAnalysis.hashUtilies.ProductionClassesSingleton;
import testSmellDetection.bean.PsiClassBean;
import testSmellDetection.bean.PsiMethodBean;
import testSmellDetection.testSmellInfo.constructorInitialization.ConstructorInitializationInfo;
import testSmellDetection.testSmellInfo.magicNamberTest.MagicNumberTestInfo;
import utility.ConverterUtilities;
import utility.TestSmellUtilities;

import testSmellDetection.structuralRules.EagerTestStructural;
import testSmellDetection.structuralRules.GeneralFixtureStructural;
import testSmellDetection.structuralRules.LackOfCohesionOfTestSmellStructural;
import testSmellDetection.testSmellInfo.eagerTest.EagerTestInfo;
import testSmellDetection.testSmellInfo.eagerTest.MethodWithEagerTest;
import testSmellDetection.testSmellInfo.generalFixture.GeneralFixtureInfo;
import testSmellDetection.testSmellInfo.generalFixture.MethodWithGeneralFixture;
import testSmellDetection.testSmellInfo.lackOfCohesion.LackOfCohesionInfo;
import testSmellDetection.textualRules.EagerTestTextual;
import testSmellDetection.textualRules.GeneralFixtureTextual;
import testSmellDetection.textualRules.LackOfCohesionOfTestSmellTextual;
import java.util.ArrayList;

public class TestSmellStructuralDetector implements IDetector{
    private ArrayList<PsiClassBean> classBeans;
    private ArrayList<PsiClassBean> testClasses;
    private ArrayList<PsiClassBean> productionClasses;
    private ProductionClassesSingleton productionClassesSingleton;

    //variabili per l'analisi di GeneralFixture
    private int numberOfProductionTypes = 3;
    private int numberOfObjectUsesInSetup = 3;

    public TestSmellStructuralDetector(Project project){
        classBeans = ConverterUtilities.getClassesFromPackages(project);
        testClasses = TestSmellUtilities.getAllTestClasses(classBeans);
        productionClasses = TestSmellUtilities.getAllProductionClasses(classBeans, testClasses);
        productionClassesSingleton = ProductionClassesSingleton.getIstance();
        productionClassesSingleton.setProductionClasses(productionClasses);
    }

    public ArrayList<MagicNumberTestInfo> executeDetectionForMagicNumber(){
        return null;
    }

    @Override
    public ArrayList<ConstructorInitializationInfo> executeDetectionForConstructorInitialization() { return null; }

    public ArrayList<GeneralFixtureInfo> executeDetectionForGeneralFixture() {
        ArrayList<GeneralFixtureInfo> classesWithGeneralFixture = new ArrayList<>();
        for(PsiClassBean testClass : testClasses){
            if(GeneralFixtureStructural.isGeneralFixture(testClass, productionClasses, testClasses, numberOfProductionTypes, numberOfObjectUsesInSetup)){
                ArrayList<MethodWithGeneralFixture> methodWithGeneralFixtures = GeneralFixtureTextual.checkMethodsThatCauseGeneralFixture(testClass);
                classesWithGeneralFixture.add(new GeneralFixtureInfo(testClass, methodWithGeneralFixtures));
            }
        }
        return classesWithGeneralFixture;
    }


    public ArrayList<EagerTestInfo> executeDetectionForEagerTest() {
        ArrayList<EagerTestInfo> classesWithEagerTest = new ArrayList<>();
        for(PsiClassBean testClass : testClasses){
            if(testClass.getProductionClass() != null) {
                if (EagerTestStructural.isEagerTest(testClass, testClasses, productionClasses)) {
                    ArrayList<MethodWithEagerTest> methodWithEagerTests = EagerTestTextual.checkMethodsThatCauseEagerTest(testClass, testClass.getProductionClass());
                    classesWithEagerTest.add(new EagerTestInfo(testClass, testClass.getProductionClass(), methodWithEagerTests));
                }
            }
        }
        return classesWithEagerTest;
    }


    public ArrayList<LackOfCohesionInfo> executeDetectionForLackOfCohesion() {
        ArrayList<LackOfCohesionInfo> classesWithLackOfCohesion = new ArrayList<>();
        for(PsiClassBean testClass : testClasses){
            if(testClass.getProductionClass() != null) {
                if (LackOfCohesionOfTestSmellStructural.isLackOfCohesion(testClass)) {
                    ArrayList<PsiMethodBean> methodsWithLackOfCohesion = LackOfCohesionOfTestSmellTextual.checkMethodsThatCauseLackOfCohesion(testClass);
                    classesWithLackOfCohesion.add(new LackOfCohesionInfo(testClass, testClass.getProductionClass(), methodsWithLackOfCohesion));
                }
            }
        }
        return classesWithLackOfCohesion;
    }
}