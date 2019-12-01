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

        GeneratedClasses.Map map = (GeneratedClasses.Map)input;
        if (map.Grids.Count != (gridManager.m_rootGrid.m_subGrid.Count * gridManager.m_rootGrid.m_subGrid[0].Count)) Debug.Log("GRID SIZE MISMATCH!");
        else
        {
            int cur = 0;
            for (int r = 0; r < gridManager.m_rootGrid.m_subGrid.Count; r++)
            {
                for (int c = 0; c < gridManager.m_rootGrid.m_subGrid[r].Count; c++)
                {
                    gridManager.m_rootGrid.m_subGrid[r][c].gridData = map.Grids[cur++];
                }
            }
        }

        return null;
    }
}
