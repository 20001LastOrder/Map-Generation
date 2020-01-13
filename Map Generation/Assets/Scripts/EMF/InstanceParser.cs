using System.Collections;
using System.Collections.Generic;
using System.Xml;
using System.Xml.Linq;
using GeneratedClasses;
using UnityEngine;
using System.Linq;
using System.Reflection;
using System.Text;
using System;

public static class InstanceParser 
{
    public static void WriteMapInstance(Map map)
    {
        var version = XNamespace.Get(EMFConfig.XMI_VERION);
        var xmi = XNamespace.Get(EMFConfig.XMLNS_XMI);
        var mapPackage = XNamespace.Get(EMFConfig.XMLNS_MAP_MAP);
        
        var document = new XDocument(
            new XDeclaration("1.0", "ASCII", "yes")
        );
        var root = new XElement(
                   mapPackage + "Map",
                   new XAttribute(xmi+"version", version.NamespaceName),
                   new XAttribute(XNamespace.Xmlns + mapPackage.NamespaceName, mapPackage.NamespaceName),
                   new XAttribute(XNamespace.Xmlns + "xmi", xmi.NamespaceName)
        );

        foreach(var grid in map.Grids){
            root.Add(CreateXMLGridInstance(grid));
        }
        document.Add(root);
        document.Save("Assets/GraphSolver/instance1.xmi");
    }

    private static XElement CreateXMLGridInstance(GeneratedClasses.Grid grid)
    {
        var element = new XElement("grids");

        //add attribute if exist
        if (grid.Left != null)
        {
            element.Add(new XAttribute("left", grid.Left.Id));
        }

        if (grid.Right != null)
        {
            element.Add(new XAttribute("right", grid.Right.Id));
        }

        if (grid.Up != null)
        {
            element.Add(new XAttribute("up", grid.Up.Id));
        }

        if (grid.Down != null)
        {
            element.Add(new XAttribute("down", grid.Down.Id));
        }
        element.Add(new XAttribute("id", grid.Id));

        //TODO: Optionally add the grid types

        return element;
    }

    public static Map ReadMapInstance(string path, Map map)
    {
        var document = XDocument.Load(path);
        XName id = "id";
        var root = document.Root;

        foreach(var ele in root.Elements().ToArray())
        {
            var grid = map.Grids[int.Parse(ele.Attribute(id).Value)-1];
            grid.Types = GetGridTypesFromXMLElement(ele);
        }

        return map;
    }

    private static List<GridType> GetGridTypesFromXMLElement(XElement grid)
    {
        var xsi = XNamespace.Get(EMFConfig.XMLNS_XSI);

        List<GridType> types = new List<GridType>();
        foreach(var ele in grid.Elements().ToArray())
        {
            var type = ele.Attribute(xsi + "type").Value.Split(':')[1];
            var GridClass = typeof(GridType);
            var generatedTypes = Assembly.GetExecutingAssembly().GetTypes()
                       .Where(t => t.Namespace == "GeneratedClasses")
                       .ToList();
            var subtype = generatedTypes.Find(a => a.Name.Equals(type));
            types.Add((GridType)Activator.CreateInstance(subtype));
        }

        return types;
    }
}
