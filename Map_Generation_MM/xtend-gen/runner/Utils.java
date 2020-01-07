package runner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import map.MapPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class Utils {
  private static final ResourceSet resSet = new ResourceSetImpl();
  
  public static void createInitialInput() {
    Map<String, Object> _extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
    XMIResourceFactoryImpl _xMIResourceFactoryImpl = new XMIResourceFactoryImpl();
    _extensionToFactoryMap.put("ecore", _xMIResourceFactoryImpl);
    Map<String, Object> _extensionToFactoryMap_1 = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
    XMIResourceFactoryImpl _xMIResourceFactoryImpl_1 = new XMIResourceFactoryImpl();
    _extensionToFactoryMap_1.put("xmi", _xMIResourceFactoryImpl_1);
    EPackage model = Utils.<EPackage>readModel(EPackage.class, "models/", "Map.ecore").get(0);
    final Function1<EClassifier, Boolean> _function = (EClassifier it) -> {
      return Boolean.valueOf(it.getName().equals("Map"));
    };
    EClassifier _findFirst = IterableExtensions.<EClassifier>findFirst(model.getEClassifiers(), _function);
    EClass map = ((EClass) _findFirst);
    final Function1<EClassifier, Boolean> _function_1 = (EClassifier it) -> {
      return Boolean.valueOf(it.getName().equals("Grid"));
    };
    EClassifier _findFirst_1 = IterableExtensions.<EClassifier>findFirst(model.getEClassifiers(), _function_1);
    EClass grid = ((EClass) _findFirst_1);
    EObject mapInstance = model.getEFactoryInstance().create(map);
    final Function1<EReference, Boolean> _function_2 = (EReference t) -> {
      return Boolean.valueOf(t.getName().equals("grids"));
    };
    EReference gridsReference = IterableExtensions.<EReference>findFirst(map.getEReferences(), _function_2);
    final Function1<EReference, Boolean> _function_3 = (EReference r) -> {
      return Boolean.valueOf(r.getName().equals("up"));
    };
    EReference up = IterableExtensions.<EReference>findFirst(grid.getEReferences(), _function_3);
    final Function1<EReference, Boolean> _function_4 = (EReference r) -> {
      return Boolean.valueOf(r.getName().equals("down"));
    };
    EReference down = IterableExtensions.<EReference>findFirst(grid.getEReferences(), _function_4);
    final Function1<EReference, Boolean> _function_5 = (EReference r) -> {
      return Boolean.valueOf(r.getName().equals("left"));
    };
    EReference left = IterableExtensions.<EReference>findFirst(grid.getEReferences(), _function_5);
    final Function1<EReference, Boolean> _function_6 = (EReference r) -> {
      return Boolean.valueOf(r.getName().equals("right"));
    };
    EReference right = IterableExtensions.<EReference>findFirst(grid.getEReferences(), _function_6);
    ArrayList<EObject> grids = Utils.createGrids(model.getEFactoryInstance(), grid, up, down, left, right, 10);
    Object _eGet = mapInstance.eGet(gridsReference);
    ((EList<EObject>) _eGet).addAll(grids);
    Utils.saveInstance(mapInstance);
  }
  
  public static ArrayList<EObject> createGrids(final EFactory factory, final EClass gridClass, final EReference up, final EReference down, final EReference left, final EReference right, final int width) {
    ArrayList<EObject> grids = new ArrayList<EObject>();
    EAttribute idAttr = gridClass.getEAttributes().get(0);
    int gridSize = (width * width);
    for (int i = 0; (i < gridSize); i++) {
      {
        EObject grid = factory.create(gridClass);
        grid.eSet(idAttr, Integer.valueOf((i + 1)));
        grids.add(grid);
      }
    }
    for (int i = 0; (i < gridSize); i++) {
      {
        if (((i % width) != 0)) {
          grids.get(i).eSet(left, grids.get((i - 1)));
        }
        if (((i % width) != (width - 1))) {
          grids.get(i).eSet(right, grids.get((i + 1)));
        }
        if ((i >= width)) {
          grids.get(i).eSet(up, grids.get((i - width)));
        }
        if ((i < (width * (width - 1)))) {
          grids.get(i).eSet(down, grids.get((i + width)));
        }
      }
    }
    return grids;
  }
  
  public static <RootType extends EObject> List<RootType> readModel(final Class<RootType> type, final String path, final String name) {
    try {
      try {
        final Resource resource = Utils.resSet.getResource(Utils.getURI(path, name), true);
        if ((resource == null)) {
          String _string = Utils.getURI(path, name).toString();
          throw new FileNotFoundException(_string);
        } else {
          EList<EObject> _contents = resource.getContents();
          return ((List<RootType>) _contents);
        }
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          e.printStackTrace();
          String _string_1 = Utils.getURI(path, name).toString();
          throw new Exception(_string_1);
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public static URI getURI(final String path, final String name) {
    return URI.createFileURI(((path + "/") + name));
  }
  
  public static map.Map loadInstance(final String directory) {
    MapPackage.eINSTANCE.getClass();
    ResourceSetImpl resSet = new ResourceSetImpl();
    Resource resource = resSet.getResource(URI.createURI(directory), true);
    EObject _get = resource.getContents().get(0);
    map.Map model = ((map.Map) _get);
    return model;
  }
  
  public static void saveInstance(final EObject root) {
    Resource resource = Utils.resSet.createResource(URI.createURI("instance.xmi"));
    resource.getContents().add(root);
    try {
      resource.save(Collections.EMPTY_MAP);
    } catch (final Throwable _t) {
      if (_t instanceof IOException) {
        final IOException e = (IOException)_t;
        e.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
}
