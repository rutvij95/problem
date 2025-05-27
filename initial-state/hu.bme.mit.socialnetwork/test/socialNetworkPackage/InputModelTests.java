package socialNetworkPackage;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;
import org.junit.Test;

import mycode.FixModel;

public class InputModelTests {
	Resource res;
	
	private static final int REQUIRED_OBJECTS = 20;
	private static final int REQUIRED_ATTRIBUTES = 20;
	
	@Before
	public void initResource() {
		FixModel.init();
	
		ResourceSet resSet = new ResourceSetImpl();
		res = FixModel.loadResource(resSet, URI.createFileURI(FixModel.MODEL_MODEL_INPUT_XMI));
	}
	
	@Test
	public void checkObjectNumber() {
		TreeIterator<EObject> iterator = res.getAllContents();
		int objects = 0; 
		while(iterator.hasNext()) {
			objects++;
			iterator.next();
		}
		TestBase.checkAmount(objects, REQUIRED_OBJECTS, "Not enough EObjects in model!");
	}
	
	@Test
	public void checkAttributeNumber() {
		TreeIterator<EObject> iterator = res.getAllContents();
		int attibutes = 0; 
		while(iterator.hasNext()) {
			EObject object = iterator.next();
			attibutes += object.eClass().getEAllAttributes().size();
			
		}
		TestBase.checkAmount(attibutes, REQUIRED_ATTRIBUTES, "Not enough EAttributes in model!");
	}
}
