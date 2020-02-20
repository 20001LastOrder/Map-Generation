using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Pipeline 
{
    // add pipeline stages here in order of executation
    private static List<PipelineStage> pipelineStages =
       new List<PipelineStage>() {
        new EPackageFactory(),
        new Parser(),
        new EcoreParser(),
        new ConstraintParser(),
        new GraphSolverRunner(),
        //new MapGen()
       };
    public static void execute()
    {
        Debug.Log("-----Executing Pipeline-----");

        System.Object cur_input = null;
        foreach (PipelineStage stage in pipelineStages)
        {
            cur_input = stage.execute(cur_input);
        }
    }
}
