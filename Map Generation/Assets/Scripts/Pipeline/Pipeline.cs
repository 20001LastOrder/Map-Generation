using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public static class Pipeline 
{
    public enum Status
    {
        Idle, Stage1, Stage1Finished, Stage2
    }


    private static Status _status = Status.Idle;

    private static int _currentStageNumber = 0;

    private static int _overallStages = 0;
   
    private static object _intermediateOutput = null;

    public static Status CurrentStatus {
        get => _status;
        set => _status = value;
    }

    public static int CurrentStageNumber
    {
        get => _currentStageNumber;
        set => _currentStageNumber = value;
    }

    public static int StagesLength
    {
        get => _overallStages;
    }



    // add pipeline stages here in order of execution
    private static PipelineMultiStage pipelineStagesPart1 = new PipelineMultiStage(
       new List<PipelineStage>() {
        new EPackageFactory(),
        new Parser(),
        new EcoreParser(),
        new ConstraintParser(),
        new GenerationConfigRunner(),
        new GraphSolverRunner()
   });

    private static PipelineMultiStage pipelineStagesPart2 = new PipelineMultiStage(
        new List<PipelineStage>() {
        new InstanceParser(),
        new RegionParser(),
        new HeightMapGen(),
        new HeightMapDisplay()
   });
    public static void execute()
    {
        _overallStages = pipelineStagesPart1.GetLength() + pipelineStagesPart2.GetLength();
        _currentStageNumber = 0;
        if (_status == Status.Idle)
        {
            Debug.Log("-----Executing Pipeline Part 1-----");
            _status = Status.Stage1;
            _intermediateOutput = pipelineStagesPart1.execute(null);
        }else if(_status == Status.Stage1Finished)
        {
            Debug.Log("-----Executing Pipeline Part 2-----");
            _status = Status.Stage2;
            pipelineStagesPart2.execute(_intermediateOutput);
            _status = Status.Idle;
        }
        else
        {
            Debug.Log("A current running pipeline existing");
        }

    }
}
