using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public static class Noise 
{
    public static float[,] getNoiseMap(int width, int height, int seed, float scale, 
        int octaves, float persistance, float lacunarity, Vector2 offset, float origin)
    {
        float[,] noiseMap = new float[width, height];

        System.Random rng = new System.Random(seed);
        Vector2[] octaveOffsets = new Vector2[octaves];
        for(int i = 0; i < octaves; i++)
        {
            float offsetX = rng.Next(-100000, 100000) + offset.x;
            float offsetY = rng.Next(-100000, 100000) + offset.y;
            octaveOffsets[i] = new Vector2(offsetX, offsetY);
        }

        if (scale <= 0) scale = 0.0001f;


        float halfWidth = width / 2.0f;
        float halfHeight = height / 2.0f;

        float maxHeight = float.MinValue;
        float minHeight = float.MaxValue;
        for(int r = 0; r < height; r++)
        {
            for(int c = 0; c < width; c++)
            {

                float amplitude = 1;
                float frequency = 1;
                float noiseHeight = 0;

                for(int i = 0; i < octaves; i++)
                {
                    float xCoord = ((float)(c - halfWidth) / scale * frequency) + octaveOffsets[i].x;
                    float yCoord = ((float)(r - halfHeight) / scale * frequency) + octaveOffsets[i].y;

                    float perlinValue = Mathf.PerlinNoise(xCoord, yCoord) * 2.0f - 1.0f;
                    noiseHeight += perlinValue * amplitude;

                    amplitude *= persistance;
                    frequency *= lacunarity;
                }

                maxHeight = Mathf.Max(maxHeight, noiseHeight);
                minHeight = Mathf.Min(minHeight, noiseHeight);

                noiseMap[c, r] = noiseHeight;
            }
        }


        for (int r = 0; r < height; r++)
        {
            for (int c = 0; c < width; c++)
            {
                noiseMap[c, r] = origin + Mathf.InverseLerp(minHeight, maxHeight, noiseMap[c, r]);
            }
        }

        return noiseMap;
    }
}
