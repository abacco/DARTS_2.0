package dartsFuncionality;

import org.junit.Test;
import testConfiguration.TestConfig;
import testSmellDetection.bean.PsiClassBean;
import testSmellDetection.bean.PsiMethodBean;
import testSmellDetection.textualRules.LackOfCohesionOfTestSmellTextual;
import utility.ConverterUtilities;
import utility.TestSmellUtilities;

import java.util.ArrayList;

public class LackOfCohesionOfTestSmellTextualTest extends TestConfig {
    @Test
    public void testLackOfCohesionOfTestSmellNotPresent() {
        super.setFileName("main/LackOfCohesionOfTestSmell.java");
        super.setFileName("test/LackOfCohesionOfTestSmellNotPresent.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<PsiMethodBean> smellList = LackOfCohesionOfTestSmellTextual.checkMethodsThatCauseLackOfCohesion(testClassBeans.get(0));
        assertEquals(null, smellList);
    }
    @Test
    public void testLackOfCohesionOfTestSmellPresent() {
        super.setFileName("main/LackOfCohesionOfTestSmell.java");
        super.setFileName("test/LackOfCohesionOfTestSmellPresent.java");
        ArrayList<PsiClassBean> psiClassBeans = ConverterUtilities.getClassesFromPackages(getProject());
        ArrayList<PsiClassBean> testClassBeans = TestSmellUtilities.getAllTestClasses(psiClassBeans);
        ArrayList<PsiMethodBean> smellList = LackOfCohesionOfTestSmellTextual.checkMethodsThatCauseLackOfCohesion(testClassBeans.get(0));
        assertEquals(1, smellList.size());
    }

}
