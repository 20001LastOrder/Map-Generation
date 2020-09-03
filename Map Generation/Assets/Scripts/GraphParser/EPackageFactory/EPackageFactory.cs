using System;
using System.Collections.Generic;
using UnityEditor;
using UnityEngine;

public class EPackageFactory : PipelineStage
{ 
    public System.Object execute(System.Object input)
    {
        GraphEditor graphEditor = EditorWindow.GetWindow<GraphEditor>("Graph Editor");
        List<Node> nodes = graphEditor.getNodes();
        List<Connection> connections = graphEditor.GetConnections();

        List<EClass> eClasses = new List<EClass>();

        // Create necessary abstract classes
        var regionClass = new EClass("Region") { IsAbstract = true};
        var compositeRegionClass = new EClass("CompositeRegion") { IsAbstract = true, ESuperType = regionClass };
        var elementaryRegionClass = new EClass("ElementaryRegion") { IsAbstract = true, ESuperType = regionClass };
        
        var insides = new EReference("insides", regionClass) { UpperBound = -1, LowerBound = 0, Containment = true};
        var next = new EReference("next", regionClass) { UpperBound = 1, LowerBound = 0};
        
        compositeRegionClass.EReferences.Add(insides);
		regionClass.EReferences.Add(next);
		next.EOpposite = next;

        // set composite regions
        foreach(var connection in connections)
        {
            if(connection.type == ConnectionType.Insides)
            {
                connection.inPoint.node.isComposite = true;
            }
        }

        // Create User EClasses
        foreach (Node node in nodes)
        {
            EClass eClass = new EClass(node.title);
            foreach ((string type, string name) in node.GetAttributes())
            {
                string eType = "";
                switch (type)
                {
                    case "int":
                        eType = "ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EInt";
                        break;
                    case "double":
                        eType = "ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EDouble";
                        break;
                    case "string":
                        eType = "ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString";
                        break;
                    case "bool":
                        eType = "ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EBoolean";
                        break;
                }

                eClass.EAttributes.Add(new EAttribute(name, eType));
            }

            if (node.isComposite)
            {
                eClass.ESuperType = compositeRegionClass;
            }
            else
            {
                eClass.ESuperType = elementaryRegionClass;
            }
            eClasses.Add(eClass);
        }

        var package = new EPackage("map");
        eClasses.AddRange(new List<EClass> { regionClass, elementaryRegionClass, compositeRegionClass });
        package.EClasses.AddRange(eClasses);


        return package;
    }

    public string GetInfo()
    {
        return "Generating EPackage...";
    }
}
