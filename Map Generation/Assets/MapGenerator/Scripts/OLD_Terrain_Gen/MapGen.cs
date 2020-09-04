using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MapGen : PipelineStage
{
    public System.Object execute(System.Object input)
    {
        Scene scene = SceneManager.GetActiveScene();
        GridManager gridManager = null;
        foreach(GameObject go in scene.GetRootGameObjects())
        {
            if(go.name.Equals("GridManager"))
            {
                gridManager = go.GetComponent<GridManager>();
                break;
            }
        }

        GameObject rootGridGO = new GameObject("rootGridGO");
        gridManager.m_rootGrid = rootGridGO.AddComponent<Grid>();
        gridManager.initRootGrid();

        return null;
    }

    public string GetInfo()
    {
        return "Map Generating...";
    }
}
