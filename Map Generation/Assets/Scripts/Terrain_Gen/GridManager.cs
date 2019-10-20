using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GridManager : MonoBehaviour
{
    public int rootGridDim = 10;
    public int rootGridSize = 10;
    public Material greenMaterial;

    Grid m_rootGrid;
    
    void initRootGrid()
    {
        if(m_rootGrid == null)
        {
            Debug.Log("Attempting to initialize root grid before instantiating it");
            return;
        }

        float offset = ((float)rootGridDim / 2.0f) * rootGridSize;
        float halfGrid = (float)rootGridSize / 2.0f;
        Vector2 topLeftCorner = new Vector2(-offset, -offset);

        Debug.Log(topLeftCorner);

        for(int r = 0; r < rootGridDim; r++)
        {
            List<Grid> row = new List<Grid>();
            float row_offset = topLeftCorner.x + (rootGridSize * r) + halfGrid;
            for(int c = 0; c < rootGridDim; c++)
            {
                float col_offset = topLeftCorner.y + (rootGridSize * c) + halfGrid;
                GameObject subGridGO = new GameObject("subGrid_" + r + "_" + c);
                subGridGO.transform.position = new Vector3(row_offset, 0, col_offset);
                subGridGO.transform.parent = m_rootGrid.transform;

                // add Grid component
                Grid subGrid = subGridGO.AddComponent<Grid>();
                subGrid.m_parentGrid = m_rootGrid;

                // add LineRenderer component
                LineRenderer lineRenderer = subGridGO.AddComponent<LineRenderer>();
                Vector3[] points = new Vector3[5];
                points[0] = subGridGO.transform.position + new Vector3(-halfGrid, 0, -halfGrid);
                points[1] = subGridGO.transform.position + new Vector3(-halfGrid, 0, halfGrid);
                points[2] = subGridGO.transform.position + new Vector3(halfGrid, 0, halfGrid);
                points[3] = subGridGO.transform.position + new Vector3(halfGrid, 0, -halfGrid);
                points[4] = subGridGO.transform.position + new Vector3(-halfGrid, 0, -halfGrid);
                lineRenderer.positionCount = 5;
                lineRenderer.SetPositions(points);
                lineRenderer.SetWidth(0.1f, 0.1f);

                // add plane child GO
                GameObject planeGO = GameObject.CreatePrimitive(PrimitiveType.Plane);
                planeGO.transform.parent = subGridGO.transform;
                planeGO.transform.localPosition = Vector3.zero;
                planeGO.GetComponent<MeshRenderer>().material = greenMaterial;

                Mesh mesh = planeGO.GetComponent<MeshFilter>().mesh;
                Vector3[] verts = mesh.vertices;
                int vertDim = (int)Mathf.Sqrt(verts.Length);
                float[,] noise = NoiseMapGeneration.GenerateNoiseMap(vertDim, vertDim, Random.value);
                for(int i = 0; i < vertDim; i++)
                {
                    for(int j = 0; j < vertDim; j++)
                    {
                        verts[i * vertDim + j].y = noise[i,j];
                    }
                }
                mesh.vertices = verts;
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
