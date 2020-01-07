package runner

import java.io.FileNotFoundException
import java.io.IOException
import java.util.ArrayList
import java.util.Collections
import java.util.List
import map.Map
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import map.MapPackage

class Utils {
	val static ResourceSet resSet = new ResourceSetImpl();
		
	def static createInitialInput() {
		// have to specify the suffix otherwise the model generation will have exception
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore",new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi",new XMIResourceFactoryImpl());
		
		var model = readModel(EPackage, "models/", "Map.ecore").get(0);
		var map = model.EClassifiers.findFirst[it|it.name.equals('Map')] as EClass;
		var grid = model.EClassifiers.findFirst[it|it.name.equals('Grid')] as EClass;
		//create map
		var mapInstance = model.EFactoryInstance.create(map);
		var gridsReference = map.EReferences.findFirst[t| t.name.equals("grids")];
		var up = grid.EReferences.findFirst[r| r.name.equals("up")];
		var down = grid.EReferences.findFirst[r| r.name.equals("down")];
		var left = grid.EReferences.findFirst[r| r.name.equals("left")];
		var right = grid.EReferences.findFirst[r| r.name.equals("right")];
		
		var grids = createGrids(model.EFactoryInstance, grid, up, down, left, right, 10);
		(mapInstance.eGet(gridsReference) as EList<EObject>).addAll(grids);
		
		saveInstance(mapInstance);
	}
	
	def static createGrids(EFactory factory, EClass gridClass, EReference up, EReference down, EReference left, EReference right, int width){
		var grids = new ArrayList<EObject>();
		var idAttr = gridClass.EAttributes.get(0);  //It only constains one attribute for id
		var gridSize = width * width;
		for(var i = 0; i < gridSize; i++){
			var grid = factory.create(gridClass);
			grid.eSet(idAttr, i + 1);		//id starts from 1
			grids.add(grid);
		}
		
		for(var i = 0; i < gridSize; i++){
			if(i % width != 0){
				grids.get(i).eSet(left, grids.get(i-1));
			}
			
			if(i % width != width - 1){
				grids.get(i).eSet(right, grids.get(i+1));
			}
			
			if(i >= width){
				grids.get(i).eSet(up, grids.get(i - width))
			}
			
			if(i < width * (width - 1)){
				grids.get(i).eSet(down, grids.get(i + width))
			}
		}
		return grids
	}
	
	def static <RootType extends EObject> List<RootType> readModel(Class<RootType> type, String path, String name) {
		try {
			val resource = resSet.getResource(getURI(path, name),true);
			if(resource === null) throw new FileNotFoundException(getURI(path, name).toString())
			else {		
				return resource.contents as List<RootType>
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception(getURI(path, name).toString());
		}
	}
	
	def static getURI(String path, String name) {
		URI.createFileURI(path + "/"  + name)
	}
//	G model = null;
//		try {
//			ResourceSet resSet = new ResourceSetImpl();
//			Resource resource = resSet.getResource(URI.createURI(uri), true);
//			model = (G) resource.getContents().get(0);
//		}catch (org.eclipse.emf.common.util.WrappedException e) {
//			// if the file cannot be found then return null
//			if(e.getCause().getClass() == java.io.FileNotFoundException.class) {
//				return null;
//			}
//		}
//		
//		return model;

	def static Map loadInstance(String directory){
		MapPackage.eINSTANCE.class
		var resSet = new ResourceSetImpl();
		var resource = resSet.getResource(URI.createURI(directory), true);
		var model = resource.getContents().get(0) as Map;
		return model;	
	}

	def static saveInstance(EObject root){
        var resource = resSet.createResource(URI.createURI("instance.xmi"));
        // Get the first model element and cast it to the right type, in my
        // example everything is hierarchical included in this first node
        resource.getContents().add(root);

        // now save the content.
        try {
            resource.save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}