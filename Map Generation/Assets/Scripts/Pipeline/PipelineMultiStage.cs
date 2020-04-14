using System.Collections;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;

public class PipelineMultiStage : PipelineStage
{
    List<PipelineStage> stages;

    public PipelineMultiStage(List<PipelineStage> _stages)
    {
        this.stages = _stages;
    }

    public System.Object execute(System.Object input)
    {
        System.Object cur_input = input;
        foreach(var stage in stages)
        {
            float progress = (float)Pipeline.CurrentStageNumber / Pipeline.StagesLength;
            GraphEditor.Instance.ShowProgressBar(stage.GetInfo(), progress);
            cur_input = stage.execute(cur_input);
            Pipeline.CurrentStageNumber++;
        }

        return cur_input;
    }

    public string GetInfo()
    {
        return "Running Multistage...";
    }

    public int GetLength()
    {
        if(stages == null)
        {
            return 0;
        }

        return stages.Count;
    }
}
