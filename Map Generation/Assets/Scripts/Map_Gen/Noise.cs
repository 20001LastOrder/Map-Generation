using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Noise : MonoBehaviour
{
    public static float[,] getNoiseMap(int width, int height, float scale)
    {
        float[,] noiseMap = new float[width, height];

        for(int r = 0; r < height; r++)
        {
            for(int c = 0; c < width; c++)
            {
                float xCoord = (float)r / scale;
                float yCoord = (float)c / scale;

                noiseMap[r, c] = Mathf.PerlinNoise(xCoord, yCoord);
            }
        }

        return noiseMap;
    }
}
