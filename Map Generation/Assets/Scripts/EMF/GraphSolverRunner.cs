using UnityEngine;
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
        string path = Application.dataPath + "/GraphSolver/";
        Thread t = new Thread(RunSolver);
        t.Start(path);
        string outputFilename = "Assets/GraphSolver/output/1.xmi";
        return outputFilename;
    }

    private static void OutputDataReceived(object sender,  System.Diagnostics.DataReceivedEventArgs e)
    {
        if(e.Data != null)
        {
            Debug.Log(e.Data);
        }
    }

    private static void RunSolver(object input)
    {
        string path = (string)input;
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
        process.OutputDataReceived += OutputDataReceived;
        process.Start();
        process.BeginOutputReadLine();
        process.WaitForExit();
        Pipeline.CurrentStatus = Pipeline.Status.Stage1Finished;
        Debug.Log("Solver Generation Done");
    }
}
