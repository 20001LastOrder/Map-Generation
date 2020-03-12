using System.Collections;
using System.Collections.Generic;
using UnityEditor;
using UnityEngine;
using GeneratedClasses;

public class HeightMapParams
{
    public int octaves;
    public float persistence;
    public float lacunarity;
    public float meshHeightMultiplier;
    public AnimationCurve meshHeightCurve;

    public HeightMapParams(int _octaves, float _persistence,
        float _lacunarity, float _meshHeightMultiplier, AnimationCurve _heightCurve)
    {
        octaves = _octaves;
        persistence = _persistence;
        lacunarity = _lacunarity;
        meshHeightMultiplier = _meshHeightMultiplier;
        meshHeightCurve = _heightCurve;
    }
}

public class HeightMapGen : PipelineStage
{
    GraphEditor graphEditor;
    List<Node> nodes;

    public System.Object execute(System.Object input)
    {
        Debug.Log("-----Executing HeighMapGen-----");

        graphEditor = EditorWindow.GetWindow<GraphEditor>("Graph Editor");
        nodes = graphEditor.getNodes();

        float[,] heightMap = new float[(int)RegionParser.map_size,
            (int)RegionParser.map_size];
        fillHeightMap(ref heightMap, (RegionInstance)input);

        return null;
    }

    private void fillHeightMap(ref float[,] heightMap, RegionInstance reg)
    {
        HeightMapParams hParams = getHeightMapParams(getRegionTypeString(reg.region));
        float[,] curHeightMap = Noise.getNoiseMap(reg.size, reg.size, 
            42, 80.0f, hParams.octaves,
            hParams.persistence, hParams.lacunarity, Vector2.zero);

        for(int c = 0; c < reg.size; c++)
        {
            for(int r = 0; r < reg.size; r++)
            {
                heightMap[r + (int)reg.top_left.y, c + (int)reg.top_left.x] =
                    curHeightMap[r, c];
            }
        }

        foreach(RegionInstance child in reg.children)
        {
            fillHeightMap(ref heightMap, child);
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
                heightMapParams = new HeightMapParams(node.octaves,
                    node.persistence, node.lacunarity, node.meshHeightMultiplier,
                    node.meshHeightCurve);

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


}
