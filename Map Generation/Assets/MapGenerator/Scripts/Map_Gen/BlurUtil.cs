using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;



public class BlurUtil
{
    public static Dictionary<int, double[,]> kernel_map = new Dictionary<int, double[,]>();

    private static void createGaussianKernel(double sigma, double[,] kernel)
    {
        int size = kernel.GetLength(0);
        int half_size = size / 2;
        double s = 2.0 * sigma * sigma;

        double sum = 0.0;
        for(int x = -half_size; x <= half_size; x++)
        {
            for(int y = -half_size; y <= half_size; y++)
            {
                double r = Math.Sqrt(x * x + y * y);
                kernel[x + half_size, y + half_size] = (Math.Exp(-(r * r) / s)) / (Math.PI * s);
                sum += kernel[x + half_size, y + half_size];
            }
        }

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                kernel[i,j] /= sum;
            }
        }
    }

    public static double[,] getGaussianKernel(int size)
    {
        if(!kernel_map.ContainsKey(size)) 
        {
            double[,] kernel = new double[size,size];
            createGaussianKernel(1.0, kernel);
            kernel_map[size] = kernel;
        }
        
        return kernel_map[size];
    }


    public static void blurRegionEdges(RegionInstance reg, float[,] heightMap, int offset)
    {
        int half_offset = offset / 2;
        float[,] heightMapCp = (float[,])heightMap.Clone();
        for(int c = 0;  c < (reg.size + offset); c++)
        {
            for(int r = 0; r < (reg.size + offset); r++)
            {
                int real_r = r + (int)reg.top_left.y - half_offset;
                int real_c = c + (int)reg.top_left.x - half_offset;

                if (c >= offset && c <= reg.size &&
                    r >= offset && r <= reg.size) continue;

                if (real_r < 0 || real_r >= heightMap.GetLength(0) ||
                    real_c < 0 || real_c >= heightMap.GetLength(1)) continue;
                heightMap[real_r, real_c] = Convert.ToSingle(blur(real_r, real_c, 13, heightMapCp));
            }
        }
    }

    public static double blur(int r, int c, int kernel_size, float[,] heightMap)
    {
        int half_size = kernel_size / 2;
        double sum = 0;
        double[,] kernel = getGaussianKernel(kernel_size);
        for(int i = -half_size; i <= half_size; i++)
        {
            for(int j = -half_size; j <= half_size; j++)
            {
                int new_r = r + i;
                int new_c = c + j;
                if (new_r < 0 || new_r >= heightMap.GetLength(0) ||
                    new_c < 0 || new_c >= heightMap.GetLength(1)) continue;

                sum += kernel[i + half_size, j + half_size] * heightMap[new_r, new_c];
            }
        }

        return sum;
    }
}
