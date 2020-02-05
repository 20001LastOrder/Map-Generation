using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using System.Xml.Linq;
using UnityEngine;
using System;
using System.Reflection;

public class GraphSolverRunner : PipelineStage
{
    [SerializeField]
    private string ecoreFileName = "map.ecore";

    public System.Object execute(System.Object input)
    {
        Debug.Log("-----Executing GraphSolverRunner-----");

        EPackage package = (EPackage)input;
        return Start(package);
    }

    // Start is called before the first frame update
    System.Object Start(EPackage package)
    { 
        var map = MapShapeGenerator.GenerateDefaultShape(GridManager.rootGridDim, GridManager.rootGridDim);
        InstanceParser.WriteMapInstance(map);
        runSolver();
        InstanceParser.ReadMapInstance("Assets/GraphSolver/output/1.xmi", map);
        Debug.Log("Map type count:" + map.Grids[1].Types.Count);

        return map;
    }

    /*void ReadXML()
    {
        XDocument document = XDocument.Load(Application.dataPath + "/GraphSolver/output/1.xmi");
        var root = document.Root;
        var map = new Map();
        var elements = root.Elements().ToArray();
        foreach (var ele in elements)
        {
            map.grids.Add(ReadElement(ele));
        }
        Debug.Log(map.grids[0]);

        for (var i = 0; i < map.grids.Count; i++)
        {
            ResolveCrossReference(map, elements[i], i);
        }

        Debug.Log(map.grids[0].left);
    }*/

    /*Grid ReadElement(XElement ele)
    {
        var typeName = ele.Attribute("{http://www.w3.org/2001/XMLSchema-instance}type").Value.Split(':')[1];
        Type elementType = Type.GetType(typeName);
        if (!elementType.IsSubclassOf(typeof(Grid)))
        {
            throw new Exception("bad type");
        }

        Grid specificElement = (Grid)Activator.CreateInstance(elementType);
        var elements = ele.Elements().ToArray();

        // if (elements.Length == 0) return null;


        return specificElement;
    }*/

    /*void ResolveCrossReference(Map root, XElement ele, int index)
    {
        var target = root.grids[index];
        var type = target.GetType();
        foreach (var attr in ele.Attributes())
        {
            var property = type.GetProperty(attr.Name.ToString());
            if (property == null) continue;
            var i = int.Parse(attr.Value.Split('.')[1]);

            property.SetValue(target, root.grids[i]);
        }
    }*/

    public void runSolver()
    {
        string path = Application.dataPath + "/GraphSolver/";
        System.Diagnostics.Process process = new System.Diagnostics.Process();
        System.Diagnostics.ProcessStartInfo startInfo = new System.Diagnostics.ProcessStartInfo();
        startInfo.WindowStyle = System.Diagnostics.ProcessWindowStyle.Hidden;
        startInfo.WorkingDirectory = path;
        startInfo.FileName = "java";
        startInfo.Arguments = "-jar app.jar map.vsconfig";
        startInfo.RedirectStandardOutput = true;
        startInfo.RedirectStandardError = true;
        startInfo.UseShellExecute = false;
        startInfo.CreateNoWindow = true;
        process.StartInfo = startInfo;
        process.Start();
        string output = process.StandardOutput.ReadToEnd();
        Debug.Log(output);
        process.WaitForExit();
        Debug.Log("Done");
    }
}
