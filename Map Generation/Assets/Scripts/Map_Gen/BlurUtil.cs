using System.Collections;
using System.Collections.Generic;
using UnityEngine;



public class BlurUtil
{
    static float[,] gaussian_5 = new float[5, 5]
    {
        { 1, 4, 6, 4, 1 },
        {4, 16, 24, 16, 4 },
        {6, 24, 36, 24, 6 },
        {4, 16, 24, 16, 4 },
        {1, 4, 6, 4, 1 }
    };

    public static void blurRegionEdges(RegionInstance reg, float[,] heightMap, int offset)
    {
        for(int c = 0;  c < (reg.size + (offset * 2)); c++)
        {
            for(int r = 0; r < (reg.size + (offset * 2)); r++)
            {
                int real_r = r + (int)reg.top_left.y - offset;
                int real_c = c + (int)reg.top_left.x - offset;

                if (c >= offset && c <= (offset + reg.size) &&
                    r >= offset && r <= (offset + reg.size)) continue;

                if (real_r < 0 || real_r >= heightMap.GetLength(0) ||
                    real_c < 0 || real_c >= heightMap.GetLength(1)) continue;
                heightMap[real_r, real_c] = blur55(real_r, real_c, heightMap);
            }
        }
    }

    public static float blur55(int r, int c, float[,] heightMap)
    {
        float sum = 0;
        for(int i = -2; i <= 2; i++)
        {
            for(int j = -2; j <= 2; j++)
            {
                int new_r = r + i;
                int new_c = c + j;
                if (new_r < 0 || new_r >= heightMap.GetLength(0) ||
                    new_c < 0 || new_c >= heightMap.GetLength(1)) continue;

                sum += gaussian_5[i + 2, j + 2] * heightMap[new_r, new_c];
            }
        }

        return sum / 256.0f;
    }
}
