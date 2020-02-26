using System.Collections.Generic;
using System.Xml.Linq;
using GeneratedClasses;
using UnityEngine;
using System.Linq;
using System.Reflection;
using System;
using System.Text.RegularExpressions;


public static class InstanceParser 
{
    static XNamespace xsi = XNamespace.Get(EMFConfig.XMLNS_XSI);
    /*    public static void WriteMapInstance(Map map)
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
        }*/

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

    public static Region ReadMapInstance(string path)
    {
        var document = XDocument.Load(path);
        var root = document.Root;

        var generatedTypes = Assembly.GetExecutingAssembly().GetTypes()
                       .Where(t => t.Namespace == "GeneratedClasses")
                       .ToList();
        // the first element is different
        // create composition hierarchy first
        var type = root.Name.ToString().Split('}')[1];
        var subtype = generatedTypes.Find(a => a.Name.Equals(type));
        var rootObject = (CompositeRegion)Activator.CreateInstance(subtype);
        rootObject.insides = new List<Region>();
        foreach(var ele in root.Elements().ToList())
        {
            rootObject.insides.Add(ReadElement(ele, generatedTypes));
        }

        //figure out associations
        foreach(var property in typeof(Region).GetFields())
        {
            ResolveRelation(property, rootObject, rootObject, root);
        }

        return rootObject;
    }

    public static void ResolveRelation(System.Reflection.FieldInfo attr, Region root, Region region, XElement ele)
    {
        var xAttr = ele.Attribute(attr.Name);


        // if the field is defined
        if (xAttr != null)
        {
            // if not null, parse the value and find corresponding reference
            var value = xAttr.Value;
            // find allocation index
            var match = Regex.Match(value, "[0-9]+");
            Region target = root;
            while (match.Success)
            {
                int pos = int.Parse(match.Value);
                Debug.Log(pos);
                target = ((CompositeRegion)target).insides[pos];
                match = match.NextMatch();
            }
            attr.SetValue(region, target);

        }

        //set for child elements
        if (ele.HasElements)
        {
            var compositeRegion = (CompositeRegion)region;
            var xEles = ele.Elements().ToList(); 
            for(var i = 0; i < compositeRegion.insides.Count; i++)
            {
                ResolveRelation(attr, root, compositeRegion.insides[i], xEles[i]);
            }
        }
    }

    public static Region ReadElement(XElement root, List<Type> types)
    {
        var type = root.Attribute(xsi + "type").Value.Split(':')[1];

        var regionCLass = typeof(Region);
        var subtype = types.Find(a => a.Name.Equals(type));
        //error handling
        if(subtype == null)
        {
            Debug.LogError("ERROR: No Such Class Generated: " + subtype);
            return null;
        }
        var rootObject = (Region)Activator.CreateInstance(subtype);
        // Composite region with elements inside
        if (root.HasElements)
        {
            var realRootObject = (CompositeRegion)rootObject;
            realRootObject.insides = new List<Region>();
            foreach (var ele in root.Elements().ToList())
            {
                realRootObject.insides.Add(ReadElement(ele, types));
            }
        }

        return rootObject;
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
