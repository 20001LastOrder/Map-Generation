using System;
using System.Collections;
using System.Collections.Generic;
using UnityEditor;
using UnityEngine;
using GeneratedClasses;

public class HeightMapParams
{
    public int octaves;
    public float persistence;
    public float scale;
    public float lacunarity;
    public float meshHeightMultiplier;
	public float height;
    public AnimationCurve meshHeightCurve;
    public AnimationCurve heightRemapCurve;

	public HeightMapParams(int _octaves, float _scale, float _height, float _persistence, 
        float _lacunarity, float _meshHeightMultiplier, AnimationCurve _heightCurve,
        AnimationCurve _remapCurve)
    {
        octaves = _octaves;
        persistence = _persistence;
        scale = _scale;
        lacunarity = _lacunarity;
        meshHeightMultiplier = _meshHeightMultiplier;
        meshHeightCurve = _heightCurve;
        heightRemapCurve = _remapCurve;
		height = _height;
    }
}

public class DisplayData
{
    public float[,] heightMap;
    public Color[] colorMap;

    public DisplayData(float[,] height, Color[] color)
    {
        heightMap = height;
        colorMap = color;
    }
}

public class HeightMapGen : PipelineStage
{
    GraphEditor graphEditor;
    List<Node> nodes;

    public System.Object execute(System.Object input)
    {
        Debug.Log("-----Executing HeightMapGen-----");

        graphEditor = EditorWindow.GetWindow<GraphEditor>("Graph Editor");
        nodes = graphEditor.getNodes();

        int map_size = RegionParser.map_size;

        float[,] heightMap = new float[map_size, map_size];
        Color[] colorMap = new Color[map_size * map_size];
        fillHeightMap(heightMap, (RegionInstance)input);

        // TODO: this is a very bad hack, replace with something better

        TerrainType[] regions = GameObject.FindObjectOfType<MapGenerator>().regions;

        for (int y = 0; y < map_size; y++)
        {
            for (int x = 0; x < map_size; x++)
            {
                float curHeight = heightMap[x, y];
                for (int i = 0; i < regions.Length; i++)
                {
                    if (curHeight <= regions[i].height)
                    {
						colorMap[y * map_size + x] = regions[i].color;
                        break;
                    }
                }
            }
        }

        return new DisplayData(heightMap, colorMap);
    }

    private void fillHeightMap(float[,] heightMap, RegionInstance reg)
    {
        HeightMapParams hParams = getHeightMapParams(getRegionTypeString(reg.region));

        int seed = (int)DateTimeOffset.Now.ToUnixTimeMilliseconds();

		float[,] curHeightMap = Noise.getNoiseMap(reg.size, reg.size, 
            seed, hParams.scale, hParams.octaves,
            hParams.persistence, hParams.lacunarity, Vector2.zero, hParams.height);

        for(int c = 0; c < reg.size; c++)
        {
            for(int r = 0; r < reg.size; r++)
            {
                float val = curHeightMap[r, c] * 
                    hParams.meshHeightMultiplier *
                    hParams.meshHeightCurve.Evaluate(curHeightMap[r, c]);
                heightMap[r + (int)reg.top_left.y, c + (int)reg.top_left.x] = 
                    hParams.heightRemapCurve.Evaluate(val);
            }
        }
        BlurUtil.blurRegionEdges(reg, heightMap, 13);

        foreach(RegionInstance child in reg.children)
        {
            fillHeightMap(heightMap, child);
        }
    }

    public HeightMapParams getHeightMapParams(string regionName)
    {
        GraphEditor graphEditor = EditorWindow.GetWindow<GraphEditor>("Graph Editor");
        List<Node> nodes = graphEditor.getNodes();

        HeightMapParams heightMapParams = null;
        foreach(Node node in nodes)
        {
            if(node.title.Equals(regionName))
            {
                heightMapParams = new HeightMapParams(node.octaves, node.scale, node.noiseHeight,
                    node.persistence, node.lacunarity, node.meshHeightMultiplier,
                    node.meshHeightCurve, node.heightRemap);

                break;
            }
        }

        if(heightMapParams == null)
        {
            Debug.Log("WARNING: Unable to find height map params for " + regionName);
        }
        return heightMapParams;
    }

    public string getRegionTypeString(Region region)
    {
        string full = region.GetType().ToString();
        return full.Substring(full.IndexOf('.') + 1);
    }

    public string GetInfo()
    {
        return "Visualizing...";
    }
}
