using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Pipeline 
{
    private static List<PipelineStage> pipelineStages =
       new List<PipelineStage>() { };

    public static void execute()
    {
        foreach (PipelineStage stage in pipelineStages) stage.execute();
    }
}
