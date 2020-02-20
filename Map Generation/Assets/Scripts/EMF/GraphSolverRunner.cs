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
using System.Threading;
public class GraphSolverRunner : PipelineStage
{
    [SerializeField]
    private string ecoreFileName = "map.ecore";

    private volatile bool isSolverRunning = false;
    private string path;
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
        path = Application.dataPath + "/GraphSolver/";
        isSolverRunning = true;
        System.Diagnostics.Process process = new System.Diagnostics.Process();
        System.Diagnostics.ProcessStartInfo startInfo = new System.Diagnostics.ProcessStartInfo();
        startInfo.WindowStyle = System.Diagnostics.ProcessWindowStyle.Normal;
        startInfo.WorkingDirectory = path;
        startInfo.FileName = "java";
        startInfo.Arguments = "-jar app.jar map.vsconfig";
        startInfo.RedirectStandardOutput = false;
        startInfo.RedirectStandardError = false;
        startInfo.UseShellExecute = true;
        //startInfo.CreateNoWindow = true;
        process.StartInfo = startInfo;
        process.Start();
        //string output = process.StandardOutput.ReadToEnd();
        //Debug.Log(output);
        process.WaitForExit();
        isSolverRunning = false;
        Debug.Log("Done");
    }
}
