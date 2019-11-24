using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Pipeline 
{
    // add pipeline stages here in order of executation
    private static List<PipelineStage> pipelineStages =
       new List<PipelineStage>() { new EPackageFactory() };

    public static void execute()
    {
        Debug.Log("-----Executing Pipeline-----");
        foreach (PipelineStage stage in pipelineStages) stage.execute();
    }
}
