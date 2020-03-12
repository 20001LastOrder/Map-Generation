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
            cur_input = stage.execute(cur_input);
        }

        return cur_input;
    }
}
