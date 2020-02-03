using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MapGenerator : MonoBehaviour
{
    public int mapWidth = 100;
    public int mapHeight = 100;
    public float noiseScale = 0.5f;
    public bool autoUpdate;

    public void generateMap()
    {
        float[,] noiseMap = Noise.getNoiseMap(mapWidth, mapHeight, noiseScale);

        MapDisplay display = FindObjectOfType<MapDisplay>();
        display.DrawNoiseMap(noiseMap);
    }
}
