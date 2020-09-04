using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HeightMapDisplay : PipelineStage
{

    public System.Object execute(System.Object input)
    {
        Debug.Log("-----Executing HeightMapDisplay-----");

        DisplayData displayData = (DisplayData)input;
        float[,] heightMap = (float[,])displayData.heightMap;
        Color[] colorMap = displayData.colorMap;

        GameObject plane = GameObject.Find("NoiseMapPlane");
        Texture2D noiseTexture = TextureGenerator.TextureFromHeightMap(heightMap);

        Renderer textureRender = plane.GetComponent<Renderer>();
        textureRender.sharedMaterial.mainTexture = noiseTexture;
        textureRender.transform.localScale = new Vector3(noiseTexture.width, 1, noiseTexture.height);

        MeshData meshData = MeshGenerator.GenerateTerrainMesh(heightMap,
            10.0f, AnimationCurve.Linear(0.0f, 0.0f, 1.0f, 1.0f));

        //Color[] colorMap = new Color[heightMap.GetLength(0) * heightMap.GetLength(1)];
        Texture2D colorTexture = TextureGenerator.TextureFromColorMap(colorMap, heightMap.GetLength(0),
            heightMap.GetLength(1));

        GameObject mesh = GameObject.Find("Mesh");
        mesh.GetComponent<MeshFilter>().sharedMesh = meshData.CreateMesh();
        mesh.GetComponent<MeshRenderer>().sharedMaterial.mainTexture = colorTexture;

        return null;
    }

    public string GetInfo()
    {
        return "Finalizing Visualization...";
    }
}
