using UnityEngine;
using System.Threading;
using System.Linq;

public class GraphSolverRunner : PipelineStage
{
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
        string path = ProjectConfig.ABSOLUTE_PROJECT_PATH + "/GraphSolver/";
        Thread t = new Thread(RunSolver);
        t.Start(path);
        string outputFilename = ProjectConfig.ABSOLUTE_PROJECT_PATH + "/GraphSolver/output/1.xmi";
        return outputFilename;
    }

    private static void OutputDataReceived(object sender,  System.Diagnostics.DataReceivedEventArgs e)
    {
        float progress = (float)Pipeline.CurrentStageNumber / Pipeline.StagesLength;
        if (e.Data != null)
        {
            if (e.Data.All(char.IsDigit))
            {
                GraphEditor.Instance.ShowProgressBarAsync("Graph Generation Status: Current Generated Regions: " + e.Data, progress);
            }
            else
            {
                GraphEditor.Instance.ShowProgressBarAsync("Graph Generation Status: " + e.Data, progress);
            }
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

    public string GetInfo()
    {
        return "Running Graph Generator...";
    }
}
