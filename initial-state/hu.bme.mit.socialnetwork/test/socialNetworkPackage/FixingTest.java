package socialNetworkPackage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;
import org.junit.Test;

import mycode.FixModel;

public class FixingTest {
	Resource res;
	
	@Before
	public void initResource() {
		FixModel.init();
	
		ResourceSet resSet = new ResourceSetImpl();
		res = FixModel.loadResource(resSet, URI.createFileURI(FixModel.MODEL_MODEL_INPUT_XMI));
	}
	
	private void setToDefault() {
		TreeIterator<EObject> iterator = res.getAllContents();
		while(iterator.hasNext()) {
			EObject object = iterator.next();
			for(EAttribute attribute : object.eClass().getEAllAttributes()) {
				if(attribute.isMany()) {
					List<?> elementList = (List<?>) object.eGet(attribute);
					elementList.clear();
				} else {
					object.eSet(attribute, attribute.getDefaultValue());
				}
			}
		}
	}
	
	private void checkForDefault() {
		TreeIterator<EObject> iterator = res.getAllContents();
		while(iterator.hasNext()) {
			EObject object = iterator.next();
			for(EAttribute attribute : object.eClass().getEAllAttributes()) {
				Object value = object.eGet(attribute);
				if(attribute.isMany()) {
					List<?> elementList = (List<?>) value;
					assertFalse(elementList.isEmpty());
				} else {
					assertNotEquals("Value was left default!", attribute.getDefaultValue(), value);
				}
			}
		}
	}
	@Test
	public void testFixing() {
		setToDefault();
		FixModel.pocessModel((SocialNetwork) res.getContents().get(0));
		checkForDefault();
	}
}
