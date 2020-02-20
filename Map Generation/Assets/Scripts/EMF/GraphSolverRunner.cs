using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using System.Xml.Linq;
using UnityEngine;
using System;
using System.Reflection;
using GeneratedClasses;

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
        //var map = MapShapeGenerator.GenerateDefaultShape(GridManager.rootGridDim, GridManager.rootGridDim);
        // No need to write Map Instance Anymore
        //InstanceParser.WriteMapInstance(map);
        runSolver();
        Region r = InstanceParser.ReadMapInstance("Assets/GraphSolver/output/1.xmi");
        //Debug.Log("Map type count:" + map.Grids[1].Types.Count);

        return r;
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
