using System.Collections;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;

public class PipelineMultiStage : PipelineStage
{
    List<PipelineStage> stages;

    public void execute()
    {
        Parallel.ForEach(stages, (stage) =>
        {
            stage.execute();
        });
    }
}
