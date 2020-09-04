using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MapGenerator : MonoBehaviour
{
    public enum DrawMode
    {
        NoiseMap,
        ColorMap,
        Mesh
    };
    public DrawMode drawMode;

    public int mapWidth = 100;
    public int mapHeight = 100;
    public float noiseScale = 0.5f;

    public int octaves;

    [Range(0,1)]
    public float persistance;
    public float lacunarity;

    public bool autoUpdate;

    public int seed;
    public Vector2 offset;

    public float meshHeightMultiplier;
    public AnimationCurve meshHeightCurve;

    public TerrainType[] regions;

    public void generateMap()
    {
        float[,] noiseMap = Noise.getNoiseMap(mapWidth, mapHeight, seed,
            noiseScale, octaves, persistance, lacunarity, offset, 0);

        Color[] colorMap = new Color[mapWidth * mapHeight];
        for (int y = 0; y < mapHeight; y++)
        {
            for (int x = 0; x < mapWidth; x++)
            {
                float curHeight = noiseMap[x, y];
                for (int i = 0; i < regions.Length; i++)
                {
                    if (curHeight <= regions[i].height)
                    {
                        colorMap[y * mapWidth + x] = Color.grey;
                        break;
                    }
                }
            }
        }

		MapDisplay display = FindObjectOfType<MapDisplay>();

		if (drawMode == DrawMode.NoiseMap)
        {
            display.DrawTexture(TextureGenerator.TextureFromHeightMap(noiseMap));
        }
        else if(drawMode == DrawMode.ColorMap)
        {
            display.DrawTexture(
                TextureGenerator.TextureFromColorMap(colorMap, mapWidth, mapHeight));
        }
        else if(drawMode == DrawMode.Mesh)
        {
			//display.DrawMesh(
			//    MeshGenerator.GenerateTerrainMesh(noiseMap, 
			//        meshHeightMultiplier, meshHeightCurve),
			//    TextureGenerator.TextureFromColorMap(colorMap, mapWidth, mapHeight));
			var displayData = new DisplayData(noiseMap, colorMap);
			HeightMapDisplay meshDisplay = new HeightMapDisplay();
			meshDisplay.execute(displayData);
		}
    }

    void OnValidate()
    {
        if (mapWidth < 1) mapWidth = 1;
        if (mapHeight < 1) mapHeight = 1;
        if (lacunarity < 1) lacunarity = 1;
        if (octaves < 0) octaves = 0;
    }
}

[System.Serializable]
public struct TerrainType
{
    public string name;
    public float height;
    public Color color;
}
