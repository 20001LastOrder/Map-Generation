using System;
using System.Collections.Generic;
using UnityEditor;
using UnityEngine;

public static class Generate
{
    public static EPackage generateEPackage(List<Node> nodes, List<Connection> connections)
    {
        List<EClass> eClasses = new List<EClass>();

        // Create necessary grid, gridtype, map classes
        EClass mapClass = new EClass("Map");
        EClass gridClass = new EClass("Grid");
        EClass gridTypeClass = new EClass("GridType")
        {
            IsAbstract = true
        };

        gridClass.EAttributes.Add(new EAttribute("id", "ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EInt") { IsId = true });

        var left = new EReference("left", gridClass);
        var right = new EReference("right", gridClass);
        var up = new EReference("up", gridClass);
        var down = new EReference("down", gridClass);
        gridClass.EReferences.AddRange(new List<EReference>() { left, right, up, down });
        left.EOpposite = right;
        up.EOpposite = down;

        EReference r = new EReference("grids", gridClass)
        {
            Conptainment = true,
            UpperBound = -1
        };
        mapClass.EReferences.Add(r);

        EReference typesRef = new EReference("types", gridTypeClass)
        {
            Conptainment = true,
            UpperBound = -1
        };
        gridClass.EReferences.Add(typesRef);

        EReference gridRef = new EReference("grid", gridClass);
        gridTypeClass.EReferences.Add(gridRef);
        typesRef.EOpposite = gridRef;

        EPackage package = new EPackage("map");

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

            eClass.ESuperType = gridTypeClass;
            eClasses.Add(eClass);
        }

        eClasses.AddRange(new List<EClass> { mapClass, gridClass, gridTypeClass });
        package.EClasses.AddRange(eClasses);

        return package;
    }
}
