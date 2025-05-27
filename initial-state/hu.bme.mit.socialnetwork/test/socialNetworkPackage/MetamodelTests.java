package socialNetworkPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.junit.Test;

public class MetamodelTests {
	private static final int REQUIRED_CLASSES = 8;
	private static final int REQUIRED_REFERENCES = 10;
	private static final int REQUIRED_ATTRIBUTES = 10;

	SocialNetworkPackagePackage metamodel = SocialNetworkPackagePackage.eINSTANCE;

	@Test
	public void hasEnoughClasses() {
		int numberOfClassifiers = metamodel.getEClassifiers().size();
		TestBase.checkAmount(numberOfClassifiers, REQUIRED_CLASSES, "Not enough EClasses!");
	}

	@Test
	public void hasEnoughReferences() {
		int numberOfReferences = 0;
		for (EClassifier classifier : metamodel.getEClassifiers()) {
			if (classifier instanceof EClass) {
				EClass clazz = (EClass) classifier;
				numberOfReferences += clazz.getEReferences().size();
			}
		}
		TestBase.checkAmount(numberOfReferences, REQUIRED_REFERENCES, "Not enough EReferences!");
	}

	@Test
	public void hasEnoughAttributes() {
		int numberOfAttributes = 0;
		for (EClassifier classifier : metamodel.getEClassifiers()) {
			if (classifier instanceof EClass) {
				EClass clazz = (EClass) classifier;
				numberOfAttributes += clazz.getEAttributes().size();
			}
		}
		TestBase.checkAmount(numberOfAttributes, REQUIRED_ATTRIBUTES, "Not enough EAttributes!");
	}

}
