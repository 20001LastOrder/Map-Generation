using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public interface PipelineStage
{
    System.Object execute(System.Object input);
}
