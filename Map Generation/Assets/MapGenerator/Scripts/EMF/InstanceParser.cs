using System.Collections.Generic;
using System.Xml.Linq;
using GeneratedClasses;
using UnityEngine;
using System.Linq;
using System.Reflection;
using System;
using System.Text.RegularExpressions;


public class InstanceParser :PipelineStage
{
    static XNamespace xsi = XNamespace.Get(EMFConfig.XMLNS_XSI);

    public object execute(object input)
    {
        Region r = ReadMapInstance((string)input);
        return r;
    }

    private XElement CreateXMLGridInstance(GeneratedClasses.Grid grid)
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

    public Region ReadMapInstance(string path)
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

    public void ResolveRelation(System.Reflection.FieldInfo attr, Region root, Region region, XElement ele)
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
                target = ((CompositeRegion)target).insides[pos];
                match = match.NextMatch();
            }
            attr.SetValue(region, target);

        }

        //set for child elements
        if (region is CompositeRegion)
        {
            var compositeRegion = (CompositeRegion)region;
            var xEles = ele.Elements().ToList(); 
            for(var i = 0; i < compositeRegion.insides.Count; i++)
            {
                ResolveRelation(attr, root, compositeRegion.insides[i], xEles[i]);
            }
        }
    }

    public Region ReadElement(XElement root, List<Type> types)
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
        if (rootObject is CompositeRegion)
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

    private List<GridType> GetGridTypesFromXMLElement(XElement grid)
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

    public string GetInfo()
    {
        return "Parsing Generated Graph...";
    }
}
