package mycode;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import socialNetworkPackage.Person;
import socialNetworkPackage.SocialNetwork;
import socialNetworkPackage.SocialNetworkPackagePackage;

public class FixModel {

	public static final String MODEL_MODEL_OUTPUT_XMI = "model/model-output.xmi";
	public static final String MODEL_MODEL_INPUT_XMI = "model/model-input.xmi";
	
	/**
	 * The three magic lines.
	 */
	public static void init() {
		SocialNetworkPackagePackage.eINSTANCE.eClass();
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		reg.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
	}

	public static Resource createResource(ResourceSet resSet, URI uri) {
	    Resource resource = resSet.createResource(uri);
	    return resource;
	}
	
	public static  Resource loadResource(ResourceSet resSet, URI uri) {
	    Resource resource = resSet.getResource(uri, true);
	    return resource;
	}
	
	public static void main(String[] args) throws IOException {
		// initialize
		init();
		
		// Load the model and copy
		ResourceSet resSet = new ResourceSetImpl();
		Resource modelInput = loadResource(resSet, URI.createFileURI(MODEL_MODEL_INPUT_XMI));
		SocialNetwork sn = (SocialNetwork) modelInput.getContents().get(0);
		
		// Process and update the model
		pocessModel(sn);
		
		// Save the updated model
		Resource modelOutput = createResource(resSet, URI.createFileURI(MODEL_MODEL_OUTPUT_XMI));
		modelOutput.getContents().add(sn);
		modelOutput.save(null);
		
	}
	
	/**
	 * Assigns a value for each attribute in a social network
	 * @param sn
	 */
	public static void pocessModel(SocialNetwork sn) {
		// If you need an iterator for all objects in sn recursively,
		// you can use the following method:
		// sn.eAllContents()
		
		for (Person p : sn.getPeople()) {
			if(p.getName() == null || p.getName().isEmpty()) {
				p.setName("unnamed");
			} else {
				System.out.println(p.getName());
				p.setName(p.getName().toUpperCase());
				System.out.println(p.getName());
			}
		}
	}
}
