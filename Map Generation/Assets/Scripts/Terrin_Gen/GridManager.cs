using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GridManager : MonoBehaviour
{
    public int rootGridDim = 10;
    public int rootGridSize = 10;

    Grid m_rootGrid;
    
    void initRootGrid()
    {
        if(m_rootGrid == null)
        {
            Debug.Log("Attempting to initialize root grid before instantiating it");
            return;
        }

        float offset = ((float)rootGridDim / 2.0f) * rootGridSize;
        Vector2 topLeftCorner = new Vector2(-offset, -offset);

        for(int r = 0; r < rootGridDim; r++)
        {
            List<Grid> row = new List<Grid>();
            float row_offset = (topLeftCorner.x * r) + ((float)rootGridSize / 2.0f);
            for(int c = 0; c < rootGridDim; c++)
            {
                float col_offset = (topLeftCorner.y * c) + ((float)rootGridSize / 2.0f);
                GameObject subGridGO = new GameObject("subGrid_" + r + "_" + c);
                subGridGO.transform.position = new Vector3(row_offset, 0, col_offset);
                subGridGO.transform.parent = m_rootGrid.transform;

                Grid subGrid = subGridGO.AddComponent<Grid>();
                subGrid.m_parentGrid = m_rootGrid;
            }
        }
    }

    void Start()
    {
        GameObject rootGridGO = new GameObject("rootGridGO");
        m_rootGrid = rootGridGO.AddComponent<Grid>();

        initRootGrid();
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
