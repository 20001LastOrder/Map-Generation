using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using System.Xml.Linq;
using UnityEngine;
using System;
using System.Reflection;

public class GraphSolverRunner : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        // create classes
        EClass mapClass = new EClass("Map");
        EClass gridClass = new EClass("Grid");
        EClass gridTypeClass = new EClass("GridType")
        {
            IsAbstract = true
        };
        EClass cityClass = new EClass("City");
        EClass mountainClass = new EClass("Mountain");
        EClass villageClass = new EClass("Village");
        EClass riverClass = new EClass("river");

        var left = new EReference("left", gridClass);
        var right = new EReference("right", gridClass);
        var up = new EReference("up", gridClass);
        var down = new EReference("down", gridClass);
        gridClass.EReferences.AddRange(new List<EReference>() { left, right, up, down });
        left.EOpposite = right;
        up.EOpposite = down;

        cityClass.ESuperType = gridTypeClass;
        mountainClass.ESuperType = gridTypeClass;
        villageClass.ESuperType = gridTypeClass;
        riverClass.ESuperType = gridTypeClass;

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
        package.EClasses.AddRange(new List<EClass>() { mapClass, gridClass, gridTypeClass, cityClass, villageClass, mountainClass, riverClass });


        EcoreParser.SaveEcore(package);
        Debug.Log("finished creating package");
        //runSolver();

        //ReadXML();
    }

    void ReadXML()
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
    }

    Grid ReadElement(XElement ele)
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
    }

    void ResolveCrossReference(Map root, XElement ele, int index)
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
    }

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
