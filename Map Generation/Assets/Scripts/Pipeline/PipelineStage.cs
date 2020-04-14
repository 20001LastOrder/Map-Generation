using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public interface PipelineStage
{
    string GetInfo();
    System.Object execute(System.Object input);
}
