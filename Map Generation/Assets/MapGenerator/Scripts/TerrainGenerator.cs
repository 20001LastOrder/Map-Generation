using System.Collections;
using System.Collections.Generic;
using UnityEngine;


// referrencing Brackey's GENERATING TERRAIN tutorial
public class TerrainGenerator : MonoBehaviour
{
    public int depth = 20;

    public int width = 256;
    public int height = 256;

    public float scale = 20;

    void Update()
    {
        Terrain terrain = GetComponent<Terrain>();
        TerrainData terrainData = terrain.terrainData;

        terrainData.heightmapResolution = width + 1;
        terrainData.size = new Vector3(width, depth, height);

        float[,] height_data = new float[width, height];
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                float xCoord = (float)i / width * scale;
                float yCoord = (float)j / height * scale;

                height_data[i, j] = Mathf.PerlinNoise(xCoord, yCoord);
            }
        }

        terrainData.SetHeights(0, 0, height_data);
    }
}
